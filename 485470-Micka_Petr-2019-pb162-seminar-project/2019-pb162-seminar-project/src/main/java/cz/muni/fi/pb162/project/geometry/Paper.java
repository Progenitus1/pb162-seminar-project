package cz.muni.fi.pb162.project.geometry;

import cz.muni.fi.pb162.project.exception.EmptyDrawableException;
import cz.muni.fi.pb162.project.exception.MissingVerticesException;
import cz.muni.fi.pb162.project.exception.TransparentColorException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Petr Micka
 * It is representation of Paper in two dimensional space. You can draw on it.
 */
public class Paper implements Drawable,PolygonFactory {
    private final Set<ColoredPolygon> canvas;
    private Color pen;

    /**
     *
     * @param paper old paper
     *  creates new paper
     */
    public Paper(Drawable paper){
        canvas = new HashSet<ColoredPolygon>(paper.getAllDrawnPolygons());
        pen = Color.BLACK;
    }

    /**
     * creates new paper
     */
    public Paper(){
        canvas = new HashSet<ColoredPolygon>();
        pen = Color.BLACK;
    }
    @Override
    public void changeColor(Color color) {
        this.pen = color;
    }

    @Override
    public void drawPolygon(Polygon polygon) throws TransparentColorException {
        if(pen == Color.WHITE){
            throw new TransparentColorException("pen has white color");
        }
        canvas.add(new ColoredPolygon(polygon,pen));
    }

    @Override
    public void erasePolygon(ColoredPolygon polygon) {
        canvas.remove(polygon);
    }

    @Override
    public void eraseAll() throws EmptyDrawableException {
        if(canvas.isEmpty()){
            throw new EmptyDrawableException("canvas is empty");
        }
        canvas.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Paper paper = (Paper) o;
        return Objects.equals(canvas, paper.canvas) &&
                pen == paper.pen;
    }

    @Override
    public int hashCode() {
        return Objects.hash(canvas, pen);
    }

    @Override
    public Collection<ColoredPolygon> getAllDrawnPolygons() {
        return Collections.unmodifiableCollection(canvas);

    }

    @Override
    public int uniqueVerticesAmount() {
        Set<Vertex2D> result = new HashSet<>();
        for (ColoredPolygon coloredPolygon : canvas){
            Polygon polygon = coloredPolygon.getPolygon();
            for(int i = 0; i < polygon.getNumVertices();i++){
                result.add(polygon.getVertex(i));
            }
        }
        return result.size();
    }

    @Override
    public Polygon tryToCreatePolygon(List<Vertex2D> vertices) throws MissingVerticesException {
        if(vertices == null){
            throw new NullPointerException("vertices is null");
        }
        List<Vertex2D> result;
        try{
            result = List.copyOf(vertices);
            return new CollectionPolygon(result);
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(vertices);
            result = new ArrayList<Vertex2D>(){};
            for(Vertex2D vertex2D : vertices){
                if(vertex2D != null){
                    result.add(new Vertex2D(vertex2D.getX(),vertex2D.getY()));
                }
            }
            return new CollectionPolygon(result);
        }
    }

    @Override
    public void tryToDrawPolygons(List<List<Vertex2D>> collectionPolygons) throws EmptyDrawableException {
        Throwable last = null;
        int drawn = 0;
        for(List<Vertex2D> polygonVertices : collectionPolygons){
            try {
                Polygon polygon = tryToCreatePolygon(polygonVertices);
                drawPolygon(polygon);
                drawn++;
            } catch (MissingVerticesException | IllegalArgumentException | NullPointerException ex){
                last = ex;
            } catch (TransparentColorException ex){
                changeColor(Color.BLACK);
                last = ex;
            }
        }
        if(drawn == 0){
            throw new EmptyDrawableException("no polygon drawn",last);
        }
    }

    /**
     *
     * @param color of polygon to be returned
     * @return all polygon of that color
     */
    public Collection<Polygon> getPolygonsWithColor(Color color){
        return canvas.stream().filter((p)->(p.getColor()==color)).map(p->p.getPolygon()).collect(Collectors.toList());
    }
}

package cz.muni.fi.pb162.project.geometry;

import cz.muni.fi.pb162.project.utils.SimpleMath;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author Petr Micka
 * It is representation of Paper in two dimensional space. You can draw on it.
 */

public class CollectionPolygon extends SimplePolygon {
    private ArrayList<Vertex2D> vertices;

    /**
     *
     * @param vertices all top of polygon
     * creates new polygon
     */
    public CollectionPolygon(Vertex2D[] vertices){
        super(vertices);
        this.vertices = new ArrayList<>(Arrays.asList(vertices));
    }

    /**
     * @param vertices all top of polygon
     * creates new polygon
     */
    public CollectionPolygon(Collection<Vertex2D> vertices){
        this(vertices.toArray(new Vertex2D[0]));

    }
    @Override
    public Vertex2D getVertex(int index) {
        if(index<0){
            throw new IllegalArgumentException("Negative index is not allowed.");
        }

        return this.vertices.get(index%getNumVertices());
    }

    @Override
    public int getNumVertices() {
        return this.vertices.size();
    }

    /**
     *
     * @return a collectionpolygon without left most vertices
     */
    public CollectionPolygon withoutLeftmostVertices(){
        if(this.getNumVertices() == 0){
            throw new IllegalArgumentException("Vertices are empty..");
        }
        double leftMost = SimpleMath.minX(this);
        ArrayList<Vertex2D> right = new ArrayList<Vertex2D>();
        for(Vertex2D vertex:vertices){
            if(!(Double.compare(leftMost,vertex.getX()) == 0)){
                right.add(vertex);
            }
        }
        return new CollectionPolygon(right);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CollectionPolygon that = (CollectionPolygon) o;
        return Objects.equals(vertices, that.vertices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertices);
    }
}

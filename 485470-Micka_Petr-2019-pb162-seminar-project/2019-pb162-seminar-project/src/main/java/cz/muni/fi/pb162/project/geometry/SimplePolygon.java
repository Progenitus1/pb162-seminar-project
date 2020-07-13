package cz.muni.fi.pb162.project.geometry;

import cz.muni.fi.pb162.project.exception.MissingVerticesException;
import cz.muni.fi.pb162.project.utils.SimpleMath;

import java.util.Arrays;

/**
 * @author Petr Micka
 * It is representation of SimplePolygon in two dimensional space.
 */

public abstract class SimplePolygon implements Polygon{
    /**
     *
     * @param vertices all tops of polygon
     */
    public SimplePolygon(Vertex2D[] vertices) {
        if(vertices == null || Arrays.asList(vertices).contains(null)){
            throw new IllegalArgumentException("Verteces or one of vertex is null.");
        }
        if(vertices.length < 3){
            throw new MissingVerticesException("not enough vertices.");
        }

    }

    @Override
    public double getHeight() {
        return SimpleMath.maxY(this)-SimpleMath.minY(this);
    }

    @Override
    public double getWidth() {
        return SimpleMath.maxX(this)-SimpleMath.minX(this);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Polygon: vertices =");
        for(int i = 0; i < this.getNumVertices();i++){
            result.append(" ");
            result.append(this.getVertex(i).toString());
        }
        return result.toString();
    }

    @Override
    public abstract Vertex2D getVertex(int index);

    @Override
    public abstract int getNumVertices();
}

package cz.muni.fi.pb162.project.geometry;

import java.util.Arrays;

/**
 * @author Petr Micka
 * It is representation of ArrayPolygon in two dimensional space.
 */

public class ArrayPolygon extends SimplePolygon {
    private final Vertex2D[] vertices;

    /**
     *
     * @param vertices it is a list of vetices used as a peaks of polygon
     * creates a new polygon
     */
    public ArrayPolygon(Vertex2D[] vertices){
        super(vertices);
        this.vertices = vertices.clone();
    }
    @Override
    public Vertex2D getVertex(int index) {
        if(index<0){
            throw new IllegalArgumentException("Negative index is not allowed.");
        }
        return this.vertices[index%this.getNumVertices()];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ArrayPolygon that = (ArrayPolygon) o;
        return Arrays.equals(vertices, that.vertices);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(vertices);
    }

    @Override
    public int getNumVertices() {
        return vertices.length;
    }

}

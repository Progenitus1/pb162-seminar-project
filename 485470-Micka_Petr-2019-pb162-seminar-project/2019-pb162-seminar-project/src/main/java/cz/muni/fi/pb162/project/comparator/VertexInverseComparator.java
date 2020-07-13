package cz.muni.fi.pb162.project.comparator;

import cz.muni.fi.pb162.project.geometry.Vertex2D;

import java.util.Comparator;

/**
 * @author Petr Micka
 * It is comparator for 2D Vertices.
 */
public class VertexInverseComparator implements Comparator<Vertex2D> {
    @Override
    public int compare(Vertex2D o1, Vertex2D o2) {
        return (-1)*o1.compareTo(o2);
    }
}

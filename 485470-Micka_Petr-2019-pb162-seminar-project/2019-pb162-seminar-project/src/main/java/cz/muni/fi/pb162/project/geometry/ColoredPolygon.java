package cz.muni.fi.pb162.project.geometry;

import java.util.Objects;

/**
 * @author Petr Micka
 * It is representation of Colored polygon in two dimensional space.
 */

public class ColoredPolygon {
    private final Color color;
    private final Polygon polygon;

    /**
     *
     * @param polygon polygon
     * @param color color
     * creates a new colored polygon
     */
    public ColoredPolygon(Polygon polygon,Color color){
        this.color = color;
        this.polygon = polygon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ColoredPolygon that = (ColoredPolygon) o;
        return color == that.color &&
                Objects.equals(polygon, that.polygon);
    }

    /**
     *
     * @return polygon
     */
    public Polygon getPolygon() {
        return polygon;
    }

    /**
     *
     * @return color
     */
    public Color getColor() {
        return color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, polygon);
    }
}

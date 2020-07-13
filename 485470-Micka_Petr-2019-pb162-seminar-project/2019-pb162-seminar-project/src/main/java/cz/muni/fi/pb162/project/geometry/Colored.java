package cz.muni.fi.pb162.project.geometry;
/**
 * @author Petr Micka
 * it is interface for colored objects
 */
public interface Colored {
    /**
     *
     * @return a color
     */
    Color getColor();

    /**
     * sets a color
     * @param color color to be set
     */
    void setColor(Color color);
}

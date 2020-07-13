package cz.muni.fi.pb162.project.geometry;

/**
 * @author Petr Micka
 * it is enum that represents color
 */

public enum Color {
    RED,GREEN,BLUE,BLACK,WHITE,ORANGE,YELLOW;
    @Override
    public String toString(){
        return name().toLowerCase();
    }
}

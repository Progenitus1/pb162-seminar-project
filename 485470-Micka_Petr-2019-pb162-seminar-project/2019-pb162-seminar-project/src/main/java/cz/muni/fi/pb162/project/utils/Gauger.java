package cz.muni.fi.pb162.project.utils;
import cz.muni.fi.pb162.project.geometry.Measurable;
import cz.muni.fi.pb162.project.geometry.Triangle;
/**
 * @author Petr Micka
 * it is class for measuring
 */
public class Gauger {
    /**
     *
     * @param object its measurements will be printed
     */
    public static void printMeasurement(Measurable object){
        StringBuilder width = new StringBuilder("Width: ");
        width.append(object.getWidth());
        System.out.println(width.toString());
        StringBuilder heigth = new StringBuilder("Height: ");
        heigth.append(object.getHeight());
        System.out.println(heigth.toString());
    }

    /**
     *
     * @param triangle its measurements will be printed
     */
    public static void printMeasurement(Triangle triangle){
        System.out.println(triangle);
        printMeasurement((Measurable)triangle);
    }
}

package cz.muni.fi.pb162.project.demo;

import cz.muni.fi.pb162.project.geometry.LabeledPolygon;
import cz.muni.fi.pb162.project.geometry.Vertex2D;

import java.io.File;
import java.io.IOException;

/**
 * Class for running main method.
 *
 * @author Petr Micka
 */
public class Demo {

    /**
     *
     * @param args it has none
     * @throws IOException it throws it when there is no file polygon-ok.txt
     */
    public static void main(String[] args) throws IOException {
        File file = new File("polygon-ok.txt");
        LabeledPolygon polygon = (new LabeledPolygon.Builder()).read(file)
                .addVertex("vrchol x" ,new Vertex2D(123,456)).build();
        polygon.writeJson(System.out);
        System.out.println("Hello World!");
    }
}

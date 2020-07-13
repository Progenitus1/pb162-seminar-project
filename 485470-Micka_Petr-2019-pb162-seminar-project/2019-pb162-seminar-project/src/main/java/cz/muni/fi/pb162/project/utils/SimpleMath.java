package cz.muni.fi.pb162.project.utils;
import cz.muni.fi.pb162.project.geometry.Polygon;

/**
 * @author Petr Micka
 * it is class that helps us with geting min and max from triangles
 */
public class SimpleMath {
    /**
     * @param polygon we will want to get min X from this polygon
     * @return min
     */
    public static double minX(Polygon polygon){
        double minX = polygon.getVertex(0).getX();
        for(int i = 0;i<polygon.getNumVertices();i++){
            minX = Math.min(minX,polygon.getVertex(i).getX());
        }
        return minX;
    }
    /**
     * @param polygon we will want to get min Y from this polygon
     * @return min
     */
    public static double minY(Polygon polygon){
        double minY = polygon.getVertex(0).getY();
        for(int i = 0;i<polygon.getNumVertices();i++){
            minY = Math.min(minY,polygon.getVertex(i).getY());
        }
        return minY;
    }
    /**
     * @param polygon we will want to get max X from this polygon
     * @return max
     */
    public static double maxX(Polygon polygon){
        double maxX = polygon.getVertex(0).getX();
        for(int i = 0;i<polygon.getNumVertices();i++){
            maxX = Math.max(maxX,polygon.getVertex(i).getX());
        }
        return maxX;
    }
    /**
     * @param polygon we will want to get max Y from this polygon
     * @return max
     */
    public static double maxY(Polygon polygon){
        double maxY = polygon.getVertex(0).getY();
        for(int i = 0;i<polygon.getNumVertices();i++){
            maxY = Math.max(maxY,polygon.getVertex(i).getY());
        }
        return maxY;
    }
}

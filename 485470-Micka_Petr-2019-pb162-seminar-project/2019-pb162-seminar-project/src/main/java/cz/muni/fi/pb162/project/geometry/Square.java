package cz.muni.fi.pb162.project.geometry;

/**
 * @author Petr Micka
 * this class represents square
 */
public class Square extends  GeneralRegularPolygon{

    /**
     *
     * @param center center of square
     * @param diameter it is diamater of circle around square
     * creates new sqeare
     */
    public Square (Vertex2D center, double diameter){
        super(center,4,diameter/2);
    }

    /**
     *
     * @param object we will take center and diameter from this object
     * to create new square
     */
    public Square(Circular object){
        this(object.getCenter(),object.getRadius()*2);
    }
    @Override
    public String toString(){
        StringBuilder result = new StringBuilder("Square: vertices=");
        int[] indexies = new int[]{0,1,2};
        for (int index:indexies) {
            result.append(getVertex(index));
            result.append(" ");
        }
        result.append(getVertex(3));
        return result.toString();
    }

}

package cz.muni.fi.pb162.project.geometry;

/**
 * @author Petr Micka
 * representation of circle
 */
public class Circle extends GeneralRegularPolygon{

    /**
     *
     * @param center center of circle
     * @param radius radius of circle
     */
    public Circle(Vertex2D center,double radius){
        super(center,Integer.MAX_VALUE,radius);
        super.setColor(Color.RED);

    }

    /**
     * creates unit circle
     */
    public Circle(){
        this(new Vertex2D(0,0),1);
    }

    /**
     *
     * @return string representation of circle
     */
    @Override
    public String toString(){
        StringBuilder result = new StringBuilder("Circle: center=");
        result.append(super.getCenter()).append(", radius=").append(super.getRadius());
        return  result.toString();
    }
    @Override
    public double getEdgeLength(){
        return 0;
    }
}

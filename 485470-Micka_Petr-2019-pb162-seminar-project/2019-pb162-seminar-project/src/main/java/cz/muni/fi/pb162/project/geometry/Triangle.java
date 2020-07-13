package cz.muni.fi.pb162.project.geometry;
import java.util.Arrays;

import static java.lang.Math.abs;

/**
 * @author Petr Micka
 * It is representation of triangle in two dimensional space.
 */
public class Triangle extends ArrayPolygon{
    private final Triangle[] triangles;
    private static final double MAX_DIFFERENCE = 0.001;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Triangle triangle = (Triangle) o;
        for (int i = 0;i<3;i++){
            if(!super.getVertex(i).equals(((Triangle) o).getVertex(i))){
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(triangles);
    }

    /**
     *
     * @param a one of the tops of triangle
     * @param b one of the tops of triangle
     * @param c one of the tops of triangle
     */
    public Triangle(Vertex2D a, Vertex2D b, Vertex2D c){
        super(new Vertex2D[]{a, b, c});
        triangles = new Triangle[3];
    }

    /**
     *
     * @param a one of the tops of triangle
     * @param b one of the tops of triangle
     * @param c one of the tops of triangle
     * @param depth it is depth to witch we wiil divide triangle
     */
    public Triangle(Vertex2D a, Vertex2D b, Vertex2D c, int depth){
        this(a,b,c);
        divide(depth);
    }

    /**
     *
     * @param index index of top of triangle
     * @return a triale top under index
     */

    /**
     *
     * @return a string representation of triangle.
     */
    @Override
    public String toString(){
        StringBuilder result = new StringBuilder("Triangle: vertices=");
        result.append(super.getVertex(0)).append(" ");
        result.append(super.getVertex(1)).append(" ");
        result.append(super.getVertex(2));
        return result.toString();
    }

    /**
     *
     * @return if a triangle was divided
     */
    public boolean isDivided(){
        return this.triangles[0] != null &&
                this.triangles[1] != null && this.triangles[2] != null;
    }

    /**
     *
     * @param index index of subtriangle
     * @return a subtriangle under index
     */
    public Triangle getSubTriangle(int index){
        if(index < 0 || index > 2 || !isDivided()){
            return null;
        }
        return this.triangles[index];
    }

    /**
     *
     * @param depth it recursivly divids triangle to deapth given by parameter
     */

    void divide(int depth){
        if(depth<1){
            return;
        }

        this.divide();
        for (Triangle i:triangles) {
            i.divide(depth-1);
        }
    }
    /**
     * If posible divides triangle in three smaller triangles.
     * @return if it was posible or not.
     */
    public boolean divide(){
        if(isDivided()){
            return false;
        }
        Vertex2D midA = super.getVertex(1).createMiddle(super.getVertex(2));
        Vertex2D midB = super.getVertex(0).createMiddle(super.getVertex(2));
        Vertex2D midC = super.getVertex(0).createMiddle(super.getVertex(1));
        triangles[0] = new Triangle(super.getVertex(0),midC,midB);
        triangles[1] = new Triangle(midC,super.getVertex(1),midA);
        triangles[2] = new Triangle(midB,midA,super.getVertex(2));

        return true;
    }

    /**
     *
     * @return if triangle is equilateral or not.
     */
    public boolean isEquilateral() {
        double a = getVertex(0).distance(getVertex(1));
        double b = getVertex(2).distance(getVertex(1));
        double c = getVertex(0).distance(getVertex(2));
        return abs(a-b) < MAX_DIFFERENCE && abs(b-c)< MAX_DIFFERENCE && abs(a-c)< MAX_DIFFERENCE;
    }


}

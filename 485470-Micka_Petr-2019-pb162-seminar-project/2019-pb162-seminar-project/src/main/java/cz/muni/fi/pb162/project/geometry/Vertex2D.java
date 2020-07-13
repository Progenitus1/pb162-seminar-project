package cz.muni.fi.pb162.project.geometry;

import static java.lang.Math.sqrt;
/**
 *@author Petr Micka
 * It is representation of point in two dimensional space.
 */
public class Vertex2D implements  Comparable{
    /**
     * x
     */
    private final double x;
    /**
     * y
     */
    private final double y;

    /**
     *
     * @param x one coordinate in two dimensional space
     * @param y second coordinate in two dimensional space
     * It is inicialization of class.
     */
    public Vertex2D(double x, double y){
        this.x = x;
        this.y = y;
    }
    /**
     *
     * @return x
     */
    public double getX(){

        return this.x;
    }

    /**
     *
     * @return y
     */
    public double getY() {
        return this.y;
    }

    /**
     *
     * @param vertex it is one of vertefies witch distance we wiill calculate
     * @return a distance between this and other vertex
     */
    public double distance(Vertex2D vertex){
        if(vertex == null){
            return -1;
        }
        return sqrt((this.x-vertex.x)*(this.x-vertex.x)+(this.y-vertex.y)*(this.y-vertex.y));
    }
    /**
     *
     * @return string represantion of vertex
     */
    @Override
    public String toString(){
        StringBuilder result = new StringBuilder("[");
        result.append(this.getX());
        result.append(", ");
        result.append(this.getY());
        result.append("]");
        return  result.toString();
    }

    /**
     *
     * @param otherVertex one of the points ,it will be used to create a midle point with this point.
     * @return a point in a middle of this point and the parametr.
     */
    public Vertex2D createMiddle(Vertex2D otherVertex){
        return new Vertex2D((this.x+otherVertex.x)/2 ,(this.y+otherVertex.y)/2);
    }
    @Override
    public boolean equals(Object vertex2D){
        if(vertex2D == null){
            return false;
        }
        if(!(vertex2D instanceof Vertex2D)){
            return false;
        }
        if(vertex2D == this){
            return true;
        }

        return Double.compare(this.x,((Vertex2D) vertex2D).x) ==0 &&
                Double.compare(this.y,((Vertex2D) vertex2D).y) == 0;
    }

    /**
     *
     * @return a hashcode of vertex
     */
    public int hashCode(){
        int hash = Double.hashCode(this.x);
        hash = hash << 8 | Double.hashCode(y);
        return hash;
    }

    @Override
    public int compareTo(Object o) {
        int x = Double.compare(getX(), ((Vertex2D) o).getX());
        if(x == 0){
            return Double.compare(getY(), ((Vertex2D) o).getY());
        }
        return x;
    }
}

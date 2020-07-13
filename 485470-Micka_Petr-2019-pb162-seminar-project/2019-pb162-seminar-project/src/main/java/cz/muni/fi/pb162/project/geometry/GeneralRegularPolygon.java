package cz.muni.fi.pb162.project.geometry;
/**
 * @author Petr Micka
 * this class represents GeneralRegularPolygon
 */
public class GeneralRegularPolygon implements Colored,RegularPolygon {
    private final Vertex2D center;
    private final int n;
    private final double radius;
    private Color color = Color.BLACK;

    /**
     *
     * @param center of polygon
     * @param n number of edges
     * @param radius of polygon
     * creates new plygon
     */
    public GeneralRegularPolygon(Vertex2D center, int n, double radius){
        this.center = center;
        this.radius = radius;
        this.n = n;
    }
    @Override
    public Vertex2D getCenter() {
        return center;
    }

    @Override
    public double getRadius() {
        return radius;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }
    private int getN(){
        return this.n;
    }
    @Override
    public String toString(){
        StringBuilder result = new StringBuilder().append(getN()).append("-gon: center=")
                .append(center).append(", radius=");
        result.append(radius).append(", color=").append(color);
        return result.toString();
    }

    @Override
    public int getNumEdges() {
        return n;
    }

    @Override
    public double getEdgeLength() {
        return  2*radius* Math.sin(Math.PI/n);
    }

    @Override
    public Vertex2D getVertex(int index) {
        return new Vertex2D(getCenter().getX()-this.radius * Math.cos(index * 2 * Math.PI/n)
                ,getCenter().getY()-radius * Math.sin(index * 2 * Math.PI/n));
    }
    private double getDiameter(){
        return radius*2;
    }
    @Override
    public double getWidth() {
        return getDiameter();
    }

    @Override
    public double getHeight() {
        return getDiameter();
    }
}

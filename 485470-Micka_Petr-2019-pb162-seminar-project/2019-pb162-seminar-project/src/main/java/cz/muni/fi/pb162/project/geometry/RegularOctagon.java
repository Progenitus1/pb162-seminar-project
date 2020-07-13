package cz.muni.fi.pb162.project.geometry;
/**
 * @author Petr Micka
 * this class represents regularOctagon
 */
public class RegularOctagon extends GeneralRegularPolygon{
    /**
     *
     * @param center of polygon
     * @param radius of polygon
     * creates a new RegularOctagon
     */
    public RegularOctagon(Vertex2D center,double radius){
        super(center,8,radius);
    }
}

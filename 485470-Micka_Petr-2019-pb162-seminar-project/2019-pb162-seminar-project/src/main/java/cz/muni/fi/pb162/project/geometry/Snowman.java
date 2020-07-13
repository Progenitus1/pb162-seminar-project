package cz.muni.fi.pb162.project.geometry;

/**
 * @author Petr Micka
 * this class represents snowman
 */
public class Snowman {
    private static final double DEFAULT_SHRINK_RATIO = 0.8;
    
    private double shrinkRatio = DEFAULT_SHRINK_RATIO;
    
    private final RegularPolygon[] balls;

    /**
     *
     * @param bottom it is bottom of snowman
     * @param shrinkRatio size of higher Circulars will be multilied by this amount
     */
    public Snowman(RegularPolygon bottom ,double shrinkRatio){
        if(shrinkRatio>0 && shrinkRatio <= 1){
            this.shrinkRatio = shrinkRatio;
        }
        this.balls = new RegularPolygon[3];
        initBalls(bottom,shrinkRatio);
        
    }

    private void initBalls(RegularPolygon bottom ,double shrinkRatio){
        balls[0] = bottom;
        for(int i = 1; i < 3; i++){
            Vertex2D newCenter = new Vertex2D(balls[i-1].getCenter().getX()
                    ,balls[i-1].getCenter().getY() + balls[i-1].getRadius()+balls[i-1].getRadius()*this.shrinkRatio);
            balls[i] = new GeneralRegularPolygon(newCenter,bottom.getNumEdges(),
                    balls[i-1].getRadius()*this.shrinkRatio);
        }
    }

    /**
     *
     * @return array of balls
     */
    public RegularPolygon[] getBalls(){
        return balls.clone();
    }
}

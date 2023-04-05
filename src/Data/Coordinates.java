package Data;

import java.io.Serializable;

/**
 * Used to manage with coordinates
 * @author Roman Kobelev
 */
public class Coordinates implements Serializable {
    /**
     * Coordinate x of human being
     * @see HumanBeing
     */
    private int x;
    /**
     * Coordinate y of human being
     * Maximum value is 493, can't be null
     * @see HumanBeing
     */
    private Long y;

    /**
     * Constructor of Coordinates with parameters
     * @param x coordinate x
     * @param y coordinate y
     */
    public Coordinates(int x, Long y){
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor of Coordinates without parameters
     */
    public Coordinates(){

    }
    /**
     * @return coordinate x
     */
    public int getX(){
        return this.x;
    }

    /**
     * Set coordinate x
     * @param x coordinate x
     */
    public void setX(int x){
        this.x = x;
    }

    /**
     * @return coordinate y
     */
    public Long getY(){
        return this.y;
    }

    /**
     * Set coordinate y
     * @param y coordinate y
     */
    public void setY(Long y){
        this.y = y;
    }

    /**
     * Used to print information about Coordinate class
     */
    @Override
    public String toString(){
        return "x: "+this.x+" y: "+this.y;
    }
}

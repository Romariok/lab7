package Data;

import java.io.Serializable;

/**
 * Used to manage with car
 * @author Roman Kobelev
 */
public class Car implements Comparable<Car>, Serializable {
    /**
     * Status of car of human being
     * @see HumanBeing
     */
    private boolean cool;

    /**
     * Constructor of Car with parameter
     * @param cool status of car
     */
    public Car(boolean cool){
        this.cool = cool;
    }

    /**
     * Constructor of Car without parameters
     */
    public Car(){

    }

    /**
     * @return status of car
     */
    public boolean getCool(){
        return this.cool;
    }

    /**
     * Set status of car
     * @param cool status of car
     */
    public void setCool(boolean cool){
        this.cool = cool;
    }

    /**
     *Used to print information about car
     */
    @Override
    public String toString(){
        return ""+this.cool;
    }

    @Override
    public int compareTo(Car car){
        int old;
        int neww;
        if (this.cool){
            old = 1;
        }
        else{
            old = 0;
        }

        if (car.getCool()){
            neww = 1;
        }
        else{
            neww = 0;
        }

        if (neww > old) return 1;
        else if ( neww < old) return -1;
        else return 0;
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Antonio A
 */
public class Auto {
    
    //attributes
    private String vin = null;
    private int topSpeed = 0;
    private int currentSpeed = 0;

    
    
    /**
     * this method add 10 to current speed
     * if that does not exceed top speed
     */
    public void accel(){
        
        if((currentSpeed+10) <= topSpeed){
            currentSpeed = currentSpeed+10;
        }
    }
/**
 * this method subtracts 10 from current speed 
 * if that does not make current speed less than 
 * zero
 */    
    
    public void decel(){
        
        if((currentSpeed-10) >= 0){
            currentSpeed = currentSpeed-10;
        }
    }
    
    
    /**
     * getters and setters
     * 
     */
    
    
    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public int getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(int topSpeed) {
        this.topSpeed = topSpeed;
    }

    public int getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(int currentSpeed) {
        this.currentSpeed = currentSpeed;
    }










}
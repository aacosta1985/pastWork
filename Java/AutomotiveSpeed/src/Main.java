/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Antonio A
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //this is an object of Auto
        Auto myFirstCar = new Auto();
        
        myFirstCar.setVin("123F1");
        myFirstCar.setTopSpeed(150);
        
        System.out.println("Current Speed " + myFirstCar.getCurrentSpeed());
        
        myFirstCar.accel();
        System.out.println("Current Speed " + myFirstCar.getCurrentSpeed());
        
        
        
    }
}

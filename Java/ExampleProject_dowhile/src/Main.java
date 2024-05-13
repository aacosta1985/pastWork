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

        
        String sentinelValue = "Y";
        int counter = 0;
        
        do{
                counter++;
                System.out.println("Counter "+ counter);
                
                if(counter>=7){
                    sentinelValue = "N";
                }
                
                        }while(sentinelValue.equalsIgnoreCase("Y"));
                
                System.out.println("Out of the loop");
    }
}

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
        
        while(sentinelValue.equalsIgnoreCase("Y")){
            counter++;
            System.out.println("Counter "+counter);
            
            if(counter>=7){
                sentinelValue = "N";
            }//end if
        }//end while
        
        
        
    }//end main
}//end class

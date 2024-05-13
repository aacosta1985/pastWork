package client;

/**
 *
 * @author Antonio A
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /*
         * Create Interface
         */
        
        Interface compApp = new Interface();
        
        //Start app
        compApp.processMenu();
    }
}

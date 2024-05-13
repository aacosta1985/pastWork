
import java.util.Scanner;
/**
 * This class manages the input and output for the 
 * Bonus Calculator
 * @author Antonio A
 */
public class IOManager {
    
    //Java object that manages the console
    private Scanner commandLine = new Scanner(System.in);
    
    /**
     * This method prompts the user if they wish to do a 
     * bonus calculation
     * @return 
     */
    public String doCalculation(){
        System.out.print("Do Bonus Calculator Y/N ");
        return commandLine.next();
    }
    
    /**
     * This method get the salary from the console
     * @return 
     */
    public int getSalary(){
            System.out.print("Enter Salary: ");
            return  commandLine.nextInt();
    }
    
    /**
     * This method gets the years of service from the console
     * @return 
     */
    public int getYrsOfSrvc(){
            System.out.print("Enter Years of Service: ");
            return commandLine.nextInt();        
    }
    
    /**
     * This method prints the bonus amount to the console
     * @param bonusAmount 
     */
    public void printBouns(int bonusAmount){
            System.out.println("Bouns Amount = " + bonusAmount);        
    }
    
    
}

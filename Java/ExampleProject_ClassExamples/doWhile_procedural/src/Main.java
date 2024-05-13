import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        
        //Create an object using the Scanner class to read the command line
        Scanner commandLine = new Scanner(System.in);
        // variable to hold the watch value entered that triggers termination
        String sentinelValue = null;
        //variables to hold input
        int salary = -999;
        int yearsOfService = -999;
        int bonusAmount = -999;
        
        //input loop
        do{
            //prompt and get salary
            System.out.print("Enter Salary: ");
            salary = commandLine.nextInt();
            
            //prompt and get years of service
            System.out.print("Enter Years of Service: ");
            yearsOfService = commandLine.nextInt();
            
            //calculate the bonus
            bonusAmount = calculateBonus(salary, yearsOfService);
            
            //print bouns 
            System.out.println("Bonus Amount = " + bonusAmount);
            
            //prompt user if they want to continue
            System.out.println("Continue Y/N");
            sentinelValue = commandLine.next();
            
        }while(sentinelValue.equalsIgnoreCase("Y"));
        
        System.out.println("Thank you for using bonus calculator!");
        
    }


    /**
     * returns a bonus based on salary and years of service
     * @param salary
     * @param yrsOfSrvc
     * @return 
     */
    private static int calculateBonus(int salary, int yrsOfSrvc){
        int bonus = -999;

        if((salary >= 50000 && salary <= 100000) && yrsOfSrvc < 20){
            bonus = 100;
        }else if((salary >= 100001 && salary <= 200000) || yrsOfSrvc >= 20){
            bonus = 200;
        }else if(salary >= 200001){
            bonus = 300;
        }else{
            bonus = 0;
        }//end if          
        
        return bonus;
    }
    
}

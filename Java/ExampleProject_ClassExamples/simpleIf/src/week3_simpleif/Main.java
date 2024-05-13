package week3_simpleif;

public class Main {

    public static void main(String[] args) {
        int salary = 0;
        
        //condition is true, print salary
        salary = 99999;
        if(salary < 100000){
            System.out.println("Bonus is 100");
        }//end if
        
        //condition is false, does not print salary
        salary = 100001;
        if(salary < 100000){
            System.out.println("Bonus is 100");
        }//end if      
    }//end main
}//end Main Class

public class Main {

    public static void main(String[] args) {
        int salary = 49000;
        int yrsOfSrvc = 20;

        if((salary >= 50000 && salary <= 100000) && yrsOfSrvc < 20){
            System.out.println("Bonus is 100");
        }else if((salary >= 100001 && salary <= 200000) || yrsOfSrvc >= 20){
            System.out.println("Bonus is 200");
        }else if(salary >= 200001){
            System.out.println("Bonus is 300");
        }else{
            System.out.println("No bonus");
        }//end if  
    }
}

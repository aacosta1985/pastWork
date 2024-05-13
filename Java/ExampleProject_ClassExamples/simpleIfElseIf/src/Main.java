public class Main {

    public static void main(String[] args) {
        int salary = 0;

        salary = 101000;
        if(salary < 100000){
            System.out.println("Bonus is 100");
        }else if((salary >= 100001) && (salary <= 200000)){
            System.out.println("Bonus is 200");
        }else{
            System.out.println("Bonus is 300");
        }//end if        
    }
}

/**
 * This class calculates a bonus based on salary and years of service
 * 
 */
public class BonusCalculator {
    
    //ioManager object used in this class for console I/O
    private IOManager ioManager = new IOManager();
    
    /**
     * Pass thru method to IO manager
     * allows user, Main, to manage loop
     * @return 
     */
    public String doCalculation(){      
        return ioManager.doCalculation();
    }
       
    /**
     * calculate the bonus based on salary and years of service
     * that are retrieved from the console using the IOManager
     */
    public void calculateBonus(){
        
        int salary = ioManager.getSalary();
        int yrsOfSrvc = ioManager.getYrsOfSrvc();
        
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
        
        ioManager.printBouns(bonus);
        
    }
    
    
    
    
    
}

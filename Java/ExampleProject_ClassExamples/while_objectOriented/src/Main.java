
public class Main {

    public static void main(String[] args) {

        BonusCalculator bonusCalculator = new BonusCalculator();
        String sentinelValue = null;
        
        sentinelValue = bonusCalculator.doCalculation();
                
        while(sentinelValue.equalsIgnoreCase("Y")){
            bonusCalculator.calculateBonus();
            
            sentinelValue = bonusCalculator.doCalculation();
        }                
        
        System.out.println("Thank you for using bonus calculator!");        
        
    }
}

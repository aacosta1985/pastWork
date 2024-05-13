
package server;

/**
 *
 * @author Antonio A
 */
public class SalesPerson {
    
private String name = null;
private double currentSales = 0.0;
private double totalAnnualCompensation = 0.0;


    public SalesPerson(String name, double currentSales){
        this.name = name;
        this.currentSales = currentSales;
    }//end SalesPerson

      public SalesPerson(SalesPerson inputSalesPerson){
        this.name = inputSalesPerson.getName();
        this.currentSales = inputSalesPerson.getCurrentSales();
        this.totalAnnualCompensation = inputSalesPerson.getTotalAnnualCompensation();
      }//end SalesPerson

    public SalesPerson(){
        
    }//end default SalesPerson
    
    
    //setters and getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCurrentSales() {
        return currentSales;
    }

    public void setCurrentSales(double currentSales) {
        this.currentSales = currentSales;
    }

    public double getTotalAnnualCompensation() {
        return totalAnnualCompensation;
    }

    public void setTotalAnnualCompensation(double totalAnnualCompensation) {
        this.totalAnnualCompensation = totalAnnualCompensation;
    }

}//end class

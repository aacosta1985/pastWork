
package server;

import java.util.ArrayList;

/**
 *
 * @author Antonio A
 */
public class PotentialEarningsReport {
    
    private SalesPerson salesPerson = null;
    private double increment = 5000.00;
    private double limitPercent = .5;
    private ArrayList<String> detailLinesList = new ArrayList<String>();
    
    /*constructor to create report header
    
    */
    public PotentialEarningsReport(SalesPerson salesPerson){
        //initialize report 
        this.salesPerson = salesPerson;
        //construct header
        constructHeader();
                
    }//end PotentialEarningsReport

    /**
     * creates report detail
     */
    public void createDetailLine(double currentCompensation){
        //construct detail line
        String detailLine = "Sales: " + this.salesPerson.getCurrentSales() + "Compensation: " 
                + currentCompensation;
        
        //add detail line to report
        this.addDetailLine(detailLine);
             
    }//end createDetailLine

    
    /**
     * getters and setters
     * 
     */
    
    public SalesPerson getSalesPerson() {
        return salesPerson;
    }

    public void setSalesPerson(SalesPerson salesPerson) {
        this.salesPerson = salesPerson;
    }

    public double getIncrement() {
        return increment;
    }

    public void setIncrement(double increment) {
        this.increment = increment;
    }

    public double getLimitPercent() {
        return limitPercent;
    }

    public void setLimitPercent(double limitPercent) {
        this.limitPercent = limitPercent;
    }

    public ArrayList<String> getDetailLinesList() {
        return detailLinesList;
    }

    public void setDetailLinesList(ArrayList<String> detailLinesList) {
        this.detailLinesList = detailLinesList;
    }
    
    
    
    
    
    private void constructHeader() {
       
        //add title
        this.addDetailLine("POTENTIAL EARNINGS REPORT FOR " + salesPerson.getName());
        //create starting detail line
        String detailLine = "Starting Sales: " + this.salesPerson.getCurrentSales() + 
                "Starting Compensation: " + this.salesPerson.getTotalAnnualCompensation();
        //add starting detail line report list
        this.addDetailLine(detailLine);
        
    }//end constructHeader
    
    private void addDetailLine(String detailLine){
        this.detailLinesList.add(detailLine);
    }//end add detail line
    
}//end PotentialEarningsReport class

package server;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Antonio A
 */
public class Compensation {
    
    private double fixedSalary = 20000;
    private double commRate = .15;
    private double salesTarget = 100000.0;
    private double commissionThresholdPercent = .80;
    private double acceleratorFactor = 1.25;
    private boolean useNewPlan = true;
    
    private final HashMap<String, SalesPerson> salesPersonList = new HashMap<String, SalesPerson>();

    
    public double calculateTotalAnnualCompensation(SalesPerson salesPerson) {
       double totalAnnualCompensation = 0.0;
       
       double commission = 0.0;
       
        if (useNewPlan) {
            
            if (salesPerson.getCurrentSales() >= (salesTarget * commissionThresholdPercent)) {
                
                commission = salesPerson.getCurrentSales() * this.commRate;
                
                if (salesPerson.getCurrentSales() > salesTarget) {
                    
                    commission = commission * acceleratorFactor;
                }//end if1
            }//end if2
        } else {//use the old compensation plan
            //calculate commision
            commission = salesPerson.getCurrentSales() * this.commRate;
        }//end if3

        //calculate compensation
        totalAnnualCompensation = fixedSalary + commission;

        return totalAnnualCompensation;
    }//end calculateTotalAnnualCompensation method
    
    public PotentialEarningsReport calculatePotentialEarnings(SalesPerson salesPerson) {

        SalesPerson localSalesPerson = new SalesPerson(salesPerson);

        double currentCompensation = this.calculateTotalAnnualCompensation(localSalesPerson);
        localSalesPerson.setTotalAnnualCompensation(currentCompensation);

        
        PotentialEarningsReport peReport = new PotentialEarningsReport(localSalesPerson);

        double targetCompensation = currentCompensation + (currentCompensation * peReport.getLimitPercent());

        while (currentCompensation <= targetCompensation) {

            //get current sales
            double currentSales = localSalesPerson.getCurrentSales();

            //increment current sales by report increment
            localSalesPerson.setCurrentSales(currentSales + peReport.getIncrement());

            //calculate compensation based on new sales
            currentCompensation = this.calculateTotalAnnualCompensation(localSalesPerson);

            //create report detail line
            peReport.createDetailLine(currentCompensation);
        }//end while loop

        return peReport;
    }//end calculatePotentialEarnings method    
    
    public SalesPersonComparisonReport calculateSalesPersonComparison() {

        //get a local copy of the salesperson list using the method that converts the hashmap to an array list
        ArrayList<SalesPerson> salesList = this.getSalesPersonArrayList();

        //calculate the total compensation for each sales person in the list
        for (int i = 0; i < salesList.size(); i++) {

            //get the salesperson
            SalesPerson salesPerson = salesList.get(i);

            //use the sales person to calculate and set the compensation in the local list
            salesList.get(i).setTotalAnnualCompensation(this.calculateTotalAnnualCompensation(salesPerson));

        }//end of for

        //create a report using the local list
        SalesPersonComparisonReport spcReport = new SalesPersonComparisonReport(getSalesPersonArrayList());

        return spcReport;
    }//end calculateSalesPersonComparison method
    
    
    public double getFixedSalary() {
        return fixedSalary;
    }

    public void setFixedSalary(double fixedSalary) {
        this.fixedSalary = fixedSalary;
    }

    public double getCommRate() {
        return commRate;
    }

    public void setCommRate(double commRate) {
        this.commRate = commRate;
    }
    
     public void addSalesPerson(SalesPerson salesPerson) {
        this.salesPersonList.put(salesPerson.getName(), salesPerson);
    }//end addSalesPerson
     
     public SalesPerson getSalesPersonByName(String name) {
        return salesPersonList.get(name);
     }//end getSalesPersonByName
     
      public ArrayList<SalesPerson> getSalesPersonArrayList() {

        //convert the hashMap to an ArrayList    
        ArrayList<SalesPerson> list = new ArrayList<SalesPerson>(salesPersonList.values());

        return list;
    }//end of getSalesPersonArrayList method
}//end class

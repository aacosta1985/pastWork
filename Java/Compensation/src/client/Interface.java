/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.ArrayList;
import java.util.Scanner;
import server.Compensation;
import server.SalesPerson;
import server.PotentialEarningsReport;
import server.SalesPersonComparisonReport;

 /**
 *
 * @author Antonio A
 */
public class Interface {
    
    private final Scanner input = new Scanner(System.in); //creation of scanner attribute with constructor
    private final Compensation compensation = new Compensation();
    
 /*
  * This method displays the menu
  * gets user input
  * calls method for input
  * 
  * 1. Input Sales Person Data
  * 2. Print Total Compensation
  * 3. Print Potential Earnings Table
  * 4. Print Sales Comparison Table
  * 5. More Input?
  */   
    
    public void processMenu(){
        
        boolean moreInput = true;
        
      
        //do
        do{
            
        System.out.println("1. Input Sales Person Data");
        System.out.println("2. Print Total Compensation");
        System.out.println("3. Print Potential Earnings Table");
        System.out.println("4. Print Sales Comparison Table");
        System.out.println("5. More Input?");
        
        
        //get input
        String inputValue = input.nextLine();
        int inputInt = Integer.parseInt(inputValue);
        
        
        //operation on input
        if(inputInt == 1){
            //call the input sales person method
            inputSales();
        }else if (inputInt == 2){
            //Print Total Compensation
                   printTotalAnnualCompensation();     
        }else if (inputInt == 3){
            //Print Potential Earnings Table
            printPotentialEarningsTable();
        }else if (inputInt == 4){
            //Print Sales Comparison Table
            printComparisonTable();
        }else if (inputInt == 5){
            moreInput = false;
            //more input? do loop
            
        }  
       } while (moreInput);
        
        //end do
            }//end of process menu method
    
    private void inputSales(){
        SalesPerson salesPerson = new SalesPerson ();
        //enter name
        System.out.print("Enter Sales Person Name:   ");
        String name = input.nextLine();
        //set salesperson name in object
        salesPerson.setName(name);
        //enter sales
        System.out.print("Enter Sales:   ");
        String saleString = input.nextLine();
        //convert input to string 
        double sales = Double.parseDouble(saleString);
        //set salesPerson sales object
        salesPerson.setCurrentSales(sales);
        //add sales person to the compensation data
        compensation.addSalesPerson(salesPerson);
        

        
        
    }//end inputSales method
/*
  * This method prints tht total annual compensation
  * 
  * 
  * 
  * 
  * 
  */  
    private void printTotalAnnualCompensation() {
        //single sales person for phase one of app
        SalesPerson salesPerson = null;
        
        //display menu for total annual compensation
        System.out.println("1. Print A Salesperson");
        System.out.println("2. Print All Salespersons");

        //get menu selection from command line
        String inputSelection = input.nextLine();
        //change string inputScanner into integer
        int inputInt = Integer.parseInt(inputSelection);
        
        //select inputScanner operation and call appropriate method
        if (inputInt ==1){
                //call the inputScanner sales person method
            //prompt for input of salesperson name
            System.out.print("Enter SalesPerson Name: ");
            //get string from command line
            String salesPersonName = input.nextLine();
            //retrieve salesperson by name from compensation system
            salesPerson = this.compensation.getSalesPersonByName(salesPersonName);
            //print the total annual compensation for the salesperson
            printTotalAnnualCompensationLine(salesPerson);
        }else if (inputInt ==2){
            ArrayList<SalesPerson> salesPersonList = this.compensation.getSalesPersonArrayList();

            //loop through all the salesPersons in the list and print 
            for (SalesPerson salesPersonFromList : salesPersonList) {
                //print the total annual compensation for the salesperson
                printTotalAnnualCompensationLine(salesPersonFromList);
            }
        }else {
            System.out.println("Invalid input! You must start over.  Please pay attention next time");
            return;
        }//end if
    }//end PrintTotalAnnualCompensation method
    private void printTotalAnnualCompensationLine(SalesPerson salesPerson) {

        System.out.println("Total Annual Compensation for: "
                + salesPerson.getName()
                + " with Current Sales = "
                + salesPerson.getCurrentSales()
                + " is "
                + compensation.calculateTotalAnnualCompensation(salesPerson));

    }//end printTotalAnnualCompensationLine method    
    
    
    private void printSalesPerson(SalesPerson salesPerson) {
       
   System.out.println(salesPerson.toString());
    
    }
    /*
    This will print the potential earnings table
    */
    private void printPotentialEarningsTable(){
        
        System.out.print("Enter SalesPerson Name: ");
        
        //get string from command line
        String salesPersonName = input.nextLine();
        
        //retrieve salesperson by name from compensation system
        SalesPerson salesPerson = this.compensation.getSalesPersonByName(salesPersonName);

        //create a report for this sales person
        PotentialEarningsReport peReport = compensation.calculatePotentialEarnings(salesPerson);

        //get the report lines from the report
        ArrayList<String> detailLineList = peReport.getDetailLinesList();

        //print all the lines in the report
        for (String detailLine : detailLineList) {
            System.out.println(detailLine);
        }//end for loop
        
        
    }//end potentialearningstable

    
    /**
     * create comparison table method
     */
    
    
    private void printComparisonTable() {
        
        //get salesperson report
        SalesPersonComparisonReport spcReport = compensation.calculateSalesPersonComparison();
        
        //print report
        for (String detailLine : spcReport.getDetailLinesList()) {

            System.out.println(detailLine);
        }//end for loop
        
    }//end printComparisonTable method
    
    }// end Interface class



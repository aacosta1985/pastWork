package server;

import java.util.ArrayList;

/**
 *
 * @author Antonio A
 */
public class SalesPersonComparisonReport {
    
    //report lines 
        private ArrayList<String> detailLinesList = new ArrayList<String>();
    
    /**
     * constructor with initialization
     */
    
    public SalesPersonComparisonReport(ArrayList<SalesPerson> salesPersonList) {

        calculate(salesPersonList);

    }//end initialization constructor
    
        /**
        * default constructor
        */
    public SalesPersonComparisonReport() {

    }//end default constructor
    
    /**
     * create the report
     */
    
    private void calculate(ArrayList<SalesPerson> salesPersonList) {

        //salesPerson list sorted in ascending order lowest to highest current sales
        ArrayList<SalesPerson> sortedSalesPersonList = bubbleSort(salesPersonList);

        //get the highest earner ... list sorted in ascending order thefore will be the last one
        SalesPerson highestEarner = sortedSalesPersonList.get(sortedSalesPersonList.size() - 1);

        //variables used in the for loop to make things more readable
        double sales;
        double comp;
        double salesDiff;
        double compDiff;
        String name;

        //create the report header
        constructHeader();
        //add it to the report
        this.addDetailLine("Higest Earner: " + highestEarner.getName() + " Total Sales: " + highestEarner.getCurrentSales() + " Total Annual Compensation: " + highestEarner.getTotalAnnualCompensation());

        //for all the sales persons in the sorted list create a report line
        for (int i = 0; i < sortedSalesPersonList.size() - 1; i++) {

            //get report line details
            salesDiff = highestEarner.getCurrentSales() - sortedSalesPersonList.get(i).getCurrentSales();
            compDiff = highestEarner.getTotalAnnualCompensation() - sortedSalesPersonList.get(i).getTotalAnnualCompensation();
            name = sortedSalesPersonList.get(i).getName();
            sales = sortedSalesPersonList.get(i).getCurrentSales();
            comp = sortedSalesPersonList.get(i).getTotalAnnualCompensation();

            //add line to report
            this.addDetailLine("Salesperson: " + name + " Total Sales: " + sales + " Total Annual Compensation: " + comp + " Sales Diff: " + salesDiff + " Comp Diff: " + compDiff);

        }//end for

    }//end calculate method

    /**
     * sorts a salesperson list into ascending order by current sales
     */
    private ArrayList<SalesPerson> bubbleSort(ArrayList<SalesPerson> inputSalesPersonList) {

        //create new array list to be returned 
        ArrayList<SalesPerson> newSalesPersonList = new ArrayList<SalesPerson>();

        //create an array to hold all the salesperson in the input arrayList
        SalesPerson[] salesPersonArray = new SalesPerson[inputSalesPersonList.size()];
        //convert arraylist to an array of salespersons        
        for (int i = 0; i < inputSalesPersonList.size(); i++) {
            salesPersonArray[i] = inputSalesPersonList.get(i);
        }

        //variables used in the bubble sort loop
        int counter, index;
        SalesPerson temp;
        int length = salesPersonArray.length;

        //outer loop
        for (counter = 0; counter < length - 1; counter++) {
            //inner loop
            for (index = 0; index < length - 1 - counter; index++) {

                if (salesPersonArray[index].getCurrentSales() > salesPersonArray[index + 1].getCurrentSales()) {

                    //swap array elements
                    temp = salesPersonArray[index];
                    salesPersonArray[index] = salesPersonArray[index + 1];
                    salesPersonArray[index + 1] = temp;

                }//end if
            }//end inner loop
        }//end outer loop

        //turn sorted array back into array list
        for (SalesPerson salesPerson : salesPersonArray) {
            newSalesPersonList.add(salesPerson);
        }

        return newSalesPersonList;

    }//end bubble sort method

    /**
     * constructs the header for the report
     */
    private void constructHeader() {

        //add title to report list
        this.addDetailLine("SALES PERSON COMPARISON REPORT");

    }//end constructHeader method

    /**
     * adds a detail line to the report
     */
    private void addDetailLine(String detailLine) {
        this.detailLinesList.add(detailLine);
    }//end addDetailLine    

    /**
     * get the report lines
     */
    public ArrayList<String> getDetailLinesList() {
        return detailLinesList;
    }//end getDetailLinesList method

    
    
}//end SalesPersonComparisonReport class

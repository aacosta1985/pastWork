/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Antonio A
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        
        
        //set student object
        Student aStudent = new Student();
        
        //set student id
        aStudent.setId(123);
        
        
        //set student name
        aStudent.setName("Asshole Antonio");
        
        
        //test
        System.out.println(aStudent.getId());
        System.out.println(aStudent.getName());
        
        aStudent.batchGrades(85.0f, 92.1f, 96.2f, 77.3f, 64.3f);
        
        float[] currentGrades = aStudent.getGrades();
       
        
        for(int index = 0; index < 5; index++){
            System.out.println(currentGrades[index]);
            
        }
       System.out.println(aStudent.calcAvg()); 
    }
}

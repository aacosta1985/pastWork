/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Antonio A
 */
public class Student {
    
    int id = 0;
    String name= "";
    
    //creating a new array of floats with 5 elements
    float[] grades = new float [5];
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float[] getGrades() {
        return grades;
    }

    public void setGrades(float[] grades) {
        this.grades = grades;
    }
    
    
    public void batchGrades(float g1, float g2, float g3, float g4, float g5){
        grades[0] = g1;
        grades[1] = g2;
        grades[2] = g3;
        grades[3] = g4;
        grades[4] = g5;
    }
    public float calcAvg(){
        float avg = 0.0f;
        float total = 0.0f;
        int testCount = 0;
              for(int index = 0; index < 5; index++){
              total = total + grades[index];
              testCount++;

              } //End Loop
              avg = total/testCount;
        return avg;
}
    
    
    
    
    
    
    
}


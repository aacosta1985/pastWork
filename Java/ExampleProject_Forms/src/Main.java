
package formExample;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import javax.swing.BoxLayout;

/**
 *
 * @author Antonio A
 */
public class Main {

    private JList dayList;
    
    public static void main(String[] args) {

        //Create frame 
        JFrame pane = new JFrame("Array Example - Week2");
        pane.setSize(600, 150);
        pane.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        
        //Create label for selection
        JLabel label = new JLabel("Select a Day");
        
        //Create a close/cancel button
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
             public void actionPerformed(ActionEvent e)
             {
                 System.exit(0);
             }
         });   
        
        //Add array list for days of hte week
        
        String[] dayList = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        
        //Add combo box 
        
        JComboBox combo = new JComboBox(dayList);
        
        //add all components together and make them visible
        pane.add(label);
        pane.add(combo);
        pane.add(cancel);
        pane.setLayout(new FlowLayout());
        pane.setVisible(true);
        
        
        
    
    }
 
}

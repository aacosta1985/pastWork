package HelloWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main {

    public static void main(String[] args) {
        
        int WINDOW_HEIGHT = 300;
        int WINDOW_WIDTH = 300;
    
        //New window
        JFrame window = new JFrame("Greeting!");
        JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        //New label (message within the window)
        JLabel label = new JLabel("Hello World");
        
        //New Button
        JButton button = new JButton ("Close");
        
            button.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    System.exit(0);
                }
            });
            

      
        pane.add(label);
        pane.add(button);
        window.add(pane);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        pane.setVisible(true);
        window.setVisible(true);
    }
    
}

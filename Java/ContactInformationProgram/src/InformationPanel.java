
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author Antonio A
 */
public class InformationPanel extends JPanel
{
    JTextField txtFirstName;
    JTextField txtLastName;
    JTextField txtAmount;
    JComboBox charities;
    String selection;            
    
    String[] charityNames = {"United Way", "Salvation Army", "Task Force for Global Health",
                "Feeding America", "Catholic Charities USA", "Goodwill Industries International",
                "Food for the Poor", "American Cancer Society", "The Y-YMCA", "World Vision"};
    
    public InformationPanel()
    {
        txtFirstName = new JTextField("Enter your first name", 10);
        txtLastName = new JTextField("Enter your last name", 10);
        charities = new JComboBox(charityNames);
        txtAmount = new JTextField("Enter an amount", 5);
        
        charities.addActionListener(new ComboBoxListener());
        
        //clears text box when user clicks inside
        txtFirstName.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                txtFirstName.setText("");
            }
        });
        
        //clears text box when user clicks inside
        txtLastName.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                txtLastName.setText("");
            }
        });
        
        //clears text box when user clicks inside
        txtAmount.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                txtAmount.setText("");
            }
        });
        
        add(Box.createVerticalStrut(5));
        add(Box.createHorizontalStrut(10));        
        add(txtFirstName);
        add(Box.createVerticalStrut(5));
        add(txtLastName);
        add(Box.createVerticalStrut(5));
        add(charities);
        add(Box.createVerticalStrut(5));
        add(txtAmount);
        add(Box.createVerticalGlue());
        add(Box.createHorizontalStrut(10));
    }
    
    private class ComboBoxListener implements ActionListener
    {
        int index;
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
            index = charities.getSelectedIndex();
            selection = charityNames[index];
            
        }
    }
}

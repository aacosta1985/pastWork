
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Crystal Fuell
 * Phetsamone Vongsouvanh
 * Antonio Acosta
 * Greg Richardson
 * 
 * Team A
 * PRG/421
 * April 22, 2014
 * David Frank
 * 
 * GUI based program to accept name of donor, charity, and amount of pledge
 * Display the entries in a JTextArea or JTable
 */
public class ContactInformationProgram extends JFrame
{
    private GreetingPanel greeting;
    private InformationPanel info;
    private JTextArea display;
    
    private JPanel buttonPanel;
    private JButton accept;
    private JButton exit;
    private JButton displayInformation;
    
    String host = "jdbc:derby://localhost:1527/Donations";
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    public ContactInformationProgram()
    {
        super("Phoenix Charity Run");
        
        //close button clicked
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setLayout(new BorderLayout());
        
        greeting = new GreetingPanel();
        info = new InformationPanel();        
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        display = new JTextArea(4, 30);                
        display.setBorder(BorderFactory.createCompoundBorder
                (BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createLoweredSoftBevelBorder())); //border
        
        buildButtonPanel();
        
        add(greeting, BorderLayout.NORTH);
        add(info, BorderLayout.WEST);
        add(display, BorderLayout.EAST);
        add(buttonPanel, BorderLayout.SOUTH);
        
        //pack and display the window
        pack();
        
        //display window
        setVisible(true);
    }
    
    private void buildButtonPanel()
    {
        buttonPanel = new JPanel();
        
        accept = new JButton("Accept");
        exit = new JButton("Exit");
        displayInformation = new JButton("Display Information");
        
        accept.addActionListener(new AccpetListener());
        exit.addActionListener(new ExitListener());
        displayInformation.addActionListener(new DisplayListener());
        
        buttonPanel.add(accept);
        buttonPanel.add(displayInformation);
        buttonPanel.add(exit);
    }
    
    private class AccpetListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            BigDecimal bdAmount;
            
            try
            {                
                String amount = info.txtAmount.getText();
                bdAmount = new BigDecimal(amount);
                bdAmount = bdAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
                
                try
                    {
                        conn = DriverManager.getConnection(host);
                        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                ResultSet.CONCUR_UPDATABLE);
                        ResultSet uprs = stmt.executeQuery("SELECT FIRSTNAME, LASTNAME, CHARITY, AMOUNT FROM Donations");
                        
                        if("Enter your first name".equals(info.txtFirstName.getText())
                                || "Enter your last name".equals(info.txtLastName.getText()))
                        {
                           JOptionPane.showMessageDialog(null, "Please enter your name");
                        }
                        else
                        {
                            String first = info.txtFirstName.getText();
                            String last = info.txtLastName.getText();
                            String selection = info.selection.toString();
                            String amountFinal = bdAmount.toString();
                            
                            uprs.moveToInsertRow();
                            uprs.updateString("FIRSTNAME", first);
                            uprs.updateString("LASTNAME", last);
                            uprs.updateString("CHARITY", selection);
                            uprs.updateString("AMOUNT", amountFinal);
                            
                            uprs.insertRow();
                            uprs.beforeFirst();
                        }                        
                    }
                    catch(SQLException i)
                    {
                        JOptionPane.showMessageDialog(null,"Error: " + i.getMessage());
                    }
                    finally
                    {
                        if(stmt != null)
                        {
                            try {
                                stmt.close();
                            } catch (SQLException ex) {
                                Logger.getLogger(ContactInformationProgram.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                
            }
            catch(NumberFormatException a)
            {
                JOptionPane.showMessageDialog(null, "Conversion Error: " + a.getMessage());
            }
            finally
            {
                info.txtFirstName.setText("Enter your first name");
                info.txtLastName.setText("Enter your last name");
                info.charities.setSelectedIndex(0);
                info.txtAmount.setText("Enter an amount");
            }
        }
    }
    
    private class DisplayListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                conn = DriverManager.getConnection(host);
                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT FIRSTNAME, LASTNAME, CHARITY, AMOUNT FROM Donations");
                
                while(rs.next())
                {
                    String first = rs.getString("FIRSTNAME");
                    String last = rs.getString("LASTNAME");
                    String charity = rs.getString("CHARITY");
                    BigDecimal amount_col = rs.getBigDecimal("AMOUNT");
                    String amount = amount_col.toString();
                    
                    String p = first + " " + last + "    " + charity + "    $" + amount + "\n";
                    
                    display.append(p);
                }
            }
            catch(SQLException err)
            {
                JOptionPane.showMessageDialog(null,"Error: " + err.getMessage());
            }
             finally
             {
                 try
                 {
                     rs.close();
                     stmt.close();
                     conn.close();
                 }
                 catch(Exception exception)
                 {
                 }
             }
        }
    }
    
    private class ExitListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    }
    
    public static void main(String[] args)
    {
        new ContactInformationProgram();
    }
}

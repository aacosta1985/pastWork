import java.awt.Font;
import javax.swing.*;

/**
 *
 * @author Antonio A
 */
public class GreetingPanel extends JPanel
{
    JLabel greeting;
    
    public GreetingPanel()
    {
        greeting = new JLabel ("Welcome to the Phoenix Charity Run's pledge tracker");
        greeting.setFont(new Font("Arial", Font.BOLD, 18));
        
        add(greeting);
    }
}
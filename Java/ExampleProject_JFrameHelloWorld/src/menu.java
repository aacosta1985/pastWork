
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.*;
import java.awt.*;



public class menu extends JFrame{
    
public menu() {
    
    createMenu();
    
    
}

    private void createMenu() {
        
        //create menubar in createMenu method
        JMenuBar menuBar = new JMenuBar();
        
        //add options to menu
        
        menuBar.add(exitOption());
        
    }

    private PopupMenu exitOption() {
    return null;
        
    }


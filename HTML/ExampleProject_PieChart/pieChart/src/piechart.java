/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;
import javax.swing.JApplet;

/**
 *
 * @author Antonio A
 */
public class piechart extends Applet
{
   
   
   public void paint(Graphics g) {
   
   int Per, Prop, Soc, Oth, Total;	// # of types of crimes
   
   float PercPer, PercProp, PercSoc, PercOth;	// The percentages
   
   int x = 300, y = 100, w = 200, h = 200;	// defines the size of the pie
   int startAngle, degrees;	// will be used to draw a pie slice
   
   // Set the # of students in each disipline
   Per = 6;
   Prop = 59;
   Soc = 1;
   Oth = 3;
   
   // Compute percentages
   Total = Per + Prop + Soc + Oth;
   
   // %Personal Crimes
   PercPer = (Per * 100.0f) / Total;
   
   // %Property Crimes
   PercProp = (Prop * 100.0f) / Total;
   
   // %Society Crimes
   PercSoc = (Soc * 100.0f) / Total;
   
   // %other
   PercOth = (Oth * 100.0f) / Total;
   
   
   // Display/output results
   System.out.println("% Crimes Against Person = " + PercPer);
   System.out.println("% Crimes Against Property = " + PercProp);
   System.out.println("% Crimes Against Society = " + PercSoc);
   System.out.println("% All Other Crimes = " + PercOth);
   
   // Display the Pie Chart
   
   // Draw the Pie for crimes against persons
   startAngle = 0;
   degrees = (int)(PercPer * 360 / 100);
   
   g.setColor(Color.green);
   g.fillArc(x, y, w, h, startAngle, degrees);
   
   // Draw the Pie for crimes against property
   startAngle = degrees;
   degrees = (int)(PercProp * 360 / 100);
   
   g.setColor(Color.red);
   g.fillArc(x, y, w, h, startAngle, degrees);
   
   // Draw the Pie for crimes against society
   startAngle = startAngle + degrees;
   degrees = (int)(PercSoc * 360 / 100);
   
   g.setColor(Color.yellow);
   g.fillArc(x, y, w, h, startAngle, degrees); // offset this slice a little
   
   // Draw the Pie for all other crimes
   startAngle = startAngle + degrees;
   degrees = (int)(PercOth * 360 / 100);
   
   g.setColor(Color.black);
   g.fillArc(x, y, w, h, startAngle, degrees); // offset this slice a little
   
   
   
   } // paint
} // PieChart
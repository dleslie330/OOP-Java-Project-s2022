//Dakota Leslie
//Object Oriented Software Development in Java Spring 2022
package pointofsalesbackend;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainFrame {
	public static void main(String[] args) {
		JFrame frame = new JFrame("PointOfSales");//creates the GUI's main frame that contains the panel with the components used
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 350);
		
		MainPanel myPanel = new MainPanel();//creates the panel object
		
		frame.getContentPane().add(myPanel);//adds the panel to the frame
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {//tells the program what to do when the user closes the frame
				myPanel.doClose();
			}
		});
	}
}
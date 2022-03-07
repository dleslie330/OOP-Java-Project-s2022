package pointofsalesbackend;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainFrame {
	public static void main(String[] args) {
		JFrame frame = new JFrame("PointOfSales");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 350);
		
		MainPanel myPanel = new MainPanel();
		
		frame.getContentPane().add(myPanel);
		//frame.pack();
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				myPanel.doClose();
			}
		});
	}
}
//Dakota Leslie
//Object Oriented Software Development in Java Spring 2022
//I know there are probably several add and/or remove and/or repaint function calls that are unused
//I was having issues, and the project just works in this state. This lead me to just leaving it as it is
package pointofsalesbackend;

import pointofsalesbackend.Itemlist;
import pointofsalesbackend.Item;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class MainPanel extends JPanel{
	private Itemlist myItems = new Itemlist(); //creates an item from the backend
	private Vector<Item> cart = new Vector<Item>(); //stores the user's items selected for purchase
	private JTextField textField; //textfield used as the search bar
	private JToolBar toolBar = new JToolBar();//toolbar to hold important actions
	private JList<Item> list = new JList<Item>();//list for user to select items
	private JScrollPane scrollPane = new JScrollPane();//allows the user to scroll in the list if there are too many options to see in one instance
	private JRadioButton rdbtn1;//creates a radio button used for multiple things
	private JRadioButton rdbtn2;//creates a radio button used for multiple things
	private JRadioButton rdbtn3;//creates a radio button used for multiple things
	private ButtonGroup group = new ButtonGroup();//group for the radio buttons
	private JSpinner spinner = new JSpinner();//spinner so the user can say how many items they want
	private DecimalFormat f = new DecimalFormat("##.00");
	
	public MainPanel() {
		super();
		setLayout(null);
		rdbtn1 = new JRadioButton();
		rdbtn2 = new JRadioButton();
		rdbtn3 = new JRadioButton();
		
		spinner = new JSpinner(new SpinnerNumberModel(0, 0, 10000, 1));
		spinner.setBounds(45, 263, 30, 20);
		add(spinner);//creates the spinner component

		JComboBox comboBox = new JComboBox();//creates the combo box component
		Vector<Item> collection = myItems.collectionOfProducts("all");
		ArrayList<String> products = new ArrayList<String>();
		for (Item i: collection) {
			products.add(i.getProduct());
		}
		comboBox.setModel(new DefaultComboBoxModel(products.toArray()));//fills the combobox with data from the backend
		comboBox.setBounds(10, 11, 59, 22);
		comboBox.addActionListener(new ActionListener() {//adds an action to the combobox
			public void actionPerformed(ActionEvent e) {
				remove(scrollPane);
				group.remove(rdbtn1);
				group.remove(rdbtn2);
				group.remove(rdbtn3);
				remove(rdbtn1);
				remove(rdbtn2);
				remove(rdbtn3);
				repaint();//clears all components used in different actions
				
				String item = (String) comboBox.getSelectedItem();
				Vector<Item> result = myItems.collectionOfProducts(item);
				list.setListData(result);
				scrollPane.setBounds(46, 85, 300, 150);
				add(scrollPane);
				scrollPane.setViewportView(list);
				
				if((list.isSelectionEmpty())) {
				list.addListSelectionListener(new ListSelectionListener() {//adds the radio buttons when an item is selected

					@Override
					public void valueChanged(ListSelectionEvent e) {
						
						rdbtn1.setText("Add to Cart");
						rdbtn1.setBounds(42, 235, 95, 23);
						add(rdbtn1);
						
						rdbtn2.setText("Restock");
						rdbtn2.setBounds(153, 235, 75, 23);
						add(rdbtn2);
						
						rdbtn3.setText("Remove from Cart");
						rdbtn3.setBounds(240, 235, 140, 23);
						add(rdbtn3);
				
						group.add(rdbtn1);
						group.add(rdbtn2);
						group.add(rdbtn3);
						
						repaint();
						
					}
				});
				}
		
				remove(rdbtn1);
				remove(rdbtn2);
				remove(rdbtn3);
				repaint();
			
			}
		});
		repaint();
		add(comboBox);
		
		toolBar.setBounds(87, 11, 353, 23);
		add(toolBar);		//adds the toolbar to the panel
		
		textField = new JTextField();
		toolBar.add(textField);
		textField.setColumns(10);//adds the textfield to the toolbar
		
		JButton btnSearch = new JButton("Search");//implements a search function from teh GUI
		btnSearch.addActionListener(new ActionListener() {//adds an action to the combobox
			public void actionPerformed(ActionEvent e) {
				remove(scrollPane);
				group.remove(rdbtn1);
				group.remove(rdbtn2);
				group.remove(rdbtn3);
				remove(rdbtn1);
				remove(rdbtn2);
				remove(rdbtn3);
				repaint();//clears all components used in different actions
				
				String item = (String) textField.getText();
				Vector<Item> result = myItems.collectionOfProducts(item);
				list.setListData(result);
				scrollPane.setBounds(46, 85, 300, 150);
				add(scrollPane);
				scrollPane.setViewportView(list);
				
				if((list.isSelectionEmpty())) {
				list.addListSelectionListener(new ListSelectionListener() {//adds the radio buttons when an item is selected

					@Override
					public void valueChanged(ListSelectionEvent e) {
						
						rdbtn1.setText("Add to Cart");
						rdbtn1.setBounds(42, 235, 95, 23);
						add(rdbtn1);
						
						rdbtn2.setText("Restock");
						rdbtn2.setBounds(153, 235, 75, 23);
						add(rdbtn2);
						
						rdbtn3.setText("Remove from Cart");
						rdbtn3.setBounds(240, 235, 140, 23);
						add(rdbtn3);
				
						group.add(rdbtn1);
						group.add(rdbtn2);
						group.add(rdbtn3);
						
						repaint();
						
					}
				});
				}
		
				remove(rdbtn1);
				remove(rdbtn2);
				remove(rdbtn3);
				repaint();
			
			}
		});
		repaint();
		toolBar.add(btnSearch);
		
		JSeparator separator = new JSeparator();
		toolBar.add(separator);//separates the toolbar buttons
		
		JButton btnCart = new JButton("Cart");
		btnCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//allows the user to see the items that are in their cart
				remove(scrollPane);
				group.remove(rdbtn1);
				group.remove(rdbtn2);
				remove(rdbtn1);
				remove(rdbtn2);
				remove(rdbtn3);
				repaint();
				
				list.setListData(cart);
				scrollPane.setBounds(46, 85, 300, 150);
				add(scrollPane);
				scrollPane.setViewportView(list);
				
				rdbtn1.setText("Add to Cart");
				rdbtn1.setBounds(42, 235, 95, 23);
				add(rdbtn1);
				
				rdbtn2.setText("Restock");
				rdbtn2.setBounds(153, 235, 75, 23);
				add(rdbtn2);
				
				rdbtn3.setText("Remove from Cart");
				rdbtn3.setBounds(240, 235, 140, 23);
				add(rdbtn3);
		
				group.add(rdbtn1);
				group.add(rdbtn2);
				group.add(rdbtn3);
				
				repaint();
				
				remove(rdbtn1);
				remove(rdbtn2);
				remove(rdbtn3);
				repaint();
			}
		});
		repaint();
		toolBar.add(btnCart);
		
		JSeparator separator_1 = new JSeparator();
		toolBar.add(separator_1);
		
		JButton btnCheckout = new JButton("Checkout");
		btnCheckout.addActionListener(new ActionListener() {//allows the user to check out with the items in their cart
			public void actionPerformed(ActionEvent e) {
				remove(scrollPane);
				remove(rdbtn1);
				remove(rdbtn2);
				remove(rdbtn3);
				repaint();
				
				double total = 0;
				for(Item i: cart) {
					total += (i.getPrice() * i.getStock());//finds the total cost of the cart
				}
				JOptionPane layeredPane = new JOptionPane("Checkout");//adds a pop-up that completes the checkout process
				layeredPane.setBounds(40, 57, 355, 150);
				layeredPane.setBackground(Color.white);
				add(layeredPane);
				
				Label label = new Label("Total: " + f.format(total) + ". Complete checkout?");//displays the total price of the cart
				label.setBounds(62, 10, 280, 22);
				layeredPane.add(label);
				
				Button button = new Button("Confirm");
				button.setBounds(29, 49, 70, 22);
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {// quits program if the user is done shopping
						doClose();
						System.exit(0);
					}
				});
				layeredPane.add(button);
				
				Button button_1 = new Button("Cancel");
				button_1.setBounds(157, 49, 70, 22);
				button_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {//allows the user to continue shopping
						remove(layeredPane);
						repaint();
					}
				});
				layeredPane.add(button_1);
				repaint();
			}
			
		});
		toolBar.add(btnCheckout);
		
		JLabel lblThankYouFor = new JLabel("Thank you for Shopping!");//a nice message for users
		lblThankYouFor.setBounds(10, 286, 149, 14);
		add(lblThankYouFor);
		
		JButton btnAddItem = new JButton("Continue");//allows the user to complete an action with selected item based on which radio button is selected
		btnAddItem.setBounds(351, 266, 89, 23);
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtn1.isSelected()) {//makes sure the user is wanting to add stuff to their cart
					cart.add(new Item (list.getSelectedValue().getID(), list.getSelectedValue().getProduct(), list.getSelectedValue().getPrice(), list.getSelectedValue().getCategory(), (Integer)spinner.getValue()));//creates a new item in the cart as to keep the "stock" values separate
					list.getSelectedValue().removeStock((Integer)spinner.getValue());//lowers the store's stock
					repaint();
				}
				else if (rdbtn2.isSelected()) {//adds stock to the store
					list.getSelectedValue().addStock((Integer)spinner.getValue());
					repaint();
				}
				else if (rdbtn3.isSelected()) {//removes object from the user's cart and adds it back to the store's item. also makes sure to remove the item from the user's cart if there is zero of that item
					list.getSelectedValue().removeStock((Integer) spinner.getValue());
					myItems.findProduct(list.getSelectedValue().getID()).addStock((Integer)spinner.getValue());
					if(list.getSelectedValue().getStock() == 0) {
						cart.remove(list.getSelectedIndex());
						repaint();
					}
				}
				
				remove(scrollPane);
				group.remove(rdbtn1);
				group.remove(rdbtn2);
				remove(rdbtn1);
				remove(rdbtn2);
				remove(rdbtn3);
				
				repaint();
			}
		});
		add(btnAddItem);
		
		JLabel lblHowMany = new JLabel("How Many?");//labels the spinner so the user knows what it is for
		lblHowMany.setBounds(85, 266, 65, 14);
		add(lblHowMany);
	}
	
	public void doClose() {
		myItems.writeFile();//makes sure the file is updated when the program closes
	}
}
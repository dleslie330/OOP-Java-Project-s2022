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

public class MainPanel extends JPanel{
	private Itemlist myItems = new Itemlist();
	private Vector<Item> cart = new Vector<Item>();
	private JTextField textField;
	private JToolBar toolBar = new JToolBar();
	private JList<Item> list = new JList<Item>();
	private JScrollPane scrollPane = new JScrollPane();
	private JRadioButton rdbtn1 = new JRadioButton();
	private JRadioButton rdbtn2 = new JRadioButton();
	private ButtonGroup group = new ButtonGroup();
	private JSpinner spinner = new JSpinner();
	
	public MainPanel() {
		super();
		setLayout(null);
		
		spinner = new JSpinner(new SpinnerNumberModel(0, 0, 10000, 1));
		spinner.setBounds(45, 263, 30, 20);
		add(spinner);

		JComboBox comboBox = new JComboBox();
		Vector<Item> collection = myItems.collectionOfProducts("all");
		ArrayList<String> products = new ArrayList<String>();
		for (Item i: collection) {
			products.add(i.getProduct());
		}
		comboBox.setModel(new DefaultComboBoxModel(products.toArray()));
		comboBox.setBounds(10, 11, 59, 22);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(scrollPane);
				remove(rdbtn1);
				remove(rdbtn2);
				
				String item = (String) comboBox.getSelectedItem();
				Vector<Item> result = myItems.collectionOfProducts(item);
				list = new JList<Item>(result);
				scrollPane.setBounds(46, 85, 300, 150);
				add(scrollPane);
				scrollPane.setViewportView(list);
				
				list.addListSelectionListener(new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						
						rdbtn1.setText("Add to Cart");
						rdbtn1.setBounds(42, 235, 105, 23);
						add(rdbtn1);
						
						rdbtn2.setText("Restock");
						rdbtn2.setBounds(153, 235, 105, 23);
						add(rdbtn2);
				
						group.add(rdbtn1);
						group.add(rdbtn2);
						
						repaint();
					}
				});
				repaint();
			}
		});
		add(comboBox);
		
		toolBar.setBounds(87, 11, 353, 23);
		add(toolBar);		
		
		textField = new JTextField();
		toolBar.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(scrollPane);
				remove(rdbtn1);
				remove(rdbtn2);
				
				String search = textField.getText().toLowerCase();
				Vector<Item> result = myItems.collectionOfProducts(search);
				list = new JList<Item>(result);
				scrollPane.setBounds(46, 85, 300, 150);
				add(scrollPane);
				scrollPane.setViewportView(list);
				
				list.addListSelectionListener(new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						
						rdbtn1.setText("Add to Cart");
						rdbtn1.setBounds(42, 235, 105, 23);
						add(rdbtn1);
						
						rdbtn2.setText("Restock");
						rdbtn2.setBounds(153, 235, 105, 23);
						add(rdbtn2);
				
						group.add(rdbtn1);
						group.add(rdbtn2);
					
						repaint();
					}
					
				});
				
				repaint();
			}
		});
		toolBar.add(btnSearch);
		
		JSeparator separator = new JSeparator();
		toolBar.add(separator);
		
		JButton btnCart = new JButton("Cart");
		btnCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(scrollPane);
				remove(rdbtn1);
				remove(rdbtn2);
				
				list = new JList<Item>(cart);
				scrollPane.setBounds(46, 85, 300, 150);
				add(scrollPane);
				scrollPane.setViewportView(list);
						
				rdbtn1 = new JRadioButton("Remove from Cart");
				rdbtn1.setBounds(42, 235, 130, 23);
				add(rdbtn1);
				
				group.add(rdbtn1);
					
				repaint();
			}
		});
		toolBar.add(btnCart);
		
		JSeparator separator_1 = new JSeparator();
		toolBar.add(separator_1);
		
		JButton btnCheckout = new JButton("Checkout");
		toolBar.add(btnCheckout);
		
		JLabel lblThankYouFor = new JLabel("Thank you for Shopping!");
		lblThankYouFor.setBounds(10, 286, 149, 14);
		add(lblThankYouFor);
		
		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.setBounds(351, 266, 89, 23);
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtn1.isSelected() && rdbtn1.getText().equals("Add to Cart")) {
					cart.add(new Item (list.getSelectedValue().getID(), list.getSelectedValue().getProduct(), list.getSelectedValue().getPrice(), list.getSelectedValue().getCategory(), (Integer)spinner.getValue()));
					list.getSelectedValue().removeStock((Integer)spinner.getValue());
				}
				else if (rdbtn2.isSelected()) {
					list.getSelectedValue().addStock((Integer)spinner.getValue());
				}
				else if (rdbtn1.isSelected() && rdbtn1.getText().equals("Remove from Cart")) {
					list.getSelectedValue().removeStock((Integer) spinner.getValue());
					myItems.findProduct(list.getSelectedValue().getID()).addStock((Integer)spinner.getValue());
					if(list.getSelectedValue().getStock() == 0) {
						cart.remove(list.getSelectedIndex());
						repaint();
					}
				}
				
				remove(list);
				remove(rdbtn1);
				remove(rdbtn2);
				
				repaint();
			}
		});
		add(btnAddItem);
		
		JLabel lblHowMany = new JLabel("How Many?");
		lblHowMany.setBounds(85, 266, 65, 14);
		add(lblHowMany);
		
	}
	
	public void doClose() {
		myItems.writeFile();
	}
}
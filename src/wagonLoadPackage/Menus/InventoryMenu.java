package wagonLoadPackage.Menus;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import wagonLoadPackage.Item;
import wagonLoadPackage.Wagon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class InventoryMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ArrayList<JLabel> inventory;
    private Wagon wagon;
	
    /**
     * InventoryMenu.java
     * Created on 05/05/2024
     * By Rod Piton
     * 
     * Creates the UI for the inventory menu. 
     * Allows user to see all items loaded in wagon
     * uses Array list of JLabels
     */	
    
    
    
public JLabel ItemLabel(Item item) {
		
		// Identify item and if its pre-loaded
		String text = item.getName() + ": " + item.getWeight();
		
		// Create and log JCheckBox
		JLabel box = new JLabel(text);
		inventory.add(box);
		
		return box;
	}

	
	
	
	
	

	/**
	 * Create the frame.
	 */
	public InventoryMenu(Wagon wagon) {
		setBackground(new Color(0, 128, 64));
		
	    this.wagon = wagon;
		
		this.inventory = new ArrayList<JLabel>();
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 369);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel ItemPanel = new JPanel();
		ItemPanel.setBackground(new Color(0, 255, 64));
		ItemPanel.setBounds(0, 47, 434, 283);
		contentPane.add(ItemPanel);
		ItemPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Inventory");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Calisto MT", Font.PLAIN, 23));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 434, 47);
		contentPane.add(lblNewLabel);
		
		
		  int x = 0;
		  int itemRows = 8;
        // Create three columns of item checkBox...
        for (int j = 0; j < 3 ; j++ ) {
        	// Track Rows 
        	int row = 0;
        	
        	
        	// Create an 8 rows of item checkBox...
	        do{
	        	// Get current Item
	        	Item item = wagon.getItem(x);
	        	
	            System.out.println("item bug: "+ item.getName());

	        	if(item.getIsLoaded()){
	        	// Create checkBox for Item
	        	JLabel Label = ItemLabel(item);
	        	
	        	Label.setBounds( (6 + j * 110) , (16 + row * 26) , 110, 35);
	        	ItemPanel.add(Label);
	        	}
	        	
	        	// Increment to next item in list
	        	x++;
	        	row++;
	        } while ( row < itemRows && x < wagon.getItemListSize());
        }
	}
}

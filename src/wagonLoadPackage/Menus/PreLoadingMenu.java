package wagonLoadPackage.Menus;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import wagonLoadPackage.Item;
import wagonLoadPackage.Wagon;
import wagonLoadPackage.WagonLoad;

import javax.swing.SwingConstants;


/**
 * PreLoadingMenu.java 
 * Created on 4/27/2024
 * By David Flores
 * 
 * Creates a new UI Frame for the Main Menu UI. 
 * Allows user to pre-select their wagon load before starting the game.
 * TODO: Adjust for singular foodItem class
 * TODO: Add weight editting
 */
public class PreLoadingMenu extends JFrame {

	// Class Variables
	private static final long serialVersionUID = 1L;
	private Wagon wagon = new Wagon();
	int totalWeight = 0;

	
	// JComponents
	private JPanel contentPane;
	public JFrame frmPreLoad;
	private JLabel lblTotalWeight_1;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PreLoadingMenu frame = new PreLoadingMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the frame.
	 */
	public PreLoadingMenu() {
		initialize();
	}
	
	
	/**
	 * Creates a JCheckBox with specified item name and weight using 
	 * the index provided.
	 * - David Flores
	 * @param index - Integer that specifies which item to fill out the 
	 * JCheckBox.
	 * @return - JCheckBox with item name and weight
	 */
	public JCheckBox ItemCheckBox(Item item) {
		
		String text = item.getName() + ": " + item.getWeight();
		boolean selected = item.getIsLoaded();
		
		JCheckBox box = new JCheckBox(text, selected);
		
		return box;
	}
	
	
	/**
	 * Updates the UI element for total weight.
	 * Calculates the totalWeight, then updates UI label's text.
	 * - David Flores
	 */
	public void updateTotalWeight() {
		// Calculate totalWeight
		totalWeight = wagon.getTotalWeight();
		
		//Update lbl text
		lblTotalWeight_1.setText(totalWeight + " lbs");
	}
	
	/**
	 * Initializes the frame.
	 */
	private void initialize() {	
		
		// Menu Frame
		frmPreLoad = new JFrame();
		frmPreLoad.setTitle("Pack Your Wagon");
		frmPreLoad.setBounds(100, 100, 589, 447);
		frmPreLoad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmPreLoad.getContentPane().setLayout(null);
        
        /// Total Weight Labels
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Travel Results", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel.setBounds(16, 332, 549, 73);
        frmPreLoad.getContentPane().add(panel);
        panel.setLayout(null);
        
        JLabel lblTotalWeight = new JLabel("Total Weight:");
        lblTotalWeight.setHorizontalAlignment(SwingConstants.TRAILING);
        lblTotalWeight.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblTotalWeight.setBounds(65, 18, 75, 14);
        panel.add(lblTotalWeight);
        
        lblTotalWeight_1 = new JLabel("____");
        lblTotalWeight_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblTotalWeight_1.setBounds(150, 18, 75, 14);
        panel.add(lblTotalWeight_1);
        
        JLabel lblWeightWarning = new JLabel("Max Weight is 2400 lbs!");
        lblWeightWarning.setHorizontalAlignment(SwingConstants.CENTER);
        lblWeightWarning.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblWeightWarning.setBounds(79, 47, 125, 14);
        panel.add(lblWeightWarning);
                
        // Menu Content Panel
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				
        
        /// Food Panel
        JPanel foodPanel = new JPanel();
        foodPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Food", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        foodPanel.setBounds(300, 16, 265, 305);
        frmPreLoad.getContentPane().add(foodPanel);
        foodPanel.setLayout(null);

        
        /// Food CheckBoxes - David Flores
       
        // Track current item in itemList
        int x = 0;
        
        // Create two columns of item checkBox...
        for (int j = 0; j < 2 ; j++ ) {
        	
        	// Track Rows 
        	int row = 0;
        	
        	// Create an 10 rows of item checkBox (of the 13 foodItems)...
        	do{
        		// Get current Item
	        	Item item = wagon.getItem(x);
	        	
	        	// Create checkBox for Item
	        	JCheckBox checkBox = ItemCheckBox(item);
	        	checkBox.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        		item.setIsLoaded(checkBox.isSelected()); 
		        		updateTotalWeight();
		        	}
		        });
	        	checkBox.setBounds( (6 + j * 110) , (16 + ( row % 8 ) * 26) , 110, 35);
	        	foodPanel.add(checkBox);
	        	
	        	// Increment to next item and row
	        	x++;
	        	row++;
	        } while ( row < 10 && x < 13 && x < wagon.getItemListSize());
        }
        
        
        /// Item Panel 
		JPanel itemPanel = new JPanel();
	    itemPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Items", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	    itemPanel.setBounds(16, 16, 265, 305);
	    frmPreLoad.getContentPane().add(itemPanel);
	    itemPanel.setLayout(null);
        
        
        /// Item CheckBoxes - David Flores
	    
        // Create two columns of item checkBox...
        for (int j = 0; j < 2 ; j++ ) {
        	// Track Rows 
        	int row = 0;
        	
        	
        	// Create an 8 rows of item checkBox...
	        do{
	        	// Get current Item
	        	Item item = wagon.getItem(x);
	        	
	            System.out.println("item bug: "+ item.getName());

	        	
	        	// Create checkBox for Item
	        	JCheckBox checkBox = ItemCheckBox(item);
	        	checkBox.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        		item.setIsLoaded(checkBox.isSelected()); 
		        		updateTotalWeight();
		        	}
		        });
	        	checkBox.setBounds( (6 + j * 110) , (16 + ( row ) * 26) , 110, 35);
	        	itemPanel.add(checkBox);
	        	
	        	// Increment to next item in list
	        	x++;
	        	row++;
	        } while ( row < 10 && x < wagon.getItemListSize());
        }
        
        
        // Travel Result Label
        JLabel lblTravelResult = new JLabel("");
        lblTravelResult.setHorizontalAlignment(SwingConstants.CENTER);
        lblTravelResult.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblTravelResult.setBounds(306, 47, 233, 14);
        panel.add(lblTravelResult);
        
        // Travel Button - David Flores
        JButton btnNewButton = new JButton("\"Travel\"");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		lblTotalWeight_1.setText(totalWeight + " lbs");
        		
        		// Calculate foodWeight
        		int foodWeight = wagon.getFoodWeight();
        		int totalWeight = wagon.getTotalWeight();

        		
        		System.out.println(foodWeight);

        		
        		// If enough food to travel, ...
        		// TODO: Move to after isEnoughFoodToTravel check
        		if (Wagon.travel.isEnoughFoodToTravel(foodWeight))
        		{
            		System.out.println("enough food");

        			
        			// Check if wagon is overweight...
        			if (totalWeight <= wagon.maxWeight) {
        				
        				System.out.println("not overweight");

						// Prompt Success
						lblTravelResult.setText("Yes! You're set to go!");
						
						// Confirm if User wants to proceed...
						int input = JOptionPane.showConfirmDialog(null, 
				                "Your wagon is set and you have enough food for now. Do you want to proceed?", "Wagon is Ready to go...",JOptionPane.YES_NO_OPTION);

						// If user answers yes, start game...
						if (input == 0) {
	        				
							System.out.println("\n\nNew Game Start!");
	        				
	        				// Open Travel Menu and dispose of current menu
							WagonLoad frame = new WagonLoad(wagon);
							frame.setVisible(true);
							frmPreLoad.dispose();	
						}
					 			
        			// Fail: Wagon is overweight
        			} else {
        				lblTravelResult.setText("Wagon is overweight!");
        				
        			}
        		}
        		// Fail: Not enough food
				else {
					// Prompt Try Again
					lblTravelResult.setText("Not enough food! Try Again!");
				}
    			
        		        		
        		
        	}
        });
        btnNewButton.setBounds(377, 18, 89, 23);
        panel.add(btnNewButton);
        
				
		// Pre-Update UI Elements
		updateTotalWeight();

		
		setContentPane(contentPane);
		frmPreLoad.setVisible(true);
	}

	/**
	 * Sets the frame to visible.
	 * @param visible - Boolean that when true 
	 * allows the frame to be visible.
	 */
	public void setVisible(boolean visible) {
		frmPreLoad.setVisible(visible);
    }
}

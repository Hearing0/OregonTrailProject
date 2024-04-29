package wagonLoadPackage.Menus;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
import javax.swing.JTextField;


/**
 * PreLoadingMenu.java 
 * Created on 4/27/2024
 * By David Flores
 * 
 * Creates a new UI Frame for the Main Menu UI. 
 * Allows user to pre-select their wagon load before starting the game.
 * TODO: Adjust for singular foodItem class
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
	private JTextField textFieldPrompt;
	private Map<String, JCheckBox> itemCheckBoxes = new HashMap<String, JCheckBox>();
	
	
	
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
	 * the index provided. Adds to current JCheckBox Map for UI updating.
	 * - David Flores
	 * @param index - Integer that specifies which item to fill out the 
	 * JCheckBox.
	 * @return - JCheckBox with item name and weight
	 */
	public JCheckBox ItemCheckBox(Item item) {
		
		// Identify item and if its pre-loaded
		String text = item.getName() + ": " + item.getWeight();
		boolean selected = item.getIsLoaded();
		
		// Create and log JCheckBox
		JCheckBox box = new JCheckBox(text, selected);
		itemCheckBoxes.put(item.getName(), box);
		
		return box;
	}
	
	
	/**
	 * Updates the UI element for total weight.
	 * Calculates the totalWeight, then updates UI label's text.
	 * - David Flores
	 */
	public void updateTotalWeightUI() {
		// Calculate totalWeight
		totalWeight = wagon.getTotalWeight();
		
		//Update lbl text
		lblTotalWeight_1.setText(totalWeight + " lbs");
	}
	
	/**
	 * Updates the "Name"'s ItemCheckBox weight.
	 * - David Flores
	 * @param name - Name/Key of the element that needs its UI updated.
	 */
	public void updateItemWeightUI(String name) {
		
		// If name has an ItemCheckBox, update its label
		if (itemCheckBoxes.containsKey(name)) {
			// Get Item and JCheckBox
			Item item = wagon.getItem(name);
			JCheckBox itemBox = itemCheckBoxes.get(name);
			
			// Update JCheckBox label
			String newLabel = item.getName() + ": " + item.getWeight();
			itemBox.setText(newLabel);
		}
		// Fail: Display error to Dev
		else
		{
			System.out.println("Error: Name is not recognized");
		}
	}
	
	/**
	 * Updates the "Name"'s ItemCheckBox weight.
	 * - David Flores
	 * @param name - Name/Key of the element that needs its UI updated.
	 */
	public void updateItemWeightUI(Item item) {
		
		// If Item exists in itemList, update its label
		if (wagon.itemList.contains(item)) {
			// Get Item name and JCheckBox
			String name = item.getName();
			JCheckBox itemBox = itemCheckBoxes.get(name);
			
			// Update JCheckBox label
			String newLabel = item.getName() + ": " + item.getWeight();
			itemBox.setText(newLabel);
			itemBox.setSelected(item.getIsLoaded());
		}
		
		// Fail: Display error to Dev
		else
		{
			System.out.println("Error: Item is not recognized");
		}
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
        lblTotalWeight_1.setBounds(150, 18, 138, 14);
        panel.add(lblTotalWeight_1);
        
        JLabel lblWeightWarning = new JLabel("Max Weight is 2400 lbs!");
        lblWeightWarning.setHorizontalAlignment(SwingConstants.CENTER);
        lblWeightWarning.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblWeightWarning.setBounds(75, 43, 125, 14);
        panel.add(lblWeightWarning);
                
        // Menu Content Panel
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				
        
        /// Food Panel
		int foodRows = 8;
		
        JPanel foodPanel = new JPanel();
        foodPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Food", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        foodPanel.setBounds(300, 16, 265, (32 + foodRows * 26));	// 
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
		        		updateTotalWeightUI();
		        	}
		        });
	        	checkBox.setBounds( (6 + j * 110) , (16 + row * 26) , 110, 35);
	        	foodPanel.add(checkBox);
	        	
	        	// Increment to next item and row
	        	x++;
	        	row++;
	        } while ( row < foodRows && x < 13 && x < wagon.getItemListSize());
        }
        
        
        /// Item Panel 
        int itemRows = 10;
        
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
		        		updateTotalWeightUI();
		        	}
		        });
	        	checkBox.setBounds( (6 + j * 110) , (16 + row * 26) , 110, 35);
	        	itemPanel.add(checkBox);
	        	
	        	// Increment to next item in list
	        	x++;
	        	row++;
	        } while ( row < itemRows && x < wagon.getItemListSize());
        }
        
        
        // Travel Result Label
        JLabel lblTravelResult = new JLabel("");
        lblTravelResult.setHorizontalAlignment(SwingConstants.CENTER);
        lblTravelResult.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblTravelResult.setBounds(173, 30, 175, 14);
        panel.add(lblTravelResult);
        
        // Travel Button - David Flores
        JButton btnNewButton = new JButton("Start Traveling");
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
        btnNewButton.setBounds(316, 41, 223, 21);
        panel.add(btnNewButton);
        
        JLabel lblStartPrompt = new JLabel("Once ready, click the button below!");
        lblStartPrompt.setHorizontalAlignment(SwingConstants.CENTER);
        lblStartPrompt.setBounds(316, 18, 223, 20);
        panel.add(lblStartPrompt);
        
        textFieldPrompt = new JTextField();
        textFieldPrompt.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// TODO: test!!!
        		
        		try {
        			// Interpret Entered Text as Item and Weight
        			String changeTxt = textFieldPrompt.getText();
        			String[] arrTxt = changeTxt.split(" ");
        			
        			System.out.println( arrTxt[0] );
        			System.out.println( arrTxt[1] );
        			
        			Item item = wagon.getItem(arrTxt[0]);
        			
        			// Set Item weight 
        			item.setWeight( Integer.parseInt(arrTxt[1] ) );
        			
        			// Update Weight labels
        			updateTotalWeightUI();
        			updateItemWeightUI(item);
        			
        		} catch (Exception evt) {
        			JOptionPane.showMessageDialog(new JFrame(), 
        					"Invalid Prompt entered!\n"
        					+ "Please enter the prompt like the following :\n"
        					+ "rice 42", 
        					"Error",
        					JOptionPane.ERROR_MESSAGE
        				);        		
        		}
        	}
        });
        textFieldPrompt.setBounds(419, 267, 146, 20);
        frmPreLoad.getContentPane().add(textFieldPrompt);
        textFieldPrompt.setColumns(10);
        
        JLabel lblPrompt = new JLabel("Change Item Weight:");
        lblPrompt.setHorizontalAlignment(SwingConstants.TRAILING);
        lblPrompt.setBounds(286, 267, 123, 20);
        frmPreLoad.getContentPane().add(lblPrompt);
        
        JLabel lblExamplerice = new JLabel("Example: \"rice 400\"");
        lblExamplerice.setHorizontalAlignment(SwingConstants.CENTER);
        lblExamplerice.setBounds(300, 301, 265, 20);
        frmPreLoad.getContentPane().add(lblExamplerice);
        
				
		// Pre-Update UI Elements
		updateTotalWeightUI();

		
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

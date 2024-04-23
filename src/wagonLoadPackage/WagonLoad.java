package wagonLoadPackage;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;


/**
 * WagonLoad.java 
 * Created on 3/23/2024
 * By David Flores
 * 
 * Creates the UI for the wagon worksheet. 
 * Allows user to manage loading, food consumption,
 * travel speed. Shows max weight and if there is enough
 * food to make the trip, along with some favor text
 * descriptors for food consumption and travel speed. 
 */
public class WagonLoad {

	private JFrame frmPackYourWagon;
	private JCheckBox chckbxItem1;
	private JTextField textField;
	private JTextField textField_1;
	
	// Initialize Wagon
	private Wagon wagon = new Wagon();

	private JCheckBox chckbxItem1_1;
	private JCheckBox chckbxItem1_1_1;
	private JCheckBox chckbxItem1_2;
	private JCheckBox chckbxItem1_1_2;
	private JCheckBox chckbxItem1_3;
	private JCheckBox chckbxItem1_1_3;
	private JCheckBox chckbxItem1_4;
	private JCheckBox chckbxItem1_1_4;
	private JCheckBox chckbxItem1_5;
	private JCheckBox chckbxItem1_1_5;
	private JCheckBox chckbxItem1_6;
	private JCheckBox chckbxItem1_1_6;
	private JCheckBox chckbxItem1_7;
	private JCheckBox chckbxItem1_1_7;
	private JCheckBox chckbxItem1_8;
	private JCheckBox chckbxItem1_1_3_1;
	private JLabel lblTotalWeight_1;
	//private JTextField testField;
	
	
	ArrayList<Location> map = wagon.travel.getMap();
	int consumptionValue = 0;
	int travelValue = 0;
	int totalDist = 0;
	int days = 0;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WagonLoad window = new WagonLoad();
					window.frmPackYourWagon.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WagonLoad() {
		//System.out.println("Y");
		initialize();
		getTotalDistance();
		
		// Debug: Readout itemList
		/*
		int i = 0;
		for ( Item item :  wagon.itemList ) {
			System.out.println("itemList [" + i + "]: ");
			System.out.println(item.name);
			i++;
		}
		*/
	}
	
	/**
	 * Updates the UI element for total weight.
	 * Calculates the totalWeight, then updates UI label's text.
	 */
	public void updateTotalWeight() {
		// Calculate totalWeight
		int totalWeight = wagon.getTotalWeight();
		
		//Update lbl text
		lblTotalWeight_1.setText(totalWeight + " lbs");
	}
	
	/**
	 * Calculates the total distance of the trip by adding the distance between each landmark together
	 */
	public void getTotalDistance() {
		totalDist = 0;
		
		for (int i = 0; i < map.size(); i++)
		{
			Location current = map.get(i);
			totalDist = totalDist + current.getDistance();
		}
	}

	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPackYourWagon = new JFrame();
		frmPackYourWagon.setTitle("Pack Your Wagon");
		frmPackYourWagon.setBounds(100, 100, 839, 382);
		frmPackYourWagon.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmPackYourWagon.getContentPane().setLayout(null);
        
        
        // Debug: testable textField
        /*testField = new JTextField();
        testField.setColumns(10);
        testField.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Retrieve Text
        		String textValue = textField.getText();
        		
        		// Try storing text as a value
        		try {
	        		int value = Integer.valueOf(textValue);
	        		
	        		// Try to set Food Consumption ... 
	        		if ( wagon.travel.setFoodConsumption(value, wagon.wagonPeople) != true) {
	        			// If Out of Bounds, prompt user
	        			JOptionPane.showMessageDialog(null, "Value entered is Out of Bounds.\n Should be an integer from 1-3." + "\nhuh...wuh?");
	        		} 
	        		// Success: Update Food Flavor Text
	        		else {
	        			lblFoodFlavorTxt.setText("Set to: " + wagon.travel.getFlavorTxtFood());
	        		}
        		} 
        		// Fail: Character entered
        		catch (Exception evt) {
        			JOptionPane.showMessageDialog(null, "Please enter an integer from 1-3" + "\nhuh...wuh?");
        		}
        	}
        });
        testField.setBounds(110, 316, 115, 20);
        frmPackYourWagon.getContentPane().add(testField);
        */
        
        
        // Debug: Testable Button
        /*JButton btnNewButton_1 = new JButton("MenuUI");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		MenuUI menu = new MenuUI(wagon.travel.getCurLocation());
        		menu.setVisible(true);
        	}
        });
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
        btnNewButton_1.setBounds(226, 313, 89, 23);
        frmPackYourWagon.getContentPane().add(btnNewButton_1);
        */
        
        // Travel Menu Button
        JButton locationButton = new JButton("MenuUI");
        locationButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		MenuUI menu = new MenuUI(wagon.travel.getCurLocation());
        		menu.setVisible(true);
        	}
        });
        locationButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
        locationButton.setBounds(226, 313, 89, 23);
        frmPackYourWagon.getContentPane().add(locationButton);
        
        //Breanna Sproul - Store button for easy testing
        JButton storeButton = new JButton("Store");
        storeButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Store store = new Store(wagon);
        		store.setVisible(true);
        	}
        });
        storeButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
        storeButton.setBounds(126, 313, 89, 23);
        frmPackYourWagon.getContentPane().add(storeButton);
        
        
        
        
        // Art Label & Border
        JPanel artPanel = new JPanel();
        artPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Chimmey Rock, John Estel CC NY-BY", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        artPanel.setBounds(545, 16, 270, 152);
        frmPackYourWagon.getContentPane().add(artPanel);
        artPanel.setLayout(null);
        
        JLabel artLabel = new JLabel();
        artLabel.setBounds(6, 16, 256, 128);
        artPanel.add(artLabel);
        artLabel.setIcon(new ImageIcon(WagonLoad.class.getResource("/images/ChimneyRock1.pixel.WebSafe.2.png")));
        
        
        
        /// Item Panel
        JPanel itemPanel = new JPanel();
        itemPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Items", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        itemPanel.setBounds(16, 16, 265, 260);
        frmPackYourWagon.getContentPane().add(itemPanel);
        itemPanel.setLayout(null);
        
        chckbxItem1 = new JCheckBox( wagon.itemList.get(26).name + ": " + wagon.itemList.get(26).getWeight() );
        chckbxItem1.setFont(new Font("Tahoma", Font.PLAIN, 10));
        chckbxItem1.setSelected(true); //preloaded tent and gear
        wagon.packWagonItem(26, chckbxItem1.isSelected());
        chckbxItem1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		wagon.packWagonItem(26, chckbxItem1.isSelected());
        		updateTotalWeight();
        	}
        });
        chckbxItem1.setBounds(6, 16, 110, 23);
        itemPanel.add(chckbxItem1);
        
        chckbxItem1_1 = new JCheckBox( wagon.itemList.get(13).name + ": " + wagon.itemList.get(13).getWeight() );
        chckbxItem1_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
        chckbxItem1_1.setSelected(true); //preloaded bedroll
        wagon.packWagonItem(13, chckbxItem1_1.isSelected());
        chckbxItem1_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		wagon.packWagonItem(13, chckbxItem1_1.isSelected());
        		updateTotalWeight();
        	}
        });
        chckbxItem1_1.setBounds(6, 42, 110, 23);
        itemPanel.add(chckbxItem1_1);
        
        //smithing tools
        chckbxItem1_1_1 = new JCheckBox( wagon.itemList.get(14).name + ": " + wagon.itemList.get(14).getWeight() );
        chckbxItem1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
        chckbxItem1_1_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		wagon.packWagonItem(14, chckbxItem1_1_1.isSelected());
        		updateTotalWeight();
        	}
        });
        chckbxItem1_1_1.setBounds(6, 94, 124, 23);
        itemPanel.add(chckbxItem1_1_1);
        
        //books
        chckbxItem1_2 = new JCheckBox( wagon.itemList.get(15).name + ": " + wagon.itemList.get(15).getWeight() );
        chckbxItem1_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
        chckbxItem1_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		wagon.packWagonItem(15, chckbxItem1_2.isSelected());
        		updateTotalWeight();
        	}
        });
        chckbxItem1_2.setBounds(6, 68, 110, 23);
        itemPanel.add(chckbxItem1_2);
        
        chckbxItem1_1_2 = new JCheckBox( wagon.itemList.get(27).name + ": " + wagon.itemList.get(27).getWeight() );
        chckbxItem1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
        chckbxItem1_1_2.setSelected(true); //preloaded tools
        wagon.packWagonItem(27, chckbxItem1_1_2.isSelected());
        chckbxItem1_1_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		wagon.packWagonItem(27, chckbxItem1_1_2.isSelected());
        		updateTotalWeight();
        	}
        });
        chckbxItem1_1_2.setBounds(6, 146, 110, 23);
        itemPanel.add(chckbxItem1_1_2);
        
        //medicine
        chckbxItem1_3 = new JCheckBox( wagon.itemList.get(16).name + ": " + wagon.itemList.get(16).getWeight() );
        chckbxItem1_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
        chckbxItem1_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		wagon.packWagonItem(16, chckbxItem1_3.isSelected());
        		updateTotalWeight();
        	}
        });
        chckbxItem1_3.setBounds(6, 120, 110, 23);
        itemPanel.add(chckbxItem1_3);
        
        //caststove
        chckbxItem1_1_3 = new JCheckBox( wagon.itemList.get(17).name + ": " + wagon.itemList.get(17).getWeight() );
        chckbxItem1_1_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
        chckbxItem1_1_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		wagon.packWagonItem(17, chckbxItem1_1_3.isSelected());
        		updateTotalWeight();
        	}
        });
        chckbxItem1_1_3.setBounds(6, 198, 110, 23);
        itemPanel.add(chckbxItem1_1_3);
        
        //chair
        chckbxItem1_4 = new JCheckBox( wagon.itemList.get(18).name + ": " + wagon.itemList.get(18).getWeight() );
        chckbxItem1_4.setFont(new Font("Tahoma", Font.PLAIN, 10));
        chckbxItem1_4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		wagon.packWagonItem(18, chckbxItem1_4.isSelected());
        		updateTotalWeight();
        	}
        });
        chckbxItem1_4.setBounds(6, 172, 110, 23);
        itemPanel.add(chckbxItem1_4);
        
        chckbxItem1_1_4 = new JCheckBox( wagon.itemList.get(19).name + ": " + wagon.itemList.get(19).getWeight() );
        chckbxItem1_1_4.setFont(new Font("Tahoma", Font.PLAIN, 10));
        chckbxItem1_1_4.setSelected(true); //preloaded cookware and utensils
        wagon.packWagonItem(19, chckbxItem1_1_4.isSelected());
        chckbxItem1_1_4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		wagon.packWagonItem(19, chckbxItem1_1_4.isSelected());
        		updateTotalWeight();
        	}
        });
        chckbxItem1_1_4.setBounds(125, 42, 135, 23);
        itemPanel.add(chckbxItem1_1_4);
        
        //granny clock
        chckbxItem1_5 = new JCheckBox( wagon.itemList.get(20).name + ": " + wagon.itemList.get(20).getWeight() );
        chckbxItem1_5.setFont(new Font("Tahoma", Font.PLAIN, 10));
        chckbxItem1_5.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		wagon.packWagonItem(20, chckbxItem1_5.isSelected());
        		updateTotalWeight();
        	}
        });
        chckbxItem1_5.setBounds(125, 16, 125, 23);
        itemPanel.add(chckbxItem1_5);
        
        //guntools
        chckbxItem1_1_5 = new JCheckBox( wagon.itemList.get(21).name + ": " + wagon.itemList.get(21).getWeight() );
        chckbxItem1_1_5.setFont(new Font("Tahoma", Font.PLAIN, 10));
        chckbxItem1_1_5.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		wagon.packWagonItem(21, chckbxItem1_1_5.isSelected());
        		updateTotalWeight();
        	}
        });
        chckbxItem1_1_5.setBounds(126, 94, 125, 23);
        itemPanel.add(chckbxItem1_1_5);
        
        //keepsakes
        chckbxItem1_6 = new JCheckBox( wagon.itemList.get(22).name + ": " + wagon.itemList.get(22).getWeight() );
        chckbxItem1_6.setFont(new Font("Tahoma", Font.PLAIN, 10));
        chckbxItem1_6.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		wagon.packWagonItem(22, chckbxItem1_6.isSelected());
        		updateTotalWeight();
        	}
        });
        chckbxItem1_6.setBounds(125, 68, 111, 23);
        itemPanel.add(chckbxItem1_6);
        
        chckbxItem1_1_6 = new JCheckBox( wagon.itemList.get(23).name + ": " + wagon.itemList.get(23).getWeight() );
        chckbxItem1_1_6.setFont(new Font("Tahoma", Font.PLAIN, 10));
        chckbxItem1_1_6.setSelected(true); //preloaded leadshot
        wagon.packWagonItem(23, chckbxItem1_1_6.isSelected());
        chckbxItem1_1_6.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		wagon.packWagonItem(23, chckbxItem1_1_6.isSelected());
        		updateTotalWeight();
        	}
        });
        chckbxItem1_1_6.setBounds(125, 146, 111, 23);
        itemPanel.add(chckbxItem1_1_6);
        
        //mirror
        chckbxItem1_7 = new JCheckBox( wagon.itemList.get(24).name + ": " + wagon.itemList.get(24).getWeight() );
        chckbxItem1_7.setFont(new Font("Tahoma", Font.PLAIN, 10));
        chckbxItem1_7.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		wagon.packWagonItem(24, chckbxItem1_7.isSelected());
        		updateTotalWeight();
        	}
        });
        chckbxItem1_7.setBounds(125, 120, 111, 23);
        itemPanel.add(chckbxItem1_7);
        
        chckbxItem1_1_7 = new JCheckBox( wagon.itemList.get(25).name + ": " + wagon.itemList.get(25).getWeight() );
        chckbxItem1_1_7.setFont(new Font("Tahoma", Font.PLAIN, 10));
        chckbxItem1_1_7.setSelected(true); //preloaded gunpowder
        wagon.packWagonItem(25, chckbxItem1_1_7.isSelected());
        chckbxItem1_1_7.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		wagon.packWagonItem(25, chckbxItem1_1_7.isSelected());
        		updateTotalWeight();
        	}
        });
        chckbxItem1_1_7.setBounds(125, 198, 111, 23);
        itemPanel.add(chckbxItem1_1_7);
        
        //tent and gear 2??
        chckbxItem1_8 = new JCheckBox( wagon.itemList.get(26).name + ": " + wagon.itemList.get(26).getWeight() );
        chckbxItem1_8.setFont(new Font("Tahoma", Font.PLAIN, 10));
        chckbxItem1_8.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		wagon.packWagonItem(26, chckbxItem1_8.isSelected());
        		updateTotalWeight();
        	}
        });
        chckbxItem1_8.setBounds(125, 172, 125, 23);
        itemPanel.add(chckbxItem1_8);
        
        //toys
        chckbxItem1_1_3_1 = new JCheckBox( wagon.itemList.get(28).name + ": " + wagon.itemList.get(28).getWeight() );
        chckbxItem1_1_3_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		wagon.packWagonItem(28, chckbxItem1_1_3_1.isSelected());
        		updateTotalWeight();
        	}
        });
        chckbxItem1_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
        chckbxItem1_1_3_1.setBounds(6, 224, 110, 23);
        itemPanel.add(chckbxItem1_1_3_1);
        
        
        
        /// Food Panel
        JPanel foodPanel = new JPanel();
        foodPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Food", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        foodPanel.setBounds(300, 16, 240, 227);
        frmPackYourWagon.getContentPane().add(foodPanel);
        foodPanel.setLayout(null);
        
        JCheckBox chckbxItem1_1_8 = new JCheckBox( wagon.itemList.get(0).name + ": " + wagon.itemList.get(0).getWeight() );
        chckbxItem1_1_8.setFont(new Font("Tahoma", Font.PLAIN, 10));
        chckbxItem1_1_8.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		wagon.packWagonItem(0, chckbxItem1_1_8.isSelected());
        		updateTotalWeight();
        	}
        });
        chckbxItem1_1_8.setBounds(125, 46, 110, 23);
        foodPanel.add(chckbxItem1_1_8);
        
        JCheckBox chckbxItem1_9 = new JCheckBox( wagon.itemList.get(1).name + ": " + wagon.itemList.get(1).getWeight() );
        chckbxItem1_9.setFont(new Font("Tahoma", Font.PLAIN, 10));
        chckbxItem1_9.setSelected(true); //preloaded bacon
        wagon.packWagonItem(1, chckbxItem1_9.isSelected());
        chckbxItem1_9.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		wagon.packWagonItem(1, chckbxItem1_9.isSelected());
        		updateTotalWeight();
        	}
        });
        chckbxItem1_9.setBounds(125, 20, 99, 23);
        foodPanel.add(chckbxItem1_9);
        
        JCheckBox chckbxItem1_1_9 = new JCheckBox( wagon.itemList.get(2).name + ": " + wagon.itemList.get(2).getWeight() );
        chckbxItem1_1_9.setFont(new Font("Tahoma", Font.PLAIN, 10));
        chckbxItem1_1_9.setSelected(true); //preloaded beans
        wagon.packWagonItem(2, chckbxItem1_1_9.isSelected());
        chckbxItem1_1_9.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		wagon.packWagonItem(2, chckbxItem1_1_9.isSelected());
        		updateTotalWeight();
        	}
        });
        chckbxItem1_1_9.setBounds(125, 98, 99, 23);
        foodPanel.add(chckbxItem1_1_9);
        
        //coffee
        JCheckBox chckbxItem1_10 = new JCheckBox( wagon.itemList.get(3).name + ": " + wagon.itemList.get(3).getWeight() );
        chckbxItem1_10.setFont(new Font("Tahoma", Font.PLAIN, 10));
        chckbxItem1_10.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		wagon.packWagonItem(3, chckbxItem1_10.isSelected());
        		updateTotalWeight();
        	}
        });
        chckbxItem1_10.setBounds(125, 72, 99, 23);
        foodPanel.add(chckbxItem1_10);
        
        JCheckBox chckbxItem1_1_10 = new JCheckBox( wagon.itemList.get(4).name + ": " + wagon.itemList.get(4).getWeight() );
        chckbxItem1_1_10.setFont(new Font("Tahoma", Font.PLAIN, 10));
        chckbxItem1_1_10.setSelected(true); //preloaded dry apple
        wagon.packWagonItem(4, chckbxItem1_1_10.isSelected());
        chckbxItem1_1_10.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		wagon.packWagonItem(4, chckbxItem1_1_10.isSelected());
        		updateTotalWeight();
        	}
        });
        chckbxItem1_1_10.setBounds(125, 150, 110, 23);
        foodPanel.add(chckbxItem1_1_10);
        
        //flour
        JCheckBox chckbxItem1_11 = new JCheckBox( wagon.itemList.get(5).name + ": " + wagon.itemList.get(5).getWeight() );
        chckbxItem1_11.setFont(new Font("Tahoma", Font.PLAIN, 10));
        chckbxItem1_11.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		wagon.packWagonItem(5, chckbxItem1_11.isSelected());
        		updateTotalWeight();
        	}
        });
        chckbxItem1_11.setBounds(125, 124, 99, 23);
        foodPanel.add(chckbxItem1_11);
        
        JCheckBox chckbxItem1_1_11 = new JCheckBox( wagon.itemList.get(6).name + ": " + wagon.itemList.get(6).getWeight() );
        chckbxItem1_1_11.setFont(new Font("Tahoma", Font.PLAIN, 10));
        chckbxItem1_1_11.setSelected(true); //preloaded hardtack
        wagon.packWagonItem(6, chckbxItem1_1_11.isSelected());
        chckbxItem1_1_11.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		wagon.packWagonItem(6, chckbxItem1_1_11.isSelected());
        		updateTotalWeight();
        	}
        });
        chckbxItem1_1_11.setBounds(6, 46, 99, 23);
        foodPanel.add(chckbxItem1_1_11);
        
        JCheckBox chckbxItem1_12 = new JCheckBox( wagon.itemList.get(7).name + ": " + wagon.itemList.get(7).getWeight() );
        chckbxItem1_12.setFont(new Font("Tahoma", Font.PLAIN, 10));
        chckbxItem1_12.setSelected(true); //preloaded lard
        wagon.packWagonItem(7, chckbxItem1_12.isSelected());
        chckbxItem1_12.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		wagon.packWagonItem(7, chckbxItem1_12.isSelected());
        		updateTotalWeight();
        	}
        });
        chckbxItem1_12.setBounds(6, 20, 99, 23);
        foodPanel.add(chckbxItem1_12);
        
        //salt
        JCheckBox chckbxItem1_10_1 = new JCheckBox( wagon.itemList.get(8).name + ": " + wagon.itemList.get(8).getWeight() );
        chckbxItem1_10_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
        chckbxItem1_10_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		wagon.packWagonItem(8, chckbxItem1_10_1.isSelected());
        		updateTotalWeight();
        	}
        });
        chckbxItem1_10_1.setBounds(6, 68, 99, 23);
        foodPanel.add(chckbxItem1_10_1);
        
        //sugar
        JCheckBox chckbxItem1_1_9_1 = new JCheckBox( wagon.itemList.get(9).name + ": " + wagon.itemList.get(9).getWeight() );
        chckbxItem1_1_9_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
        chckbxItem1_1_9_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		wagon.packWagonItem(9, chckbxItem1_1_9_1.isSelected());
        		updateTotalWeight();
        	}
        });
        chckbxItem1_1_9_1.setBounds(6, 94, 99, 23);
        foodPanel.add(chckbxItem1_1_9_1);
        
        JCheckBox chckbxItem1_11_1 = new JCheckBox( wagon.itemList.get(10).name + ": " + wagon.itemList.get(10).getWeight() );
        chckbxItem1_11_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
        chckbxItem1_11_1.setSelected(true); //preloaded rice
        wagon.packWagonItem(10, chckbxItem1_11_1.isSelected());
        wagon.packWagonItem(10, chckbxItem1_11_1.isSelected());
        chckbxItem1_11_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		wagon.packWagonItem(10, chckbxItem1_11_1.isSelected());
        		updateTotalWeight();
        	}
        });
        chckbxItem1_11_1.setBounds(6, 120, 99, 23);
        foodPanel.add(chckbxItem1_11_1);
        
        JCheckBox chckbxItem1_1_10_1 = new JCheckBox( wagon.itemList.get(11).name + ": " + wagon.itemList.get(11).getWeight() );
        chckbxItem1_1_10_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
        chckbxItem1_1_10_1.setSelected(true); //preloaded water
        wagon.packWagonItem(11, chckbxItem1_1_10_1.isSelected());
        chckbxItem1_1_10_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		wagon.packWagonItem(11, chckbxItem1_1_10_1.isSelected());
        		updateTotalWeight();
        	}
        });
        chckbxItem1_1_10_1.setBounds(6, 146, 99, 23);
        foodPanel.add(chckbxItem1_1_10_1);
        
        JCheckBox chckbxItem1_1_10_1_1 = new JCheckBox( wagon.itemList.get(12).name + ": " + wagon.itemList.get(12).getWeight() );
        chckbxItem1_1_10_1_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
        chckbxItem1_1_10_1_1.setSelected(true); //preloaded whiskey
        wagon.packWagonItem(12, chckbxItem1_1_10_1_1.isSelected());
        chckbxItem1_1_10_1_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		wagon.packWagonItem(12, chckbxItem1_1_10_1_1.isSelected());
        		updateTotalWeight();
        	}
        });
        chckbxItem1_1_10_1_1.setBounds(6, 172, 99, 23);
        foodPanel.add(chckbxItem1_1_10_1_1);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Travel Options", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel_1.setBounds(550, 170, 255, 100);
        frmPackYourWagon.getContentPane().add(panel_1);
        panel_1.setLayout(null);
        

        JLabel lblFoodFlavorTxt = new JLabel("Set to: Filling");
        lblFoodFlavorTxt.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblFoodFlavorTxt.setBounds(10, 72, 115, 14);
        panel_1.add(lblFoodFlavorTxt);
        
        JLabel lblTravelFlavorText = new JLabel("Set to: 20 miles/day");
        lblTravelFlavorText.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblTravelFlavorText.setBounds(135, 72, 110, 14);
        panel_1.add(lblTravelFlavorText);
        
        
        // Food Consumption Text Field
        textField = new JTextField("1");
        textField.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Retrieve Text
        		String textValue = textField.getText();
        		
        		// Try storing text as a value
        		try {
	        		int value = Integer.valueOf(textValue);
	        		
	        		// Try to set Food Consumption ... 
	        		if ( wagon.travel.setFoodConsumption(value, wagon.wagonPeople) != true) {
	        			// If Out of Bounds, prompt user
	        			JOptionPane.showMessageDialog(null, "Value entered is Out of Bounds.\n Should be an integer from 1-3." + "\nhuh...wuh?");
	        		} 
	        		// Success: Update Food Flavor Text
	        		else {
	        			lblFoodFlavorTxt.setText("Set to: " + wagon.travel.getFlavorTxtFood());
	        		}
        		} 
        		// Fail: Character entered
        		catch (Exception evt) {
        			JOptionPane.showMessageDialog(null, "Please enter an integer from 1-3" + "\nhuh...wuh?");
        		}
        	}
        });
        textField.setBounds(10, 41, 115, 20);
        panel_1.add(textField);
        textField.setColumns(10);
        
        // Food Consumption Label
        JLabel lblNewLabel = new JLabel("Food Consumption: 1-3");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblNewLabel.setBounds(10, 16, 115, 14);
        panel_1.add(lblNewLabel);
        
        
        
        // Travel Speed Label
        JLabel lblTravelSpeed = new JLabel("Travel Speed: 12-20");
        lblTravelSpeed.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblTravelSpeed.setBounds(135, 16, 100, 14);
        panel_1.add(lblTravelSpeed);
        
        // Travel Speed Text Field
        textField_1 = new JTextField("12");
        textField_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Retrieve Text 
        		String textValue = textField_1.getText();
        		
        		
        		// Try storing text as a value
        		try {
        			int value = Integer.parseInt(textValue);
        			
	        		// Try to setTravelSpeed...
	        		if ( wagon.travel.setTravelSpeed(value) != true) {
	        			// If Out of Bounds, prompt user
	        			JOptionPane.showMessageDialog(null, "Value entered is Out of Bounds.\n Should be an integer from 12-20." + "\nhuh...wuh?");
	        		} 
	        		// Success: Update Travel Speed Flavor Text
	        		else {
	        			lblTravelFlavorText.setText("Set to: " + wagon.travel.travelSpeed + " miles/day");
	        		}
        		} 
        		// Fail: Character entered
        		catch (Exception evt) {
        			JOptionPane.showMessageDialog(null, "Please enter an integer from 12-20" + "\nhuh...wuh?");
        		}
        	}
        });
        textField_1.setBounds(135, 41, 110, 20);
        panel_1.add(textField_1);
        textField_1.setColumns(10);
        
        

        
        /// Travel Results Panel
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Travel Results", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel.setBounds(500, 270, 305, 69);
        frmPackYourWagon.getContentPane().add(panel);
        panel.setLayout(null);

        // Enough Food Labels
        JLabel lblEnoughFood = new JLabel("Enough Food?");
        lblEnoughFood.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblEnoughFood.setHorizontalAlignment(SwingConstants.CENTER);
        lblEnoughFood.setBounds(108, 21, 96, 14);
        panel.add(lblEnoughFood);
        
        JLabel lblClicktravelTo = new JLabel("Click \"Travel\"!");
        lblClicktravelTo.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblClicktravelTo.setHorizontalAlignment(SwingConstants.CENTER);
        lblClicktravelTo.setBounds(105, 35, 111, 23);
        panel.add(lblClicktravelTo);
        
        // Total Weight Labels
        JLabel lblTotalWeight = new JLabel("Total Weight:");
        lblTotalWeight.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblTotalWeight.setBounds(222, 21, 75, 14);
        panel.add(lblTotalWeight);
        
        lblTotalWeight_1 = new JLabel("New label");
        lblTotalWeight_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblTotalWeight_1.setBounds(222, 39, 75, 14);
        panel.add(lblTotalWeight_1);
        
        JLabel distanceLbl = new JLabel("");
        distanceLbl.setBounds(407, 279, 60, 23);
        frmPackYourWagon.getContentPane().add(distanceLbl);
   
        // Travel Button
        JButton btnNewButton = new JButton("\"Travel\"");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Calculate foodWeight
        		int foodWeight = wagon.getFoodWeight();
        		int totalWeight = wagon.getTotalWeight();
        		
        		lblTotalWeight_1.setText(totalWeight + " lbs");
        		
        		if (wagon.travel.isEnoughFoodToTravel(foodWeight))
        		{
        			days++;
        			int distance = wagon.travel.getCurLocation().getDistance();
        			distanceLbl.setText(distance + "");
        			if (wagon.travel.travelMap(foodWeight))
        			{
        				MenuUI menu = new MenuUI(wagon.travel.getCurLocation());
                		menu.setVisible(true);
        			}
        			
        			
        			
        		}
        		
        		
        		//Breanna Sproul
        		//FOR CONSUMPTION
        		//wagon.travel.isEnoughFoodToTravel(foodWeight);
        		String textValue = textField.getText();
        		int value = Integer.valueOf(textValue);
        		wagon.travel.setFoodConsumption(value, wagon.wagonPeople);
        		System.out.println("Check for correct subtraction: " + wagon.travel.dailyConsume(foodWeight));
        		
        		//maybe way for when days are available?
        		//for(int i = 0; i < days; i++){
        		//	wagon.travel.setFoodConsumption(value, wagon.wagonPeople);
        		//}
        		//end of consumption

        		
        		System.out.println(foodWeight);
        		
        		
        		// Check if wagon is overweight...
        		if (totalWeight <= wagon.maxWeight) {
        		
	        		// Check if there isEnoughFoodToTravel
	        		if ( wagon.travel.isEnoughFoodToTravel(foodWeight) == true) {
	        			// Prompt Success
	        			lblClicktravelTo.setText("Yes! You'll make it!");
	        		}
	        		// Fail: Didn't make it
	        		else {
	        			// Prompt Try Again
	        			lblClicktravelTo.setText("Nope! Try Again!");
	        		}
        		}
        		
        		// Fail: Wagon is overweight
        		else {
        			JOptionPane.showMessageDialog(null, "Wagon is overweight!");
        		}
        		
        		
        	}
        });
        btnNewButton.setBounds(10, 35, 89, 23);
        panel.add(btnNewButton);
        
        JLabel lblWeightWarning = new JLabel("Max Weight is 2400 lbs!");
        lblWeightWarning.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblWeightWarning.setBounds(336, 250, 125, 14);
        frmPackYourWagon.getContentPane().add(lblWeightWarning);
    
        
        JLabel distanceTestLabel = new JLabel("Distance Until Next Location:");
        distanceTestLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
        distanceTestLabel.setBounds(248, 288, 149, 14);
        frmPackYourWagon.getContentPane().add(distanceTestLabel);
        
        updateTotalWeight();
        
        frmPackYourWagon.setVisible(true); // Exhibit the frame
	}	
}

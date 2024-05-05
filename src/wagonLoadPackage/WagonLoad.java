package wagonLoadPackage;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import wagonLoadPackage.Location;
import wagonLoadPackage.Menus.EndMenu;
import wagonLoadPackage.Menus.MenuUI;
import wagonLoadPackage.Scavenging;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.net.URL;
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
 * Allows user to manage food consumption and
 * travel speed. Shows max weight and if there is enough
 * food to make the trip, along with some favor text
 * descriptors for food consumption and travel speed.
 */
public class WagonLoad {

    // Grabs sound from the sound class so I can use it in other classes and Jframes

    Sound sound = new Sound();

    public JFrame frmPackYourWagon;
    private JCheckBox chckbxItem1;
    private JTextField textFieldFoodConsump;
    private JTextField textFieldTravelSpeed;

    // Initialize Wagon
    private Wagon wagon;
    private RandomEvent events;

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
    private JLabel WagonHealthLabel;
    // private JTextField testField;

    Weather weather = new Weather();
    boolean hasScavenged = false;
    Scavenging scavenge = new Scavenging();
    ArrayList<Location> map;
    int totalDist;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Wagon emptyWagon = new Wagon();
                    WagonLoad window = new WagonLoad(emptyWagon);
                  
                    window.frmPackYourWagon.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    

    // breanna sproul
    // testing for health
    

    /**
     * Create the application with the user's pre-configured wagon.
     * 
     * @param wagon -
     */
    public WagonLoad(Wagon wagon) {

        URL soundURL = getClass().getResource("/The Oregon Trail - Main Theme.wav");

        playMusic(soundURL);

        // If pre-emptively launched (for testing), create a pre-loaded wagon
        if (wagon == null) {

            this.wagon = new Wagon();
        }
        // Otherwise, pass and store user's wagon
        else {
            this.wagon = wagon;
        }

        // Initialize frame and its parameters
        map = wagon.travel.getMap();
        totalDist = wagon.travel.getTotalDistance();
        events = new RandomEvent(wagon);

        initialize();

        // Debug: Readout itemList
        /*
         * int i = 0;
         * for ( Item item : wagon.itemList ) {
         * System.out.println("itemList [" + i + "]: ");
         * System.out.println(item.name);
         * i++;
         * }
         */
    }

    // breanna sproul
    // testing for health
    

    public void testUpdateWagonHP() {
        // get value
        int totalHP = wagon.HPList.get(0).getHealth();
       // System.out.println("Wagon Health: "+ wagon.HPList.get(0).getHealth());
        
        //update label
        WagonHealthLabel.setText("wagon: " + totalHP);
    }

    /**
     * Updates the UI element for total weight.
     * Calculates the totalWeight, then updates UI label's text.
     */
    public void updateTotalWeightUI() {
        // Calculate totalWeight
        int totalWeight = wagon.getTotalWeight();

        // Update lbl text
        lblTotalWeight_1.setText(totalWeight + " lbs");
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
        /*
         * testField = new JTextField();
         * testField.setColumns(10);
         * testField.addActionListener(new ActionListener() {
         * public void actionPerformed(ActionEvent e) {
         * // Retrieve Text
         * String textValue = textField.getText();
         * 
         * // Try storing text as a value
         * try {
         * int value = Integer.valueOf(textValue);
         * 
         * // Try to set Food Consumption ...
         * if ( wagon.travel.setFoodConsumption(value, wagon.wagonPeople) != true) {
         * // If Out of Bounds, prompt user
         * JOptionPane.showMessageDialog(null,
         * "Value entered is Out of Bounds.\n Should be an integer from 1-3." +
         * "\nhuh...wuh?");
         * }
         * // Success: Update Food Flavor Text
         * else {
         * lblFoodFlavorTxt.setText("Set to: " + wagon.travel.getFlavorTxtFood());
         * }
         * }
         * // Fail: Character entered
         * catch (Exception evt) {
         * JOptionPane.showMessageDialog(null, "Please enter an integer from 1-3" +
         * "\nhuh...wuh?");
         * }
         * }
         * });
         * testField.setBounds(110, 316, 115, 20);
         * frmPackYourWagon.getContentPane().add(testField);
         */

        // Debug: Testable Button
        /*
         * JButton btnNewButton_1 = new JButton("MenuUI");
         * btnNewButton_1.addActionListener(new ActionListener() {
         * public void actionPerformed(ActionEvent e) {
         * MenuUI menu = new MenuUI(wagon.travel.getCurLocation());
         * menu.setVisible(true);
         * }
         * });
         * btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
         * btnNewButton_1.setBounds(226, 313, 89, 23);
         * frmPackYourWagon.getContentPane().add(btnNewButton_1);
         */

        /*
         * // Debug: Location Menu Button - David Flores
         * JButton locationButton = new JButton("Location Menu");
         * locationButton.addActionListener(new ActionListener() {
         * public void actionPerformed(ActionEvent e) {
         * MenuUI menu = new MenuUI(wagon.travel.getCurLocation(), wagon);
         * menu.setVisible(true);
         * }
         * });
         * locationButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
         * locationButton.setBounds(226, 313, 89, 23);
         * frmPackYourWagon.getContentPane().add(locationButton);
         * 
         * // Breanna Sproul - Store button for easy testing, can be removed later
         * JButton storeButton = new JButton("Store");
         * storeButton.addActionListener(new ActionListener() {
         * public void actionPerformed(ActionEvent e) {
         * Store store = new Store(wagon);
         * store.setVisible(true);
         * }
         * });
         * storeButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
         * storeButton.setBounds(126, 313, 89, 23);
         * frmPackYourWagon.getContentPane().add(storeButton);
         */

        // Art Label & Border - David Flores
        JPanel artPanel = new JPanel();
        artPanel.setBorder(new TitledBorder(
                new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
                "Chimmey Rock, John Estel CC NY-BY", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        artPanel.setBounds(10, 11, 270, 152);
        frmPackYourWagon.getContentPane().add(artPanel);
        artPanel.setLayout(null);

        JLabel artLabel = new JLabel();
        artLabel.setBounds(6, 16, 256, 128);
        artPanel.add(artLabel);
        artLabel.setIcon(new ImageIcon(WagonLoad.class.getResource("/images/ChimneyRock1.pixel.WebSafe.2.png")));

        /// Travel Options Panel
        JPanel panelTravel = new JPanel();
        panelTravel.setBorder(new TitledBorder(
                new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
                "Travel", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panelTravel.setBounds(550, 11, 255, 325);
        frmPackYourWagon.getContentPane().add(panelTravel);
        panelTravel.setLayout(null);

        JLabel lblFoodFlavorTxt = new JLabel("Set to: Filling");
        lblFoodFlavorTxt.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblFoodFlavorTxt.setBounds(10, 72, 115, 14);
        panelTravel.add(lblFoodFlavorTxt);

        JLabel lblTravelFlavorText = new JLabel("Set to: 20 miles/day");
        lblTravelFlavorText.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblTravelFlavorText.setBounds(135, 72, 110, 14);
        panelTravel.add(lblTravelFlavorText);

        JLabel dateLabel = new JLabel("");
        dateLabel.setBackground(new Color(0, 0, 0));
        dateLabel.setBounds(26, 278, 189, 23);
        frmPackYourWagon.getContentPane().add(dateLabel);

        // Food Consumption Text Field - David Flores
        textFieldFoodConsump = new JTextField("1");
        textFieldFoodConsump.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Retrieve Text
                String textValue = textFieldFoodConsump.getText();

                // Try storing text as a value
                try {
                    int value = Integer.valueOf(textValue);

                    // Try to set Food Consumption ...
                    if (wagon.travel.setFoodConsumption(value, wagon.wagonPeople) != true) {
                        // If Out of Bounds, prompt user
                        JOptionPane.showMessageDialog(null,
                                "Value entered is Out of Bounds.\n Should be an integer from 1-3." + "\nhuh...wuh?");
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
        textFieldFoodConsump.setBounds(10, 41, 115, 20);
        panelTravel.add(textFieldFoodConsump);
        textFieldFoodConsump.setColumns(10);

        // Food Consumption Label
        JLabel lblNewLabel = new JLabel("Food Consumption: 1-3");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblNewLabel.setBounds(10, 16, 115, 14);
        panelTravel.add(lblNewLabel);

        // Travel Speed Label
        JLabel lblTravelSpeed = new JLabel("Travel Speed: 12-20");
        lblTravelSpeed.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblTravelSpeed.setBounds(135, 16, 100, 14);
        panelTravel.add(lblTravelSpeed);

        // Travel Speed Text Field - David Flores
        textFieldTravelSpeed = new JTextField("20");
        textFieldTravelSpeed.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Retrieve Text
                String textValue = textFieldTravelSpeed.getText();

                // Try storing text as a value
                try {
                    int value = Integer.parseInt(textValue);

                    // Try to setTravelSpeed...
                    if (wagon.travel.setTravelSpeed(value) != true) {
                        // If Out of Bounds, prompt user
                        JOptionPane.showMessageDialog(null,
                                "Value entered is Out of Bounds.\n Should be an integer from 12-20." + "\nhuh...wuh?");
                    }
                    // Success: Update Travel Speed Flavor Text
                    else {
                        lblTravelFlavorText.setText("Set to: " + wagon.travel.getTravelSpeed() + " miles/day");
                    }
                }
                // Fail: Character entered
                catch (Exception evt) {
                    JOptionPane.showMessageDialog(null, "Please enter an integer from 12-20" + "\nhuh...wuh?");
                }
            }
        });
        textFieldTravelSpeed.setBounds(135, 41, 110, 20);
        panelTravel.add(textFieldTravelSpeed);
        textFieldTravelSpeed.setColumns(10);

        // Travel Button - David Flores and Breanna Sproul
        JButton btnTravel = new JButton("\"Travel\"");
        btnTravel.setBounds(86, 131, 89, 23);
        panelTravel.add(btnTravel);
        btnTravel.setFont(new Font("Tahoma", Font.PLAIN, 10));

        // Total Weight Labels
        JLabel lblTotalWeight = new JLabel("Total Weight:");
        lblTotalWeight.setBounds(100, 257, 75, 14);
        panelTravel.add(lblTotalWeight);
        lblTotalWeight.setFont(new Font("Tahoma", Font.PLAIN, 10));

        lblTotalWeight_1 = new JLabel("____");
        lblTotalWeight_1.setBounds(100, 275, 75, 14);
        panelTravel.add(lblTotalWeight_1);
        lblTotalWeight_1.setFont(new Font("Tahoma", Font.PLAIN, 10));

        // health testing label - delete later
        WagonHealthLabel = new JLabel("wagon: " + wagon.HPList.get(0).getHealth());
        WagonHealthLabel.setBounds(50, 220, 90, 14);
        panelTravel.add(WagonHealthLabel);
        WagonHealthLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));

        JLabel lblWeightWarning = new JLabel("Max Weight is 2400 lbs!");
        lblWeightWarning.setBounds(72, 300, 125, 14);
        panelTravel.add(lblWeightWarning);
        lblWeightWarning.setFont(new Font("Tahoma", Font.PLAIN, 10));

        JLabel distanceTestLabel = new JLabel("Distance Until Next Location:");
        distanceTestLabel.setBounds(10, 192, 149, 14);
        panelTravel.add(distanceTestLabel);
        distanceTestLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));

        JLabel distanceLbl = new JLabel("");
        distanceLbl.setBounds(175, 192, 60, 14);
        panelTravel.add(distanceLbl);

        // Cody Dusek
        // adds a scavenging button that can add food to the wagon
        JButton scavengeButton = new JButton("Scavenge");
        scavengeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (hasScavenged == false) {
                    int foodgained = scavenge.scavengeFood();
                    hasScavenged = true;
                    String scavengePrompt = scavenge.getFoodPrompt();
                    wagon.addToItem("Food", foodgained);
                    JOptionPane.showMessageDialog(null, scavengePrompt);
                    updateTotalWeightUI();
                } else {
                    JOptionPane.showMessageDialog(null, "You already scavenged everything here!");
                }

            }
        });
        scavengeButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
        scavengeButton.setBounds(86, 165, 89, 23);
        panelTravel.add(scavengeButton);
        
        JLabel tempLabel = new JLabel("");
        tempLabel.setBackground(Color.BLACK);
        tempLabel.setBounds(26, 309, 189, 23);
        frmPackYourWagon.getContentPane().add(tempLabel);

        btnTravel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Checks if the current location is a river, and doesn't allow the player to
                // continue until they cross it
                if (wagon.travel.getCurLocation().getIsRiver()) {
                    if (MenuUI.hasCrossed == false) {
                        JOptionPane.showMessageDialog(null, "You must cross the river first!");
                        return;
                    }
                }
                hasScavenged = false;
                weather.getNewWeather();
                String tempText = "Temperature: " + weather.getTempResults();
                tempLabel.setText(tempText);
                // Calculate foodWeight
                int foodWeight = wagon.getFoodWeight();
                int totalWeight = wagon.getTotalWeight();
                

                // If enough food, ...
                if (wagon.travel.isEnoughFoodToTravelOneDay(foodWeight)) {

                    // Check if wagon is overweight...
                    if (totalWeight <= wagon.maxWeight) {

                        // Keep track of date
                        wagon.date.increaseDays(); // increases days
                        dateLabel.setText(wagon.date.getDate());

                        // Check for random event
                        // Events.forceEvent(); //used during testing ONLY
                        // Events.eventCheck(); //correct event activation
                        testUpdateWagonHP();
                        System.out.println("Wagon HP: " + wagon.HPList.get(0).getHealth());

                        // Travel towards next location on map...
                        // If wagon makes it to next location, then pop-up location menu
                        if (wagon.travel.travelMap(foodWeight)) {
                            MenuUI menu = new MenuUI(wagon.travel.getCurLocation(), wagon);
                            menu.setVisible(true);
                        }

                        // Update Distance till location
                        int distance = wagon.travel.getCurLocation().getDistance();
                        distanceLbl.setText(distance + "");

                        // Consume Food
                        int foodConsumed = wagon.travel.getFoodConsumed();
                        foodWeight = wagon.getFoodWeight() - foodConsumed;
                    	System.out.println("Food Consumed: "+ foodConsumed);
                    	System.out.println("Food left: "+ foodWeight);
                        
						// Consume Food
						wagon.setFoodWeight(foodWeight);
						
						// If less than 5 days of food, warn player
						if (wagon.travel.isEnoughFoodToTravelFiveDays(foodWeight) != true) {
                    		JOptionPane.showMessageDialog(null, "Wagon has less than 5 days of food!\nGet some quickly!!!");
                    	}
						
                        // Check for random event
						events.doesEventHappen();
                        //events.forceEvent(); //used during testing ONLY
                       //testUpdateWagonHP();
                       // System.out.println("Wagon Health out: "+ wagon.HPList.get(0).getHealth());
						
						
						// Recalculate Total Weight and Display it
                        totalWeight = wagon.getTotalWeight();
                        lblTotalWeight_1.setText(totalWeight + " lbs");

                    }

                    // Fail: Wagon is overweight
                    else {
                        JOptionPane.showMessageDialog(null, "Wagon is overweight!");
                    }
                }
                // Fail: Ran out of food!
                else {
                    EndMenu endMenu = new EndMenu();
                    JFrame end = endMenu.getEndMenu();
                    end.setVisible(true);
                    frmPackYourWagon.dispose();
                    /**
                     * end.setBounds(100, 100, 460, 240);
                     * end.repaint();
                     * end.setLocationRelativeTo(null);
                     */

                    // TODO: Add game over loop
                }
            }
        });

        JButton settingsBtn = new JButton("Settings");
        settingsBtn.setFont(new Font("Tahoma", Font.PLAIN, 10));
        settingsBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		//Rod Piton passed through sound and WagonLoad JFrame to settings menu so it can have access and change them
        		
        		SettingsMenu settings = new SettingsMenu(sound, frmPackYourWagon);
        		settings.setVisible(true);
        	}
        });
        settingsBtn.setBounds(333, 313, 89, 23);
        frmPackYourWagon.getContentPane().add(settingsBtn);
        
        // Pre-Update UI Elements
        updateTotalWeightUI();

        frmPackYourWagon.setVisible(true); // Exhibit the frame
    }

    /**
     * Sets the frame to visible.
     * - David Flores
     * 
     * @param visible - Boolean that when true
     *                allows the frame to be visible.
     */
    public void setVisible(boolean visible) {
        frmPackYourWagon.setVisible(visible);
    }

    // Sets the music file plays it and loops it

    public void playMusic(URL url) {

        sound.setFile(url);
        sound.play(url);
        sound.loop(url);

    }
}

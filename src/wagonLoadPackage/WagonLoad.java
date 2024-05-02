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
import wagonLoadPackage.Menus.MenuUI;

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
 * Allows user to manage food consumption and
 * travel speed. Shows max weight and if there is enough
 * food to make the trip, along with some favor text
 * descriptors for food consumption and travel speed.
 */
public class WagonLoad {

    public JFrame frmPackYourWagon;
    private JCheckBox chckbxItem1;
    private JTextField textFieldFoodConsump;
    private JTextField textFieldTravelSpeed;

    RandomEvent Events = new RandomEvent();

    // Initialize Wagon
    private Wagon wagon;

    // Initialize Wagon
    private Wagon wagon = new Wagon();
    private RandomEvent RanEvents = new RandomEvent();

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
    // private JTextField testField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Wagon emptyWagon = null;
                    WagonLoad window = new WagonLoad(emptyWagon);
                    window.frmPackYourWagon.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application with the user's pre-configured wagon.
     * 
     * @param wagon -
     */
    public WagonLoad(Wagon wagon) {
        // If pre-emptively launched (for testing), create a pre-loaded wagon
        if (wagon == null) {

            this.wagon = new Wagon();
        }
        // Otherwise, pass and store user's wagon
        else {
            this.wagon = wagon;
        }

        // Initialize frame and its parameters
        initialize();
        int totalDist = wagon.travel.getTotalDistance();

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
        textFieldTravelSpeed = new JTextField("12");
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
        JButton btnNewButton = new JButton("\"Travel\"");
        btnNewButton.setBounds(86, 131, 89, 23);
        panelTravel.add(btnNewButton);
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 10));

        // Total Weight Labels
        JLabel lblTotalWeight = new JLabel("Total Weight:");
        lblTotalWeight.setBounds(100, 257, 75, 14);
        panelTravel.add(lblTotalWeight);
        lblTotalWeight.setFont(new Font("Tahoma", Font.PLAIN, 10));

        lblTotalWeight_1 = new JLabel("____");
        lblTotalWeight_1.setBounds(100, 275, 75, 14);
        panelTravel.add(lblTotalWeight_1);
        lblTotalWeight_1.setFont(new Font("Tahoma", Font.PLAIN, 10));

        JLabel lblWeightWarning = new JLabel("Max Weight is 2400 lbs!");
        lblWeightWarning.setBounds(72, 300, 125, 14);
        panelTravel.add(lblWeightWarning);
        lblWeightWarning.setFont(new Font("Tahoma", Font.PLAIN, 10));

        JLabel distanceTestLabel = new JLabel("Distance Until Next Location:");
        distanceTestLabel.setBounds(10, 181, 149, 14);
        panelTravel.add(distanceTestLabel);
        distanceTestLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));

        JLabel distanceLbl = new JLabel("");
        distanceLbl.setBounds(169, 172, 60, 23);
        panelTravel.add(distanceLbl);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Calculate foodWeight
                int foodWeight = wagon.getFoodWeight();
                int totalWeight = wagon.getTotalWeight();

                // If enough food, ...
                if (wagon.travel.isEnoughFoodToTravel(foodWeight)) {

                    // Check if wagon is overweight...
                    if (totalWeight <= wagon.maxWeight) {

                        // Update food consumption Modifier and Calculate food to consume
                        // Breanna Sproul and David Flores
                        String textFoodConsump = textFieldFoodConsump.getText();
                        int foodConsumptionMod = Integer.valueOf(textFoodConsump);
                        wagon.travel.setFoodConsumption(foodConsumptionMod, wagon.wagonPeople);
                        int newFoodWeight = wagon.travel.dailyConsume(foodWeight);

                        // Debug: Food Calculations
                        System.out.println("Check for correct subtraction: " + newFoodWeight);
                        System.out.println(foodWeight);

                        // Consume Food
                        wagon.setFoodWeight(newFoodWeight);

                        // Keep track of date
                        wagon.date.increaseDays(); // increases days
                        dateLabel.setText(wagon.date.getDate());

                        // Update Distance till location
                        int distance = wagon.travel.getCurLocation().getDistance();
                        distanceLbl.setText(distance + "");

                        // Check for random event
                        Events.eventCheck(distance);

                        // Recalculate Weight and Display
                        totalWeight = wagon.getTotalWeight();
                        lblTotalWeight_1.setText(totalWeight + " lbs");

                        // Travel towards next location on map...
                        // If wagon makes it to next location, then pop-up location menu
                        if (wagon.travel.travelMap(foodWeight)) {
                            MenuUI menu = new MenuUI(wagon.travel.getCurLocation(), wagon);
                            menu.setVisible(true);
                        }

                    }

                    // Fail: Wagon is overweight
                    else {
                        JOptionPane.showMessageDialog(null, "Wagon is overweight!");
                    }
                }
            }
        });

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
}

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
    private JTextField textField;
    private JTextField textField_1;

    RandomEvent Events = new RandomEvent();

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
        artPanel.setBounds(545, 16, 270, 152);
        frmPackYourWagon.getContentPane().add(artPanel);
        artPanel.setLayout(null);

        JLabel artLabel = new JLabel();
        artLabel.setBounds(6, 16, 256, 128);
        artPanel.add(artLabel);
        artLabel.setIcon(new ImageIcon(WagonLoad.class.getResource("/images/ChimneyRock1.pixel.WebSafe.2.png")));

        /// Travel Options Panel
        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(
                new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
                "Travel Options", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
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

        // Food Consumption Text Field - David Flores
        textField = new JTextField("1");
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Retrieve Text
                String textValue = textField.getText();

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

        // Travel Speed Text Field - David Flores
        textField_1 = new JTextField("12");
        textField_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Retrieve Text
                String textValue = textField_1.getText();

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
        textField_1.setBounds(135, 41, 110, 20);
        panel_1.add(textField_1);
        textField_1.setColumns(10);

        /// Travel Results Panel
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(
                new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
                "Travel Results", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
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

        lblTotalWeight_1 = new JLabel("____");
        lblTotalWeight_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblTotalWeight_1.setBounds(222, 39, 75, 14);
        panel.add(lblTotalWeight_1);

        JLabel distanceLbl = new JLabel("");
        distanceLbl.setBounds(407, 279, 60, 23);
        frmPackYourWagon.getContentPane().add(distanceLbl);

        JLabel dateLabel = new JLabel("");
        dateLabel.setBounds(26, 278, 189, 23);
        frmPackYourWagon.getContentPane().add(dateLabel);

        // Travel Button - David Flores and Breanna Sproul
        JButton btnNewButton = new JButton("\"Travel\"");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Calculate foodWeight
                int foodWeight = wagon.getFoodWeight();
                int totalWeight = wagon.getTotalWeight();

                lblTotalWeight_1.setText(totalWeight + " lbs");

                // If enough food, ...
                // TODO: Move to after isEnoughFoodToTravel check
                if (wagon.travel.isEnoughFoodToTravel(foodWeight)) {
                    // Keep track of date
                    wagon.date.increaseDays(); // increases days
                    dateLabel.setText(wagon.date.getDate());

                    // Update Distance till location
                    int distance = wagon.travel.getCurLocation().getDistance();
                    distanceLbl.setText(distance + "");
                    // random event
                    Events.eventCheck(distance);

                    // Travel to next location on map
                    // If wagon makes it to next location, pop-up location menu
                    if (wagon.travel.travelMap(foodWeight)) {
                        MenuUI menu = new MenuUI(wagon.travel.getCurLocation(), wagon);
                        menu.setVisible(true);
                    }
                }

                // Breanna Sproul
                // FOR CONSUMPTION
                // TODO: Move to after isEnoughFoodToTravel check
                String textValue = textField.getText();
                int value = Integer.valueOf(textValue);
                wagon.travel.setFoodConsumption(value, wagon.wagonPeople);
                System.out.println("Check for correct subtraction: " + wagon.travel.dailyConsume(foodWeight));

                // maybe way for when days are available?
                // for(int i = 0; i < days; i++){
                // wagon.travel.setFoodConsumption(value, wagon.wagonPeople);
                // }
                // end of consumption

                System.out.println(foodWeight);

                // Check if wagon is overweight...
                if (totalWeight <= wagon.maxWeight) {

                    // Check if there isEnoughFoodToTravel
                    if (wagon.travel.isEnoughFoodToTravel(foodWeight) == true) {
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

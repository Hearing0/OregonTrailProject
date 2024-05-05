package wagonLoadPackage.Menus;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import wagonLoadPackage.Location;
import wagonLoadPackage.River;
import wagonLoadPackage.Store;
import wagonLoadPackage.Trader;
import wagonLoadPackage.Travel;
import wagonLoadPackage.Wagon;
import wagonLoadPackage.WagonLoad;

import javax.swing.JTextArea;

/**
 * MenuUI.java
 * Created on 4/8/2024
 * By David Flores
 * 
 * Creates a new UI Frame for a location/rest.
 * Allows user to read a description of
 * the current location, talk to passerbys,
 * and view the map.
 */
public class MenuUI {

	// Initialize variables
	private JFrame frmLocationName;
	private JTextField promptField;
	private Location location;
	private JTabbedPane mapPane;
	private Wagon wagon;
	private Travel travelspeed;

	// Trader Variables
	private Trader trader;
	private ArrayList<String> offer;

	/**
	 * Create the application.
	 */
	public MenuUI(Location location, Wagon wagon) {
		this.location = location;
		this.wagon = wagon;
		this.travelspeed = travelspeed;
		this.trader = new Trader(0, wagon);
		initialize();
	}

	/**
	 * Creates a JPanel for chatting with random passerbys.
	 * The panel is populated with a text box and button.
	 * The "Chat with Passerbys" Button selects from a pool of text options,
	 * then populates the text box with the text selected.
	 * - David Flores
	 * 
	 * @return - A populated JPanel with text box and button.
	 */
	public JPanel talkPanel() {
		JPanel localsTalkTextPanel = new JPanel(new BorderLayout());
		JTextArea localsTalkTextArea = new JTextArea("...");
		localsTalkTextArea.setLineWrap(true);
		localsTalkTextArea.setWrapStyleWord(true);
		JButton talkButton = new JButton("Chat with Passerbys");
		talkButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Select a random chat
				String[] chats = { "You chat with a local about the trail"
						+ "...\n\"Sometimes it feels like every step forward is "
						+ "met with two steps back, but we press on, "
						+ "determined to reach our destination.\"",
						"You chat with a local about family"
								+ "...\n\"It's a constant struggle balancing the needs of my family "
								+ "with the challenges of the unforgiving trail, but its worth it.\"",
						"You chat with a local about past hardships"
								+ "...\n\"From sickness to dwindling supplies... it can miserable at times."
								+ "A thief snuck into our wagon and accidently lit a fire. Most of our "
								+ "spare wagon parts were burned and we lost seven oxen in the panic."
								+ "To think we even got this far after all that!\"",
						"You try to yield a small group of travelers, but they ignore you and pass by. "
								+ "They seem to be in a exhausted daze from their travels. "
								+ "Perhaps its best to not bother them.",
						"You look around and off into the distance, but no one is nearby to talk to." };
				Random random = new Random();
				int choice = random.nextInt(chats.length);
				localsTalkTextArea.setText(chats[choice]);
			}
		});
		localsTalkTextPanel.add(localsTalkTextArea, BorderLayout.CENTER);
		localsTalkTextPanel.add(talkButton, BorderLayout.SOUTH);

		return localsTalkTextPanel;
	}

	/**
	 * Creates a JPanel for store
	 * 
	 * @return - A populated JPanel with store button. //picture will be added later
	 */
	public JPanel storePanel() {
		JPanel storeTextPanel = new JPanel(new BorderLayout());
		JButton storeButton = new JButton("Enter the store");
		storeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Store store = new Store(wagon);
				store.setVisible(true);
			}
		});
		storeTextPanel.add(storeButton, BorderLayout.SOUTH);

		return storeTextPanel;
	}

	/**
	 * Updates a text area to display the current trade offer.
	 * Additionally, can display another line of text below the trade offer.
	 * - David Flores
	 * 
	 * @param area        - The JTextArea that is updated with text.
	 * @param displayText - Additional line displayed below the trade offer.
	 */
	private void updateTradingTextArea(JTextArea area, String displayText) {
		area.setText(
				"Offering	: " + offer.get(0) + " for " + offer.get(1) + " lbs" +
						"\nWants	: " + offer.get(2) + " for " + offer.get(3) + " lbs" +
						"\nTrades Remaining	: " + trader.getTradesRemaining() +
						"\nOffers Remaining	: " + trader.getOffersRemaining() +
						"\nTraders Remaining: " + location.getTradersRemaining() +
						"\n" + displayText);
	}

	/**
	 * Creates and populates the Trading Panel
	 * with its various functionality.
	 * - David Flores
	 * 
	 * @return - Returns the Trading Panel
	 */
	public JPanel tradingPanel() {

		JPanel tradingPanel = new JPanel(new BorderLayout());
		JTextArea tradingTextArea = new JTextArea("...");
		tradingTextArea.setLineWrap(true);
		tradingTextArea.setWrapStyleWord(true);

		// Display current trade offer
		offer = trader.getTradeOffer();
		this.updateTradingTextArea(tradingTextArea, "");

		// Ask to Trade Button - David Flores
		JButton askTradeButton = new JButton("Ask around to Trade");
		askTradeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (location.hasTraders()) {
					// Decrease num of local traders
					location.decrementTradersRemaining();

					// Create random Trader (random, native, trapper, or traveler)
					// TODO: Configure different trader types
					trader = new Trader(0, wagon);

					// Display current trade offer
					offer = trader.getTradeOffer();
					updateTradingTextArea(tradingTextArea, "");
				}

				// Fail: No more traders in local
				else {
					// Display No more Traders Prompt
					tradingTextArea.setText("No one else want to trade or has left the area!");

					// Prevent player from trading
					trader.clearTrade();
				}
			}
		});

		// Confirm Trade Button - David Flores
		JButton tradeButton = new JButton("Confirm Trade");
		tradeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Try to Trade
				if (trader.conductTrade()) {
					// Display Success Prompt
					offer = trader.getTradeOffer();
					updateTradingTextArea(tradingTextArea, "\"Thanks for the trade!\"");
				}

				// Fail: Player does not have Resources
				// TODO: Add check to see if user has resources to trade
				/*
				 * else if () {
				 * // Display Not able to trade Prompt
				 * offer = trader.getTradeOffer();
				 * updateTradingTextArea(tradingTextArea,
				 * "\"It doesn't look like you have enough to trade\"");
				 * }
				 */

				// Fail: No more trades left
				else {
					// Display No more Trades Prompt
					tradingTextArea.setText("Hey thanks for the offer, but I think I've had enough for now.");

					// Clear Trade
					trader.clearTrade();
				}
			}
		});

		// New Offer Button
		JButton newOfferButton = new JButton("Ask for another Offer");
		newOfferButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Try to get another Trade Offer
				if (trader.generateTradeOffer()) {
					// Display Success Prompt
					offer = trader.getTradeOffer();
					updateTradingTextArea(tradingTextArea, "\n\n\"Hmmm... here's what I'll offer.\"");
				}

				// Fail: No more offers left
				else {
					// Display No more Trades Prompt
					tradingTextArea.setText("\"Thanks, but I'm good. I'll trade with someone else.\"");

					// Clear Trade
					trader.clearTrade();
				}
			}
		});

		// Add elements into Panel
		JPanel buttonPanel = new JPanel(); // Create a panel to hold the buttons
		buttonPanel.setLayout(new GridLayout(3, 1));
		buttonPanel.add(askTradeButton);
		buttonPanel.add(newOfferButton);
		buttonPanel.add(tradeButton);

		tradingPanel.add(tradingTextArea, BorderLayout.CENTER);
		tradingPanel.add(buttonPanel, BorderLayout.SOUTH);

		return tradingPanel;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLocationName = new JFrame();
		frmLocationName.setTitle(location.getName());
		frmLocationName.setBounds(100, 100, 450, 300);
		frmLocationName.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmLocationName.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 418, 212);
		frmLocationName.getContentPane().add(tabbedPane);

		// Description tab - David Flores
        JPanel descriptionPanel = new JPanel(new BorderLayout());
        JTextArea descriptionTextArea = new JTextArea(location.getDesc());
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setWrapStyleWord(true);
        descriptionTextArea.setEditable(false);
        descriptionPanel.add(new JScrollPane(descriptionTextArea), BorderLayout.CENTER);
        tabbedPane.addTab("Description", descriptionPanel);
		

     
        /**
    	 * Rod Piton - Initializes the contents of the Wagon tab and Travel tab.
    	 * Wagon tab will show Current party weight location and Food weight
    	 * Travel will show Location Items and Travel speed
    	 */
        // Wagon tab
        // TODO: show individual item loaded and their weight
        JPanel WagonPanel = new JPanel(new BorderLayout());
        JTextArea WagonTextArea = new JTextArea(
        		
        		"Current Distance: " + location.getDistance()+"\n"+
        		"Current Location: " + location.getName() + "\n" +
        		"Current wagon weight: "	+ wagon.getTotalWeight() + "\n" +
        		"Current food weight: " + wagon.getFoodWeight()
        		
        		);
        WagonTextArea.setLineWrap(true);
        WagonTextArea.setWrapStyleWord(true);
        WagonTextArea.setEditable(false);
        WagonPanel.add(new JScrollPane(WagonTextArea), BorderLayout.CENTER);
        tabbedPane.addTab("Wagon", WagonPanel);
        
        // Travel tab to be implemented
        
        // JPanel TravelPanel = new JPanel(new BorderLayout());
        //JTextArea TravelTextArea = new JTextArea(
        		
        //	"Current Travel Speed: " + travelspeed.getTravelSpeed()+"\n"+
        //	"Current Items: " + wagon + "\n" 
        		
        		
        //	);
        //  TravelTextArea.setLineWrap(true);
	    //  TravelTextArea.setWrapStyleWord(true);
        //  TravelTextArea.setEditable(false);
        // TravelPanel.add(new JScrollPane(TravelTextArea), BorderLayout.CENTER);
        // tabbedPane.addTab("Wagon", TravelPanel);
       
        
        // If current location has activites, create activities
        // - David Flores
        if(this.location.getHasActivites()) {
        	// Debug
        	System.out.println("\nLocation has Activities");
        	
	        // Talk to Locals Tab
	        JPanel talkToLocalsPanel = this.talkPanel();
	        tabbedPane.addTab("Chat", talkToLocalsPanel);
	        
	        // Trading Tab
	        JPanel tradingPanel = this.tradingPanel();
	        tabbedPane.addTab("Trade", tradingPanel);
	        
	        //Store Tab
	        tabbedPane.addTab("Store", storePanel());
	        
        }
		
        // Map Tab - David Flores
        // TODO: Fix png not showing on load
        JPanel mapPanel = new JPanel(new BorderLayout());
        JLabel artLabel = new JLabel();
        URL imageUrl = WagonLoad.class.getResource("/images/OreganTrailMap.png");
        if (imageUrl != null) {
            artLabel.setIcon(new ImageIcon(imageUrl));
            System.out.println("imageURL: " + imageUrl);
        } else {
            System.out.println("Image not found");
        }
        mapPanel.add(artLabel);
        mapPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.addTab("View Map", mapPane);
		
		
        
       // Cody Dusek
		// Panel for River Options tab
		JPanel riverPanel = new JPanel(new BorderLayout());
		tabbedPane.addTab("River Options", riverPanel);
		JTextArea riverText = new JTextArea("...");
		riverText.setLineWrap(true);
		riverText.setWrapStyleWord(true);
		riverText.setEditable(false);
		riverText.setText(location.getName() + "\n" + location.getDesc());

		riverPanel.add(riverText);

		// gets the current location
		Location current = wagon.travel.getCurLocation();

		// hides the Rivers Option tab if the current location is not a river
		if (current.getIsRiver() == false) {
			riverPanel.setVisible(false);
			tabbedPane.removeTabAt(5); // TODO: Find a better way to do this
		}

		JButton btnNewButton = new JButton("See options");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// converts the current location into a river
				River newRiver = current.toRiver();
				newRiver.setConditions(current); // sets the conditions based on each individual river

				JFrame riverOptionsFrame = new JFrame();
				riverOptionsFrame.setBounds(100, 200, 400, 300);
				riverOptionsFrame.setVisible(true);
				riverOptionsFrame.setTitle(location.getName());

				JPanel riverOptionsPanel = new JPanel();

				JTextArea riverOptionsText = new JTextArea();
				riverOptionsText.setLineWrap(true);
				riverOptionsText.setWrapStyleWord(true);
				riverOptionsText.setEditable(false);

				String swiftness = String.format("%.2f", newRiver.getSwiftness()); // converts the swiftness value to a
																					// string
				String depth = String.format("%.2f", newRiver.getDepth()); // converts the depth value to a string
				String width = String.format("%.2f", newRiver.getWidth()); // converts the width value to a string
				String bottomType = newRiver.getBottomType(); // gets the bottom type

				riverOptionsText.setText("Swiftness: " + swiftness + "\n" + "Depth: " + depth +
						"\n" + "Width: " + width + "\n" + "Bottom type: " + bottomType); // displays the swiftness,
																							// depth, width, and bottom
																							// type
				riverOptionsPanel.add(riverOptionsText);

				// creates the four buttons for the options to cross the river
				JButton fordButton = new JButton("Ford");
				JButton floatButton = new JButton("Float");
				JButton ferryButton = new JButton("Ferry");
				JButton guideButton = new JButton("Hire a Guide?");
				riverOptionsPanel.add(fordButton);
				riverOptionsPanel.add(floatButton);
				riverOptionsPanel.add(ferryButton);
				riverOptionsPanel.add(guideButton);

				JFrame riverResultsFrame = new JFrame();
				riverResultsFrame.setVisible(false);
				riverResultsFrame.setBounds(100, 200, 400, 300);

				JPanel riverResultsPanel = new JPanel();

				JTextArea riverResultsText = new JTextArea();
				riverResultsText.setLineWrap(true);
				riverResultsText.setWrapStyleWord(true);
				riverResultsText.setEditable(false);

				riverResultsPanel.add(riverResultsText);
				riverResultsFrame.add(riverResultsPanel);

				guideButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						newRiver.crossWithGuide(true);
					}
				});

				// gets the results from fording the river, and displays the corresponding
				// results
				fordButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						int[] conditions = newRiver.fordRiver();
						String results = newRiver.getPrompt(0);
						String supplyResults = "", daysLost = "";
						if (conditions[2] != 0) {
							supplyResults = "You lost " + conditions[2] + "% of your supplies!"; // displays the amount
																									// of supplies lost
																									// based on the
																									// conditions
						}
						if (conditions[3] != 0) {
							daysLost = "You lost " + conditions[3] + " days!"; // displays the amount of days lost
						}
						riverResultsText.setText(results + "\n" + supplyResults + "\n" + daysLost);
						riverResultsFrame.setVisible(true);
					}
				});

				// gets the results from floating across the river, and displays the
				// corresponding results
				floatButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						int[] conditions = newRiver.floatRiver();
						String results = newRiver.getPrompt(1);
						String supplyResults = "", daysLost = "";
						if (conditions[1] != 0) {
							supplyResults = "You lost " + conditions[1] + "% of your supplies";
						}
						if (conditions[3] != 0) {
							daysLost = "You lost " + conditions[3] + " days!";
						}
						riverResultsText.setText(results + "\n" + supplyResults + "\n" + daysLost);
						riverResultsFrame.setVisible(true);
					}
				});

				// gets the results from ferrying across the river, and displays the
				// corresponding results
				ferryButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						int[] conditions = newRiver.ferryRiver();
						String results = newRiver.getPrompt(2);
						String supplyResults = "", daysLost = "";
						if (conditions[1] != 0) {
							supplyResults = "You lost " + conditions[1] + "% of your supplies";
						}
						if (conditions[3] != 0) {
							daysLost = "You lost " + conditions[3] + " days!";
						}
						riverResultsText.setText(results + "\n" + supplyResults + "\n" + daysLost);
						riverResultsFrame.setVisible(true);

					}
				});

				riverOptionsFrame.add(riverOptionsPanel);
			}
		});
		riverPanel.add(btnNewButton, BorderLayout.SOUTH);
	}

	/**
	 * Sets the frame to visible.
	 * 
	 * @param visible - Boolean that when true
	 *                allows the frame to be visible.
	 */
	public void setVisible(boolean visible) {
		frmLocationName.setVisible(visible);
	}
}

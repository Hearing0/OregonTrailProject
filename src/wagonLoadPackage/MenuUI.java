package wagonLoadPackage;

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
                String[] chats = 
                	{"You chat with a local about the trail"
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
                	"You look around and off into the distance, but no one is nearby to talk to."};
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
	 * Updates a text area to display the current trade offer.
	 * Additionally, can display another line of text below the trade offer. 
	 * @param area - The JTextArea that is updated with text.
	 * @param displayText - Additional line displayed below the trade offer.
	 */
	private void updateTradingTextArea(JTextArea area, String displayText) {
		area.setText(
				"Offering	: " + offer.get(0) + " for " + offer.get(1) + " lbs" +
				"\nWants	: "	+ offer.get(2) + " for " + offer.get(3) + " lbs" +
				"\nTrades Remaining	: " + trader.tradesRemaining +
				"\nOffers Remaining	: " + trader.offersRemaining + 
				"\nTraders Remaining: " + location.getTradersRemaining() + 
				"\n" + displayText
			);
	}
	
	
	/**
	 * 
	 * @return
	 */
	public JPanel tradingPanel() {	
		
		JPanel tradingPanel = new JPanel(new BorderLayout());
		JTextArea tradingTextArea = new JTextArea("...");
		tradingTextArea.setLineWrap(true);
		tradingTextArea.setWrapStyleWord(true);
		
		// Display current trade offer 
		offer = trader.getTradeOffer();
		this.updateTradingTextArea(tradingTextArea, "");
		
		// Ask to Trade Button
    	JButton askTradeButton = new JButton("Ask around to Trade");
        askTradeButton.addActionListener(new ActionListener() {

        	
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( location.hasTraders() ) {
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
        
        // Confirm Trade Button
        JButton tradeButton = new JButton("Confirm Trade");
        tradeButton.addActionListener(new ActionListener() {
        	
            @Override
            public void actionPerformed(ActionEvent e) {
                // Try to Trade
            	if ( trader.conductTrade()) {
            		// Display Success Prompt
            		offer = trader.getTradeOffer();
            		updateTradingTextArea(tradingTextArea, "\"Thanks for the trade!\"");
				}
            	
            	// Fail: Player does not have Resources
            	// TODO: Add check to see if user has resources to trade
            	/*
            	else if () {
            		// Display Not able to trade Prompt
            		offer = trader.getTradeOffer();
					updateTradingTextArea(tradingTextArea, "\"It doesn't look like you have enough to trade\"");
            	}
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
            	if ( trader.generateTradeOffer() ) {
            		// Display Success Prompt
            		offer = trader.getTradeOffer();
            		updateTradingTextArea(tradingTextArea, "\n\n\"Hmmm... here's what I'll offer.\"" );
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
    	 * Rod Piton Initializes the contents of the Wagon tab and Travel tab.
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
    //    TravelPanel.add(new JScrollPane(TravelTextArea), BorderLayout.CENTER);
        // tabbedPane.addTab("Wagon", TravelPanel);
       
        
        // If current location has activites, create activities
        if(this.location.getHasActivites()) {
        	// Debug
        	System.out.println("Location has Activities");
        	
	        // Talk to Locals Tab
	        JPanel talkToLocalsPanel = this.talkPanel();
	        tabbedPane.addTab("Chat", talkToLocalsPanel);
	        
	        // Trading Tab
	        JPanel tradingPanel = this.tradingPanel();
	        tabbedPane.addTab("Trade", tradingPanel);
	        
        }
		
        // Map Tab
        // TODO: Fix png not showing on load
        // png is being sourced from bin (but is located in src??? check if is issue)
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
		
		
		// Prompt Field
		promptField = new JTextField(location.getPrompt());
		promptField.setBounds(213, 234, 215, 20);
		frmLocationName.getContentPane().add(promptField);
		promptField.setColumns(10);
		
		// Prompt Label
		JLabel promptLabel = new JLabel(location.getPrompt());
		promptLabel.setBounds(10, 234, 193, 14);
		promptLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		frmLocationName.getContentPane().add(promptLabel);
		
		 JPanel riverPanel = new JPanel(new BorderLayout());
	        tabbedPane.addTab("River Options", riverPanel);
	        JTextArea riverText = new JTextArea("...");
	        riverText.setLineWrap(true);
	        riverText.setWrapStyleWord(true);
	        riverText.setEditable(false);
	        riverText.setText(location.getName() + "\n" + location.getDesc());
	        
	        riverPanel.add(riverText);
	        
	        if (Wagon.travel.getCurLocation().getIsRiver() == false)
	        {
	        	riverPanel.setVisible(false);
	        	tabbedPane.removeTabAt(5); // TODO: Find a better way to do this
	        }
	        
	        JButton btnNewButton = new JButton("See options");
	        btnNewButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		
	        		Location current = Wagon.travel.getCurLocation();
	        		River newRiver = current.toRiver();
	        		newRiver.setConditions(current);
	        		
	        		JFrame riverOptionsFrame = new JFrame();
	        		riverOptionsFrame.setBounds(100, 200, 400, 300);
	        		riverOptionsFrame.setVisible(true);
	        		riverOptionsFrame.setTitle(location.getName());
	        		
	        		JPanel riverOptionsPanel = new JPanel();
	        		
	        		JTextArea riverOptionsText = new JTextArea();
	        		riverOptionsText.setLineWrap(true);
	                riverOptionsText.setWrapStyleWord(true);
	                riverOptionsText.setEditable(false);
	               
	                String swiftness = String.format("%.2f", newRiver.getSwiftness());
	                String depth = String.format("%.2f", newRiver.getDepth());
	                String width = String.format("%.2f", newRiver.getWidth());
	                String bottomType = newRiver.getBottomType();
	                
	        		riverOptionsText.setText("Swiftness: " + swiftness + "\n" + "Depth: " + depth + 
	        				"\n" + "Width: " + width + "\n" + "Bottom type: " + bottomType);
	         		riverOptionsPanel.add(riverOptionsText);
	         		
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
	        	
	        		fordButton.addActionListener(new ActionListener(){
	        			public void actionPerformed(ActionEvent e) {
	        				int[] conditions = newRiver.fordRiver();
	        				String results = newRiver.getPrompt(0);
	        				String supplyResults = "", daysLost = "";
	        				if (conditions[2] != 0)
	        				{
	        					supplyResults = "You lost " + conditions[2] + "% of your supplies!";
	        				}
	        				if (conditions[3] != 0)
	        				{
	        					daysLost = "You lost " + conditions[3] + " days!";
	        				}
	        				riverResultsText.setText( results + "\n" + supplyResults + "\n" + daysLost);
	        				riverResultsFrame.setVisible(true);
	        			}
	        		});
	        		
	        		floatButton.addActionListener(new ActionListener() {
	        			public void actionPerformed(ActionEvent e) {
	        				int[] conditions = newRiver.floatRiver();
	        				String results = newRiver.getPrompt(1);
	        				String supplyResults = "", daysLost = "";
	        				if (conditions [1] != 0)
	        				{
	        					supplyResults = "You lost " + conditions[1] + "% of your supplies";
	        				}
	        				if (conditions[3] != 0)
	        				{
	        					daysLost = "You lost " + conditions[3] + " days!";
	        				}
	        				riverResultsText.setText(results + "\n" + supplyResults + "\n" + daysLost);
	        				riverResultsFrame.setVisible(true);
	        			}
	        		});
	        		
	        		ferryButton.addActionListener(new ActionListener() {
	        			public void actionPerformed(ActionEvent e) {
	        				int[] conditions = newRiver.ferryRiver();
	        				String results = newRiver.getPrompt(2);
	        				String supplyResults = "", daysLost = "";
	        				if (conditions [1] != 0)
	        				{
	        					supplyResults = "You lost " + conditions[1] + "% of your supplies";
	        				}
	        				if (conditions[3] != 0)
	        				{
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
	 * @param visible - Boolean that when true 
	 * allows the frame to be visible.
	 */
	public void setVisible(boolean visible) {
        frmLocationName.setVisible(visible);
    }
}

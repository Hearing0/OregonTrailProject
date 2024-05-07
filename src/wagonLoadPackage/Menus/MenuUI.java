package wagonLoadPackage.Menus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import wagonLoadPackage.Item;
import wagonLoadPackage.Location;
import wagonLoadPackage.River;
import wagonLoadPackage.Store;
import wagonLoadPackage.Trader;
import wagonLoadPackage.Travel;
import wagonLoadPackage.Wagon;
import wagonLoadPackage.WagonLoad;
import wagonLoadPackage.Date;

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
	private ArrayList<JLabel> inventory;
	
	public static boolean hasCrossed = false;

	/**
	 * Create the application.
	 */
	public MenuUI(Location location, Wagon wagon) {
		this.location = location;
		this.wagon = wagon;
		this.travelspeed = travelspeed;
		this.trader = new Trader(0, wagon);
		this.inventory = new ArrayList<JLabel>();
		initialize();
	}
	
	public JLabel ItemLabel(Item item) {
		
		// Identify item and if its pre-loaded
		String text = item.getName() + ": " + item.getWeight();
		
		// Create and log JCheckBox
		JLabel box = new JLabel(text);
		inventory.add(box);
		
		return box;
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

	    JScrollPane localsTalkScrollPane = new JScrollPane (localsTalkTextArea);
	    localsTalkScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	          localsTalkScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		
		JButton talkButton = new JButton("Chat with Passerbys");
		talkButton.addActionListener(new ActionListener() {
			
			private String[] diaristConvos= {
					"“It’s nice to meet you, my name’s Mary Louisa Black. We just joined the wagon train not long ago at Kirkville, Missouri…What’s that, I look unwell? Oh…that’s because of a recent death in the family. We barely started and my daughter passed due to what must have been spoiled food from one of the train’s cooks. I tried to help her with my medicine, but she was still taken from me…I am sorry, I have to help out my family. I hope to see you on the trail once again, stranger.”\n",
					"[You spot Mary Black in a dazed state] “Oh…hi stranger. Sorry I can’t stop thinking of… What’s that, where in Oregon am I headed to? To Jacksonville. My aunt, Mary Hannah, recently sent a letter with mention of land available next to their own and that they would help us get acquainted with the land. They say that they have made a living spinning clay into pots and other cookware for the other families nearby. [You hear Mary’s name shouted out] Ah, sorry stranger, it seems my family is crossing now. I hope to see you again, stranger.”\n",
					"“Hi…What’s that?... Have I heard any tips for the Oregon Trail? Not quite sure I can think of any… Oh, make sure you take medicine along with you on your travels. Before I left Kirkville, Missouri, my family doctor was kind enough to provide a list of drugs and instructions on when and how to use them to treat all kinds of sicknesses. I am sure it will be of great use, if you have some paper, I’ll copy it down for you...”\n",
					"“Hi again. It’s been over a month since my family and I first started for Oregon. I hope we can arrive before winter, otherwise it will be awfully dreadful. I hear that the snow the blizzards bring can lock your wagon in place and make your party’s pace terribly slow, then there’s the chilly weather. I think I can spare some clothes of my late daughter if you like…It's… too painful to keep around and I have to save face for the children so I would appreciate you taking them off my hands.”",
					"[You spot Mary Louisana Black… seems she was expecting you] “Finally a familiar face. Welcome to Chimney Rock, stranger. You made it at one of the best seasonal times of year. Make sure to enjoy the scenery around but, be careful of the bears unfortunately you aren’t the only ones trying to take advantage of this weather. Be sure to track how many days you stay and the changes in weather. My family went through very strong winds and heavy rain during our first two days staying here. The weather changes very unexpectedly at Chimney Rock. [Mary points to her left] This is the direction you’ll want to travel in once you’re done. Be safe on your travels west.”\n",
					"“Water…water…” [Mary Looks dehydrated, so you offer her some extra water you had]. “Thank you stranger” [You watch as Mary and her family drink]. “This area is dry land and our water was ransacked by some nefarious animals. Try not to stay on this route too long there isn’t that much to do anyways. The nights here are extremely cold, make sure to put on a few layers.”",
					"“[You arrive at night and see Mary] Let me tell you a tale about South pass, a few months prior close to where we are right now a big family decided to settle and end their trail here. There was a lake nearby so water wasn’t an issue and they gathered livestock  and started a fruitful farm. The father one day decided to venture on his horse to explore the land he had brought himself two gallons of water on the trip. It had started to get dark but as soon as he turned around he had seen lights and people in the distance. They danced and sang in great celebration but when the father rode closer they vanished. Unamused by the situation, he assumed he was tired and decided to go back so he could sleep. When he arrived back to his town, he had seen the lights of the cabins still on but no people. Confused, he picked up pace and arrived at an empty town. All the family and livestock had left but all of the items had remained. The father packed up and picked up his Trail map to look for his family. He still travels to this day.”",
					"“Hi, it’s a wonderful morning outside is it not, [Mary walks you through plain grass and sits by trees and lake]. This adventure has been a struggle, but beautiful areas like this really distract you from what you’ve endured. [A family of deer walk past you as they eat and drink from the lake and run back into the forest]. We are getting closer and closer to the end of the Trail. Parts of me are excited, but the rest will miss it.”",
					"“It’s nice to finally have good water and grass after these past rough roads, don’t you agree? My group and their horses can finally drink without worrying too much about sickness. We're the fourth wagon in line for crossing, so we’ll be camping here for tonight. I don’t mind the wait, it gives me more time to recover from lingering flux. Don’t worry I am feeling much better. Our old family doctor gave us instructions on treating flux, and it works wonders. You might want to join my husband down the river if you have time. He has had great success fishing in the river. Or if you don’t mind I could use help cooking these peaches, and perhaps I’ll have time to fry some pies before dinner. I think it’ll be a great surprise for my family after some of the grueling experiences of this trip.”\n",
					"“Hello, how are you this morning? I’ve started on coffee, and my husband is out hunting for rabbits after fishing yielded no results. I’m not a fan of the chill in the air, but this beautiful spring does give us time to prepare for the last stretch. The hot water is great for refilling our water supply and washing my children’s clothes. I'd recommend you rest a day or two before heading off again.”",
					"“It’s been dusty and barren these past couple of miles, and I was worried one of our horses wouldn’t make it. It didn’t help that some people complaining in our wagon train caused us to derail for two days. I’d recommended keeping an eye on your map, especially in these harsher environments that drain your supplies. I wouldn’t want to get in an emergency with how the stores keep raising their prices. Of course you might be able to find someone willing to trade. I got a good deal trading a tin of peaches for beans the other day.”\r\n",
					"“This river has given my group a lot of trouble. One of our wagons sunk after the cattle tried climbing on, at least no one drowned. These roads won’t help our dwindling cattle situation either. Some people in my group are saying the steep hills and rocky roads might take out even more cattle and horses. It might be better to wait a day to see if the river gets calmer than to risk any more oxen. There’s not a lot of wood around here, but I saw a few sage brushes around here. The sage brush works just as well as wood chips, and if you want to help me collect some, I'll show you how to make a decent fire out of them.”\r\n",
					"“Are you here to trade too? These rocky roads have been rough on our cattle. Old Sorrel was too weak to travel the mountains, so we had to sell him. It’s better to get some money now than to have no money and a dead ox later down the trail. We already used our emergency grain for the horses, so maybe we’ll replenish our stock. The icy mountains are going to be rough on us and the horses, but I'd rather the horses be strong enough to pull us through the mountains instead of being stranded.”\r\n",
					"“Hey stranger, over here!... Quite a trek up these mountains, that’s for sure! But I am glad you made it, thank the lord… It sure is a pretty view up here. How has your journey been? I tell you, I have been nothing but sick for the longest time, so it’s nice to finally be up and moving around! Well, have safe travels!”",
					"“Oh hey again stranger! It sure is nice to be in a safe place after all those miles. The journey here was not easy, but by the grace of God we made it. How has yours been? Oh your wagon’s cover tore? I can help you fix that, just follow me!”",
					"“What a pleasure to run into you again! Why, we’re so close now that I can taste it! But my, the country sure is beautiful. It really does make all the hardship worth it. Oh, your wagon’s broken down? Our family has a lot of experience with that, let us take a look at it.”",
					"“By the grace of the Lord we made it! It sure was a rough journey, but we pulled through! Say, we should meet up again, maybe when we’ve both settled down a bit. Good luck, and I hope to see you soon!”",
			};
			
			// Determine location-unique conversation from Diarist
			private String locationUniqueConvo = diaristConvos[wagon.travel.wagonLocation-1];
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Select a random chat
				String[] chats = { 
						locationUniqueConvo,
						"You chat with a local about the trail"
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
						"You look around and off into the distance, but no one is nearby to talk to.",
						//new conversations
						"You chat with a Native American guide about the trail"
								+ "...\n\"The rivers may seem harsh, but I've been guiding wagons around "
								+ "them for a couple of months now. I ensure you'll have a smoother "
								+ "ride if you hire me or another from my tribe to cross any upcoming rivers. \"",
						"You chat with a young boy who's feeding his family's oxen"
								+"...\n\" My mama said we can have a happier life over in Oregon and papa agrees. "
								+ " I miss our old farm, but at least I could bring my books with me."
								+ " Mama says there's a better farm in Oregon next to my uncle's farm, "
								+ "we just have to travel to Oregon first. \"",
						"You wave to a woman foraging near the camp"
								+ "...\n\" Sorry stranger, I can't talk right now. My husband and youngest"
								+ "son have fallen ill, so I'm hoping I can find some medical herbs close by"
								+ "instead of paying for them. We were saving our money for a safe river guide, "
								+ "but I fear we may have to cross the river ourselves. "
								+ "Perhaps there's some untouched herbs deeper in the woods. \"",
						"You approach a man and his daughter fishing nearby."
								+"...\n\" Hello, there traveler. Are you here to fish too?"
								+ "The fish are plentyiful enough that we might not have to buy food at the next fort. "
								+ "I'm sure the others will be glad to have extra money for any future problems. "
								+ "Although now that I'm thinking about it, maybe we should consider getting some "
								+ "spare parts for our wagon. \"",
						"You overhear the loud conversations from the group walking near you. "
								+"...\n\" Now that's some real bad luck. A bite from a snake is bad enough, but having"
								+ "an accident that leads to an injured ox and broken wheel right after is just unfortunate."
								+ "Yeah, I don't know how much further the Smith family will make it with that kind of luck. \""
								+ "... They walk far enough away that you can't make out what they're saying anymore. ",
						"You talk with a local from this area."
								+"...\n\" We see more and more wagons everyday. I honestly thought my uncle was exaggerating in his letters."
								+ "My sister seems happy to have the extra business for her shop though.  \"",
						"You see a rancher approach you"
								+"...\n\" Hey there traveler, have you seen any wayward oxen around here?"
								+ "Some of ours escaped last night due to a faulty rope. Our group should be fine without them "
								+ "cause they were spares, but I would rather have them just in case. \""
								};
				Random random = new Random();
				int choice = random.nextInt(chats.length);
				localsTalkTextArea.setText(chats[choice]);
			}
		});
		localsTalkTextPanel.add(localsTalkScrollPane, BorderLayout.CENTER);
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
		frmLocationName.setBounds(100, 100, 600, 300);
		frmLocationName.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmLocationName.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 568, 212);
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
		riverText.setText(location.getDesc());

		riverPanel.add(riverText);

		// gets the current location
		Location current = wagon.travel.getCurLocation();
		if (current.getIsRiver() == true)
			hasCrossed = false;

		JButton btnNewButton = new JButton("See options");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// converts the current location into a river
				River newRiver = current.toRiver();
				newRiver.setConditions(current); // sets the conditions based on each individual river

				JFrame riverOptionsFrame = new JFrame();
				riverOptionsFrame.setBounds(100, 200, 400, 200);
				riverOptionsFrame.setVisible(true);
				riverOptionsFrame.setTitle(location.getName());

				JPanel riverOptionsPanel = new JPanel();

				JTextArea riverOptionsText = new JTextArea();
				riverOptionsText.setLineWrap(true);
				riverOptionsText.setWrapStyleWord(true);
				riverOptionsText.setEditable(false);
				riverOptionsPanel.setBackground(Color.WHITE);

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
							results += "\nYou lost " + conditions[2] + "% of your food!";	// displays the amount
							int foodAmount = wagon.getFoodWeight();									// of supplies lost
							int foodLost = (int) (((double)conditions[2]/100) * foodAmount);								// based on the
							wagon.setFoodWeight(foodAmount - foodLost);	
							//wagon.addToItem("Food", -foodLost);									// conditions
						}
						if (conditions[3] != 0) {
							results += "\nYou lost " + conditions[3] + " days!"; // displays the amount of days lost
							Date.increaseDays(conditions[3]);
						}
						JOptionPane.showMessageDialog(null, results);
						//riverResultsText.setText(results + "\n" + supplyResults + "\n" + daysLost);
						//riverResultsFrame.setVisible(true);
						hasCrossed = true;
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
							results += "\nYou lost " + conditions[1] + "% of your food!";
							int foodAmount = wagon.getFoodWeight();									// of supplies lost
							int foodLost = (int) (((double)conditions[1]/100) * foodAmount);								// based on the
							wagon.setFoodWeight(foodAmount - foodLost);	
							//wagon.addToItem("Food", -foodLost);
						}
						if (conditions[3] != 0) {
							results += "\nYou lost " + conditions[3] + " days!";
							Date.increaseDays(conditions[3]);
						}
						//riverResultsText.setText(results + "\n" + supplyResults + "\n" + daysLost);
						//riverResultsFrame.setVisible(true);
						JOptionPane.showMessageDialog(null, results);
						hasCrossed = true;
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
							results += "You lost " + conditions[1] + "% of your food!";
							int foodAmount = wagon.getFoodWeight();									// of supplies lost
							int foodLost = (int) (((double)conditions[1]/100) * foodAmount);								// based on the
							wagon.setFoodWeight(foodAmount - foodLost);
							//wagon.addToItem("Food", -foodLost);
						}
						if (conditions[3] != 0) {
							results += "You lost " + conditions[3] + " days!";
							Date.increaseDays(conditions[3]);
						}
						//riverResultsText.setText(results + "\n" + supplyResults + "\n" + daysLost);
						//riverResultsFrame.setVisible(true);
						JOptionPane.showMessageDialog(null, results);
						hasCrossed = true;

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

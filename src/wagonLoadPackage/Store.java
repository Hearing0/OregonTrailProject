package wagonLoadPackage;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Store {
	
	//STILL WAITING ON
	//health system merge to for adding bought oxen with health
	//need to fix it so tab only appears in a fort, but not quite sure how to get hasActivities to work
	
	private JFrame Storeframe;
	//private JTextField foodBuyTextBox;
	//private JTextField wheelBuyTextBox;
	//private JTextField bulletBuyTextBox;
	Wagon wagonS;
	int moneyShown;
	int foodStoreEdit;
	
	/**
	 * Create the application.
	 */
	public Store(Wagon wagon) {
		this.wagonS = wagon;
		moneyShown = wagonS.getTotalMoney();
		initialize();
	}
	


	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//base frame 
		Storeframe = new JFrame();
		Storeframe.setBounds(100, 100, 450, 300);
		Storeframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Storeframe.getContentPane().setLayout(null); //absolute layout effect
		Storeframe.setVisible(false);
		
		JLabel storeLabel = new JLabel("STORE");
		storeLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		storeLabel.setBounds(10, 5, 65, 31);
		Storeframe.getContentPane().add(storeLabel);
		
		JLabel foodLabel = new JLabel("Food $10 for 55 lbs"); //recommend 200 for each person
		foodLabel.setBounds(31, 33, 133, 22);
		Storeframe.getContentPane().add(foodLabel);
		
		JTextField foodBuyTextBox = new JTextField("0");
		foodBuyTextBox.setBounds(153, 33, 96, 20);
		Storeframe.getContentPane().add(foodBuyTextBox);
		foodBuyTextBox.setColumns(10);

		JLabel bulletLabel = new JLabel("Bullets $2 for 20");
		bulletLabel.setBounds(31, 53, 123, 31);
		Storeframe.getContentPane().add(bulletLabel);
		
		JTextField bulletBuyTextBox = new JTextField("0");
		bulletBuyTextBox.setBounds(153, 56, 96, 20);
		Storeframe.getContentPane().add(bulletBuyTextBox);
		bulletBuyTextBox.setColumns(10);
		
		JLabel wheelLabel = new JLabel("Wagon Wheel $10");
		wheelLabel.setBounds(31, 73, 133, 36);
		Storeframe.getContentPane().add(wheelLabel);
		
		JTextField wheelBuyTextBox = new JTextField("0");
		wheelBuyTextBox.setBounds(153, 79, 96, 20);
		Storeframe.getContentPane().add(wheelBuyTextBox);
		wheelBuyTextBox.setColumns(10);
		
		JLabel axleLabel = new JLabel("Wagon Axle $10");
		axleLabel.setBounds(31, 99, 123, 31);
		Storeframe.getContentPane().add(axleLabel);
		
		JTextField axleBuyTextBox = new JTextField("0");
		axleBuyTextBox.setBounds(153, 102, 96, 20);
		Storeframe.getContentPane().add(axleBuyTextBox);
		axleBuyTextBox.setColumns(10);
		
		JLabel tongueLabel = new JLabel("Wagon Tongue $10");
		tongueLabel.setBounds(31, 120, 123, 31);
		Storeframe.getContentPane().add(tongueLabel);
		
		JTextField tongueBuyTextBox = new JTextField("0");
		tongueBuyTextBox.setBounds(153, 125, 96, 20);
		Storeframe.getContentPane().add(tongueBuyTextBox);
		tongueBuyTextBox.setColumns(10);
		
		JLabel oxenLabel = new JLabel("Oxen $20 "); //recommend 6 oxen
		oxenLabel.setBounds(31, 142, 123, 31);
		Storeframe.getContentPane().add(oxenLabel);
		
		JTextField oxenBuyTextBox = new JTextField("0");
		oxenBuyTextBox.setBounds(153, 147, 96, 20);
		Storeframe.getContentPane().add(oxenBuyTextBox);
		oxenBuyTextBox.setColumns(10);
		
		JLabel clothesLabel = new JLabel("Clothes $10"); //recommend 2 clothes per person
		clothesLabel.setBounds(31, 163, 123, 31);
		Storeframe.getContentPane().add(clothesLabel);
		
		JTextField clothesBuyTextBox = new JTextField("0");
		clothesBuyTextBox.setBounds(153, 169, 96, 20);
		Storeframe.getContentPane().add(clothesBuyTextBox);
		clothesBuyTextBox.setColumns(10);
		
		JLabel totalMLabel = new JLabel("Total Money Left: $");
		totalMLabel.setBounds(31, 199, 115, 22);
		Storeframe.getContentPane().add(totalMLabel);
		
		//JLabel totalMoneyText = new JLabel();
		JLabel totalMoneyText = new JLabel("" + moneyShown);
		totalMoneyText.setBounds(150, 203, 49, 14);
		Storeframe.getContentPane().add(totalMoneyText);
		
		JLabel talkLabel = new JLabel("Hello Traveler");
		talkLabel.setBounds(195, 188, 211, 44);
		Storeframe.getContentPane().add(talkLabel);
		
		JButton buyButton = new JButton("Buy");
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//get the numbers from the text boxes and convert to int
				String foodString = foodBuyTextBox.getText();
				int buyFood = Integer.parseInt(foodString);
				String bulletString = bulletBuyTextBox.getText();
				int buyBullet = Integer.parseInt(bulletString);
				String wheelString = wheelBuyTextBox.getText();
				int buyWheel = Integer.parseInt(wheelString);
				String axleString = axleBuyTextBox.getText();
				int buyAxle = Integer.parseInt(axleString);
				String tongueString = tongueBuyTextBox.getText();
				int buyTongue = Integer.parseInt(tongueString);
				String oxenString = oxenBuyTextBox.getText();
				int buyOxen = Integer.parseInt(oxenString);
				String clothesString = clothesBuyTextBox.getText();
				int buyClothes = Integer.parseInt(clothesString);
				
				//determine the cost for the amount of items chosen to buy 
				int totalCost = (buyFood * 10) + (buyWheel * 10) + (buyBullet * 2 
						+ (buyAxle * 10)+ (buyTongue * 10)+ (buyOxen * 20)+ (buyClothes * 10));
				
				//checks if an item is >0 and if the player has enough money to buy items
				//if the player buys an item, then the cost is subtracted and item is added to wagon
				if(totalCost == 0) { //nothing is selected
					talkLabel.setText("Uh, you gonna buy anything?");
				}
				else if( wagonS.getTotalMoney() >= totalCost) { //player has enough money to buy
					wagonS.setTotalMoney(totalCost);

					talkLabel.setText("Thanks for the sale!");
					totalMoneyText.setText("" + wagonS.getTotalMoney());
					//items are added to Wagon arrayList
					if(buyFood > 0) {
						for(int i = 0; i <= buyFood; i++) {
							foodStoreEdit = wagonS.getFoodWeight() + 55;
							wagonS.setFoodWeight(foodStoreEdit);
						}
					}
					if(buyWheel > 0) {
						for(int i = 0; i <= buyWheel; i++) {
							wagonS.addToItem("Wheel", 1);
						}
					}
					if(buyBullet > 0) {
						for(int i = 0; i <= buyBullet; i++) {
							wagonS.addToItem("Bullets", 20);
						}
					}
					if(buyAxle > 0) {
						for(int i = 0; i <= buyAxle; i++) {
							wagonS.addToItem("Axle", 1);
						}
					}
					if(buyTongue > 0) {
						for(int i = 0; i <= buyTongue; i++) {
							wagonS.addToItem("Tongue", 1);
						}
					}
					if(buyOxen > 0) {
						for(int i = 0; i <= buyOxen; i++) {
							wagonS.addHealth("Ox", 400, "", true);
						}
					}
					if(buyClothes > 0) {
						for(int i = 0; i <= buyClothes; i++) {
							wagonS.addToItem("Clothes", 1);
						}
					}
				}
				else {
					talkLabel.setText("Hey you don't have enough for that!"); //player doesnt have enough money
				}
				
			}
		});
		buyButton.setBounds(299, 95, 89, 23);
		Storeframe.getContentPane().add(buyButton);
		

	}
	
	/**
	 * Sets the frame to visible.
	 * @param visible - Boolean that when true 
	 * allows the frame to be visible.
	 */
	public void setVisible(boolean visible) {
        Storeframe.setVisible(visible);
    }
	
	
	
	

}


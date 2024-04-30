package wagonLoadPackage;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Store extends Wagon{
	
	private JFrame Storeframe;
	private JTextField foodBuyTextBox;
	private JTextField wheelBuyTextBox;
	private JTextField bulletBuyTextBox;
	Wagon wagonS;
	int moneyShown;
	
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
		storeLabel.setBounds(10, 11, 65, 31);
		Storeframe.getContentPane().add(storeLabel);
		
		JLabel foodLabel = new JLabel("Food $50 for 100 lbs");
		foodLabel.setBounds(31, 53, 133, 22);
		Storeframe.getContentPane().add(foodLabel);
		
		JLabel wheelLabel = new JLabel("Wagon Wheel $100");
		wheelLabel.setBounds(31, 88, 133, 36);
		Storeframe.getContentPane().add(wheelLabel);
		
		JLabel bulletLabel = new JLabel("Bullets $10 for 20");
		bulletLabel.setBounds(31, 137, 123, 31);
		Storeframe.getContentPane().add(bulletLabel);
		
		JLabel totalMLabel = new JLabel("Total Money Left: $");
		totalMLabel.setBounds(31, 199, 115, 22);
		Storeframe.getContentPane().add(totalMLabel);
		
		//JLabel totalMoneyText = new JLabel();
		JLabel totalMoneyText = new JLabel("" + moneyShown);
		totalMoneyText.setBounds(150, 203, 49, 14);
		Storeframe.getContentPane().add(totalMoneyText);
		
		foodBuyTextBox = new JTextField("0");
		foodBuyTextBox.setBounds(153, 54, 96, 20);
		Storeframe.getContentPane().add(foodBuyTextBox);
		foodBuyTextBox.setColumns(10);
		
		wheelBuyTextBox = new JTextField("0");
		wheelBuyTextBox.setBounds(153, 96, 96, 20);
		Storeframe.getContentPane().add(wheelBuyTextBox);
		wheelBuyTextBox.setColumns(10);
		
		bulletBuyTextBox = new JTextField("0");
		bulletBuyTextBox.setBounds(153, 142, 96, 20);
		Storeframe.getContentPane().add(bulletBuyTextBox);
		bulletBuyTextBox.setColumns(10);
		
		JLabel talkLabel = new JLabel("Hello Traveler");
		talkLabel.setBounds(195, 188, 211, 44);
		Storeframe.getContentPane().add(talkLabel);
		
		JButton buyButton = new JButton("Buy");
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//get the numbers from the text boxes and convert to int
				String foodString = foodBuyTextBox.getText();
				int buyFood = Integer.parseInt(foodString);
				String wheelString = wheelBuyTextBox.getText();
				int buyWheel = Integer.parseInt(wheelString);
				String bulletString = bulletBuyTextBox.getText();
				int buyBullet = Integer.parseInt(bulletString);
				
				//determine the cost for the amount of items chosen to buy 
				int totalCost = (buyFood * 50) + (buyWheel * 100) + (buyBullet * 10);
				
				//checks if an item is >0 and if the player has enough money to buy items
				//if the player buys an item, then the cost is subtracted and item is added to wagon
				if(totalCost == 0) { //nothing is selected
					talkLabel.setText("Uh, you gonna buy anything?");
				}
				else if( wagonS.getTotalMoney() >= totalCost) { //player has enough money to buy
					wagonS.setTotalMoney(totalCost);

					talkLabel.setText("Thanks for the sale!");
					totalMoneyText.setText("" + wagonS.getTotalMoney());
					if(buyFood > 0) {
						for(int i = 0; i <= buyFood; i++) {
							addFoodItem("Food", 100, true, true);
						}
					}
					if(buyWheel > 0) {
						for(int i = 0; i <= buyWheel; i++) {
							addItem("Wheel", 100);
						}
					}
					if(buyBullet > 0) {
						for(int i = 0; i <= buyBullet; i++) {
							addItem("Bullets", 100);
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


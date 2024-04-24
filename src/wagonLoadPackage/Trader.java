package wagonLoadPackage;

import java.util.ArrayList;
import java.util.Random;

import wagonLoadPackage.Item;
import wagonLoadPackage.Wagon;

public class Trader {

	Random rand = new Random();
	Wagon wagon;

	// Store Trader Information
	int traderType;
	ArrayList<String> preferredItems;
	int offersRemaining = 1 + rand.nextInt(2);
	int tradesRemaining = 1 + rand.nextInt(2);

	// Store Trade Offer
	Item itemRequest;
	int itemRequestNum;
	Item itemOffer;
	int itemOfferNum;

	/**
	 * Initializes the Trader, the trader's type, and generate an offer.
	 * 
	 * @param traderType - Select trader type.
	 *                   0 is random, 1 is native, 2 is Fur Trader,
	 *                   and 3 is Traveler.
	 * @param wagon      - Player wagon for item interactions.
	 */
	public Trader(int traderType, Wagon wagon) {
		this.wagon = wagon;
		this.traderType = traderType;

		// this.setTraderPreferences();
		this.generateTradeOffer();
	}

	/**
	 * Selects a random item from the item pool.
	 * 
	 * @return - An random Item.
	 */
	public Item randomItem() {
		int choice = rand.nextInt(wagon.getItemListSize());

		Item result = wagon.getItem(choice);

		// Debug
		System.out.println("Wagon ItemList Size: " + wagon.getItemListSize());
		System.out.println("Random Item choice: " + choice);
		System.out.println("Random Item: " + result.getName());

		return result;
	}

	/**
	 * Selects a random, unique item separate from the itemCheck.
	 * Randomly selects an item, then checks if it matches itemCheck.
	 * If so, it recursively calls itself till it generates a unique item.
	 * 
	 * @param itemCheck - Item that will be compared with.
	 * @return - Item that is not the same as itemCheck.
	 */
	public Item randomUniqueItem(Item itemCheck) {
		Item result = this.randomItem();

		// Fail: Duplicate found, recursive call
		if (itemCheck.equals(result)) {
			result = this.randomUniqueItem(itemCheck);
		}

		return result;
	}

	/**
	 * Sets the Trader's preferred items to exchange.
	 */
	public void setTraderPreferences() {
		// Pick b/w traders' preference lists
		switch (traderType) {

			/*
			 * // Native
			 * case 1:
			 * String[] items = {
			 * "Water",
			 * "Bedroll",
			 * "Lard",
			 * "Salt",
			 * "Flour",
			 * "DryApples",
			 * "Beans",
			 * "Rice",
			 * "Bacon",
			 * };
			 * Stream.of(items).forEach(preferredItems::add);
			 * break;
			 * 
			 * // Fur Trader
			 * case 2:
			 * String[] items = {
			 * "Water",
			 * "Bedroll",
			 * "Lard",
			 * "Whiskey",
			 * "Salt",
			 * "Flour",
			 * "DryApples",
			 * "Beans",
			 * "Rice",
			 * "Bacon",
			 * };
			 * Stream.of(items).forEach(preferredItems::add);
			 * break;
			 * 
			 * // Traveler
			 * case 3:
			 * 
			 * break;
			 */
			// Default: No Preferences
		}

		// Debug: Display Trader Preferences
		/*
		 * System.out.println("--------Trader Preferences--------\n");
		 * for( int i = 0; i < preferredItems.size(); i++) {
		 * System.out.println("preference[" + i +"]:" + preferredItems.get(i));
		 * }
		 * System.out.println("\n--------Trader Preferences--------");
		 */
	}

	/**
	 * Generates a trade offer. Checks if the trader
	 * can make another offer.
	 * 
	 * @return - Boolean of whether a new offer could be made.
	 */
	public boolean generateTradeOffer() {
		boolean result = false;

		// If trader has offersRemaining, generate offer
		if (this.offersRemaining > 0) {
			// Update tracker and result
			offersRemaining--;
			result = true;

			// Pick b/w traders
			switch (traderType) {

				// Native
				case 1:

					break;

				// Fur Trader
				case 2:

					break;

				// Traveler
				case 3:
					break;

				// Default: Any item for any item
				default:
					// Generate random trade
					itemRequest = this.randomItem();
					itemRequestNum = rand.nextInt(itemRequest.getWeight());
					System.out.println("Wagon Loses weight " + itemRequestNum);
					System.out.println("LostItem total weight " + itemRequest.getWeight());

					itemOffer = this.randomUniqueItem(itemRequest);
					itemOfferNum = rand.nextInt(itemOffer.getWeight());
					System.out.println("Wagon Gains weight " + itemOfferNum);
					System.out.println("GainedItem total weight " + itemOffer.getWeight());

			}
		}

		return result;
	}

	/**
	 * Gets the current trade offer.
	 * 
	 * @return - ArrayList of Strings of:
	 *         Item Offered, for x amount, Item Requested, for y amount.
	 */
	public ArrayList<String> getTradeOffer() {
		ArrayList<String> offer = new ArrayList<String>();

		// If trade exists, fill with current trade offer
		if (itemOffer != null) {
			offer.add(itemOffer.getName());
			offer.add("" + itemOfferNum);
			offer.add(itemRequest.getName());
			offer.add("" + itemRequestNum);
		}

		// Fail: Trade does not exist
		else {
			for (int i = 0; i < 4; i++) {
				offer.add("N/A");
			}
		}

		return offer;
	}

	/**
	 * Conducts the trade between the player's wagon and the trader.
	 * Will check if the trade is possible (player has enough
	 * of item & that trader can still trade).
	 * 
	 * @return - Boolean of whether the trade was successfully
	 *         completed.
	 */
	public boolean conductTrade() {
		boolean result = false;

		// if canTrade, conduct trade
		if (canTrade()) {

			// Takes away wagonLosesNum weight of player
			int curItemWeight = itemRequest.getWeight();
			System.out.println(
					"Wagon loses " + itemRequest.getWeight() + " - " + itemRequestNum + " of " + itemRequest.getName());
			itemRequest.setWeight(curItemWeight - this.itemRequestNum);
			System.out.println("Wagon has " + itemRequest.getWeight());

			// Give wagonGainsNum weight to player
			curItemWeight = itemOffer.getWeight();
			System.out.println(
					"Wagon gains " + itemOffer.getWeight() + " + " + itemOfferNum + " of " + itemOffer.getName());
			itemOffer.setWeight(curItemWeight + this.itemOfferNum);
			System.out.println("Wagon has " + itemOffer.getWeight());

			// Flag that the Trade was Conducted
			this.tradesRemaining--;
			result = true;
		}

		return result;
	}

	/**
	 * Checks if the player has the item weight necessary
	 * to complete the trade or if the offer is available.
	 * 
	 * @return - Boolean of whether the player has the
	 *         necessary item weight.
	 */
	public boolean canTrade() {
		boolean result = false;

		try {
			// If player's does have enough of the requested
			// item & the trader can still trade, return true
			if (itemRequest.getWeight() >= itemRequestNum && this.tradesRemaining > 0) {
				result = true;
			}
		}
		// Fail: No Trade Offer available
		catch (NullPointerException evt) {
			result = false;
		}

		return result;
	}

	/**
	 * Clears the current trade offer.
	 */
	public void clearTrade() {
		this.itemOffer = null;
		this.itemOfferNum = 0;
		this.itemOfferNum = 0;
		this.itemRequest = null;
	}

}

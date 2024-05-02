package wagonLoadPackage;

import java.util.ArrayList;

import wagonLoadPackage.Travel;

/**
 * Wagon.java
 * Created on 3/23/2024
 * By David Flores
 * 
 * Creates a wagon that stores a list of items, maxWeight of the wagon,
 * edible food weight, and packing/unpacking items.
 */
public class Wagon {
	
	// Initialize Variables
	public ArrayList<Item> itemList;
	public int maxWeight = 2400;
	int wagonPeople = 4;
	public static Travel travel = new Travel();
    Date date = new Date();
	public int totalMoney = 1000;
	
	
	/**
	 * Gets the total amount of money in the player's wagon
	 * @return totalMoney - money in wagon
	 */
	public int getTotalMoney() {
		return totalMoney;
	}
	
	/**
	 * Sets the new total money in wagon after a cost is applied
	 * @param costChange - by how much is the total money to be changed
	 */
	public void setTotalMoney(int costChange) {
		totalMoney -= costChange;
	}


    /**
     * Initializes wagon with ArrayList filled with all items.
     * Note these items are not "loaded" in the wagon (the flags
     * are off by default).
     */
    public Wagon() {
        itemList = new ArrayList<Item>();

        /// Add all 31 items
        
        /*
        // Add 13 foodItems
        addFoodItem("ApleVingr", 25, false, false);
        addFoodItem("Bacon", 400, true, true, true); 		// pre-loaded
        addFoodItem("Beans", 200, true, true, true); 		// pre-loaded
        addFoodItem("Coffee", 80, false, false);
        addFoodItem("DryApples", 80, false, true, true); 	// pre-loaded
        addFoodItem("Flour", 500, true, false, true); 		// pre-loaded
        addFoodItem("Hardtack", 200, false, true, true); 	// pre-loaded
        addFoodItem("Lard", 200, true, true, true); 		// pre-loaded
        addFoodItem("Salt", 50, true, false);
        addFoodItem("Sugar", 40, true, false);
        addFoodItem("Rice", 200, true, true, true); 		// pre-loaded
        addFoodItem("Water", 100, true, true, true); 		// pre-loaded
        addFoodItem("Whiskey", 80, true, true);
		*/

        addItem("Food", 300, true);

        // ammo, clothes, etc.
        addItem("Bedroll", 15);
        addItem("Smith Tools", 200);
        addItem("Books", 75);
        addItem("Medicine", 10);
        addItem("Stove", 300);
        addItem("Chair", 20);
        addItem("Cookware", 75);
        addItem("Old Clock", 15);
        addItem("GunTools", 200);
        addItem("Bullets", 200);
        addItem("Keepsakes", 40);
        addItem("Leadshot", 25);
        addItem("Mirror", 15);
        addItem("Gunpower", 80);
        addItem("Tent Gear", 150);
        addItem("Tools", 50);
        addItem("Clothes", 8);
        addItem("Toys", 15);
        addItem("Wheel", 10);
        addItem("Axle", 10);
        addItem("Tongue", 10);
        
        addHealth("Human", 1000, "Name1"); //will likely be placed in whatever class allows naming
        addHealth("Wagon", 500, "");
        addHealth("Ox", 300, "");
        
        
        
    }

    /**
     * Creates and adds new health bar to the item HashMap
     * @param String Type - what the health is for
     * @param int HealthValue - amount of health left
     * @param String PlayerName - name either given by user or auto-generated 
     */
    public void addHealth(String Type, int HealthValue, String PlayerName) {
    	Health HP = new Health(Type, HealthValue, PlayerName);
        itemList.add(HP);
    }
    
    
    /**
     * Creates and adds new item to the item HashMap
     * 
     * @param name   - Name of the item
     * @param weight - Weight of the item
     */
    public void addItem(String name, int weight) {
        Item item = new Item(name, weight);
        itemList.add(item);
    }

    /**
     * Creates and adds new pre-loaded item to the item HashMap
     * 
     * @param name  	- Name of the item
     * @param weight 	- Weight of the item
     * @param isLoaded	- Whether the item is loaded into the wagon
     */
    public void addItem(String name, int weight, boolean isLoaded) {
        Item item = new Item(name, weight, isLoaded);
        itemList.add(item);
    }
    
    /**
     * Adds weight to a pre-existing item
     * @param name - Name of pre-existing Item to be updated
     * @param weightAdded - Weight to be added to Item.
     */
    public void addToItem(String name, int weightAdded) {
    	Item item = this.getItem(name);
    	
    	System.out.println(item.getName() + " old weight: " + item.getWeight());
    	
    	item.setWeight(weightAdded +  item.getWeight() );
    	
    	System.out.println(item.getName() + " new weight: " + item.getWeight());
    }
    
    
    /**
     * Creates and adds new unloaded food item to the item HashMap
     * @param name - Name of the item
     * @param weight - Weight of the item
     * @param cookable - Whether the food item can be cooked.
     * @param edible   - Whether the food item can be eaten.
     */
    public void addFoodItem(String name, int weight, boolean cookable, boolean edible) {
        FoodItem item = new FoodItem(name, weight, cookable, edible);
        itemList.add(item);
    }
    
    
    /**
     * Creates and adds new loaded food item to the item HashMap
     * @param name - Name of the item
     * @param weight - Weight of the item
     * @param cookable - Whether the food item can be cooked.
     * @param edible - Whether the food item can be eaten.
     * @param isLoaded - Whether the food item is loaded
     */
    /*
    public void addFoodItem(String name, int weight, boolean cookable, boolean edible, boolean isLoaded) {
    	FoodItem item = new FoodItem(name, weight, cookable, edible, isLoaded);
    	itemList.add(item);
    }
    */
    
    /**
     * Calculates the total weight of the loaded items on the wagon.
     * 
     * @return - Integer of total weight in lbs.
     */
    public int getTotalWeight() {
        int totalWeight = 0;

        // For every item in list
        for (Item item : itemList) {

            // If isLoaded into wagon...
            if (item.getIsLoaded() == true) {
                // Add weight to total
                totalWeight = totalWeight + item.getWeight();

                System.out.println(item.name);
            }
        }

        // Debug: Print out totalWeight
        System.out.println("\n----------------------------\n");
        System.out.println("totalWeight: " + totalWeight);
        System.out.println("\n----------------------------\n\n");

        return totalWeight;
    }

    
    /**
     * Gets the food weight in the wagon.
     * @return - Total weight of edible food in the wagon
     */
    public int getFoodWeight() {
        int foodWeight = itemList.get(0).getWeight();

        /*
        // For every item in list
        for (Item item : itemList) {
            // If isLoaded into wagon & is FoodItem...
            if (item.getIsLoaded() == true && item instanceof FoodItem) {
                // Cast Item as FoodItem
                FoodItem food = (FoodItem) item;

                // If FoodItem is edible...
                if (food.isEdible() == true) {
                    // Add weight to total
                    foodWeight = foodWeight + item.getWeight();
                }
            }
        }
        */

        return foodWeight;
    }
    
    /**
     * Sets the food weight for the "food" item
     * @param foodWeight - New food weight to be assigned
     */
    public void setFoodWeight(int foodWeight) {
    	System.out.println("Old food weight: "+itemList.get(0).getWeight());
    	
    	itemList.get(0).setWeight(foodWeight);
    	
    	System.out.println("New food weight: "+itemList.get(0).getWeight());
    }
    

    /**
     * Searches itemList to find the item w/ matching name. Can be
     * used to check if item is in list.
     * 
     * @param name - Name of the item instance to find
     * @return - Return Item instance w/ matching name. Returns null if non-existant
     */
    public Item getItem(String name) {
    	Item result = null;
    	
    	// Search list for item w/ matching name
    	for (Item item : itemList) {
    		
    		// If match found, return item
    		if (item.getName().equalsIgnoreCase(name)) {
    			System.out.println( item.getName() + " the item has been found!");
    			
    			result = item;
    			break;
    		}
    	}
    	
        return result;
    }

    /**
     * Gets the Item at index x of the itemList.
     * 
     * @param x - Integer index. Used to search for an
     *          item within itemList. Must be between 0
     *          and itemList.size().
     * @return - Item given by the index x. If x is
     *         not in itemList bounds, will return null.
     */
    public Item getItem(int x) {
        Item result = null;

        // If x is w/in bounds of itemList, retrieve item
        if (x >= 0 && x < itemList.size()) {
            result = itemList.get(x);
        }

        return result;
    }

    /**
     * Searches wagon for item matching name, then sets
     * the flag for whether that item is loaded into the
     * wagon.
     * 
     * @param name - Name of the item instance to find
     * @param flag - Boolean that is set to isLoaded.
     */
    public void packWagonItem(int i, boolean loadFlag) {
        itemList.get(i).setIsLoaded(loadFlag);

        // Debug: Readout each loadFlag
        System.out.println("\n----------------------------\n");
        System.out.println(itemList.get(i).name + " flaged: " + loadFlag);
        System.out.println("\n----------------------------\n\n");
    }

    /**
     * Gets the number of items currently in the game.
     * 
     * @return - Integer of the total number of items in the game.
     */
    public int getItemListSize() {
        return itemList.size();
    }

    /**
     * Consumes a random FoodItem by weightConsumed
     * 
     * @param weightConsumed - Total amount of weight to be eaten in lbs
     *                       TODO: Finish post MP3
     */
    /*
     * public void consumeFoodItem( int weightConsumed ) {
     * 
     * }
     */

    /**
     * Converts weight from uncooked FoodItem to cooked version of the FoodItem;
     * subtracting weightToCook from FoodItem
     * and (creating new FoodItem, if needed) adding weightToCook.
     * 
     * Max cooking amount is 50 lbs of food (per day) //TODO: verify this amount
     * 
     * @param name
     * @param weightToCook
     * 
     *                     TODO: Implement if time permits
     */
    /*
     * public void cookFoodItem(String name, int weightToCook) {
     * int maxCookLbs = 50;
     * 
     * // Attempt to find food item
     * try {
     * Item uncooked = getItem(name);
     * 
     * // TODO: check item is cookable
     * 
     * // check uncooked's weight enough & weighToCook is not too much
     * if (uncooked.getWeight() >= weightToCook && weightToCook <= maxCookLbs) {
     * int weight1 = uncooked.getWeight();
     * uncooked.setWeight(weight1 - weightToCook);
     * }
     * }
     * // Fail: Not Found
     * catch (Exception evt) {
     * 
     * }
     * }
     */

}

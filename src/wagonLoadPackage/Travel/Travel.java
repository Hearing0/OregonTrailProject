package wagonLoadPackage.Travel;

import java.util.ArrayList;

/**
 * Travel.java 
 * Created on 3/25/2024
 * By David Flores
 * 
 * Stores the travelSpeed, pace, food consumption, and can
 * determine the travelSpeed and whether there is enough
 * food on the wagon to make the trip. Also retrieves 
 * the flavor text for food consumption.
 */
public class Travel {
	
	// Initialize Variables
	int travelSpeed = 20; 		// miles per day
	double paceMod = 1; 		// 0.5x, 1x, 2x
	int consumeSelect = 1;
	int foodConsumeMod = consumeSelect * 4;		// (1 to 3) * 4 people on wagon
	double milesTillEnd = 2200;
	
	// Map Variables
	ArrayList<Location> map = new ArrayList<Location>(); 	// map of Oregon Trail
	int wagonLocation = 0;			// coordinates for map
	
	
	/**
	 * Initialize Travel w/ a filled map
	 */
	public Travel() {
		addLocation("Independence", 
				"Welcome to the fort town of Independence! It serves as a bustling hub of activity and a vital "
				+ "waypoint for pioneers heading westward. It boasts a "
				+ "diverse array of services and amenities, including trading posts, blacksmiths, "
				+ "general stores, and inns, catering to the needs of weary travelers. The town bustles "
				+ "with the comings and goings of wagons, settlers, and traders, with a lively "
				+ "atmosphere filled with anticipation and adventure. Independence is a crucial "
				+ "starting point for thousands embarking on the arduous journey to the Pacific "
				+ "Northwest, marking the beginning of their quest for a better life on the frontier.", 
				"Select Option", 
				200, 
				true);
		addLocation("Fort Kearney", 
				"Fort Kearney was established in the 1840s to protect pioneers, traders, "
				+ "and emigrants as they journeyed through the frontier. "
				+ "While not as heavily fortified as some military forts, "
				+ "Fort Kearney provided essential services and amenities to travelers. "
				+ "Its strategic location along the Platte River made it a natural "
				+ "resting place for travelers, offering a sense of safety and security "
				+ "amidst the challenges of the trail. "
				+ "Fort Kearney buzzed with activity during its heyday, bustling "
				+ "with wagons, settlers, soldiers, and traders, all flowing "
				+ "through this point on their journey westward.", 
				"Select Option", 
				75, 
				true);
		addLocation("The end!", 
				"You made it!", 
				"Select Option", 
				10000, 
				true);
	}
	/**
	 * returns the ArrayList of Locations associated with the map
	 * @return ArrayList of Location that contains every location
	 */
	public ArrayList<Location> getMap() {
		return map;
	}
	
	/**
	 * Sets the pace of the wagon
	 * @param paceSelect - From 1-3, sets
	 * the pace of the wagon to 0.5x, 1x, or 2x
	 * respectively. 
	 * 
	 * TODO: Use in future
	 */
	public void setPace( int paceSelect ) {
		switch( paceSelect ) {
			case 1:
				this.paceMod = 0.5; 
				break;
			case 2:
				this.paceMod = 1;
				break;
			case 3:
				this.paceMod = 2;
				break;				
		}
	}
	
	
	/**
	 * Sets the foodConsumptionMod of the wagon
	 * @param consumeSelect - Selects from 'Filling', 'Meager',
	 * and 'Bare Bones', each multiplying food eaten per person by
	 * 3, 2, and 1 respectively. 
	 * @param people - Number of people alive that will be eating 
	 * food.
	 * 
	 * TODO: Maybe dispose of people var in future 
	 * 
	 * @return - True if foodConsumeMod was set 
	 * (where consumeSelect was b/w 1-3), otherwise, returns false.
	 */
	public boolean setFoodConsumption( int consumeSelect, int people ) {
		boolean result = true;
		
		// Process the selection
		switch( consumeSelect ) {
			// Filling
			case 1:
				this.consumeSelect = consumeSelect;
				this.foodConsumeMod = 3 * people; 
				break;
				
			// Meager
			case 2:
				this.consumeSelect = consumeSelect;
				this.foodConsumeMod = 2 * people;
				break;
				
			// Bare Bones
			case 3:
				this.consumeSelect = consumeSelect;
				this.foodConsumeMod = 1 * people;
				break;
				
			default:
				result = false;
				break;
		}
		
		return result;
	}
	
	
	//Breanna Sproul
	//MOVED CONSUMPTION FROM FoodItem.java TO HERE DO TO MORE USEFUL VARIABLES PRESENT
	//checks if the weight is greater than weight of food needed to consume
	//just realized that .isEdible means nothing right now, so removed that and required foodWeight instead
	/**
	 * eatFood - boolean to check if there is enough foodWeight to be consumed
	 * @param int foodWeight - amount of food available in wagon
	 * @return result - true if there is enough food in wagon to be consumed. False if there is not enough food
	*/
    public boolean eatFood(int foodWeight) {
		boolean result = false;

		// Check foodweight against amount to consume
		if (foodWeight >= foodConsumeMod ) {
			result = true;
		} 
		
		// Fail: Not possible to Eat (not edible or amount is too much)
		else {
			result = false;
		}
		    	
    	return result;
    }
    
    //uses previous method to check if its possible to eat
    //if true, then remove the foodWeight and return the new weight
    //if false, then return -1 to signal that no food is left
	/**
	 * dailyConsume - if eatFood() is true, the food consumed if removed from the foodWeight
	 * @param int foodWeight - amount of food available in wagon
	 * @return foodWeight - returns the amount of food in wagon after food is consumed. If there is no food, then foodWeight=-1 to signal no food is available
	*/
    public int dailyConsume(int foodWeight) {
    	if(eatFood(foodWeight) == true) {
    		foodWeight = foodWeight - foodConsumeMod;
    		
    	}
    	else if(eatFood(foodWeight) == false) {
    		foodWeight = -1;
    		//put with a "you died" text pop-up?
    		
    	}
    	return foodWeight;
    }
	
	
	
	
	
	
	/**
	 * Gets the flavor text for the food consumption option
	 * selected.
	 * @return - String that can be either "Bare Bones",
	 * "Meager", or "Filling" depending on the consumeSelect 
	 * (1-3).
	 */
	public String getFlavorTxtFood() {
		String result = "";
		switch( this.consumeSelect ) {
			case 3:
				result = "Bare Bones";
				break;
			case 2:
				result = "Meager";
				break;
			case 1:
				result = "Filling";
				break;
		}
		
		return result;
	}
	
	
	/**
	 * Sets travelSpeed to value if in between the bounds of
	 * 12 <= Value <= 20. 
	 * 
	 * TODO: Replace in future
	 * 
	 * @param value - Integer value to set travelSpeed to. 
	 * Should be w/in 12 <= Value <= 20.
	 * @return - True if travelSpeed was set correctly, 
	 * otherwise, returns false.
	 */
	public boolean setTravelSpeed(int value) {
		boolean result = false;
		
		// Check: 12 <= value <= 20
		if ( value <= 20 && value >= 12 ) {
			// Set travelSpeed
			this.travelSpeed = value;
			
			// Debug: Print Travel Speed 
			System.out.println("travelSpeed: " + value);
			
			// Flag success
			result = true;
		}
		
		
		
		return result;
	}
	
	
	/**
	 * Calculates the travelSpeed of the wagon using 
	 * paceMod & foodConsumeMod
	 * @return - Returns the double travelSpeed post 
	 * calculation.
	 * TODO: Finish implementation in future & replace setTravelSpeed
	 */
	public int calculateTravelSpeed(int weight) {
		int baseTravelSpeed = 20; 
		
		// Determine Event
		
		// Get eventSpeedModifier
		
		// Determine location
		
		// Get locationSpeedModifier
		
		
		/// Calculate weightMod
		double weightMod;
		
		// Case: Underweight
		if ( weight <= 1000) {
			weightMod = 1.2;
		} 
		// Case: Normal weight
		else if( weight <= 1750) {
			weightMod = 1;
		} 
		// Case: Overweight
		else {
			weightMod = .8;
		}
		
			
		// Calculate travelSpeed
		this.travelSpeed = (int) (Math.round( baseTravelSpeed * paceMod * foodConsumeMod * weightMod ));
		
		return this.travelSpeed;
	}
	
	
	/**
	 * Checks if there is enough food for the trip. Determines
	 * the number of days till the final destination and days of food
	 * remaining. If days of food is enough for days till 
	 * destination, returns true.
	 * @param amtFood - The amount of food on the wagon
	 * @return - True if daysOfFood is greater or equal to daysToTravel,
	 * otherwise, returns false.
	 */
	public boolean isEnoughFoodToTravel( int amtFood) {
		boolean result = false;
				
		
		double daysToTravel = this.milesTillEnd / this.travelSpeed; 
		double daysOfFood = amtFood / this.foodConsumeMod;
		
		System.out.println("TravelDays: " + daysToTravel);	
		System.out.println("FoodDays: " + daysOfFood);
		
		
		// Check that enough daysOfFood to sustain daysToTravel...
		if (daysOfFood >= daysToTravel) {
			// ... is enough food
			result = true;
		}
		
		return result;
	}
	
	
	/**
	 * If isEnoughFoodToTravel, travel a distance of travelSpeed.
	 * If the wagon has reached a special Location, return true 
	 * and shift wagonLocation to next spot on map. 
	 * Can be used to pop open location menu.
	 * @param amtFood - Amount of Food in lbs 
	 * (used to check if enough food)
	 * @return - Returns true if wagon has made it to a new location.
	 */
	public boolean travelMap( int amtFood ) {
		boolean result = false;
		
		
		// if isEnoughFoodToTravel...
		if (this.isEnoughFoodToTravel(amtFood) == true) {

			// Travel by calculated distance
			// If location is fully traversed...
			if (map.get(wagonLocation).travel(travelSpeed) == true) {
				// Shift wagonLocation to new spot
				this.wagonLocation++;
				
				
				
				result = true;

				// Check for events...
				// evtCheck(currentLocation);
			}
		}
		return result;
	}
	
	
	/**
	 * Retrieves the most recently traversed Location instance. 
	 * @return - Returns the Location instance that the wagon 
	 * is has most recently arrived at.
	 */
	public Location getCurLocation() {
		
		System.out.println("current location: " + map.get(wagonLocation).name);
		
		return map.get(wagonLocation);
	}
	
	
	/**
	 * Adds Location to map.
	 * @param name - Name of location
	 * @param desc - Description of location
	 * @param prompt - Prompt for the location
	 * @param disTillNext - distance till the next location
	 * @param hasActs - Whether or not the location has any activites
	 */
    public void addLocation(String name, String desc, String prompt, int disTillNext, boolean hasActs) {
    	Location loc = new Location(name, desc, prompt, disTillNext, hasActs);
        map.add(loc);
    }
    
    /// Getter/Setters
    
    
    /**
     * Gets the travelSpeed of the wagon.
     * @return - Integer of the wagon's travelSpeed 
     */
    public int getTravelSpeed() {
    	return this.travelSpeed;
    }
    
    
}

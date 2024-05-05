package wagonLoadPackage;

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
	private int baseTravelSpeed = 10;
	private int travelSpeed = 20; // miles per day
	private double paceMod = 1; // 0.5x, 1x, 2x
	private int consumeSelect = 1;
	private int foodConsumeMod = consumeSelect * 4; // (1 to 3) * 4 people on wagon (updated via method)
	private double milesTillEnd = 1932;

	// Map Variables
	public ArrayList<Location> map = new ArrayList<Location>(); // map of Oregon Trail

	public int wagonLocation = 0; // coordinates for map

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
				106,
				true, false); // 0 miles
		addRiver("Kansas River", "Also known as the Kaw", 59, true, true); // 106 miles
		addRiver("Big Blue River", "River description here", 154, true, true); // 165 miles
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
				256,
				true, false); // 319 miles
		addLocation("Chimney Rock", "add description",75, true, false); // 575 miles
		addLocation("Fort Laramie", "add description", 164, true, false); // 650 miles
		addLocation("Independence Rock", "add description", 100, true, false); // 814 miles
		addLocation("South Pass", "add description",  67, true, false); // 914 miles
		addRiver("Green River", "River description here",  45, true, true); // 981 miles
		addLocation("Fort Bridger", "add description",  129, true, false); // 1026 miles
		addLocation("Soda Springs", "add description", 62, true, false); // 1155 miles
		addLocation("Fort Hall", "add description", 180, true, false); // 1217 miles
		addRiver("Snake River", "River description here", 113, true, true); // 1397 miles
		addLocation("Fort Boise", "add description", 158, true, false); // 1510 miles
		addLocation("Blue Mountains", "add description", 62, true, false); // 1668 miles
		addLocation("Fort Walla Walla", "add description", 89, true, false); // 1730 miles
		addLocation("The Dalles", "add description", 113, true, false); // 1819 miles
		addLocation("Willamette Valley, Oregon", "You made it!", 10000, true, false); // 1932 miles
	}

	/**
	 * Gets the map of Locations
	 * @return - ArrayList of Location
	 */
	public ArrayList<Location> getMap() {
		return map;
	}

	/**
	 * Sets the pace of the wagon
	 * 
	 * @param paceSelect - From 1-3, sets
	 *                   the pace of the wagon to 0.5x, 1x, or 1.5x
	 *                   respectively.
	 * @return - True if the pace was successfully set.
	 * @author David F
	 */
	public boolean setPace(int paceSelect) {
		boolean result = true;
		
		switch (paceSelect) {
			case 1:
				this.paceMod = 1.5;
				break;
			case 2:
				this.paceMod = 1;
				break;
			case 3:
				this.paceMod = .5;
				break;
			default:
				result = false;
		}
		
		return result;
	}

	/**
	 * Sets the foodConsumptionMod of the wagon
	 * 
	 * @param consumeSelect - Selects from 'Filling', 'Meager',
	 *                      and 'Bare Bones', each multiplying food eaten per person
	 *                      by
	 *                      3, 2, and 1 respectively.
	 * @param people        - Number of people alive that will be eating
	 *                      food.
	 * 
	 *                      TODO: Maybe dispose of people var in future
	 * 
	 * @return - True if foodConsumeMod was set
	 *         (where consumeSelect was b/w 1-3), otherwise, returns false.
	 * @author David F
	 */
	public boolean setFoodConsumption(int consumeSelect, int people) {
		boolean result = true;

		// Process the selection
		switch (consumeSelect) {
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

	/**
	 * Gets the current food consumption modifier.
	 * @return - Current food to be consumed
	 */
	int getFoodConsumed() {
		return foodConsumeMod;
	}
	
	/**
	 * Gets the flavor text for the food consumption option
	 * selected.
	 * 
	 * @return - String that can be either "Bare Bones",
	 *         "Meager", or "Filling" depending on the consumeSelect
	 *         (1-3).
	 * @author David F
	 */
	public String getFlavorTxtFood() {
		String result = "";
		switch (this.consumeSelect) {
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
	 * Gets the flavor text for the travel pace option
	 * selected.
	 * 
	 * @return - String that can be either "Steady pace",
	 *         "Strenuous pace", or "Grueling pace" depending on the paceMod
	 *         (1-3).
	 * @author David F
	 */
	public String getFlavorTxtPace() {
		String result = "";
		switch (this.consumeSelect) {
			case 3:
				result = "Steady pace";
				break;
			case 2:
				result = "Strenuous pace";
				break;
			case 1:
				result = "Grueling pace";
				break;
		}

		return result;
	}
	
	

	/**
	 * Sets travelSpeed to value if in between the bounds of
	 * 12 <= Value <= 20.
	 * 
	 * @param value - Integer value to set travelSpeed to.
	 *              Should be w/in 12 <= Value <= 20.
	 * @return - True if travelSpeed was set correctly,
	 *         otherwise, returns false.
	 * @author David F
	 */
	public boolean setTravelSpeed(int value) {
		boolean result = false;

		// Check: 12 <= value <= 20
		if (value <= 20 && value >= 12) {
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
	 * Calculates the travelSpeed of the wagon using wagonWeight,
	 * paceMod, eventSpeedMod, and general health of party
	 * 
	 * @param wagonWeight - Weight of wagon
	 * @param eventSpeedMod - Speed Modifier from Event
	 * @param partyHealth - Health of all party Members
	 * 
	 * @return - Returns the double travelSpeed post
	 *         calculation.
	 *         
	 * @author David F
	 */
	public int calculateTravelSpeed(int wagonWeight, int eventSpeedMod, ArrayList<Health> partyHealth) {
		baseTravelSpeed = 20;
		System.out.println("--Travel Speed--");
		
		// Determine Event
		//.eventCheck();

		// Get eventSpeedModifier

		// Determine location

		// Get locationSpeedModifier
		
		
		
		/// Check # of Oxen speed, # of sick people, & wagon condition ***
		int oxNum = 0;
		int sickNum = 0;
		
		System.out.println("-Party Condition-");
		// Check condition of all party members ***
		for (Health member : partyHealth) {
			String memType = member.getType();
			
			// Count Ox
			if (memType == "Ox") {
				oxNum++;
			}
			
			// Count Sick Humans
			else if (memType == "Human") {
				if (member.isLowHealth() != 0) {
					sickNum++;
				}
			}
			
			// Change baseTravelSpeed depending on Wagon condition  ***
			else if (memType == "Wagon") {
				switch (member.isLowHealth()) {
				
					// Faulty
					case 1:
						baseTravelSpeed = 15;
						break;
						
					// Barely moves
					case 2:
						baseTravelSpeed = 10;
						break;
						
					// Fine
					default:
						baseTravelSpeed = 20;
						break;
				}
			}
		}
		double oxMod = oxNum/4;
		double sickMod = 1 - sickNum/10;
		
		System.out.println("-oxMod: " + oxNum);
		System.out.println("-sickMod: " + sickMod);
		System.out.println("-baseTravelSpeed: " + this.baseTravelSpeed);
		
		/// Calculate weightMod ***
		double weightMod;
		int upperWeightLimit = 1850;
		int lowerWeightLimit = 1000;
		
		System.out.println("-Weight Modifier-");
		// Case: Under weight
		if (wagonWeight <= lowerWeightLimit) {
			System.out.print("-> under");
			double interpolate = 0.5 * (wagonWeight / lowerWeightLimit);
			weightMod = 1 + interpolate;
		}
		// Case: Normal weight
		else if (wagonWeight <= upperWeightLimit) {
			System.out.print("-> normal");
			weightMod = 1;
		}
		// Case: Over weight
		else {
			System.out.print("-> over");
			// Decrease from 1 to 0.5 as weight increases from upperWeightLimit to MAX_WEIGHT
			double interpolate = 0.5 * (wagonWeight - upperWeightLimit) / (Wagon.MAX_WEIGHT - upperWeightLimit);
			weightMod = 1 - interpolate;
		}		
		
		System.out.println("Weight Modifier: " + weightMod);
		System.out.println("Pace Modifier: " + this.paceMod);
		
		
		// Calculate travelSpeed
		this.travelSpeed = (int) (Math.round(baseTravelSpeed * paceMod * weightMod));
		System.out.println("travelSpeed: " + this.travelSpeed);
		

		return this.travelSpeed;
	}

	/**
	 * Checks if there is enough food for the trip. Determines
	 * the number of days till the final destination and days of food
	 * remaining. If days of food is enough for days till
	 * destination, returns true.
	 * 
	 * @param amtFood - The amount of food on the wagon
	 * @return - True if daysOfFood is greater or equal to daysToTravel,
	 *         otherwise, returns false.
	 * @author David F
	 */
	public boolean isEnoughFoodToTravelTrip(int amtFood) {
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
	 * Checks if there is enough food for another 5 days of traveling. Determines
	 * the days of food remaining. If days of food is more than 1, returns true.
	 * 
	 * @param amtFood - The amount of food on the wagon
	 * @return - True if daysOfFood is greater or equal to daysToTravel,
	 *         otherwise, returns false.
	 * @author David F
	 */
	public boolean isEnoughFoodToTravelFiveDays(int amtFood) {
		boolean result = false;

		double daysToTravel = 5;
		double daysOfFood = amtFood / this.foodConsumeMod;

		System.out.println("TravelDays: " + daysToTravel);
		System.out.println("FoodDays: " + daysOfFood);

		// Check that enough daysOfFood to sustain 5 more days of travel...
		if (daysOfFood >= 5) {
			// ... is enough food
			result = true;
		}

		return result;
	}
	
	/**
	 * Checks if there is enough food for another day of traveling. Determines
	 * the  days of food remaining. If days of food is more than 1, returns true.
	 * 
	 * @param amtFood - The amount of food on the wagon
	 * @return - True if daysOfFood is greater or equal to daysToTravel,
	 *         otherwise, returns false.
	 * @author David F
	 */
	public boolean isEnoughFoodToTravelOneDay(int amtFood) {
		boolean result = false;

		double daysToTravel = this.milesTillEnd / this.travelSpeed;
		double daysOfFood = amtFood / this.foodConsumeMod;

		System.out.println("TravelDays: " + daysToTravel);
		System.out.println("FoodDays: " + daysOfFood);

		// Check that enough daysOfFood to sustain 1 more day of travel...
		if (daysOfFood >= 1) {
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
	 * 
	 * @param amtFood - Amount of Food in lbs
	 *                (used to check if enough food)
	 * @return - Returns true if wagon has made it to a new location.
	 * @author David F
	 */
	public boolean travelMap(int amtFood) {
		boolean result = false;

		// if isEnoughFoodToTravel...
		if (this.isEnoughFoodToTravelOneDay(amtFood) == true) {

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
	 * 
	 * @return - Returns the Location instance that the wagon
	 *         is has most recently arrived at.
	 * @author David F
	 */
	public Location getCurLocation() {

		System.out.println("current location: " + map.get(wagonLocation).name);

		return map.get(wagonLocation);
	}

	/**
	 * Adds Location to map.
	 * 
	 * @param name        - Name of location
	 * @param desc        - Description of location
	 * @param disTillNext - distance till the next location
	 * @param hasActs     - Whether or not the location has any activites
	 * @param isRiver	  - Whether or not the location is a river
	 * @author David F
	 */
	private void addLocation(String name, String desc,  int disTillNext, boolean hasActs,
			boolean isRiver) {
		Location loc = new Location(name, desc, disTillNext, hasActs, isRiver);
		map.add(loc);
	}
	
	/**
	 * Adds River to map.
	 * 
	 * @param name        - Name of location
	 * @param desc        - Description of location
	 * @param disTillNext - distance till the next location
	 * @param hasActs     - Whether or not the location has any activites
	 * @param isRiver	  - Whether or not the location is a river
	 * @author Cody
	 */
	private void addRiver(String name, String desc,  int disTillNExt, boolean hasActs, boolean isRiver) {
		River river = new River(name, desc, disTillNExt, hasActs, isRiver);
		map.add(river);
	}

	/// Getter/Setters

	/**
	 * Gets the travelSpeed of the wagon.
	 * 
	 * @return - Integer of the wagon's travelSpeed
	 */
	public int getTravelSpeed() {
		return this.travelSpeed;
	}

	/**
	 * Calculates the total distance of the trip by adding the distance between each
	 * landmark together
	 * 
	 * @return - Integer of the total distance till the final location.
	 */
	public int getTotalDistance() {
		int totalDist = 0;

		for (int i = wagonLocation; i < map.size(); i++) {
			Location current = map.get(i);
			totalDist = totalDist + current.getDistance();
		}

		return totalDist;
	}

	/**
	 * gets the location of the wagon as an integer
	 * 
	 * @return - integer of the wagon's location
	 */
	public int getWagonLocation() {
		return this.wagonLocation;
	}

}

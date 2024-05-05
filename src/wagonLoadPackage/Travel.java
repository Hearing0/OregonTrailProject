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
	int travelSpeed = 20; // miles per day
	double paceMod = 1; // 0.5x, 1x, 2x
	int consumeSelect = 1;
	int foodConsumeMod = consumeSelect * 4; // (1 to 3) * 4 people on wagon
	double milesTillEnd = 1932;

	// Map Variables
	public ArrayList<Location> map = new ArrayList<Location>(); // map of Oregon Trail

	int wagonLocation = 0; // coordinates for map

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
				106,
				true, false); // 0 miles
		addRiver("Kansas River",
				"Also known as the Kaw, the Kansas River serves a vital lifeline for pioneers "
						+ "heading westward. The river’s course causes it to often run close to the trail, providing "
						+ "relief and a chance to refill traveler’s water barrels. It’s not all sunshine and rainbows though, "
						+ "as crossing it can be perilous, especially after long periods of rain. Good luck!",
				"Select Option",
				59,
				true, true); // 106 miles
		addRiver("Big Blue River",
				"The Blue River is a formidable natural obstacle for pioneers on the Oregon Trail. "
						+ "Its course often intersects the trail, which provides many a weary traveler with either danger or opportunity. "
						+ "When dry, it can be as small as a mere trickle, but in times of heavy rain, it can transform into an unpredictable "
						+ "torrent of water. Be careful, and good luck!",
				"Select Option",
				154,
				true, true); // 165 miles
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
				256,
				true, false); // 319 miles
		addLocation("Chimney Rock",
				"The towering grandiosity of Chimney Rock fills many a fatigued traveler with courage and determination. "
						+ "It can be seen for miles in any direction, proving a vital landmark to those on the Trail, and an inspiration for pioneers "
						+ "of the wonders to come along the trail. Take heart, and good luck!",
				"Select Option",
				75,
				true, false); // 575 miles
		addLocation("Fort Laramie",
				"Originally established as a fur trading post, Fort Laramie is now an important military outpost and trading hub"
						+ "for pioneers on the trail. You can restock your supplies, repair your wagon, and much more! Take a rest, but be sure to "
						+ "get back on the road! Good luck!",
				"Select Option",
				164, true, false); // 650 miles
		addLocation("Independence Rock",
				"Independence Rock stands as a monumental landmark over vast prairies. Named so after the many previous "
						+ "pioneers who reached this granite outcrop around Independence Day, it serves as a tangible sign that "
						+ "you are making your way across the country. Good luck!",
				"Select Option",
				100, true, false); // 814 miles
		addLocation("South Pass",
				"The South Pass plays a pivotal role in helping pioneers such as yourself cross the Rocky Mountains. "
						+ "It offers a much more pleasant crossing than other passes to the north or south. "
						+ "While this crossing may seem easy, the hardest leg of your journey is yet to come. "
						+ "Take a brief respite, and good luck!",
				"Select Option",
				67, true, false); // 914 miles
		addRiver("Green River",
				"The Green River cuts through Wyoming and Utah, and serves as a vital lifeline for pioneers such as yourself. "
						+ "While it might offer a brief respite from the hardships of the Trail, watch out for its rocky rapids "
						+ "and swift currents. Good luck!",
				"Select Option", 45, true, true); // 981 miles
		addLocation("Fort Bridger",
				"Established in the 1840s, Fort Bridger serves as a vital waypoint for pioneers on their way to Oregon."
						+ " Here you can restock your supplies, repair your wagon, and take a rest from the challenges of the Trail. Good luck!",
				"Select Option",
				129, true, false); // 1026 miles
		addLocation("Soda Springs",
				"The natural wonder of Soda Springs provides travelers with a small reprieve from the Oregon Trail. "
						+ "Its cool, naturally carbonated waters serve as a place where travelers can quench their thirst, "
						+ "before getting back on the road. Good luck!",
				"Select Option",
				62, true, false); // 1155 miles
		addLocation("Fort Hall",
				"Established in 1834 as a fur trading post, Fort Hall now serves as one of the most important posts for "
						+ "travelers on their journey to Oregon. Here you can restock your supplies and repair your wagon, "
						+ "and prepare for the last stretch of your journey. Good luck!",
				"Select Option",
				180, true, false); // 1217 miles
		addRiver("Snake River",
				"Snake River functions as an important waterway for those undertaking the Oregon Trail. "
						+ "Despite its rapids and swift currents, many a pioneer have used it to their advantage, setting up "
						+ "homesteads and settlements. While its crossing may be perilous, you’re almost there. Good luck!",
				"Select Option",
				113, true, true); // 1397 miles
		addLocation("Fort Boise",
				"Fort Boise, established in 1834, serves as one of the final forts along the path to the Willamette Valley."
						+ " Here weary explorers can take a rest and restock their supplies. You’re so close, don’t give up now! Good luck!",
				"Select Option",
				158, true, false); // 1510 miles
		addLocation("Blue Mountains",
				"As one of the last major obstacles on the path to Oregon, crossing the Blue Mountains serves as a rite "
						+ "of passage for many pioneers. With its towering pine trees, and sharp ascents and descents, it presents a true challenge "
						+ "to those wishing to get to Oregon. Good luck with this one, pioneer!",
				"Select Option",
				62, true, false); // 1668 miles
		addLocation("Fort Walla Walla",
				"As the last fort on your journey, make sure to stock up and be ready for the final leg of the journey. "
						+ "Fort Walla Walla is one of the last stops for many travelers seeking passage to the Willamette Valley. "
						+ "Get as many supplies as you can carry, and good luck of the final stretch!",
				"Select Option",
				89, true, false); // 1730 miles
		addLocation("The Dalles",
				"The Dalles serves as the last obstacle that many travelers face on the Oregon Trail. "
						+ "It marks the transition from the rugged Rocky Mountains to the lush, temperate climate of Oregon, and "
						+ "above all the Willamette Valley. Take a short rest here, but your adventure is almost over. Good luck!",
				"Select Option",
				113, true, false); // 1819 miles
		addLocation("Willamette Valley, Oregon", "You’ve made it! You’ve crossed almost half the country to get here, "
				+ "and the riches that the land provides are yours for the taking. Through all of the hardship that "
				+ "you and your party have endured, you have persevered through it all and made it here. "
				+ "While your journey on the Oregon Trail ends here, the rest of your adventure has just begun. "
				+ "Congratulations, and thanks for playing!",
				"Select Option",
				10000, true, false); // 1932 miles
	}

	public ArrayList<Location> getMap() {
		return map;
	}

	/**
	 * Sets the pace of the wagon
	 * 
	 * @param paceSelect - From 1-3, sets
	 *                   the pace of the wagon to 0.5x, 1x, or 2x
	 *                   respectively.
	 * 
	 *                   TODO: Use in future
	 */
	public void setPace(int paceSelect) {
		switch (paceSelect) {
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
	 * 
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
	 * Sets travelSpeed to value if in between the bounds of
	 * 12 <= Value <= 20.
	 * 
	 * TODO: Replace in future
	 * 
	 * @param value - Integer value to set travelSpeed to.
	 *              Should be w/in 12 <= Value <= 20.
	 * @return - True if travelSpeed was set correctly,
	 *         otherwise, returns false.
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
	 * Calculates the travelSpeed of the wagon using
	 * paceMod & foodConsumeMod
	 * 
	 * @return - Returns the double travelSpeed post
	 *         calculation.
	 *         TODO: Finish implementation in future & replace setTravelSpeed
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
		if (weight <= 1000) {
			weightMod = 1.2;
		}
		// Case: Normal weight
		else if (weight <= 1750) {
			weightMod = 1;
		}
		// Case: Overweight
		else {
			weightMod = .8;
		}

		// Calculate travelSpeed
		this.travelSpeed = (int) (Math.round(baseTravelSpeed * paceMod * foodConsumeMod * weightMod));

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
	 * the days of food remaining. If days of food is more than 1, returns true.
	 * 
	 * @param amtFood - The amount of food on the wagon
	 * @return - True if daysOfFood is greater or equal to daysToTravel,
	 *         otherwise, returns false.
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
	 * @param prompt      - Prompt for the location
	 * @param disTillNext - distance till the next location
	 * @param hasActs     - Whether or not the location has any activites
	 */
	public void addLocation(String name, String desc, String prompt, int disTillNext, boolean hasActs,
			boolean isRiver) {
		Location loc = new Location(name, desc, prompt, disTillNext, hasActs, isRiver);
		map.add(loc);
	}

	public void addRiver(String name, String desc, String prompt, int disTillNExt, boolean hasActs, boolean isRiver) {
		River river = new River(name, desc, prompt, disTillNExt, hasActs, isRiver);
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

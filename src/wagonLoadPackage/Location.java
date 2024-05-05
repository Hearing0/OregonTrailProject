package wagonLoadPackage;

import java.util.Random;

/**
 * Location.java
 * Created on 4/8/2024
 * By David Flores
 * 
 * Creates a location with a name,
 * description, prompt, distance till next
 * landmark, and whether it has activities.
 * Has get methods for all strings.
 * Has travel method to decrement distanceTillNext.
 */
public class Location {

	// Initialize Variables
	private int distanceTillNext = 0;
	// var imageBackgnd;
	// var imageForegnd;

	private boolean hasActivities = false;
	private boolean isRiver = false;

	// Generate number of available traders in locale
	private Random random = new Random();
	private int tradersRemaining;
	public Trader trader;

	public String name = "";
	private String desc = "";

	/**
	 * Initialize location
	 * 
	 * @param name        - Name of Location
	 * @param desc        - Description of Location
	 * @param prompt      - Special Prompt to the User
	 * @param disTillNext - Distance till the next Location
	 * @param hasActs     - Whether the location has any special activities
	 */
	public Location(String name, String desc, int disTillNext, boolean hasActs, boolean isRiver) {
		this.name = name;
		this.desc = desc;
		this.distanceTillNext = disTillNext;
		this.hasActivities = hasActs;
		this.isRiver = isRiver;

		// If hasActivites, initialize tradersRemaining
		if (this.hasActivities) {
			tradersRemaining = 1 + random.nextInt(3);
		}
	}

	/**
	 * Converts a Location object to a River object
	 * 
	 * @return - the Location as a River
	 */
	public River toRiver() {
		River newRiver = new River(name, desc, distanceTillNext, hasActivities, isRiver);
		return newRiver;
	}

	/**
	 * Get the location name
	 * 
	 * @return - String of Location name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the location description
	 * 
	 * @return - String of Location description
	 */
	public String getDesc() {
		return desc;
	}
	
	/**
	 * Gets the current distance till the next location.
	 * 
	 * @return - Integer of distance till the next location.
	 */
	public int getDistance() {
		return distanceTillNext;
	}

	/**
	 * Gets whether or not the current location has activities.
	 * 
	 * @return - Boolean. If location has activities then returns true.
	 */
	public boolean getHasActivites() {
		return hasActivities;
	}

	/**
	 * checks if the location has any traders remaining.
	 * 
	 * @return - True if tradersRemaining is above zero.
	 */
	public boolean hasTraders() {
		boolean result = false;

		if (this.tradersRemaining > 0) {
			result = true;
		}

		return result;
	}

	/**
	 * Decreases number of traders at the current location
	 */
	public void decrementTradersRemaining() {
		tradersRemaining--;
	}

	/**
	 * Gets the number of traders remaining
	 * 
	 * @return - Number of traders left at the location
	 */
	public int getTradersRemaining() {
		return tradersRemaining;
	}

	/**
	 * Get whether the location is a river.
	 * @return - True if the location is a river.
	 */
	public boolean getIsRiver() {
		return isRiver;
	}

	/**
	 * Travels towards the location by a distance.
	 * 
	 * @param distance - Number of miles traveled
	 * @return - True if the distance to the next
	 *         location has been fully traversed
	 *         , otherwise, false.
	 */
	public boolean travel(int distance) {
		boolean result = false;

		// Travel towards next Location
		distanceTillNext = distanceTillNext - distance;

		// Check if made it to next Location...
		if (distanceTillNext <= 0) {
			result = true;

			// Change coordinates to next location
		}

		return result;
	}
}

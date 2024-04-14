package wagonLoadPackage.Travel;



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
	int distanceTillNext = 0;
	//var imageBackgnd;
	//var imageForegnd;
		
	boolean hasActivities = false;
	int conditions[];
	
	String name = "";
	String desc = "";
	String prompt = "";
	
	
	/**
	 * Initialize location 
	 * @param name
	 * @param desc
	 * @param prompt
	 * @param disTillNext
	 * @param hasActs
	 */
	public Location(String name, String desc, String prompt, int disTillNext, boolean hasActs) {
		this.name = name;
		this.desc = desc;
		this.prompt = prompt;
		this.distanceTillNext = disTillNext;
		this.hasActivities = hasActs;
	}
		
	/**
	 * Get the location name
	 * @return - String of Location name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get the location description
	 * @return - String of Location description
	 */
	public String getDesc() {
		return desc;
	}
	
	/**
	 * Get the location prompt
	 * @return - String of Location prompt
	 */
	public String getPrompt() {
		return prompt;
	}
	
	/**
	 * Gets the current distance till the next location.
	 * @return - Integer of distance till the next location.
	 */
	public int getDistance() {
		return distanceTillNext;
	}
	
	/**
	 * Gets whether or not the current location has activities.
	 * @return - Boolean. If location has activities then returns true.
	 */
	public boolean getHasActivites() {
		return hasActivities;
	}
	
	/**
	 * Travels towards the location by a distance.
	 * @param distance - Number of miles traveled
	 * @return - True if the distance to the next
	 * location has been fully traversed
	 * , otherwise, false.
	 */
	public boolean travel(int distance) {
		boolean result = false;
		
		// Travel towards next Location
		distanceTillNext = distanceTillNext - distance;
		
		// Check if made it to next Location...
		if (distanceTillNext < 0 ) {
			result = true;
			
			// Change coordinates to next location
		}
		
		return result;
	}
	
	/*
	 * Not currently implemented nor planned to use (keeping in case tho)
	//TODO: move to travelMenu or other menu, and implement locMenu w/ activities
	public String chooseOption(int n) {
		String result = "";
		
		switch (n) {
			case 1:
				// Talk w/ locals
				result = "A local says, 'Hey, crazy story but...'";
				break;
			case 2:
				// Look at map
				result = "Looking at a map!";
				break;
			case 3:
				// Check out Store
				result = "Visiting the store...";
				break;
			case 4:
				// Checking supplies
				result = "Checking supplies...";
				break;
			case 5:
				// Continue travel
				result = "Continuing to travel...";
				break;
		}
				
		return result;
	}	
	*/
}

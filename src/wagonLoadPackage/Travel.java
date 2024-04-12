package wagonLoadPackage;

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
	
	
	
	// TODO: Implement Event & Location Classes
	/*
	String[][] map; 	// map of Oregan Trail
	int[] location;		// x,y coords for map
	*/

	
	
	/**
	 * Sets the pace of the wagon
	 * @param paceSelect - From 1-3, sets
	 * the pace of the wagon to 0.5x, 1x, or 2x
	 * respectively. 
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
				
		// Calculate daysToTravel and daysOfFood
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
}

package wagonLoadPackage;

import java.util.Random;

public class Health extends Item {
	
	//STILL NEEDS DONE
	//incorporate player naming system and addHealth for them once menus are available
	//put ox addition in store and make an addHealth for it
	//remove addHealth human and ox from Wagon class once above are done
	//might be some problems with the super() variables. - fix by moving anything with them to Item class??
	
	
	
	
	String type; //Human, Wagon, or Ox
	int healthValue; // Human = 1000, Wagon = 500, Ox = 300. Probably need to see if official HP is available for game
	String playerName; // "" if not a player
	private static Random ranName = new java.util.Random();
	
	
    /**
     * Initializes Health for Human, Wagon, or Ox
     * @param String Type - what the health is for
     * @param int HealthValue - amount of health left
     * @param String PlayerName - name either given by user or auto-generated 
     */
	public Health(String Type, int HealthValue, String PlayerName) {
		super(Type, HealthValue); //not sure if this works with Item class or if it needs to be changed to name and weight to fit methods
		this.playerName = PlayerName;
		
	}
	
	
	/**
	 * Gets the type/what the health is for
	 * @return String type - what the health is for
	 */
	public String getType() {
		return this.type;
	}
	
	
	/**
	 * Gets the healthValue of the item
	 * @return int healthValue - amount of health left
	 */
	public int getHealth() {
		return this.healthValue;
	}
	
	/**
	 * alters the health value by the amount given in parameters
	 * @param healthChange - how much is health being changed by, is added cause all events should be either positive to add or negative to subtract
	 */
	public void setHealth(int healthChange) {
		this.healthValue += healthChange;
	}
	
	
	/**
	 * Gets the name of the player if available
	 * @return String playerName - name either given by user or auto-generated 
	 */
	public String getName() {
		return this.playerName;
	}
	
	/**
	 * after checking if the user wants to name the people, fills in any empty names with random choice from pre-written names
	 * @return playerName - selects a name for unnamed person
	 */
	public void setRandomName() {
		int die = ranName.nextInt(6) + 1;
		if (die == 1) {
			this.playerName = "Sally";
		}
		else if(die == 2) {
			this.playerName = "Robert";
		}
		else if(die == 3) {
			this.playerName = "Alex";
		}
		else if(die == 4) {
			this.playerName = "Mary";
		}
		else if(die == 5) {
			this.playerName = "Bob";
		}
		else if(die == 6) {
			this.playerName = "Taylor";
		}
		
	}
	
	
	
	

}

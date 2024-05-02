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
	public Health(String type, int healthValue, String playerName) {
		super(type, healthValue); //not sure if this works with Item class or if it needs to be changed to name and weight to fit methods
		this.playerName = playerName;
		
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
		int die = ranName.nextInt(21) + 1;
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
		else if(die == 7) {
			this.playerName = "George";
		}
		else if(die == 8) {
			this.playerName = "Tamsen";
		}
		else if(die == 9) {
			this.playerName = "Eliza";
		}
		else if(die == 10) {
			this.playerName = "Frances";
		}
		else if(die == 11) {
			this.playerName = "Jacob";
		}
		else if(die == 12) {
			this.playerName = "Elizabeth";
		}
		else if(die == 13) {
			this.playerName = "William";
		}
		else if(die == 14) {
			this.playerName = "Caleb";
		}
		else if(die == 15) {
			this.playerName = "Isaac";
		}
		else if(die == 16) {
			this.playerName = "Lewis";
		}
		else if(die == 17) {
			this.playerName = "Samuel";
		}
		else if(die == 18) {
			this.playerName = "James";
		}
		else if(die == 19) {
			this.playerName = "Margret";
		}
		else if(die == 20) {
			this.playerName = "Martha";
		}
		else if(die == 21) {
			this.playerName = "Thomas";
		}
		
	}
	
	
	
	

}

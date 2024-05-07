package wagonLoadPackage;

/**
 * Health.java
 * 
 * Creates health storing what the health is for, amount of health left, name of player(if player), 
 * and if health is >0 or not. This is used in Wagon to make arrayList of events with 
 * easy parameters for the methods. 
 */

import java.util.Random;

public class Health {
	//variables
	String HPType;
	String playerName;
	int baseHP;
	int HP;
	boolean alive;
	private static Random ranName = new java.util.Random();
	
	static String player1Name = "";
	static String player2Name = "";
	static String player3Name = "";
	static String player4Name = "";
	
	
    /**
     * Initializes Health for Human, Wagon, or Ox
     * @param String HPType - what the health is for (human, wagon, or ox only)
     * @param int HP - amount of health left
     * @param String playerName - name either given by user or auto-generated 
     * @param boolean alive - true if the health is > 0, false if health is < 0
     */
	public Health(String HPType, int HP, String playerName, boolean alive) {
		this.HPType = HPType;
		this.HP = HP;
		this.baseHP = HP;
		this.playerName = playerName;
		this.alive = alive;
	}
	
	
	/**
	 * Checks if the party member's health status. 
	 * 0 is Healthy
	 * 1 is Sick
	 * 2 is Near Death
	 * 
	 * @return - Health Status of party member
	 */
	public int isLowHealth() {
		int result = 0;
		
		// If HP below 0.6 * baseHP, party member is "Mid" on HP
		if ( this.baseHP * 0.6 >= this.HP ) {
			result = 1;
		} 
		
		// If HP below 0.25 * baseHP, party member is "Low" on HP
		else if ( this.baseHP * 0.25 >= this.HP ) {
			result = 2;
		}
		
		return result;
	}
	

	/**
	 * Gets whether chosen is still alive or not
	 * @return boolean alive - true if the HP > 0
	 */
	public boolean getAlive() {
		return alive;
	}
	
	
	/**
	 * Gets the type/what the health is for
	 * @return String type - what the health is for
	 */
	public String getType() {
		return HPType;
	}
	
	
	/**
	 * Gets the healthValue of the item
	 * @return int healthValue - amount of health left
	 */
	public int getHealth() {
		return HP;
	}
	
	/**
	 * alters the health value by the amount given in parameters
	 * @param healthChange - how much is health being changed by, is added cause all events should be either positive to add or negative to subtract
	 */
	public void setHealth(int healthChange) {
		HP = healthChange;
	}
	
	/**
	 * Gets the name of the player
	 * @return String playerName - name either given by user or auto-generated 
	 */
	public String getName() {
		return playerName;
	}
	
	/**
	 * Gets the name of the first player
	 * @return String player1Name - name either given by user or auto-generated 
	 */
	public static String getName1() {
		return player1Name;
	}
	
	/**
	 * Gets the name of the second player
	 * @return String player2Name - name either given by user or auto-generated 
	 */
	public static String getName2() {
		return player2Name;
	}
	
	/**
	 * Gets the name of the third player
	 * @return String player3Name - name either given by user or auto-generated 
	 */
	public static String getName3() {
		return player3Name;
	}
	
	/**
	 * Gets the name of the fourth player
	 * @return String player4Name - name either given by user or auto-generated 
	 */
	public static String getName4() {
		return player4Name;
	}
	
	
	
	/**
	 * sets the given name
	 * @param String newName - given new name to override placeholder text
	 */
	public void setName(String newName) {
		playerName = newName;
	}
	
	
	/**
	 * after checking if the user wants to name the people, fills in any empty names with random choice from pre-written names
	 * @return playerName - selects a name for unnamed person
	 */
	public String setRandomName() {
		int die = ranName.nextInt(21) + 1;
		if (die == 1) {
			playerName = "Sally";
		}
		else if(die == 2) {
			playerName = "Robert";
		}
		else if(die == 3) {
			playerName = "Alex";
		}
		else if(die == 4) {
			playerName = "Mary";
		}
		else if(die == 5) {
			playerName = "Bob";
		}
		else if(die == 6) {
			playerName = "Taylor";
		}
		else if(die == 7) {
			playerName = "George";
		}
		else if(die == 8) {
			playerName = "Tamsen";
		}
		else if(die == 9) {
			playerName = "Eliza";
		}
		else if(die == 10) {
			playerName = "Frances";
		}
		else if(die == 11) {
			playerName = "Jacob";
		}
		else if(die == 12) {
			playerName = "Elizabeth";
		}
		else if(die == 13) {
			playerName = "William";
		}
		else if(die == 14) {
			playerName = "Caleb";
		}
		else if(die == 15) {
			playerName = "Isaac";
		}
		else if(die == 16) {
			playerName = "Lewis";
		}
		else if(die == 17) {
			playerName = "Samuel";
		}
		else if(die == 18) {
			playerName = "James";
		}
		else if(die == 19) {
			playerName = "Margret";
		}
		else if(die == 20) {
			playerName = "Martha";
		}
		else if(die == 21) {
			playerName = "Thomas";
		}
		return playerName;
		
	}
	

	public void setPlayerName(String first, String second , String third, String fourth) {
		if(first != "") {
			player1Name = first;
		}
		if(second != "") {
			player2Name = second;
		}
		if(third != "") {
			player3Name = third;
		}
		if(fourth != "") {
			player4Name = fourth;
		}
	}
	

}

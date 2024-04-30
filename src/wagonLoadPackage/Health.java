package wagonLoadPackage;

public class Health extends Item {
	
	String type; //Human, Wagon, or Ox
	int healthValue; // Human = 1000, Wagon = 500, Ox = 300
	String playerName; // "" if not a player
	
	
    /**
     * Initializes Health for Human, Wagon, or Ox
     * @param String Type - what the health is for
     * @param int HealthValue - amount of health left
     * @param String PlayerName - name either given by user or auto-generated 
     */
	public Health(String Type, int HealthValue, String PlayerName) {
		super(Type, HealthValue);
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
	 * Gets the name of the player if available
	 * @return String playerName - name either given by user or auto-generated 
	 */
	public String getName() {
		return this.playerName;
	}
	
	
	
	

}

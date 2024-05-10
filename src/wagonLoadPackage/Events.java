package wagonLoadPackage;

/**
 * Events.java
 * Created: April 2024
 * Author - Breanna Sproul
 * 
 * Creates an event that stores the event's name, location, if its affecting an item, how much is it
 * affecting item, if its affecting health, how much is it affecting health, if it affects the date,
 * and if it increases the risk for future events to occur. This is used in RandomEvents to make an 
 * arrayList of events with easy parameters for the methods
 */

public class Events {
	//variables
	String event;
	String locationID;
	String whatItem;
	int itemChange;
	String whoseHealth;
	int healthChange;
	boolean affectDate;
	boolean increaseRisk;

	/**
	 * Initializes new health item to HPList in Wagon
	 * @param String event - name of the event
	 * @param String locationID - where the event takes place
	 * @param String whatItem - the item that is being affected by the event
	 * @param int itemChange - amount the item will be changed by
	 * @param String whoseHealth - the health owner that is being affected
	 * @param int healthChange - amount the health owner will be changed by
	 * @param boolean affectDate - true means the date is moved forward, false means nothing happens
	 * @param boolean increaseRisk - true means that the random number is lowered to increase event odds, false means nothing happens
	 */
	public Events(String event, String locationID, String whatItem, int itemChange, String whoseHealth,
			int healthChange, boolean affectDate, boolean increaseRisk) {
		this.event = event;
		this.locationID = locationID;
		this.whatItem = whatItem;
		this.itemChange = itemChange;
		this.whoseHealth = whoseHealth;
		this.healthChange = healthChange;
		this.affectDate = affectDate;
		this.increaseRisk = increaseRisk;

	}

	/**
	 * gets the event name
	 * @return String event - name of the event
	 */
	public String getEvent() {
		return this.event;
	}

	/**
	 * gets the ID of the location
	 * @return String locationID - where the event takes place
	 */
	public String getLocationID() {
		return this.locationID;
	}
	
	/**
	 * gets which item is being affected
	 * @return String whatItem - the item that is being affected by the event
	 */
	public String getwhatItem() {
		return this.whatItem;
	}
	
	/**
	 * gets how much the item chosen is being affected
	 * @return int itemChange - amount the item will be changed by
	 */
	public int getItemChange() {
		return this.itemChange;
	}
	
	/**
	 * gets which health owner is being affected
	 * @return String whoseHealth - the health owner that is being affected
	 */
	public String getWhoseHealth() {
		return this.whoseHealth;
	}
	
	/**
	 * gets how much the chosen health owner is being affect
	 * @return int getHealthChange - amount the health owner will be changed by
	 */
	public int getHealthChange() {
		return this.healthChange;
	}
	
	/**
	 * gets whether the event will affect the current date or not
	 * @return boolean affectDate - true means the date is moved forward, false means nothing happens
	 */
	public boolean getAffectDate() {
		return this.affectDate;
	}
	
	/**
	 * gets whether the event will increase the risk for future events
	 * @return boolean increaseRisk - true means that the random number is lowered to increase event odds, false means nothing happens
	 */
	public boolean getIncreaseRisk() {
		return this.increaseRisk;
	}

}

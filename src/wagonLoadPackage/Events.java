package wagonLoadPackage;


public class Events {
	String event;
	String locationID;
	String whatItem;
	int itemChange;
	String whoseHealth;
	int healthChange;
	boolean affectDate;
	boolean increaseRisk;
	
	
	public Events(String event, String locationID, String whatItem, int itemChange, String whoseHealth, 
			int healthChange, boolean affectDate, boolean increaseRisk) {
        this.event = event;
        this.locationID = locationID;
        //need to add the rest here as well as make getter methods for them
		
	}
	
	public String getEvent() {
		return this.event;
	}
	
	public String getLocationID() {
		return this.locationID;
	}
	


}

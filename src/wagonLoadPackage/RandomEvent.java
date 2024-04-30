package wagonLoadPackage;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class RandomEvent {
	private static Random rng = new java.util.Random();
	ArrayList<Events> eventList;
	String name = "";
	String locator = "";
	
	//STILL NEEDS DONE
	//get methods to affect systems such as health, travel, weather, and other wagon things once those system are fully set up and finalized
	//make method(s) that incorporates the eventList
	//connect locationID in eventList to places found in location class - could alter location() to include a parameter for this

	
	public RandomEvent(){
		eventList = new ArrayList<Events>();
		addEvent("Thunderstorm", "General", "", 0, "", 0, false, true);
		addEvent("Blizzard", "Mountain", "", 0, "", 0, false, true);
		addEvent("Lost Trail", "General", "", 0, "", 0, true, false);
		addEvent("Thunderstorm", "General", "", 0, "", 0, false, true);
		addEvent("A Thief stole from you", "General", "food", -50, "", 0, false, false);
		addEvent("Wild fruit", "General", "food", 50, "", 0, false, false);
		addEvent("Injured ox", "General", "", 0, "ox", -50, false, true);
		addEvent("Bad grass", "General", "", 0, "", 0, false, true);
		addEvent("Snakebite", "snakeRiver", "", 0, "person", 50, false, false);
		addEvent("Broken bone", "General", "", 0, "person", -50, false, false);
		
	}
	
	/**
	 * adds an event to arrayList so it can be easily used in later methods
	 * @param name - description of event that pops up in dialog box
	 * @param locationID - where must the event occur at, ex. blizzard cant occur in desert, use general if theres no specific need
	 * @param whatItem - if an item is being changed, what is the item
	 * @param itemChange - if an item is being changed, how much is it being changed by (use negative # if it is removing)
	 * @param whoseHealth - if health is being changed, whose HP is changing
	 * @param healthChange - if health is being changed, how much is it being changed by (use negative # if it is removing)
	 * @param affectDate - does this event affect the travel date
	 * @param increaseRisk - does this event increase risk, ex. if theres heavy fog then theres a greater chance to encounter more events due to low visibility
	 */
    public void addEvent(String name, String locationID, String whatItem, int itemChange, 
    		String whoseHealth, int healthChange, boolean affectDate, boolean increaseRisk) {
    	Events entry = new Events(name, locationID, whatItem, itemChange, whoseHealth, 
    			healthChange, affectDate, increaseRisk);
        eventList.add(entry);
    }

	
	
	
	//uses rng to randomly check for occurence of random event
	//basic right now to prepare for weather and traveling changes
	//may need to alter the odds to  make it similar to actual game, but its good for testing now
    /**
     * performs RNG to determine if an event occurs, and uses methods to select the event if event does occur
     * @param distanceTraveled - distance given by travel button, may be removed later
     */
	public void eventCheck(int distanceTraveled) {
		int die = rng.nextInt(10) + 1;
		if(die == 1) {
			//eventAlert();
			eventPop(eventAlert());
		}
		
	}
	
	//will be replaced by the arrayList
	//determines which event is activated, will need to edit once location and event are relevant
	//called by the activeEvent method which also does the decrease of miles
	//displays to console right now, but should probably be put on GUI/pop-box later
	private String eventAlert() {
		int choice = rng.nextInt(6) + 1;
		String event;
		if(choice == 1) {
			event = "Snakebite!";
			System.out.println(event);
			return event;
		}
		else if(choice == 2) {
			event = "Bad Water";
			System.out.println(event);
			return event;
		}
		else if(choice == 3) {
			event = "Injured Ox";
			System.out.println(event);
			return event;
		}
		else if(choice == 4) {
			event = "Fever";
			System.out.println(event);
			return event;
		}
		else if(choice == 5) {
			event = "Bad Grass";
			System.out.println(event);
			return event;
		}
		else {
			event = "Rough Trail";
			System.out.println(event);
			return event;
		}
	}
	
	/**
	 * brings up a dialog box that lists the event that occurred
	 * @param event - grabs the description of the event to display
	 */
	public void eventPop(String event) {
		String textF  = event;
		String titleF = "An event has occured";
		int typeF = JOptionPane.INFORMATION_MESSAGE;
		JOptionPane.showMessageDialog(null, textF, titleF, typeF);
		
	}

	
	

	
	
}

package wagonLoadPackage;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class RandomEvent {
	private static Random rng = new java.util.Random();
	ArrayList<Events> eventList;
	String name = "";
	String locator = "";
	Wagon wagonE;

	//due to lack of time, im just going to limit events and hard code them
	// STILL NEEDS DONE 
	// get methods to affect systems such as health, travel, weather, and other
	// make method(s) that incorporates the eventList
	// connect locationID in eventList to places found in location class - could
	// alter location() to include a parameter for this

	public RandomEvent(Wagon wagon) {
		this.wagonE = wagon;
		
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


	// uses rng to randomly check for occurence of random event
	// basic right now to prepare for weather and traveling changes
	// may need to alter the odds to make it similar to actual game, but its good
	// for testing now
	/**
	 * performs RNG to determine if an event occurs, and uses methods to select the
	 * event if event does occur
	 * 
	 * @param distanceTraveled - distance given by travel button, may be removed
	 *                         later
	 */
	public void eventCheck(int distanceTraveled) {
		eventCheck();
	}
	
	//uses rng to randomly check for occurence of random event
	//basic right now to prepare for weather and traveling changes
	//may need to alter the odds to  make it similar to actual game, but its good for testing now
    /**
     * performs RNG to determine if an event occurs, and uses methods to select the event if event does occur
     * NOTE: Same with distance
     */
	public void eventCheck() {
		int die = rng.nextInt(10) + 1;
		if (die == 1) {
			eventPop(eventAlert());
		}
	}
	
	/**
	 * brings up a dialog box that lists the event that occurred
	 * 
	 * @param event - grabs the description of the event to display
	 */
	public void eventPop(String event) {
		String textF = event;
		String titleF = "An event has occured";
		int typeF = JOptionPane.INFORMATION_MESSAGE;
		JOptionPane.showMessageDialog(null, textF, titleF, typeF);

	}

	//used for testing purposes ONLY
	//hard code this method to whatever event you are trying to test
	public void forceEvent() {
		String event = "Injure Wagon!";
		//System.out.println(event);
		int wagonHP = wagonE.HPList.get(0).getHealth();
		//int newWHP = wagonHP - 5;
		wagonE.HPList.get(0).setHealth(wagonHP - 5);
		eventPop(event);
	}
	
	// will be replaced by the arrayList
	// determines which event is activated, will need to edit once location and
	// event are relevant
	// called by the activeEvent method which also does the decrease of miles
	// displays to console right now, but should probably be put on GUI/pop-box
	// later
	private String eventAlert() {
		int choice = rng.nextInt(3) + 1;
		String event;
		if (choice == 1) {
			event = "Injure Wagon!";
			//System.out.println(event);
			int wagonHP = wagonE.HPList.get(0).getHealth();
			int newWHP = wagonHP - 25;
			wagonE.HPList.get(0).setHealth(newWHP);
			return event;
		} else if (choice == 2) {
			event = "Injure Human";
			//System.out.println(event);
			int pickHuman = rng.nextInt(4) + 1;
			if(pickHuman == 1 ) { //human1
				int humanHP = wagonE.HPList.get(1).getHealth();
				int newHHP = humanHP - 5;
				wagonE.HPList.get(1).setHealth(newHHP);
			}
			else if(pickHuman == 2) { //human2
				int humanHP = wagonE.HPList.get(2).getHealth();
				int newHHP = humanHP - 5;
				wagonE.HPList.get(2).setHealth(newHHP);
			}
			else if(pickHuman == 3) { //human3
				int humanHP = wagonE.HPList.get(3).getHealth();
				int newHHP = humanHP - 5;
				wagonE.HPList.get(3).setHealth(newHHP);
			}
			else if(pickHuman == 4) { //human4
				int humanHP = wagonE.HPList.get(4).getHealth();
				int newHHP = humanHP - 5;
				wagonE.HPList.get(4).setHealth(newHHP);
			}
			return event;
		} else {
			event = "Injure Ox";
			//System.out.println(event);
			for(int i = 5; i < wagonE.HPList.size(); i++) {
				if(wagonE.HPList.get(i).getAlive() == true) {
					int oxHP = wagonE.HPList.get(i).getHealth();
					int newOHP = oxHP - 25;
					wagonE.HPList.get(i).setHealth(newOHP);
				}
			}
			return event;
		}

	}
	
	

	/**
	 * adds an event to arrayList so it can be easily used in later methods
	 * 
	 * @param name         - description of event that pops up in dialog box
	 * @param locationID   - where must the event occur at, ex. blizzard cant occur
	 *                     in desert, use general if theres no specific need
	 * @param whatItem     - if an item is being changed, what is the item
	 * @param itemChange   - if an item is being changed, how much is it being
	 *                     changed by (use negative # if it is removing)
	 * @param whoseHealth  - if health is being changed, whose HP is changing
	 * @param healthChange - if health is being changed, how much is it being
	 *                     changed by (use negative # if it is removing)
	 * @param affectDate   - does this event affect the travel date
	 * @param increaseRisk - does this event increase risk, ex. if theres heavy fog
	 *                     then theres a greater chance to encounter more events due
	 *                     to low visibility
	 */
	public void addEvent(String name, String locationID, String whatItem, int itemChange,
			String whoseHealth, int healthChange, boolean affectDate, boolean increaseRisk) {
		Events entry = new Events(name, locationID, whatItem, itemChange, whoseHealth,
				healthChange, affectDate, increaseRisk);
		eventList.add(entry);
	}
	
	

}

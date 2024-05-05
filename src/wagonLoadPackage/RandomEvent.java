package wagonLoadPackage;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class RandomEvent {
	private static Random rng = new java.util.Random();
	ArrayList<Events> eventList;
	Wagon wagonE;
	
	boolean itemEdit = false;
	boolean healthEdit = false;

	String name;
	String event;
	String locationID = "";
	String whatItem;
	int itemChange;
	String whoseHealth;
	int healthChange;
	boolean affectDate;
	boolean increaseRisk;
	boolean isRiskActive;

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
	public void addEvent(String event, String locationID, String whatItem, int itemChange,
			String whoseHealth, int healthChange, boolean affectDate, boolean increaseRisk) {
		Events entry = new Events(event, locationID, whatItem, itemChange, whoseHealth,
				healthChange, affectDate, increaseRisk);
		this.event = event;
		this.locationID = locationID;
		this.whatItem = whatItem; //currently just a food edit, but it might stay that way
		this.itemChange = itemChange;
		this.whoseHealth = whoseHealth;
		this.healthChange = healthChange;
		this.affectDate = affectDate;
		this.increaseRisk = increaseRisk;
		eventList.add(entry);
	}
	
	//ARRAYLIST STUFF
	//grabs a random event from eventList and gets its index
	public int pickFromList() {
		int listNum = rng.nextInt(eventList.size()) + 1;
		//add an if correct location here??
		//if correct location then continue
		//it not correct then re-run this method until a correct location is found
		return listNum;
	}
	
	//ARRAYLIST STUFF
	//sets up boolean of the picked events
	public void pickBooleanCheck(int index) {
		if(eventList.get(index).itemChange != 0) {
			itemEdit = true;
		}
		if(eventList.get(index).healthChange != 0 ) {
			healthEdit = true;
		}
	}
	
	//ARRAYLIST STUFF
	//identifies any edits and applies them
	//I made it so you need to use negative numbers, but this needs tested to ensure proper calculations
	public void causeEdits(int index) {
		
		//date edit - IDK IF THIS WORKS, I JUST TOOK THIS FROM WAGONLOAD
		if(eventList.get(index).affectDate == true) {
			for(int i = 0; i < 4; i++) {
				wagonE.date.increaseDays();
			}
		}
		
		//food edit
		if(itemEdit == true) {
			int newFoodEdit = wagonE.getFoodWeight() + itemChange;
			wagonE.setFoodWeight(newFoodEdit);
			isRiskActive = false;
		}
		
		//risk edit - alters the rng number when calling next event
		if(eventList.get(index).increaseRisk == true){
			isRiskActive = true;
		}
		
		//health edit
		if(healthEdit == true) {
			//wagon HP
			if(eventList.get(index).whoseHealth == "Wagon") {
				int newHPEdit = wagonE.HPList.get(0).getHealth() + healthChange;
				wagonE.HPList.get(0).setHealth(newHPEdit);
				isRiskActive = false;
			}
			//human HP
			else if(eventList.get(index).whoseHealth == "Human") {
				int pickHuman = rng.nextInt(4) + 1;
				if(pickHuman == 1 ) { //human1
					int newHPEdit = wagonE.HPList.get(1).getHealth() + healthChange;
					wagonE.HPList.get(1).setHealth(newHPEdit);
					isRiskActive = false;
				}
				else if(pickHuman == 2 ) { //human2
					int newHPEdit = wagonE.HPList.get(2).getHealth() + healthChange;
					wagonE.HPList.get(2).setHealth(newHPEdit);
					isRiskActive = false;
				}
				else if(pickHuman == 3 ) { //human3
					int newHPEdit = wagonE.HPList.get(3).getHealth() + healthChange;
					wagonE.HPList.get(3).setHealth(newHPEdit);
					isRiskActive = false;
				}
				else if(pickHuman == 4 ) { //human4
					int newHPEdit = wagonE.HPList.get(4).getHealth() + healthChange;
					wagonE.HPList.get(4).setHealth(newHPEdit);
					isRiskActive = false;
				}					
			}
			//ox HP
			else if(eventList.get(index).whoseHealth == "Ox") {
				for(int i = 5; i < wagonE.HPList.size(); i++) {
					if(wagonE.HPList.get(i).getAlive() == true) {
						int newHPEdit = wagonE.HPList.get(i).getHealth() + healthChange;
						wagonE.HPList.get(i).setHealth(newHPEdit);
						isRiskActive = false;
						break;
					}
				}
			}
			
		//should be all but add here if not
			
			
		}
		
	}
	
	
	//ARRAYLIST STUFF
	//new eventCheck so fix any classes that used this method
	public void doesEventHappen() {
		//check if Risk is active and decrease number if it is
		if(isRiskActive == true) {
			int die = rng.nextInt(5) + 1;
			//if die lands on 1, then an event occurs
			if (die == 1) {
				int index = pickFromList();
				pickBooleanCheck(index);
				causeEdits(index);
				eventPop(eventList.get(index).event);
			}
		}
		else if (isRiskActive == false) {
			int die = rng.nextInt(10) + 1;
			//if die lands on 1, then an event occurs
			if (die == 1) {
				int index = pickFromList();
				pickBooleanCheck(index);
				causeEdits(index);
				eventPop(eventList.get(index).event);
			}
		}
		
		
	}
	
	/**
	 * brings up a dialog box that lists the event that occurred
	 * @param event - grabs the description of the event to display
	 */
	public void eventPop(String name) {
		String textF = name;
		String titleF = "An event has occured";
		int typeF = JOptionPane.INFORMATION_MESSAGE;
		JOptionPane.showMessageDialog(null, textF, titleF, typeF);

	}
	
	

	//OLD STUFF THAT USED HARD CODING
	//uses rng to randomly check for occurence of random event
    /**
     * performs RNG to determine if an event occurs, and uses methods to select the event if event does occur
     */
	/*
	public void eventCheck() {
		int die = rng.nextInt(10) + 1;
		if (die == 1) {
			eventPop(eventAlert());
		}
	}
	

	//used for testing purposes ONLY
	//hard code this method to whatever event you are trying to test
	public void forceEvent() {
		String event = "Injure Wagon!";
		//System.out.println(event);
		int wagonHP = wagonE.HPList.get(0).getHealth();
		//int newWHP = wagonHP - 5;
		wagonE.HPList.get(0).setHealth(wagonHP - 5);
		System.out.println("Wagon Health: "+ wagonE.HPList.get(0).getHealth());
		
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
			int newWHP = wagonHP - 5;
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
					int newOHP = oxHP - 5;
					wagonE.HPList.get(i).setHealth(newOHP);
				}
			}
			return event;
		}

	}
	 */
	
	

}

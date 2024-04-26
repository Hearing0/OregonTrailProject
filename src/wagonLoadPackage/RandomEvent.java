package wagonLoadPackage;

import java.util.Random;

import javax.swing.JOptionPane;

public class RandomEvent {
	private static Random rng = new java.util.Random();
	
	public RandomEvent(){
		
	}
	
	
	//being rewritten under new eventCheck to make it compatible with program
	//takes total distance and uses a dice like probability counter to determine if event occurs
	//does it in one fell-swoop, so will need to be changed if dynamic miles are done
	/*
	public void activeEvent(int totalDistance) {
		int milesLeft = totalDistance;
		while(milesLeft > 0) {
			int die = rng.nextInt(6) + 1;
			if(die == 1) {
				eventAlert();
				milesLeft = milesLeft - 100;
			}
			else {
				milesLeft = milesLeft - 100;
			}
		}
	}
	*/
	
	//uses rng to randomly check for occurence of random event
	//basic right now to prepare for weather and traveling changes
	//may need to alter the odds to  make it similar to actual game, but its good for testing now
	public void eventCheck(int distanceTraveled) {
		int die = rng.nextInt(10) + 1;
		if(die == 1) {
			//eventAlert();
			eventPop(eventAlert());
		}
		
	}
	
	
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
	
	
	public void eventPop(String event) {
		String textF  = event;
		String titleF = "An event has occured";
		int typeF = JOptionPane.INFORMATION_MESSAGE;
		JOptionPane.showMessageDialog(null, textF, titleF, typeF);
		
	}

	
	

	
	
}

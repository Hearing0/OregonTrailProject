package wagonLoadPackage;

import java.util.Random;

/**
 * River.java
 * Version 1.0 - 4/17/24
 * Created by Cody Dusek
 * 
 * Creates the framework for rivers and river crossings
 * Allows the user to either ford, ferry, or float across the river
 */

public class River extends Location {

	public River(String name, String desc, int disTillNext, boolean hasActs, boolean isRiver, String geotype) {
		super(name, desc, disTillNext, hasActs, isRiver, geotype);
	}

	// creates the necessary arrays for each crossing choice
	final int FORDLENGTH = 10;
	int[] fordConditions = new int[FORDLENGTH];

	final int FLOATLENGTH = 4;
	int[] floatConditions = new int[FLOATLENGTH];

	final int FERRYLENGTH = 4;
	int[] ferryConditions = new int[FERRYLENGTH];

	int disTillNext;

	double depth, width;
	double swiftness;
	double bottom;
	double widthRainVary, depthRainVary, swiftRainVary;
	double waterLevel;

	final int ROCKYBT = 0;
	final int MUDDYBT = 1;
	final int FIRMBT = 2;
	final int OXENNUM = 6;

	boolean isGuide = false;
	boolean hasActs;

	String riverPrompt;

	// contains all of the prompts corresponding to the type of crossing chosen, and
	// the proper one is chosen below
	String[] fordPrompts = { "Muddy crossing, but you did not get stuck.", "Rough crossing, but you did not overturn.",
			"You got stuck; lose one day",
			"You overturned!", "Wagon swamped!", "Wagon and oxen swept away!" };

	String[] floatPrompts = { "Some of your supplies got wet!", "Some of your supplies fell overboard!",
			"Most of your supplies fell overboard, and you got pushed downriver!",
			"Your wagon overturned, and you lost most of your supplies and got pushed far downstream!",
			"You crossed successfully, but lost a day!", "You crossed successfully, but lost two days!",
			"You crossed successfully!" };

	String[] ferryPrompts = { "Some of your supplies fell overboard!", "You crossed successfully!",
			"Some of your supplies fell overboard, and you lost a day!" };

	Random rand = new Random();
	/**
	 * determines the bottom type of the river
	 * 0 - rocky
	 * 1 - muddy
	 * 2 - firm
	 */
	int bottomType = rand.nextInt(3);

	/**
	 * gets the depth of the river
	 * 
	 * @return the depth of the river
	 */
	public double getDepth() {
		return depth;
	}

	/**
	 * gets the width of the river
	 * 
	 * @return the width of the river
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * gets the swiftness, or speed of the river
	 * 
	 * @return the swiftness of the river
	 */
	public double getSwiftness() {
		return swiftness;
	}

	/**
	 * returns a string based on the number for the river bottom generated in
	 * setConditions
	 * 
	 * @return the bottom type of the river
	 */
	public String getBottomType() {
		if (bottomType == 0)
			return "Rocky";
		else if (bottomType == 1)
			return "Muddy";
		else
			return "Firm";
	}

	/**
	 * depending on which method of crossing the river was used, returns a string
	 * displaying what happened
	 * 
	 * @param input - determines which method of crossing the river was used
	 * @return - a string that displays what happened on the crossing
	 */
	public String getPrompt(int input) {
		int promptNum = -1;

		if (input == 0) {
			promptNum = fordConditions[1];
			riverPrompt = fordPrompts[promptNum];
		} else if (input == 1) {
			promptNum = floatConditions[2];
			riverPrompt = floatPrompts[promptNum];
		} else if (input == 2) {
			promptNum = ferryConditions[2];
			riverPrompt = ferryPrompts[promptNum];
		}

		return riverPrompt;
	}

	/**
	 * determines whether or not the user hired a guide to cross
	 * the guide will reduce the players chance of losing supplies by half
	 * 
	 * @param isGuide - used to determine whether or not the user hired a guide
	 */
	public void crossWithGuide(boolean isGuide) {
		this.isGuide = isGuide;
	}

	/**
	 * determines the variation in the various river attributes according to the
	 * weather system
	 */
	private void checkWeather() {
		waterLevel = Weather.getGroundWaterLevel();
		depthRainVary = (waterLevel * 1.25);
		widthRainVary = (waterLevel * 12);
		swiftRainVary = (waterLevel * 0.75);
	}

	/***
	 * sets the conditions for the river, and randomly varies the depth and
	 * swiftness of the river
	 * initial numbers were obtained from "You Have Died of Dysentery" and
	 * geological data for each river
	 * 
	 * @param current - used to determine which river the user is currently at
	 *                TODO add Columbia River maybe
	 * 
	 */
	public void setConditions(Location current) {
		checkWeather();

		int addOrSubtract = rand.nextInt(2); // used to determine whether or not to add or subtract
		double depthVary = rand.nextDouble(4); // used to vary the depth
		double swiftVary = rand.nextDouble(4); // used to vary the swiftness
		double widthVary = rand.nextDouble(10);

		if (waterLevel != 0) {
			depthVary += depthRainVary;
			widthVary += depthRainVary;
			swiftVary += swiftRainVary;
		}

		if (this.name == "Kansas River") { // determines the conditions for the Kansas River
			depth = 6.7;
			width = 628;
			swiftness = 4.8;
			// adds some variation in the river's depth and swiftness, will be based on
			// weather models once that is finished
			if (addOrSubtract == 1) {
				depth += depthVary;
				swiftness += swiftVary;
				width += widthVary;
			} else {
				depth -= depthVary;
				swiftness -= swiftVary;
				width -= widthVary;
			}
		}

		else if (this.name == "Big Blue River") { // determines the conditions for the Big Blue River
			depth = 6.8;
			width = 467;
			swiftness = 6.7;
			if (addOrSubtract == 1) {
				depth += depthVary;
				swiftness += swiftVary;
				width += widthVary;
			} else {
				depth -= depthVary;
				swiftness -= swiftVary;
				width -= widthVary;
			}
		}

		else if (this.name == "Green River") { // determines the conditions for the Green River
			depth = 7.1;
			width = 350;
			swiftness = 5.4;
			if (addOrSubtract == 1) {
				depth += depthVary;
				swiftness += swiftVary;
				width += widthVary;
			} else {
				depth -= depthVary;
				swiftness -= swiftVary;
				width -= widthVary;
				swiftness += swiftVary;
			}
		}

		else if (this.name == "Snake River") {
			depth = 12.25;
			width = 753;
			swiftness = 8.5;
			if (addOrSubtract == 1) {
				depth += depthVary;
				swiftness += swiftVary;
				width += widthVary;
			} else {
				depth -= depthVary;
				swiftness -= swiftVary;
				width -= widthVary;
			}
		}
	}

	/**
	 * if the player chooses to ford the river, determines how well they did
	 * 
	 * @return an array containing several values depending on the conditions
	 *         fordConditions[0] - determines if the wagon crossed, 0 if yes, 1 if
	 *         no
	 *         fordConditions[1] - will choose which prompt to display
	 *         fordConditions[2] - will determine the percent of supplies lost, if
	 *         any
	 *         fordConditions[3] - number of days lost
	 *         fordConditions[4] - Percent health lost of Person #1
	 *         fordConditions[5] - Percent health lost of Person #2
	 *         fordConditions[6] - Percent health lost of Person #3
	 *         fordConditions[7] - Percent health lost of Person #4
	 *         fordConditions[8] - Percent health lost of Person #5
	 *         fordConditions[9] - Amount of oxen lost
	 */
	public int[] fordRiver() {
		int suppliesLost;
		fordConditions[0] = 0;
		if (depth < 4.5) {
			if (bottomType == ROCKYBT) // determines the conditions if the bottom type is rocky
			{
				int rockRand = rand.nextInt(100); // random number generator to determine if the player makes it across
													// with no losses
				int rockTurnCheck = 16;
				if (isGuide) //
					rockTurnCheck /= 2;
				if (rockRand < rockTurnCheck) {
					int rockLost = rand.nextInt(30) + 10; // random number generator to determine the percentage of
															// supplies that the player loses
					fordConditions[2] = rockLost;
					for (int i = 4; i < 9; i++) {
						int isHealthLost = rand.nextInt(10); // random number generator to determine if a party member
																// will lose health
						if (isHealthLost == 1) {
							fordConditions[i] = rand.nextInt(5) + 10; // random number generator to determine how much
																		// health a party member lost
						}
					}
				} else {
					fordConditions[1] = 1;
				}
			} else if (bottomType == MUDDYBT) // determines the conditions if the bottom type is muddy
			{
				int mudStuck = rand.nextInt(10); // random number generator to determine whether or not the wagon gets
													// stuck
				int mudStuckCheck = 4;
				if (isGuide)
					mudStuckCheck /= 2;
				if (mudStuck <= mudStuckCheck) {
					fordConditions[3] = 1;
					// TODO incorporate health element
				} else {
					fordConditions[1] = 0;
				}
			} else if (bottomType == FIRMBT) // determines the conditions if the bottom type is firm
			{
				for (int i = 0; i < FORDLENGTH; i++)
					fordConditions[i] = 0;
			}
		}

		else if (depth > 4.5 && depth < 8) // supplies will be lost in this scenario, but
		{
			fordConditions[1] = 4;
			suppliesLost = rand.nextInt(40) + 50;
			fordConditions[2] = suppliesLost;
			for (int i = 0; i < OXENNUM; i++) {
				int isLost = rand.nextInt(10); // random number generator to determine if an oxen dies in the crossing
												// attempt
				if (isLost < 3) // 20% chance for an oxen to die
					fordConditions[9]++;
			}
		}

		else if (depth > 8) // most dangerous scenario; almost everything will be lost if the player chooses
							// this
		{
			fordConditions[1] = 5;
			fordConditions[2] = 100;
			for (int i = 4; i < 9; i++) {
				int didDrown = rand.nextInt(10); // random number generator to determine if each player died
				if (didDrown < 3) // determines if the player died; 30% chance for each player to drown
				{
					fordConditions[i] = 100;
				}
			}

		}
		return fordConditions;
	}

	/**
	 * returns an integer array with integers representing specific conditions
	 * floatConditions[0] - represents if the wagon crossed successfully, 0 if it
	 * did, 1 if it didnt
	 * floatConditions[1] - represents the percentage of supplies lost, if any
	 * floatConditions[2] - determines which prompt to display, shown in the String
	 * arrays above
	 * floatConditions[3] - determines the amount of days lost
	 * 
	 * @return an integer array representing the various conditions described above
	 */
	public int[] floatRiver() {

		if (swiftness <= 3) {
			floatConditions[0] = 0;
			int slowRand = rand.nextInt(10); // random number generator to determine if the player successfully crosses
			int slowCheck = 4; // difficulty check for the river speed
			if (isGuide) // will decrease the difficulty of crossing by half if a guide is hired
				slowCheck /= 2;
			if (slowRand < slowCheck) {
				floatConditions[1] = rand.nextInt(10); // random number generator to determine the amount of supplies
														// lost
				floatConditions[2] = 0;
			} else
				floatConditions[2] = 6;
		}

		else if (swiftness > 3 && swiftness < 10) {
			floatConditions[0] = 0;
			int medRand = rand.nextInt(10); // random number generator to determine if the player floats successfully
			int medCheck = 6; // difficulty check for the river speed
			if (isGuide)
				medCheck /= 2;
			if (medRand < medCheck) {
				floatConditions[1] = rand.nextInt(10) + 20; // determines the percentage of supplies lost
				floatConditions[2] = 1;
			} else
				floatConditions[2] = 6;
		}

		else if (swiftness >= 10 && swiftness < 12) {
			floatConditions[0] = 0;
			int quickRand = rand.nextInt(10); // random number generator to determine if the player can cross
												// successfully
			int quickCheck = 8; // difficulty check for the quick river speed
			if (isGuide)
				quickCheck /= 2;
			if (quickRand < quickCheck) {
				floatConditions[1] = rand.nextInt(30) + 50; // determines the percentage of supplies lost
				floatConditions[2] = 2;
			} else
				floatConditions[2] = 4;
			floatConditions[3] = 1; // adds a day to the journey
		}

		else if (swiftness >= 12) {
			floatConditions[0] = 0;
			int fastRand = rand.nextInt(10); // random number generator to determine if the player can cross
												// successfully
			int fastCheck = 10; // difficulty check for the fast river speed
			if (isGuide)
				fastCheck /= 2;
			if (fastRand < fastCheck) {
				floatConditions[1] = rand.nextInt(20) + 70; // determines the percentage of supplies lost
				floatConditions[2] = 3;
			} else
				floatConditions[2] = 5;
			floatConditions[3] = 2; // adds two days to the journey
		}

		return floatConditions;
	}

	/**
	 * determines if the player can cross the river via ferry given the conditions
	 * of said river
	 * should be the easiest, but most expensive, option
	 * 
	 * @return an array with integers representing different outcomes from crossing
	 *         ferryConditions[0] - did the wagon make it across, 0 if yes, 1 if no
	 *         ferryConditions[1] - the percentage of supplies lost
	 *         ferryConditions[2] - the prompt to be displayed along with the
	 *         outcome, chosen from String arrays above
	 *         ferryConditions[3] - the amount of days lost (not implemented yet)
	 */
	public int[] ferryRiver() {

		if (swiftness < 8) { // logic for the slow river speed
			ferryConditions[0] = 0;
			int slowRand = rand.nextInt(10); // random number generator to determine if the player crosses
			int slowCheck = 2; // difficulty check for the slow river speed
			if (isGuide)
				slowCheck /= 2;
			if (slowRand < slowCheck) {
				ferryConditions[1] = rand.nextInt(10); // determines the amount of supplies lost
				ferryConditions[2] = 0;
			} else
				ferryConditions[2] = 1;
		}

		else if (swiftness >= 8 && swiftness <= 12) { // logic for the medium river speed
			ferryConditions[0] = 0;
			int medRand = rand.nextInt(10); // random number generator to determine if the player crosses
			int medCheck = 4; // difficulty check for the medium river speed
			if (isGuide)
				medCheck /= 2;
			if (medRand < medCheck) {
				ferryConditions[1] = rand.nextInt(20); // determines the amount of supplies lost
				ferryConditions[2] = 0;
			} else
				ferryConditions[2] = 1;
		}

		else if (swiftness > 12) { // logic for the fast river speed
			ferryConditions[0] = 0;
			int fastRand = rand.nextInt(10); // random number generator to determine if the player crosses
			int fastCheck = 5; // difficulty check for the fast river speed
			if (isGuide)
				fastCheck /= 2;
			if (fastRand < fastCheck) {
				ferryConditions[1] = rand.nextInt(20) + 10; // determines the amount of supplies lost
				ferryConditions[2] = 2;
			} else
				ferryConditions[2] = 1;
		}
		return ferryConditions;
	}

}
package wagonLoadPackage;

import java.util.ArrayList;
import java.util.Random;



public class River extends Location {
	
	public River(String name, String desc, String prompt, int disTillNext, boolean hasActs, boolean isRiver) {
		super(name, desc, prompt, disTillNext, hasActs, isRiver);
	}
	
	//private int date;
	
	final int FORDLENGTH = 10;
	 int[] fordConditions = new int[FORDLENGTH];
	
	final int FLOATLENGTH = 4;
	int[] floatConditions = new int[FLOATLENGTH];
	
	final int FERRYLENGTH = 4;
	int[] ferryConditions = new int[FERRYLENGTH];
	int disTillNext;
	boolean hasActs;

	 double depth, width;
	 double swiftness;
	 double bottom; 
	final int ROCKYBT = 0;
	final int MUDDYBT = 1;
	final int FIRMBT = 2;
	boolean isGuide = false;
	final int OXENNUM = 4;
	boolean isFerry = false;
	 boolean isFord = false;
	 boolean isFloat = false;
	
	 String riverPrompt;
	
	 String[] fordPrompts = { "Muddy crossing, but you did not get stuck.", "Rough crossing, but you did not overturn.", "You got stuck; lose one day",
			"You overturned!", "Wagon swamped!", "Wagon and oxen swept away!"};
	
	String[] floatPrompts = { "Some of your supplies got wet!", "Some of your supplies fell overboard!", "Most of your supplies fell overboard, and you got pushed downriver!",
			"Your wagon overturned, and you lost most of your supplies and got pushed far downstream!", "You crossed successfully, but lost a day!", "You crossed successfully, but lost two days!",
			"You crossed successfully!"};
	
	String[] ferryPrompts = { "Some of your supplies fell overboard!", "You crossed successfully!", "Some of your supplies fell overboard, and you lost a day!"};
	
	Random rand = new Random();
	int bottomType = rand.nextInt(3);
	
	/**
	public River toRiver(Location location)
	{
		name = location.getName();
		desc = location.getDesc();
		prompt = location.getPrompt();
		disTillNext = location.getDistance();
		hasActs = location.getActs();
		isRiver = location.getIsRiver();
		River newRiver = new River(name, desc, prompt, disTillNext, hasActs, isRiver);
		return newRiver;
	}
	*/
	
	public double getDepth() {
		return depth; 
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getSwiftness() {
		return swiftness;
	}
	
	public String getBottomType() {
		if (bottomType == 0)
			return "Rocky";
		else if (bottomType == 1)
			return "Muddy";
		else
			return "Firm";
	}
	
	public String getPrompt(int input) {
		int promptNum = -1;
		if (input == 0)
		{
			promptNum = fordConditions[1];
			riverPrompt = fordPrompts[promptNum];
		}
		else if (input == 1)
		{
			promptNum = floatConditions[2];
			riverPrompt = floatPrompts[promptNum];
		}
		else if (input == 2)
		{
			promptNum = ferryConditions[2];
			riverPrompt = ferryPrompts[promptNum];
		}
		return riverPrompt;
	}
	
	public void crossWithGuide(boolean isGuide)
	{
		this.isGuide = isGuide;
	}
	
	/***
	 * 
	 * @param current
	 * TODO make a more realistic and in-depth model for river depth, width, etc.
	 */
	public void setConditions(Location current)
	{
		double variation = rand.nextDouble(4);
		double swiftnessVary = rand.nextDouble(4);
		int addOrSubtract = rand.nextInt(2);
		if (this.name == "Kansas River")
		{
			depth = 6.7;
			width = 628;
			swiftness = 4.8;
			if (addOrSubtract == 1)
			{
				depth += variation;
				swiftness += swiftnessVary;
			}
			else
			{
				depth -= variation;
				swiftness -= swiftnessVary;
			}
		}
		
		else if (this.name == "Big Blue River")
		{
			depth = 6.8;
			width = 467;
			swiftness = 6.7;
			if (addOrSubtract == 1)
			{
				depth += variation;
				swiftness += swiftnessVary;
			}
			else
			{
				depth -= variation;
				swiftness -= swiftnessVary;
			}
		}
		
		else if (this.name == "Green River")
		{
			depth = 7.1;
			width = 350;
			swiftness = 5.4;
			if (addOrSubtract == 1)
			{
				depth += variation;
				swiftness += swiftnessVary;
			}
			else
			{
				depth -= variation;
				swiftness -= swiftnessVary;
			}	
		}
		
		else if (this.name == "Snake River")
		{
			depth = 12.25;
			width = 753;
			swiftness = 8.5;
			if (addOrSubtract == 1)
			{
				depth += variation;
				swiftness += swiftnessVary;
			}
			else
			{
				depth -= variation;
				swiftness -= swiftnessVary;
			}
		}
	}
	/**
	 * 
	 * @return an array containing several values depending on the conditions
	 * 		fordConditions[0] - determines if the wagon crossed, 0 if yes, 1 if no
	 * 		fordConditions[1] - will choose which prompt to display
	 * 		fordConditions[2] - will determine the percent of supplies lost, if any
	 * 		fordConditions[3] - number of days lost
	 * 		fordConditions[4] - Percent health lost of Person #1
	 * 		fordConditions[5] - Percent health lost of Person #2
	 * 		fordConditions[6] - Percent health lost of Person #3
	 * 		fordConditions[7] - Percent health lost of Person #4
	 * 		fordConditions[8] - Percent health lost of Person #5
	 * 		fordConditions[9] - Amount of oxen lost
	 */
	public int[] fordRiver()
	{
		isFord = true;
		int suppliesLost;
		fordConditions[0] = 0;
		if (depth < 4.5)
		{
			if (bottomType == ROCKYBT)
			{
				int rockRand = rand.nextInt(100);
				int rockTurnCheck = 16;
				if (isGuide)
						rockTurnCheck /= 2;
				if (rockRand < rockTurnCheck)
				{
					int rockLost = rand.nextInt(30) + 10;
					fordConditions[2] = rockLost;
					for (int i = 4; i < 9; i++)
					{
						int isHealthLost = rand.nextInt(10);
						if (isHealthLost == 1)
						{
							fordConditions[i] = rand.nextInt(5) + 10;
						}
					}
				}
				else
				{
					fordConditions[1] = 1;
				}
			}
			else if (bottomType == MUDDYBT)
			{
				int mudStuck = rand.nextInt(10);
				int mudStuckCheck = 4;
				if (isGuide)
					mudStuckCheck /= 2;
				if (mudStuck <= mudStuckCheck)
				{
					fordConditions[3] = 1;
					// TODO incorporate health element
				}
				else
				{
					fordConditions[1] = 0;
				}
			}
			else if (bottomType == FIRMBT)
			{
				for (int i = 0; i < FORDLENGTH; i++)
					fordConditions[i] = 0;
			}
		}
		
		else if (depth > 4.5 && depth < 8)
		{
			fordConditions[1] = 4;
			suppliesLost = rand.nextInt(40) + 50;
			fordConditions[2] = suppliesLost;
			for (int i = 0; i < OXENNUM; i++)
			{
				int isLost = rand.nextInt(10);
				if (isLost < 3)
					fordConditions[9]++;
			}
		}
		
		else if (depth > 8)
		{
			fordConditions[1] = 5;
			fordConditions[2] = 100;
			for (int i = 4; i < 9; i++)
			{
				int didDrown = rand.nextInt(10);
				if (didDrown < 3)
				{
					fordConditions[i] = 100;
				}
			}
			
		}
		return fordConditions;
	}
	
	/**
	 * 
	 * @return
	 */
	public int[] floatRiver()
	{
		isFloat = true;
		
		
		if (swiftness <= 3)
		{
			floatConditions[0] = 0;
			int slowRand = rand.nextInt(10);
			int slowCheck = 4;
			if (isGuide)
				slowCheck /= 2;
			if (slowRand < slowCheck)
			{
				floatConditions[1] = rand.nextInt(10);
				floatConditions[2] = 0;
			}
			else
				floatConditions[2] = 6;
		}
		
		else if (swiftness > 3 && swiftness < 10)
		{
			floatConditions[0] = 0;
			int medRand = rand.nextInt(10);
			int medCheck = 6;
			if (isGuide)
				medCheck /= 2;
			if (medRand < medCheck)
			{
				floatConditions[1] = rand.nextInt(10) + 20;
				floatConditions[2] = 1;
			}
			else
				floatConditions[2] = 6;
		}
		
		else if (swiftness >= 10 && swiftness < 12)
		{
			floatConditions[0] = 0;
			int quickRand = rand.nextInt(10);
			int quickCheck = 8;
			if (isGuide)
				quickCheck /= 2;
			if (quickRand < quickCheck)
			{
				floatConditions[1] = rand.nextInt(30) + 50;
				floatConditions[2] = 2;
			}
			else
				floatConditions[2] = 4;
			floatConditions[3] = 1;
			
		}
		
		else if (swiftness >= 12)
		{
			floatConditions[0] = 0;
			int fastRand = rand.nextInt(10);
			int fastCheck = 10;
			if (isGuide)
				fastCheck /= 2;
			if (fastRand < fastCheck)
			{
				floatConditions[1] = rand.nextInt(20) + 70;
				floatConditions[2] = 3;
			}
			else
				floatConditions[2] = 5;
			floatConditions[3] = 2;
		}
		
		return floatConditions;
		
	}
	
	public int[] ferryRiver()
	{
		isFerry = true;
		if (swiftness < 8)
		{
			ferryConditions[0] = 0;
			int slowRand = rand.nextInt(10);
			int slowCheck = 2;
			if (isGuide)
				slowCheck /= 2;
			if (slowRand < slowCheck)
			{
				ferryConditions[1] = rand.nextInt(10);
				ferryConditions[2] = 0;
			}
			else
				ferryConditions[2] = 1;
		}
		
		else if (swiftness >= 8 && swiftness <= 12)
		{
			ferryConditions[0] = 0;
			int medRand = rand.nextInt(10);
			int medCheck = 4;
			if (isGuide)
				medCheck /= 2;
			if (medRand < medCheck)
			{
				ferryConditions[1] = rand.nextInt(20);
				ferryConditions[2] = 0;
			}
			else
				ferryConditions[2] = 1;
		}
		
		else if (swiftness > 12)
		{
			ferryConditions[0] = 0;
			int fastRand = rand.nextInt(10);
			int fastCheck = 6;
			if (isGuide)
			{
				fastCheck /= 2;
			}
			if (fastRand < fastCheck)
			{
				ferryConditions[1] = rand.nextInt(20) + 10;
				ferryConditions[2] = 2;
			}
			else
				ferryConditions[2] = 1;
		}
		return ferryConditions;
	}
	
	
	
	
}
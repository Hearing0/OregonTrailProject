package wagonLoadPackage;

import java.util.ArrayList;
import java.util.Random;



public class River extends Location {
	
	public River(String name, String desc, String prompt, int disTillNext, boolean hasActs) {
		super(name, desc, prompt, disTillNext, hasActs);
	}
	
	//private int date;

	double depth, width, swiftness, bottom; 

	/*
	public boolean crossWithGuide()
	{
		
	}
	*/
	
	/***
	 * 
	 * @param current
	 * TODO make a more realistic and in-depth model for river depth, width, etc.
	 */
	private void setConditions(Location current)
	{
		Random rand = new Random();
		double variation = rand.nextDouble(4);
		int addOrSubtract = rand.nextInt(2);
		if (this.name == "Kansas River")
		{
			depth = 6.7;
			width = 628;
			if (addOrSubtract == 1)
				depth += variation;
			else
				depth -= variation;
		}
		
		else if (this.name == "Big Blue River")
		{
			depth = 6.8;
			width = 467;
			if (addOrSubtract == 1)
				depth += variation;
			else
				depth -= variation;
		}
		
		else if (this.name == "Green River")
		{
			depth = 7.1;
			width = 350;
			if (addOrSubtract == 1)
				depth += variation;
			else
				depth -= variation;
		}
		
		else if (this.name == "Snake River")
		{
			depth = 12.25;
			width = 753;
			if (addOrSubtract == 1)
				depth += variation;
			else
				depth -= variation;
		}
	}
	
	//public int fordRiver()
	{
		
	}
	
	//public int floatRiver()
	{
		
	}
	
	//public int ferryRiver()
	{
		
	}
	
	
}
package wagonLoadPackage;

import java.util.Random;

/**
 * Weather.java
 * Version 1.0 - April 30, 2024
 * Author - Cody Dusek
 * 
 * Creates a weather system that is dependent on six different locations listed
 * below, and contains the average monthly rainfall in inches, and the average
 * monthly temperature in Fahrenheit. Determines if it will rain, and if it
 * will, if it is rain or snow, and the temperature for a specific day
 */

public class Weather extends Date {

	boolean willSnow = false; // determines if any precipitation will be snow
	boolean willRain = false; // determines if it will rain on a certain day
	boolean isDrought = false; // determines if the conditions are correct for a drought
	boolean isSevere = false; // determines if the drought is severe or not
	boolean isHot = false; // true if the temperature is "Very Hot", "Hot", or "Warm"
	boolean isCold = false; // true if the temperature is "Very Cold", "Cold", or "Cool"
	boolean isRainy = false; // true if the precipitation is 0.2in for rain, or 2in for snow
	boolean isVeryRainy = false; // true if the precipitation is 0.8in for rain, or 8in for snow

	double rainLevel = 0; // determines the amount of rain that would fall on a given day
	double snowLevel = 0; // determines the amount of snow that would fall on a given day
	double groundWaterLevel; // determines how much water is actually on the ground
	double groundSnowLevel; // determines how much snow is actually on the ground

	int wagonLocale = getWagonLocation(); // used to determine which weather zone the player is in
	int location = parseLocation(); // determines which weather zone the player is in
	int daysConsistent = 0; // determines how long the weather has stayed the same

	String rainResults; // the descriptor for the rain conditions
	String tempResults; // the descriptor for the temperature conditions

	Random rand = new Random();

	// average temperature in Fahrenheit per month for each location zone
	int[][] averageTemps = {
			{ 30, 34, 45, 56, 65, 75, 80, 78, 70, 58, 45, 33 }, // Kansas City, MO
			{ 24, 28, 38, 48, 58, 68, 74, 72, 62, 49, 35, 25 }, // North Platte, NE
			{ 27, 27, 37, 44, 54, 65, 73, 70, 60, 47, 37, 27 }, // Casper, WY
			{ 17, 23, 34, 42, 51, 61, 69, 66, 56, 44, 31, 19 }, // Lander, WY
			{ 30, 36, 44, 51, 59, 67, 75, 74, 65, 53, 40, 31 }, // Boise, ID
			{ 42, 44, 48, 52, 58, 62, 68, 69, 64, 55, 47, 41 }, // Portland, OR
	};

	// average rainfall in inches for each location zone
	double[][] averageRain = {
			{ 1.14, 1.48, 2.15, 3.71, 5.13, 5.52, 3.97, 4.39, 4.16, 3.52, 2.14, 1.75 }, // Kansas City, MO
			{ 0.33, 0.50, 1.12, 2.18, 3.35, 3.69, 2.86, 2.39, 1.58, 1.70, 0.69, 0.41 }, // North Platte, NE
			{ 0.51, 0.57, 0.82, 1.29, 2.02, 1.61, 1.41, 0.85, 1.08, 1.11, 0.76, 0.49 }, // Casper, WY
			{ 0.41, 0.58, 1.16, 1.87, 2.20, 1.27, 0.78, 0.61, 1.05, 1.29, 0.86, 0.58 }, // Lander, WY
			{ 1.24, 0.99, 1.39, 1.23, 1.39, 0.69, 0.33, 0.24, 0.58, 0.75, 1.35, 1.55 }, // Boise, ID
			{ 4.88, 3.66, 3.68, 2.73, 2.47, 1.70, 0.65, 0.67, 1.47, 3.00, 5.63, 5.49 }, // Portland, OR
	};

	/**
	 * determines the weather zone that the player is in using the wagonLocation
	 * from Travel
	 * 
	 * @return - an integer representing the weather zone that the player is in
	 */
	private int parseLocation() {
		int locationNum = -1; // set to an unrealistic number for testing
		if (wagonLocale >= 0 && wagonLocale < 3)
			locationNum = 0;

		else if (wagonLocale >= 3 && wagonLocale < 5)
			locationNum = 1;

		else if (wagonLocale >= 5 && wagonLocale < 6)
			locationNum = 2;

		else if (wagonLocale >= 6 && wagonLocale < 11)
			locationNum = 3;

		else if (wagonLocale >= 11 && wagonLocale < 16)
			locationNum = 4;

		else if (wagonLocale >= 16 && wagonLocale <= 19)
			locationNum = 5;

		return locationNum;
	}

	/**
	 * determines the temperature for a given zone and month, 
	 */
	private void tempCheck() {
		isCold = false;
		isHot = false;
		int finalTemp = 0;
		int temp = averageTemps[location][month];
		int tempVary = rand.nextInt(0, 21);
		boolean addOrSubtract = rand.nextBoolean();
		
		if(addOrSubtract)
			finalTemp = temp + tempVary;
		else
			finalTemp = temp - tempVary;

		if (finalTemp > 90) {
			tempResults = "Very hot";
			isHot = true;
		} else if (finalTemp > 70) {
			tempResults = "Hot";
			isHot = true;
		} else if (finalTemp > 50) {
			tempResults = "Warm";
			isHot = true;
		} else if (finalTemp > 30) {
			tempResults = "Cool";
			isCold = true;
		} else if (finalTemp > 10) {
			tempResults = "Cold";
			isCold = true;
			willSnow = true;
		} else if (finalTemp < 10) {
			tempResults = "Very Cold";
			willSnow = true;
		} else
			tempResults = "error";
	}

	private void rainCheck() {
		double avgRain = averageRain[location][month];
		double rainVary = rand.nextDouble(0, 0.5);
		double finalRain = 0;
		boolean addOrSubtract = rand.nextBoolean();
		
		if (addOrSubtract)
			finalRain = avgRain + rainVary;
		else
			finalRain = avgRain - rainVary;
		
		double rainRand = rand.nextDouble(8);

		isRainy = false;
		isVeryRainy = false;
		willRain = false;
		willSnow = false;

		if (rainRand < finalRain)
			willRain = true;

		int rainAmount = rand.nextInt(10);
		if (willSnow) {
			if (rainAmount < 3) {
				snowLevel = 8;
				isVeryRainy = true;
			} else {
				snowLevel = 3;
				isRainy = true;
			}
			groundSnowLevel += snowLevel;
		} else if (willRain) {
			if (rainAmount < 3) {
				rainLevel = 0.8;
				isVeryRainy = true;
			} else {
				rainLevel = 0.2;
				isRainy = true;
			}
			groundWaterLevel += rainLevel;
		}
		getRain();
	}

	private void getRain() {
		if (willRain) {
			if (willSnow) {
				if (rainLevel == 0.8)
					rainResults = "Very snowy";
				else if (rainLevel == 0.2)
					rainResults = "Snowy";
			} else {
				if (rainLevel == 0.8)
					rainResults = "Very rainy";
				else if (rainLevel == 0.2)
					rainResults = "Rainy";
			}
		} else
			rainResults = "";
	}

	private void rainLevelCheck() {
		if (willRain) {
			groundWaterLevel -= (0.1 * groundWaterLevel);
			groundWaterLevel += rainLevel;
			if (groundWaterLevel < 0.2)
				isDrought = true;
			if (groundWaterLevel < 0.1)
				isSevere = true;
		}
	}

	private void snowLevelCheck() {
		if (willSnow) {
			groundSnowLevel += snowLevel;
			if (isCold) {
				if (isVeryRainy == false) {
					groundSnowLevel -= (0.03 * groundSnowLevel);
				}
			} else if (isHot) {
				if (isRainy == false) {
					groundSnowLevel -= 5;
					groundWaterLevel += 0.5;
				}
			}
		}

		if (groundSnowLevel < 0)
			groundSnowLevel = 0;
	}

	public void getNewWeather() {
		int getNew = rand.nextInt(2);
		if (getNew == 1) {
			rainCheck();
			tempCheck();
			rainLevelCheck();
			snowLevelCheck();
			daysConsistent = 0;
		} else
			daysConsistent++;
	}

	// Getters

	public String getRainResults() {
		return rainResults;
	}

	public String getTempResults() {
		return tempResults;
	}

	public double getGroundWaterLevel() {
		return groundWaterLevel;
	}

	public double getGroundSnowLevel() {
		return groundSnowLevel;
	}

	public boolean getIsRainy() {
		return isRainy;
	}

	public boolean getIsVeryRainy() {
		return isVeryRainy;
	}

	public boolean getIsDrought() {
		return isDrought;
	}

	public boolean getIsSevere() {
		return isSevere;
	}

	public int getDaysConsistent() {
		return daysConsistent;
	}
}

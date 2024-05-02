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
	static boolean isDrought = false; // determines if the conditions are correct for a drought
	static boolean isSevere = false; // determines if the drought is severe or not
	boolean isHot = false; // true if the temperature is "Very Hot", "Hot", or "Warm"
	boolean isCold = false; // true if the temperature is "Very Cold", "Cold", or "Cool"
	static boolean isRainy = false; // true if the precipitation is 0.2in for rain, or 2in for snow
	static boolean isVeryRainy = false; // true if the precipitation is 0.8in for rain, or 8in for snow

	double rainLevel = 0; // determines the amount of rain that would fall on a given day
	double snowLevel = 0; // determines the amount of snow that would fall on a given day
	static double groundWaterLevel; // determines how much water is actually on the ground
	static double groundSnowLevel; // determines how much snow is actually on the ground

	int wagonLocale = getWagonLocation(); // used to determine which weather zone the player is in
	int location; // determines which weather zone the player is in
	static int daysConsistent = 0; // determines how long the weather has stayed the same

	static String rainResults; // the descriptor for the rain conditions
	static String tempResults; // the descriptor for the temperature conditions

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
	private void parseLocation() {
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

		location = locationNum;
	}

	/**
	 * determines the temperature for a given zone and month, the corresponding
	 * tag to go along with it, and determines that if there is any precipitation,
	 * whether it will be snow or rain
	 */
	private void tempCheck() {
		// resets isCold and isHot before determining them again
		isCold = false;
		isHot = false;
		int finalTemp = 0;
		int temp = averageTemps[location][month]; // grabs the correct average temperature from the array above
		int tempVary = rand.nextInt(0, 21); // generates a random number between 0 and 20, inclusive, that will be added
											// or subtracted to the actual temperature value
		boolean addOrSubtract = rand.nextBoolean(); // determines whether the random number will be added or subtracted
		if (addOrSubtract)
			finalTemp = temp + tempVary;
		else
			finalTemp = temp - tempVary;

		if (finalTemp > 90) { // sets the tag to Very Hot if the temperature is above 90F
			tempResults = "Very hot";
			isHot = true;
		} else if (finalTemp > 70) { // sets the tag to Hot if the temperature is above 70F but less than 90F
			tempResults = "Hot";
			isHot = true;
		} else if (finalTemp > 50) { // sets the tag to Warm if the temperative is above 50F but less than 70F
			tempResults = "Warm";
			isHot = true;
		} else if (finalTemp > 30) { // sets the tag to Cool if the temperature is above 30F but less than 50F
			tempResults = "Cool";
			isCold = true;
		} else if (finalTemp > 10) { // sets the tag to Cold if the temperature is above 10F but less than 30F
			tempResults = "Cold";
			isCold = true;
			willSnow = true;
		} else if (finalTemp < 10) { // sets the tag to Very Cold if the temperature is less than 10F
			tempResults = "Very Cold";
			isCold = true;
			willSnow = true;
		} else // displays an error in case all of the previous checks failed
			tempResults = "error";
	}

	/**
	 * determines if it will rain on a given day in a given month and weather zone
	 * also checks if the precipitation will be rain or snow
	 */
	private void rainCheck() {
		double avgRain = averageRain[location][month]; // grabs the rain data for the current weather zone and month
		double rainVary = rand.nextDouble(0, 0.51); // creates a random number to add or subtract to the rain level
		double finalRain = 0;
		boolean addOrSubtract = rand.nextBoolean(); // determines whether to add or subtract the random number

		if (addOrSubtract)
			finalRain = avgRain + rainVary;
		else
			finalRain = avgRain - rainVary;

		double rainRand = rand.nextDouble(8); // generates a random number that will determine if it rains or not

		// resets every boolean before reassignment
		isRainy = false;
		isVeryRainy = false;
		willRain = false;
		willSnow = false;
		// if the random number generated is less than the average rain provided above,
		// it will rain on that day
		if (rainRand < finalRain)
			willRain = true;

		int rainAmount = rand.nextInt(10); // generates a random number to determine the amount of rain or snow that
											// will fall
		if (willSnow) { // conditions for snow
			if (rainAmount < 3) { // provides a 30% chance for heavy snow to occur
				snowLevel = 8; // adds 8 inches of snow to the ground
				isVeryRainy = true;
			} else { // provides a 70% chance for normal snow to occur
				snowLevel = 2; // adds 2 inches of snow to the ground
				isRainy = true;
			}

		} else if (willRain) { // conditions for rain
			if (rainAmount < 3) { // provies a 30% chance for heavy rain to occur
				rainLevel = 0.8; // adds 0.8 inches of water to the ground
				isVeryRainy = true;
			} else { // provides a 70% chance for normal rain to occur
				rainLevel = 0.2; // adds 0.2 inches of water to the ground
				isRainy = true;
			}

		}
		getRain(); // generates the tags for the precipitation conditions
	}

	/**
	 * generates the tag for the rain condition based on the rain level, and if it
	 * is snow or rain
	 */
	private void getRain() {
		if (willRain) {
			if (willSnow) { // tags for snow
				if (snowLevel == 8)
					rainResults = "Very snowy"; // sets the tag to Very Snowy if the snow level is 8 in
				else if (snowLevel == 2)
					rainResults = "Snowy"; // sets the tag to Snowy if the snow level is 2 in
			} else { // tags for rain
				if (rainLevel == 0.8)
					rainResults = "Very rainy"; // sets the tag to Very Rainy if the rain level is 0.8 in
				else if (rainLevel == 0.2)
					rainResults = "Rainy"; // sets the tag to Rainy if the rain level is 0.2 in
			}
		} else
			rainResults = "";
	}

	/**
	 * adds the rain level to the current level of water on the ground, and
	 * determines if a drought is happening, and its severity
	 */
	private void rainLevelCheck() {
		if (willRain) {
			groundWaterLevel -= (0.1 * groundWaterLevel); // removes 10% of the groundwater per day
			groundWaterLevel += rainLevel; // adds the current amount of water to the overall level of water
			if (groundWaterLevel < 0.2) // if the groundwater level is less than 0.2, there will be a drought
				isDrought = true;
			if (groundWaterLevel < 0.1) // if the groundwater level is less than 0.1, the drought will be severe
				isSevere = true;
		}
	}

	/**
	 * adds the current amount of snow to the level of snow already on the ground,
	 * and determines how much snow is melted given the conditions
	 */
	private void snowLevelCheck() {
		if (willSnow) {
			groundSnowLevel += snowLevel; // adds current layer of snow to the ground snow level
			// if the weather is cold, and it is not raining hard, 3% of the snow will melt
			// per day
			if (isCold) {
				if (isVeryRainy == false) {
					groundSnowLevel -= (0.03 * groundSnowLevel);
				}
				// if the weather is hot, and it is not raining, 5 inches of snow will melt per
				// day
			} else if (isHot) {
				if (isRainy == false) {
					groundSnowLevel -= 5;
					groundWaterLevel += 0.5;
				}
			}
		}

		if (groundSnowLevel < 0) // ensures that the level of snow is never negative
			groundSnowLevel = 0;
	}

	/**
	 * generates new weather for a day, with a 50% chance to not change from the
	 * previous day
	 */
	public void getNewWeather() {
		int getNew = rand.nextInt(2);
		if (getNew == 1) {
			parseLocation();
			tempCheck();
			rainCheck();
			rainLevelCheck();
			snowLevelCheck();
			daysConsistent = 0;
		} else
			daysConsistent++;
	}

	// Getters

	public static String getRainResults() {
		return rainResults;
	}

	public static String getTempResults() {
		return tempResults;
	}

	public static double getGroundWaterLevel() {
		return groundWaterLevel;
	}

	public static double getGroundSnowLevel() {
		return groundSnowLevel;
	}

	public static boolean getIsRainy() {
		return isRainy;
	}

	public static boolean getIsVeryRainy() {
		return isVeryRainy;
	}

	public static boolean getIsDrought() {
		return isDrought;
	}

	public static boolean getIsSevere() {
		return isSevere;
	}

	public static int getDaysConsistent() {
		return daysConsistent;
	}
}

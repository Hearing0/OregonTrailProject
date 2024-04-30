package wagonLoadPackage;

import java.util.Random;

public class Weather extends Date {
	double avgTemp;
	double avgPrecip;
	String tempTag = "";
	Location current = getCurLocation();
	Random rand = new Random();
	String name = current.getName();
	int wagonLocale = getWagonLocation();
	int location = parseLocation();
	boolean willSnow = false;
	boolean willRain = false;
	boolean isDrought = false;
	boolean isSevere = false;
	double rainLevel = 0;
	double groundWaterLevel;
	String rainResults;
	String tempResults;

	// average temperature in Fahrenheit per month for each location zone
	// first number is columns, second is rows
	int[][] averageTemps = {
			{ 30, 34, 45, 56, 65, 75, 80, 78, 70, 58, 45, 33 }, // Kansas City, MO
			{ 24, 28, 38, 48, 58, 68, 74, 72, 62, 49, 35, 25 }, // North Platte, NE
			{ 27, 27, 37, 44, 54, 65, 73, 70, 60, 47, 37, 27 }, // Casper, WY
			{ 17, 23, 34, 42, 51, 61, 69, 66, 56, 44, 31, 19 }, // Lander, WY
			{ 30, 36, 44, 51, 59, 67, 75, 74, 65, 53, 40, 31 }, // Boise, ID
			{ 42, 44, 48, 52, 58, 62, 68, 69, 64, 55, 47, 41 } // Portland, OR
	};
	// average rainfall in inches for each location zone
	double[][] averageRain = {
			{ 1.14, 1.48, 2.15, 3.71, 5.13, 5.52, 3.97, 4.39, 4.16, 3.52, 2.14, 1.75 }, // Kansas City, MO
			{ 0.33, 0.50, 1.12, 2.18, 3.35, 3.69, 2.86, 2.39, 1.58, 1.70, 0.69, 0.41 }, // North Platte, NE
			{ 0.51, 0.57, 0.82, 1.29, 2.02, 1.61, 1.41, 0.85, 1.08, 1.11, 0.76, 0.49 }, // Casper, WY
			{ 0.41, 0.58, 1.16, 1.87, 2.20, 1.27, 0.78, 0.61, 1.05, 1.29, 0.86, 0.58 }, // Lander, WY
			{ 1.24, 0.99, 1.39, 1.23, 1.39, 0.69, 0.33, 0.24, 0.58, 0.75, 1.35, 1.55 }, // Boise, ID
			{ 4.88, 3.66, 3.68, 2.73, 2.47, 1.70, 0.65, 0.67, 1.47, 3.00, 5.63, 5.49 } // Portland, OR
	};

	private int parseLocation() {
		int locationNum = -1;
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

	private void tempCheck() {
		int temp = averageTemps[location][month];
		int tempVary = rand.nextInt(-20, 20);
		int finalTemp = temp + tempVary;

		if (finalTemp > 90)
			tempResults = "Very hot";
		else if (finalTemp > 70)
			tempResults = "Hot";
		else if (finalTemp > 50)
			tempResults = "Warm";
		else if (finalTemp > 30)
			tempResults = "Cool";
		else if (finalTemp > 10) {
			tempResults = "Cold";
			willSnow = true;
		} else if (finalTemp < 10) {
			tempResults = "Very Cold";
			willSnow = true;
		} else
			tempResults = "error";
	}

	private void rainCheck() {
		double avgRain = averageRain[location][month];
		double rainVary = rand.nextDouble(-0.5, 0.5);
		double finalRain = avgRain + rainVary;
		double rainRand = rand.nextDouble(8);

		if (rainRand < finalRain)
			willRain = true;

		if (willRain) {
			int rainAmount = rand.nextInt(10);

			if (rainAmount < 3)
				rainLevel = 0.8;
			else
				rainLevel = 0.2;
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
			groundWaterLevel *= 0.9;
			groundWaterLevel += rainLevel;
		}
		if (groundWaterLevel < 0.2)
			isDrought = true;
		if (groundWaterLevel < 0.1)
			isSevere = true;
	}

	public void getNewWeather() {
		int getNew = rand.nextInt(2);
		if (getNew == 1) {
			rainCheck();
			tempCheck();
			rainLevelCheck();
		}
	}
}

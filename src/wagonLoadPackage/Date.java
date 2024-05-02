package wagonLoadPackage;

/**
 * Data.java
 * Version 1.0 - April 28th, 2024
 * Author - Cody Dusek
 * 
 * Simulates time passing as the Oregon Trail is traveled
 */
public class Date extends Travel {

	// array to list the names of the months in order
	String[] months = { "January", "February", "March", "April", "May",
			"June", "July", "August", "September", "October", "November", "December" };

	// array to list the number of days in each month in order
	int[] numOfDays = { 30, 27, 30, 29, 30, 29, 30, 30, 29, 30, 30, 30 };

	// date variables for month, day, and year - can be preset if necessary
	static int month = 0;
	int day = 0;
	int year = 1848;

	/**
	 * checks if the day value is higher than the actual number of days in a month,
	 * and increments month and resets day if 0
	 * also does a similar function for the relationship between months and years
	 */
	private void setDate() {
		if (day > numOfDays[month]) {
			month++;
			day = 0;
		}
		if (month > 11) {
			month = 0;
			year++;
		}
	}

	/**
	 * compiles the full date as a String, in the order of month, day, year
	 * 
	 * @return - a String consisting of the current date
	 */
	public String getDate() {
		String suffix = "";
		String date = months[month];
		int displayDay = day + 1;

		if (displayDay > 30)
			suffix = "st";
		else if (displayDay > 20) {
			if (displayDay == 21)
				suffix = "st";
			else if (displayDay == 22)
				suffix = "nd";
			else if (displayDay == 23)
				suffix = "rd";
			else
				suffix = "th";
		} else if (displayDay == 1)
			suffix = "st";
		else if (displayDay == 2)
			suffix = "nd";
		else if (displayDay == 3)
			suffix = "rd";
		else
			suffix = "th";

		date += " " + displayDay + suffix + ", " + year;
		return date;
	}

	/**
	 * increases the day number
	 */
	public void increaseDays() {
		day++;
		setDate();
	}

	/**
	 * sets the month value, planning to be used mainly for when the player begins
	 * their journey
	 * 
	 * @param month - the month value as a integer, with January being 0 and
	 *              December being 11
	 */
	public void setMonth(int month) {
		Date.month = month;
	}
}
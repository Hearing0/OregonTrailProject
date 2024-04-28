package wagonLoadPackage;

public class Date extends Travel {

	String[] months = { "January", "February", "March", "April", "May",
			"June", "July", "August", "September", "October", "November", "December" };
	int month = 2, day = 0, year = 1848;
	int[] numOfDays = {31,28,31,30,31,30,31,31,30,31,31,31};

	/**
	 * determines an actual day value based on the month the user chose to start in,
	 * and will allow for semi-accurate weather data once implemented
	 * 
	 * @param month - the month that the user starts in
	 */
	public void setMonth(String monthIn) {
		if (monthIn == "March") {
			this.month = 2;
			this.day = 0;
		} else if (monthIn == "April") {
			this.month = 3;
			this.day = 0;
		} else if (monthIn == "May") {
			this.month = 4;
			this.day = 0;
		} else if (monthIn == "June") {
			this.month = 5;
			this.day = 0;
		} else if (monthIn == "July") {
			this.month = 6;
			this.day = 0;
		}
	}

	public String getMonth() {
		String currentMonth = months[month];
		return currentMonth;
	}

	private void setDate() {
		/**
		if (month == 2 || month == 4 || month == 6 || month == 7 || month == 9
				|| month == 11 || month == 0) {
			if (day > 30) {
				month++;
				day = 0;
			}
		} else if (month == 1) {
			if (day > 27) {
				month++;
				day = 0;
			}
		} else {
			if (day > 29) {
				month++;
				day = 0;
			}
		}

		if (month > 11) {
			month = 0;
			year++;
		}
	}
	*/
		if (day > numOfDays[month])
		{
			month++;
			day = 0;
		}
		
		if (month > 11)
		{
			month = 0;
			year++;
		}
	}

	public String getDate() {
		String date = months[month];
		int displayDay = day + 1;
		date += " " + displayDay + ", " + year;
		/**
		 * TODO implement this later, logic will just take some time
		 * if (displayDay == 1)
		 * {
		 * date += " " + displayDay + "st, ";
		 * }
		 * else if (displayDay == 2)
		 * {
		 * date += " " + displayDay + "nd, ";
		 * }
		 * else if (displayDay == 3)
		 * {
		 * date += " " + displayDay + "rd, ";
		 * }
		 * date += year;
		 */
		System.out.println("Date: " + date);
		return date;
	}

	public void increaseDays() {
		day += 5;
		setDate();
	}
	
	public void setMonth(int month)
	{
		this.month = month;
	}
}
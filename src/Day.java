import java.time.*;

/**
 * Day class represents a date. 
 */
public class Day {
	int year, month, day;
	private static final int GREGORIAN_START_YEAR = 1582;

	public Day() {
		LocalDate currentTime = LocalDate.now();
		this.year = currentTime.getYear();
		this.month = currentTime.getMonthValue();
		this.day = currentTime.getDayOfMonth();
	}

	public Day(int year, int month, int day)
	{
		this.year = year;
		this.month = month;
		this.day = day;
	}

	/**
	 * Sets the year.
	 * @param year
	 */
	public void setYear(int year)
	{
		if(year > 0)
			this.year = year;
		else
			System.out.println("Invalid year argument.");
	}

	public int getYear()
	{
		return year;
	}

	/**
	 * Sets the month.
	 * @param month
	 */
	public void setMonth(int month)
	{
		if(month > 0 && month < 13)
			this.month = month;
		else
			System.out.println("Invalid month argument.");
	}

	/**
	 * Gets the int value of the current month.
	 * @return
	 */
	public int getMonthValue()
	{
		return month;
	}

	/**
	 * Converts int month to associated String name.
	 * @return
	 */
	public String getMonthString() {
		switch(month) {
		case 1: return "January";
		case 2: return "February";
		case 3: return "March";
		case 4: return "April";
		case 5: return "May";
		case 6: return "June";
		case 7: return "July";
		case 8: return "August";
		case 9: return "September";
		case 10: return "October";
		case 11: return "November";
		case 12: return "December";
		}
		return null;
	}

	/**
	 * Sets the day.
	 * @param day
	 */
	public void setDayOfMonth(int day)
	{
		if(day > 0 && day < 32)
			this.day = day;
		else
			System.out.println("Invalid day argument.");
	}

	public int getDayOfMonth()
	{
		return day;
	}

	/**
	 * Tests if a year is a leap year
	 * @param y the year
	 * @return true if y is a leap year
	 */
	private static boolean isLeapYear(int y)
	{
		if (y % 4 != 0) return false;
		if (y < GREGORIAN_START_YEAR) return true;
		return (y % 100 != 0) || (y % 400 == 0);
	}


	public String toString() {
		return month + "/" + day + "/" + year;
	}

	/**
	 * Checks if one day equals another day.
	 */
	public boolean equals(Object anotherDay){
		if (anotherDay instanceof Day)
			return ((Day) anotherDay).day == day && ((Day) anotherDay).month == month && ((Day) anotherDay).year == year;
		return false;
	}

	/**
	 * Gets the hashcode.
	 */
	public int hashCode(){
		return toString().hashCode();
	}
}

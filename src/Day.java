import java.time.*;

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
	
	public void setMonth(int month)
	{
		if(month > 0 && month < 13)
			this.month = month;
		else
			System.out.println("Invalid month argument.");
	}

	public int getMonthValue()
	{
		return month;
	}
	
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
}

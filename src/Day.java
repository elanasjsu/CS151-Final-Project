import java.time.*;

public class Day {
	int year, month, day;
	
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
	
	public String toString() {
		return month + "/" + day + "/" + year;
	}
}

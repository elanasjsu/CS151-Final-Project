/**
 * DaySet is the model used to hold a hashtable of Days and todolists.
 * Each Day has a day/month/year + a ToDoList Associated with it.
 */
import java.util.*;
import javax.swing.event.*;

public class DateSet
{
	private Hashtable<Day, ToDoList> table;
	private ArrayList<ChangeListener> listeners;
	private Day selectedEntry; //indicates the Day for which todolist should be displayed
	private int selectedMonth; //indicates the month for which month's todolist should be displayed
	public int currentYear;
	public int currentMonth;

	public DateSet() {
		table = new Hashtable<Day, ToDoList>();
		listeners = new ArrayList<>();
		setEmptySelectedEntry();
	}
	
	public void setEmptySelectedEntry() {
		selectedEntry = new Day();
		add(selectedEntry);
		System.out.println("Initialized an empty list for today's Day.");
	}

	/**
	 * Sets the selected DayItem from the Calendar.
	 * @param Day
	 */
	public void selectDay(Day Day) {
		selectedEntry = Day;

		if (!table.containsKey(selectedEntry))
			add(selectedEntry);

		// Notify all observers of the change to the dataset
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener listener : listeners)
			listener.stateChanged(event);
	}

	/**
	 * Retrieves the selected DayItem for ToDoListFrame to use. 
	 * @return
	 */
	public Day getSelectedDay() {
		return selectedEntry;
	}

	public ToDoList getSelectedList() {
		return table.get(selectedEntry);
	}

	/**
	 * Sets the month when user wants to display a month's todolist
	 * @param i
	 */
	public void setSelectedMonth(int i) {
		if(i > 0 && i < 13)
			selectedMonth = i;
		else
			System.out.println("Enter a month between 1..12");
	}

	public int getSelectedMonth() {
		return selectedMonth;
	}

	/**
	 * Add a new item to table Hashtable
	 * @param Day
	 */
	public void add(Day Day) {
		table.put(Day, new ToDoList());
	}

	/**
	 * Replace value of existing item
	 * @param Day
	 * @param list
	 */
	public void upDayList(Day Day, ToDoList list) {
		table.replace(Day, list);	

		// Notify all observers of the change to the dataset
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener listener : listeners)
			listener.stateChanged(event);
	}

	/**
	 * Check if item exists in table Hashtable
	 * @param Day
	 * @return
	 */
	public Boolean containsDay(Day Day) {
		return table.containsKey(Day);
	}

	/**
	 * Returns item at index
	 * @param Day
	 * @return
	 */
	public ToDoList getList(Day Day) {
		return table.get(Day);
	}

	/**
	 * Returns size of table Hashtable
	 * @return
	 */
	public int getSize() {
		return table.size();
	}

	/**
	 * Adds change listeners to each item
	 * @param listener
	 */
	public void addChangeListener(ChangeListener listener) {
		listeners.add(listener);
	}

	/**
	 * Returns table Hashtable
	 * @return
	 */
	public Hashtable<Day, ToDoList> getTable() {
		return table;
	}

	/**
	 * String version of table Hashtable
	 */
	public String toString() {
		return table.toString();
	}
}








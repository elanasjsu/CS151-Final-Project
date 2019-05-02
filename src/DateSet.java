/**
 * DateSet is the model used to hold a hashtable of dates and todolists.
 * Each date has a day/month/year + a ToDoList Associated with it.
 */
import java.util.*;
import javax.swing.event.*;

public class DateSet
{
	private Hashtable<Date, ToDoList> table;
	private ArrayList<ChangeListener> listeners;
	private Date selectedEntry; //indicates the date for which todolist should be displayed
	private int selectedMonth; //indicates the month for which month's todolist should be displayed
	public int currentYear;
	public int currentMonth;

	public DateSet() {
		table = new Hashtable<Date, ToDoList>();
		listeners = new ArrayList<>();
	}

	/**
	 * Sets the selected DateItem from the Calendar.
	 * @param item
	 */
	public void selectDate(Date date) {
		selectedEntry = date;
	}

	/**
	 * Retrieves the selected DateItem for ToDoListFrame to use. 
	 * @return
	 */
	public Date getSelectedDate() {
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
	 * @param date
	 */
	public void add(Date date) {
		table.put(date, new ToDoList());

		// Notify all observers of the change to the dataset
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener listener : listeners)
			listener.stateChanged(event);
	}

	/**
	 * Replace value of existing item
	 * @param date
	 * @param list
	 */
	public void updateList(Date date, ToDoList list) {
		table.replace(date, list);	

		// Notify all observers of the change to the dataset
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener listener : listeners)
			listener.stateChanged(event);
	}

	/**
	 * Check if item exists in table Hashtable
	 * @param date
	 * @return
	 */
	public Boolean containsDate(Date date) {
		return table.containsKey(date);
	}

	/**
	 * Returns item at index
	 * @param date
	 * @return
	 */
	public ToDoList getList(Date date) {
		return table.get(date);
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
	public Hashtable<Date, ToDoList> getTable() {
		return table;
	}

	/**
	 * String version of table Hashtable
	 */
	public String toString() {
		return table.toString();
	}
}








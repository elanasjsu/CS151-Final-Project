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
	private Date selectedEntry;
	
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
	public Date getSelectedEntryDate() {
		return selectedEntry;
	}
	
	public ToDoList getSelectedEntryList() {
		return table.get(selectedEntry);
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








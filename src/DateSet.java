/**
 * DateSet is the model used to hold an arraylist of dates.
 * Each date has a day/month/year + a ToDoList Associated with it.
 * frames in question2.
 */
import java.util.*;
import javax.swing.event.*;

public class DateSet extends ArrayList<DateItem>
{
	private ArrayList<DateItem> items;
	private ArrayList<ChangeListener> listeners;
	private DateItem selectedDateItem;
	
	public DateSet() {
		items = new ArrayList<>();
		listeners = new ArrayList<>();
	}
	
	/**
	 * Sets the selected DateItem from the Calendar.
	 * @param item
	 */
	public void selectDate(DateItem item) {
		selectedDateItem = item;
	}
	
	/**
	 * Retrieves the selected DateItem for ToDoListFrame to use. 
	 * @return
	 */
	public DateItem getSelectedDate() {
		return selectedDateItem;
	}
	
	/**
	 * Add a new item to items ArrayList
	 * @param item
	 */
	public void addItem(DateItem item) {
		items.add(item);

		// Notify all observers of the change to the dataset
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener listener : listeners)
			listener.stateChanged(event);
	}
	
	/**
	 * Replace value of existing item
	 * @param index
	 * @param item
	 */
	public void changeItem(int index, DateItem item) {
		items.set(index, item);	
		
		// Notify all observers of the change to the dataset
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener listener : listeners)
			listener.stateChanged(event);
	}
	
	/**
	 * Check if item exists in items ArrayList
	 * @param index
	 * @return
	 */
	public Boolean isFilled(int index) {
		System.out.println("fill?");
		if(items.get(index).equals(null))
			return false;
		return true;
	}
	
	/**
	 * Returns item at index
	 * @param x
	 * @return
	 */
	public DateItem getItem(int x) {
		return items.get(x);
	}
	
	/**
	 * Returns size of items ArrayList
	 * @return
	 */
	public int getSize() {
		return items.size();
	}
	
	/**
	 * Adds change listeners to each item
	 * @param listener
	 */
	public void addChangeListener(ChangeListener listener) {
		listeners.add(listener);
	}

	/**
	 * Returns items ArrayList
	 * @return
	 */
	public ArrayList<DateItem> getItems() {
		return items;
	}

	/**
	 * String version of items ArrayList
	 */
	public String toString() {
		String r = "";
		for(int x = 0; x < items.size(); x++) {
			r += items.get(x).toString() + ", ";
		}
		return r;
	}
}








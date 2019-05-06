/**
 * ToDoList is the model used to hold an arraylist of list items.
 * This model updates as the ToDoList for a particular date is 
 * modified in the ToDoListFrame.
 */
import java.util.ArrayList;
import java.util.Date;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ToDoList {
	private ArrayList<ListItem> list;
	private ArrayList<ChangeListener> listeners;
	
	public ToDoList() {
		list = new ArrayList<>();
		listeners = new ArrayList<>();
	}
	
	/**
	 * Add a new item to list ArrayList
	 * @param item
	 */
	public void addItem(ListItem item) {
		list.add(item);

		// Notify all observers of the change to the dataset
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener listener : listeners)
			listener.stateChanged(event);
	}
	
	/**
	 * Delete an item from list ArrayList
	 * @param item
	 */
	public void deleteItem(ListItem item) {
		list.remove(item);
		
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
	public void changeItem(int index, ListItem item) {
		list.set(index, item);	
		
		// Notify all observers of the change to the dataset
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener listener : listeners)
			listener.stateChanged(event);
	}
	
	/**
	 * Check if item exists in list ArrayList
	 * @param index
	 * @return
	 */
	public Boolean isFilled(int index) {
		System.out.println("fill?");
		return list.get(index) != null;
	}
	
	/**
	 * Returns item at index
	 * @param x
	 * @return
	 */
	public ListItem getItem(int x) {
		return list.get(x);
	}
	
	/**
	 * Returns size of list ArrayList
	 * @return
	 */
	public int getSize() {
		return list.size();
	}
	
	/**
	 * Adds change listeners to each item
	 * @param listener
	 */
	public void addChangeListener(ChangeListener listener) {
		listeners.add(listener);
	}

	/**
	 * Returns list ArrayList
	 * @return
	 */
	public ArrayList<ListItem> getlist() {
		return list;
	}

	/**
	 * String version of list ArrayList
	 */
	public String toString() {
		String r = "";
		for(int x = 0; x < list.size(); x++) {
			r += list.get(x).toString() + ", ";
		}
		return r;
	}
}

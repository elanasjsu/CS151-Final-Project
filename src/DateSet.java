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
	private Day selectedMonthEntry;  //indicates the month for which month's todolist should be displayed
	public Boolean displayMonthList;

	public DateSet() {
		table = new Hashtable<Day, ToDoList>();
		listeners = new ArrayList<>();
		setEmptySelectedEntry();
	}

	public void setEmptySelectedEntry() {
		selectedEntry = new Day();
		selectedMonthEntry = null;
		displayMonthList = false;
		add(selectedEntry);
		System.out.println("Initialized an empty list for today's Day.");
	}

	/**
	 * Sets the selected DayItem from the Calendar.
	 * @param Day
	 */
	public void selectDay(Day Day) {
		selectedEntry = Day;
		selectedMonthEntry = null;

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
	 * and creates a month/year entry that has a day of 0. 
	 * @param i
	 */
	public void selectMonthEntry(int year, int month) {
		System.out.println("In selectMonthEntry(" + month + ", " + year + ")");
		if(month > 0 && month < 13) {
			Set<Day> keys = table.keySet();
			Day match = null;
			for(Day key: keys) {
				//if entry already exists 
				if(key.getMonthValue() == month && key.getYear() == year
						&& key.getDayOfMonth() == 0) {
					match = key;
				} 
			}
			
			//if found that month entry already
			if(match != null) {
				selectedMonthEntry = match;
				System.out.println("Selected month already in database: " + selectedMonthEntry.toString());
			} 
			//otherwise create a new month entry with day = 0
			else {
				selectedMonthEntry = new Day(year, month, 0);
				add(selectedMonthEntry);
				System.out.println("Added month to database: " + selectedMonthEntry.toString());
			}
			
			buildMonthList(year, month);

			// Notify all observers of the change to the dataset
			ChangeEvent event = new ChangeEvent(this);
			for (ChangeListener listener : listeners)
				listener.stateChanged(event);
		} 
		
		//input error handling
		else {
			System.out.println("Enter a month between 1..12");
		}
	}

	/**
	 * Adds the ToDoList from each Day entry that matches the same
	 * selected month and year to a Month entry.
	 */
	private void buildMonthList(int year, int month) {
		System.out.println("Building");
		System.out.println("Month: " + getSelectedMonthList().toString());
		
		//rebuilding the ToDoList for selectedMonthEntry
		table.get(selectedMonthEntry).reset();
		
		Set<Day> keys = table.keySet();
		ToDoList monthlist = new ToDoList();
		for(Day key: keys) {
			
			//if the entry matches same month and year...
			if(key.getMonthValue() == selectedMonthEntry.getMonthValue()
					&& key.getYear() == selectedMonthEntry.getYear()) {

				//copy entry's list into the selectedMonthEntry's list
				ToDoList templist = getList(key);
				for(int j = 0; j < templist.getSize(); j++) {
					if(templist.getItem(j) != null) {
						monthlist.addItem(templist.getItem(j));
					}	
				}
			}
		}
		upDateList(selectedMonthEntry, monthlist);
		System.out.println("Month: " + getSelectedMonthList().toString());
	}

	public Day getSelectedMonth() {
		return selectedMonthEntry;
	}

	public ToDoList getSelectedMonthList() {
		return table.get(selectedMonthEntry);
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
	public void upDateList(Day Day, ToDoList list) {
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








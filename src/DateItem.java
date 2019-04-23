import java.util.Date;

/*
 * A singular date that has a day/month/year + todolist 
 */
public class DateItem {
	private Date date;
	private ToDoList list;
	
	public DateItem(Date date) {
		this.date = date;
	}
	
	public DateItem(Date date, ToDoList list) {
		this.date = date;
		this.list = list;
	}
	
	public void setToDoList(ToDoList list) {
		this.list = list;
	}
	
	public void addItemToList(ListItem item) {
		
	}
	
	public String toString() {
		return date.toString();
	}
}

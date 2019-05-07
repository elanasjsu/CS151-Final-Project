import java.util.Date;

public class main {

	public static void main(String[] args) {
		final DateSet dateset = new DateSet(); //Model
		Calendar calendar = new Calendar(dateset); //View
		ToDoListFrame toDoListFrame = new ToDoListFrame(dateset);
		
		Day curDate = dateset.getSelectedDay();
		ToDoList toDoList = dateset.getSelectedList();
		toDoListFrame.repaint(dateset);
		
		/*
		 * MVC: Model-View-Controller interactions
		 * 
		 * If user clicks on a date on the calendar
		 * -> update selectedEntry in DateSet (M)
		 * 
		 * If selectedEntry is changed
		 * -> change displayed ToDoList (V)
		 * 
		 * -----
		 * 
		 * If user clicks on the month on the calendar
		 * -> update selectedMonth in DateSet (M)
		 * 
		 * If selectedMonth is changed
		 * -> change displayed ToDoList to combine all 
		 * ListItems from all ToDoLists in that month (V)
		 * 
		 */
		
	}

}

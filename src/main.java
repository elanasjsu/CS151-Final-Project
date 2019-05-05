import java.util.Date;

public class main {

	public static void main(String[] args) {
		final DateSet dateset = new DateSet(); //Model
		Calendar calendar = new Calendar(dateset); //View
		ToDoListFrame toDoListFrame = new ToDoListFrame(dateset);
		
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
		
		Day curDate = dateset.getSelectedDay();
		ToDoList toDoList = dateset.getSelectedList();
		
		//when the dataset model is changed, update the bar graph view
		//dateset.addChangeListener(event -> toDoListFrame.repaint(dateset));

		
		toDoListFrame.repaint(dateset);
		
		
	}

}

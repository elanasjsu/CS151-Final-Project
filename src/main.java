public class main {
	public static void main(String[] args) {
		final DateSet dateset = new DateSet(); //Model
		Calendar calendar = new Calendar(dateset); //View
		ToDoListFrame toDoListFrame = new ToDoListFrame(dateset);
		
		Day curDate = dateset.getSelectedDay();
		ToDoList toDoList = dateset.getSelectedList();
		toDoListFrame.repaint(dateset);	
	}
}

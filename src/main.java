import java.util.Date;

public class main {

	public static void main(String[] args) {
		final DateSet dateset = new DateSet(); //Model
//		Calendar calendar = new Calendar(); //View

		Date curDate = new Date();
		ToDoList toDoList = new ToDoList();

		for(int i = 0; i < 20; i++){
			toDoList.addItem(new ListItem("thing " + i));

		}

		dateset.add(curDate);
		dateset.selectDate(curDate);
		dateset.updateList(curDate, toDoList);

		ToDoListFrame toDoListFrame = new ToDoListFrame();
		toDoListFrame.repaint(dateset);
	}

}

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ToDoListFrame {

	JFrame frame;
	DateSet set;

	public ToDoListFrame() {
		frame = new JFrame();
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
	}

	/**
	 * Sets the data, populates the graph, and reconfigures the frame.
	 * @param set
	 */
	public void setData(DateSet set) {
		this.set = set;
		populateFrame();
		config();
	}

	/**
	 * Get's the selectedEntry value from DateSet set,
	 * retrieve the Date and ToDoList associated, and
	 * add title + list to the frame.
	 */
	public void populateFrame() {
		JLabel label = new JLabel(set.getSelectedEntryDate().toString()); //title of the ToDoList
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		ToDoList selectedList = set.getSelectedEntryList();

		//adds each item in the list as a new textfield to a panel
		for(int i = 0; i < selectedList.getSize(); i++) {
			JTextField field = new JTextField(selectedList.getItem(i).toString());
			panel.add(field);
		}

		//adds the title and panel of items to the frame
		frame.add(label);
		frame.add(panel);

		//would need to add those buttons at the bottom to the frame here...
	}

	/*
	 * This is triggered when user clicks "Add" button on ToDoListFrame
	 * Adding Item to ToDoList
	 * Call list.addItem to the list, then update
	 * the model by calling table.updateList(date, list)
	 */
	public void addItem(ListItem item) {

	}

	/*
	 * This is triggered when user clicks "Delete" button on ToDoListFrame
	 * Deleting Item from ToDoList
	 * Call list.deleteItem from the list, then update
	 * the model by calling table.updateList(date, list)
	 */
	public void deleteItem(ListItem item) {

	}

	/**
	 * Clean frame and reset data
	 * @param set
	 */
	public void repaint(DateSet set) {
		frame.getContentPane().removeAll();
		frame.repaint();
		frame.setLayout(new FlowLayout());
		setData(set);
	}

	/**
	 * Configure frame
	 */
	public void config() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("To Do List");
		frame.setMinimumSize(new Dimension(50, 50));
		frame.pack();
		frame.setVisible(true);
	}
}
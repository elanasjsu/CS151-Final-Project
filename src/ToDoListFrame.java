import com.sun.tools.javac.comp.Flow;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.html.ListView;

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
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();

		JLabel label = new JLabel(set.getSelectedEntryDate().toString()); //title of the ToDoList

		ToDoList selectedList = set.getSelectedEntryList();
		
		//adds each item in the list as a new textfield to a panel
		String[] data = new String[selectedList.getSize()];
		for(int i = 0; i < selectedList.getSize(); i++) {
			data[i] = selectedList.getItem(i).toString();
		}
		JList<String> list = new JList<>(data);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(200, 250));

		Button buttonOne = new Button("delete task");
		Button buttonTwo = new Button("edit task");
		Button buttonThree = new Button("add task");
		Button buttonFour = new Button("export");

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		buttonPanel.add(buttonOne);
		buttonPanel.add(buttonTwo);
		buttonPanel.add(buttonThree);
		buttonPanel.add(buttonFour);

		//adds the title and panel of items to the frame
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1.0;
		panel.add(label, constraints);

		constraints.gridy = 1;
		panel.add(listScroller, constraints);

		constraints.gridy = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		panel.add(new JTextField(), constraints);

		constraints.gridy = 3;
		panel.add(buttonPanel, constraints);

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

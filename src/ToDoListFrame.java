import java.awt.*;
import javax.swing.*;
import javax.swing.text.html.ListView;

public class ToDoListFrame {

	JFrame frame;
	DateSet set;
	Button buttonOne, buttonTwo, buttonThree, buttonFour;
	String newItemText;

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

		JLabel label = new JLabel(set.getSelectedDate().toString()); //title of the ToDoList

		ToDoList selectedList = set.getSelectedList();
		
		//adds each item in the list as a new textfield to a panel
		String[] data = new String[selectedList.getSize()];
		for(int i = 0; i < selectedList.getSize(); i++) {
			data[i] = selectedList.getItem(i).toString();
		}
		
		//lists and scrolls through the ListItems 
		JList<String> list = new JList<>(data);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(200, 250));

		//adds the title and panel of items to the frame
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1.0;
		panel.add(label, constraints);

		constraints.gridy = 1;
		panel.add(listScroller, constraints);

		//Add text field panel for adding new items
		constraints.gridy = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		JTextField field = addTextField();
		panel.add(field, constraints);

		//Add the buttons at the bottom 
		constraints.gridy = 3;
		JPanel buttonPanel = addButtons();
		panel.add(buttonPanel, constraints);

		frame.add(panel);
	}
	
	public JTextField addTextField() {
		JTextField field = new JTextField();
		
		field.addActionListener(event ->
        {
        	newItemText = field.getText();
		    System.out.println("Typed: " + field.getText());
		});
		
		return field;
	}
	
	public JPanel addButtons() {
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
		buttonOne = new Button("delete task");
		buttonTwo = new Button("edit task");
		buttonThree = new Button("add task");
		buttonFour = new Button("export");
		
		buttonOne.addActionListener(event ->
        {
		    System.out.println("Delete Button clicked");
		});
		
		buttonTwo.addActionListener(event ->
        {
		    System.out.println("Edit Button clicked");
		});
		
		buttonThree.addActionListener(event ->
        {
        	addNewItem();
		    System.out.println("Add Button clicked");
		});
		
		buttonFour.addActionListener(event ->
        {
		    System.out.println("Export Button clicked");
		});
		
		buttonPanel.add(buttonOne);
		buttonPanel.add(buttonTwo);
		buttonPanel.add(buttonThree);
		buttonPanel.add(buttonFour);

		return buttonPanel;
	}

	/*
	 * This is triggered when user clicks "Add" on ToDoListFrame
	 * Adding Item to ToDoList
	 * Call list.addItem to the list, then update
	 * the model by calling table.updateList(date, list)
	 */
	public void addNewItem() {
		System.out.println("In add new Item");
		ListItem item = new ListItem(newItemText);
		set.getSelectedList().addItem(item);
		repaint(set);
	}

	/*
	 * This is triggered when user clicks "Delete" on ToDoListFrame
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
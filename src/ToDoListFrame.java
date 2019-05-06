import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ToDoListFrame {

	JFrame frame;
	JLabel date;
	DateSet set;
	ToDoList selectedList;
	JScrollPane listScroller;
	Button buttonOne, buttonTwo, buttonThree, buttonFour;
	String newItemText;
	String[] tasks;
	JList<String> list;

	public ToDoListFrame(DateSet set) {
		frame = new JFrame();
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		setData(set);
		populateFrame();
		config();

		set.addChangeListener(e -> {
			date.setText(set.getSelectedDay().toString());
			setData(set);
			repaint(set);
		});
	}

	/**
	 * Sets the data, populates the graph, and reconfigures the frame.
	 * @param set
	 */
	public void setData(DateSet set) {
		this.set = set;
		this.selectedList = set.getSelectedList();
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

		date = new JLabel(set.getSelectedDay().toString()); //title of the ToDoList
		
		createList();
		
		listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(200, 250));

		//adds the title and panel of items to the frame
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1.0;
		panel.add(date, constraints);

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

	void createList(){
		//adds each item in the list to a panel
		tasks = new String[selectedList.getSize()];
		for(int i = 0; i < selectedList.getSize(); i++) {
			tasks[i] = selectedList.getItem(i).toString();
		}

		//lists and scrolls through the ListItems
		list = new JList<>(tasks);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
	}
	
	public JTextField addTextField() {
		JTextField field = new JTextField();

		field.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				newItemText = field.getText();
				System.out.println("Typed: " + field.getText());
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				newItemText = field.getText();
				System.out.println("Typed: " + field.getText());
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				newItemText = field.getText();
				System.out.println("Typed: " + field.getText());
			}
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
        	deleteItem();
		    System.out.println("Delete Button clicked");
		});
		
		buttonTwo.addActionListener(event ->
        {
        	editItem();
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

	/**
	 * Deleting Item from ToDoList and updates the dataset
	 */
	public void deleteItem() {
		try {
			System.out.println("In delete item");
	    	selectedList.deleteItem(selectedList.getItem(list.getSelectedIndex()));
	    	repaint(set);
		} catch (Exception e) {};
	}
	
	public void editItem() {
		try{
			System.out.println("Editting Item");
			ListItem item = selectedList.getItem(list.getSelectedIndex());
			item.setName(newItemText);
			selectedList.changeItem(list.getSelectedIndex(), item);
			repaint(set);
		} catch (Exception e) {};
	}
	
	/**
	 * Adding Item to ToDoList and updates the dataset
	 */
	public void addNewItem() {
		try{
			System.out.println("In add new Item");
			ListItem item = new ListItem(newItemText);
			selectedList.addItem(item);
			repaint(set);
		} catch (Exception e) {};
	}

	/**
	 * Clean frame and reset data
	 * @param set
	 */
	public void repaint(DateSet set) {
		setData(set);
		createList();
		listScroller.setViewportView(list);
		listScroller.repaint();
		frame.repaint();
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
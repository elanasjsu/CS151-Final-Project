
import java.awt.*;
import javax.swing.table.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class Calendar {

	// all the necessary components to make up Frame 1, the calendar component
	static JFrame frame;
	static DateSet set;
	static JComboBox navYear; // combobox to navigate thru the years
	static Container container;
	static JButton Prev, Next; //buttons for changing months
	static JButton lMonth; //month label
	static JLabel today;  //today's date
	static JLabel Month;
	static JLabel Day;
	static JLabel Year;
	static JTable tCalendar; //table for the calendar view
	static DefaultTableModel dtCalendar; //default tabel model
	static JScrollPane spCalendar;
	static JPanel panelCalendar;
	static int theYear, theMonth, theDay, currYear, currMonth;

	public Calendar(DateSet set) {
		frame = new JFrame("Calendar");
		setData(set);
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

	public void populateFrame() {

		int numOfdays = 0;
		int startDay = 0;

		container = frame.getContentPane();

		Prev = new JButton("Prev");
		Next = new JButton("Next");
		today = new JLabel("Today:");
		lMonth = new JButton("month");
		navYear = new JComboBox();

		dtCalendar = new DefaultTableModel() {
			//method provided by the tabel model interface
			public boolean isCellEditable(int ColIndex, int rowIndex) {
				return false;
			}
		};
		panelCalendar = new JPanel(null);
		tCalendar = new JTable(dtCalendar);
		spCalendar = new JScrollPane(tCalendar);

		//adding listeners
		navYear.addActionListener(new yearNav());
		Prev.addActionListener(new bPrev());
		Next.addActionListener(new bNext());
		lMonth.addActionListener(event -> 
		{
			System.out.println("clicked " + lMonth.getText() + ":" + (currMonth + 1) + " " + currYear);
			set.selectMonthEntry(currYear, (currMonth + 1));
		}); 

		//adding all components
		container.add(panelCalendar);
		panelCalendar.add(today);
		panelCalendar.add(lMonth);
		panelCalendar.add(navYear);
		panelCalendar.add(Prev);
		panelCalendar.add(Next);
		panelCalendar.add(spCalendar);

		//setting bounds for components
		panelCalendar.setBounds(0, 0, 320, 335);
		lMonth.setBounds(160 - lMonth.getPreferredSize().width / 2, 25, 100, 25);
		navYear.setBounds(230, 305, 90, 20);
		Prev.setBounds(20, 25, 60, 25);
		Next.setBounds(220, 25, 60, 25);
		spCalendar.setBounds(10, 50, 300, 250);
		today.setBounds(20, 305, 90, 20);

		//get todays date
		GregorianCalendar c = new GregorianCalendar();
		theMonth = c.get(GregorianCalendar.MONTH);
		theDay = c.get(GregorianCalendar.DAY_OF_MONTH);
		theYear = c.get(GregorianCalendar.YEAR);
		currYear = theYear;
		currMonth = theMonth;

		//display today's date in bottom corner
		Month = new JLabel(String.valueOf(theMonth + 1) + "");
		panelCalendar.add(Month);
		Month.setBounds(20, 325, 90, 20);

		Day = new JLabel(" /" + theDay);
		panelCalendar.add(Day);
		Day.setBounds(25, 325, 90, 20);

		Year = new JLabel("  /" + theYear);
		panelCalendar.add(Year);
		Year.setBounds(35, 325, 90, 20);


		//days of the week row
		String[] headers = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
		for (int i = 0; i < 7; i++)
			dtCalendar.addColumn(headers[i]);

		tCalendar.getTableHeader().setReorderingAllowed(false);
		tCalendar.getTableHeader().setResizingAllowed(false);
		tCalendar.setRowHeight(40);

		dtCalendar.setColumnCount(7);
		dtCalendar.setRowCount(7);

		//add items to combobox for navYear
		for (int i = theYear - 100; i <= theYear + 100; i++)
			navYear.addItem(String.valueOf(i));

		//calls refresh method to later change the months/years
		refresh(theMonth, theYear);
	}

	/**
	 * Configure frame
	 */
	public void config() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Calendar");
		frame.setSize(360, 385);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	public static void refresh(int month, int year) {

		String[] months = {"January", "February", "March", "April", "May",
				"June", "July", "August", "September", "October",
				"November", "December"};
		int startDay;
		int numOfdays;

		lMonth.setText(months[month]); //sets the current month
		navYear.setSelectedItem(String.valueOf(year));

		//clearing the calendar table
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++)
				dtCalendar.setValueAt(null, i, j);
		}

		//retrieve numOfDays
		GregorianCalendar c = new GregorianCalendar(year, month, 1);
		startDay = c.get(GregorianCalendar.DAY_OF_WEEK);
		numOfdays = c.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

		//this for loop properly fills in the table in the appropriate rows/columns
		for (int i = 1; i <= numOfdays; i++) {
			int row = new Integer((i + startDay - 2) / 7);
			int column = new Integer(i + startDay - 2) % 7;
			dtCalendar.setValueAt(i, row, column);
		}

		tCalendar.setDefaultRenderer(tCalendar.getColumnClass(0), new tableCalendarRenderer());
	}

	static class tableCalendarRenderer extends DefaultTableCellRenderer {

		//method provided for Jtable component
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

			if (column == 0 || column == 6)   //sat or sun
				setBackground(new Color(255, 250, 252));
			else
				setBackground(new Color(255, 255, 255));

			if (value != null) {
				if (Integer.parseInt(value.toString()) == theDay
						&& currMonth == theMonth
						&& currYear == theYear) { //if todays date set to red
					setForeground(Color.red);
				} else
					setForeground(Color.black);  //else black
			}

			if (isSelected && hasFocus) {
				setBackground(Color.pink);
				System.out.println("Date: " + value + "/" + (currMonth + 1) + "/" + currYear);
				set.selectDay(new Day(currYear, currMonth + 1, (int)value));
			} else {
				setBackground(Color.white);
			}

			setBorder(null);
			return this;
		}
	}

	//this handles the actions of the previous button
	static class bPrev implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (currMonth == 0) {
				currMonth = 11;
				currYear -= 1;
			} else
				currMonth -= 1;
			refresh(currMonth, currYear);
		}
	}

	//this handles the actions of the next button
	static class bNext implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (currMonth == 11) {
				currMonth = 0;
				currYear += 1;
			} else
				currMonth += 1;
			refresh(currMonth, currYear);
		}
	}

	//this handles the actions of the navigation between years
	static class yearNav implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (navYear.getSelectedItem() != null) {
				String s = navYear.getSelectedItem().toString();
				currYear = Integer.parseInt(s);
				refresh(currMonth, currYear);
			}
		}
	}
}
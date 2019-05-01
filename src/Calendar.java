
import java.util.*;
import java.awt.*;
import javax.swing.table.*;
import javax.swing.*;

public class Calendar {

    // all the necessary components to make up Frame 1, the calendar component

    static JComboBox navYear; // combobox to navigate thru the years
    static Container container;
    static JButton Prev, Next; //buttons for changing months
    static JFrame frame;
    static JLabel lMonth; //month label
    static JLabel today;  //today's date
    static JTable tCalendar; //table for the calendar view
    static DefaultTableModel dtCalendar; //default tabel model
    static JScrollPane spCalendar;
    static JPanel panelCalendar;
    static int theYear, theMonth, theDay, currYear, currMonth;

    public Calendar(){
        frame = new JFrame("Calendar");
        populateFrame();

    }


    public void populateFrame() {


        frame.setSize(360, 385);
        container = frame.getContentPane();

        Prev = new JButton("Prev");
        Next = new JButton("Next");
        today = new JLabel("today's date");
        lMonth = new JLabel("month");
        navYear = new JComboBox();

        dtCalendar = new DefaultTableModel() {
            //method provided by the tabel model interface
            public boolean isCellEditable(int ColIndex,int rowIndex ) {
                return false;
            }
        };
        panelCalendar = new JPanel(null);
        tCalendar = new JTable(dtCalendar);
        spCalendar = new JScrollPane(tCalendar);


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
        lMonth.setBounds(160 - lMonth.getPreferredSize().width / 2, 25, 200, 25);
        navYear.setBounds(230, 305, 90, 20);
        Prev.setBounds(20, 25, 60, 25);
        Next.setBounds(220, 25, 60, 25);
        spCalendar.setBounds(10, 50, 300, 250);
        today.setBounds(20, 305, 90, 20);


        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //get todays date
        GregorianCalendar cal = new GregorianCalendar();
        theMonth = cal.get(GregorianCalendar.MONTH);
        theDay = cal.get(GregorianCalendar.DAY_OF_MONTH);
        theYear = cal.get(GregorianCalendar.YEAR);
        currYear = theYear;
        currMonth = theMonth;


        //days of the week row
        String[] headers = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (int i = 0; i < 7; i++) {
            dtCalendar.addColumn(headers[i]);
        }

        tCalendar.getTableHeader().setReorderingAllowed(false);
        tCalendar.getTableHeader().setResizingAllowed(false);
        tCalendar.setRowHeight(40);

        dtCalendar.setColumnCount(7);
        dtCalendar.setRowCount(7);

        //add items to combobox for navYear
        for (int i = theYear - 100; i <= theYear + 100; i++) {
            navYear.addItem(String.valueOf(i));
        }

        //calls refresh method to later change the months/years
        refresh(theMonth, theYear);
    }

    public static void refresh(int month, int year) {

        String[] months = {"January", "February", "March", "April", "May",
                "June", "July", "August", "September", "October",
                "November", "December"};
        int numOfdays;

        lMonth.setText(months[month]); //sets the current month
        navYear.setSelectedItem(String.valueOf(year));


        //retrieve numOfDays
        GregorianCalendar cal = new GregorianCalendar(year, month, 1);
        numOfdays = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

        //this for loop properly fills in the table in the appropriate rows/columns
        for (int i = 1; i <= numOfdays; i++) {
            int column = new Integer((i ) % 7);
            int row = new Integer((i) / 7);

            dtCalendar.setValueAt(i, row, column);
        }

        tCalendar.setDefaultRenderer(tCalendar.getColumnClass(0), new tableCalendarRenderer());
    }

    static class tableCalendarRenderer extends DefaultTableCellRenderer {

        //method provided for Jtable component

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (column == 0 || column == 6) {   //sat or sun
                setBackground(new Color(255, 250, 252));
            }
            else {
                setBackground(new Color(255, 255, 255));
            }

            if (value != null) {
                if (Integer.parseInt(value.toString()) == theDay
                        && currMonth == theMonth
                        && currYear == theYear) { //if todays date set to red
                    setForeground(Color.red);
                }
                else {
                    setForeground(Color.black);  //else black
                }
            }

            setBorder(null);
            return this;
        }
    }

   /* static class bprevAction implements ActionListener {

    }

    static class bnextAction implements ActionListener {

    }

    static class yearNavAction implements ActionListener {

    } */
}
package CA1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.*;





public class FlightReservationFrame extends JFrame{

	
	private JPanel parentPnl = new JPanel(new BorderLayout());
	private JPanel topPnl = new JPanel();
	private JPanel middlePnl = new JPanel();
	private JPanel bottomPnl = new JPanel();
	private JLabel depLbl = new JLabel("Departures");
	private String []depNames = {"Dublin", "Copenhagen", "Edinburgh", "London", "New York", "Oslo", "San Francisco"};
	private JComboBox depCmbBx = new JComboBox(depNames);
	private JLabel destLbl = new JLabel("Destination");
	private String []destNames = {"Copenhagen", "Dublin", "Edinburgh", "London", "New York", "Oslo", "San Francisco"};
	private JComboBox destCmbBx = new JComboBox(destNames);
	private JButton searchBtn = new JButton("Search");
	private JButton showAllBtn = new JButton("Show All");
	private JLabel tixLbl = new JLabel("Number of Tickets");
	private JTextField tixTxtBx = new JTextField(10);
	private JButton purchaseButton = new JButton("Purchase");
	private TableRowSorter<TableModel> rowSorter;
	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private Date date = new Date();
	private JLabel dateLbl = new JLabel();
	
	
	public FlightReservationFrame(){
		//create an instance of your custom TableModel and pass into JTable constructor
		JTable tbl = new JTable(new FlightReservationModel());
        	tbl.setPreferredScrollableViewportSize(new Dimension(500, 70));
        	tbl.setFillsViewportHeight(true);
        
        	
        topPnl.add(depLbl);
        topPnl.add(depCmbBx);
        topPnl.add(destLbl);
        topPnl.add(destCmbBx);
        topPnl.add(searchBtn);
        topPnl.add(showAllBtn);

		JScrollPane srl = new JScrollPane(tbl);
		middlePnl.add(srl);
		
		dateLbl.setText("Todays date: " + dateFormat.format(date));
		
		bottomPnl.add(tixLbl);
		bottomPnl.add(tixTxtBx);
		bottomPnl.add(purchaseButton);
		bottomPnl.add(dateLbl);
		
		
		parentPnl.add(topPnl, BorderLayout.NORTH);
		parentPnl.add(middlePnl, BorderLayout.CENTER);
		parentPnl.add(bottomPnl, BorderLayout.SOUTH);
		add(parentPnl);
		
		//set frame properties
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		pack();
		setTitle("Test");
		
		
		searchBtn.addActionListener(new ActionListener(){
			@Override
		      public void actionPerformed(ActionEvent e){
		          if (depCmbBx.getSelectedItem().equals(destCmbBx.getSelectedItem())) {
		        	  JOptionPane.showMessageDialog(FlightReservationFrame.this, "Both cities cannot be the same!");
		          }
		          else {
		        	  ArrayList<RowFilter<Object, Object>> rowFilter = new ArrayList<RowFilter<Object, Object>>(2);
		        	  
		        	  // I created strings out of the combo boxes here to pass them into the row filter below
		        	  String depFilter = (String)depCmbBx.getSelectedItem();
		        	  String destFilter = (String)destCmbBx.getSelectedItem();
		        	  rowFilter.add(RowFilter.regexFilter(depFilter,0));
		        	  rowFilter.add(RowFilter.regexFilter(destFilter,1));
		        	  
		        	  RowFilter<Object, Object> andFilter = RowFilter.andFilter(rowFilter);
		        	  
		        	  rowSorter = new TableRowSorter<TableModel>(tbl.getModel());
		        	  tbl.setRowSorter(rowSorter);
		        	  
		        	  rowSorter.setRowFilter(andFilter);
		          }
		       }
		    });
		
		showAllBtn.addActionListener(new ActionListener(){
			@Override
		      public void actionPerformed(ActionEvent e){
					// Get the list back to full size by taking off any filters it has
					rowSorter.setRowFilter(null);
		       }
		    });
}

public static void main(String[] args) {
		
		new FlightReservationFrame();

	}
}

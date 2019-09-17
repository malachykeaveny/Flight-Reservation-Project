package CA1;

import javax.swing.table.AbstractTableModel;
import javax.swing.border.*;

public class FlightReservationModel extends AbstractTableModel {

	private String[] columnNames = {"Departures",
            "Destination",
            "Flight num",
            "Number of Txx", 
            "First class available"};
	
	//private String []ageRatings = {"G", "PG", "PG-13", "R"};
	
	private Object[][] data = {
		    {"Dublin", "Copenhagen", "SK538", new Integer(200), new Boolean(false)},
		    {"Dublin", "Oslo", "DY1363", new Integer(27), new Boolean(false)},
		    {"San Francisco","Dublin", "EI147", new Integer(30), new Boolean(true)},
		    {"Edinburgh","Dublin", "EI147", new Integer(30), new Boolean(false)},
		    {"New York","Dublin", "EI109", new Integer(40), new Boolean(true)},
		};

	@Override
	public int getColumnCount() {
		// get number of columns in the column array
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		// get number of rows in the data array
		return data.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// used to populate the Table
		return data[rowIndex][columnIndex];
	}
	@Override
	public String getColumnName(int col) {
        return columnNames[col];
	}
	
	
	@Override
	public Class getColumnClass(int columnIndex) {
		return getValueAt(0,columnIndex).getClass();
	}
	@Override
	  public boolean isCellEditable(int row, int col) {
			return true;
	    }
	@Override
    	public void setValueAt(Object value, int row, int col) {
        	data[row][col] = value;
        	fireTableCellUpdated(row, col);
    }
}


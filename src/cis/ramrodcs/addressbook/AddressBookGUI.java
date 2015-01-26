package cis.ramrodcs.addressbook;

import java.util.LinkedHashSet;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cis.ramrodcs.addressbook.interfaces.GUI;
import cis.ramrodcs.addressbook.interfaces.listeners.TableMouseListener;


public class AddressBookGUI extends AddressBook {

	private DefaultTableModel model;
	private LinkedHashSet<String> columns;
	
	public JTable table;
	
	public AddressBookGUI(GUI gui) {
		model = new DefaultTableModel();
		table = new JTable(model);
	    table.setAutoCreateRowSorter(true);
	    table.setAutoResizeMode(0);
		for(String s : DataEntry.defaultEntries) {
			model.addColumn(s);
			System.out.println("Adding column: " + s);
		}
		table.addMouseListener(new TableMouseListener(gui, table));
	}
	
	@Override
	public void addDataEntry(DataEntry entry) {
		super.addDataEntry(entry);
		
		String[] row = new String[model.getColumnCount()];
		
		for(int i=0; i<row.length; i++) {
			row[i] = entry.getField(DataEntry.defaultEntries[i]);
		}
		
		model.addRow(row);
		table.repaint();
	}
}

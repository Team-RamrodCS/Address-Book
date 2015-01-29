package cis.ramrodcs.addressbook;

import java.util.LinkedHashSet;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import cis.ramrodcs.addressbook.interfaces.GUI;
import cis.ramrodcs.addressbook.interfaces.listeners.TableSelectionListener;


public class AddressBookGUI extends JScrollPane{

	private AddressBook book;
	private DefaultTableModel model;
	private LinkedHashSet<String> columns;
	
	public JTable table;

	public AddressBookGUI(GUI gui) {
		this(gui, new AddressBook());
	}
	public AddressBookGUI(GUI gui, AddressBook book) {
		this.book = new AddressBook();
		model = new DefaultTableModel();
		table = new JTable(model);
	    table.setAutoCreateRowSorter(true);
	    table.setAutoResizeMode(0);
		for(String s : DataEntry.defaultEntries) {
			model.addColumn(s);
		}	
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		model.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent event) {
				String key = model.getColumnName(event.getColumn());
				if( key == null || key.equals("") ) {
					return;
				}
				String value = (String) model.getValueAt(event.getFirstRow(), event.getColumn());
				gui.getActiveBook().getEntries().get(event.getFirstRow()).addField(key, value);
				gui.setCurrentEntry(gui.getActiveBook().getEntries().get(event.getFirstRow()));
			}
			
		});
		table.getSelectionModel().addListSelectionListener(new TableSelectionListener(gui));
		this.setViewportView(table);
		
		for(DataEntry entry : book.getEntries()) {
			addDataEntry(entry);
		}
	}
	
	/**
	 * Add data entry to the current book and update GUI
	 * 
	 * @param entry to be added
	 */
	public void addDataEntry(DataEntry entry) {
		book.addDataEntry(entry);
		addTableRow(entry);
	}
	
	/**
	 * Populate a table row, then add it to the GUI and update
	 * @param entry
	 */
	private void addTableRow(DataEntry entry) {
		String[] row = new String[model.getColumnCount()];
		
		for(int i=0; i<row.length; i++) {
			String value = entry.getField(DataEntry.defaultEntries[i]);
			if(value != null) {
				row[i] = entry.getField(DataEntry.defaultEntries[i]);
			} else {
				row[i] = " ";
			}
		}
		
		model.addRow(row);
		table.repaint();
	}
	
	/**
	 * Get the JTable associated with this address book GUI
	 * 
	 * @return associated JTable
	 */
	public JTable getTable() {
		return table;
	}
	
	/**
	 * Get the address book associated with this address book GUI
	 * 
	 * @return
	 */
	public AddressBook getBook() {
		return book;
	}
	
	/**
	 * Update all the entries of the address book associated with this address book GUI
	 */
	public void updateElements() {
		model.setRowCount(0);
		for(DataEntry entry : book.getEntries()) {
			addTableRow(entry);
		}
	}
	
	/**
	 * Remove the data entry in the address book associated with this address book GUI
	 * 
	 * @param entry to be removed
	 */
	public void removeDataEntry(DataEntry entry) {
		book.removeDataEntry(entry);
		updateElements();
	}

}

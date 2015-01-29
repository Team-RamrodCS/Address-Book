package cis.ramrodcs.addressbook.interfaces.listeners;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import cis.ramrodcs.addressbook.AddressBook;
import cis.ramrodcs.addressbook.interfaces.GUI;

public class TableSelectionListener implements ListSelectionListener {
	
	private GUI gui;
	
	public TableSelectionListener(GUI gui) {
		this.gui = gui;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent event) {
		ListSelectionModel lsm = (ListSelectionModel)event.getSource();
		if(lsm.getLeadSelectionIndex() < 0) {
			return;
		}
		gui.setCurrentEntry(gui.getActiveBook().getEntries().get(lsm.getLeadSelectionIndex()));
	}

}

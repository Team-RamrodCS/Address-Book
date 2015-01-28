package cis.ramrodcs.addressbook.interfaces.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cis.ramrodcs.addressbook.AddressBookGUI;
import cis.ramrodcs.addressbook.DataEntry;
import cis.ramrodcs.addressbook.interfaces.GUI;

public class AddEntryListener implements ActionListener {
	
	private GUI gui = null;

	public AddEntryListener(GUI gui) {
		this.gui  = gui;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		AddressBookGUI book = gui.getActiveBookGui();
		if(book != null) {
			DataEntry entry = new DataEntry(book.getBook());
			book.addDataEntry(entry);
			gui.setCurrentEntry(entry);
		}
	}

}

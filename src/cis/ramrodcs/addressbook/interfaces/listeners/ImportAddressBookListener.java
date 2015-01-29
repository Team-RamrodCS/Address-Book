package cis.ramrodcs.addressbook.interfaces.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cis.ramrodcs.addressbook.interfaces.GUI;

public class ImportAddressBookListener implements ActionListener {
	private GUI gui;
	
	public ImportAddressBookListener(GUI gui) {
		this.gui = gui;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gui.importAddressBook(gui.getMainWindow());
	}	
}

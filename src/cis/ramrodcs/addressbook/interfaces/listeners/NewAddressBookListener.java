package cis.ramrodcs.addressbook.interfaces.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cis.ramrodcs.addressbook.AddressBookGUI;
import cis.ramrodcs.addressbook.interfaces.GUI;

public class NewAddressBookListener implements ActionListener {

	private GUI gui;
	
	public NewAddressBookListener(GUI gui) {
		this.gui = gui;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		AddressBookGUI book = new AddressBookGUI();
		gui.addAddressBook(book);
	}

}

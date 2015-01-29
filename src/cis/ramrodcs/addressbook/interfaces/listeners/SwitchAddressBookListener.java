package cis.ramrodcs.addressbook.interfaces.listeners;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import cis.ramrodcs.addressbook.AddressBookGUI;
import cis.ramrodcs.addressbook.interfaces.GUI;
import cis.ramrodcs.addressbook.interfaces.gui.HMUMainPanel;

public class SwitchAddressBookListener implements ChangeListener {

	private GUI gui = null;
	
	public SwitchAddressBookListener(GUI gui) {
		this.gui = gui;
	}

	@Override
	public void stateChanged(ChangeEvent event) {
		HMUMainPanel panel = (HMUMainPanel) event.getSource();
		AddressBookGUI book = (AddressBookGUI) panel.getSelectedComponent();
		gui.setActiveBookGui(book);
	}


}

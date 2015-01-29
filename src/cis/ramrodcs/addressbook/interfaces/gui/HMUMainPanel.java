package cis.ramrodcs.addressbook.interfaces.gui;

import javax.swing.JTabbedPane;

import cis.ramrodcs.addressbook.interfaces.GUI;
import cis.ramrodcs.addressbook.interfaces.listeners.SwitchAddressBookListener;

public class HMUMainPanel extends JTabbedPane{

	public HMUMainPanel(GUI gui) {
	    this.addChangeListener(new SwitchAddressBookListener(gui));
	}
}

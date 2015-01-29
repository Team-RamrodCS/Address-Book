package cis.ramrodcs.addressbook.interfaces.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cis.ramrodcs.addressbook.interfaces.GUI;

public class RemoveEntryListener implements ActionListener {

	private GUI gui = null;
	
	public RemoveEntryListener(GUI gui) {
		this.gui = gui;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		gui.removeCurrentEntry();
	}

}

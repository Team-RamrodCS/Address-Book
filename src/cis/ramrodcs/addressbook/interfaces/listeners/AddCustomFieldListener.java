package cis.ramrodcs.addressbook.interfaces.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cis.ramrodcs.addressbook.interfaces.GUI;

public class AddCustomFieldListener implements ActionListener {

	private GUI gui;
	
	public AddCustomFieldListener(GUI gui) {
		this.gui = gui;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
	}

}

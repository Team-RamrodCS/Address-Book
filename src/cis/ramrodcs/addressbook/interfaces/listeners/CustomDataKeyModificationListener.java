package cis.ramrodcs.addressbook.interfaces.listeners;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import cis.ramrodcs.addressbook.interfaces.GUI;

public class CustomDataKeyModificationListener implements DocumentListener {

	GUI gui;
	
	public CustomDataKeyModificationListener(GUI gui) {
		this.gui = gui;
	}
	
	@Override
	public void changedUpdate(DocumentEvent arg0) {
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
	}

}

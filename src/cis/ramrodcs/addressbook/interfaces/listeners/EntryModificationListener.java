package cis.ramrodcs.addressbook.interfaces.listeners;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import cis.ramrodcs.addressbook.DataEntry;
import cis.ramrodcs.addressbook.interfaces.GUI;

public class EntryModificationListener implements DocumentListener {

	DataEntry entry = null;
	String key = null;
	JTextField field = null;
	GUI gui = null;
	
	public EntryModificationListener(DataEntry entry, String key, JTextField field, GUI gui) {
		this.entry = entry;
		this.key = key;
		this.field = field;
		this.gui = gui;
	}
	
	@Override
	public void changedUpdate(DocumentEvent e) {
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		update(field.getText());
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		update(field.getText());
	}
	
	private void update(String value) {
		entry.addField(key, value);
		gui.update();
	}
}

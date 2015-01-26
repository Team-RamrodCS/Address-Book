package cis.ramrodcs.addressbook.interfaces.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import cis.ramrodcs.addressbook.interfaces.GUI;

public class SaveAddressBookListener implements ActionListener {

	private GUI gui;
	
	public SaveAddressBookListener(GUI gui){
		this.gui = gui;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gui.saveFile(gui.getMainWindow());
	}

}

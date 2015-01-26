package cis.ramrodcs.addressbook.interfaces.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import cis.ramrodcs.addressbook.interfaces.GUI;

public class LoadAddressBookListener implements ActionListener {

	private GUI gui;
	private JFrame window;
	
	public LoadAddressBookListener(GUI gui, JFrame window) {
		this.gui = gui;
		this.window = window;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gui.loadFile(window);
	}

}

package cis.ramrodcs.addressbook.interfaces.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import cis.ramrodcs.addressbook.interfaces.GUI;

public class QuitListener implements ActionListener {

	private GUI gui;
	
	public QuitListener(GUI gui) {
		this.gui = gui;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gui.getMainWindow().dispose();
	}

}

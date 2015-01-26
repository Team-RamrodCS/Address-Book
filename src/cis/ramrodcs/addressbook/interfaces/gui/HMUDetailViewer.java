package cis.ramrodcs.addressbook.interfaces.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cis.ramrodcs.addressbook.DataEntry;

public class HMUDetailViewer extends JPanel{

	private DataEntry currentEntry = null;
	
	public HMUDetailViewer() {
	    super.setOpaque(true);
	    super.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	}
	
	private void updateElements() {
		this.removeAll();
		if(currentEntry == null) {
			return;
		}
		for(String s : currentEntry.getEntries().keySet()) {
			JPanel info = new JPanel();
			JLabel key = new JLabel(s + ":");
			JLabel label = new JLabel(currentEntry.getField(s));
			info.add(key);
			info.add(label);
			this.add(info);
		}
	}
	
	public void setCurrentEntry(DataEntry entry) {
		this.currentEntry = entry;
		updateElements();
		super.revalidate();
		super.repaint();
	}
	
}

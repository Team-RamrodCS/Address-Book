package cis.ramrodcs.addressbook.interfaces.gui;

import java.awt.Button;
import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cis.ramrodcs.addressbook.DataEntry;

public class HMUDetailViewer extends JPanel{

	private DataEntry currentEntry = null;
	
	public HMUDetailViewer() {
	    super.setOpaque(true);
	    super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
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
			info.setAlignmentX(Component.LEFT_ALIGNMENT);
			this.add(info);
		}
	}
	
	public void setCurrentEntry(DataEntry entry) {
		this.currentEntry = entry;
		updateElements();
		super.revalidate();
		super.repaint();
	}
	
	private JButton getAddEntryButton() {
		JButton button = new JButton();
		//JImageIcon icon = new JImageIcon();
		return button;
	}
	
}

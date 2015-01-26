package cis.ramrodcs.addressbook.interfaces.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class HMUDetailedViewer {

	private JPanel sidePanel;
	
	public HMUDetailedViewer() {
	    // Customize side panel
	    sidePanel = new JPanel();
	    sidePanel.setOpaque(true);
	    sidePanel.setBackground(Color.blue);
	}
	
	public void addElements(JFrame frame) {
		frame.add(sidePanel, BorderLayout.WEST);
	}
	
}

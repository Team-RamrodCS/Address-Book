package cis.ramrodcs.addressbook.interfaces.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class HMUMainPanel {

    JTabbedPane tabPane;
    
    public HMUMainPanel() {
    	tabPane = new JTabbedPane();
    }

	public void addTab(String string, JScrollPane scrollPane) {
		tabPane.addTab(string, scrollPane);
	}
	
	public void addElements(JFrame frame) {
		frame.add(tabPane, BorderLayout.CENTER);
	}

}

package cis.ramrodcs.addressbook.interfaces.listeners;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import cis.ramrodcs.addressbook.DataEntry;
import cis.ramrodcs.addressbook.interfaces.GUI;

public class PrintUPSListener implements ActionListener {
	
	private GUI gui;

	public PrintUPSListener(GUI gui) {
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame window = new JFrame("UPS Formatter");
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setPreferredSize(new Dimension(200, 100));
		if(gui.getCurrentEntry() == null) {
			window.getContentPane().add(new JLabel("No entry selected."));
		} else {	
			DataEntry entry = gui.getCurrentEntry();
			String result = "";
			result = entry.getField("first name") + " " + entry.getField("last name") + "\n";
			result += entry.getField("street address") + "\n";
			result += entry.getField("city") + ", " + entry.getField("state") + " " + entry.getField("zip");
			
			JTextPane field = new JTextPane();
			field.setText(result);

			field.setEditable(false);
			field.setBorder(null);
			field.setForeground(window.getForeground());
			field.setBackground(window.getBackground());
			
			window.getContentPane().add(field);
		}
		window.pack();
		window.setVisible(true);
	}

}

package cis.ramrodcs.addressbook.interfaces.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import cis.ramrodcs.addressbook.DataEntry;
import cis.ramrodcs.addressbook.interfaces.GUI;
import cis.ramrodcs.addressbook.interfaces.listeners.AddEntryListener;
import cis.ramrodcs.addressbook.interfaces.listeners.CustomDataKeyModificationListener;
import cis.ramrodcs.addressbook.interfaces.listeners.EntryModificationListener;
import cis.ramrodcs.addressbook.interfaces.listeners.RemoveEntryListener;

public class HMUDetailViewer extends JPanel{

	private DataEntry currentEntry = null;
	JPanel details = null;
	JPanel detailPanel = null;
	JButton addFieldButton = null;
	GUI gui = null;
	
	public HMUDetailViewer(GUI gui) {
	    super.setOpaque(true);
	    super.setLayout(new GridLayout(0, 1));
	    this.gui = gui;
	    // Create Header
	    JPanel header = new JPanel();
	    header.setLayout(new BorderLayout());
	    JLabel mainLabel = new JLabel("Address Viewer", SwingConstants.CENTER);
	    header.add(mainLabel, BorderLayout.CENTER);
	    
		JButton addEntryButton = new JButton();
		JButton remEntryButton = new JButton();
		ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("assets/gui/addEntry.png"));
		ImageIcon remIcon = new ImageIcon(getClass().getClassLoader().getResource("assets/gui/remEntry.png"));
		Image resize = icon.getImage().getScaledInstance(icon.getIconWidth()/6, icon.getIconHeight()/6, 0);
		Image resizeRem = remIcon.getImage().getScaledInstance(icon.getIconWidth()/6, icon.getIconHeight()/6, 0);
		addEntryButton.setIcon(new ImageIcon(resize));
		addEntryButton.setPreferredSize(new Dimension(25, 25));
		addEntryButton.addActionListener(new AddEntryListener(gui));
		
		remEntryButton.setIcon(new ImageIcon(resizeRem));
		remEntryButton.setPreferredSize(new Dimension(25, 25));
		remEntryButton.addActionListener(new RemoveEntryListener(gui));
		JPanel editPanel = new JPanel(new FlowLayout());
		
		editPanel.add(remEntryButton);
		editPanel.add(addEntryButton);
		
		header.add(editPanel, BorderLayout.EAST);
	    //header.setMaximumSize(new Dimension(500, 25));
	    header.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	    
	    // Create Info Box
	    detailPanel = new JPanel(new BorderLayout());
	    details = new JPanel();
	    details.setLayout(new GridLayout(0, 2, 5, 5));
	    details.setAlignmentY(Component.TOP_ALIGNMENT);
	    //detailView.setLayout(new BoxLayout(detailView, BoxLayout.PAGE_AXIS	));
	    //info.setLayout(new GridLayout(0, 1));
	    
		addFieldButton = new JButton("Add custom field value");
		addFieldButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField keyField = new JTextField("Key");
				keyField.getDocument().addDocumentListener(new CustomDataKeyModificationListener(gui));
				details.add(new JTextField("Key"));
				details.add(new JTextField("Value"));
				details.revalidate();
			}
			
		});
	    // Put Elements together
	    detailPanel.add(header, BorderLayout.NORTH);
	    detailPanel.add(details, BorderLayout.CENTER);
	    detailPanel.add(addFieldButton, BorderLayout.SOUTH);
	    super.add(detailPanel);
	    

	}
	
	private void updateElements() {
		details.removeAll();
		if(currentEntry == null) {
			return;
		}

		for(String key : currentEntry.getDefaultEntries().keySet()) {
			details.add(new JLabel(key + ":"));
			JPanel entryPanel = new JPanel(new BorderLayout());
			JTextField entryField = new JTextField(currentEntry.getField(key));
			entryField.getDocument().addDocumentListener(new EntryModificationListener(currentEntry, key, entryField, gui));
			entryPanel.add(entryField);
			details.add(entryPanel);
		}
	
		details.add(new JSeparator());
		details.add(new JSeparator());
			
		for(String key : currentEntry.getCustomEntries().keySet()) {
			details.add(new JLabel(key + ":"));
			JTextField entryField = new JTextField(currentEntry.getField(key));
			entryField.setMaximumSize(entryField.getPreferredSize());
			details.add(entryField);
		}
		//detailView.add(addFieldButton);
		
		//detailView.add(Box.createGlue());
	}
	
	public void setCurrentEntry(DataEntry entry) {
		this.currentEntry = entry;
		updateElements();
		super.revalidate();
		super.repaint();
	}
	
	public DataEntry getCurrentEntry() {
		return currentEntry;
	}
}

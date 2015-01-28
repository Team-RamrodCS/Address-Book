package cis.ramrodcs.addressbook.interfaces.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;

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
import cis.ramrodcs.addressbook.interfaces.listeners.EntryModificationListener;
import cis.ramrodcs.addressbook.interfaces.listeners.RemoveEntryListener;

public class HMUDetailViewer extends JPanel{

	private DataEntry currentEntry = null;
	JPanel detailView = null;
	GUI gui = null;
	
	public HMUDetailViewer(GUI gui) {
	    super.setOpaque(true);
	    super.setLayout(new GridLayout());
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
	    detailView = new JPanel();
	    detailView.setLayout(new GridLayout(0, 2, 5, 5));
	    detailView.setAlignmentY(Component.TOP_ALIGNMENT);
	    //detailView.setLayout(new BoxLayout(detailView, BoxLayout.PAGE_AXIS	));
	    //info.setLayout(new GridLayout(0, 1));
	    
	    // Put Elements together
	    JPanel total = new JPanel();
	    total.setLayout(new BorderLayout());
	    total.add(header, BorderLayout.NORTH);
	    total.add(detailView, BorderLayout.CENTER);
	    super.add(total);
	}
	
	private void updateElements() {
		detailView.removeAll();
		if(currentEntry == null) {
			return;
		}

		for(String key : currentEntry.getDefaultEntries().keySet()) {
			detailView.add(new JLabel(key + ":"));
			JPanel entryPanel = new JPanel(new BorderLayout());
			JTextField entryField = new JTextField(20);
			entryField.setText(currentEntry.getField(key));
			entryField.getDocument().addDocumentListener(new EntryModificationListener(currentEntry, key, entryField, gui));
			entryPanel.add(entryField, BorderLayout.WEST);
			entryField.setMaximumSize(new Dimension(entryPanel.getWidth(), entryField.getHeight()));
			detailView.add(entryPanel);
		}
	
		detailView.add(new JSeparator());
		detailView.add(new JSeparator());
			
		for(String key : currentEntry.getCustomEntries().keySet()) {
			detailView.add(new JLabel(key + ":"));
			JTextField entryField = new JTextField(currentEntry.getField(key));
			entryField.setMaximumSize(entryField.getPreferredSize());
			detailView.add(entryField);
		}
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

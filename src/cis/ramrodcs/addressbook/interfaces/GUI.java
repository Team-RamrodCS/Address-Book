package cis.ramrodcs.addressbook.interfaces;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import cis.ramrodcs.addressbook.AddressBook;

/**
 * 
 * @author JeremyLipps
 * @date Jan. 19, 2015
 * 
 * Class that controls the GUI and its associated functions.
 * @see ABInterface
 * 
 */
public class GUI implements ABInterface
{
	/**
	 * When called, this function starts the GUI interface.
	 */
	@Override
	public void start() 
	{
	    JFrame frame = new JFrame("Address Book");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    JButton addButton = new JButton("Add Address");
	    JButton view_oneButton = new JButton("View An Address");
	    JButton view_allButton = new JButton("View All Addresses");
	    JButton quitButton = new JButton("Quit Program");
	    
	    // Create new AddressBook
	    AddressBook book = new AddressBook();
	    
	    //Where the GUI is created:
	    JMenuBar menuBar;
	    JMenu menu, submenu;
	    JMenuItem addAddress, viewAddress, viewAddresses, load, quit, menuItem;

	    //Create the menu bar.
	    menuBar = new JMenuBar();

	    //Build the first menu.
	    menu = new JMenu("File");
	    //menu.setMnemonic(KeyEvent.VK_A);
	    menu.getAccessibleContext().setAccessibleDescription(
	            "The only menu in this program that has menu items");
	    menuBar.add(menu);

	    //a group of JMenuItems
	    load = new JMenuItem("Load file");
	    menu.add(load);
	    
	    addAddress = new JMenuItem("Add an address",
	                             KeyEvent.VK_T);
	    addAddress.setAccelerator(KeyStroke.getKeyStroke(
	            KeyEvent.VK_1, ActionEvent.ALT_MASK));
	    menu.add(addAddress);

	    menu.addSeparator();
	    viewAddress = new JMenuItem("View an address");
	    menu.add(viewAddress);

	    viewAddresses = new JMenuItem("View all addresses");
	    menu.add(viewAddresses);
	   

	    //a submenu
	    menu.addSeparator();
	    submenu = new JMenu("A submenu");

	    menuItem = new JMenuItem("An item in the submenu");
	    submenu.add(menuItem);

	    menuItem = new JMenuItem("Another item");
	    submenu.add(menuItem);
	    menu.add(submenu);
	    
	    //Quit button
	    menu.addSeparator();
	    quit = new JMenuItem("Quit");
	    menu.add(quit);

	    //Build second menu in the menu bar.
	    menu = new JMenu("Edit");
	    menu.setMnemonic(KeyEvent.VK_N);
	    menu.getAccessibleContext().setAccessibleDescription(
	            "This menu does nothing");
	    menuBar.add(menu);

	    frame.setJMenuBar(menuBar);
	    
	    ActionListener actionListener = new ActionListener() 
	    {
		    public void actionPerformed(ActionEvent e) 
		    {
		    	if (e.getSource() == addButton)
		    	{
		    		System.out.println("Add an address.");
		    	}
		    	
		    	else if (e.getSource() == view_oneButton)
		    	{
		    		System.out.println("View an address.");
		    	}
		    	
		    	else if (e.getSource() == view_allButton)
		    	{
		    		System.out.println("View all addresses.");
		    		viewAll(book);
		    	}
		    	
		    	else if (e.getSource() == quitButton)
		    	{
		    		System.out.println("Quit program.");
		    	}
		    	
		    	else if (e.getSource() == load)
		    	{
		    		System.out.println("load file");
		    		loadFile(frame, book);
		    	}
		    	
		    	else if (e.getSource() == addAddress)
		    	{
		    		addFrame();
		    	}
		    	
		    	else if (e.getSource() == viewAddress)
		    	{
		    		System.out.println("Menu view address.");
		    	}
		    	
		    	else if (e.getSource() == viewAddresses)
		    	{
		    		System.out.println("Menu view addresses.");
		    		viewAll(book);
		    	}
		    	
		    	else if (e.getSource() == quit)
		    	{
		    		System.out.println("Menu quit.");
		    	}
		    }
	    };
	    
	    // Set buttons and menu items to action listener
	    addButton.addActionListener(actionListener);
	    view_oneButton.addActionListener(actionListener);
	    view_allButton.addActionListener(actionListener);
	    quitButton.addActionListener(actionListener);
	    
	    load.addActionListener(actionListener);
	    addAddress.addActionListener(actionListener);
	    viewAddress.addActionListener(actionListener);
	    viewAddresses.addActionListener(actionListener);
	    quit.addActionListener(actionListener);
	    
	    // Customize JPanel
	    JPanel panel = new JPanel();
	    panel.add(addButton);
	    panel.add(view_oneButton);
	    panel.add(view_allButton);
	    panel.add(quitButton);

	    frame.add(panel);
	    frame.setSize(300, 300);
	    frame.setVisible(true);
	}	
	
	/**
	 * Opens new frame to input address data.
	 */
	public void addFrame()
	{
		JFrame frame = new JFrame("Add Address");
		JPanel inputpanel = new JPanel();
		
		JLabel nameButton = new JLabel("Name");
		JTextField nameInput = new JTextField(20);
		JLabel zipButton = new JLabel("ZIP");
		JTextField zipInput = new JTextField(20);
		JLabel numButton = new JLabel("Number");
		JTextField numInput = new JTextField(20);
		
		inputpanel.add(nameButton);
		inputpanel.add(nameInput);
		inputpanel.add(zipButton);
		inputpanel.add(zipInput);
		inputpanel.add(numButton);
		inputpanel.add(numInput);
		
		GroupLayout layout = new GroupLayout(inputpanel);
		inputpanel.setLayout(layout);
		// Turn on automatically adding gaps between components
	    layout.setAutoCreateGaps(true);

	    // Turn on automatically creating gaps between components that touch
	    // the edge of the container and the container.
	    layout.setAutoCreateContainerGaps(true);

		
		frame.add(inputpanel, layout);
		frame.setSize(300, 300);
		frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setResizable(false);
	}
	
	
	void loadFile(JFrame jf, AddressBook bk) {
		final JFileChooser fc = new JFileChooser();
		File open = null;
		String path = null;
		
		fc.showOpenDialog(jf);
		open = fc.getSelectedFile();
		
		try {
			path = open.getCanonicalPath();
		}
		catch (IOException ex) {
			System.out.println( "Invalid file: " + ex.getMessage());
		}
		
		System.out.println("path : " + path);
		try {
			bk.loadFile(path);
		}
		catch (FileNotFoundException ex) {
			System.out.println(" File not found: " + ex.getMessage());
		}
	}
	
	void viewAll(AddressBook bk) {
		System.out.println("INSIDE VIEWALL");
		bk.printAllEntries();
	}
}
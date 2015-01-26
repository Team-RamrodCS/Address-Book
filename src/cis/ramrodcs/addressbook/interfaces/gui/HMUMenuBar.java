package cis.ramrodcs.addressbook.interfaces.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import cis.ramrodcs.addressbook.interfaces.AddAddress;
import cis.ramrodcs.addressbook.interfaces.GUI;
import cis.ramrodcs.addressbook.interfaces.ViewAddress;
import cis.ramrodcs.addressbook.interfaces.listeners.LoadAddressBookListener;
import cis.ramrodcs.addressbook.interfaces.listeners.NewAddressBookListener;
import cis.ramrodcs.addressbook.interfaces.listeners.QuitListener;
import cis.ramrodcs.addressbook.interfaces.listeners.SaveAddressBookListener;

public class HMUMenuBar {

	JMenuBar menuBar;
	
	public HMUMenuBar(GUI gui) {
	    JButton addButton = new JButton("Add Address");
	    JButton view_oneButton = new JButton("View An Address");
	    JButton view_allButton = new JButton("View All Addresses");
	    JButton quitButton = new JButton("Quit Program");
	    JButton searchButton = new JButton("Search");
	    JTextField searchField = new JTextField("Search");
	    

	    //Where the GUI is created:
	    JMenu menu, newSubmenu;
	    JMenuItem newAddress, newBook, newField, openBook, importBook, exportBook, viewAddress, viewAddresses, saveBook, quitBook;
	    
	    //Create the menu bar.
	    menuBar = new JMenuBar();

	    //Build the first menu.
	    menu = new JMenu("File");
	    //menu.setMnemonic(KeyEvent.VK_A);
	    menu.getAccessibleContext().setAccessibleDescription(
	            "The only menu in this program that has menu items");
	    menuBar.add(menu);
	    
	    // A submenu for New 
	    newSubmenu = new JMenu("New");

	    newAddress = new JMenuItem("New Address");
	    newSubmenu.add(newAddress);

	    newBook = new JMenuItem("New Address Book");
	    newSubmenu.add(newBook);
	    
	    newField = new JMenuItem("New User Field");
	    newSubmenu.add(newField);
	    
	    menu.add(newSubmenu);
	    	    
	    openBook = new JMenuItem("Open");
	    menu.add(openBook);

	    menu.addSeparator();
	    
	    importBook = new JMenuItem("Import");
	    menu.add(importBook);
	    
	    exportBook = new JMenuItem("Export");
	    menu.add(exportBook);
	    
	    menu.addSeparator();
	    
	    viewAddress = new JMenuItem("View an address");
	    menu.add(viewAddress);

	    viewAddresses = new JMenuItem("View all addresses");
	    menu.add(viewAddresses);
	    
	    menu.addSeparator();
	    
	    saveBook = new JMenuItem("Save");
	    menu.add(saveBook);
	    
	    quitBook = new JMenuItem("Quit");
	    menu.add(quitBook);

	    //Build second menu in the menu bar.
	    menu = new JMenu("Edit");
	    menu.setMnemonic(KeyEvent.VK_N);
	    menu.getAccessibleContext().setAccessibleDescription(
	            "This menu does nothing");
	    menuBar.add(menu);
	    
	    //Build a third menu for search field.
	    menuBar.add(searchButton);
	    menuBar.add(searchField);
	    
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
		    		ViewAddress.main(null);
		    	}
		    	
		    	else if (e.getSource() == view_allButton)
		    	{
		    		System.out.println("View all addresses.");
		    		gui.viewAll(gui.getCurrentBook());
		    	}
		    	
		    	else if (e.getSource() == newAddress)
		    	{
		    		//addFrame();
		    		AddAddress.main(null);
		    	}
		    	
		    	else if (e.getSource() == newField)
		    	{
		    		System.out.println("New field.");
		    	}
		    	
		    	else if (e.getSource() == importBook)
		    	{
		    		System.out.println("Import book.");
		    	}
		    	
		    	else if (e.getSource() == exportBook)
		    	{
		    		System.out.println("Export book.");
		    	}
		    	
		    	else if (e.getSource() == viewAddress)
		    	{
		    		System.out.println("You've chosen to retrieve an an address.  Please input the full name of the person you'd like to see.");
					gui.addFrame();

		    	}
		    	
		    	else if (e.getSource() == viewAddresses)
		    	{
		    		System.out.println("You've chosen to view all addresses, which are listed below.");
					System.out.println("Number of current contacts: " + gui.getCurrentBook().getEntries().size());
					gui.viewAll(gui.getCurrentBook());
		    	}
		    	
		    	else if (e.getSource() == searchButton)
		    	{
		    		String searched = searchField.getText();
		    		System.out.println(searched);
		    	}
		    }
	    };	    
	    // Set buttons and menu items to action listener
	    addButton.addActionListener(actionListener);
	    view_oneButton.addActionListener(actionListener);
	    view_allButton.addActionListener(actionListener);
	    quitButton.addActionListener(actionListener);

	    
	    newAddress.addActionListener(actionListener);
	    newBook.addActionListener(new NewAddressBookListener(gui));
	    newField.addActionListener(actionListener);
	    openBook.addActionListener(new LoadAddressBookListener(gui));
	    importBook.addActionListener(actionListener);
	    exportBook.addActionListener(actionListener);
	    viewAddress.addActionListener(actionListener); // TODO remove
	    viewAddresses.addActionListener(actionListener); // TODO remove
	    saveBook.addActionListener(new SaveAddressBookListener(gui));
	    quitBook.addActionListener(new QuitListener(gui));
	    searchButton.addActionListener(actionListener);
	}
	
	public JMenuBar getMenuBar() {
		return menuBar;
	}
}

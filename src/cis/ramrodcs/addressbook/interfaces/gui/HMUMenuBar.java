package cis.ramrodcs.addressbook.interfaces.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import cis.ramrodcs.addressbook.interfaces.GUI;
import cis.ramrodcs.addressbook.interfaces.listeners.ImportAddressBookListener;
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
	    newBook = new JMenuItem("New Address Book");
	    menu.add(newBook);
	    	    
	    openBook = new JMenuItem("Open");
	    menu.add(openBook);

	    menu.addSeparator();
	    
	    importBook = new JMenuItem("Import");
	    menu.add(importBook);
	    
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
		    	
		    	if (e.getSource() == searchButton)
		    	{
		    		String searched = searchField.getText();
		    		System.out.println(searched);
		    	}
		    }
	    };	    
	    // Set buttons and menu items to action listener
	    quitButton.addActionListener(actionListener);
	    newBook.addActionListener(new NewAddressBookListener(gui));
	    openBook.addActionListener(new LoadAddressBookListener(gui));
	    importBook.addActionListener(new ImportAddressBookListener(gui));
	    saveBook.addActionListener(new SaveAddressBookListener(gui));
	    quitBook.addActionListener(new QuitListener(gui));
	    searchButton.addActionListener(actionListener);
	}
	
	public JMenuBar getMenuBar() {
		return menuBar;
	}
}

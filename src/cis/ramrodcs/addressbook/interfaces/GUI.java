package cis.ramrodcs.addressbook.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import cis.ramrodcs.addressbook.AddressBook;
import cis.ramrodcs.addressbook.AddressBookGUI;
import cis.ramrodcs.addressbook.interfaces.listeners.LoadAddressBookListener;
import cis.ramrodcs.addressbook.interfaces.listeners.NewAddressBookListener;
import cis.ramrodcs.addressbook.io.FileChooser;
import cis.ramrodcs.addressbook.io.FileType;

/**
 * 
 * @author JeremyLipps
 * 
 * Class that controls the GUI and its associated functions.
 * @see ABInterface
 * 
 */
public class GUI implements ABInterface
{
	
	private ArrayList<AddressBookGUI> books;
	private int currentBook = 0;
    JTabbedPane tabbedPane = new JTabbedPane();

	
	/**
	 * When called, this function starts the GUI interface.
	 */
	@Override
	public void start() 
	{
		books = new ArrayList<AddressBookGUI>();
	    JFrame frame = new JFrame("Address Book");
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    JButton addButton = new JButton("Add Address");
	    JButton view_oneButton = new JButton("View An Address");
	    JButton view_allButton = new JButton("View All Addresses");
	    JButton quitButton = new JButton("Quit Program");
	    JButton searchButton = new JButton("Search");
	    
	    JTextField searchField = new JTextField("Search");
	    
	    //Where the GUI is created:
	    JMenuBar menuBar;
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
		    		ViewAddress.main(null);
		    	}
		    	
		    	else if (e.getSource() == view_allButton)
		    	{
		    		System.out.println("View all addresses.");
		    		viewAll(books.get(currentBook));
		    	}
		    	
		    	else if (e.getSource() == quitButton)
		    	{
		    		System.out.println("Quit program.");
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
					addFrame();

		    	}
		    	
		    	else if (e.getSource() == viewAddresses)
		    	{
		    		System.out.println("You've chosen to view all addresses, which are listed below.");
					System.out.println("Number of current contacts: " + books.get(currentBook).getEntries().size());
					viewAll(books.get(currentBook));
		    	}
		    	
		    	else if (e.getSource() == saveBook)
		    	{
		    		System.out.println("Save book.");
		    	}
		    	
		    	else if (e.getSource() == quitBook)
		    	{
		    		System.out.println("Menu quit.");
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
	    newBook.addActionListener(new NewAddressBookListener(this));
	    newField.addActionListener(actionListener);
	    openBook.addActionListener(new LoadAddressBookListener(this, frame));
	    importBook.addActionListener(actionListener);
	    exportBook.addActionListener(actionListener);
	    viewAddress.addActionListener(actionListener);
	    viewAddresses.addActionListener(actionListener);
	    saveBook.addActionListener(actionListener);
	    quitBook.addActionListener(actionListener);
	    searchButton.addActionListener(actionListener);
	    
	    // Customize main JPanel
	    
	    tabbedPane = new JTabbedPane();

	    // Customize side panel
	    JPanel sidePanel = new JPanel();
	    sidePanel.setOpaque(true);
	    sidePanel.setBackground(Color.blue);
	    
	    // Add panels to the frame
	    frame.add(tabbedPane, BorderLayout.CENTER);
	    frame.add(sidePanel, BorderLayout.WEST);
	    frame.setSize(500, 500);
	    frame.setMinimumSize(new Dimension(300, 300));
	    frame.setVisible(true);
	    
	}	
	
	
	 protected JComponent makeTextPanel(String text) {
         JPanel panel = new JPanel(false);
         JLabel filler = new JLabel(text);
         filler.setHorizontalAlignment(JLabel.CENTER);
         panel.setLayout(new GridLayout(1, 1));
         panel.add(filler);
         return panel;
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
		
		GroupLayout layout = new GroupLayout(inputpanel);
		
		inputpanel.add(nameButton);
		inputpanel.add(nameInput);
		/*
		inputpanel.add(zipButton);
		inputpanel.add(zipInput);
		inputpanel.add(numButton);
		inputpanel.add(numInput);
		*/
		
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
	
	
	public void loadFile(JFrame jf) {
		final FileChooser fc = new FileChooser();
		File open = null;
		String path = null;
		
		fc.showOpenDialog(jf);
		open = fc.getSelectedFile();
		
		if(open == null) {
			return;
		}
		
		try {
			path = open.getCanonicalPath();
		}
		catch (IOException ex) {
			System.out.println( "Invalid file: " + ex.getMessage());
		}
		
		System.out.println("path : " + path);
		try {
			AddressBookGUI bk = new AddressBookGUI();
			bk.loadFile(path, FileType.UPS);
			addAddressBook(bk);
		}
		catch (FileNotFoundException ex) {
			System.out.println(" File not found: " + ex.getMessage());
		}
	}
	
	public void saveFile(JFrame jf) {
		final FileChooser fc = new FileChooser();
		File save = null;
		String path = "";
		
		fc.showOpenDialog(jf);
		save = fc.getSelectedFile();
		
		
		
	}
	
	void viewAll(AddressBook bk) {
		System.out.println("INSIDE VIEWALL");
		bk.printAllEntries();
	}
	
	public void addAddressBook(AddressBookGUI book) {
		books.add(book);
	    JScrollPane scrollPane = new JScrollPane(book.table);
	    tabbedPane.addTab("New Address Book", scrollPane);
	}

	public void tableLayout()
	{
		
	}
}

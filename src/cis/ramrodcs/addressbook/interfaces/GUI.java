package cis.ramrodcs.addressbook.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.awt.Component;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import cis.ramrodcs.addressbook.AddressBook;
import cis.ramrodcs.addressbook.DataEntry;

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
		AddressBook book = new AddressBook();
		
	    JFrame frame = new JFrame("Address Book");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    JButton addButton = new JButton("Add Address");
	    JButton view_oneButton = new JButton("View An Address");
	    JButton view_allButton = new JButton("View All Addresses");
	    JButton quitButton = new JButton("Quit Program");
	    
	    //Where the GUI is created:
	    JMenuBar menuBar;
	    JMenu menu, newSubmenu;
	    JMenuItem addAddress, viewAddress, viewAddresses, quit, menuItem;

	    //Create the menu bar.
	    menuBar = new JMenuBar();

	    //Build the first menu.
	    menu = new JMenu("File");
	    //menu.setMnemonic(KeyEvent.VK_A);
	    menu.getAccessibleContext().setAccessibleDescription(
	            "The only menu in this program that has menu items");
	    menuBar.add(menu);

	    //a group of JMenuItems
	    // A submenu for New
	    
	    newSubmenu = new JMenu("New");

	    menuItem = new JMenuItem("An item in the submenu");
	    newSubmenu.add(menuItem);

	    menuItem = new JMenuItem("Another item");
	    newSubmenu.add(menuItem);
	    menu.add(newSubmenu);
	    
	    menu.addSeparator();
	    
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
		    		ViewAddress.main(null);
		    	}
		    	
		    	else if (e.getSource() == view_allButton)
		    	{
		    		System.out.println("View all addresses.");
		    		
		    	}
		    	
		    	else if (e.getSource() == quitButton)
		    	{
		    		System.out.println("Quit program.");
		    	}
		    	
		    	else if (e.getSource() == addAddress)
		    	{
		    		//addFrame();
		    		AddAddress.main(null);
		    	}
		    	
		    	else if (e.getSource() == viewAddress)
		    	{
		    		System.out.println("You've chosen to retrieve an an address.  Please input the full name of the person you'd like to see.");
					addFrame();
		    		/*String nameRequest = scan.nextLine();
					
					
					boolean found = false;
					
					for (DataEntry contact : book.getEntries())
					{
						if (nameRequest.equals(contact.getField("Name")))
						{
							found = true;
							System.out.println("Here's what we found:");
							System.out.println("Name: " + contact.getField("Name"));
							System.out.println("State: " + contact.getField("State"));
							System.out.println("Phone number: " + contact.getField("Number"));
						}
					}

					if (found == false)
					{
						System.out.println("Contact name was not found.");
					}*/
		    	}
		    	
		    	else if (e.getSource() == viewAddresses)
		    	{
		    		System.out.println("You've chosen to view all addresses, which are listed below.");
					System.out.println("Number of current contacts: " + book.getEntries().size());
					for (DataEntry contact : book.getEntries())
					{
						System.out.println("Name: " + contact.getField("Name"));
						System.out.println("State: " + contact.getField("State"));
						System.out.println("Phone number: " + contact.getField("Number"));
						System.out.println("");
					}
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
	    
	    addAddress.addActionListener(actionListener);
	    viewAddress.addActionListener(actionListener);
	    viewAddresses.addActionListener(actionListener);
	    quit.addActionListener(actionListener);
	    
	    // Customize main JPanel
	    JPanel mainPanel = new JPanel();
	    mainPanel.setOpaque(true);
	    mainPanel.setBackground(Color.black);
	    mainPanel.add(addButton);
	    mainPanel.add(view_oneButton);
	    mainPanel.add(view_allButton);
	    mainPanel.add(quitButton);
	    
	    // Customize side panel
	    JPanel sidePanel = new JPanel();
	    sidePanel.setOpaque(true);
	    sidePanel.setBackground(Color.blue);
	    
	    // Customize bottom panel
	    JPanel bottomPanel = new JPanel();
	    bottomPanel.setOpaque(true);
	    bottomPanel.setBackground(Color.red);
	    
	    // Add panels to the frame
	    frame.add(mainPanel, BorderLayout.CENTER);
	    frame.add(sidePanel, BorderLayout.WEST);
	    frame.add(bottomPanel, BorderLayout.SOUTH);
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
}
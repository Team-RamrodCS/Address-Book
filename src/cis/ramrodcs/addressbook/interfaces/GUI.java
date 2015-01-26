package cis.ramrodcs.addressbook.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import cis.ramrodcs.addressbook.AddressBook;
import cis.ramrodcs.addressbook.AddressBookGUI;
import cis.ramrodcs.addressbook.DataEntry;
import cis.ramrodcs.addressbook.interfaces.gui.HMUDetailViewer;
import cis.ramrodcs.addressbook.interfaces.gui.HMUMainPanel;
import cis.ramrodcs.addressbook.interfaces.gui.HMUMenuBar;
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
	
	
	JFrame window;
	HMUMenuBar menuBar;
	HMUMainPanel mainPanel;
	HMUDetailViewer sidePanel;

	
	/**
	 * When called, this function starts the GUI interface.
	 */
	@Override
	public void start() 
	{
		books = new ArrayList<AddressBookGUI>();
	    window = new JFrame("Address Book");
	    window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    
	    menuBar = new HMUMenuBar(this);
	    mainPanel = new HMUMainPanel();
	    sidePanel = new HMUDetailViewer();

	    window.setJMenuBar(menuBar.getMenuBar());
	    
	    
	    // Add panels to the frame
	    JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sidePanel, mainPanel);
	    split.setOneTouchExpandable(true);
	    split.setPreferredSize(new Dimension(100, 500));
	    split.setResizeWeight(0.3);
	    //window.add(sidePanel, BorderLayout.CENTER);
	    //window.add(sidePanel, BorderLayout.WEST);
	    window.add(split, BorderLayout.CENTER);
	    window.setSize(500, 500);
	    window.setMinimumSize(new Dimension(300, 300));
	    window.setVisible(true);
	    
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
			AddressBookGUI bk = new AddressBookGUI(this);
			bk.loadFile(path, FileType.UPS);
			addAddressBook(bk);
		}
		catch (FileNotFoundException ex) {
			System.out.println(" File not found: " + ex.getMessage());
		}
	}
	
	public void saveFile(JFrame jf) {
		AddressBook bk = getCurrentBook();
		final FileChooser fc = new FileChooser();
		File save = null;
		String path = null;
		
		fc.showSaveDialog(jf);
		save = fc.getSelectedFile();
		String ext = fc.getFileFilter().getDescription();
		
		System.out.println("FILEFILTER: " + ext);
		
		try {
			path = save.getCanonicalPath();
		}
		catch (IOException ex) {
			System.out.println(" Invalid file: " + ex.getMessage());
		}
		
		
		bk.saveFile(path, FileType.valueOf(ext.toUpperCase()));
		
	}
	
	public void viewAll(AddressBook bk) {
		System.out.println("INSIDE VIEWALL");
		bk.printAllEntries();
	}
	
	public void addAddressBook(AddressBookGUI book) {
		books.add(book);
	    JScrollPane scrollPane = new JScrollPane(book.table);
	    mainPanel.addTab("New Address Book", scrollPane);
	}
	
	public AddressBook getCurrentBook() {
		return books.get(currentBook);
	}

	public JFrame getMainWindow() {
		return window;
	}
	
	public void setCurrentEntry(DataEntry entry) {
		sidePanel.setCurrentEntry(entry);
	}

	public DataEntry getEntryAtRow(int rowAtPoint) {
		return getCurrentBook().getEntries().get(rowAtPoint);
	}
}

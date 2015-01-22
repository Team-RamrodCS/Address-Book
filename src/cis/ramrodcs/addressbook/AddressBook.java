package cis.ramrodcs.addressbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Stores lists of entries to use as an Address Book.
 * 
 * @author eewing
 * 
 * @see DataEntry
 *
 */

public class AddressBook {
	final static Charset ENCODING = StandardCharsets.UTF_8;
	final static String WORKINGDIR = "src/cis/ramrodcs/addressbook";
	final static String IN_FILE = WORKINGDIR + "/io/in.txt";
	final static String DEFAULT = WORKINGDIR + "/io/out.txt";
	
	private ArrayList<DataEntry> entries = new ArrayList<DataEntry>();
	private String saveFile = new String("");
	
	/**
	 *  Basic constructor
	 */
	public AddressBook() {
		
	}
	
	/**
	 *  Overloaded constructor for building an address book from file path
	 *  
	 * @param fileStr the default file to load from for this Address Book.
	 */
	public AddressBook (String fileStr){
		try {
			loadFile(fileStr);
		} catch (FileNotFoundException e) {
			System.out.println("Sorry, the file at " + fileStr + " does not exist.");
		}
	}
	
	/**
	 * Adds an entry to the list of data entries for this address book.
	 * 
	 * @param entry the entry to add to this book.
	 */
	public void addDataEntry(DataEntry entry) {
		entries.add(entry);
	}

	
	
	/**
	 * Gets a list of all the entries added to the address book.
	 * 
	 * @return an array list of type DataEntry.
	 */
	public ArrayList<DataEntry> getEntries()
	{
		return entries;
	}

	
	
	/**
	 * Get the string path to the address book's save file
	 *  
	 * @return the string path to the save file
	 */
	public String getSave() {
		return saveFile;
	}
	
	/**
	 * Set the string path for the address book's save file
	 * 
	 * @param fileStr the file to set as the default save location
	 */
	public void setSave(String fileStr) {
		saveFile = fileStr;
	}
	
	/**
	 * Load contents of the address book into an array list.
	 * 
	 * @return an array list representation of the address book
	 */
	public ArrayList<String> toStringArray() {
		ArrayList<String> lines = new ArrayList<String>();
		for(DataEntry element: entries) {
			Map<String,String> fieldVal = element.getEntries();
			String line = new String();
			for (String key : fieldVal.keySet()) {
				line += "\"" + key + ":" + fieldVal.get(key) + "\",";
			}
			lines.add(line);
		}
		return lines;
	}
	
	/**
	 * Load text file into a new address book
	 *  
	 * @param fileStr the file to load from.
	 * @throws FileNotFoundException if <code>fileStr</code> cannot be found on the filesystem.
	 */
	public void loadFile(String fileStr) throws FileNotFoundException {
		/* Load contents of text file into fresh AddressBook */
		File file = new File(fileStr);
		if(!file.exists()) {
			throw new FileNotFoundException("File: '" + fileStr + "' does not exist.");
		}
		Scanner scanner = new Scanner(file);
		
		String[] pairs = {};
		String[] piece = {};
		
		while(scanner.hasNextLine()) {
			/* Parse each line and convert to DataEntry format; Add each entry
			 * to the AddressBook */
			DataEntry entry = new DataEntry();
			String delim1 = "[,]";
			String delim2 = "[:]";
			String element = scanner.nextLine();
			
			if (element.equals("")) {
				continue;
			}
			pairs = element.split(delim1);
			for (int i = 0; i < pairs.length; i++) {
				/* Replace quotations and split for name:val pieces */
				String str = pairs[i].replace("\"", "");
				piece = str.split(delim2);
				entry.addField(piece[0], piece[1]);
			}
			this.addDataEntry(entry);
		}
		scanner.close();
	}
	
	/**
	 * Check for valid save file. If it exists, write contents of address book to said file 
	 *  
	 * @throws FileNotFoundException if the address doesn't have a default save file stored.
	 */
	public void saveFile() throws FileNotFoundException {
		if (getSave().equals("")) {
			throw new FileNotFoundException("This address book has no valid save file.");
		}
		else {
			saveFile(getSave());
		}
	}
	
	/**
	 * Write contents of address book to file
	 * TODO: If file does not exist, prompt to create/save to new file 
	 *  
	 * @param fileStr the file to save to
	 */
	public void saveFile(String fileStr) {
		Path path = Paths.get(fileStr);
		ArrayList<String> lst = toStringArray();
		try {
			Files.write(path, lst, ENCODING);
		} catch (IOException e) {
			System.out.println("Could not save Address Book to file: " + fileStr);
		}
		setSave(fileStr);
	}

}

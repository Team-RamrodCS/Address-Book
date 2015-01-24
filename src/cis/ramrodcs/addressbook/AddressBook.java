package cis.ramrodcs.addressbook;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import cis.ramrodcs.addressbook.io.FileReader;
import cis.ramrodcs.addressbook.io.FileType;
import cis.ramrodcs.addressbook.io.TSVWriter;
import cis.ramrodcs.addressbook.io.readers.HMUReader;
import cis.ramrodcs.addressbook.io.readers.TSVReader;
import cis.ramrodcs.addressbook.io.readers.UPSReader;
import cis.ramrodcs.addressbook.io.writers.FileWriter;
import cis.ramrodcs.addressbook.io.writers.HMUWriter;
import cis.ramrodcs.addressbook.io.writers.UPSWriter;

/**
 * Stores lists of entries to use as an Address Book.
 * 
 * @author eewing
 * @date Jan. 12, 2015
 * 
 * @see DataEntry
 *
 */

public class AddressBook {
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
	 * @param fileStr
	 * @throws IOException
	 */
	public AddressBook (String fileStr) throws IOException {
		loadFile(fileStr);
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
	 */
	public void setSave(String fileStr) {
		saveFile = fileStr;
	}
	
	/**
	 * Load text file into a new address book
	 *  
	 * @param fileStr
	 * @throws IOException
	 */
	public void loadFile(String fileStr) throws FileNotFoundException {
		loadFile(fileStr, FileType.HMU);
	}
	
	public void loadFile(String fileStr, FileType type) throws FileNotFoundException {
		FileReader reader = null;
		switch(type) {
		case TSV:
			reader = new TSVReader(fileStr, this);
			break;
		case UPS:
			reader = new UPSReader(fileStr, this);
			break;
		case HMU:
		default:
			reader = new HMUReader(fileStr, this);
			break;
		}
		reader.read();
	}
	
	/**
	 * Check for valid save file. If it exists, write contents of address book to said file 
	 *  
	 * @throws IOException
	 */
	public void saveFile() throws FileNotFoundException {
		saveFile(FileType.HMU);
		//TODO Instead of assuming HMU file type, save loaded file type when loading file.
	}
	
	public void saveFile(FileType type) throws FileNotFoundException{
		if (getSave().equals("")) {
			throw new FileNotFoundException("This address book has no valid save file.");
		}
		else {
			saveFile(getSave(), type);
		}
	}
	
	public void saveFile(String fileStr, FileType type) {
		FileWriter writer;
		switch(type) {
		case TSV:
			writer = new TSVWriter(this);
			break;
		case UPS:
			writer = new UPSWriter(this);
			break;
		case HMU:
		default:
			writer = new HMUWriter(this);
			break;
		}
		writer.write(fileStr);
		setSave(fileStr);
	}
	
	/**
	 * Write contents of address book to file
	 * TODO: If file does not exist, prompt to create/save to new file 
	 *  
	 * @param fileStr
	 * @throws IOException
	 */
	public void saveFile(String fileStr) {
		saveFile(fileStr, FileType.HMU);
		//TODO Instead of assuming HMU file type, save loaded file type when loading file.
	}

}

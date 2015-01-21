package cis.ramrodcs.addressbook;

import java.util.ArrayList;

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
	
	private ArrayList<DataEntry> entries = new ArrayList<DataEntry>();
	
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

	
	
	// TODO Add a way to get entries through some parameters

}

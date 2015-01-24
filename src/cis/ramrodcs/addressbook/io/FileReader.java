package cis.ramrodcs.addressbook.io;

import java.io.File;
import java.io.FileNotFoundException;

import cis.ramrodcs.addressbook.AddressBook;

/**
 * Abstract class to handle loading different File Formats
 * 
 * @author eewing
 *
 */
public abstract class FileReader {
	
	protected File file;
	protected AddressBook book;
	
	/**
	 * Concrete Constructor
	 * 
	 * @param file the string path to save the file to
	 * @param book the Address Book to save contents after reading
	 * 
	 * @see Address Book
	 */
	public FileReader(String file, AddressBook book) {
		this.file = new File(file);
		this.book = book;
	}
	
	/**
	 * Abstract function that defines how reading files should work.
	 * 
	 * @throws FileNotFoundException when the specified file cannot be found.
	 */
	public abstract void read() throws FileNotFoundException;

}

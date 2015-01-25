package cis.ramrodcs.addressbook.io;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import cis.ramrodcs.addressbook.AddressBook;

/**
 * Abstract class to handle writing different file types
 * 
 * @author eewing
 */
public abstract class FileWriter {
	
	private final static Charset ENCODING = StandardCharsets.UTF_8;
	private FileType type;
	protected AddressBook book;
	
	/**
	 * Concrete Constructor
	 * 
	 * @param book the AddressBook to write.
	 * @param type the FileType to save as
	 * 
	 * @see AddressBook
	 * @see FileType
	 */
	public FileWriter(AddressBook book, FileType type) {
		this.book = book;
		this.type = type;
	}
	
	/**
	 * Writes address book to files using the stored FileType
	 * 
	 * @param fileStr the path to save this address book to
	 * 
	 * @see FileType
	 */
	public void write(String fileStr) {
		ArrayList<String> lst = format();
		Path path = Paths.get(fileStr + type.getExtension());
		try {
			Files.write(path, lst, ENCODING);
		} catch (IOException e) {
			System.out.println("Could not save Address Book to file: " + fileStr);
		}
	}
	
	/**
	 * Abstract function that defines how output parsing should work
	 * 
	 * @return A list of strings to write to a file
	 */
	protected abstract ArrayList<String> format();
}

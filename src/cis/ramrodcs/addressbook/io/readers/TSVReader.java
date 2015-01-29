package cis.ramrodcs.addressbook.io.readers;

import java.io.FileNotFoundException;
import java.util.Scanner;

import cis.ramrodcs.addressbook.AddressBook;
import cis.ramrodcs.addressbook.DataEntry;
import cis.ramrodcs.addressbook.io.FileReader;

/**
 * Loads TSV-style documents into Address Book objects
 * 
 * @author eewing
 *
 */
public class TSVReader extends FileReader {

	/**
	 * Superclass Constructor
	 * 
	 * @param file the file to read an Address Book from.
	 * @param book the Address Book to save read contacts to.
	 */
	public TSVReader(String file, AddressBook book) {
		super(file, book);
	}

	/**
	 * Reads a file into the stored Address Book
	 * 
	 * @throws FileNotFoundException when the stored file cannot be found.
	 */
	@Override
	public void read() throws FileNotFoundException {
		/* Load contents of text file into fresh AddressBook */
		Scanner scanner = new Scanner(file);
		
		String keyStr = "";
		if(scanner.hasNextLine()) {
			keyStr = scanner.nextLine();
		} else {
			scanner.close();
			return;
		}
		
		String keys[] = keyStr.split("\t");
		
		String[] values = {};
		
		while(scanner.hasNextLine()) {
			/* Parse each line and convert to DataEntry format; Add each entry
			 * to the AddressBook */
			DataEntry entry = new DataEntry(book);
			String element = scanner.nextLine();
			
			if (element.equals("")) {
				continue;
			}
			values = element.split("\t", -1);
			for (int i = 0; i < keys.length; i++) {
				if(values[i].equals("")) {
					continue;
				}
				entry.addField(keys[i], values[i]);
			}
			
			// Check to see if the entry already exists
			if (!book.containsEntry(entry)) {
				book.addDataEntry(entry);
			}
		}
		scanner.close();
	}

}

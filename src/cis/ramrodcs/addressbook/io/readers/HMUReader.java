package cis.ramrodcs.addressbook.io.readers;

import java.io.FileNotFoundException;
import java.util.Scanner;

import cis.ramrodcs.addressbook.AddressBook;
import cis.ramrodcs.addressbook.DataEntry;
import cis.ramrodcs.addressbook.io.FileReader;

/**
 * Loads HMU-style documents into Address Book objects
 * 
 * @author eewing
 *
 */
public class HMUReader extends FileReader {

	/**
	 * Superclass Constructor
	 * 
	 * @param file the file to read an Address Book from.
	 * @param book the Address Book to save read contacts to.
	 */
	public HMUReader(String file, AddressBook book) {
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
		
		String[] pairs = {};
		String[] piece = {};
		
		while(scanner.hasNextLine()) {
			/* Parse each line and convert to DataEntry format; Add each entry
			 * to the AddressBook */
			DataEntry entry = new DataEntry(book);
			String delim1 = "[,]";
			String delim2 = "[:]";
			String element = scanner.nextLine();
			System.out.println(element);
			
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
			book.addDataEntry(entry);
		}
		scanner.close();
	}

}

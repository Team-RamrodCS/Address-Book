package cis.ramrodcs.addressbook.io.writers;

import java.util.ArrayList;
import java.util.Map;

import cis.ramrodcs.addressbook.AddressBook;
import cis.ramrodcs.addressbook.DataEntry;

/**
 * Writes address books using the <code>Hit Me Up!</code> File format.
 * 
 * @author eewing
 * 
 * @see FileWriter
 */
public class HMUWriter extends FileWriter {

	/**
	 * Superclass constructor
	 * 
	 * @param book the Address Book to write when <code>write</code> is called.
	 * 
	 * @see FileWriter.FileWriter
	 */
	public HMUWriter(AddressBook book) {
		super(book);
	}

	/**
	 * Formats the stored Address Book to the <code>Hit Me Up!</code> File Format.
	 */
	@Override
	protected ArrayList<String> format() {
		ArrayList<String> lines = new ArrayList<String>();
		for(DataEntry element: book.getEntries()) {
			Map<String,String> fieldVal = element.getEntries();
			String line = new String();
			for (String key : fieldVal.keySet()) {
				line += "\"" + key + ":" + fieldVal.get(key) + "\",";
			}
			lines.add(line);
		}
		return lines;
	}

}

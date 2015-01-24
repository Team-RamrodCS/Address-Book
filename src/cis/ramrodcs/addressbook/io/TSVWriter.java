package cis.ramrodcs.addressbook.io;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;

import cis.ramrodcs.addressbook.AddressBook;
import cis.ramrodcs.addressbook.DataEntry;
import cis.ramrodcs.addressbook.io.writers.FileWriter;

/**
 * Writes address books using the <code>Tab Separated Values</code> File format.
 * 
 * @author eewing
 * 
 * @see FileWriter
 */
public class TSVWriter extends FileWriter {

	/**
	 * Superclass constructor
	 * 
	 * @param book the Address Book to write when <code>write</code> is called.
	 * 
	 * @see FileWriter.FileWriter
	 */
	public TSVWriter(AddressBook book) {
		super(book);
	}


	/**
	 * Formats the stored Address Book to the <code>Tab Separated Values</code> File Format.
	 */
	@Override
	protected ArrayList<String> format() {
		ArrayList<String> lines = new ArrayList<String>();
		LinkedHashSet<String> keys = new LinkedHashSet<String>();
		
		keys.addAll(Arrays.asList(DataEntry.defaultEntries));
		for(DataEntry element : book.getEntries()) {
			keys.addAll(element.getEntries().keySet());
		}
		String keyStr = "";
		for(String key : keys) {
			keyStr += (key+"\t");
		}
		lines.add(keyStr);
		
		for(DataEntry element: book.getEntries()) {
			String line = "";
			for(String key : keys) {
				String value = element.getField(key);
				if(value != null) {
					line += element.getField(key);
				}
				line += "\t";
			}
			lines.add(line);
		}
		return lines;
	}

}

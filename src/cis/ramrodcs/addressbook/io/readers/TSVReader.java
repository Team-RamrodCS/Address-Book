package cis.ramrodcs.addressbook.io.readers;

import java.io.FileNotFoundException;
import java.util.Scanner;

import cis.ramrodcs.addressbook.AddressBook;
import cis.ramrodcs.addressbook.DataEntry;
import cis.ramrodcs.addressbook.io.FileReader;

public class TSVReader extends FileReader {

	public TSVReader(String file, AddressBook book) {
		super(file, book);
	}

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
			DataEntry entry = new DataEntry();
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
			book.addDataEntry(entry);
		}
		scanner.close();
	}

}

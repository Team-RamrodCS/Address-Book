package cis.ramrodcs.addressbook.io.readers;

import java.io.FileNotFoundException;
import java.util.Scanner;

import cis.ramrodcs.addressbook.AddressBook;
import cis.ramrodcs.addressbook.DataEntry;
import cis.ramrodcs.addressbook.io.FileReader;

public class UPSReader extends FileReader {

	public UPSReader(String file, AddressBook book) {
		super(file, book);
	}

	@Override
	public void read() throws FileNotFoundException {		/* Load contents of text file into fresh AddressBook */
		Scanner scanner = new Scanner(file);
		
		
		while(scanner.hasNextLine()) {
			/* Parse each line and convert to DataEntry format; Add each entry
			 * to the AddressBook */
			DataEntry entry = new DataEntry();
			String element = scanner.nextLine();
			
			if (element.equals("")) {
				continue;
			}
			String[] values = element.split("\t", -1);
			
			String[] last = values[0].split(" ", -1);
			String delivery = values[1].toString();
			String second = values[2].toString();
			String[] recip = values[3].split(" ", -1);
			String number = values[4];

			entry.addField("city", last[0]);
			entry.addField("state", last[1]);
			entry.addField("zip", last[2]);
			entry.addField("street address", delivery);
			entry.addField("address2", second);
			entry.addField("first name", recip[0]);
			entry.addField("last name", recip[1]);
			entry.addField("phone number", number);
			
			book.addDataEntry(entry);
		}
		scanner.close();
	}

}

package cis.ramrodcs.addressbook.io.readers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import cis.ramrodcs.addressbook.AddressBook;
import cis.ramrodcs.addressbook.DataEntry;
import cis.ramrodcs.addressbook.io.FileReader;

public class HMUReader extends FileReader {

	public HMUReader(String file, AddressBook book) {
		super(file, book);
	}

	@Override
	public void read() throws FileNotFoundException {
		/* Load contents of text file into fresh AddressBook */
		Scanner scanner = new Scanner(file);
		
		String[] pairs = {};
		String[] piece = {};
		
		while(scanner.hasNextLine()) {
			/* Parse each line and convert to DataEntry format; Add each entry
			 * to the AddressBook */
			DataEntry entry = new DataEntry();
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

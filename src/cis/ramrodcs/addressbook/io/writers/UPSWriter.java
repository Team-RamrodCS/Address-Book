package cis.ramrodcs.addressbook.io.writers;

import java.util.ArrayList;
import java.util.Map;

import cis.ramrodcs.addressbook.AddressBook;
import cis.ramrodcs.addressbook.DataEntry;

public class UPSWriter extends FileWriter {

	public UPSWriter(AddressBook book) {
		super(book);
	}

	@Override
	protected ArrayList<String> format() {
		ArrayList<String> lines = new ArrayList<String>();
		for(DataEntry element: book.getEntries()) {
			String line = "";
			String last = "";
			String delivery = "";
			String second = "";
			String recip = "";
			String phone = "";
			
			if(element.getEntries().containsKey("city")) {
				last += element.getField("city");
			}
			if(element.getEntries().containsKey("state")) {
				last += " " + element.getField("state");
			}
			if(element.getEntries().containsKey("zip")) {
				last += " " + element.getField("zip");
			}
			if(element.getEntries().containsKey("street address")) {
				delivery = element.getField("street address");
			}
			if(element.getEntries().containsKey("address2")) {
				second = element.getField("address2");
			}
			if(element.getEntries().containsKey("first name")) {
				recip += element.getField("first name");
			}
			if(element.getEntries().containsKey("last name")) {
				recip += " " + element.getField("last name");
			}
			if(element.getEntries().containsKey("phone number")) {
				phone = element.getField("phone number");
			}
			
			line += last + "\t" + delivery + "\t" + second + "\t" + recip + "\t" + phone;
	
			lines.add(line);
		}
		return lines;
	}

}

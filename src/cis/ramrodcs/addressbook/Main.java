package cis.ramrodcs.addressbook;

public class Main {
	public static void main( String args[] ) {
		AddressBook book = new AddressBook();
		DataEntry entry = new DataEntry();
		entry.addField("Name", "RamrodCS");
		entry.addField("ZIP", "974xx");
		book.addDataEntry(entry);
		System.out.println(entry.getField("Name"));
		System.out.println(entry.getField("ZIP"));
		System.out.println(entry.getField("Unknown")); // Should be null, since it was never set.
	}
}

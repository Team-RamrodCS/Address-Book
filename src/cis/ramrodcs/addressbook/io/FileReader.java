package cis.ramrodcs.addressbook.io;

import java.io.File;
import java.io.FileNotFoundException;

import cis.ramrodcs.addressbook.AddressBook;

public abstract class FileReader {
	
	protected File file;
	protected AddressBook book;
	
	public FileReader(String file, AddressBook book) {
		this.file = new File(file);
		this.book = book;
	}
	
	public abstract void read() throws FileNotFoundException;

}

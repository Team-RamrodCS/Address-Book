package cis.ramrodcs.addressbook.io;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import cis.ramrodcs.addressbook.AddressBook;

public abstract class FileWriter {
	
	private final static Charset ENCODING = StandardCharsets.UTF_8;
	protected AddressBook book;
	
	public FileWriter(AddressBook book) {
		this.book = book;
	}
	
	public void write(String fileStr) {
		Path path = Paths.get(fileStr);
		ArrayList<String> lst = format();
		try {
			Files.write(path, lst, ENCODING);
		} catch (IOException e) {
			System.out.println("Could not save Address Book to file: " + fileStr);
		}
	}
	
	protected abstract ArrayList<String> format();
}

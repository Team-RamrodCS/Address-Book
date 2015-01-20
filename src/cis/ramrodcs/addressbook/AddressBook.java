package cis.ramrodcs.addressbook;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Stores lists of entries to use as an Address Book.
 * 
 * @author eewing
 * @date Jan. 12, 2015
 * 
 * @see DataEntry
 *
 */

public class AddressBook {
	final static Charset ENCODING = StandardCharsets.UTF_8;
	final static String WORKINGDIR = "src/cis/ramrodcs/addressbook";
	final static String IN_FILE = WORKINGDIR + "/io/in.txt";
	final static String DEFAULT = WORKINGDIR + "/io/out.txt";
	
	private ArrayList<DataEntry> entries = new ArrayList<DataEntry>();
	private String saveFile = new String("");
	
	/**
	 *  Basic constructor
	 */
	public AddressBook() {
		
	}
	
	/**
	 *  Overloaded constructor for building an address book from file path
	 *  
	 * @param fileStr
	 * @throws IOException
	 */
	public AddressBook (String fileStr) throws IOException {
		loadFile(fileStr);
	}
	
	/**
	 * Adds an entry to the list of data entries for this address book.
	 * 
	 * @param entry the entry to add to this book.
	 */
	public void addDataEntry(DataEntry entry) {
		entries.add(entry);
	}
	
	
	/**
	 * Gets a list of all the entries added to the address book.
	 * 
	 * @return an array list of type DataEntry.
	 */
	public ArrayList<DataEntry> getEntries()
	{
		return entries;
	}
	
	/**
	 *  Get the string path to the address book's save file
	 * @return the string path to the save file
	 */
	public String getSave() {
		return saveFile;
	}
	
	/*
	 *  Set the string path for the address book's save file
	 */
	public void setSave(String fileStr) {
		saveFile = fileStr;
	}
	/**
	 * Load contents of the address book into an array list.
	 * 
	 * @return an array list representation of the address book
	 */
	public ArrayList<String> toStringArray() {
		ArrayList<String> lines = new ArrayList<String>();
		for(DataEntry element: entries) {
			Map<String,String> fieldVal = element.getEntries();
			String line = new String();
			for (String key : fieldVal.keySet()) {
				line += "\"" + key + ":" + fieldVal.get(key) + "\",";
			}
			lines.add(line);
		}
		return lines;
	}
	
	// TODO Add a way to get entries through some parameters
	
	/**
	 *  Load text file into a new address book
	 * @param fileStr
	 * @throws IOException
	 */
	public void loadFile(String fileStr) throws IOException {
		/* Load contents of text file into fresh AddressBook */
		File file = null;
		try {
			file = new File(fileStr);
		}
		catch (Exception e) {
			if (e instanceof IOException) {
				System.out.println("File does not exist.");
			}
		}
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
			this.addDataEntry(entry);
		}
		scanner.close();
	}
	
	/**
	 *  Check for valid save file. If it exists, write contents of address book to said file 
	 *  
	 * @throws IOException
	 */
	public void saveFile() throws IOException {
		if (getSave().equals("")) {
			System.out.println("This address book has no valid save file");
		}
		else {
			Path path = null;
			try {
				path = Paths.get(saveFile);
			}
			catch (Exception e) {
				if (e instanceof IOException) {
					System.out.println("There is no file at that location");
				}
			}
			ArrayList<String> lst = toStringArray();
			Files.write(path,lst,ENCODING);
		}
	}
	
	/**
	 *  Write contents of address book to file
	 *  TODO: If file does not exist, prompt to create/save to new file 
	 *  
	 * @param fileStr
	 * @throws IOException
	 */
	public void saveFile(String fileStr) throws IOException {
		Path path = null;
		try {
			path = Paths.get(fileStr);
		}
		catch (Exception e) {
			if (e instanceof IOException) {
				System.out.println("There is no file at that location");
			}
		}
		ArrayList<String> lst = toStringArray();
		Files.write(path, lst, ENCODING);
		setSave(fileStr);
	}

}

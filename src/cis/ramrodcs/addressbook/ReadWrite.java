package cis.ramrodcs.addressbook;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ReadWrite {
	final static Charset ENCODING = StandardCharsets.UTF_8;
	final static String WORKINGDIR = "src/cis/ramrodcs/addressbook";
	final static String IN_FILE = WORKINGDIR + "/io/in.txt";
	final static String OUT_FILE= WORKINGDIR + "/io/out.txt";
	
	public static void main (String[] args) throws IOException {
		ReadWrite doc = new ReadWrite();
		AddressBook book = new AddressBook();
		book.saveFile();
		// read, write, or add to a file 
    	List<String> lines = doc.readFile(IN_FILE);
    	doc.loadFile(IN_FILE, book);
    	//lines.add("added");
		doc.writeFile(lines, OUT_FILE);
		
		// View all AddressBook entries after loading
		for (DataEntry contact : book.getEntries()) {
			System.out.println("Name: " + contact.getField("Name"));
			System.out.println("State: " + contact.getField("State"));
			System.out.println("Phone number: " + contact.getField("Number"));
			System.out.println("");
		}
		
		
		book.toStringArray();
		book.saveFile();
	}

	
	void writeFile (List<String> lines, String file) throws IOException {
		/* Write the list of strings to an output text file */
		Path path = Paths.get(file);
		Files.write(path, lines, ENCODING);
	}

	List<String> readFile (String file) throws IOException {
		/* Read and return contents of text file as a list of strings */
		File file2 = new File(file);
		Path path = Paths.get(file2.getCanonicalPath());
		return Files.readAllLines(path, ENCODING);
	}
	
	void loadFile (String fileStr, AddressBook book) throws IOException {
		/* Load contents of text file into fresh AddressBook */
		File file = new File(fileStr);
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
			book.addDataEntry(entry);
		}
		scanner.close();
	}
	
}

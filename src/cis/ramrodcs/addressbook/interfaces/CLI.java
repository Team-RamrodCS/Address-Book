package cis.ramrodcs.addressbook.interfaces;

import java.io.FileNotFoundException;
import java.util.*;

import cis.ramrodcs.addressbook.AddressBook;
import cis.ramrodcs.addressbook.DataEntry;
import cis.ramrodcs.addressbook.io.FileType;


/**
 * 
 * @author JeremyLipps
 * @date Jan. 19, 2015
 * 
 * Class that controls the CLI and its associated functions.
 * @see ABInterface
 * 
 */
public class CLI implements ABInterface
{
	/**
	 * Starts a command line dialog to navigate address book functions.
	 */
	@Override
	public void start()
	{
		System.out.println("Welcome to your address book!  Please input the number of the operation you'd like to perform: ");
		AddressBook book = new AddressBook();
		Scanner scan = new Scanner(System.in);
		boolean done = false;
		
		while (done == false)
		{
			System.out.println("1.  Add an address");
			System.out.println("2.  View an existing address");
			System.out.println("3.  View all addresses");
			System.out.println("4.  Save book");
			System.out.println("5.  Load book");
			System.out.println("6.  Quit the program");
			
			String option = scan.nextLine();
			
			DataEntry entry = new DataEntry();
			
			if (option.equals("1"))
			{
				System.out.println("You've chosen to add a new address.  With each prompt, please input the appropriate values.");
				System.out.print("First Name:  ");
				String name = scan.nextLine();
				entry.addField("first name", name);
				
				System.out.print("Last Name:  ");
				String lastname = scan.nextLine();
				entry.addField("last name", lastname);
				
				System.out.print("Street Address:  ");
				String address = scan.nextLine();
				entry.addField("street address", address);

				System.out.print("City:  ");
				String city = scan.nextLine();
				entry.addField("city", city);
				
				System.out.print("State:  ");
				String state = scan.nextLine();
				entry.addField("state", state);
				
				System.out.print("Zip Code:  ");
				String zip = scan.nextLine();
				entry.addField("zip", zip);
				
				System.out.print("Phone number:  ");
				String number = scan.nextLine();
				entry.addField("phone number", number);
				
				System.out.println("Would you like to add custom values? ('y' = yes, 'n' = no)");
				String response = scan.nextLine();
				while(!response.equals("n")) {
				//if(response.equals("y")) {
					if(!response.equals("y")) {
						System.out.println("Sorry, I didn't understand that option.");
						response = scan.nextLine();
						continue;
					}
					System.out.println("What field would you like to add?");
					System.out.println("Field Name:");
					String key = scan.nextLine();
					System.out.println("Field Value:");
					String value = scan.nextLine();
					entry.addField(key, value);
					System.out.println("Would you like to add another custom field? ('y' = yes, 'n' = no)");
					response = scan.nextLine();
				}
				
				book.addDataEntry(entry);
				
				System.out.println("Great!  Your new address is: ");
				System.out.println("Name: " + name + ", State: " + state + ", Phone number: " + number);				
			}
			
			else if (option.equals("2"))
			{
				System.out.println("You've chosen to retrieve an an address.  Please input the full name of the person you'd like to see.");
				String nameRequest = scan.nextLine();
				boolean found = false;
				
				for (DataEntry contact : book.getEntries())
				{
					if (nameRequest.equals(contact.getField("Name")))
					{
						found = true;
						System.out.println("Here's what we found:");
						System.out.println("Name: " + contact.getField("Name"));
						System.out.println("State: " + contact.getField("State"));
						System.out.println("Phone number: " + contact.getField("Number"));
					}
				}

				if (found == false)
				{
					System.out.println("Contact name was not found.");
				}
			}
			
			else if (option.equals("3"))
			{
				System.out.println("You've chosen to view all addresses, which are listed below.");
				System.out.println("Number of current contacts: " + book.getEntries().size());
				for (DataEntry contact : book.getEntries())
				{
					for(String key : DataEntry.defaultEntries) {
						System.out.println(key + ": " + contact.getField(key));
					}
					for(String key : contact.getEntries().keySet()) {
						if(!Arrays.asList(DataEntry.defaultEntries).contains(key)) {
							/* Only display custom entries */
							System.out.println(key + ": " + contact.getField(key));
						}
					}
					System.out.println("");

					
				}
			} else if (option.equals("4")) {
				System.out.println("What file do you wish to save to?");
				String file = scan.nextLine();				
				String format = "";
				System.out.println("Please input the file type would you like to save as. ('ups', 'hmu', 'tsv')");
				while(format.equals("")) {
					String in = scan.nextLine();
					if(in.equalsIgnoreCase("UPS") || in.equalsIgnoreCase("HMU") || in.equalsIgnoreCase("TSV")) {
						format = in;
						break;
					} else {
						System.out.println("That file format is not recognized.");
					}
				}
				FileType type = FileType.valueOf(format.toUpperCase());
				System.out.println("Saving file to: " + file);
				book.saveFile(file, type);
				System.out.println("Saved file to: " + file);
			} else if (option.equals("5")) {
				System.out.println("Please input the path to the book to load.");
				String file = scan.nextLine();
				String format = "";
				System.out.println("Please input the file type would you like to load. ('ups', 'hmu', 'tsv')");
				while(format.equals("")) {
					String in = scan.nextLine();
					if(in.equalsIgnoreCase("UPS") || in.equalsIgnoreCase("HMU") || in.equalsIgnoreCase("TSV")) {
						format = in;
						break;
					} else {
						System.out.println("That file format is not recognized.");
					}
				}
				FileType type = FileType.valueOf(format.toUpperCase());
				System.out.println("Loading address book from: " + file);
				book = new AddressBook();
				boolean loaded = true;
				try {
					book.loadFile(file, type);
				} catch (FileNotFoundException e) {
					System.out.println("Could not find file at: " + file);
					loaded = false;
				}
				if(loaded)
					System.out.println("Loaded address book from: " + file);
			} else if (option.equals("6")) {
				done = true;
				System.out.println("Thanks for participating!");
			}
			
			else 
			{
				System.out.println("Sorry, we didn't understand that option.");
			}	
		}
		scan.close();
	}	
}

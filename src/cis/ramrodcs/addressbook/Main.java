package cis.ramrodcs.addressbook;

import java.util.*;

public class Main {
	public static void main( String args[] ) 
	{
		System.out.println("Welcome to your address book!  Please input the number of the operation you'd like to perform: ");
		AddressBook book = new AddressBook();
		Scanner scan = new Scanner(System.in);
		boolean done = false;
		
		while (done == false)
		{
			System.out.println("1.  Add an address");
			System.out.println("2.  View a current address");
			System.out.println("3.  View all addresses");
			System.out.println("4.  Quit the program");
			
			String option = scan.nextLine();
			
			DataEntry entry = new DataEntry();
			
			if (option.equals("1"))
			{
				System.out.println("You've chosen to add a new address.  With each prompt, please input the appropriate values.");
				System.out.print("Full name:  ");
				String name = scan.nextLine();
				entry.addField("Name", name);
				
				System.out.print("State:  ");
				String state = scan.nextLine();
				entry.addField("State", state);
				
				System.out.print("Phone number:  ");
				String number = scan.nextLine();
				entry.addField("Number", number);
				
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
				
				if (found = false)
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
					System.out.println("Name: " + contact.getField("Name"));
					System.out.println("State: " + contact.getField("State"));
					System.out.println("Phone number: " + contact.getField("Number"));
					System.out.println("");
					
				}
			}
			
			else 
			{
				done = true;
				System.out.println("Thanks for participating!");
			}
			
		}
		
		scan.close();
		
		/*
		AddressBook book = new AddressBook();
		List<String> names = Arrays.asList("Jeremy", "Elliott", "Dylan", "Taylor", "Eric");
		List<String> address = Arrays.asList("Oregon", "Washington", "California", "Idaho", "Nevada");
		List<String> number = Arrays.asList("503", "524", "971", "808", "623");
		int length = names.size();
		for (int i = 0; i < length; i++)
		{
			DataEntry entry = new DataEntry();
			entry.addField("Name", names.get(i));
			entry.addField("ZIP", address.get(i));
			entry.addField("Number", number.get(i));
			book.addDataEntry(entry);
			System.out.println(entry.getField("Name"));
			System.out.println(entry.getField("ZIP"));
			System.out.println(entry.getField("Number")); // Should be null, since it was never set.
			System.out.println();
		}
		
		System.out.println(book);
		
		//DataEntry entry = new DataEntry();
		//entry.addField("Name", "RamrodCS");
		//entry1.addField("Name", "Jeremy");
		//entry.addField("ZIP", "974xx");
		//book.addDataEntry(entry);
		//System.out.println(entry.getField("Name"));
		//System.out.println(entry.getField("ZIP"));
		//System.out.println(entry.getField("Unknown")); // Should be null, since it was never set.
		*/
	}
}

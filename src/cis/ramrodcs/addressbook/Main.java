package cis.ramrodcs.addressbook;

import java.util.*;

import cis.ramrodcs.addressbook.interfaces.*;

public class Main {
	private static ABInterface abInterface;

	public static void main( String args[] ) 
	{
		if (Arrays.toString(args).equals("[-cli]"))
		{
			abInterface = new CLI();
		}
		
		else
		{
			abInterface = new GUI();
		}
		
		abInterface.start();
	}
}

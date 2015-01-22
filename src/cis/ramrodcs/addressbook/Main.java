package cis.ramrodcs.addressbook;	

import cis.ramrodcs.addressbook.interfaces.*;

public class Main {
	private static ABInterface abInterface;

	public static void main( String args[] ) 
	{
		abInterface = new CLI();
		abInterface.start();
	}
}

package cis.ramrodcs.addressbook;

import java.util.Comparator;

/**
 * Comparator for custom class DataEntry
 * 
 * @author Dylan Ruggeroli
 * 
 */
public class DataEntryComparator implements Comparator<DataEntry> {
	private String field = null;
	
	/** 
	 * Construct comparator based on search field
	 *  
	 * @param field to search by
	 */
	public DataEntryComparator(String field) {
		this.field = field;
	}
	
	/**
	 * Overloaded compare that supports DataEntry parameters
	 */
	public int compare(DataEntry d1, DataEntry d2) {
		String val1 = d1.getField(field);
		String val2 = d2.getField(field);
		int ret = 0;
		
		if (val1.equals(val2)) {
			val1 = d1.getField("first name");
			val2 = d2.getField("first name");
			
			if (val2.equals(val2)) {
				val1 = d1.getField("last name");
				val2 = d2.getField("last name");
				
				ret = val1.compareTo(val2);
			}
			else {
				ret = val1.compareTo(val2);
			}
		}
		else {
			ret = val1.compareTo(val2);
		}
		
		return ret;
	}
}

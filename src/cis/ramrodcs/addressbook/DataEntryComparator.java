package cis.ramrodcs.addressbook;

import java.util.Comparator;

/**
 * Comparator for sorting data entries by field
 * 
 * @author Dylan Ruggeroli
 * 
 */
public class DataEntryComparator implements Comparator<DataEntry> {
	private String field = null;
	
	public DataEntryComparator(String field) {
		this.field = field;
	}
	
	public int compare(DataEntry d1, DataEntry d2) {
		String val1 = d1.getField(field);
		String val2 = d2.getField(field);
		int ret = 0;
		
		if (val1.equals(val2)) {
			val1 = d1.getField("firstname");
			val2 = d2.getField("firstname");
			
			if (val2.equals(val2)) {
				val1 = d1.getField("lastname");
				val2 = d2.getField("lastname");
				
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

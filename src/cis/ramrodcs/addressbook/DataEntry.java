package cis.ramrodcs.addressbook;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Stores a Map of strings which represent the field values associated with entries into an Address Book
 * 
 * @author eewing
 * 
 * @see AddressBook
 *
 */
public class DataEntry {

	public final static String defaultEntries[] = {"first name", "last name", "street address", "city", "state", "zip", "phone number"};

	private AddressBook book;
	private Map<String, String> fields = new HashMap<String, String>(); 
	// TODO look into other types of maps for more efficient sorting

	public DataEntry(AddressBook book) {
		this.book = book;
	}
	
	/**
	 * Returns the value of a field in this entry.
	 * 
	 * @param key the name of the value to fetch from this DataEntry
	 * 
	 * @return the value of the field given by <code>key</code>. If this DataEntry does not contain
	 * a field named <code>key</code>, then this function returns null.
	 */
	public String getField(String key) {
		String rtn = fields.get(key.toLowerCase());
		return rtn == null ? "" : rtn;
	}

	/**
	 * Sets the field value of key to value.
	 * @param key the field to set to <code>value</code>.
	 * @param value the value to set <code>field</code> to.
	 */
	public void addField(String key, String value) {
		if(value.equals("") || value == null) {
			return;
		}
		fields.put(key.trim().toLowerCase(), value);
	}
	
	public Map<String,String> getEntries() {
		return fields;
	}
	
	public Map<String, String> getCustomEntries() {
		Map<String, String> rtn = new HashMap<String, String>();
		for(String key : fields.keySet()) {
			if(!Arrays.asList(defaultEntries).contains(key)) {
				rtn.put(key, fields.get(key));
			}
		}
		return rtn;
	}
	
	public Map<String, String> getDefaultEntries() {
		Map<String, String> rtn = new LinkedHashMap<String, String>();
		for(String key : defaultEntries) {
			rtn.put(key, fields.get(key));
		}
		return rtn;
	}
}

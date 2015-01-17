package cis.ramrodcs.addressbook;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores a Map of strings which represent the field values associated with entries into an Address Book
 * 
 * @author eewing
 * @date Jan. 13, 2015
 * 
 * @see AddressBook
 *
 */
public class DataEntry {

	private Map<String, String> fields = new HashMap<String, String>(); 
	// TODO look into other types of maps for more efficient sorting

	/**
	 * Returns the value of a field in this entry.
	 * 
	 * @return the value of the field given by <code>key</code>. If this DataEntry does not contain
	 * a field named <code>key</code>, then this function returns null.
	 */
	public String getField(String key) {
		return fields.get(key);
	}

	/**
	 * Sets the field value of key to value.
	 * @param key the field to set to <code>value</code>.
	 * @param value the value to set <code>field</code> to.
	 */
	public void addField(String key, String value) {
		fields.put(key, value);
	}

}

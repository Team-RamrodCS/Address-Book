package cis.ramrodcs.addressbook.io;

/**
 * An enumeration of the types of files Hit Me Up! supports
 * 
 * @author eewing
 *
 */
public enum FileType {
	HMU(".hmu"),
	TSV(".tsv"),
	UPS(".ups");
	
	private String extension;
	
	/**
	 * Constructor for each FileType
	 * 
	 * @param path extension to append to any file written or loaded with this format
	 */
	FileType(String path) {
		extension = path;
	}
	
	/**
	 * Gets the extension associated with this FileType
	 * 
	 * @return extension for any file written with this type
	 */
	public String getExtension() {
		return extension;
	}
}

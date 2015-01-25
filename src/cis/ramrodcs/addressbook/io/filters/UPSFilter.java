package cis.ramrodcs.addressbook.io.filters;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class UPSFilter extends FileFilter {
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
	    }

		String extension = "";

		int i = f.getName().lastIndexOf('.');
		if (i > 0) {
		    extension = f.getName().substring(i+1);
		}	
		
	    if (extension != null) {
	    	if (extension.equals("ups")) {
	                    return true;
	            } else {
	                return false;
	            }
	        }

	        return false;
	    }
	public String getDescription() {
        return ".ups";
    }
}

package cis.ramrodcs.addressbook.io;

import javax.swing.JFileChooser;

import cis.ramrodcs.addressbook.io.filters.HMUFilter;
import cis.ramrodcs.addressbook.io.filters.TSVFilter;
import cis.ramrodcs.addressbook.io.filters.UPSFilter;

public class FileChooser extends JFileChooser {
	public FileChooser() {
		this.addChoosableFileFilter(new UPSFilter());
		this.addChoosableFileFilter(new TSVFilter());
		this.addChoosableFileFilter(new HMUFilter());

		this.setAcceptAllFileFilterUsed(false);
	}

}

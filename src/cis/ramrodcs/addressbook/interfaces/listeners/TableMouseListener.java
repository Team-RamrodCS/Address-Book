package cis.ramrodcs.addressbook.interfaces.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

import cis.ramrodcs.addressbook.interfaces.GUI;

public class TableMouseListener implements MouseListener {
	
	private GUI gui;
	private JTable table;
	
	public TableMouseListener(GUI gui, JTable table) {
		this.gui = gui;
		this.table = table;
	}

	@Override
	public void mouseClicked(MouseEvent event) {}
	@Override
	public void mouseEntered(MouseEvent event) {}
	@Override
	public void mouseExited(MouseEvent event) {}
	@Override
	public void mouseReleased(MouseEvent event) {}

	@Override
	public void mousePressed(MouseEvent event) {
		gui.setCurrentEntry(gui.getEntryAtRow(table.rowAtPoint(event.getPoint())));
	}
}

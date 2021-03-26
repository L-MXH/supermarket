package com.supermarket.model;

public class DeoptModel extends javax.swing.table.DefaultTableModel {
	Class[] types = new Class[] { java.lang.Object.class,
			java.lang.String.class, java.lang.String.class };

	boolean[] canEdit = new boolean[] { false, false, false };

	public DeoptModel() {
		super(new Object[][] {}, new String[] { "±‡∫≈", "ø‚π‹", "√Ë ˆ" });
	}

	public Class getColumnClass(int columIndex) {
		return types[columIndex];
	}

	public boolean isCellEditable(int rowIndex, int columIndex) {
		return canEdit[columIndex];
	}

}

/**
 * ���Ź���ģ��
 */
package com.supermarket.model;

public class StockModel extends javax.swing.table.DefaultTableModel {
	Class[] types = new Class[] { java.lang.Object.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class };

	boolean[] canEdit = new boolean[] { false, false, false };

	public StockModel() {
		super(new Object[][] {}, new String[] { "���", "������", "��Ʒ����", "��������",
				"������", "����", "���" });
	}

	public Class getColumnClass(int columIndex) {
		return types[columIndex];
	}

	public boolean isCellEditable(int rowIndex, int columIndex) {
		return canEdit[columIndex];
	}

}

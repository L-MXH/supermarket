/**
 * ���Ź���ģ��
 */
package com.supermarket.model;

public class DeptModel extends javax.swing.table.DefaultTableModel {
	Class[] types = new Class[] { java.lang.Object.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class };

	boolean[] canEdit = new boolean[] { false, false, false, false };

	public DeptModel() {
		super(new Object[][] {}, new String[] { "���", "��������", "������", "����" });
	}

	public Class getColumnClass(int columIndex) {
		return types[columIndex];
	}

	public boolean isCellEditable(int rowIndex, int columIndex) {
		return canEdit[columIndex];
	}

}

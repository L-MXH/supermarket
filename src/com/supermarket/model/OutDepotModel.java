/**
 * ���ܣ�����ģ��
 * 
 */
package com.supermarket.model;

public class OutDepotModel extends javax.swing.table.DefaultTableModel {
	Class[] types = new Class[] { java.lang.Object.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class };

	boolean[] canEdit = new boolean[] { false, false, false, false, false,
			false, false };

	public OutDepotModel() {
		super(new Object[][] {}, new String[] { "���", "�ֿ���", "��Ʒ����", "����ʱ��",
				"����", "��ע" });
	}

	public Class getColumnClass(int columIndex) {
		return types[columIndex];
	}

	public boolean isCellEditable(int rowIndex, int columIndex) {
		return canEdit[columIndex];
	}

}

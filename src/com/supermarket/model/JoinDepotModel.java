/**
 * ���ܣ����ģ��
 * 
 */
package com.supermarket.model;

public class JoinDepotModel extends javax.swing.table.DefaultTableModel {

	boolean[] canEdit = new boolean[] { false, false, false, false, false,
			false, false };

	public JoinDepotModel() {
		super(new Object[][] {}, new String[] { "���", "������", "�ֿ���", "�ͻ�",
				"���ʱ��", "����", "��ע" });
	}

	public boolean isCellEditable(int rowIndex, int columIndex) {
		return canEdit[columIndex];
	}

}

/**
 * 功能：入库模板
 * 
 */
package com.supermarket.model;

public class JoinDepotModel extends javax.swing.table.DefaultTableModel {

	boolean[] canEdit = new boolean[] { false, false, false, false, false,
			false, false };

	public JoinDepotModel() {
		super(new Object[][] {}, new String[] { "编号", "订单号", "仓库编号", "客户",
				"入库时间", "重量", "备注" });
	}

	public boolean isCellEditable(int rowIndex, int columIndex) {
		return canEdit[columIndex];
	}

}

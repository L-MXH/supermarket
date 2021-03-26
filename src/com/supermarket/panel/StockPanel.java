package com.supermarket.panel;

import java.awt.Color;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;

import com.supermarket.bean.Stock;
import com.supermarket.dao.StockDao;

public class StockPanel extends JPanel {
	private JTable table;

	public StockPanel() {
		this.setBackground(new Color(71, 201, 223));
		setSize(631, 420);
		setLayout(null);
		String[] name = { "客户", "订单号", "交货日期" };
		StockDao dao = new StockDao();
		List list = dao.selectStock();
		Object obj[][] = new Object[list.size()][list.size()];
		for (int i = 0; i < list.size(); i++) {
			Stock stock = (Stock) list.get(i);
			obj[0][i] = stock.getBaleName();
		}
		JButtonTablePanel panel = new JButtonTablePanel();
		panel.setBackground(new Color(71, 201, 223));
		panel.setBounds(0, 0, 631, 420);
		add(panel);
	}
}

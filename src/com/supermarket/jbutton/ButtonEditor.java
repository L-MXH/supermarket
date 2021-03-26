package com.supermarket.jbutton;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;
import com.supermarket.util.GetDate;
import com.supermarket.bean.JoinDepot;
import com.supermarket.bean.Stock;
import com.supermarket.dao.DepotDao;
import com.supermarket.dao.JoinDepotDao;
import com.supermarket.dao.StockDao;
import com.supermarket.mainFrame.RemoveButtonFrame;

public class ButtonEditor extends DefaultCellEditor {

	protected JButton button;
	private String label;
	private boolean isPushed;
	private StockDao dao = new StockDao();

	public ButtonEditor(JCheckBox checkBox) {
		super(checkBox);
		button = new JButton();
		button.setOpaque(true);
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int did = 0;
				try {
					if (button.getText().equals("���")) {
						DepotDao daoA = new DepotDao();
						String[] options = new String[] { "1��", "2��", "3��", };
						String message = "��ѡ��ֿ⣿";
						int num = JOptionPane.showOptionDialog(
								new RemoveButtonFrame(), message, "ѡ��ֿ�",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.INFORMATION_MESSAGE, null, options,
								"1��");
						if (options[num].equals("1��")) {
							did = 1;
						} else if (options[num].equals("2��")) {
							did = 2;
						} else if (options[num].equals("3��")) {
							did = 3;
						}
						File file = new File("files.txt");
						FileInputStream fin = new FileInputStream(file);
						int id = fin.read();
						Stock stock = dao.selectStockByid(id);
						JoinDepot joinDepot = new JoinDepot();
						joinDepot.setoId(stock.getOrderId());
						System.out.println("did:" + did);
						joinDepot.setdId(did);
						joinDepot.setWareName(stock.getsName());
						GetDate getDate = new GetDate();
						String date = getDate.getDateTime();
						joinDepot.setJoinTime(date);
						System.out.println();
						joinDepot.setWeight(Float.parseFloat(stock.getCount()));
						joinDepot.setRemark("��");
						JoinDepotDao jDao = new JoinDepotDao();
						jDao.insertJoinDepot(joinDepot);
						file.delete();

					} else {
						JOptionPane.showMessageDialog(button, "��Ʒ�����");
					}

				} catch (Exception ee) {
					// TODO: handle exception
					ee.printStackTrace();
				}
			}
		});

		// TODO Auto-generated constructor stub
	}

	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int columen) {
		if (isSelected) {
			button.setForeground(table.getSelectionForeground());
			button.setBackground(table.getSelectionBackground());
		} else {
			button.setForeground(table.getForeground());
			button.setBackground(table.getBackground());
		}
		label = (value == null) ? "" : value.toString();
		button.setText(label);
		isPushed = true;
		return button;

	}

	public Object getCellEditorValue() {
		if (isPushed) {
			JOptionPane.showMessageDialog(button, label + ": Ouch!");
		}
		isPushed = false;
		return new String(label);
	}

	public boolean stopCellEditing() {
		isPushed = false;
		return super.stopCellEditing();
	}

	public void fireEditingStopped() {
		super.fireEditingStopped();
	}

}

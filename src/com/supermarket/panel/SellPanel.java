/**
 * 功能：销售商管理
 */
package com.supermarket.panel;

import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import com.supermarket.archives.InsertSellFrame;
import com.supermarket.archives.UpdateSellFrame;
import com.supermarket.bean.Sell;
import com.supermarket.dao.SellDao;
import com.supermarket.model.LocalTableModel;

import static javax.swing.BorderFactory.createTitledBorder;

public class SellPanel extends JPanel {

	private JPanel message;
	private JTextField nameTextField;
	private JTable table;
	private JTextField addressTextField;
	SellDao sellDao = new SellDao();
	LocalTableModel model = new LocalTableModel();

	public SellPanel() {

	}

	public JPanel getMessage() {
		this.setBorder(createTitledBorder(null, "销售商信息",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, new Font(
						"sansserif", Font.BOLD, 12), new Color(59, 59, 59)));

		message = new JPanel();
		message.setBackground(new Color(71, 201, 223));
		message.setBounds(0, 0, 520, 394);
		message.setLayout(null);

		JLabel nameLabel = new JLabel("销售商名称");
		nameLabel.setBounds(10, 34, 63, 15);
		message.add(nameLabel);

		nameTextField = new JTextField();
		nameTextField.setBounds(68, 31, 97, 25);
		message.add(nameTextField);
		nameTextField.setColumns(10);

		JLabel addresLlabel = new JLabel("地址");
		addresLlabel.setBounds(169, 34, 38, 15);
		message.add(addresLlabel);

		addressTextField = new JTextField();
		addressTextField.setBounds(204, 31, 119, 25);
		message.add(addressTextField);
		addressTextField.setColumns(10);

		JButton findButton = new JButton("搜索");
		findButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				model.setRowCount(0);
				String name = nameTextField.getText();
				String address = addressTextField.getText();
				if ((name.equals("")) && (address.equals(""))) {
					JOptionPane.showMessageDialog(getParent(), "请填写查询条件",
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if ((!name.equals("")) && (address.equals(""))) {
					List list = sellDao.selectsellByName(name);
					for (int i = 0; i < list.size(); i++) {
						Sell sell = (Sell) list.get(i);
						model.addRow(new Object[] { sell.getId(),
								sell.getSellName(), sell.getAddress(),
								sell.getLinkman(), sell.getLinkPhone(),
								sell.getFaxNum(), sell.getPostNum(),
								sell.getBankNum(), sell.getNetAddress(),
								sell.getEmaillAddress(), sell.getRemark() });

					}
				}
				if ((name.equals("")) && (!address.equals(""))) {
					List list = sellDao.selectsellByName(name);
					for (int i = 0; i < list.size(); i++) {
						Sell sell = (Sell) list.get(i);
						model.addRow(new Object[] { sell.getId(),
								sell.getSellName(), sell.getAddress(),
								sell.getLinkman(), sell.getLinkPhone(),
								sell.getFaxNum(), sell.getPostNum(),
								sell.getBankNum(), sell.getNetAddress(),
								sell.getEmaillAddress(), sell.getRemark() });

					}
				}
				if ((!name.equals("")) && (!address.equals(""))) {
					List list = sellDao.selectsellByName(name);
					for (int i = 0; i < list.size(); i++) {
						Sell sell = (Sell) list.get(i);
						model.addRow(new Object[] { sell.getId(),
								sell.getSellName(), sell.getAddress(),
								sell.getLinkman(), sell.getLinkPhone(),
								sell.getFaxNum(), sell.getPostNum(),
								sell.getBankNum(), sell.getNetAddress(),
								sell.getEmaillAddress(), sell.getRemark() });

					}
				}
			}
		});

		findButton.setBounds(333, 30, 77, 23);
		message.add(findButton);

		JButton insertButton = new JButton("添加");
		insertButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				InsertSellFrame insertSell = new InsertSellFrame();
				insertSell.setVisible(true);
			}
		});
		insertButton.setBounds(51, 313, 77, 23);
		message.add(insertButton);

		JButton updateButton = new JButton("修改");
		updateButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = table.getSelectedRow();

				if (row < 0) {
					JOptionPane.showMessageDialog(getParent(), "没有选择要修改的数据！",
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				} else {
					File file = new File("file.txt");
					try {
						String column = model.getValueAt(row, 0).toString();
						file.createNewFile();
						FileOutputStream out = new FileOutputStream(file);
						out.write((Integer.parseInt(column)));
						UpdateSellFrame updateSell = new UpdateSellFrame();
						updateSell.setVisible(true);
						repaint();
					} catch (Exception ee) {
						// TODO: handle exception
						ee.printStackTrace();
					}
				}
			}
		});
		updateButton.setBounds(169, 313, 77, 23);
		message.add(updateButton);

		JButton deleteButton = new JButton("删除");
		deleteButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = table.getSelectedRow();
				String column = model.getValueAt(row, 0).toString();
				if (Integer.parseInt(column) < 0) {
					JOptionPane.showMessageDialog(getParent(), "没有选择要删除的数据！",
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				} else {
					sellDao.deleteSell(Integer.parseInt(column));
					JOptionPane.showMessageDialog(getParent(), "数据删除成功！",
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);
					repaint();
				}
			}
		});
		deleteButton.setBounds(285, 313, 77, 23);
		message.add(deleteButton);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 70, 416, 213);
		message.add(scrollPane_2);

		// scrollPane_2.setViewportView(table);
		table = new JTable(model);
		repaint();
		List list = sellDao.selectSell();
		model.setRowCount(0);
		for (int i = 0; i < list.size(); i++) {
			Sell sell = (Sell) list.get(i);
			model.addRow(new Object[] { sell.getId(), sell.getSellName(),
					sell.getAddress(), sell.getLinkman(), sell.getLinkPhone(),
					sell.getFaxNum(), sell.getPostNum(), sell.getBankNum(),
					sell.getNetAddress(), sell.getEmaillAddress(),
					sell.getRemark() });

		}
		scrollPane_2.setViewportView(table);

		return message;
	}
}

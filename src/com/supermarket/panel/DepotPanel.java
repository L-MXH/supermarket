/**
 * 功能：仓库管理
 */
package com.supermarket.panel;

import static javax.swing.BorderFactory.createTitledBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import static javax.swing.BorderFactory.createTitledBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.supermarket.archives.InsertDepotFrame;
import com.supermarket.archives.UpdateDepotFrame;
import com.supermarket.archives.UpdateWareFrame;
import com.supermarket.bean.Depot;
import com.supermarket.bean.Provide;
import com.supermarket.bean.Ware;
import com.supermarket.dao.DepotDao;
import com.supermarket.model.DeptModel;

public class DepotPanel extends JPanel {
	private JPanel message;
	private JTextField idTextField;
	private JTable table;
	private JTextField managerTextField;
	DepotDao dao = new DepotDao();
	DeptModel model = new DeptModel();

	public DepotPanel() {

	}

	public JPanel getMessage() {
		this.setBorder(createTitledBorder(null, "仓库信息",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, new Font(
						"sansserif", Font.BOLD, 12), new Color(59, 59, 59)));

		message = new JPanel();
		message.setBackground(new Color(71, 201, 223));
		message.setBounds(0, 0, 520, 394);
		message.setLayout(null);

		JLabel nameLabel = new JLabel("仓库编号");
		nameLabel.setBounds(10, 34, 54, 15);
		message.add(nameLabel);

		idTextField = new JTextField();
		idTextField.setBounds(62, 31, 97, 25);
		message.add(idTextField);
		idTextField.setColumns(10);

		JLabel addresLabel = new JLabel("库管");
		addresLabel.setBounds(204, 31, 119, 25);
		message.add(addresLabel);

		managerTextField = new JTextField();
		managerTextField.setBounds(204, 31, 119, 25);
		message.add(managerTextField);
		managerTextField.setColumns(10);

		// JButton findButton = new JButton("搜索");
		// findButton.setBounds(333, 30, 77, 23);
		// message.add(findButton);

		JButton findButton = new JButton("搜索");
		findButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				String id = idTextField.getText();
				String manager = managerTextField.getText();
				if ((id.equals("")) && (manager.equals(""))) {
					JOptionPane.showMessageDialog(getParent(), "请填写查询条件！",
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if ((!id.equals("")) && (manager.equals(""))) {
					int idint = 0;
					try {
						idint = Integer.parseInt(id);
					} catch (Exception ee) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(getParent(),
								"输入的编号要是整数类型", "信息提示框",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					Depot depot = dao.selectDepotByid(idint);
					model.addRow(new Object[] { depot.getId(),
							depot.getManage(), depot.getFunctional() });
				}
				if ((id.equals("")) && (!manager.equals(""))) {
					List list = dao.selectDepotByManage(manager);
					for (int i = 0; i < list.size(); i++) {
						Depot depot = (Depot) list.get(i);
						model.addRow(new Object[] { depot.getId(),
								depot.getManage(), depot.getFunctional() });
					}
				}
				if ((!id.equals("")) && (!manager.equals(""))) {
					int idint = 0;
					try {
						idint = Integer.parseInt(id);
					} catch (Exception ee) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(getParent(),
								"输入的编号要是整数类型", "信息提示框",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					List list = dao.selectDepotByManageAndId(idint, manager);
					for (int i = 0; i < list.size(); i++) {
						Depot depot = (Depot) list.get(i);
						model.addRow(new Object[] { depot.getId(),
								depot.getManage(), depot.getFunctional() });

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
				InsertDepotFrame depot = new InsertDepotFrame();
				depot.setVisible(true);
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
						out.close();
						UpdateDepotFrame update = new UpdateDepotFrame();
						update.setVisible(true);
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
					dao.deleteDepot(Integer.parseInt(column));
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

		table = new JTable(model);
		repaint();
		List list = dao.selectDepot();
		model.setRowCount(0);
		for (int i = 0; i < list.size(); i++) {
			Depot depot = (Depot) list.get(i);
			model.addRow(new Object[] { depot.getId(), depot.getManage(),
					depot.getFunctional() });

		}
		scrollPane_2.setViewportView(table);

		// scrollPane_2.setViewportView(table);

		return message;
	}
}

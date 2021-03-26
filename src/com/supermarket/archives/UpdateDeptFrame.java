package com.supermarket.archives;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.supermarket.bean.Depot;
import com.supermarket.bean.Dept;
import com.supermarket.bean.Ware;
import com.supermarket.dao.DepotDao;
import com.supermarket.dao.DeptDao;
import com.supermarket.dao.WareDao;

public class UpdateDeptFrame extends JFrame {

	private JPanel contentPane;
	private JTextField dNameTextField;
	private JLabel unitLabel;
	private JButton closeButton;
	private JLabel starLabel;
	JTextArea textArea = new JTextArea();
	private JTextField managerTextField;
	DeptDao dao = new DeptDao();
	Dept dept = new Dept();

	public UpdateDeptFrame() {
		setTitle("�޸Ĳ�����Ϣ����");
		setBounds(100, 100, 635, 267);
		try {
			File file = new File("file.txt");
			FileInputStream fin = new FileInputStream(file);
			int count = fin.read();
			file.delete();
			dept = dao.selectDeptByid(count);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel cNameLabel = new JLabel("�������ƣ�");
		cNameLabel.setBounds(39, 55, 65, 15);
		contentPane.add(cNameLabel);

		dNameTextField = new JTextField();
		dNameTextField.setBounds(114, 50, 164, 25);
		contentPane.add(dNameTextField);
		dNameTextField.setText(dept.getdName());
		dNameTextField.setColumns(10);

		unitLabel = new JLabel("������");
		unitLabel.setBounds(39, 121, 54, 15);
		contentPane.add(unitLabel);

		JButton insertButton = new JButton("�޸�");
		insertButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DeptDao dao = new DeptDao();
				String dName = dNameTextField.getText();
				String manager = managerTextField.getText();

				if (dName.equals("") || (manager.equals(""))) {
					JOptionPane.showMessageDialog(getContentPane(),
							"�����Ǻŵ���Ϣ��д������", "��Ϣ��ʾ��",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				dept.setdName(dName);
				dept.setPrincipal(manager);
				dept.setBewrite(textArea.getText());
				dao.insertDept(dept);
				JOptionPane.showMessageDialog(getContentPane(), "�������ӳɹ���",
						"��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);

			}

		});
		insertButton.setBounds(210, 196, 93, 23);
		contentPane.add(insertButton);

		closeButton = new JButton("�˳�");
		closeButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				do_closeButton_actionPerformed(e);
			}

		});
		closeButton.setBounds(365, 196, 93, 23);
		contentPane.add(closeButton);

		starLabel = new JLabel("*");
		starLabel.setForeground(Color.RED);
		starLabel.setBounds(283, 53, 6, 15);
		contentPane.add(starLabel);

		textArea.setBounds(114, 93, 435, 82);
		contentPane.add(textArea);
		textArea.setText(dept.getBewrite());

		JLabel managerLabel = new JLabel("������");
		managerLabel.setBounds(308, 55, 54, 15);
		contentPane.add(managerLabel);

		managerTextField = new JTextField();
		managerTextField.setText(dept.getPrincipal());
		managerTextField.setColumns(10);
		managerTextField.setBounds(361, 52, 164, 25);
		contentPane.add(managerTextField);

		JLabel label = new JLabel("*");
		label.setForeground(Color.RED);
		label.setBounds(535, 55, 6, 15);
		contentPane.add(label);

	}

	protected void do_closeButton_actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}
}
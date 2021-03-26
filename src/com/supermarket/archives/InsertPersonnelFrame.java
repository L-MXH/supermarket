package com.supermarket.archives;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.supermarket.bean.BasicMessage;
import com.supermarket.bean.Contact;
import com.supermarket.bean.Dept;
import com.supermarket.bean.Headship;
import com.supermarket.bean.JoinDepot;
import com.supermarket.bean.Provide;
import com.supermarket.bean.Stock;
import com.supermarket.dao.DeptDao;
import com.supermarket.dao.FeelDao;
import com.supermarket.dao.JoinDepotDao;
import com.supermarket.dao.PersonnelDao;
import com.supermarket.dao.StockDao;

public class InsertPersonnelFrame extends JFrame {

	private JPanel contentPane;
	private JTextField nameTextField;
	private JTextField ageTextField;
	private JTextField contactTextField;
	private JTextField officePhoneTextField;
	private JTextField faxTextField;
	private JTextField emailJTextField;
	private JTextField addressTextField;
	private PersonnelDao dao = new PersonnelDao();
	BasicMessage message = new BasicMessage();
	int hid = 0;
	String nameMessage = "";

	/**
	 * 
	 */
	public InsertPersonnelFrame() {
		setTitle("添加员工信息窗体");
		setBounds(100, 100, 512, 284);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 496, 246);
		contentPane.add(tabbedPane);

		message.setSex("男");
		JPanel panel = new JPanel();
		tabbedPane.addTab("基本信息", null, panel, null);
		panel.setLayout(null);

		JLabel nameLabel = new JLabel("姓名：");
		nameLabel.setBounds(37, 24, 44, 15);
		panel.add(nameLabel);

		nameTextField = new JTextField();
		nameTextField.setBounds(85, 19, 133, 25);
		panel.add(nameTextField);
		nameTextField.setColumns(10);

		JLabel ageLabel = new JLabel("年龄：");
		ageLabel.setBounds(251, 24, 44, 15);
		panel.add(ageLabel);

		ageTextField = new JTextField();
		ageTextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent event) {
				char ch = event.getKeyChar();
				if ((ch < '0' || ch > '9')) {
					event.consume();
				}
			}
		});
		ageTextField.setColumns(10);
		ageTextField.setBounds(290, 19, 133, 25);
		panel.add(ageTextField);

		JLabel sexLabel = new JLabel("性别：");
		sexLabel.setBounds(37, 65, 44, 15);
		panel.add(sexLabel);

		final JRadioButton manRadioButton = new JRadioButton("男");
		manRadioButton.setSelected(true);
		manRadioButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				message.setSex(manRadioButton.getText());
			}
		});
		manRadioButton.setBounds(85, 61, 44, 23);
		panel.add(manRadioButton);

		final JRadioButton wRadioButton = new JRadioButton("女");
		wRadioButton.setSelected(true);
		wRadioButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				message.setSex(wRadioButton.getText());
			}
		});
		wRadioButton.setBounds(131, 61, 44, 23);
		panel.add(wRadioButton);

		ButtonGroup group = new ButtonGroup();
		group.add(manRadioButton);
		group.add(wRadioButton);

		JLabel deptLabel = new JLabel("部门：");
		deptLabel.setBounds(251, 65, 44, 15);
		panel.add(deptLabel);

		DeptDao deptDao = new DeptDao();
		List list = deptDao.selectDept();
		String[] dName = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			Dept dept = (Dept) list.get(i);
			dName[i] = dept.getdName();
		}
		final JComboBox deptComboBox = new JComboBox(dName);
		deptComboBox.setBounds(290, 65, 133, 21);
		panel.add(deptComboBox);

		JLabel headshipLabel = new JLabel("职务：");
		headshipLabel.setBounds(37, 109, 44, 15);
		panel.add(headshipLabel);

		List headName = dao.selectHeadship();
		String headship[] = new String[headName.size()];
		for (int j = 0; j < headName.size(); j++) {
			Headship headshi = (Headship) headName.get(j);
			headship[j] = headshi.getHeadshipName();
		}
		final JComboBox headshipComboBox = new JComboBox(headship);
		headshipComboBox.setBounds(85, 106, 133, 21);
		panel.add(headshipComboBox);

		JButton insertutton = new JButton("添加");
		insertutton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = nameTextField.getText();
				String age = ageTextField.getText();
				String dept = deptComboBox.getSelectedItem().toString();
				String headship = headshipComboBox.getSelectedItem().toString();
				int id = dao.selectIdByHeadship(headship);
				if ((name.equals("")) || (age.equals(""))) {
					JOptionPane.showMessageDialog(getContentPane(),
							"将带星号的信息填写完整！", "信息提示框",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				int ageid = Integer.parseInt(age);
				DeptDao depotDao = new DeptDao();
				Dept dpt = depotDao.selectDeptByName(dept);

				message.setName(name);
				message.setAge(ageid);
				message.setDept(dpt.getId());
				message.setHeadship(id);
				dao.insertBasicMessage(message);
				nameMessage = "ok";

				JOptionPane.showMessageDialog(getContentPane(), "将信息添加成功！",
						"信息提示框", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		insertutton.setBounds(112, 163, 93, 23);
		panel.add(insertutton);

		JButton closeButton = new JButton("关闭");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				do_closeButton_actionPerformed(e);
			}
		});
		closeButton.setBounds(251, 163, 93, 23);
		panel.add(closeButton);

		JLabel messageLabel = new JLabel("*");
		messageLabel.setForeground(Color.RED);
		messageLabel.setBounds(228, 24, 15, 15);
		panel.add(messageLabel);

		JLabel label_1 = new JLabel("*");
		label_1.setForeground(Color.RED);
		label_1.setBounds(228, 65, 15, 15);
		panel.add(label_1);

		JLabel label_2 = new JLabel("*");
		label_2.setForeground(Color.RED);
		label_2.setBounds(433, 65, 15, 15);
		panel.add(label_2);

		JLabel label_3 = new JLabel("*");
		label_3.setForeground(Color.RED);
		label_3.setBounds(228, 109, 15, 15);
		panel.add(label_3);

		JPanel panel2 = new JPanel();
		tabbedPane.addTab("联系资料", null, panel2, null);
		panel2.setLayout(null);

		JLabel contactlLabel = new JLabel("手机：");
		contactlLabel.setBounds(28, 28, 48, 15);
		panel2.add(contactlLabel);

		contactTextField = new JTextField();
		contactTextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent event) {
				char ch = event.getKeyChar();
				if ((ch < '0' || ch > '9')) {
					event.consume();
				}
			}
		});
		contactTextField.setColumns(10);
		contactTextField.setBounds(86, 23, 133, 25);
		panel2.add(contactTextField);

		JLabel opgoneLLabel = new JLabel("办公电话：");
		opgoneLLabel.setBounds(233, 28, 73, 15);
		panel2.add(opgoneLLabel);

		officePhoneTextField = new JTextField();
		officePhoneTextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent event) {
				char ch = event.getKeyChar();
				if ((ch < '0' || ch > '9')) {
					event.consume();
				}
			}
		});
		officePhoneTextField.setColumns(10);
		officePhoneTextField.setBounds(307, 23, 133, 25);
		panel2.add(officePhoneTextField);

		JLabel faxLabel = new JLabel("传真：");
		faxLabel.setBounds(28, 72, 48, 15);
		panel2.add(faxLabel);

		faxTextField = new JTextField();
		faxTextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent event) {
				char ch = event.getKeyChar();
				if ((ch < '0' || ch > '9')) {
					event.consume();
				}
			}
		});
		faxTextField.setColumns(10);
		faxTextField.setBounds(86, 68, 133, 25);
		panel2.add(faxTextField);

		JLabel label = new JLabel("邮箱：");
		label.setBounds(257, 72, 49, 15);
		panel2.add(label);

		emailJTextField = new JTextField();
		emailJTextField.setBounds(307, 67, 133, 25);
		panel2.add(emailJTextField);
		emailJTextField.setColumns(10);

		JLabel addressLabel = new JLabel("住址：");
		addressLabel.setBounds(26, 117, 48, 15);
		panel2.add(addressLabel);

		addressTextField = new JTextField();
		addressTextField.setBounds(86, 114, 133, 25);
		panel2.add(addressTextField);
		addressTextField.setColumns(10);

		JButton insertbutton = new JButton("添加");
		insertbutton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (nameMessage.equals("")) {
					JOptionPane.showMessageDialog(getContentPane(),
							"请先添加用户基本信息！", "信息提示框",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				Contact contact = new Contact();
				hid = dao.selectBasicMessageByName(nameTextField.getText());
				contact.setHid(hid);
				contact.setContact(contactTextField.getText());
				contact.setOfficePhone(officePhoneTextField.getText());
				contact.setFax(faxTextField.getText());
				contact.setEmail(emailJTextField.getText());
				contact.setFaddress(addressTextField.getText());
				dao.insertContact(contact);

				JOptionPane.showMessageDialog(getContentPane(), "数据添加成功！",
						"信息提示框", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		insertbutton.setBounds(126, 162, 93, 23);
		panel2.add(insertbutton);

		JButton closeButton2 = new JButton("关闭");
		closeButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				do_closeButton_actionPerformed(e);
			}
		});
		closeButton2.setBounds(257, 162, 93, 23);
		panel2.add(closeButton2);

	}

	protected void do_closeButton_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}
}

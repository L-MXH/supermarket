package com.supermarket.archives;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.supermarket.bean.Sell;
import com.supermarket.dao.SellDao;

public class InsertSellFrame extends JFrame {

	private JPanel contentPane;
	private JTextField cNameTextField;
	private JTextField addressTextField;
	private JLabel linkNameLabel;
	private JTextField linkNameTextField;
	private JLabel linkPhoneLabel;
	private JTextField linkPhoneTextField;
	private JLabel fexesLabel;
	private JTextField faxesTextField;
	private JLabel postNumLabel;
	private JTextField postNumTextField;
	private JLabel bankNumLabel;
	private JTextField bankNumTextField;
	private JLabel netAddressLabel;
	private JTextField netAddressTextField;
	private JLabel emailLabel;
	private JTextField emailTextField;
	private JLabel label;
	private JButton closeButton;
	JTextArea remarkTextArea = new JTextArea();
	private JLabel starLabel;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;

	public InsertSellFrame() {
		setTitle("添加销售商窗体");
		setBounds(100, 100, 635, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel cNameLabel = new JLabel("销售商名称：");
		cNameLabel.setBounds(35, 53, 86, 15);
		contentPane.add(cNameLabel);

		cNameTextField = new JTextField();
		cNameTextField.setBounds(114, 50, 164, 25);
		contentPane.add(cNameTextField);
		cNameTextField.setColumns(10);

		JLabel addressLabel = new JLabel("销售商地址：");
		addressLabel.setBounds(301, 53, 86, 15);
		contentPane.add(addressLabel);

		addressTextField = new JTextField();
		addressTextField.setBounds(385, 50, 164, 25);
		contentPane.add(addressTextField);
		addressTextField.setColumns(10);

		linkNameLabel = new JLabel("联系人：");
		linkNameLabel.setBounds(49, 97, 70, 15);
		contentPane.add(linkNameLabel);

		linkNameTextField = new JTextField();
		linkNameTextField.setBounds(114, 94, 164, 25);
		contentPane.add(linkNameTextField);
		linkNameTextField.setColumns(10);

		linkPhoneLabel = new JLabel("联系电话：");
		linkPhoneLabel.setBounds(315, 97, 72, 15);
		contentPane.add(linkPhoneLabel);

		linkPhoneTextField = new JTextField();
		linkPhoneTextField.setBounds(385, 94, 164, 25);
		contentPane.add(linkPhoneTextField);
		linkPhoneTextField.setColumns(10);

		fexesLabel = new JLabel("传真：");
		fexesLabel.setBounds(50, 140, 54, 15);
		contentPane.add(fexesLabel);

		faxesTextField = new JTextField();
		faxesTextField.setBounds(114, 137, 164, 25);
		contentPane.add(faxesTextField);
		faxesTextField.setColumns(10);

		postNumLabel = new JLabel("邮编：");
		postNumLabel.setBounds(321, 140, 54, 15);
		contentPane.add(postNumLabel);

		postNumTextField = new JTextField();
		postNumTextField.setBounds(385, 137, 164, 25);
		contentPane.add(postNumTextField);
		postNumTextField.setColumns(10);

		bankNumLabel = new JLabel("银行账号：");
		bankNumLabel.setBounds(49, 180, 72, 15);
		contentPane.add(bankNumLabel);

		bankNumTextField = new JTextField();
		bankNumTextField.setBounds(114, 177, 164, 25);
		contentPane.add(bankNumTextField);
		bankNumTextField.setColumns(10);

		netAddressLabel = new JLabel("网址：");
		netAddressLabel.setBounds(315, 180, 60, 15);
		contentPane.add(netAddressLabel);

		netAddressTextField = new JTextField();
		netAddressTextField.setBounds(385, 177, 164, 25);
		contentPane.add(netAddressTextField);
		netAddressTextField.setColumns(10);

		emailLabel = new JLabel("邮箱：");
		emailLabel.setBounds(49, 223, 72, 15);
		contentPane.add(emailLabel);

		emailTextField = new JTextField();
		emailTextField.setBounds(114, 220, 164, 25);
		contentPane.add(emailTextField);
		emailTextField.setColumns(10);

		label = new JLabel("备注：");
		label.setBounds(49, 294, 72, 15);
		contentPane.add(label);

		remarkTextArea.setBounds(114, 267, 435, 89);
		contentPane.add(remarkTextArea);

		JButton insertButton = new JButton("添加");
		insertButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SellDao selldao = new SellDao();
				Sell sell = new Sell();
				String sellName = cNameTextField.getText();
				String address = addressTextField.getText();
				String bankNum = bankNumTextField.getText();
				String linkName = linkNameTextField.getText();
				String linkPhone = linkPhoneTextField.getText();
				String faxes = faxesTextField.getText();
				String netAddress = netAddressTextField.getText();
				String emaillAddress = emailTextField.getText();
				if ((sellName.equals("")) || (address.equals(""))
						|| (bankNum.equals("")) || (linkName.equals(""))
						|| (linkPhone.equals("")) || (faxes.equals(""))) {
					JOptionPane.showMessageDialog(getContentPane(),
							"将带星号的信息填写完整！", "信息提示框",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				sell.setSellName(sellName);
				sell.setAddress(address);
				sell.setBankNum(bankNumTextField.getText());
				sell.setLinkman(linkName);
				sell.setLinkPhone(linkPhone);
				sell.setFaxNum(faxes);
				sell.setPostNum(postNumTextField.getText());
				sell.setNetAddress(netAddress);
				sell.setEmaillAddress(emailTextField.getText());
				sell.setRemark(remarkTextArea.getText());
				selldao.insertSell(sell);
				JOptionPane.showMessageDialog(getContentPane(), "数据添加成功！",
						"信息提示框", JOptionPane.INFORMATION_MESSAGE);

			}
		});
		insertButton.setBounds(185, 388, 93, 23);
		contentPane.add(insertButton);

		closeButton = new JButton("退出");
		closeButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				do_closeButton_actionPerformed(e);
			}

		});
		closeButton.setBounds(342, 388, 93, 23);
		contentPane.add(closeButton);

		starLabel = new JLabel("*");
		starLabel.setForeground(Color.RED);
		starLabel.setBounds(284, 53, 7, 15);
		contentPane.add(starLabel);

		label_1 = new JLabel("*");
		label_1.setForeground(Color.RED);
		label_1.setBounds(558, 53, 7, 15);
		contentPane.add(label_1);

		label_2 = new JLabel("*");
		label_2.setForeground(Color.RED);
		label_2.setBounds(284, 97, 7, 15);
		contentPane.add(label_2);

		label_3 = new JLabel("*");
		label_3.setForeground(Color.RED);
		label_3.setBounds(558, 97, 7, 15);
		contentPane.add(label_3);

		label_4 = new JLabel("*");
		label_4.setForeground(Color.RED);
		label_4.setBounds(284, 140, 7, 15);
		contentPane.add(label_4);

		label_5 = new JLabel("*");
		label_5.setForeground(Color.RED);
		label_5.setBounds(284, 180, 7, 15);
		contentPane.add(label_5);

		label_6 = new JLabel("*");
		label_6.setForeground(Color.RED);
		label_6.setBounds(284, 223, 7, 15);
		contentPane.add(label_6);

	}

	protected void do_closeButton_actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}
}

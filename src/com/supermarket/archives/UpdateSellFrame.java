package com.supermarket.archives;

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

import com.supermarket.bean.Provide;
import com.supermarket.bean.Sell;
import com.supermarket.dao.FeelDao;
import com.supermarket.dao.SellDao;

public class UpdateSellFrame extends JFrame {

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
	private JTextField emaillTextField;
	private JLabel label;
	private JButton closeButton;
	JTextArea remarkTextArea = new JTextArea();
	Sell sell = null;

	public UpdateSellFrame() {
		setTitle("修改销售商窗体");
		SellDao dao = new SellDao();
		try {
			File file = new File("file.txt");
			FileInputStream fin = new FileInputStream(file);
			int count = fin.read();
			file.delete();
			sell = dao.selectProvideByid(count);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		setBounds(100, 100, 635, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel cNameLabel = new JLabel("客户名称：");
		cNameLabel.setBounds(49, 53, 72, 15);
		contentPane.add(cNameLabel);

		cNameTextField = new JTextField();
		cNameTextField.setText(sell.getSellName());
		cNameTextField.setBounds(114, 50, 164, 25);
		contentPane.add(cNameTextField);
		cNameTextField.setColumns(10);

		JLabel addressLabel = new JLabel("客户地址：");
		addressLabel.setBounds(315, 53, 72, 15);
		contentPane.add(addressLabel);

		addressTextField = new JTextField();
		addressTextField.setText(sell.getAddress());
		addressTextField.setBounds(385, 50, 164, 25);
		contentPane.add(addressTextField);
		addressTextField.setColumns(10);

		linkNameLabel = new JLabel("联系人：");
		linkNameLabel.setBounds(49, 97, 70, 15);
		contentPane.add(linkNameLabel);

		linkNameTextField = new JTextField();
		linkNameTextField.setText(sell.getLinkman());
		linkNameTextField.setBounds(114, 94, 164, 25);
		contentPane.add(linkNameTextField);
		linkNameTextField.setColumns(10);

		linkPhoneLabel = new JLabel("联系电话：");
		linkPhoneLabel.setBounds(315, 97, 72, 15);
		contentPane.add(linkPhoneLabel);

		linkPhoneTextField = new JTextField();
		linkPhoneTextField.setText(sell.getLinkPhone());
		linkPhoneTextField.setBounds(385, 94, 164, 25);
		contentPane.add(linkPhoneTextField);
		linkPhoneTextField.setColumns(10);

		fexesLabel = new JLabel("传真：");
		fexesLabel.setBounds(50, 140, 54, 15);
		contentPane.add(fexesLabel);

		faxesTextField = new JTextField();
		faxesTextField.setText(sell.getFaxNum());
		faxesTextField.setBounds(114, 137, 164, 25);
		contentPane.add(faxesTextField);
		faxesTextField.setColumns(10);

		postNumLabel = new JLabel("邮编：");
		postNumLabel.setBounds(321, 140, 54, 15);
		contentPane.add(postNumLabel);

		postNumTextField = new JTextField();
		postNumTextField.setText(sell.getPostNum());
		postNumTextField.setBounds(385, 137, 164, 25);
		contentPane.add(postNumTextField);
		postNumTextField.setColumns(10);

		bankNumLabel = new JLabel("银行账号：");
		bankNumLabel.setBounds(49, 180, 72, 15);
		contentPane.add(bankNumLabel);

		bankNumTextField = new JTextField();
		bankNumTextField.setText(sell.getBankNum());
		bankNumTextField.setBounds(114, 177, 164, 25);
		contentPane.add(bankNumTextField);
		bankNumTextField.setColumns(10);

		netAddressLabel = new JLabel("网址：");
		netAddressLabel.setBounds(315, 180, 60, 15);
		contentPane.add(netAddressLabel);

		netAddressTextField = new JTextField();
		netAddressTextField.setText(sell.getNetAddress());
		netAddressTextField.setBounds(385, 177, 164, 25);
		contentPane.add(netAddressTextField);
		netAddressTextField.setColumns(10);

		emailLabel = new JLabel("邮箱：");
		emailLabel.setBounds(49, 233, 72, 15);
		contentPane.add(emailLabel);

		emaillTextField = new JTextField();
		emaillTextField.setText(sell.getEmaillAddress());
		emaillTextField.setBounds(114, 220, 164, 25);
		contentPane.add(emaillTextField);
		emaillTextField.setColumns(10);

		label = new JLabel("备注：");
		label.setBounds(49, 294, 72, 15);
		contentPane.add(label);

		remarkTextArea.setBounds(114, 267, 435, 89);
		contentPane.add(remarkTextArea);
		remarkTextArea.setText(sell.getRemark());

		JButton uodatetButton = new JButton("修改");
		uodatetButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SellDao dao = new SellDao();
				sell.setSellName(cNameTextField.getText());
				sell.setAddress(addressTextField.getText());
				sell.setBankNum(bankNumTextField.getText());
				sell.setLinkman(linkNameTextField.getText());
				sell.setLinkPhone(linkPhoneTextField.getText());
				sell.setFaxNum(faxesTextField.getText());
				sell.setPostNum(postNumTextField.getText());
				sell.setNetAddress(netAddressTextField.getText());
				sell.setEmaillAddress(emaillTextField.getText());
				sell.setRemark(remarkTextArea.getText());
				dao.updateSell(sell);
				repaint();
				JOptionPane.showMessageDialog(getContentPane(), "数据修改成功！",
						"信息提示框", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		uodatetButton.setBounds(185, 388, 93, 23);
		contentPane.add(uodatetButton);

		closeButton = new JButton("退出");
		closeButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				do_closeButton_actionPerformed(e);
			}

		});
		closeButton.setBounds(342, 388, 93, 23);
		contentPane.add(closeButton);

	}

	protected void do_closeButton_actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}
}

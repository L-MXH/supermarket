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

import sun.net.www.content.audio.wav;

import com.supermarket.bean.Ware;
import com.supermarket.dao.WareDao;

public class InsertWareFrame extends JFrame {

	private JPanel contentPane;
	private JTextField cNameTextField;
	private JTextField bewriteTextField;
	private JLabel unitLabel;
	private JTextField unitTextField;
	private JLabel stockLabel;
	private JTextField stockTextField;
	private JLabel retailLabel;
	private JTextField retailTextField;
	private JLabel memberLabel;
	private JTextField memberTextField;
	private JButton closeButton;
	private JLabel starLabel;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;

	public InsertWareFrame() {
		setTitle("添加销售商窗体");
		setBounds(100, 100, 635, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel cNameLabel = new JLabel("货品名称：");
		cNameLabel.setBounds(49, 53, 65, 15);
		contentPane.add(cNameLabel);

		cNameTextField = new JTextField();
		cNameTextField.setBounds(114, 50, 164, 25);
		contentPane.add(cNameTextField);
		cNameTextField.setColumns(10);

		JLabel bewriteLabel = new JLabel("货品描述：");
		bewriteLabel.setBounds(315, 53, 72, 15);
		contentPane.add(bewriteLabel);

		bewriteTextField = new JTextField();
		bewriteTextField.setBounds(385, 50, 164, 25);
		contentPane.add(bewriteTextField);
		bewriteTextField.setColumns(10);

		unitLabel = new JLabel("单位：");
		unitLabel.setBounds(49, 97, 54, 15);
		contentPane.add(unitLabel);

		unitTextField = new JTextField();
		unitTextField.setBounds(114, 94, 164, 25);
		contentPane.add(unitTextField);
		unitTextField.setColumns(10);

		stockLabel = new JLabel("进货价：");
		stockLabel.setBounds(315, 97, 72, 15);
		contentPane.add(stockLabel);

		stockTextField = new JTextField();
		stockTextField.setBounds(385, 94, 164, 25);
		contentPane.add(stockTextField);
		stockTextField.setColumns(10);

		retailLabel = new JLabel("零售价：");
		retailLabel.setBounds(49, 142, 65, 15);
		contentPane.add(retailLabel);

		retailTextField = new JTextField();
		retailTextField.setBounds(114, 137, 164, 25);
		contentPane.add(retailTextField);
		retailTextField.setColumns(10);

		memberLabel = new JLabel("会员价：");
		memberLabel.setBounds(315, 140, 60, 15);
		contentPane.add(memberLabel);

		memberTextField = new JTextField();
		memberTextField.setBounds(385, 137, 164, 25);
		contentPane.add(memberTextField);
		memberTextField.setColumns(10);

		JButton insertButton = new JButton("添加");
		insertButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				WareDao dao = new WareDao();

				String cName = cNameTextField.getText();
				String bewrite = bewriteTextField.getText();
				String unit = unitTextField.getText(); // 单位
				String stock = stockTextField.getText(); // 进货价
				String retail = retailTextField.getText();
				String member = memberTextField.getText();
				float memberPrice = 0;
				float stockPrice = 0;
				float retailPrice = 0;

				if ((cName.equals("")) || (bewrite.equals(""))
						|| (stock.equals("")) || (retail.equals(""))
						|| (member.equals(""))) {
					JOptionPane.showMessageDialog(getContentPane(),
							"将带星号的信息填写完整！", "信息提示框",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				try {
					stockPrice = Float.parseFloat(stock);
					retailPrice = Float.parseFloat(retail);
					memberPrice = Float.parseFloat(member);

				} catch (Exception ee) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(getContentPane(),
							"进货价、零售价、会员价 必须是数字！", "信息提示框",
							JOptionPane.INFORMATION_MESSAGE);

				}

				Ware ware = new Ware();
				ware.setWareName(cName);
				ware.setWarBewrite(bewrite);
				ware.setAssociatorPrice(memberPrice); // 666666
				ware.setSpec(unit);
				ware.setStockPrice(stockPrice);
				ware.setRetailPrice(retailPrice);

				dao.insertWare(ware);
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
		starLabel.setBounds(283, 53, 6, 15);
		contentPane.add(starLabel);

		label_1 = new JLabel("*");
		label_1.setForeground(Color.RED);
		label_1.setBounds(559, 53, 6, 15);
		contentPane.add(label_1);

		label_2 = new JLabel("*");
		label_2.setForeground(Color.RED);
		label_2.setBounds(283, 97, 6, 15);
		contentPane.add(label_2);

		label_3 = new JLabel("*");
		label_3.setForeground(Color.RED);
		label_3.setBounds(559, 97, 6, 15);
		contentPane.add(label_3);

		label_4 = new JLabel("*");
		label_4.setForeground(Color.RED);
		label_4.setBounds(283, 140, 6, 15);
		contentPane.add(label_4);

		JLabel label = new JLabel("*");
		label.setForeground(Color.RED);
		label.setBounds(559, 140, 6, 15);
		contentPane.add(label);

	}

	protected void do_closeButton_actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}
}

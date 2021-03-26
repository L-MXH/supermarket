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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.supermarket.bean.Ware;
import com.supermarket.dao.WareDao;

public class UpdateWareFrame extends JFrame {

	private JPanel contentPane;
	private JTextField cNameTextField;
	private JTextField bewriteTextField;
	private JLabel unitLabel;
	private JTextField unitTextField;
	private JLabel stockLabel;
	private JTextField stockTextField;
	private JLabel memberLabel;
	private JTextField memberTextField;
	private JLabel retailLabel;
	private JTextField retailTextField;
	private JButton closeButton;
	// private JLabel label_1;
	// private JLabel label_2;
	// private JLabel label_3;
	// private JLabel label_4;

	Ware ware = null;
	WareDao dao = new WareDao();

	public UpdateWareFrame() {
		setTitle("修改货品窗体");

		try {
			File file = new File("file.txt");
			FileInputStream fin = new FileInputStream(file);
			int count = fin.read();
			file.delete();
			ware = dao.selectWareByid(count);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		setBounds(100, 100, 635, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel cNameLabel = new JLabel("货品名称：");
		cNameLabel.setBounds(49, 53, 72, 15);
		contentPane.add(cNameLabel);

		cNameTextField = new JTextField();
		cNameTextField.setText(ware.getWareName());
		cNameTextField.setBounds(114, 50, 164, 25);
		contentPane.add(cNameTextField);
		cNameTextField.setColumns(10);

		JLabel addressLabel = new JLabel("货品描述：");
		addressLabel.setBounds(315, 53, 72, 15);
		contentPane.add(addressLabel);

		bewriteTextField = new JTextField();
		bewriteTextField.setText(ware.getWarBewrite());
		bewriteTextField.setBounds(385, 50, 164, 25);
		contentPane.add(bewriteTextField);
		bewriteTextField.setColumns(10);

		unitLabel = new JLabel("单位：");
		unitLabel.setBounds(49, 97, 70, 15);
		contentPane.add(unitLabel);

		unitTextField = new JTextField();
		unitTextField.setText(ware.getSpec());
		unitTextField.setBounds(114, 94, 164, 25);
		contentPane.add(unitTextField);
		unitTextField.setColumns(10);

		stockLabel = new JLabel("进货价：");
		stockLabel.setBounds(315, 97, 72, 15);
		contentPane.add(stockLabel);

		stockTextField = new JTextField();
		stockTextField.setText("" + ware.getStockPrice());
		stockTextField.setBounds(385, 94, 164, 25);
		contentPane.add(stockTextField);
		stockTextField.setColumns(10);

		memberLabel = new JLabel("会员价：");
		memberLabel.setBounds(50, 140, 54, 15);
		contentPane.add(memberLabel);

		memberTextField = new JTextField();
		memberTextField.setText("" + ware.getAssociatorPrice());
		memberTextField.setBounds(114, 137, 164, 25);
		contentPane.add(memberTextField);
		memberTextField.setColumns(10);

		retailLabel = new JLabel("零售价：");
		retailLabel.setBounds(321, 140, 54, 15);
		contentPane.add(retailLabel);

		retailTextField = new JTextField();
		retailTextField.setText("" + ware.getRetailPrice());
		retailTextField.setBounds(385, 137, 164, 25);
		contentPane.add(retailTextField);
		retailTextField.setColumns(10);

		JButton uodatetButton = new JButton("修改");
		uodatetButton.addActionListener(new ActionListener() {

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
						|| (unit.equals("")) || (stock.equals(""))
						|| (retail.equals("")) || (member.equals(""))) {
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

				ware.setWareName(cNameTextField.getText());
				ware.setWarBewrite(bewriteTextField.getText());// 666666
				ware.setSpec(unitTextField.getText());
				ware.setStockPrice(stockPrice);
				ware.setAssociatorPrice(memberPrice);
				ware.setRetailPrice(retailPrice);
				dao.updateWare(ware);
				JOptionPane.showMessageDialog(getContentPane(), "数据修改成功！",
						"信息提示框", JOptionPane.INFORMATION_MESSAGE);
				repaint();
			}
		});
		uodatetButton.setBounds(217, 172, 93, 23);
		contentPane.add(uodatetButton);

		closeButton = new JButton("退出");
		closeButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				do_closeButton_actionPerformed(e);
			}

		});

		closeButton.setBounds(331, 172, 93, 23);
		contentPane.add(closeButton);

		JLabel label = new JLabel("*");
		label.setForeground(Color.RED);
		label.setBounds(288, 53, 17, 15);
		contentPane.add(label);

		JLabel label_1 = new JLabel("*");
		label_1.setForeground(Color.RED);
		label_1.setBounds(559, 53, 17, 15);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("*");
		label_2.setForeground(Color.RED);
		label_2.setBounds(288, 97, 17, 15);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("*");
		label_3.setForeground(Color.RED);
		label_3.setBounds(559, 97, 17, 15);
		contentPane.add(label_3);

		JLabel label_4 = new JLabel("*");
		label_4.setForeground(Color.RED);
		label_4.setBounds(288, 140, 17, 15);
		contentPane.add(label_4);

		JLabel label_5 = new JLabel("*");
		label_5.setForeground(Color.RED);
		label_5.setBounds(559, 140, 17, 15);
		contentPane.add(label_5);

	}

	protected void do_closeButton_actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}
}

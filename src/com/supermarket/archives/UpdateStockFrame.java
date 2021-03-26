package com.supermarket.archives;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.supermarket.bean.Provide;
import com.supermarket.bean.Stock;
import com.supermarket.dao.FeelDao;
import com.supermarket.dao.StockDao;

public class UpdateStockFrame extends JFrame {

	private JPanel contentPane;
	private JTextField orderIdTextField;
	private JTextField nameTextField;
	private JLabel dateLabel;
	private JTextField dateTextField;
	private JLabel wNameLabel;
	private JTextField wNameTextField;
	private JLabel countLabel;
	private JTextField countTextField;
	private JLabel moneyLabel;
	private JTextField moneyTextField;
	private JButton closeButton;
	private JLabel starLabel;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private Stock stock;
	private StockDao dao = new StockDao();

	public UpdateStockFrame() {
		setTitle("修改采购订货窗体");
		setBounds(100, 100, 635, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		try {
			File file = new File("filedd.txt"); // 创建文件对象
			FileInputStream fin = new FileInputStream(file);
			int count = fin.read();
			stock = dao.selectStockByid(count);
			file.delete();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		JLabel orderIdLabel = new JLabel("订单号：");
		orderIdLabel.setBounds(59, 55, 60, 15);
		contentPane.add(orderIdLabel);

		orderIdTextField = new JTextField();
		orderIdTextField.setText(stock.getOrderId());
		orderIdTextField.setBounds(114, 50, 164, 25);
		contentPane.add(orderIdTextField);
		orderIdTextField.setColumns(10);

		JLabel nameLabel = new JLabel("客户：");
		nameLabel.setBounds(315, 55, 60, 15);
		contentPane.add(nameLabel);

		nameTextField = new JTextField();
		nameTextField.setText(stock.getsName());
		nameTextField.setBounds(385, 50, 164, 25);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);

		dateLabel = new JLabel("交货日期：");
		dateLabel.setBounds(50, 99, 70, 15);
		contentPane.add(dateLabel);

		dateTextField = new JTextField();
		dateTextField.setText(stock.getConsignmentDate());
		dateTextField.setBounds(114, 94, 164, 25);
		contentPane.add(dateTextField);
		dateTextField.setColumns(10);

		wNameLabel = new JLabel("货品名称：");
		wNameLabel.setBounds(315, 97, 72, 15);
		contentPane.add(wNameLabel);

		wNameTextField = new JTextField();
		wNameTextField.setText(stock.getBaleName());
		wNameTextField.setBounds(385, 94, 164, 25);
		contentPane.add(wNameTextField);
		wNameTextField.setColumns(10);

		countLabel = new JLabel("数量：");
		countLabel.setBounds(50, 140, 54, 15);
		contentPane.add(countLabel);

		countTextField = new JTextField();
		countTextField.setText(stock.getCount());
		countTextField.setBounds(114, 137, 164, 25);
		contentPane.add(countTextField);
		countTextField.setColumns(10);

		moneyLabel = new JLabel("金额：");
		moneyLabel.setBounds(315, 140, 60, 15);
		contentPane.add(moneyLabel);

		moneyTextField = new JTextField();
		moneyTextField.setText("" + stock.getMoney());
		moneyTextField.setBounds(385, 137, 164, 25);
		contentPane.add(moneyTextField);
		moneyTextField.setColumns(10);

		JButton insertButton = new JButton("修改");
		insertButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StockDao dao = new StockDao();
				String oId = orderIdTextField.getText();
				String wname = nameTextField.getText();
				String wDate = dateTextField.getText();
				String count = countTextField.getText();
				String bName = wNameTextField.getText();
				String money = moneyTextField.getText();
				int countIn = 0;
				float fmoney = 0;
				if ((oId.equals("")) || (wname.equals(""))
						|| (wDate.equals("")) || (count.equals(""))
						|| (money.equals(""))) {
					JOptionPane.showMessageDialog(getContentPane(),
							"将带星号的信息填写完整！", "信息提示框",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				try {
					countIn = Integer.parseInt(count);
					fmoney = Float.parseFloat(money);
				} catch (Exception ee) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(getContentPane(), "要输入数字！",
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				stock.setsName(wname);
				stock.setBaleName(bName);
				stock.setConsignmentDate(wDate);
				stock.setCount(count);
				stock.setMoney(fmoney);
				stock.setOrderId(oId);
				dao.insertStock(stock);

				JOptionPane.showMessageDialog(getContentPane(), "数据添加成功！",
						"信息提示框", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		insertButton.setBounds(176, 193, 93, 23);
		contentPane.add(insertButton);

		closeButton = new JButton("退出");
		closeButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				do_closeButton_actionPerformed(e);
			}

		});
		closeButton.setBounds(315, 193, 93, 23);
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
		label_2.setBounds(283, 97, 7, 15);
		contentPane.add(label_2);

		label_3 = new JLabel("*");
		label_3.setForeground(Color.RED);
		label_3.setBounds(559, 97, 7, 15);
		contentPane.add(label_3);

		label_4 = new JLabel("*");
		label_4.setForeground(Color.RED);
		label_4.setBounds(284, 140, 7, 15);
		contentPane.add(label_4);

		label_5 = new JLabel("*");
		label_5.setForeground(Color.RED);
		label_5.setBounds(559, 140, 18, 15);
		contentPane.add(label_5);

	}

	protected void do_closeButton_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}
}

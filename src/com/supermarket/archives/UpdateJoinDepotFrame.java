package com.supermarket.archives;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.supermarket.bean.JoinDepot;
import com.supermarket.bean.Provide;
import com.supermarket.bean.Stock;
import com.supermarket.dao.FeelDao;
import com.supermarket.dao.JoinDepotDao;
import com.supermarket.dao.StockDao;

public class UpdateJoinDepotFrame extends JFrame {

	private JPanel contentPane;
	private JLabel dateLabel;
	private JTextField wNameTextField;
	private JLabel joinTimeLabel;
	private JTextField joinTimeTextField;
	private JLabel remarkLabel;
	private JButton closeButton;
	private JLabel starLabel;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	JoinDepotDao dao = new JoinDepotDao();
	private JComboBox dIdcomboBox;
	JComboBox oIdcomboBox;
	JTextArea remarkTextArea = new JTextArea();
	JoinDepot depot;
	private JLabel wightLabel;
	private JTextField wightTextField;
	private JTextField qianketextField;
	private JLabel label;
	private JLabel label_4;

	public UpdateJoinDepotFrame() {
		setTitle("?޸Ĳֿ????ⴰ??");
		setBounds(100, 100, 635, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		try {
			File file = new File("file.txt");
			FileInputStream fin = new FileInputStream(file);
			int count = fin.read();
			file.delete();
			depot = dao.selectJoinDepotByid(count);
		} catch (Exception e) {
			e.printStackTrace();
		}

		JLabel orderIdLabel = new JLabel("?????ţ?");
		orderIdLabel.setBounds(59, 55, 60, 15);
		contentPane.add(orderIdLabel);

		JLabel dIdLabel = new JLabel("?ֿ????ţ?");
		dIdLabel.setBounds(315, 55, 74, 15);
		contentPane.add(dIdLabel);

		dateLabel = new JLabel("??Ʒ???ƣ?");
		dateLabel.setBounds(41, 99, 70, 15);
		contentPane.add(dateLabel);

		wNameTextField = new JTextField();
		wNameTextField.setText(depot.getWareName());
		wNameTextField.setBounds(114, 94, 164, 25);
		contentPane.add(wNameTextField);
		wNameTextField.setColumns(10);

		joinTimeLabel = new JLabel("????ʱ?䣺");
		joinTimeLabel.setBounds(315, 97, 72, 15);
		contentPane.add(joinTimeLabel);

		joinTimeTextField = new JTextField();
		joinTimeTextField.setText(depot.getJoinTime());
		joinTimeTextField.setBounds(385, 94, 164, 25);
		contentPane.add(joinTimeTextField);
		joinTimeTextField.setColumns(10);

		remarkLabel = new JLabel("??ע??");
		remarkLabel.setBounds(59, 236, 54, 15);
		contentPane.add(remarkLabel);

		JButton insertButton = new JButton("?޸?");
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String wName = wNameTextField.getText();
				String joinTime = joinTimeTextField.getText();
				String wight = wightTextField.getText();
				float wightFloat = 0;
				if (wName.equals("") || (joinTime.equals(""))
						|| (wight.equals(""))) {
					JOptionPane.showMessageDialog(getContentPane(), "???????ӳɹ???",
							"??Ϣ??ʾ??", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				try {
					wightFloat = Float.parseFloat(wight);
				} catch (Exception ee) {
					JOptionPane.showMessageDialog(getContentPane(), "Ҫ??????ֵ???ͣ?",
							"??Ϣ??ʾ??", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				depot.setdId(Integer.parseInt(dIdcomboBox.getSelectedItem()
						.toString()));
				depot.setWareName(wName);
				depot.setJoinTime(joinTime);
				depot.setWeight(wightFloat);
				depot.setRemark(remarkTextArea.getText());

				dao.updateJoinDepot(depot);
				JOptionPane.showMessageDialog(getContentPane(), "?????޸ĳɹ???",
						"??Ϣ??ʾ??", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		insertButton.setBounds(186, 338, 93, 23);
		contentPane.add(insertButton);

		closeButton = new JButton("?˳?");
		closeButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				do_closeButton_actionPerformed(e);
			}

		});
		closeButton.setBounds(315, 338, 93, 23);
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
		label_4.setBounds(283, 149, 7, 15);
		contentPane.add(label_4);

		String oid = depot.getoId();
		System.out.println("oid:" + oid);
		List list = dao.selectOidId();
		String[] orderId;
		orderId = new String[list.size() + 1];
		orderId[0] = "";
		for (int i = 0; i < list.size(); i++) {
			orderId[i + 1] = (String) list.get(i);

		}
		oIdcomboBox = new JComboBox(orderId);
		oIdcomboBox.setSelectedItem(oid);
		oIdcomboBox.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				String oid = oIdcomboBox.getSelectedItem().toString();
				String wName = dao.selectOidId(oid);
				wNameTextField.setText(wName);
				if (!(oIdcomboBox.getSelectedItem().toString()).equals("")) {
					wNameTextField.setEditable(false);

				} else {
					wNameTextField.setEditable(true);
				}
			}
		});
		oIdcomboBox.setBounds(114, 52, 164, 21);
		contentPane.add(oIdcomboBox);
		remarkTextArea.setBounds(114, 193, 435, 112);
		remarkTextArea.setText(depot.getRemark());
		contentPane.add(remarkTextArea);
		List listDid = dao.selectDepotId();
		Integer[] did = new Integer[listDid.size()];
		int didCount = depot.getdId();
		for (int j = 0; j < listDid.size(); j++) {
			did[j] = (Integer) listDid.get(j);

		}
		dIdcomboBox = new JComboBox(did);
		dIdcomboBox.setSelectedItem("" + didCount);
		dIdcomboBox.setBounds(385, 52, 164, 21);
		contentPane.add(dIdcomboBox);

		JLabel wightlabel = new JLabel("??????");
		wightlabel.setBounds(69, 149, 36, 15);
		contentPane.add(wightlabel);

		wightTextField = new JTextField();
		System.out.println("dddddddd " + depot.getWeight());
		wightTextField.setText("" + depot.getWeight());
		wightTextField.setColumns(10);
		wightTextField.setBounds(114, 146, 164, 25);
		contentPane.add(wightTextField);

		JLabel label = new JLabel("ǧ?ˣ?");
		label.setBounds(315, 146, 72, 15);
		contentPane.add(label);

		qianketextField = new JTextField();
		qianketextField.setBounds(385, 143, 164, 25);
		contentPane.add(qianketextField);
		qianketextField.setColumns(10);

	}

	protected void do_closeButton_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}
}

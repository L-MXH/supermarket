package com.supermarket.archives;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

public class InsertJoinDepotFrame extends JFrame {

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
	private JLabel label_4;

	JoinDepotDao dao = new JoinDepotDao();
	private JComboBox dIdcomboBox;
	JComboBox oIdcomboBox;
	JTextArea remarkTextArea = new JTextArea();
	private JTextField wighttextField;
	private JTextField qianketextField;

	public InsertJoinDepotFrame() {
		setTitle("???Ӳɹ?????????");
		setBounds(100, 100, 635, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

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
		wNameTextField.setBounds(114, 94, 164, 25);
		contentPane.add(wNameTextField);
		wNameTextField.setColumns(10);

		joinTimeLabel = new JLabel("????ʱ?䣺");
		joinTimeLabel.setBounds(315, 97, 72, 15);
		contentPane.add(joinTimeLabel);

		joinTimeTextField = new JTextField();
		joinTimeTextField.setBounds(385, 94, 164, 25);
		contentPane.add(joinTimeTextField);
		joinTimeTextField.setColumns(10);

		remarkLabel = new JLabel("??ע??");
		remarkLabel.setBounds(59, 233, 54, 15);
		contentPane.add(remarkLabel);

		JButton insertButton = new JButton("????");
		insertButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String did = dIdcomboBox.getSelectedItem().toString();
				String oId = oIdcomboBox.getSelectedItem().toString();
				String wName = wNameTextField.getText();
				String joinTime = joinTimeTextField.getText();
				String wight = wighttextField.getText();
				float depotWight = 0;
				if ((did.equals("")) || (wName.equals(""))
						|| (joinTime.equals("")) || (wight.equals(""))) {
					JOptionPane.showMessageDialog(getContentPane(),
							"?????Ǻŵ???Ϣ??д??????", "??Ϣ??ʾ??",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				try {
					depotWight = Float.parseFloat(wight);

				} catch (Exception ee) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(getContentPane(), "????????ֵ???ͣ?",
							"??Ϣ??ʾ??", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				JoinDepot joinDepot = new JoinDepot();
				joinDepot.setdId(Integer.parseInt(did));
				joinDepot.setoId(oId);
				joinDepot.setJoinTime(joinTime);
				joinDepot.setWareName(wName);
				joinDepot.setWeight(depotWight);
				joinDepot.setRemark(remarkTextArea.getText());
				dao.insertJoinDepot(joinDepot);

				JOptionPane.showMessageDialog(getContentPane(), "???????ӳɹ???",
						"??Ϣ??ʾ??", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		insertButton.setBounds(185, 329, 93, 23);
		contentPane.add(insertButton);

		closeButton = new JButton("?˳?");
		closeButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				do_closeButton_actionPerformed(e);
			}

		});
		closeButton.setBounds(326, 329, 93, 23);
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
		label_3.setBounds(559, 97, 7, 15);
		contentPane.add(label_3);

		label_4 = new JLabel("*");
		label_4.setForeground(Color.RED);
		label_4.setBounds(284, 146, 54, 15);
		contentPane.add(label_4);

		List list = dao.selectOidId();
		String[] orderId = new String[list.size() + 1];
		orderId[0] = "";
		for (int i = 0; i < list.size(); i++) {
			orderId[i + 1] = (String) list.get(i);

		}
		oIdcomboBox = new JComboBox(orderId);
		oIdcomboBox.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				String oid = oIdcomboBox.getSelectedItem().toString();
				String wName = dao.selectOidId(oid);
				wNameTextField.setText(wName);
			}
		});
		oIdcomboBox.setBounds(114, 52, 164, 21);
		contentPane.add(oIdcomboBox);
		remarkTextArea.setBounds(114, 193, 435, 112);
		contentPane.add(remarkTextArea);

		List listDid = dao.selectDepotId();
		Integer[] did = new Integer[listDid.size()];
		for (int j = 0; j < listDid.size(); j++) {
			did[j] = (Integer) listDid.get(j);

		}
		dIdcomboBox = new JComboBox(did);
		dIdcomboBox.setBounds(385, 52, 164, 21);
		contentPane.add(dIdcomboBox);

		JLabel wightlabel = new JLabel("??????");
		wightlabel.setBounds(70, 146, 44, 15);
		contentPane.add(wightlabel);

		wighttextField = new JTextField();
		wighttextField.setBounds(114, 143, 164, 25);
		contentPane.add(wighttextField);
		wighttextField.setColumns(10);

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

/**
 * 功能：人事管理
 * 
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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicPanelUI;

import com.supermarket.archives.InsertPersonnelFrame;
import com.supermarket.archives.UpdatePersonnelFrame;
import com.supermarket.archives.UpdateWareFrame;
import com.supermarket.bean.BasicMessage;
import com.supermarket.bean.Contact;
import com.supermarket.bean.Dept;
import com.supermarket.dao.DeptDao;
import com.supermarket.dao.PersonnelDao;

public class PersonnelPanel extends JPanel {
	final JoinDepotPanel model = new JoinDepotPanel();
	private final PersonnelDao perdao = new PersonnelDao();
	private JTextField nameTextField;
	private JTextField ageTextField;
	private JComboBox pNameComboBox = new JComboBox();
	JPanel contactPanel;
	private Contact contact = new Contact();
	JPanel panel_1 = new JPanel();
	JPanel contentPanel = new JPanel();
	private BasicMessage message = new BasicMessage();
	private JRadioButton manRadioButton;
	private JRadioButton wradioButton;
	private JTextField deptField;
	private JTextField headshipField;
	private JTextField pnoneTextField;
	private JTextField officeTextField;
	private JTextField faxTextField;
	private JTextField emailtextField;
	private JTextField addressTextField;
	private int pId;

	public PersonnelPanel() {
		this.setBorder(createTitledBorder(null, "人员管理",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, new Font(
						"sansserif", Font.BOLD, 12), new Color(59, 59, 59)));
		setSize(631, 427);
		setLayout(null);
		this.setBackground(new Color(71, 201, 223));
		panel_1.setBackground(new Color(71, 201, 223));
		JPanel panel = new JPanel();
		panel.setBackground(new Color(71, 201, 223));
		panel.setBorder(createTitledBorder(null, "员工管理",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.ABOVE_TOP,
				new Font("sansserif", Font.BOLD, 12), new Color(59, 59, 59)));
		panel.setBounds(29, 25, 572, 71);
		add(panel);
		panel.setLayout(null);

		JLabel dNameLabel = new JLabel("部门");
		dNameLabel.setBounds(70, 35, 54, 15);
		panel.add(dNameLabel);
		final DeptDao dao = new DeptDao();
		List list = dao.selectDept();
		String dName[] = new String[list.size() + 1];
		dName[0] = "";
		for (int i = 0; i < list.size(); i++) {
			Dept dept = (Dept) list.get(i);
			dName[i + 1] = dept.getdName();
		}
		final JComboBox dNamecomboBox = new JComboBox(dName);
		dNamecomboBox.addActionListener((new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pNameComboBox.removeAllItems();
				String dName = dNamecomboBox.getSelectedItem().toString();
				DeptDao deptDao = new DeptDao();
				int id = deptDao.selectDeptIdByName(dName);
				List<String> listName = perdao.selectBasicMessageByDept(id);
				for (int i = 0; i < listName.size(); i++) {
					pNameComboBox.addItem(listName.get(i));
				}
				repaint();
			}
		}));
		dNamecomboBox.setBounds(134, 32, 102, 21);
		panel.add(dNamecomboBox);

		JLabel pNameLabel = new JLabel("姓名：");
		pNameLabel.setBounds(262, 35, 54, 15);
		panel.add(pNameLabel);

		pNameComboBox.setBounds(310, 32, 101, 21);
		panel.add(pNameComboBox);
		contentPanel.setLayout(null);

		panel_1.setBorder(createTitledBorder(null, "员工信息",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.ABOVE_TOP,
				new Font("sansserif", Font.BOLD, 12), new Color(59, 59, 59)));
		panel_1.setBounds(29, 106, 572, 310);
		add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(createTitledBorder(null, "员工信息",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, new Font(
						"sansserif", Font.BOLD, 12), new Color(59, 59, 59)));
		scrollPane.setBounds(21, 53, 109, 218);
		panel_1.add(scrollPane);
		String name[] = { "基本信息", "联系方式" };
		final JList jlist = new JList(name);
		final JPanel bpanel = basicPanel();
		final JPanel particular = particularPsnel();

		jlist.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if (!e.getValueIsAdjusting()) {
					if (dNamecomboBox.getSelectedItem() == null
							|| pNameComboBox.getSelectedItem() == null) {
						JOptionPane.showMessageDialog(getParent(),
								"没有选择查询的员工！", "信息提示框",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					JList list = (JList) e.getSource();

					String value = (String) list.getSelectedValue();
					if (value.equals("基本信息")) {
						String name = pNameComboBox.getSelectedItem()
								.toString();
						panel_1.remove(particular);
						panel_1.add(bpanel);
						bpanel.setBounds(140, 53, 409, 208);
						message = perdao.selectBNameById(dNamecomboBox
								.getSelectedItem().toString(), name);
						pId = message.getId();
						nameTextField.setText(message.getName());
						ageTextField.setText(message.getAge() + "");
						String sex = message.getSex();
						if (sex.equals("男")) {
							manRadioButton.setSelected(true);
						} else {
							wradioButton.setSelected(true);
						}
						int dept = message.getDept();
						Dept depts = dao.selectDeptById(dept);
						deptField.setText(depts.getdName());
						String hName = perdao.selectHeadshipById(message
								.getHeadship());
						headshipField.setText(hName);
						repaint();
					}
					if (value.equals("联系方式")) {
						panel_1.remove(bpanel);
						panel_1.add(particular);
						contact = perdao.selectContactById(pId);
						pnoneTextField.setText(contact.getContact());
						officeTextField.setText(contact.getOfficePhone());
						faxTextField.setText(contact.getFax());
						emailtextField.setText(contact.getEmail());
						addressTextField.setText(contact.getFaddress());
						repaint();
					}
					doLayout();

				}
			}
		});
		scrollPane.setViewportView(jlist);

		JButton insertButton = new JButton("添加");
		insertButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				InsertPersonnelFrame frame = new InsertPersonnelFrame();
				frame.setVisible(true);
			}
		});
		insertButton.setBounds(179, 266, 62, 23);
		panel_1.add(insertButton);

		JButton updateButton = new JButton("修改");
		updateButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (dNamecomboBox.getSelectedItem() == null
						|| pNameComboBox.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(getParent(), "请输入修改条件！",
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				message = perdao.selectBNameById(dNamecomboBox
						.getSelectedItem().toString(), pNameComboBox
						.getSelectedItem().toString());
				pId = message.getId();
				if (pId < 0) {
					JOptionPane.showMessageDialog(getParent(), "没有选择要修改的数据！",
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				} else {
					File file = new File("file.txt");
					try {
						file.createNewFile();
						FileOutputStream out = new FileOutputStream(file);
						out.write(pId);
						out.close();
						UpdatePersonnelFrame updateFrame = new UpdatePersonnelFrame();
						updateFrame.setVisible(true);
						repaint();
					} catch (Exception ee) {
						// TODO: handle exception
						ee.printStackTrace();
					}
				}
			}
		});
		updateButton.setBounds(275, 266, 62, 23);
		panel_1.add(updateButton);

		JButton deleteButton = new JButton("删除");
		deleteButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int n = JOptionPane.showConfirmDialog(getParent(), "确定正确吗？",
						"确认对话框", JOptionPane.YES_NO_CANCEL_OPTION);
				message = perdao.selectBNameById(dNamecomboBox
						.getSelectedItem().toString(), pNameComboBox
						.getSelectedItem().toString());
				pId = message.getId();
				if (n == JOptionPane.YES_OPTION) {
					perdao.deleteBasicMessage(pId);
					pNameComboBox.removeAllItems();
					repaint();
				}
			}
		});
		deleteButton.setBounds(362, 266, 62, 23);
		panel_1.add(deleteButton);
	}

	private JPanel particularPsnel() {
		// TODO Auto-generated method stub

		contactPanel = new JPanel();
		contactPanel.setBounds(128, 53, 420, 208);
		contactPanel.setLayout(null);

		JLabel phoneLabel = new JLabel("手机：");
		phoneLabel.setBounds(25, 35, 54, 15);
		contactPanel.add(phoneLabel);

		pnoneTextField = new JTextField();

		pnoneTextField.setBounds(60, 32, 133, 25);
		contactPanel.add(pnoneTextField);
		pnoneTextField.setColumns(10);

		JLabel officeLabel = new JLabel("办公电话：");
		officeLabel.setBounds(204, 35, 69, 15);
		contactPanel.add(officeLabel);

		officeTextField = new JTextField();

		officeTextField.setBounds(264, 32, 121, 25);
		contactPanel.add(officeTextField);
		officeTextField.setColumns(10);

		JLabel faxLabel = new JLabel("传真：");
		faxLabel.setBounds(25, 82, 36, 15);
		contactPanel.add(faxLabel);

		faxTextField = new JTextField();

		faxTextField.setBounds(61, 82, 133, 25);
		contactPanel.add(faxTextField);
		faxTextField.setColumns(10);

		JLabel emailLabel = new JLabel("邮箱：");
		emailLabel.setBounds(226, 82, 36, 25);
		contactPanel.add(emailLabel);

		emailtextField = new JTextField();

		emailtextField.setColumns(10);
		emailtextField.setBounds(264, 82, 121, 25);
		contactPanel.add(emailtextField);

		JLabel addressLabel = new JLabel("家庭地址：");
		addressLabel.setBounds(10, 145, 60, 15);
		contactPanel.add(addressLabel);

		addressTextField = new JTextField();

		addressTextField.setColumns(10);
		addressTextField.setBounds(61, 140, 133, 25);
		contactPanel.add(addressTextField);
		contactPanel.setBorder(createTitledBorder(null, "详细信息",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, new Font(
						"sansserif", Font.BOLD, 12), new Color(59, 59, 59)));

		return contactPanel;
	}

	private JPanel basicPanel() {
		// TODO Auto-generated method stub

		contentPanel = new JPanel();
		JLabel nameLabel = new JLabel("姓名：");
		nameLabel.setBounds(25, 35, 54, 15);
		contentPanel.add(nameLabel);

		nameTextField = new JTextField();
		nameTextField.setBounds(60, 32, 133, 25);
		contentPanel.add(nameTextField);
		nameTextField.setColumns(10);

		JLabel agelabel = new JLabel("年龄：");
		agelabel.setBounds(208, 35, 41, 15);
		contentPanel.add(agelabel);

		ageTextField = new JTextField();
		ageTextField.setBounds(250, 30, 123, 25);
		contentPanel.add(ageTextField);
		ageTextField.setColumns(10);

		JLabel sexLabel = new JLabel("性别：");
		sexLabel.setBounds(25, 82, 41, 15);
		contentPanel.add(sexLabel);

		manRadioButton = new JRadioButton("男");
		manRadioButton.setBounds(66, 78, 41, 23);
		contentPanel.add(manRadioButton);

		wradioButton = new JRadioButton("女");
		wradioButton.setBounds(119, 78, 41, 23);
		contentPanel.add(wradioButton);

		ButtonGroup group = new ButtonGroup();
		group.add(manRadioButton);
		group.add(wradioButton);

		JLabel deptLabel = new JLabel("部门：");
		deptLabel.setBounds(208, 82, 41, 15);
		contentPanel.add(deptLabel);

		deptField = new JTextField();
		deptField.setBounds(250, 79, 123, 25);
		contentPanel.add(deptField);

		JLabel headshipLabel = new JLabel("职务：");
		headshipLabel.setBounds(25, 123, 41, 15);
		contentPanel.add(headshipLabel);

		headshipField = new JTextField();
		headshipField.setBounds(64, 120, 123, 25);
		contentPanel.add(headshipField);

		contentPanel.setBorder(createTitledBorder(null, "基本信息",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, new Font(
						"sansserif", Font.BOLD, 12), new Color(59, 59, 59)));

		return contentPanel;
	}

}

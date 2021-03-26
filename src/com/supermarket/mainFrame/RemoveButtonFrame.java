package com.supermarket.mainFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import static javax.swing.BorderFactory.createTitledBorder;

import com.supermarket.bean.User;
import com.supermarket.panel.*;
import com.supermarket.util.Session;
import com.supermarket.widget.BGPanel;
import com.supermarket.widget.GlassButton;
import com.supermarket.widget.SmallScrollPanel;

/**
 * 功能：通过点击功能按钮，在主窗体中能显示内容的代码实现
 */
public class RemoveButtonFrame extends JFrame {

    private BGPanel backPanel;
    private SmallScrollPanel moduleButtonGroup = null;
    private JTree tree;
    private final JPanel panel_1 = new JPanel();
    private final JLabel firstLabel = new JLabel("基本档案管理");
    private BGPanel jPanel = null;
    private JPanel panel = null;
    private ButtonGroup buttonGroup = null;
    private GlassButton workSpaceButton = null;
    private GlassButton progressButton = null;
    private GlassButton bookProjectButton = null;
    private GlassButton stockButton = null;
    private GlassButton personnelManagerButton = null;
    private GlassButton deptManagerButton = null;
    private final FeelWarePanel panelFeel = new FeelWarePanel();
    private final JLabel label_1 = new JLabel("您当前的位置是：");

    public RemoveButtonFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 934, 625);
        setTitle("超市管理系统");
        MyJPanel contentPane = new MyJPanel();
        contentPane.setLayout(null);
        contentPane.add(getModuleButtonGroup());
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.add(getContJPanel());
        setContentPane(contentPane);
        setResizable(false);

        JPanel clockPanel = new JPanel();
        clockPanel.setBackground(new Color(71, 201, 223));
        clockPanel.setBounds(10, 124, 258, 245);
        clockPanel.setLayout(null);
        contentPane.add(clockPanel);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(0, 210, 276, 1);
        panel_1.setLayout(null);
        clockPanel.add(panel_1);

        User user = Session.getUser();
        String info = "<html><body>" + "<font color=#000000>你 好：</font>"
                + "<font color=yellow><b>" + user.getUserName() + "</b></font>"
                + "<font color=#FFFFFF>                欢 迎 登 录</font>"
                + "</body></html>";

        JLabel label = new JLabel(info);

        label.setBounds(45, 210, 128, 35);
        clockPanel.add(label);
        clockPanel.add(getPanel1());

        CalendarPanel panel_2 = new CalendarPanel();
        panel_2.setBounds(10, 364, 258, 207);
        contentPane.add(panel_2);

    }

    private JPanel getPanel1() {
        if (panel == null) {
            panel = new ClockPanel();
            panel.setBounds(56, 35, 153, 148);
        }
        return panel;
    }

    private JPanel getContJPanel() {
        if (backPanel == null) {
            backPanel = new BGPanel();
            backPanel.setBackground(new Color(71, 201, 223));
            backPanel.setSize(620, 416);
            backPanel.setLocation(279, 149);
            backPanel.setLayout(null);

            backPanel.setBorder(createTitledBorder(null, "您当前的位置",
                    TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP,
                    new Font("宋体", Font.BOLD, 12), new Color(59, 59, 59)));

            firstLabel.setBounds(133, 38, 123, 15);
            backPanel.add(firstLabel);

            panel_1.setBounds(10, 63, 611, 343);
            panel_1.setLayout(null);
            backPanel.add(panel_1);

            JScrollPane scrollPane_1 = new JScrollPane();
            scrollPane_1.setBackground(new Color(71, 201, 233));
            scrollPane_1.setBounds(0, 0, 138, 343);
            panel_1.add(scrollPane_1);

            DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(
                    "基本档案管理");
            DefaultMutableTreeNode childNode1 = new DefaultMutableTreeNode(
                    "供货商管理");
            DefaultMutableTreeNode childNode2 = new DefaultMutableTreeNode(
                    "销售商管理");
            DefaultMutableTreeNode childNode3 = new DefaultMutableTreeNode(
                    "货品档案管理");
            DefaultMutableTreeNode childNode4 = new DefaultMutableTreeNode(
                    "仓库管理");

            rootNode.add(childNode1);
            rootNode.add(childNode2);
            rootNode.add(childNode3);
            rootNode.add(childNode4);

            final JPanel sellPanel = new JPanel();
            sellPanel.setBackground(new Color(171, 201, 223));
            sellPanel.setBounds(138, 0, 473, 343);
            sellPanel.setLayout(null);
            panel_1.add(sellPanel);

            tree = new JTree(rootNode);

            scrollPane_1.setColumnHeaderView(tree);

            tree.getSelectionModel().setSelectionMode(
                    TreeSelectionModel.SINGLE_TREE_SELECTION);
            TreeSelectionModel treeSelectionModel = tree.getSelectionModel();
            treeSelectionModel
                    .setSelectionMode(treeSelectionModel.CONTIGUOUS_TREE_SELECTION);
            tree.addTreeSelectionListener(new TreeSelectionListener() {

                public void valueChanged(TreeSelectionEvent e) {
                    if (!tree.isSelectionEmpty()) {
                        TreePath selectionPaths = tree.getSelectionPath();
                        Object path = selectionPaths.getLastPathComponent();
                        DefaultMutableTreeNode node = (DefaultMutableTreeNode) path;
                        String userObject = (String) node.getUserObject();
                        repaint();

                        switch (userObject) {
                            case "供货商管理":
                                firstLabel.setText("供货商管理");
                                sellPanel.removeAll();
                                sellPanel.add(panelFeel.getMessage());
                                sellPanel.add(firstLabel);
                                break;
                            case "销售商管理":
                                firstLabel.setText("销售商管理");
                                sellPanel.removeAll();
                                SellPanel sell = new SellPanel();
                                sellPanel.add(sell.getMessage());
                                // sellPanel.add(firstLabel);
                                repaint();
                                break;
                            case "货品档案管理":
                                firstLabel.setText("货品档案管理");
                                sellPanel.removeAll();
                                WarePanel warePanel = new WarePanel();
                                sellPanel.add(warePanel.getMessage());
                                // sellPanel.add(firstLabel);
                                repaint();
                                break;
                            case "仓库管理":
                                firstLabel.setText("仓库管理");
                                sellPanel.removeAll();
                                DepotPanel depotPanel = new DepotPanel();
                                sellPanel.add(depotPanel.getMessage());
                                // sellPanel.add(firstLabel);
                                repaint();
                                break;
                        }

                    }

                }
            });

        }
        return backPanel;
    }

    public BGPanel getPanel() {
        if (jPanel == null) {
            GridLayout gridLayout = new GridLayout();
            gridLayout.setRows(1);
            gridLayout.setHgap(0);
            gridLayout.setVgap(0);

            jPanel = new BGPanel();
            jPanel.setLayout(gridLayout);
            jPanel.setPreferredSize(new Dimension(400, 50));
            jPanel.setOpaque(false);

            jPanel.add(getWorkSpaceButton(), null);
            jPanel.add(getProgressButton(), null);
            jPanel.add(getBookProjectButton(), null);
            jPanel.add(getStockButton(), null);
            jPanel.add(getPersonnelManagerButton(), null);
            jPanel.add(getDeptManagerButton(), null);

            if (buttonGroup == null) {
                buttonGroup = new ButtonGroup();
            }

            buttonGroup.add(getWorkSpaceButton());
            buttonGroup.add(getProgressButton());
            buttonGroup.add(getBookProjectButton());
            buttonGroup.add(getStockButton());
            buttonGroup.add(getPersonnelManagerButton());
            buttonGroup.add(getDeptManagerButton());

        }
        return jPanel;
    }

    private GlassButton getWorkSpaceButton() {
        if (workSpaceButton == null) {
            workSpaceButton = new GlassButton();
            workSpaceButton.setActionCommand("基本档案管理");
            workSpaceButton.setIcon(new ImageIcon(getClass().getResource(
                    "../frame/buttonIcons/myWorkSpace.png")));
            ImageIcon icon = new ImageIcon(getClass().getResource(
                    "../frame/buttonIcons/myWorkSpace2.png"));
            workSpaceButton.setRolloverIcon(icon);
            workSpaceButton.setSelectedIcon(icon);
            workSpaceButton.setSelected(true);
            workSpaceButton.addActionListener(new toolsButtonActionAdapter());
        }
        return workSpaceButton;
    }

    private GlassButton getProgressButton() {
        if (progressButton == null) {
            progressButton = new GlassButton();
            progressButton.setActionCommand("采购进货");
            progressButton.setIcon(new ImageIcon(getClass().getResource(
                    "../frame/buttonIcons/caigou1.png")));
            ImageIcon icon = new ImageIcon(getClass().getResource(
                    "../frame/buttonIcons/caigou2.png"));
            progressButton.setRolloverIcon(icon);
            progressButton.setSelectedIcon(icon);
            progressButton.addActionListener(new toolsButtonActionAdapter());
        }
        return progressButton;
    }

    private GlassButton getBookProjectButton() {
        if (bookProjectButton == null) {
            bookProjectButton = new GlassButton();
            bookProjectButton.setActionCommand("仓库入库");
            bookProjectButton.setIcon(new ImageIcon(getClass().getResource(
                    "../frame/buttonIcons/ruku1.png")));
            ImageIcon icon = new ImageIcon(getClass().getResource(
                    "../frame/buttonIcons/ruku2.png"));
            bookProjectButton.setRolloverIcon(icon);
            bookProjectButton.setSelectedIcon(icon);
            bookProjectButton.addActionListener(new toolsButtonActionAdapter());
        }
        return bookProjectButton;
    }

    private GlassButton getStockButton() {
        if (stockButton == null) {
            stockButton = new GlassButton();
            stockButton.setActionCommand("仓库出库");

            stockButton.setIcon(new ImageIcon(getClass().getResource(
                    "../frame/buttonIcons/chuku1.png")));
            ImageIcon icon = new ImageIcon(getClass().getResource(
                    "../frame/buttonIcons/chuku2.png"));
            stockButton.setRolloverIcon(icon);
            stockButton.setSelectedIcon(icon);
            stockButton.addActionListener(new toolsButtonActionAdapter());
        }
        return stockButton;
    }

    private GlassButton getPersonnelManagerButton() {
        if (personnelManagerButton == null) {
            personnelManagerButton = new GlassButton();
            personnelManagerButton.setActionCommand("查询及统计系统");
            personnelManagerButton.setIcon(new ImageIcon(getClass()
                    .getResource("../frame/buttonIcons/person2.png")));
            ImageIcon icon = new ImageIcon(getClass().getResource(
                    "../frame/buttonIcons/person1.png"));
            personnelManagerButton.setRolloverIcon(icon);
            personnelManagerButton.setSelectedIcon(icon);
            personnelManagerButton
                    .addActionListener(new toolsButtonActionAdapter());
        }
        return personnelManagerButton;
    }

    private GlassButton getDeptManagerButton() {
        if (deptManagerButton == null) {
            deptManagerButton = new GlassButton();
            deptManagerButton.setActionCommand("查询及统计系统");
            deptManagerButton.setIcon(new ImageIcon(getClass().getResource(
                    "../frame/buttonIcons/deptButton.png")));
            ImageIcon icon = new ImageIcon(getClass().getResource(
                    "../frame/buttonIcons/deptButton2.png"));
            deptManagerButton.setRolloverIcon(icon);
            deptManagerButton.setSelectedIcon(icon);
            deptManagerButton.addActionListener(new toolsButtonActionAdapter());
        }
        return deptManagerButton;
    }

    class toolsButtonActionAdapter implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == workSpaceButton) {
                backPanel.removeAll();
                backPanel.add(label_1);
                firstLabel.setBounds(133, 38, 123, 15);
                backPanel.add(firstLabel);
                panel_1.setBounds(10, 63, 611, 376);
                backPanel.add(panel_1);
                firstLabel.setText("基本档案管理");
                repaint();
            }
            if (e.getSource() == progressButton) {
                backPanel.removeAll();
                backPanel.add(label_1);
                firstLabel.setBounds(133, 38, 123, 15);
                backPanel.add(firstLabel);
                panel_1.setBounds(10, 63, 611, 386);
                StockPanel stockPanel = new StockPanel();
                firstLabel.setText("采购订货");
                backPanel.add(stockPanel);
                repaint();

            }
            if (e.getSource() == bookProjectButton) {
                backPanel.removeAll();
                backPanel.add(label_1);
                firstLabel.setBounds(133, 38, 123, 15);
                backPanel.add(firstLabel);
                panel_1.setBounds(10, 63, 611, 386);
                JoinDepotPanel joinPanel = new JoinDepotPanel();
                backPanel.add(joinPanel);

                firstLabel.setText("仓库入库");
                repaint();

            }
            if (e.getSource() == stockButton) {
                backPanel.removeAll();
                backPanel.add(label_1);
                firstLabel.setBounds(133, 38, 123, 15);
                backPanel.add(firstLabel);
                panel_1.setBounds(10, 63, 611, 386);
                OutDepotPanel outPanel = new OutDepotPanel();
                backPanel.add(outPanel);
                firstLabel.setText("仓库出库");
                repaint();

            }
            if (e.getSource() == personnelManagerButton) {
                backPanel.removeAll();
                backPanel.add(label_1);
                firstLabel.setBounds(133, 38, 123, 15);
                backPanel.add(firstLabel);
                panel_1.setBounds(10, 63, 611, 386);
                PersonnelPanel outPanel = new PersonnelPanel();
                backPanel.add(outPanel);
                firstLabel.setText("人员管理");
                repaint();

            }
            if (e.getSource() == deptManagerButton) {
                backPanel.removeAll();
                backPanel.add(label_1);
                firstLabel.setBounds(133, 38, 123, 15);
                backPanel.add(firstLabel);
                panel_1.setBounds(10, 63, 611, 386);
                DeptPanel outPanel = new DeptPanel();
                backPanel.add(outPanel);
                // backPanel.add(panel_1);
                firstLabel.setText("部门管理");
                repaint();

            }
        }
    }

    private SmallScrollPanel getModuleButtonGroup() {

        if (moduleButtonGroup == null) {
            moduleButtonGroup = new SmallScrollPanel();
            moduleButtonGroup.setBounds(250, 20, 434, 68);
            moduleButtonGroup.setOpaque(false);

            moduleButtonGroup.setViewportView(getPanel());
            moduleButtonGroup.getalAlphaScrollPane()
                    .setViewportView(getPanel());

        }

        return moduleButtonGroup;
        // TODO Auto-generated method stub

    }

}

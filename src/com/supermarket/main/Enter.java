package com.supermarket.main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.supermarket.dao.UserDao;
import com.supermarket.bean.User;
import com.supermarket.mainFrame.RemoveButtonFrame;
import com.supermarket.panel.ClockPanel;
import com.supermarket.util.Session;

import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.net.URL;

/**
 * @author: MaXinHong
 * @Email: 2372632949@qq.com
 * @Date: 2021/03/26 16:28
 * @Description: ��½��������
 */
public class Enter extends JFrame {// �̳�JFrame

    // �����������
    private BackgroundPanel contentPane;
    private JTextField userNameTextField;
    private JPasswordField passwordField;
    /*private Point spoint;*/

    private static final URL login;
    private static final URL close;
    private static final URL enter;

    static {
        login = Enter.class.getResource("login.png");
        close = Enter.class.getResource("close.png");
        enter = Enter.class.getResource("enter.png");
    }

    /**
     * ����������
     */
    public static void main(String[] args) {
        Enter enter = new Enter();
        enter.setVisible(true);
    }

    public Enter() {
        // ��¼�����
        int width = 559;
        // ��¼���߶�
        int height = 285;

        // �û��������ڵĹرհ�ťʱ����ִ�еĲ���
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ���ô˴����Ƿ�����û�������С
        setResizable(false);
        // �������
        //setLocationRelativeTo(null);
        setTitle("��¼����");
        // �����Ļ�ߴ�
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println("��Ļ�ֱ��ʣ�" + d.getWidth() + " * " + d.getHeight());
        // ���ڵ�����ͳߴ磬���ַ�ʽ����
        setBounds((d.width - width) / 2, (d.height - height) / 2, width, height);

        contentPane = getLoginPanel();
        // ���Բ���
        //contentPane.setLayout(null);
        // ���õ�½����ı߿�
        //contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
    }

    /**
     * ��ʼ����¼���
     *
     * @return BackgroundPanel
     */
    private BackgroundPanel getLoginPanel() {
        if (contentPane != null) {
            return contentPane;
        }

        // ������¼������
        contentPane = new BackgroundPanel();
        // ���͸��
        contentPane.setOpaque(false);
        // ������屳��ͼƬ
        contentPane.setImage(getToolkit().getImage(login));
        //contentPane.setLayout(null);
        // �������¼�������
        // contentPane.addMouseListener(new TitleMouseAdapter());

        // ���ʱ�����
        addClockPanel();
        // ����û���������
        addUserPasswordPanel();
        // ���ʱ�����
        addEnterButtonPanel();

        /*ImageIcon imageIconClose = new ImageIcon(close);*/

        // �����궯��������
        return contentPane;
    }

    /**
     * ���ʱ�����
     */
    private void addEnterButtonPanel() {
        // ��Ӱ�ť
        JButton enterButton = new JButton();
        // ���ò���
        ImageIcon imageIcon = new ImageIcon(enter);
        enterButton.setIcon(imageIcon);
        enterButton.setBounds(253, 116, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        // ȡ���������
        enterButton.setContentAreaFilled(false);
        // ȡ���߿�
        enterButton.setBorder(null);

        // ��ť�ĵ����¼�
        enterButton.addActionListener(e -> {
            System.out.println("�����¼��ť");
            // �û���
            String userName = userNameTextField.getText();
            // ����
            String passWord = passwordField.getText();

            System.out.println("�û�����" + userName + " ======>>> ���룺" + passWord);
            if (userName == null || "".equals(userName.trim())) {
                JOptionPane.showMessageDialog(getContentPane(), "�������û���", "����", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (passWord == null || "".equals(passWord.trim())) {
                JOptionPane.showMessageDialog(getContentPane(), "����������", "����", JOptionPane.WARNING_MESSAGE);
                return;
            }

            UserDao userDao = new UserDao();
            User user = userDao.getUser(userName, passWord);
            System.out.println("��ǰ�û���Ϣ��" + user);

            if (user.getId() > 0) {
                // ����Session�����User����ֵ
                Session.setUser(user);
                // ����������
                RemoveButtonFrame frame = new RemoveButtonFrame();
                frame.setVisible(true);
                // ���ٳ�����ָ����ͼ�ν�����Դ����������Դ������Ӱ��
                Enter.this.dispose();
            } else {
                JOptionPane.showMessageDialog(getContentPane(), "�û������������", "����", JOptionPane.ERROR_MESSAGE);
                userNameTextField.setText(null);
                passwordField.setText(null);
            }
        });

        contentPane.add(enterButton);
    }

    /**
     * ����û���������
     */
    private void addUserPasswordPanel() {

        /* ��������� */
        int titleX = 40;
        /* ���������� */
        int ContextX = 92;
        /* ���������� */
        int titleW = 54;
        /* ����������� */
        int ContextW = 139;
        /* ��������߶� */
        int titleH = 15;
        /* ���������߶� */
        int ContextH = 25;
        /* �û������� */
        int userY = 116;
        /* ���������� */
        int passWordY = 158;

        // �û�����ǩ
        JLabel userNameLabel = new JLabel("�û�����");
        userNameLabel.setBounds(titleX, userY, titleW, titleH);// ���ñ�ǩ�ı߽磬���������С
        contentPane.add(userNameLabel);

        // �û����ı���
        userNameTextField = new JTextField();
        userNameTextField.setBounds(ContextX, userY - 3, ContextW, ContextH);
        contentPane.add(userNameTextField);
        /*userNameTextField.setColumns(10);*/

        // �����ǩ
        JLabel passWordLabel = new JLabel("��  �룺");
        passWordLabel.setBounds(titleX, passWordY, titleW, titleH);
        contentPane.add(passWordLabel);

        // �����ı���
        passwordField = new JPasswordField();
        passwordField.setBounds(ContextX, passWordY - 3, ContextW, ContextH);
        contentPane.add(passwordField);
    }

    /**
     * ���ʱ�����
     */
    private void addClockPanel() {
        JPanel panel = new ClockPanel();
        panel.setBounds(377, 54, 151, 142);
        contentPane.add(panel);
    }

    /*public final class TitleMouseAdapter extends MouseAdapter implements Serializable {
        // ��걻���£�һֱ�����֣�����ͣ����ԭ�������ƶ�����ʱ�������� mousePressed �¼�
        public void mousePressed(MouseEvent e) {
            System.out.println("��걻���£�һֱ������");
            spoint = e.getPoint();
        }
    }*/
}

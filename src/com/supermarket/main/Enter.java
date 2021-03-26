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
 * @Description: 登陆窗体的设计
 */
public class Enter extends JFrame {// 继承JFrame

    // 定义所需组件
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
     * 程序主函数
     */
    public static void main(String[] args) {
        Enter enter = new Enter();
        enter.setVisible(true);
    }

    public Enter() {
        // 登录面板宽度
        int width = 559;
        // 登录面板高度
        int height = 285;

        // 用户单击窗口的关闭按钮时程序执行的操作
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置此窗体是否可由用户调整大小
        setResizable(false);
        // 窗体居中
        //setLocationRelativeTo(null);
        setTitle("登录窗体");
        // 获得屏幕尺寸
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println("屏幕分辨率：" + d.getWidth() + " * " + d.getHeight());
        // 窗口的坐标和尺寸，这种方式居中
        setBounds((d.width - width) / 2, (d.height - height) / 2, width, height);

        contentPane = getLoginPanel();
        // 绝对布局
        //contentPane.setLayout(null);
        // 设置登陆窗体的边框
        //contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
    }

    /**
     * 初始化登录面板
     *
     * @return BackgroundPanel
     */
    private BackgroundPanel getLoginPanel() {
        if (contentPane != null) {
            return contentPane;
        }

        // 创建登录面板对象
        contentPane = new BackgroundPanel();
        // 面板透明
        contentPane.setOpaque(false);
        // 设置面板背景图片
        contentPane.setImage(getToolkit().getImage(login));
        //contentPane.setLayout(null);
        // 添加鼠标事件监听器
        // contentPane.addMouseListener(new TitleMouseAdapter());

        // 添加时钟组件
        addClockPanel();
        // 添加用户名和密码
        addUserPasswordPanel();
        // 添加时钟组件
        addEnterButtonPanel();

        /*ImageIcon imageIconClose = new ImageIcon(close);*/

        // 添加鼠标动作监听器
        return contentPane;
    }

    /**
     * 添加时钟组件
     */
    private void addEnterButtonPanel() {
        // 添加按钮
        JButton enterButton = new JButton();
        // 设置布局
        ImageIcon imageIcon = new ImageIcon(enter);
        enterButton.setIcon(imageIcon);
        enterButton.setBounds(253, 116, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        // 取消填充区域
        enterButton.setContentAreaFilled(false);
        // 取消边框
        enterButton.setBorder(null);

        // 按钮的单击事件
        enterButton.addActionListener(e -> {
            System.out.println("点击登录按钮");
            // 用户名
            String userName = userNameTextField.getText();
            // 密码
            String passWord = passwordField.getText();

            System.out.println("用户名：" + userName + " ======>>> 密码：" + passWord);
            if (userName == null || "".equals(userName.trim())) {
                JOptionPane.showMessageDialog(getContentPane(), "请输入用户名", "警告", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (passWord == null || "".equals(passWord.trim())) {
                JOptionPane.showMessageDialog(getContentPane(), "请输入密码", "警告", JOptionPane.WARNING_MESSAGE);
                return;
            }

            UserDao userDao = new UserDao();
            User user = userDao.getUser(userName, passWord);
            System.out.println("当前用户信息：" + user);

            if (user.getId() > 0) {
                // 设置Session对象的User属性值
                Session.setUser(user);
                // 进入主界面
                RemoveButtonFrame frame = new RemoveButtonFrame();
                frame.setVisible(true);
                // 销毁程序中指定的图形界面资源，对数据资源不产生影响
                Enter.this.dispose();
            } else {
                JOptionPane.showMessageDialog(getContentPane(), "用户名或密码错误", "错误", JOptionPane.ERROR_MESSAGE);
                userNameTextField.setText(null);
                passwordField.setText(null);
            }
        });

        contentPane.add(enterButton);
    }

    /**
     * 添加用户名和密码
     */
    private void addUserPasswordPanel() {

        /* 标题横坐标 */
        int titleX = 40;
        /* 输入框横坐标 */
        int ContextX = 92;
        /* 标题组件宽度 */
        int titleW = 54;
        /* 输入框组件宽度 */
        int ContextW = 139;
        /* 标题组件高度 */
        int titleH = 15;
        /* 输入框组件高度 */
        int ContextH = 25;
        /* 用户纵坐标 */
        int userY = 116;
        /* 密码纵坐标 */
        int passWordY = 158;

        // 用户名标签
        JLabel userNameLabel = new JLabel("用户名：");
        userNameLabel.setBounds(titleX, userY, titleW, titleH);// 设置标签的边界，并调整其大小
        contentPane.add(userNameLabel);

        // 用户名文本框
        userNameTextField = new JTextField();
        userNameTextField.setBounds(ContextX, userY - 3, ContextW, ContextH);
        contentPane.add(userNameTextField);
        /*userNameTextField.setColumns(10);*/

        // 密码标签
        JLabel passWordLabel = new JLabel("密  码：");
        passWordLabel.setBounds(titleX, passWordY, titleW, titleH);
        contentPane.add(passWordLabel);

        // 密码文本框
        passwordField = new JPasswordField();
        passwordField.setBounds(ContextX, passWordY - 3, ContextW, ContextH);
        contentPane.add(passwordField);
    }

    /**
     * 添加时钟组件
     */
    private void addClockPanel() {
        JPanel panel = new ClockPanel();
        panel.setBounds(377, 54, 151, 142);
        contentPane.add(panel);
    }

    /*public final class TitleMouseAdapter extends MouseAdapter implements Serializable {
        // 鼠标被按下，一直不松手，不论停留在原处还是移动，此时触发的是 mousePressed 事件
        public void mousePressed(MouseEvent e) {
            System.out.println("鼠标被按下，一直不松手");
            spoint = e.getPoint();
        }
    }*/
}

package com.supermarket.panel;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;

import static java.util.Calendar.HOUR;
import static java.util.Calendar.MILLISECOND;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.SECOND;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author: MaXinHong
 * @Email: 2372632949@qq.com
 * @Date: 2021/03/26 18:44
 * @Description: 时钟线程
 */
public class ClockPanel extends JPanel implements Runnable {

    /**
     * BasicStroke          用于定义线条的特征
     * HOURS_POINT_WIDTH    时针
     * MINUTE_POINT_WIDTH   分针
     * SEC_POINT_WIDTH      秒针
     */
    private static final BasicStroke HOURS_POINT_WIDTH = new BasicStroke(5);
    private static final BasicStroke MINUTE_POINT_WIDTH = new BasicStroke(3);
    private static final BasicStroke SEC_POINT_WIDTH = new BasicStroke(2);
    /**
     * 中心坐标
     */
    private final int centerX;
    private final int centerY;
    /* 时钟背景 */
    private final ImageIcon background;
    /**
     * secLen       秒针长度
     * minuteLen    分针长度
     * hoursLen     时针长度
     */
    private static final int secLen = 60;
    private static final int minuteLen = 55;
    private static final int hoursLen = 36;
    private Point fp;

    private static final URL core;

    static {
        core = ClockPanel.class.getResource("Core.png");
    }

    public ClockPanel() {
        // 面板透明
        setOpaque(false);
        background = new ImageIcon(core);
        int iconWeight = background.getIconWidth();
        int iconHeight = background.getIconWidth();
        centerX = iconWeight / 2;
        centerY = iconHeight / 2;
        // 仅仅是设置最好的大小
        //setPreferredSize(new Dimension(532, 286));
        new Thread(this).start();
    }

    public void paint(Graphics g) {
        /**
         * Graphics2D
         * 绘图属性：stroke属性、paint属性、transform属性、clip属性、Composite属性
         * stroke属性         stroke属性控制线条的宽度、笔形样式、线段连接方式或短划线图案
         * paint属性          控制填充效果
         * transform属性      用来实现常用的图形平移、缩放和斜切等变换操作
         * clip属性           用于实现剪裁效果
         * Composite属性      设置图形重叠区域的效果
         */
        Graphics2D g2 = (Graphics2D) g.create();
        // 设置图形重叠区域的效果
        Composite composite = g2.getComposite();
        g2.setComposite(composite);

        /**
         *      透明值由透明到不透明是在0.0和1.0之间
         *      AlphaComposite.CLEAR        - 交集部分的颜色和透明被清除。
         *      AlphaComposite.DST          - 目标未修改。
         *      AlphaComposite.DST_ATOP     - 目标和源重叠的部分组合在源上。
         *      AlphaComposite.DST_IN       - 显示目标和源重叠的部分。
         *      AlphaComposite.DST_OUT      -显示目标没有和源重叠的部分。
         *      AlphaComposite.DST_OVER     - 目标覆盖在源之上。
         *      AlphaComposite.SRC          - 源复制给目标。
         *      AlphaComposite.SRC_ATOP     - 源和目标重叠的部分组合在目标上。
         *      AlphaComposite.SRC_IN       - 显示源和目标重叠的部分。
         *      AlphaComposite.SRC_OUT      - 显示源没有和目标重叠的部分。
         *      AlphaComposite.SRC_OVER     - 源覆盖在目标之上。
         */
        g2.setComposite(AlphaComposite.SrcOver.derive(0.6f));

        Calendar calendar = Calendar.getInstance();
        // 画钟
        drawClock(g2, calendar);

        // 绘制指定图像中已缩放到适合指定矩形内部的图像
        g2.drawImage(background.getImage(), 0, 0, this);
        /* 释放资源 */
        g2.dispose();
    }

    /**
     * @param g2       二维
     * @param calendar 日期
     */
    private void drawClock(Graphics2D g2, Calendar calendar) {
        // 毫秒
        int millisecond = calendar.get(MILLISECOND);
        // 秒
        int sec = calendar.get(SECOND);
        // 分
        int minutes = calendar.get(MINUTE);
        // 小时
        int hours = calendar.get(HOUR);

        String time = hours +
                ":" +
                minutes +
                ":" +
                sec +
                ":" +
                millisecond;
        //System.out.println("Now Time: " + time);

        // 55*6 ==> 5π/6+π=11π/6=1 5π/6
        // 计算角度
        double secAngle = (60 - sec) * 6 - (millisecond / 150);
        int minutesAngle = (60 - minutes) * 6;
        int hoursAngle = (12 - hours) * 360 / 12 - (minutes / 2);

        /**
         * Math.toRadians       角度转换为弧度
         * Math.sin             正弦函数-对边比斜边
         * Math.cos             余弦函数-邻边比斜边
         */
        // 时间坐标
        int secX = (int) (secLen * Math.sin(Math.toRadians(secAngle)));
        int secY = (int) (secLen * Math.cos(Math.toRadians(secAngle)));
        int minutesX = (int) (minuteLen * Math.sin(Math.toRadians(minutesAngle)));
        int minutesY = (int) (minuteLen * Math.cos(Math.toRadians(minutesAngle)));
        int hoursX = (int) (hoursLen * Math.sin(Math.toRadians(hoursAngle)));
        int hoursY = (int) (hoursLen * Math.cos(Math.toRadians(hoursAngle)));

        // 渲染
        // setStroke() 设置笔画的属性，如改变线条的粗细、虚实和定义线段端点的形状、风格
        g2.setColor(Color.BLACK);
        g2.setStroke(HOURS_POINT_WIDTH);
        g2.drawLine(centerX, centerY, centerX - hoursX, centerY - hoursY);
        g2.setColor(new Color(0x2F2F2F));
        g2.setStroke(MINUTE_POINT_WIDTH);
        g2.drawLine(centerX, centerY, centerX - minutesX, centerY - minutesY);
        g2.setColor(Color.RED);
        g2.setStroke(SEC_POINT_WIDTH);
        g2.drawLine(centerX, centerY, centerX - secX, centerY - secY);

        // 画点
        g2.fillOval(centerX - 5, centerY - 5, 10, 10);
    }

    public void run() {
        // TODO Auto-generated method stub
        try {
            while (true) {
                if (getRootPane() != null) {
                    JFrame main = (JFrame) getRootPane().getParent();
                    if (main != null && main.isVisible()) {
                        EventQueue.invokeLater(new Runnable() {
                            public void run() {
                                // TODO Auto-generated method stub
                                ClockPanel.this.repaint();
                            }
                        });

                    }
                }
                Thread.sleep(150);
            }

        } catch (InterruptedException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    protected void do_this_mouseDragged(final MouseEvent e) {
        JDialog frame = (JDialog) getRootPane().getParent();
        Point point = e.getLocationOnScreen();
        frame.setLocation(point.x - fp.x, point.y - fp.y);

    }

    protected void do_this_mousePressed(final MouseEvent e) {
        fp = e.getPoint();
    }

}

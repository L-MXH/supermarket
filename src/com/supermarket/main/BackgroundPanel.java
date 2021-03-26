package com.supermarket.main;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 * @author: MaXinHong
 * @Email: 2372632949@qq.com
 * @Date: 2021/03/27 16:25
 * @Description: 背景面板
 */
public class BackgroundPanel extends JPanel {
    // 背景图片
    private Image image;

    public BackgroundPanel() {
        // 设置控件透明
        setOpaque(false);
        // 使用绝对定位布局控件
        setLayout(null);
    }

    /**
     * 设置背景图片对象的方法
     *
     * @param image
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * 画出背景
     */
    protected void paintComponent(Graphics g) {
        // 如果图片已经初始化
        if (image != null) {
            // 画出图片
            g.drawImage(image, 0, 0, this);
        }
        super.paintComponent(g);
    }
}

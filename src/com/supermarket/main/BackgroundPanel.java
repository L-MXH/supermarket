package com.supermarket.main;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 * @author: MaXinHong
 * @Email: 2372632949@qq.com
 * @Date: 2021/03/27 16:25
 * @Description: �������
 */
public class BackgroundPanel extends JPanel {
    // ����ͼƬ
    private Image image;

    public BackgroundPanel() {
        // ���ÿؼ�͸��
        setOpaque(false);
        // ʹ�þ��Զ�λ���ֿؼ�
        setLayout(null);
    }

    /**
     * ���ñ���ͼƬ����ķ���
     *
     * @param image
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * ��������
     */
    protected void paintComponent(Graphics g) {
        // ���ͼƬ�Ѿ���ʼ��
        if (image != null) {
            // ����ͼƬ
            g.drawImage(image, 0, 0, this);
        }
        super.paintComponent(g);
    }
}

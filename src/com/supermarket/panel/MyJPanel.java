package com.supermarket.panel;

/**
 * ���ܣ�������л��Ʊ���ͼƬ
 */

import java.awt.*;
import java.net.URL;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MyJPanel extends JPanel {

	public void paintComponent(Graphics g) {
		try {
			URL url = getClass().getResource("../frame/buttonIcons/back.jpg");
			ImageIcon image = new ImageIcon(url);
			g.drawImage(image.getImage(), 0, 0, this);

		} catch (Exception e) {

		}
	}

}
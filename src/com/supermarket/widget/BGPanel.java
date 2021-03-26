package com.supermarket.widget;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.omg.CORBA.INITIALIZE;

import com.sun.corba.se.impl.orbutil.graph.Graph;

public class BGPanel extends JPanel implements Serializable {

	/**
	 * 功能：移动面板背景的实现
	 */
	private static final long serialversionUID = 1L;
	private ImageIcon icon;
	public static final int HORIZOMGTAL_FILL = 1;
	public static final int VERTICAL_FILL = 2;
	public static final int BOTH_FILL = 3;
	public static final int NO_FILL = 0;
	public int iconFill = NO_FILL;

	public BGPanel() {
		// TODO Auto-generated method stub
		super();
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		this.setSize(new Dimension(300, 200));
		this.setLayout(new GridBagLayout());
	}

	public ImageIcon getiIcon() {
		return icon;
	}

	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (icon != null) {
			switch (iconFill) {
			case NO_FILL:
				g.drawImage(icon.getImage(), 0, 0, this);
				break;
			case HORIZOMGTAL_FILL:
				g.drawImage(icon.getImage(), 0, 0, getWidth(),
						icon.getIconHeight(), this);
				break;
			case VERTICAL_FILL:
				g.drawImage(icon.getImage(), 0, 0, icon.getIconWidth(),
						getHeight(), this);
				break;
			case BOTH_FILL:
				g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(),
						this);
				break;
			default:
				break;
			}
		}
	}

	public int getIconFill() {
		return iconFill;
	}

	public void setIconFill(int iconFill) {
		this.iconFill = iconFill;
	}

}

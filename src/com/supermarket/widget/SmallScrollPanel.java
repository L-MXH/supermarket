package com.supermarket.widget;

import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import static javax.swing.BorderFactory.createEmptyBorder;
import javax.swing.*;

public class SmallScrollPanel extends BGPanel {

	/**
	 * 功能：移动面板
	 */
	private static final long serialVersionUID = 3592329256836525981L;
	private AlphaScrollPane alphaScrollPane;
	private JButton leftScrollButton = null;
	private JButton rightScrollButton = null;
	private scrollMouseAdapter scrollMouseAdapter;
	private ImageIcon icon1;
	private ImageIcon icon2;

	public SmallScrollPanel() {
		scrollMouseAdapter = new scrollMouseAdapter();
		// icon1 = new ImageIcon(getClass().getResource(""));
		// icon2 = new ImageIcon(getClass().getResource(""));
		// setIcon(icon1);
		setIconFill(BOTH_FILL);
		initialize();
	}

	private void initialize() {
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setHgap(0);
		this.setLayout(borderLayout);
		this.setSize(new Dimension(300, 84));
		this.setOpaque(false);
		this.add(getalAlphaScrollPane(), BorderLayout.CENTER);
		this.add(getleftScrollButton(), BorderLayout.EAST);
		this.add(getRightScrollButton(), BorderLayout.WEST);
	}

	public AlphaScrollPane getalAlphaScrollPane() {
		if (alphaScrollPane == null) {
			alphaScrollPane = new AlphaScrollPane();
			alphaScrollPane.setPreferredSize(new Dimension(564, 69));
			alphaScrollPane
					.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
			alphaScrollPane
					.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			alphaScrollPane.setBordetPaint(false);
			alphaScrollPane
					.addComponentListener(new ScrollButtonShowListener());

		}
		return alphaScrollPane;
	}

	public void setViewportView(Component view) {
		alphaScrollPane.setViewportView(view);
	}

	private class ScrollButtonShowListener extends ComponentAdapter implements
			Serializable {
		private static final long serialVersionUID = 814596372430146361L;

		public void componentResized(ComponentEvent e) {
			JScrollBar scrollBar = alphaScrollPane.getHorizontalScrollBar();
			int scrollWidth = scrollBar.getMaximum();
			int paneWidth = alphaScrollPane.getWidth();

			if (paneWidth >= scrollWidth) {
				getleftScrollButton().setVisible(false);
				getRightScrollButton().setVisible(false);
			}
			if (paneWidth < scrollWidth) {
				getleftScrollButton().setVisible(true);
				getRightScrollButton().setVisible(true);
			}
		}
	}

	private JButton getRightScrollButton() {
		// TODO Auto-generated method stub

		if (leftScrollButton == null) {
			leftScrollButton = new JButton();
			ImageIcon icon1 = new ImageIcon(getClass().getResource(
					"../frame/buttonIcons/zuoyidongoff.png"));
			ImageIcon icon2 = new ImageIcon(getClass().getResource(
					"../frame/buttonIcons/zuoyidongon.png"));
			leftScrollButton.setOpaque(false);
			leftScrollButton.setBorder(createEmptyBorder(0, 10, 0, 0));
			leftScrollButton.setIcon(icon1);
			leftScrollButton.setPressedIcon(icon2);
			leftScrollButton.setRolloverIcon(icon2);
			leftScrollButton.setContentAreaFilled(false);
			leftScrollButton.setPreferredSize(new Dimension(38, 0));
			leftScrollButton.setFocusable(false);
			leftScrollButton.addMouseListener(scrollMouseAdapter);
		}

		return leftScrollButton;
	}

	private JButton getleftScrollButton() {
		if (rightScrollButton == null) {
			rightScrollButton = new JButton();
			ImageIcon icon1 = new ImageIcon(getClass().getResource(

			"../frame/buttonIcons/youyidongoff.png"));
			ImageIcon icon2 = new ImageIcon(getClass().getResource(
					"../frame/buttonIcons/youyidongon.png"));
			rightScrollButton.setOpaque(false);
			rightScrollButton.setBorder(createEmptyBorder(0, 0, 0, 10));
			rightScrollButton.setIcon(icon1);
			rightScrollButton.setPressedIcon(icon2);
			rightScrollButton.setRolloverIcon(icon2);
			rightScrollButton.setContentAreaFilled(false);
			rightScrollButton.setPreferredSize(new Dimension(38, 92));
			rightScrollButton.setFocusable(false);
			rightScrollButton.addMouseListener(scrollMouseAdapter);
		}
		return rightScrollButton;
	}

	private final class scrollMouseAdapter extends MouseAdapter implements
			Serializable {
		private static final long serialVersionUID = 5589204752770150732L;
		JScrollBar scrollBar = getalAlphaScrollPane().getHorizontalScrollBar();
		private boolean isPressed = true;

		public void mousePressed(MouseEvent e) {
			Object source = e.getSource();
			isPressed = true;
			if (source == getleftScrollButton()) {
				scrollMoved(-1);
			} else {
				scrollMoved(1);
			}
		}

		private void scrollMoved(final int orientation) {
			new Thread() {
				private int oldValue = scrollBar.getValue();

				public void run() {
					while (isPressed) {
						try {
							Thread.sleep(10);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						oldValue = scrollBar.getValue();
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								scrollBar.setValue(oldValue + 3 * orientation);
							}
						});
					}
				}
			}.start();

		}

		public void mouseExited(MouseEvent arg0) {
			isPressed = false;
		}

		public void mouseReleased(MouseEvent e) {
			isPressed = false;
		}

		// protected void paintComponent(Graphics g){
		// super.paintComponent(g);
		// g.drawImage(icon2.getImage(), 0, 0, getWidth(), getHeight(), this);
		// }

	}
}

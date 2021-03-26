/**
 * 功能：首个滚动窗格的初始化代码实现
 */
package com.supermarket.widget;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

public class AlphaScrollPane extends JScrollPane {

	private static final long serialVersionUID = 1L;
	private boolean borderPaint = false;
	private boolean headerOpaquae = true;
	private boolean viewportBorderPaint = false;

	public AlphaScrollPane() {
		super();
		Initializer();
	}

	private void Initializer() {
		this.setSize(300, 200);
		setBackground(new Color(151, 188, 229));
		setOpaque(false);
		addPropertyChangeListener(new PropertyChangeAdapter());
	}

	private final class PropertyChangeAdapter implements
			PropertyChangeListener, Serializable {

		public void propertyChange(PropertyChangeEvent e) {
			String name = e.getPropertyName();
			if (name.equals("ancestor")) {
				getViewport().setOpaque(isOpaque());
				if (!viewportBorderPaint)
					setViewportBorder(null);

				if (!isBorderPaint())
					setBorder(null);

			}
			if (name.equals("background")) {
				setBorder(new LineBorder(getBackground(), 1, true));
			}

		}
	}

	public boolean isBorderPaint() {
		return borderPaint;
	}

	public void setBordetPaint(boolean borderPaint) {
		this.borderPaint = borderPaint;
	}

	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		Component[] components = getComponents();
		for (Component component : components) {
			component.setEnabled(enabled);
		}
		Component view = getViewport().getView();
		if (view != null)
			view.setEnabled(enabled);
		if (getColumnHeader() != null)
			getColumnHeader().setEnabled(enabled);
	}

	public void setHeaderOpaquae(boolean headerOpaquse) {
		
		this.headerOpaquae = headerOpaquse;
	}

	public boolean isViewportBorderPaint() {
		return viewportBorderPaint;
	}

	public void setViewportBorderPaint(boolean viewportBorderPaint) {
		this.viewportBorderPaint = viewportBorderPaint;
	}
}

package com.mec.model;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MPanel extends JPanel{
	

	private ImageIcon icon;
	
	private Image img;
	
	public MPanel(String path) {
		this.icon = new ImageIcon(path);
		this.img = icon.getImage();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this.getHeight(), this.getWidth(),this);
	}
	


}

package com.mec.test;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.mec.core.ViewFactory;
import com.mec.core.ViewFactoryBuilder;
import com.mec.model.MFrame;

public class Test {

	public static void main(String[] args) {
		ViewFactory view = ViewFactoryBuilder.load("/test.xml");
		MFrame mframe = view.getElement("mfrmTestView");
		mframe.setVisible(true);
	}

}

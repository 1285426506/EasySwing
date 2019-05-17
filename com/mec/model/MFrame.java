package com.mec.model;

import java.awt.Container;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class MFrame extends JFrame implements IContainer {
	private static final long serialVersionUID = -8348043575558482089L;
	
	private Container container;

	public MFrame() throws HeadlessException {
		init();
	}

	public MFrame(GraphicsConfiguration arg0) {
		super(arg0);
		init();
	}

	public MFrame(String arg0) throws HeadlessException {
		super(arg0);
		init();
	}

	public MFrame(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		init();
	}
	
	private void init() {
		this.container = getContentPane();
		this.container.setLayout(null);
	}

	@Override
	public void addElement(JComponent component) {
		this.container.add(component);
	}

}

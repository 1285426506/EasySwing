package com.mec.model;

import java.awt.Container;

import javax.swing.JComponent;
import javax.swing.JInternalFrame;

public class MInternalFrame extends JInternalFrame implements IContainer {
	private static final long serialVersionUID = -6407119698628425553L;
	private Container container;

	public MInternalFrame() {
		init();
	}

	public MInternalFrame(String arg0) {
		super(arg0);
		init();
	}

	public MInternalFrame(String arg0, boolean arg1) {
		super(arg0, arg1);
		init();
	}

	public MInternalFrame(String arg0, boolean arg1, boolean arg2) {
		super(arg0, arg1, arg2);
		init();
	}

	public MInternalFrame(String arg0, boolean arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		init();
	}

	public MInternalFrame(String arg0, boolean arg1, boolean arg2, boolean arg3, boolean arg4) {
		super(arg0, arg1, arg2, arg3, arg4);
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

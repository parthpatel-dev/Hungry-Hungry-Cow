package com.sfu.group6.hungrycow.ui;

import javax.swing.*;
import java.awt.*;

public class UIFrame extends JFrame{
	public UIFrame() {
		UIFrame UI = new UIFrame();
		UI.setVisible(true);
		UI.setSize(600,600);
		add(new UIPanel());
		UI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		UI.setLocationRelativeTo(null);
	}
	
}

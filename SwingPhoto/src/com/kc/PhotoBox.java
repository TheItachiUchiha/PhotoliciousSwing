package com.kc;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PhotoBox extends JPanel {
	private JLabel photoIcon = new JLabel();
	private JLabel photoName = new JLabel();
	
	public PhotoBox(ImageIcon icon,String name) {
		setLayout(new BoxLayout(new PhotoBox(icon,name) , BoxLayout.Y_AXIS));
		photoIcon.setIcon(icon);
		photoName.setText(name);
	}

}

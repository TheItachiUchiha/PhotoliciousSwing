package com.kc.model;

import java.awt.Dimension;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PhotoBox extends JPanel {
	private JLabel photoIcon = new JLabel();
	private JLabel photoName = new JLabel();
	private JLabel print = new JLabel();
	
	public PhotoBox(ImageIcon icon,File file, JLabel print) {
		photoIcon.setPreferredSize(new Dimension(150, 150));
		photoIcon.setIcon(icon);
		photoName.setText((file.getName().length()) > 20 ? file.getName().substring(0,10)+".." : file.getName());
		this.print.setText(print.getText());
		setLayout(new BoxLayout(new PhotoBox(icon,file,print) , BoxLayout.Y_AXIS));
		
	}
	
	public void setSelected()
	{
		print.setText("Selected");
	}

}

package com.kc.model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.kc.utils.ImageHelper;
import com.kc.utils.WrapLayout;

public class PhotoBox extends JPanel {
	
	
	private JLabel photoIcon;
	private JLabel photoName;
	private JLabel print;
	private ImageHelper imageHelper;
	
	
	
	

	public PhotoBox(File file, JLabel print) throws IOException {
		
		imageHelper = new ImageHelper();
		this.photoIcon =  new JLabel();
		this.photoIcon.setIcon(imageHelper.createThumbnails(file));
		this.photoIcon.setMaximumSize(new Dimension(200, 150));
		this.photoIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.photoName = new JLabel();
		photoName.setText((file.getName().length()) > 20 ? file.getName().substring(0,10)+".." : file.getName());
		this.photoName.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		this.print = new JLabel();
		this.print.setText(print.getText());
		this.print.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.print.setMaximumSize(new Dimension(10, 10));
	
		setLayout(new BoxLayout(this , BoxLayout.PAGE_AXIS));
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black), getBorder()));
		//setPreferredSize(new Dimension(200,150));
		
		add(this.photoIcon);
		add(this.photoName);
		add(this.print);
	}
	
	public void setSelected()
	{
		print.setText("Selected");
	}

}

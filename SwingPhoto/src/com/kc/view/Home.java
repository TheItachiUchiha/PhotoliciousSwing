package com.kc.view;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;


public class Home extends JPanel {

	/**
	 * Create the frame.
	 */
	public Home() {
		
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		setLayout(new BorderLayout(0, 0));
		add(splitPane, BorderLayout.CENTER);
		
		
		JPanel leftPane = new JPanel();
		splitPane.setLeftComponent(leftPane);
		leftPane.setLayout(new BoxLayout(leftPane, BoxLayout.Y_AXIS));
		
		JPanel detailsBox = new JPanel();
		leftPane.add(detailsBox);
		detailsBox.setLayout(new BoxLayout(detailsBox, BoxLayout.Y_AXIS));
		
		Component rigidArea = Box.createRigidArea(new Dimension(0, 30));
		detailsBox.add(rigidArea);
		
		JLabel lblNewLabel = new JLabel("No. of Photos in Output Folder");
		detailsBox.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(0, 10));
		detailsBox.add(rigidArea_1);
		
		JLabel noOfPhotos = new JLabel("No Lab");
		detailsBox.add(noOfPhotos);
		detailsBox.add(Box.createRigidArea(new Dimension(0, 20)));
		
		JLabel lblNoOfPrints = new JLabel("No. Of Prints (Current Session)");
		lblNoOfPrints.setFont(new Font("Tahoma", Font.BOLD, 11));
		detailsBox.add(lblNoOfPrints);
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(0, 10));
		detailsBox.add(rigidArea_2);
		
		JLabel noOfPrint = new JLabel("No print");
		detailsBox.add(noOfPrint);
		detailsBox.add(Box.createRigidArea(new Dimension(0, 20)));
		
		JLabel lblNewestPhotoFile = new JLabel("Newest Photo File Name");
		lblNewestPhotoFile.setFont(new Font("Tahoma", Font.BOLD, 11));
		detailsBox.add(lblNewestPhotoFile);
		
		Component rigidArea_3 = Box.createRigidArea(new Dimension(0, 10));
		detailsBox.add(rigidArea_3);
		
		JLabel newFileName = new JLabel("name");
		detailsBox.add(newFileName);
		detailsBox.add(Box.createRigidArea(new Dimension(0, 20)));
		
		JLabel lblNewLabel_1 = new JLabel("Photo Timestamp");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		detailsBox.add(lblNewLabel_1);
		
		Component rigidArea_4 = Box.createRigidArea(new Dimension(0, 10));
		detailsBox.add(rigidArea_4);
		
		JLabel timeStamp = new JLabel("stamp");
		detailsBox.add(timeStamp);
		detailsBox.add(Box.createRigidArea(new Dimension(0, 50)));
		
		JPanel printOptionsBox = new JPanel();
		leftPane.add(printOptionsBox);
		printOptionsBox.setLayout(new BoxLayout(printOptionsBox, BoxLayout.Y_AXIS));
		
		Component rigidArea_p = Box.createRigidArea(new Dimension(30, 0));
		detailsBox.add(rigidArea_p);
		
		JButton printButton = new JButton("Print Selected");
		printOptionsBox.add(printButton);
		
		Component rigidArea_5 = Box.createRigidArea(new Dimension(0, 30));
		printOptionsBox.add(rigidArea_5);
		
		JButton slideshowButton = new JButton("Begin Slideshow");
		printOptionsBox.add(slideshowButton);
		
		Component rigidArea_6 = Box.createRigidArea(new Dimension(0, 50));
		printOptionsBox.add(rigidArea_6);
		
		JPanel imageBox = new JPanel();
		leftPane.add(imageBox);
		imageBox.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);
	}

}

package com.kc;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Settings extends JFrame {

	private JPanel mainPane;
	private JTextField fieldImageFolder;
	private JTextField fieldOutputfolder;
	private JTextField fieldWatermark;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Settings frame = new Settings();
					frame.setExtendedState(frame.getExtendedState()|JFrame.MAXIMIZED_BOTH);
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Settings() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 827, 493);
		mainPane = new JPanel();
		mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		Component rigidArea = Box.createRigidArea(new Dimension(0, 300));
		mainPane.add(rigidArea);
		Component rigidArea1 = Box.createRigidArea(new Dimension(300, 0));
		mainPane.add(rigidArea1);
		setContentPane(mainPane);
		GridBagLayout gbl_mainPane = new GridBagLayout();
		gbl_mainPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_mainPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_mainPane.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_mainPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		mainPane.setLayout(gbl_mainPane);
		
		int hiddenHeight=getInsets().bottom+getInsets().top;  
		int hiddenWidth=getInsets().left+getInsets().right;  
		// now setting the location of the JPanel so that it's at the center of the visible area of the JFrame  
		mainPane.setLocation(200,200); 
		
		fieldImageFolder = new JTextField();
		GridBagConstraints gbc_fieldImageFolder = new GridBagConstraints();
		gbc_fieldImageFolder.insets = new Insets(0, 0, 5, 5);
		gbc_fieldImageFolder.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldImageFolder.gridx = 3;
		gbc_fieldImageFolder.gridy = 3;
		gbc_fieldImageFolder.anchor = GridBagConstraints.CENTER;
		mainPane.add(fieldImageFolder, gbc_fieldImageFolder);
		fieldImageFolder.setColumns(10);
		
		JButton imageFolder = new JButton("Browse Directory");
		imageFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		GridBagConstraints gbc_imageFolder = new GridBagConstraints();
		gbc_imageFolder.insets = new Insets(0, 0, 5, 5);
		gbc_imageFolder.gridx = 4;
		gbc_imageFolder.gridy = 3;
		gbc_imageFolder.anchor = GridBagConstraints.CENTER;
		mainPane.add(imageFolder, gbc_imageFolder);
		
		fieldOutputfolder = new JTextField();
		GridBagConstraints gbc_fieldOutputfolder = new GridBagConstraints();
		gbc_fieldOutputfolder.insets = new Insets(0, 0, 5, 5);
		gbc_fieldOutputfolder.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldOutputfolder.gridx = 3;
		gbc_fieldOutputfolder.gridy = 4;
		gbc_fieldOutputfolder.anchor = GridBagConstraints.CENTER;
		mainPane.add(fieldOutputfolder, gbc_fieldOutputfolder);
		fieldOutputfolder.setColumns(10);
		
		JButton outputFolder = new JButton("Browse Directory");
		GridBagConstraints gbc_outputFolder = new GridBagConstraints();
		gbc_outputFolder.insets = new Insets(0, 0, 5, 5);
		gbc_outputFolder.gridx = 4;
		gbc_outputFolder.gridy = 4;
		gbc_outputFolder.anchor = GridBagConstraints.CENTER;
		mainPane.add(outputFolder, gbc_outputFolder);
		
		fieldWatermark = new JTextField();
		fieldWatermark.setColumns(10);
		GridBagConstraints gbc_fieldWatermark = new GridBagConstraints();
		gbc_fieldWatermark.insets = new Insets(0, 0, 0, 5);
		gbc_fieldWatermark.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldWatermark.gridx = 3;
		gbc_fieldWatermark.gridy = 5;
		gbc_fieldWatermark.anchor = GridBagConstraints.CENTER;
		mainPane.add(fieldWatermark, gbc_fieldWatermark);
		
		JButton watermarkImage = new JButton("Browse File");
		GridBagConstraints gbc_watermarkImage = new GridBagConstraints();
		gbc_watermarkImage.insets = new Insets(0, 0, 0, 5);
		gbc_watermarkImage.gridx = 4;
		gbc_watermarkImage.gridy = 5;
		gbc_watermarkImage.anchor = GridBagConstraints.CENTER;
		mainPane.add(watermarkImage, gbc_watermarkImage);
		
		imageFolder.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

					JFileChooser chooser = new JFileChooser();
				   /* FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "JPG & GIF Images", "jpg", "gif");
				    chooser.setFileFilter(filter);*/
					chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				    int returnVal = chooser.showOpenDialog(mainPane);
				    if(returnVal == JFileChooser.APPROVE_OPTION)
				    {
				    	File tempFile =chooser.getSelectedFile();
				    	if(tempFile!=null)
						{
							fieldImageFolder.setText(tempFile.getPath());
						}
				    }
			}
		});
		
		outputFolder.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    int returnVal = chooser.showOpenDialog(mainPane);
			    if(returnVal == JFileChooser.APPROVE_OPTION)
			    {
			    	File tempFile =chooser.getSelectedFile();
			    	if(tempFile!=null)
					{
						fieldOutputfolder.setText(tempFile.getPath());
					}
			    }
			}
		});
		
		watermarkImage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser chooser = new JFileChooser();
			    int returnVal = chooser.showOpenDialog(mainPane);
			    if(returnVal == JFileChooser.APPROVE_OPTION)
			    {
			    	File tempFile =chooser.getSelectedFile();
			    	if(tempFile!=null)
					{
						fieldWatermark.setText(tempFile.getPath());
					}
			    }
			}
		});
	}

}

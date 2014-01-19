package com.kc;
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
import javax.swing.border.EmptyBorder;


public class Home extends JFrame {

	private JPanel contentPane;
	private JTextField fieldImageFolder;
	private JTextField fieldOutputFolder;
	private JTextField fieldWatermark;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 843, 523);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		final JPanel panel = new JPanel();
		tabbedPane.addTab("Settings", null, panel, null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 46, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{-3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		fieldImageFolder = new JTextField();
		GridBagConstraints gbc_fieldImageFolder = new GridBagConstraints();
		gbc_fieldImageFolder.gridwidth = 2;
		gbc_fieldImageFolder.insets = new Insets(0, 0, 5, 5);
		gbc_fieldImageFolder.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldImageFolder.gridx = 10;
		gbc_fieldImageFolder.gridy = 3;
		panel.add(fieldImageFolder, gbc_fieldImageFolder);
		fieldImageFolder.setColumns(10);
		
		JButton imageFolder = new JButton("Browse Directory");
		GridBagConstraints gbc_imageFolder = new GridBagConstraints();
		gbc_imageFolder.insets = new Insets(0, 0, 5, 5);
		gbc_imageFolder.gridx = 12;
		gbc_imageFolder.gridy = 3;
		panel.add(imageFolder, gbc_imageFolder);
		
		fieldOutputFolder = new JTextField();
		fieldOutputFolder.setColumns(10);
		GridBagConstraints gbc_fieldOutputFolder = new GridBagConstraints();
		gbc_fieldOutputFolder.gridwidth = 2;
		gbc_fieldOutputFolder.insets = new Insets(0, 0, 5, 5);
		gbc_fieldOutputFolder.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldOutputFolder.gridx = 10;
		gbc_fieldOutputFolder.gridy = 4;
		panel.add(fieldOutputFolder, gbc_fieldOutputFolder);
		
		JButton outputFolder = new JButton("Browse Directory");
		GridBagConstraints gbc_outputFolder = new GridBagConstraints();
		gbc_outputFolder.insets = new Insets(0, 0, 5, 5);
		gbc_outputFolder.gridx = 12;
		gbc_outputFolder.gridy = 4;
		panel.add(outputFolder, gbc_outputFolder);
		
		fieldWatermark = new JTextField();
		fieldWatermark.setColumns(10);
		GridBagConstraints gbc_fieldWatermark = new GridBagConstraints();
		gbc_fieldWatermark.gridwidth = 2;
		gbc_fieldWatermark.insets = new Insets(0, 0, 5, 5);
		gbc_fieldWatermark.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldWatermark.gridx = 10;
		gbc_fieldWatermark.gridy = 5;
		panel.add(fieldWatermark, gbc_fieldWatermark);
		
		JButton watermarkImage = new JButton("Browse File");
		GridBagConstraints gbc_watermarkImage = new GridBagConstraints();
		gbc_watermarkImage.insets = new Insets(0, 0, 5, 5);
		gbc_watermarkImage.gridx = 12;
		gbc_watermarkImage.gridy = 5;
		panel.add(watermarkImage, gbc_watermarkImage);
		
		JPanel actionPanel = new JPanel();
		GridBagConstraints gbc_actionPanel = new GridBagConstraints();
		gbc_actionPanel.gridheight = 2;
		gbc_actionPanel.insets = new Insets(0, 0, 5, 5);
		gbc_actionPanel.fill = GridBagConstraints.BOTH;
		gbc_actionPanel.gridx = 11;
		gbc_actionPanel.gridy = 6;
		panel.add(actionPanel, gbc_actionPanel);
		
		JButton convert = new JButton("Convert");
		actionPanel.add(convert);
		
		JButton reset = new JButton("Reset");
		actionPanel.add(reset);
		convert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent paramActionEvent) {
			}
		});
		reset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent paramActionEvent) {

				fieldImageFolder.setText("");
				fieldOutputFolder.setText("");
				fieldWatermark.setText("");
				
			}
		});
		
imageFolder.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

					JFileChooser chooser = new JFileChooser();
				   /* FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "JPG & GIF Images", "jpg", "gif");
				    chooser.setFileFilter(filter);*/
					chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				    int returnVal = chooser.showOpenDialog(panel);
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
	    int returnVal = chooser.showOpenDialog(panel);
	    if(returnVal == JFileChooser.APPROVE_OPTION)
	    {
	    	File tempFile =chooser.getSelectedFile();
	    	if(tempFile!=null)
			{
				fieldOutputFolder.setText(tempFile.getPath());
			}
	    }
	}
});

watermarkImage.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JFileChooser chooser = new JFileChooser();
	    int returnVal = chooser.showOpenDialog(panel);
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
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		tabbedPane.addTab("Home", null, splitPane, null);
		
		
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

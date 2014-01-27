package com.kc.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.concurrent.ExecutorService;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.kc.service.Convert;
import com.kc.utils.PhotoliciousUtils;

public class Settings extends JPanel {

	private JTextField fieldImageFolder;
	private JTextField fieldOutputFolder;
	private JTextField fieldWatermark;
	static Home home;

	public Settings(final MainWindow mainWindow, final ExecutorService exec,
			final JTabbedPane jTabbedPane) {
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				46, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { -3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gbl_panel);

		fieldImageFolder = new JTextField();
		GridBagConstraints gbc_fieldImageFolder = new GridBagConstraints();
		gbc_fieldImageFolder.gridwidth = 2;
		gbc_fieldImageFolder.insets = new Insets(0, 0, 5, 5);
		gbc_fieldImageFolder.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldImageFolder.gridx = 10;
		gbc_fieldImageFolder.gridy = 3;
		add(fieldImageFolder, gbc_fieldImageFolder);
		fieldImageFolder.setColumns(10);

		JButton imageFolder = new JButton("Browse Directory");
		GridBagConstraints gbc_imageFolder = new GridBagConstraints();
		gbc_imageFolder.insets = new Insets(0, 0, 5, 5);
		gbc_imageFolder.gridx = 12;
		gbc_imageFolder.gridy = 3;
		add(imageFolder, gbc_imageFolder);

		fieldOutputFolder = new JTextField();
		fieldOutputFolder.setColumns(10);
		GridBagConstraints gbc_fieldOutputFolder = new GridBagConstraints();
		gbc_fieldOutputFolder.gridwidth = 2;
		gbc_fieldOutputFolder.insets = new Insets(0, 0, 5, 5);
		gbc_fieldOutputFolder.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldOutputFolder.gridx = 10;
		gbc_fieldOutputFolder.gridy = 4;
		add(fieldOutputFolder, gbc_fieldOutputFolder);

		JButton outputFolder = new JButton("Browse Directory");
		GridBagConstraints gbc_outputFolder = new GridBagConstraints();
		gbc_outputFolder.insets = new Insets(0, 0, 5, 5);
		gbc_outputFolder.gridx = 12;
		gbc_outputFolder.gridy = 4;
		add(outputFolder, gbc_outputFolder);

		fieldWatermark = new JTextField();
		fieldWatermark.setColumns(10);
		GridBagConstraints gbc_fieldWatermark = new GridBagConstraints();
		gbc_fieldWatermark.gridwidth = 2;
		gbc_fieldWatermark.insets = new Insets(0, 0, 5, 5);
		gbc_fieldWatermark.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldWatermark.gridx = 10;
		gbc_fieldWatermark.gridy = 5;
		add(fieldWatermark, gbc_fieldWatermark);

		JButton watermarkImage = new JButton("Browse File");
		GridBagConstraints gbc_watermarkImage = new GridBagConstraints();
		gbc_watermarkImage.insets = new Insets(0, 0, 5, 5);
		gbc_watermarkImage.gridx = 12;
		gbc_watermarkImage.gridy = 5;
		add(watermarkImage, gbc_watermarkImage);

		JPanel actionPanel = new JPanel();
		GridBagConstraints gbc_actionPanel = new GridBagConstraints();
		gbc_actionPanel.gridheight = 2;
		gbc_actionPanel.insets = new Insets(0, 0, 5, 5);
		gbc_actionPanel.fill = GridBagConstraints.BOTH;
		gbc_actionPanel.gridx = 11;
		gbc_actionPanel.gridy = 6;
		add(actionPanel, gbc_actionPanel);

		JButton convert = new JButton("Convert");
		actionPanel.add(convert);

		JButton reset = new JButton("Reset");
		actionPanel.add(reset);
		convert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent paramActionEvent) {

				PhotoliciousUtils.saveOutputFolder(fieldOutputFolder.getText());
				final Convert convert123 = new Convert(fieldImageFolder
						.getText(), fieldWatermark.getText(), fieldOutputFolder
						.getText());
				exec.execute(convert123);
				
				jTabbedPane.remove(0);
				home = new Home(mainWindow, exec);
				jTabbedPane.addTab("Home", null, home, null);
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
				/*
				 * FileNameExtensionFilter filter = new FileNameExtensionFilter(
				 * "JPG & GIF Images", "jpg", "gif");
				 * chooser.setFileFilter(filter);
				 */
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = chooser.showOpenDialog(mainWindow);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File tempFile = chooser.getSelectedFile();
					if (tempFile != null) {
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
				int returnVal = chooser.showOpenDialog(mainWindow);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File tempFile = chooser.getSelectedFile();
					if (tempFile != null) {
						fieldOutputFolder.setText(tempFile.getPath());
					}
				}
			}
		});

		watermarkImage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showOpenDialog(mainWindow);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File tempFile = chooser.getSelectedFile();
					if (tempFile != null) {
						fieldWatermark.setText(tempFile.getPath());
					}
				}
			}
		});
	}

}

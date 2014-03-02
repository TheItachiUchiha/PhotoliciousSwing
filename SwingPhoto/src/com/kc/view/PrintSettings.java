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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.kc.service.Convert;
import com.kc.utils.CommonConstants;
import com.kc.utils.PhotoliciousUtils;
import com.kc.utils.Validation;
import javax.swing.JComboBox;

public class PrintSettings extends JPanel {
	private Validation validation;
	static Home home;
	
	String sizes[] = {"3r","4r","5r"};

	public PrintSettings( ) {
		validation = new Validation();
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {230, 220, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 320};
		gbl_panel.rowHeights = new int[] { -3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 0.0, 1.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gbl_panel);
		
		JButton save = new JButton("Save");
		GridBagConstraints gbc_save = new GridBagConstraints();
		gbc_save.gridwidth = 4;
		gbc_save.insets = new Insets(0, 0, 5, 5);
		gbc_save.gridx = 9;
		gbc_save.gridy = 5;
		add(save, gbc_save);
		
		JComboBox sizeCombo = new JComboBox(sizes);
		GridBagConstraints gbc_sizeCombo = new GridBagConstraints();
		gbc_sizeCombo.gridwidth = 4;
		gbc_sizeCombo.insets = new Insets(0, 0, 5, 5);
		gbc_sizeCombo.fill = GridBagConstraints.HORIZONTAL;
		gbc_sizeCombo.gridx = 9;
		gbc_sizeCombo.gridy = 4;
		add(sizeCombo, gbc_sizeCombo);
		
		JLabel lblSelectInputDirectory = new JLabel("Printer");
		GridBagConstraints gbc_lblSelectInputDirectory = new GridBagConstraints();
		gbc_lblSelectInputDirectory.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelectInputDirectory.gridx = 8;
		gbc_lblSelectInputDirectory.gridy = 3;
		add(lblSelectInputDirectory, gbc_lblSelectInputDirectory);
		
		JComboBox printerCombo = new JComboBox();
		GridBagConstraints gbc_printerCombo = new GridBagConstraints();
		gbc_printerCombo.gridwidth = 4;
		gbc_printerCombo.insets = new Insets(0, 0, 5, 5);
		gbc_printerCombo.fill = GridBagConstraints.HORIZONTAL;
		gbc_printerCombo.gridx = 9;
		gbc_printerCombo.gridy = 3;
		add(printerCombo, gbc_printerCombo);
		
		JLabel lblSelectOutputDirectory = new JLabel("Print Size");
		GridBagConstraints gbc_lblSelectOutputDirectory = new GridBagConstraints();
		gbc_lblSelectOutputDirectory.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelectOutputDirectory.gridx = 8;
		gbc_lblSelectOutputDirectory.gridy = 4;
		add(lblSelectOutputDirectory, gbc_lblSelectOutputDirectory);
	}

}

package com.kc.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import com.kc.model.PrintServiceVO;
import com.kc.service.PrintImage;
import com.kc.utils.CommonConstants;
import com.kc.utils.PhotoliciousUtils;
import com.kc.utils.Validation;

public class PrintSettings extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Validation validation;
	static Home home;
	PrintImage printImage;
	String sizes[] = {"3r","4r","5r"};
	String time[] = {"3","5"};
	//String printer[] = {"Color","Normal"};
	String currentSize;
	String currentSlideTime;
	String currentPrinter;

	public PrintSettings( ) {
		try{
			validation = new Validation();
			printImage = new PrintImage();
			currentSlideTime = PhotoliciousUtils.readSlideTime();
			currentSize = PhotoliciousUtils.readPrintSize();
 			currentPrinter = PhotoliciousUtils.readDefaultPrinter();
			
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[] {230, 220, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 320};
			gbl_panel.rowHeights = new int[] {230, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30};
			gbl_panel.columnWeights = new double[] { 1.0, 0.0, 1.0, 0.0, 0.0, 0.0,
					0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0,
					Double.MIN_VALUE };
			gbl_panel.rowWeights = new double[] { 1.0, 0.0, 1.0, 0.0, 0.0, 0.0,
					0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
			setLayout(gbl_panel);
			
			JSeparator separator = new JSeparator();
			separator.setForeground(Color.BLACK);
			separator.setBackground(Color.BLACK);
			GridBagConstraints gbc_separator = new GridBagConstraints();
			gbc_separator.fill = GridBagConstraints.HORIZONTAL;
			gbc_separator.gridwidth = 5;
			gbc_separator.insets = new Insets(0, 0, 5, 5);
			gbc_separator.gridx = 8;
			gbc_separator.gridy = 5;
			add(separator, gbc_separator);
			
			JLabel lblSlideTime = new JLabel("Slide Time");
			GridBagConstraints gbc_lblSlideTime = new GridBagConstraints();
			gbc_lblSlideTime.anchor = GridBagConstraints.WEST;
			gbc_lblSlideTime.insets = new Insets(0, 0, 5, 5);
			gbc_lblSlideTime.gridx = 8;
			gbc_lblSlideTime.gridy = 6;
			add(lblSlideTime, gbc_lblSlideTime);
			
			final JComboBox<String> slideTimeCombo = new JComboBox<String>(time);
			GridBagConstraints gbc_slideTime = new GridBagConstraints();
			gbc_slideTime.gridwidth = 4;
			gbc_slideTime.insets = new Insets(0, 0, 5, 5);
			gbc_slideTime.fill = GridBagConstraints.HORIZONTAL;
			gbc_slideTime.gridx = 9;
			gbc_slideTime.gridy = 6;
			add(slideTimeCombo, gbc_slideTime);
			
			if(!(null==currentSlideTime || "".equals(currentSlideTime)))
			{
				slideTimeCombo.setSelectedItem(currentSlideTime);
			}
			
			final JLabel message = new JLabel("");
			message.setVerticalAlignment(SwingConstants.TOP);
			message.setForeground(Color.GREEN);
			GridBagConstraints gbc_message = new GridBagConstraints();
			gbc_message.gridwidth = 4;
			gbc_message.insets = new Insets(0, 0, 5, 5);
			gbc_message.gridx = 9;
			gbc_message.gridy = 8;
			add(message, gbc_message);
			
			JButton save = new JButton("Save");
			GridBagConstraints gbc_save = new GridBagConstraints();
			gbc_save.gridwidth = 4;
			gbc_save.insets = new Insets(0, 0, 5, 5);
			gbc_save.gridx = 9;
			gbc_save.gridy = 7;
			add(save, gbc_save);
			
			final JComboBox<String> sizeCombo = new JComboBox<String>(sizes);
			GridBagConstraints gbc_sizeCombo = new GridBagConstraints();
			gbc_sizeCombo.gridwidth = 4;
			gbc_sizeCombo.insets = new Insets(0, 0, 5, 5);
			gbc_sizeCombo.fill = GridBagConstraints.HORIZONTAL;
			gbc_sizeCombo.gridx = 9;
			gbc_sizeCombo.gridy = 4;
			add(sizeCombo, gbc_sizeCombo);
			
			if(!(null==currentSize || "".equals(currentSize)))
			{
				sizeCombo.setSelectedItem(currentSize);
			}
			
			JLabel lblSelectInputDirectory = new JLabel("Printer");
			GridBagConstraints gbc_lblSelectInputDirectory = new GridBagConstraints();
			gbc_lblSelectInputDirectory.anchor = GridBagConstraints.WEST;
			gbc_lblSelectInputDirectory.insets = new Insets(0, 0, 5, 5);
			gbc_lblSelectInputDirectory.gridx = 8;
			gbc_lblSelectInputDirectory.gridy = 3;
			add(lblSelectInputDirectory, gbc_lblSelectInputDirectory);
			
			final JComboBox<PrintServiceVO> printerCombo = new JComboBox<PrintServiceVO>(printImage.printerList());
			GridBagConstraints gbc_printerCombo = new GridBagConstraints();
			gbc_printerCombo.gridwidth = 4;
			gbc_printerCombo.insets = new Insets(0, 0, 5, 5);
			gbc_printerCombo.fill = GridBagConstraints.HORIZONTAL;
			gbc_printerCombo.gridx = 9;
			gbc_printerCombo.gridy = 3;
			add(printerCombo, gbc_printerCombo);
			
			PrintServiceVO[] list = printImage.printerList();
			for(int i=0;i< list.length; i++)
			{
				if(null!=list[i]){
					if(list[i].toString().equals(currentPrinter)){
						printerCombo.setSelectedIndex(i);
					}
				}
			}
			
			JLabel lblSelectOutputDirectory = new JLabel("Print Size");
			GridBagConstraints gbc_lblSelectOutputDirectory = new GridBagConstraints();
			gbc_lblSelectOutputDirectory.anchor = GridBagConstraints.WEST;
			gbc_lblSelectOutputDirectory.insets = new Insets(0, 0, 5, 5);
			gbc_lblSelectOutputDirectory.gridx = 8;
			gbc_lblSelectOutputDirectory.gridy = 4;
			add(lblSelectOutputDirectory, gbc_lblSelectOutputDirectory);
			
			save.addActionListener(new ActionListener() {
	
				@Override
				public void actionPerformed(ActionEvent paramActionEvent) {
					PhotoliciousUtils.saveConfiguration(printerCombo.getSelectedItem().toString(), sizeCombo.getSelectedItem().toString(), slideTimeCombo.getSelectedItem().toString());
					message.setText(CommonConstants.SUCCESS);
				}
			});
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}

package com.kc.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;


public class Validation {
	public boolean isEmpty(JTextField textField){
		Pattern p =Pattern.compile(" ");
		 Matcher m = p.matcher(textField.getText());
		 String temp=textField.getText();
		 temp=m.replaceAll("");
		
			if ((temp==null)||("".equals(temp))){
				textField.requestFocus();
				return true;
			}
			
			else{
				
			
			return false;
			}
		}
}

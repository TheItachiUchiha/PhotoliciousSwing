package com.kc.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.UIManager;

public class ImageHelper 
{
	Color bgColor;
	
	public ImageHelper()
	{
		bgColor = UIManager.getColor("Panel.background");  
	}
	public ImageIcon createThumbnails(File file) throws IOException  
    {  
        final int WIDTH  = 200;  
        final int HEIGHT = 150;  
        BufferedImage images;
        AffineTransform at;  
        
        BufferedImage origs = ImageIO.read(file);  
        
        images = new BufferedImage(WIDTH, HEIGHT,  
                                      BufferedImage.TYPE_INT_RGB);  
        Graphics2D g2 = images.createGraphics();  
        g2.setPaint(bgColor);  
        g2.fillRect(0, 0, WIDTH, HEIGHT);  
        // scale to fit  
        double xScale = (double)WIDTH  / origs.getWidth();  
        double yScale = (double)HEIGHT / origs.getHeight();  
        double scale = Math.min(xScale, yScale);  
        // center thumbnail image  
        double x = (WIDTH  - origs.getWidth()  * scale)/2;  
        double y = (HEIGHT - origs.getHeight() * scale)/2;  
        at = AffineTransform.getTranslateInstance(x, y);  
        at.scale(scale, scale);  
        g2.drawRenderedImage(origs, at);  
        g2.dispose();  
        return new ImageIcon(images);
          
    }
	
	public ImageIcon createFullScreenImage(File file) throws IOException  
    {  
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int WIDTH  = (int)screenSize.getWidth();
        final int HEIGHT = (int)screenSize.getHeight();
        BufferedImage images;
        AffineTransform at;  
        
        BufferedImage origs = ImageIO.read(file);  
        
        images = new BufferedImage(WIDTH, HEIGHT,  
                                      BufferedImage.TYPE_INT_RGB);  
        Graphics2D g2 = images.createGraphics();  
        g2.setPaint(bgColor);  
        g2.fillRect(0, 0, WIDTH, HEIGHT);  
        // scale to fit  
        double xScale = (double)WIDTH  / origs.getWidth();  
        double yScale = (double)HEIGHT / origs.getHeight();  
        double scale = Math.min(xScale, yScale);  
        // center thumbnail image  
        double x = (WIDTH  - origs.getWidth()  * scale)/2;  
        double y = (HEIGHT - origs.getHeight() * scale)/2;  
        at = AffineTransform.getTranslateInstance(x, y);  
        at.scale(scale, scale);  
        g2.drawRenderedImage(origs, at);  
        g2.dispose();  
       
        return new ImageIcon(images);
    }
	
	public ImageIcon imagePreview(File file) throws IOException  
    {  
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int WIDTH  = 200;
        final int HEIGHT = 150;
        BufferedImage images;
        AffineTransform at;  
        
        BufferedImage origs = ImageIO.read(file);  
        
        images = new BufferedImage(WIDTH, HEIGHT,  
                                      BufferedImage.TYPE_INT_RGB);  
        Graphics2D g2 = images.createGraphics();
        g2.setPaint(bgColor);  
        g2.fillRect(0, 0, WIDTH, HEIGHT);  
        // scale to fit  
        double xScale = (double)WIDTH  / origs.getWidth();  
        double yScale = (double)HEIGHT / origs.getHeight();  
        double scale = Math.min(xScale, yScale);  
        // center thumbnail image  
        double x = (WIDTH  - origs.getWidth()  * scale)/2;  
        double y = (HEIGHT - origs.getHeight() * scale)/2;  
        at = AffineTransform.getTranslateInstance(x, y);  
        at.scale(scale, scale);  
        g2.drawRenderedImage(origs, at);  
        g2.dispose();  
        ImageIcon imageIcon = new ImageIcon(images);
        imageIcon.setDescription(file.getPath());
        return imageIcon;
    }
}

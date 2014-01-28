package com.kc.service;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.Timer;

import com.kc.model.ScreenVO;
import com.kc.utils.ImageHelper;
import com.kc.utils.PhotoliciousUtils;

public class Slideshow
{

	File outputFolder;
	ScreenVO screenVO;
	ImageHelper helper;
	List<String> listOfImagesParsed;
	JFrame frame;
	JPanel panel;
	ExecutorService executorService;
	JLabel label;

	public Slideshow(File outputFolder, ScreenVO screenVO, ExecutorService executorService) {
		this.outputFolder = outputFolder;
		this.screenVO = screenVO;
		this.helper = new ImageHelper();
		this.listOfImagesParsed = new ArrayList<String>();
		this.executorService = executorService;
		start(outputFolder, screenVO, executorService);
	}

	
		
		
	public void start(final File outputFolder, ScreenVO screenVO, ExecutorService executorService) {
		try {

			this.frame = new JFrame();
			this.panel = new JPanel();
			frame.add(panel);
			frame.setSize(new Dimension(200, 200));
			frame.setUndecorated(true);
			frame.addKeyListener(new KeyListener() {
				
				@Override
				public void keyTyped(java.awt.event.KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void keyReleased(java.awt.event.KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void keyPressed(java.awt.event.KeyEvent e) {
					if (e.getKeyCode() == e.VK_ESCAPE) {
		                frame.dispose();
		                Thread.interrupted();
		            }
					
				}
			});
			
			label = new JLabel();
			panel.add(label);
			screenVO.getScreen().setFullScreenWindow(frame);
			frame.setVisible(true);
			Timer timer = new Timer(5000, startCycle());
	        timer.setRepeats(true);
	        timer.start();
			
			//screenVO.getScreen().setFullScreenWindow(frame);

			/* frame.setExtendedState(JFrame.MAXIMIZED_BOTH); */
		
					
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Action startCycle() {
        return new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	//Need to close this thread when ESC is pressed
            	executorService.execute(new MyTask());
            }
        };
    }

	
	

	public static ScreenVO[] fetchListOfScreen() {
		ScreenVO[] listOfScreenVO = new ScreenVO[10];
		try {
			GraphicsDevice[] listOfScreen;
			GraphicsEnvironment ge = GraphicsEnvironment
					.getLocalGraphicsEnvironment();
			listOfScreen = ge.getScreenDevices();
			for (int i = 0; i < listOfScreen.length; i++) {
				ScreenVO screenVO = new ScreenVO();
				if (i == 0) {
					screenVO.setName("Primary Screen" + " : "
							+ listOfScreen[i].getDisplayMode().getWidth() + "x"
							+ listOfScreen[i].getDisplayMode().getHeight());
				} else {
					screenVO.setName("Screen" + i + 1 + " : "
							+ listOfScreen[i].getDisplayMode().getWidth() + "x"
							+ listOfScreen[i].getDisplayMode().getHeight());
				}
				screenVO.setScreen(listOfScreen[i]);
				listOfScreenVO[i] = screenVO;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listOfScreenVO;
	}

	
	public class MyTask extends SwingWorker<ImageIcon, Object>
	{
			@Override
			protected ImageIcon doInBackground() throws Exception {
				return loadImages();
				
			}
			
			@Override
			protected void done()
			{
				try
				{
					label.setIcon(get());
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
			
			public ImageIcon loadImages()
			{
				ImageIcon icon = new ImageIcon();
				File[] listOfFiles = PhotoliciousUtils
						.filterJPEGImagesFromFolder(outputFolder
								.listFiles());
				
				for (final File file : listOfFiles) {
					try {
						if(listOfImagesParsed.size()==listOfFiles.length)
						{
							listOfImagesParsed.clear();
						}
						if(!listOfImagesParsed.contains(file.getName()))
						{
							icon= helper.createFullScreenImage(file);
							listOfImagesParsed.add(file.getName());
							break;
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				return icon;
			}
	}


}


	

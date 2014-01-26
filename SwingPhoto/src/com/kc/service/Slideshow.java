package com.kc.service;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.Timer;

import com.kc.model.ScreenVO;
import com.kc.utils.ImageHelper;
import com.kc.utils.PhotoliciousUtils;

public class Slideshow extends SwingWorker<Integer, Integer> {

	File outputFolder;
	ScreenVO screenVO;
	ImageHelper helper;

	public Slideshow(File outputFolder, ScreenVO screenVO) {
		this.outputFolder = outputFolder;
		this.screenVO = screenVO;
		this.helper = new ImageHelper();
	}

	public void start(final File outputFolder, ScreenVO screenVO) {
		try {

			final JFrame frame = new JFrame();
			final JPanel panel = new JPanel();

			frame.add(panel);
			frame.setVisible(true);
			frame.setSize(new Dimension(200, 200));
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
		            }
					
				}
			});
			
			//screenVO.getScreen().setFullScreenWindow(frame);

			/* frame.setExtendedState(JFrame.MAXIMIZED_BOTH); */
		
					File[] listOfFile = PhotoliciousUtils
							.filterJPEGImagesFromFolder(outputFolder
									.listFiles());
					List list = PhotoliciousUtils.nameOfFiles(listOfFile);
					while (!Thread.interrupted()) {
						File[] listOfFiles = PhotoliciousUtils
								.filterJPEGImagesFromFolder(outputFolder
										.listFiles());

						if (list.size() > listOfFiles.length) {
							list.clear();
							list = PhotoliciousUtils.nameOfFiles(listOfFiles);
						} else {
							for (final File file : listOfFiles) {
								try {
									panel.removeAll();
									JLabel fullImage = new JLabel(helper
											.createThumbnails(file));
									panel.add(fullImage);
									Thread.sleep(5000);
									
								} catch (Exception ex) {
									ex.printStackTrace();
								}
							}
						}
					}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	@Override
	protected Integer doInBackground() throws Exception {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				start(outputFolder, screenVO);
			}
		});
		return null;
	}

}

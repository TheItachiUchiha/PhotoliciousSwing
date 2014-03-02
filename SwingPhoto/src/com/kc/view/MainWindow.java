package com.kc.view;

import it.sauronsoftware.junique.AlreadyLockedException;
import it.sauronsoftware.junique.JUnique;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.kc.utils.CommonConstants;
import com.kc.utils.PhotoliciousUtils;
import com.kc.utils.SplashScreen;

public class MainWindow extends JFrame
{
	private JPanel contentPane;
	ExecutorService exec = Executors.newCachedThreadPool(new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        }
    });
	/**
	 * Launch the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		// set the name of the application menu item
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Thepica Live studio");
		String appId = "Thepica Live studio-App";
    	PhotoliciousUtils.saveOutputFolder(CommonConstants.DEFAULT_OUTPUT_FOLDER);
    	boolean running;
    	try {
			JUnique.acquireLock(appId);
			running=true;
		} 
    	catch (AlreadyLockedException e) 
		{
			running=false;
		}
    	if(running)
    	{
    		SplashScreen splash = new SplashScreen(2000);
    		splash.showSplash();
    		EventQueue.invokeLater(new Runnable() {
    			public void run() {
    				try {
    					
    					MainWindow frame = new MainWindow();
    					frame.setTitle("Thepica Live studio");
    					frame.setExtendedState(frame.getExtendedState()|JFrame.MAXIMIZED_BOTH);
    					frame.setVisible(true);
    					frame.setResizable(true);
    					
    				} catch (Exception e) {
    					e.printStackTrace();
    				}
    			}
    		});
    	}  
		
	}
	
	public MainWindow()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 843, 523);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		final Settings settings = new Settings(this, exec, tabbedPane);
		tabbedPane.addTab("Settings", null, settings, null);
		
		
	}
}

package com.kc.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;

import com.kc.model.PhotoBox;
import com.kc.model.PrintServiceVO;
import com.kc.model.ScreenVO;
import com.kc.service.PrintImage;
import com.kc.service.Slideshow;
import com.kc.utils.ImageHelper;
import com.kc.utils.PhotoliciousUtils;
import com.kc.utils.WrapLayout;




public class Home extends JPanel {

	JLabel newFile;
	JLabel timeStamp;
	JLabel noOfPrint;
	JLabel number;
	List<String> list = new ArrayList<String>();
	PrintImage printImage;
	File outputFolder;
	ImageHelper helper;
	JPanel detailsBox;
	JPanel printOptionsBox;
	JPanel imageBox;
	JButton printButton;
	JButton slideshowButton;
	JLabel lblNoOfPrints;
	JLabel currentImage;
	Map<String, JLabel> filePrints = new HashMap<String, JLabel>();
	
	ExecutorService exec = null;
	
	SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
	String sizes[] = {"3r","4r","5r"};
	
	public Home(final MainWindow stage, final ExecutorService exec) {
		
		this.exec = exec;
		number = new JLabel("0");
		outputFolder = new File(PhotoliciousUtils.readOutputFolder());
		this.helper = new ImageHelper();
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		setLayout(new BorderLayout(0, 0));
		add(splitPane, BorderLayout.CENTER);
		splitPane.setLeftComponent(leftPane(stage));
		splitPane.setRightComponent(viewGallery(stage));
	}
	
	public JPanel leftPane(final MainWindow stage)
	{
		JPanel leftPane = new JPanel();
		
		leftPane.setLayout(new BoxLayout(leftPane, BoxLayout.Y_AXIS));
		leftPane.setPreferredSize(new Dimension(160, 0));
		
		detailsBox = new JPanel();
		leftPane.add(detailsBox);
		detailsBox.setLayout(new BoxLayout(detailsBox, BoxLayout.Y_AXIS));
		detailsBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		Component rigidArea = Box.createRigidArea(new Dimension(0, 30));
		detailsBox.add(rigidArea);
		
		JLabel lblNewLabel = new JLabel("No. of Photos in Output Folder             ");
		detailsBox.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(0, 10));
		detailsBox.add(rigidArea_1);
		
		number = new JLabel("0");
		detailsBox.add(number);
		detailsBox.add(Box.createRigidArea(new Dimension(0, 20)));
		
		lblNoOfPrints = new JLabel("No. Of Prints (Current Session)");
		lblNoOfPrints.setFont(new Font("Tahoma", Font.BOLD, 11));
		detailsBox.add(lblNoOfPrints);
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(0, 10));
		detailsBox.add(rigidArea_2);
		
		noOfPrint = new JLabel("0");
		detailsBox.add(noOfPrint);
		detailsBox.add(Box.createRigidArea(new Dimension(0, 20)));
		
		JLabel lblNewestPhotoFile = new JLabel("Newest Photo File Name");
		lblNewestPhotoFile.setFont(new Font("Tahoma", Font.BOLD, 11));
		detailsBox.add(lblNewestPhotoFile);
		
		Component rigidArea_3 = Box.createRigidArea(new Dimension(0, 10));
		detailsBox.add(rigidArea_3);
		
		newFile = new JLabel("");
		detailsBox.add(newFile);
		detailsBox.add(Box.createRigidArea(new Dimension(0, 20)));
		
		JLabel lblNewLabel_1 = new JLabel("Photo Timestamp");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		detailsBox.add(lblNewLabel_1);
		
		Component rigidArea_4 = Box.createRigidArea(new Dimension(0, 10));
		detailsBox.add(rigidArea_4);
		
		timeStamp = new JLabel("");
		detailsBox.add(timeStamp);
		
		Component rigidArea_6 = Box.createRigidArea(new Dimension(0, 20));
		detailsBox.add(rigidArea_6);
		
		JLabel lblCurrentlySelectedFile = new JLabel("Currently Selected File");
		lblCurrentlySelectedFile.setFont(new Font("Tahoma", Font.BOLD, 11));
		detailsBox.add(lblCurrentlySelectedFile);
		
		Component rigidArea_7 = Box.createRigidArea(new Dimension(0, 10));
		detailsBox.add(rigidArea_7);
		
		currentImage = new JLabel("");
		detailsBox.add(currentImage);
		
		Component rigidArea_9 = Box.createRigidArea(new Dimension(0, 60));
		leftPane.add(rigidArea_9);
		
		printOptionsBox = new JPanel();
		leftPane.add(printOptionsBox);
		printOptionsBox.setLayout(new BoxLayout(printOptionsBox, BoxLayout.Y_AXIS));
		printOptionsBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		Component rigidArea_p = Box.createRigidArea(new Dimension(30, 0));
		detailsBox.add(rigidArea_p);
		
		Component rigidArea_10 = Box.createRigidArea(new Dimension(220, 0));
		printOptionsBox.add(rigidArea_10);
		
		printButton = new JButton("Print Selected");
		printButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		printOptionsBox.add(printButton);
		
		
		Component rigidArea_5 = Box.createRigidArea(new Dimension(0, 30));
		printOptionsBox.add(rigidArea_5);
		
		slideshowButton = new JButton("Begin Slideshow");
		slideshowButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		printOptionsBox.add(slideshowButton);
		
		slideshowButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(PhotoliciousUtils.readSlideTime().equals("")||
						PhotoliciousUtils.readSlideTime()==null)
				{
					/*final JFrame frame= new JFrame("Slideshow");
					frame.setSize(300, 100);
					JPanel panel = new JPanel();
					BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
					panel.setLayout(layout);
					JPanel upperPane = new JPanel();
					JPanel lowerPane = new JPanel();
					JLabel selectScreen = new JLabel("Please Set the Slideshow Time in Settings !");
					upperPane.add(selectScreen);
					JButton button = new JButton("OK");
					lowerPane.add(button);
					
					button.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							frame.dispose();
						}
					});
					panel.add(upperPane, BorderLayout.LINE_START);
					panel.add(lowerPane, BorderLayout.CENTER);
					frame.add(panel);
					frame.setResizable(false);
					frame.setAlwaysOnTop(true);
					frame.setVisible(true);
					Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
					frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
					frame.show();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
					JOptionPane.showMessageDialog(Settings.home, "Please Set the Slideshow Time in Settings !");
				}
				else
				{
					final JFrame frame= new JFrame("Start Slideshow");
					JPanel panel = new JPanel();
					BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
					panel.setLayout(layout);
				
					
					
					JPanel upperPane = new JPanel();
					/*JPanel centerPane = new JPanel();*/
					JPanel lowerPane = new JPanel();
					/*FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER);
					upperPane.setLayout(flowLayout);
					lowerPane.setLayout(flowLayout);
					centerPane.setLayout(flowLayout);*/
					
					JLabel selectScreen = new JLabel("Screen");
					upperPane.add(selectScreen);
					
					/*JLabel selectTime = new JLabel("Time (in sec)");
					centerPane.add(selectTime);*/
					
					/*Integer[] timeList = new Integer[2];
					timeList[0] = 3;
					timeList[1] = 5;
					final JComboBox<Integer> timeListCombo = new JComboBox<Integer>(timeList);
					centerPane.add(timeListCombo);*/
					
					
					
					ScreenVO[] listOfScreensInstalled = Slideshow.fetchListOfScreen();
					final JComboBox<ScreenVO> screenList = new JComboBox<ScreenVO>(listOfScreensInstalled);
					
					upperPane.add(screenList);
					
					JButton button = new JButton("Start !");
					lowerPane.add(button);
					
					button.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							new Slideshow(outputFolder, (ScreenVO)screenList.getSelectedItem(), Integer.parseInt(PhotoliciousUtils.readSlideTime()), exec, stage);
							frame.dispose();
						}
					});
					
					
					panel.add(upperPane, BorderLayout.LINE_START);
					//panel.add(centerPane, BorderLayout.LINE_START);
					panel.add(lowerPane, BorderLayout.CENTER);
					
					frame.getContentPane().add(panel);
					frame.setSize(320, 150);
					frame.setResizable(false);
					frame.setAlwaysOnTop(true);
					frame.setVisible(true);
					Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
					frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
					frame.show();
				}
			}
		});
		
		printButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if(!(null==PhotoliciousUtils.readDefaultPrinter()||"".equals(PhotoliciousUtils.readDefaultPrinter())||
						null==PhotoliciousUtils.readPrintSize()||"".equals(PhotoliciousUtils.readPrintSize())))
				{
					if(imageBox.getComponents().length!=0)
					{
						/*final JFrame frame= new JFrame("Print");
						JPanel panel = new JPanel();
						BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
						panel.setLayout(layout);
						
						JPanel upperPane = new JPanel();
						JPanel centerPane = new JPanel();
						JPanel lowerPane = new JPanel();
						FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER);
						upperPane.setLayout(flowLayout);
						lowerPane.setLayout(flowLayout);
						centerPane.setLayout(flowLayout);
						
						
						JLabel selectPrinter = new JLabel("Printer");
						upperPane.add(selectPrinter);
						final JComboBox<PrintServiceVO> print = new JComboBox<PrintServiceVO>(printImage.printerList());
						upperPane.add(print);
						JLabel selectSize = new JLabel("Size");
						centerPane.add(selectSize);
						final JComboBox<String> size = new JComboBox<String>(sizes);
						centerPane.add(size);
						
						JButton button = new JButton("Print !");
						lowerPane.add(button);
						button.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								PrintImage image = new PrintImage(((ImageIcon)((JLabel)imageBox.getComponent(0)).getIcon()).getDescription(),size.getSelectedItem().toString());
								exec.submit(image);
								frame.dispose();
							}
						});
						
						//panel.add(upperPane, BorderLayout.LINE_START);
						panel.add(centerPane, BorderLayout.LINE_START);
						panel.add(lowerPane, BorderLayout.CENTER);
						
						frame.getContentPane().add(panel);
						frame.setSize(320, 150);
						frame.setResizable(false);
						frame.setAlwaysOnTop(true);
						frame.setVisible(true);
						Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
						frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
						frame.show();*/
						PrintImage image = new PrintImage(((ImageIcon)((JLabel)imageBox.getComponent(0)).getIcon()).getDescription(),PhotoliciousUtils.readPrintSize(),
								PhotoliciousUtils.readDefaultPrinter());
						exec.submit(image);
						
					}
					else
					{			
						JOptionPane.showMessageDialog(Settings.home, "Please Select An Image !");
					}
				}
				else {
					JOptionPane.showMessageDialog(Settings.home, "Please Set the Print Settings Correctly !");
				}
			}
		});
		
		Component rigidArea_8 = Box.createRigidArea(new Dimension(0, 50));
		leftPane.add(rigidArea_8);
		
		imageBox = new JPanel();
		//imageBox.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black), getBorder()));
		imageBox.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		imageBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		leftPane.add(imageBox);
		
		return leftPane;
		
	}
	public void removeBorder(JPanel panel)
	{
		panel.setBorder(BorderFactory.createEmptyBorder());
	}
	
	public JScrollPane viewGallery(final MainWindow stage)
	{
		JScrollPane root = null;
		final JPanel tile = new JPanel();
		
		try{
			WrapLayout Layout = new WrapLayout(FlowLayout.LEADING, 50, 10);
			tile.setLayout(Layout);
			
			root = new JScrollPane(tile, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			
		    exec.execute(new Runnable() {

                @Override
                public void run() {
                	outputFolder = new File(PhotoliciousUtils.readOutputFolder());
                	while(!Thread.interrupted())
                	{
                	final File[] listOfFiles = PhotoliciousUtils.filterJPEGImagesFromFolder(outputFolder.listFiles());
        			
                    SwingUtilities.invokeLater(new Runnable() {

                        @SuppressWarnings("deprecation")
						@Override
                        public void run() {
                        	number.setText(String.valueOf(listOfFiles.length));
                        	if(list.size()!=
                        			listOfFiles.length)
                        	{
                        		if(list.size()>listOfFiles.length)
                        		{
                        			list.clear();
                        			list = PhotoliciousUtils.nameOfFiles(listOfFiles);
                        			for(int i=0;i<tile.getComponentCount();i++)
                        			{
                        				PhotoBox vBox = (PhotoBox)tile.getComponent(i);
                        				if(!list.contains(((JLabel)(vBox.getComponent(1))).getText()));
                        				{
                        					tile.remove(i);
                        					break;
                        				}
                        			}
                        		}
                        		else
                        		{
                        		for (final File file : listOfFiles) {
                        			try{
                        			if(!list.contains(file.getName())){
                        				newFile.setText(file.getName());
                        				timeStamp.setText(sdf.format(file.lastModified()));
		                				System.out.println(file.getPath());
		                				
		                				
		                				
		                				filePrints.put(file.getName(), new JLabel("0"));
		                				final PhotoBox vBox = new PhotoBox(file,filePrints.get(file.getName()));
		                				
		                				tile.add(vBox);
		                				System.out.println(tile.getComponentCount());
		                				list.add(file.getName());
		                				
		                				
		                				vBox.addMouseListener(new MouseListener() {
											
											@Override
											public void mouseReleased(MouseEvent e) {
												// TODO Auto-generated method stub
												
											}
											
											@Override
											public void mousePressed(MouseEvent e) {
												// TODO Auto-generated method stub
												
											}
											
											@Override
											public void mouseExited(MouseEvent e) {
												// TODO Auto-generated method stub
												
											}
											
											@Override
											public void mouseEntered(MouseEvent e) {
												// TODO Auto-generated method stub
												
											}
											
											@Override
											public void mouseClicked(MouseEvent e) {
											if(e.getClickCount()==1)
											{
												try
												{
													for(int i=0;i<tile.getComponents().length;i++)
													{
														removeBorder((JPanel)(tile.getComponent(i)));
													}
													vBox.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black), getBorder()));
													currentImage.setText(file.getName());
													JLabel thumb = new JLabel(helper.imagePreview(file));
													imageBox.removeAll();
													imageBox.add(thumb);
												}
												catch (Exception ex) {
													ex.printStackTrace();
												}
												
											}
											else if(e.getClickCount()==2){
												try{
													   JFrame frame= new JFrame(file.getName());
										        	   JPanel panel = new JPanel();
										        	   JLabel fullImage = new JLabel(helper.createFullScreenImage(file));
										        	   panel.add(fullImage);
										        	   frame.getContentPane().add(panel);
										        	   frame.setVisible(true);
										        	   frame.show();
										        	   frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
												}
												catch (Exception ex) {
													ex.printStackTrace();
												}
												 }
												
											}
										});
                        			}
                        			}
                        			catch (Exception e) {
										// TODO: handle exception
									}
                        		}
                        	}
                        	}
                        }
                    });
                    try {
						Thread.sleep(5000);
					} catch(InterruptedException ex)
					{
						Thread.currentThread().interrupt();
						return;
					}catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
                	
                }
            });
		    
		   
		}catch(Exception e)
		{
			e.printStackTrace();
		}
        return root;
	}
	
	class MouseAdapterMod extends MouseAdapter {

		// usually better off with mousePressed rather than clicked
		   public void mousePressed(MouseEvent e) {
		       PhotoBox btnPanel = (PhotoBox)e.getSource();
		       btnPanel.setSelected();
		   }
		}
	
	private GridBagConstraints makeGbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.weightx = x;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = (x == 0) ? GridBagConstraints.LINE_START : GridBagConstraints.LINE_END;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        return gbc;
    }

}

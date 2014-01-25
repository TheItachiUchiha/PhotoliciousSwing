package com.kc.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
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




import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

import com.kc.utils.PhotoliciousUtils;

import com.kc.model.PhotoBox;
import com.kc.service.PrintImage;




public class Home extends JPanel {

	JLabel newFileName;
	JLabel timeStamp;
	JLabel noOfPrint;
	JLabel number;
	List<String> list = new ArrayList<String>();
	PrintImage printImage;
	File outputFolder;
	JLabel newFile;
	JPanel detailsBox;
	JPanel printOptionsBox;
	JPanel imageBox;
	Map<String, JLabel> filePrints = new HashMap<String, JLabel>();
	
	ExecutorService exec = null;
	
	SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
	
	public Home(final MainWindow stage, final ExecutorService exec) {
		
		this.exec = exec;
		number = new JLabel("0");
		outputFolder = new File(PhotoliciousUtils.readOutputFolder());
		newFile = new JLabel();
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		setLayout(new BorderLayout(0, 0));
		add(splitPane, BorderLayout.CENTER);
		splitPane.setLeftComponent(leftPane());
		splitPane.setRightComponent(viewGallery(stage));
		
		
	}
	
	public JPanel leftPane()
	{
		JPanel leftPane = new JPanel();
		
		leftPane.setLayout(new BoxLayout(leftPane, BoxLayout.Y_AXIS));
		
		detailsBox = new JPanel();
		leftPane.add(detailsBox);
		detailsBox.setLayout(new BoxLayout(detailsBox, BoxLayout.Y_AXIS));
		
		Component rigidArea = Box.createRigidArea(new Dimension(0, 30));
		detailsBox.add(rigidArea);
		
		JLabel lblNewLabel = new JLabel("No. of Photos in Output Folder");
		detailsBox.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(0, 10));
		detailsBox.add(rigidArea_1);
		
		number = new JLabel("0");
		detailsBox.add(number);
		detailsBox.add(Box.createRigidArea(new Dimension(0, 20)));
		
		JLabel lblNoOfPrints = new JLabel("No. Of Prints (Current Session)");
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
		
		newFileName = new JLabel("name");
		detailsBox.add(newFileName);
		detailsBox.add(Box.createRigidArea(new Dimension(0, 20)));
		
		JLabel lblNewLabel_1 = new JLabel("Photo Timestamp");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		detailsBox.add(lblNewLabel_1);
		
		Component rigidArea_4 = Box.createRigidArea(new Dimension(0, 10));
		detailsBox.add(rigidArea_4);
		
		timeStamp = new JLabel("stamp");
		detailsBox.add(timeStamp);
		detailsBox.add(Box.createRigidArea(new Dimension(0, 50)));
		
		printOptionsBox = new JPanel();
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
		
		imageBox = new JPanel();
		leftPane.add(imageBox);
		imageBox.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		return leftPane;
		
	}
		
	
	public JScrollPane viewGallery(final MainWindow stage)
	{
		JScrollPane root = null;
		final JPanel tile = new JPanel();
		
		try{
			SpringLayout layout = new SpringLayout();
			tile.setLayout(layout);
			root = new JScrollPane(tile, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			
			
		    
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
                        		for (File file : listOfFiles) {
                        			if(!list.contains(file.getName())){
                        				newFile.setText(file.getName());
                        				timeStamp.setText(sdf.format(file.lastModified()));
		                				System.out.println(file.getPath());
		                				final ImageIcon image = new ImageIcon(file.getPath());
		                				
		                				
		                				
		                				filePrints.put(file.getName(), new JLabel("0"));
		                				final PhotoBox vBox = new PhotoBox(image, file,filePrints.get(file.getName()));
		                				
		                				tile.add(vBox);
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
												JLabel thumb = new JLabel(image);
												imageBox.removeAll();
												imageBox.add(thumb);
												
											}
											else if(e.getClickCount()==2){
													 JFrame frame= new JFrame();
										        	   JPanel panel = new JPanel();
										        	   JLabel fullImage = new JLabel(image);
										        	   panel.add(fullImage);
										        	   frame.add(panel);
										        	   Toolkit tk = Toolkit.getDefaultToolkit(); 
										        	   int xSize = ((int) tk.getScreenSize().getWidth());  
										        	   int ySize = ((int) tk.getScreenSize().getHeight());  
										        	   frame.setSize(xSize,ySize);  
										        	   frame.show();
												 }
												
											}
										});
		                				/*vBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
		                						
											@Override
											public void handle(MouseEvent mouseEvent) {
												
												if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
										            
													
									            	
													if(mouseEvent.getClickCount() == 1 ){
														
														ImageView imageView = new ImageView();
										            	imageView.setImage(image);
										            	
														imageView.setFitHeight(imageViewBox.getMaxHeight() - 10);
														imageView.setFitWidth(imageViewBox.getMaxWidth() - 10);
														imageView.setPreserveRatio(true);
														imageView.setSmooth(true);
														imageView.setCache(true);
														imageViewBox.getChildren().clear();
														imageViewBox.getChildren().add(imageView);
														
														
														for(Node node : tile.getChildren())
														{
															if(node.getId().equals("imgShow"))
																node.setId("noCss");
														}
														vBox.setId("imgShow");
														//currentPrints.setText(filePrints.get(new File((((ImageView)imageViewBox.getChildren().get(0)).getImage().impl_getUrl()).substring(5)).getName()).getText()); 
														
													
													}
													
													else if(mouseEvent.getClickCount() == 2){

										            	BorderPane borderPane = new BorderPane();
										            	ImageView imageView = new ImageView();
										            	imageView.setImage(image);
										            	imageView.setFitHeight(stage.getHeight() - 10);
														imageView.setPreserveRatio(true);
														imageView.setSmooth(true);
														imageView.setCache(true);
										            	borderPane.setCenter(imageView);
										            	Stage newStage = new Stage();
										            	newStage.setWidth(stage.getWidth());
										            	newStage.setHeight(stage.getHeight());
										            	newStage.setTitle(new File((((ImageView)imageViewBox.getChildren().get(0)).getImage().impl_getUrl()).substring(5)).getName());
										            	newStage.setScene(new Scene(borderPane));
										                newStage.show();
										                
										            }
										            
										           
												}
											}
										});*/
		                				
		                				
		                				
		                				
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
        
		/*root.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);    // Horizontal scroll bar
		root.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);    // Vertical scroll bar
		//	root.setFitToHeight(true);
		root.setFitToWidth(true);
        root.setContent(tile);	*/
		
		
        return root;
	}
	
	class MouseAdapterMod extends MouseAdapter {

		   // usually better off with mousePressed rather than clicked
		   public void mousePressed(MouseEvent e) {
		       PhotoBox btnPanel = (PhotoBox)e.getSource();
		       btnPanel.setSelected();
		   }
		}

}

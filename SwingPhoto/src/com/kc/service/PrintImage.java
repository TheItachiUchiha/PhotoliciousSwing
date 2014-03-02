package com.kc.service;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.print.PrintService;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.SwingWorker;

import com.kc.model.PrintServiceVO;

public class PrintImage extends SwingWorker<Integer, String> {
	
	String file=null;
	String size=null;
	
	public PrintImage()
	{
		
	}
	
	public PrintImage(String file, String size)
	{
		this.file=file;
		this.size=size;
	}


	

	public PrintServiceVO[] printerList() {
		List<PrintServiceVO> observableList = new ArrayList<PrintServiceVO>();
		PrintService printService[] = PrinterJob.lookupPrintServices();
		for (PrintService printService2 : printService) {
			PrintServiceVO printServiceVO = new PrintServiceVO();
			printServiceVO.setPrintService(printService2);
			observableList.add(printServiceVO);
		}
		return (PrintServiceVO[]) observableList.toArray();
	}
	
	public static void printImage(String file, String size) {

	    try {
	        Image img = ImageIO.read(new File(file));
	        
	        print(img, size);
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}

	private static void print(final Image img, String size) {
	    PrinterJob printjob = PrinterJob.getPrinterJob();
	    printjob.setJobName("Print");

	    ImgPrinter printable = new ImgPrinter(img);

	    try {
	        System.out.println("Printing.");
	        printable.printPage(size);
	    } catch (Exception ex) {
	        System.out.println("NO PAGE FOUND." + ex);
	    }										
	}

	private static class ImgPrinter implements Printable {

	    Image img;
	    //private int currentPage = -1;

	    public ImgPrinter(Image img) {
	        this.img = img;
	    }

	    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) {
            if (pageIndex >= 1) {
                return Printable.NO_SUCH_PAGE;
            }

            Graphics2D g2d = (Graphics2D) graphics;
            // Be careful of clips...
//            g2d.setClip(0, 0, (int) pageFormat.getWidth(), (int) pageFormat.getHeight());
            g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());

            double width = pageFormat.getImageableWidth();
            double height = pageFormat.getImageableHeight();

            System.out.println("width = " + width);
            System.out.println("height = " + height);


            g2d.setColor(Color.BLACK);
            //g2d.draw(new Rectangle2D.Double(0, 0, width, height));
            g2d.drawImage(img, 0, 0, (int)width, (int)height,   null);

            return Printable.PAGE_EXISTS;
        }

	    public void printPage(String size)
	    {
	    	double width = 0.0;
            double height = 0.0;
            double margin = 0.0;
            PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
	    	try{
		    PrinterJob pj = PrinterJob.getPrinterJob();
	        if (pj.printDialog()) {
	            PageFormat pf = pj.defaultPage();
	            Paper paper = pf.getPaper();
	            if(size.equals("3r"))
	            {
	            	width = 5d * 72d;
		            height = 3.5d * 72d;
		            margin = 0d * 72d;
		            aset.add(MediaSizeName.JIS_B7);
	            }
	            else if(size.equals("4r"))
	            {
	            	width = 6d * 72d;
		            height = 4d * 72d;
		            margin = 0d * 72d;
		            aset.add(MediaSizeName.JAPANESE_POSTCARD);
	            }
	            else if(size.equals("5r"))
	            {
	            	width = 7d * 72d;
		            height = 5d * 72d;
		            margin = 0d * 72d;
		            aset.add(MediaSizeName.NA_5X7);
	            }
	            else if(size.equals("6r"))
	            {
	            	width = 8d * 72d;
		            height = 6d * 72d;
		            margin = 0d * 72d;
		            aset.add(MediaSizeName.JAPANESE_DOUBLE_POSTCARD);
	            }
	            else if(size.equals("8r"))
	            {
	            	width = 10d * 72d;
		            height = 8d * 72d;
		            margin = 0d * 72d;
		            aset.add(MediaSizeName.NA_8X10);
	            }
	            paper.setSize(width, height);
	            paper.setImageableArea(
	                    margin,
	                    margin,
	                    width - (margin * 2),
	                    height - (margin * 2));
	            System.out.println("Before- " + dump(paper));
	            pf.setOrientation(PageFormat.LANDSCAPE);
	            pf.setPaper(paper);
	            System.out.println("After- " + dump(paper));
	            System.out.println("After- " + dump(pf));
	            dump(pf);
	            PageFormat validatePage = pj.validatePage(pf);
	            System.out.println("Valid- " + dump(validatePage));
	
	            Book pBook = new Book();
	            pBook.append(this, pf);
	            pj.setPageable(pBook);
	            
	           
	            aset.add(OrientationRequested.LANDSCAPE);
	            
	            try {
	                pj.setPrintable(this);
	                pj.print(aset);
	            } catch (PrinterException ex) {
	                ex.printStackTrace();
	            }
	        }
	
	    } catch (Exception exp) {
	        exp.printStackTrace();
	    }
    }

	protected static String dump(Paper paper) {
	    StringBuilder sb = new StringBuilder(64);
	    sb.append(paper.getWidth()).append("x").append(paper.getHeight())
	            .append("/").append(paper.getImageableX()).append("x").
	            append(paper.getImageableY()).append(" - ").append(paper
	            .getImageableWidth()).append("x").append(paper.getImageableHeight());
	    return sb.toString();
	}

	protected static String dump(PageFormat pf) {
	    Paper paper = pf.getPaper();
	    return dump(paper);
	}

	}
	
	public static void main(String args[])
	{
		PrintImage.printImage("C:\\Users\\Abhinay_Kryptcoder\\Desktop\\Untitled.jpg", "4r");
	}

	
	@Override
	protected Integer doInBackground() throws Exception {
		// TODO Auto-generated method stub
		printImage(this.file, this.size);
		return null;
	}

}

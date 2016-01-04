package crypto.ui;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

public class Picture {
	
	private File file;
	private ArrayList<Point> firsts = new ArrayList<Point>();
	private ArrayList<Point> lasts = new ArrayList<Point>();
	private Coded coded;
	private BufferedImage uncoded;
	private String ext;
	
	public Picture()
	{
		this.file = null;
		this.coded = null;
	}
	
	public Picture(File file) throws IOException {
		this.file = file;
		this.coded = null;
		this.uncoded = null;
	}

	 /**
	  * getFile is a getter which gets the file.
	  * @return the file.
	  */
	public File getFile()
	{
		return this.file;
	}

	 /**
	  * setFile is a setter which sets the file.
	  * @param file - the file to be set.
	  */
	public void setFile(File file)
	{
		this.file = file;
	}
	
	 /**
	  * addFirst is a methode which add on the first point list a point. The first points are the point on top left of our selected rectangle..
	  * @param p - the point added to the list.
	  */
	public void addFirst(Point p)
	{
		this.firsts.add(p);
	}
	
	 /**
	  * addLast is a methode which add on the last point list a point. The last points are the point on bottom right of our selected rectangle.
	  * @param p - the point added to the list.
	  */
	public void addLast(Point p)
	{
		this.lasts.add(p);
	}
	
	 /**
	  * getFirsts is a getter which get an array list of firsts' points.
	  * @return all of the point on the array list.
	  */
	public ArrayList<Point> getFirsts()
	{
		return this.firsts;
	}
	
	 /**
	  * getLasts is a getter which get an array list of lasts' points.
	  * @return all of the point on the array list.
	  */
	public ArrayList<Point> getLasts()
	{
		return this.lasts;
	}
	
	 /**
	  * setCoded is a setter which sets the coded object. This object is use to keep information about how the picture is encrypted.
	  * @param coded - the object of Coded.
	  */
	public void setCoded(Coded coded)
	{
		this.coded = coded;
	}
	
	 /**
	  * getCoded is a getter which gets the coded object
	  * @return the coded object which got information about how the picture is encrypted.
	  */
	public Coded getCoded() {
		return this.coded;
	}

	/**
	  * getUncoded is a getter which gets an image.
	  * @return the image.
	  */
	public BufferedImage getUncoded() {
		return uncoded;
	}

	 /**
	  * setUncoded is a setter which sets the image.
	  * @param uncoded the image.
	  */
	public void setUncoded(BufferedImage uncoded) {
		this.uncoded = uncoded;
	}
	
	/**
	  * getExt is a getter which gets the extension of file
	  * @return the extension.
	  */
	public String getExt() {
		return ext;
	}

	 /**
	  * setUncoded is a setter which sets extension of file
	  * @param the extension.
	  */
	public void setExt(String ext) {
		this.ext = ext;
	}
	
}

package crypto.ui;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Coded implements Serializable{

	private byte[] crypted;
	private byte[] image;
	private transient String decodedKey;
	private String ext;
	
	private ArrayList<Point> firsts;
	private ArrayList<Point> lasts;
	
	public Coded(byte[] crypted, byte[] image, String decodedKey, ArrayList<Point> firsts, ArrayList<Point> lasts, String ext) {
		/**This is the constructor of Coded, it created a coded file to save with encrypted data and the image; 
		 * It takes the decodedKey but it does not Serialize it
		 * firsts is the list of top left point of the encrypted areas
		 * lasts is the list of bottom right point of the encrypted areas
		 */

		this.crypted = crypted;
		this.image = image;
		this.decodedKey = decodedKey;
		this.setExt(ext);
		this.firsts = firsts;
		this.lasts = lasts;
	}

	/**
	  * printFie is a method which display a file on the frame.
	  * @param output - file that we want to display.
	  * @throws IOException
	  */
	public void printFile(File output) throws IOException{
		ByteArrayInputStream bais = new ByteArrayInputStream(image); 
		BufferedImage buffer = ImageIO.read(bais);
		ImageIO.write(buffer, "JPG", output);
	}
	
	/**
	  * getBuffImage is a getter which gets the image
	  * @return the image
	  * @throws IOException
	  */
	public BufferedImage getBuffImage() throws IOException{
		ByteArrayInputStream bais = new ByteArrayInputStream(image); 
		return ImageIO.read(bais);
	}

	 /**
	  * getKey is a getter which gets the key
	  * @return the decoded key
	  */
	public String getKey() {
		return this.decodedKey;
	}

	 /**
	  * setKey is a setter which sets the key
	  * @param decodedKey - the decoded key to be set
	  */
	public void setKey(String decodedKey) {
		this.decodedKey = decodedKey;
	}
	
	/**
	  * getFirsts is a getter which gets the array list of firsts points.
	  * @return an array list of firsts' points.
	  */
	public ArrayList<Point> getFirsts(){
		return this.firsts;
	}
	
	 /**
	  * getLasts is a getter which gets the array list of lasts points.
	  * @return an array list of lasts' points.
	  */
	public ArrayList<Point> getLasts(){
		return this.lasts;
	}

	/**
	  * getCrypted is a getter which gets the byte's table of encrypted image.
	  * @return the byte's table
	  */
	public byte[] getCrypted() {
		return this.crypted;
	}

	 /**
	  *getImage is a getter which gets the image in a byte's table form.
	  * @return the byte's table of this image.
	  */
	public byte[] getImage() {
		return this.image;
	}

	 /**
	  * getEx is a getter which gets the extension of file.
	  * @return the extension of file.
	  */
	public String getExt() {
		return ext;
	}

	 /**
	  * setEx is a setter which sets the extension of file.
	  * @param ext - the extension of file to be set
	  */
	public void setExt(String ext) {
		this.ext = ext;
	}
}
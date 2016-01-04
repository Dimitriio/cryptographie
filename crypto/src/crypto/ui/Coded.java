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

	public void printFile(File output) throws IOException{
		ByteArrayInputStream bais = new ByteArrayInputStream(image); 
		BufferedImage buffer = ImageIO.read(bais);
		ImageIO.write(buffer, "JPG", output);
	}
	
	public BufferedImage getBuffImage() throws IOException{
		ByteArrayInputStream bais = new ByteArrayInputStream(image); 
		return ImageIO.read(bais);
	}

	public String getKey() {
		return this.decodedKey;
	}

	public void setKey(String decodedKey) {
		this.decodedKey = decodedKey;
	}
	
	public ArrayList<Point> getFirsts(){
		return this.firsts;
	}
	
	public ArrayList<Point> getLasts(){
		return this.lasts;
	}


	public byte[] getCrypted() {
		return this.crypted;
	}


	public byte[] getImage() {
		return this.image;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}
}
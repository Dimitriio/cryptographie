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
	
	private ArrayList<Point> firsts;
	private ArrayList<Point> lasts;
	
	public Coded(byte[] crypted, byte[] image, String decodedKey, ArrayList<Point> firsts, ArrayList<Point> lasts) {
		/**This is the constructor of Coded, it created a coded file to save with encrypted data and the image; 
		 * It takes the decodedKey but it does not Serialize it
		 * x1 and y1 are the coordinates of the top left point of the encrypted area
		 * x2 and y2 are the coordinates of the bottom right point of the encrypted area
		 */

		this.crypted = crypted;
		this.image = image;
		this.decodedKey = decodedKey;
		
		this.firsts = firsts;
		this.lasts = lasts;
	}

	public void printFile(File output) throws IOException{
		ByteArrayInputStream bais = new ByteArrayInputStream(image); 
		BufferedImage buffer = ImageIO.read(bais);
		ImageIO.write(buffer, "JPG", output);
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
}
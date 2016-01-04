package crypto.ui;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class Coded implements Serializable{
	private byte[] crypted;
	private byte[] image;
	private transient String decodedKey;
	
	private int x1, x2, y1, y2;
	
	public Coded(byte[] crypted, byte[] image, String decodedKey, int x1, int y1, int x2, int y2) {
		/**This is the constructor of Coded, it created a coded file to save with encrypted data and the image; 
		 * It takes the decodedKey but it does not Serialize it
		 * x1 and y1 are the coordinates of the top left point of the encrypted area
		 * x2 and y2 are the coordinates of the bottom right point of the encrypted area
		 */

		this.crypted = crypted;
		this.image = image;
		this.decodedKey = decodedKey;
		
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
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
	
	public void printCoord(){
		System.out.println("Point 1: x=" + this.x1 + " ; y=" + this.y1);
		System.out.println("Point 2: x=" + this.x2 + " ; y=" + this.y2);
	}
	
	public int getx1(){
		return this.x1;
	}
	
	public int gety1(){
		return this.y1;
	}
	
	public int getx2(){
		return this.x2;
	}
	
	public int gety2(){
		return this.y2;
	}


	public byte[] getCrypted() {
		return this.crypted;
	}


	public byte[] getImage() {
		return this.image;
	}
}

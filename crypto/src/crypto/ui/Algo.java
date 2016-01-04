package crypto.ui;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;

public class Algo {
	
	static final String ALGORITHM = "AES";
	static final int WORD_SIZE = 16;
	
	public static Coded encryption(File input, ArrayList<Point> firsts, ArrayList<Point> lasts) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		/**File input is the not encrypted image, File output is the encrypted result of input
		 * int x1, int y1 are the coordinates of the top left point of the area to encrypt
		 * int x2, int y2 are the coordinates of the bottom right point of the area to encrypt
		 * 
		 * This method is reading every pixel of the image and encrypting it using an AES algorithm.
		 * It returns a Coded object containing the new encrypted image, the encrypted area and the decodedKey (transient).
		 */
		
		// Reading the input image ; Can create a IOExcetpion
		BufferedImage image = ImageIO.read(input);
		
		// Creating the Key for Cipher ; Can create a NoSuchAlgorithmException
		KeyGenerator factory = KeyGenerator.getInstance(ALGORITHM);
		factory.init(128);
		SecretKey key = factory.generateKey();
		
		// Creating the Cipher instance ; Can create a NoSuchPaddingException
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		// Initialisation of the Cipher instance ; Can create InvalidKeyException
		cipher.init(Cipher.ENCRYPT_MODE, key);
		
		// Creating a new Random object to hide the encrypted pixels.
		Random r = new Random();

		int size = 0;
		Iterator<Point> f = firsts.iterator();
		Iterator<Point> l = lasts.iterator();
		for(;f.hasNext();){
			Point p1 = f.next();
			Point p2 = l.next();
			
			// Calculating the size of the total encrypted data size in Bytes.
			size += WORD_SIZE*((p2.x-p1.x)*(p2.y-p1.y));
		}	
		// Creating a new ByteBuffer to take every Byte the algorithm is gonna give us;
		ByteBuffer buffer = ByteBuffer.allocate(size);
					
		f = firsts.iterator();
		l = lasts.iterator();
		for(;f.hasNext();){
			Point p1 = f.next();
			Point p2 = l.next();
			
			System.out.println("Crypting zone between point" + p1 + " and " + p2);
			// Reading the area to encrypt and encrypting every pixel of it.
			for(int x = p1.x; x < p2.x; x++){
				for(int y = p1.y; y < p2.y; y++){
					// Getting the pixel
					int pixel = image.getRGB(x, y);
					
					// Creating the byte[] version of pixel
					byte[] binary = ByteBuffer.allocate(4).putInt(pixel).array();
					
					// Putting it in buffer ; Can create a IllegalBlockSizeException
					buffer.put(cipher.doFinal(binary));
					
					// Randomize the original image pixel
					image.setRGB(x, y, r.nextInt());
				}
			}
			System.out.println("Done crypting this area");
		}
		
		// Creating the coded element with byte[] of encrypted and Image
		return new Coded(buffer.array(), Algo.imageToByteArray(image), Algo.secretKeyToString(key), firsts, lasts);
	}
	
	public static void uncryption(Coded c, File output) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException{
		/**Coded contains byte[] buffer of crypted data, the decodedKey and the bufferedImage.
		 * This method is reading very crypted word, uncrypt it and write the resulting it into the file.
		 */
		
		// Creating the Cipher instance ; Can create a NoSuchPaddingException
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		// Initialisation of the Cipher instance ; Can create InvalidKeyException
		cipher.init(Cipher.DECRYPT_MODE, Algo.stringToSecretKey(c.getKey()));
		
		// We get the byte[] image and we make a BufferedImage of it
		ByteArrayInputStream bais = new ByteArrayInputStream(c.getImage());
		BufferedImage img = ImageIO.read(bais);
		
		Iterator<Point> f = c.getFirsts().iterator();
		Iterator<Point> l = c.getLasts().iterator();
		for(;f.hasNext();){
			Point p1 = f.next();
			Point p2 = l.next();
		
			int parsor = 0;
			for(int x = p1.x; x < p2.x; x++){
				for(int y = p1.y; y < p2.y; y++){
					// Creating a byte[] buffer
					byte[] buffer = new byte[WORD_SIZE];
					for(int j = 0; j < WORD_SIZE; j++, parsor++)
						buffer[j] = c.getCrypted()[parsor];
					
					// Getting the crypted pixel
					// Uncrypting the ByteBuffer ; Can create an exception
					byte[] result = cipher.doFinal(buffer);
					
					//  Creating the int version of result
					int pixel = (result[0]<<24)&0xff000000|
						        (result[1]<<16)&0x00ff0000|
						        (result[2]<< 8)&0x0000ff00|
						        (result[3]<< 0)&0x000000ff;
					
					// We change the pixel
					img.setRGB(x, y, pixel);
				}
			}
		}
		// Finally we make the .jpg out of it.
		ImageIO.write(img, "JPG", output);
	}
	
	public static byte[] imageToByteArray(BufferedImage image) throws IOException
	{
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    ImageIO.write(image, "jpg", baos);
	    baos.close();
	    return baos.toByteArray();
	}
	
	public static String secretKeyToString(SecretKey decodedKey){
		return Base64.getEncoder().encodeToString(decodedKey.getEncoded());
	}
	
	public static SecretKey stringToSecretKey(String encodedKey){
		byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
		return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
	}
}
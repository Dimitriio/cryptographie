package crypto.ui;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import crypto.ui.Coded;
import ui.Controller;
import ui.View;

public class CryptoController extends Controller{
	
	public int xFirst, yFirst;
	public int width, height;
	public int xLock, yLock;
	
	public CryptoController(Object newModel) {
		super(newModel);
	}
	
	public void mousePressed(MouseEvent e)
	{
		this.xFirst = e.getX();
		this.yFirst = e.getY();
		this.width = 0;
		this.height = 0;
		
		this.mousePressed=true;
		
		this.getView().repaint();
	}

	public void mouseReleased(MouseEvent e)
	{
		Picture p = (Picture) this.getModel();
		this.mousePressed=false;
		
		int x = xLock < xFirst ? xLock : xFirst;
		int y = yLock < yFirst ? yLock : yFirst;
		
		Rectangle r1 = new Rectangle(new Point(x, y), new Dimension(width, height));
		
		boolean doContains = false;
		Iterator<Point> f = p.getFirsts().iterator();
		Iterator<Point> l = p.getLasts().iterator();
		for(;f.hasNext() && l.hasNext();){
			Point p1 = f.next();
			Point p2 = l.next();
		
			Rectangle r2 = new Rectangle(p1, new Dimension(p2.x - p1.x, p2.y - p1.y));
			
			if (r1.intersects(r2)){
				doContains = true;
				break;
			}
		}
		
		if(!doContains){
			p.addFirst(new Point(x, y));
			p.addLast(new Point(x+width, y+height));
		}
		
		width = 0;
		height = 0;
		this.getView().repaint();
	}

	public void mouseClicked(MouseEvent e)
	{
	}
		
	public void mouseEntered(MouseEvent e)
	{
	}

	public void mouseExited(MouseEvent e)
	{
	}
	
	public void mouseMoved(MouseEvent evt)
	{
		this.getView().repaint();
	}
	
	public void mouseDragged(MouseEvent evt)
	{
		int xLast = evt.getX();
		int yLast = evt.getY();
		
		height = Math.abs(yLast - yFirst);
		width = Math.abs(xLast - xFirst);
		
		if (xFirst > xLast)
			xLock = xFirst - width;
		else
			xLock = xFirst;
		if (yFirst > yLast)
			yLock = yFirst - height;
		else
			yLock = yFirst;
		
		this.getView().repaint();
	}
	
	public void keyTyped(KeyEvent evt)
	{
	}
	
	public void keyPressed(KeyEvent evt)
	{
	}

	public void keyReleased(KeyEvent evt)
	{
	}
	
	/**
	 * 
	 * OpenFileAction is a subclass that define the action of the button Open File. 
	 * It permits to open a JPG image or a CRY file which is the partial encrypted version of a JPG, to load this file in the model and to display in the UI.
	 *
	 */
	public class OpenFileAction extends AbstractAction
	{
		CryptoView cview;
		
		public OpenFileAction(View view)
		{
			super("Open File");
			putValue(SHORT_DESCRIPTION, "Open file");
			//putValue(MNEMONIC_KEY, mnemonic);
			this.cview=(CryptoView) view;
		}

		public void actionPerformed(ActionEvent e)
		{
			try {
				// code pour ouvrir un fichier et le mettre dans le modele
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & CRY Images", "jpg", "cry");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("You chose to open this file: " +
							chooser.getSelectedFile().getName());
				}
				setModel(new Picture(chooser.getSelectedFile()));
				
				StringTokenizer tokenizer = new StringTokenizer(((Picture) getModel()).getFile().getAbsolutePath(), ".");
				String part = null;
				if(tokenizer.hasMoreTokens()) { 
				    part = tokenizer.nextToken();
				}
				part = tokenizer.nextToken();
				((Picture) getModel()).setExt(part);
				if (!part.equals("cry")){
					((Picture) getModel()).setUncoded(ImageIO.read(chooser.getSelectedFile()));
				}
				else{					
					FileInputStream inputStream = new FileInputStream(chooser.getSelectedFile().getAbsolutePath());
					ObjectInputStream in = new ObjectInputStream(inputStream);
					Coded c = (Coded) in.readObject();
					in.close();
					inputStream.close();
					
					((Picture) getModel()).setCoded(c);
					((Picture) getModel()).setUncoded(c.getBuffImage());
				}
			} catch (Exception ex) {
			}
			
			((View) this.cview).setModel(this.cview.getController().getModel());
	        this.cview.repaint();
		}
	}
	
	/**
	 * 
	 * EncryptAction is a subclass that define the action of the button that encrypt/decrypt the opened File. 
	 * It permits to encrypt the opened JPG image or decrypt the opened CRY file .
	 * IT IS ENCRYPTED IN THE SOFTWARE, IT IS NOT SAVE YET
	 * 
	 */
	public class EncryptAction extends AbstractAction
	{
		CryptoView cview;
		
		public EncryptAction(View view)
		{
			super("Encrypt/Uncrypt");
			putValue(SHORT_DESCRIPTION, "Encrypt selected/Uncrypt selected");
			//putValue(MNEMONIC_KEY, mnemonic);
			this.cview=(CryptoView) view;
		}

		public void actionPerformed(ActionEvent e)
		{
			Picture picture = (Picture) getModel();
			Pattern p = Pattern.compile("(.*?).cry");
			Matcher m = p.matcher(picture.getFile().getName());
			try {
				if(!m.find()){
					picture.setCoded(Algo.encryption(Algo.copyImage(picture.getUncoded()),picture.getFirsts(), picture.getLasts()));
					picture.getCoded().setExt(picture.getExt());
					this.cview.getField().setText(picture.getCoded().getKey());	
					((Picture) getModel()).setUncoded(((Picture) getModel()).getCoded().getBuffImage());
				}else{
					picture.getCoded().setKey(this.cview.getField().getText());
					picture.setUncoded(Algo.uncryption(picture.getCoded()));
					picture.setExt(picture.getCoded().getExt());
				}
			} catch (Exception exc) {
			}
			
			((View) this.cview).setModel(this.cview.getController().getModel());
			this.cview.repaint();
		}
	}

	/**
	 * 
	 * SaveAction is a subclass that define the action of the button that save the encrypted/decrypted File. 
	 * It permits to save the encrypted JPG image or save the decrypted CRY file at the same location that the original one.
	 * 
	 */
	public class SaveAction extends AbstractAction
	{
		CryptoView cview;
		
		public SaveAction(View view)
		{
			super("Save");
			putValue(SHORT_DESCRIPTION, "Save encrypted file");
			//putValue(MNEMONIC_KEY, mnemonic);
			this.cview=(CryptoView) view;
		}

		public void actionPerformed(ActionEvent e)
		{
			StringTokenizer tokenizer = new StringTokenizer(((Picture) getModel()).getFile().getAbsolutePath(), ".");
			String part = null;
			if(tokenizer.hasMoreTokens()) { 
			    part = tokenizer.nextToken();
			}
			String ext = tokenizer.nextToken();
			try {
				if(!ext.equals("cry")){
					FileOutputStream outputStream = new FileOutputStream(part+".cry");
					ObjectOutputStream out = new ObjectOutputStream(outputStream);
					out.writeObject(((Picture) getModel()).getCoded());
					out.close();
					outputStream.close();
					
					System.out.println("Saved");
				}
				else {
					File output = new File(part+"_uncrypted."+((Picture) getModel()).getExt());
					ImageIO.write(((Picture) getModel()).getUncoded(), ((Picture) getModel()).getExt(), output);
					System.out.println("Saved");
				}
			} catch (IOException e1) {
			}
			
			this.cview.repaint();
		}
	}
}

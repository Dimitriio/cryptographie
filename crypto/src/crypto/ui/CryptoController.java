package crypto.ui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	
	public int x, y;
	
	public CryptoController(Object newModel) {
		super(newModel);
	}
	
	public void mousePressed(MouseEvent e)
	{
		this.x = e.getX();
		this.y = e.getY();
		
		this.mousePressed=true;
		if(e.getButton() == MouseEvent.BUTTON1)
		{
			((Picture) this.getModel()).addFirst(e.getPoint());
			this.getView().setModel(this.getModel());
		}
		else if(e.getButton() == MouseEvent.BUTTON3)
		{
			// � faire pour avoir une action sur le click droit
		}
		this.getView().repaint();
	}

	public void mouseReleased(MouseEvent e)
	{
		this.mousePressed=false;
		if(e.getButton() == MouseEvent.BUTTON1)
		{
			((Picture) this.getModel()).addLast(e.getPoint());
			this.getView().setModel(this.getModel());
		}
		else if(e.getButton() == MouseEvent.BUTTON3)
		{
			// � faire pour avoir une action sur le click droit
		}
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
		this.x = evt.getX();
		this.y = evt.getY();
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
					System.out.println(chooser.getSelectedFile().getAbsolutePath());
					
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
				}
			} catch (IOException e1) {
			}
			
			this.cview.repaint();
		}
	}
}

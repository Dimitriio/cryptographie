package crypto.ui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import ui.Controller;
import ui.View;

public class CryptoController extends Controller{
	
	public CryptoController(Object newModel) {
		super(newModel);
	}
	
	public void mousePressed(MouseEvent e)
	{
		this.mousePressed=true;
		if(e.getButton() == MouseEvent.BUTTON1)
		{
			((Picture) this.getModel()).addFirst(e.getPoint());
			this.getView().setModel(this.getModel());
		}
		else if(e.getButton() == MouseEvent.BUTTON3)
		{
			// à faire pour avoir une action sur le click droit
		}
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
			// à faire pour avoir une action sur le click droit
		}
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
	}
	
	public void mouseDragged(MouseEvent evt)
	{
		if(mousePressed==true)
		{
			
			super.getView().setModel(this.getModel());
			super.getView().repaint();
		}
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
			((View) this.cview).setModel(this.cview.getController().getModel());
	        this.cview.repaint();
		}
	}
	
	public class EncryptAction extends AbstractAction
	{
		CryptoView cview;
		
		public EncryptAction(View view)
		{
			super("Encrypt");
			putValue(SHORT_DESCRIPTION, "Encrypt selected");
			//putValue(MNEMONIC_KEY, mnemonic);
			this.cview=(CryptoView) view;
		}

		public void actionPerformed(ActionEvent e)
		{
			Picture picture = (Picture) getModel();
			try {
				((Picture)getModel()).setCoded(Algo.encryption(picture.getFile(),picture.getFirsts(), picture.getLasts()));
			} catch (Exception exc) {
			}
			((View) this.cview).setModel(this.cview.getController().getModel());
			this.cview.repaint();
		}
	}

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
			String name = ((Picture) getModel()).getFile().getName();
			System.out.println(name);
			StringTokenizer tokenizer = new StringTokenizer(((Picture) getModel()).getFile().getAbsolutePath(), ".");
			String part = null;
			if(tokenizer.hasMoreTokens()) { 
			    part = tokenizer.nextToken();
			}
			try {
				((Picture) getModel()).getCoded().printFile(new File(part+".cry"));
			} catch (IOException e1) {
			}
		}
	}
}

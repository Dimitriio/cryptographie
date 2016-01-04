package crypto.ui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.AbstractAction;

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
			this.lastClick= e.getPoint();
		}
		else if(e.getButton() == MouseEvent.BUTTON3)
		{
			// à faire pour avoir une action sur le click droit
		}
	}

	public void mouseReleased(MouseEvent e)
	{
		this.mousePressed=false;
		/*if(e.getButton() == MouseEvent.BUTTON1)
		{
		}
		else if(e.getButton() == MouseEvent.BUTTON3)
		{
			// à faire pour avoir une action sur le click droit
		}*/
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
			// this.firstClick est la premiere coordonnée
			// this.lastClick est la deuxieme coordonnee du rectangle de selection
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
			// code permettant de crypter l'image et le mettre dans le modele ?
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
			// code pour sauver le modele encrypter
	        ((View) this.cview).setModel(this.cview.getController().getModel());
	        this.cview.repaint();
		}
	}
}

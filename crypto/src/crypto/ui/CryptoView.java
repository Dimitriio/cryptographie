package crypto.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JTextField;

import ui.View;

public class CryptoView extends View{
	private JTextField f;
	
	public CryptoView(Object model) {
		super(model);
		this.defaultController(model);
	}
	
	 /**
	  * paintComponent is a method that displays every object used on the model which need a representation.
	  */
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(((Picture) this.getModel()).getUncoded(), 0, 0, null);
	}
	
	 /**
	  * defaultController is a method used to instantiate a new controller with default attributes.
	  */
	public CryptoController defaultController(Object model)
	{
		return new CryptoController(model);
	}
	
	 /**
	  * getField is used to get the Field of JTextField
	  */
	public JTextField getField() {
		return f;
	}

	 /**
	  * setField is used to set the Field of JTextField
	  */
	public void setField(JTextField f) {
		this.f = f;
	}
	
}

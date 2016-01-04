package crypto.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import ui.View;

public class CryptoView extends View{

	public CryptoView(Object model) {
		super(model);
		this.defaultController(model);
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(((Picture) this.getModel()).getUncoded(), 0, 0, null);
	}
	
	public CryptoController defaultController(Object model)
	{
		return new CryptoController(model);
	}
}

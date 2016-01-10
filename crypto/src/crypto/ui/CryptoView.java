package crypto.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Iterator;

import javax.swing.JTextField;

import ui.View;

public class CryptoView extends View{
	private JTextField f;
	
	 /**
	  * defaultController is a method used to instantiate a new controller with default attributes.
	  */
	public CryptoView(Object model) {
		super(model);
		this.defaultController(model);
	}
	
	 /**
	  * paintComponent is a method that displays every object used on the model which need a representation.
	  */
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		Picture p = (Picture) this.getModel();
		CryptoController c = (CryptoController) this.getController();
		g.drawImage(p.getUncoded(), 0, 0, null);
		
		g.setColor(Color.RED);
		if ((p.getCoded() == null || p.getUncoded() == null) && !(p.getCoded() == null && p.getUncoded() == null)){
			Iterator<Point> f = p.getFirsts().iterator();
			Iterator<Point> l = p.getLasts().iterator();
			for(;f.hasNext() && l.hasNext();){
				Point p1 = f.next();
				Point p2 = l.next();
				
				g.drawRect(p1.x, p1.y, p2.x-p1.x, p2.y-p1.y);	
			}
			
			g.drawRect(c.xLock, c.yLock, c.width, c.height);
		}
		else {
			
		}
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

package crypto.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;

import ui.View;

public class CryptoView extends View{

	public CryptoView(Object model) {
		super(model);
		this.defaultController(model);
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		Picture model = ((Picture) this.getModel());
		/*ShapeDraftman sv = new ShapeDraftman();
		sv.setGraphics((Graphics2D) g);
		if(model==null) return;
		model.accept(sv);*/
	}
	
	public CryptoController defaultController(Object model)
	{
		return new CryptoController(model);
	}
}

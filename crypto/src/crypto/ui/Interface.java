package crypto.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Interface extends JFrame
{
	Picture model;
	CryptoView cview;
	
	public Interface()
	{	
		super("Crypto");
		this.addWindowListener(new java.awt.event.WindowAdapter()
		{
			public void windowClosing(java.awt.event.WindowEvent evt)
			{
				System.exit(0);
			}
		});
		
		this.buildModel();
		
		this.cview = new CryptoView(this.model);
		this.cview.setPreferredSize(new Dimension(300,300));
		/*Menu menu = new Menu(model, cview);
		menu.createMenu();
		this.setJMenuBar(menu.getMenuBar());*/
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(1,3,5,5));
		buttons.add(new JButton(((CryptoController)this.cview.getController()).new OpenFileAction(cview)));
		buttons.add(new JButton(((CryptoController)this.cview.getController()).new EncryptAction(cview)));
		buttons.add(new JButton(((CryptoController)this.cview.getController()).new SaveAction(cview)));
		
		this.getContentPane().add(buttons, BorderLayout.SOUTH);
		this.getContentPane().add(this.cview, BorderLayout.CENTER);
	}

	
	private void buildModel()
	{
		this.model = new Picture();
		// ici on peut ajouter un modele au lancement 
	}
	
	public static void main(String[] args)
	{
		Interface self = new Interface();
		self.pack();
		self.setVisible(true);
	}
}

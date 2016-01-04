package crypto.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import crypto.ui.CryptoController.GetKey;

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
		JTextField textField = new JTextField();
		textField.setColumns(10); //On lui donne un nombre de colonnes à afficher
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(1,5,5,5));
		buttons.add(new JButton(((CryptoController)this.cview.getController()).new OpenFileAction(cview)));
		buttons.add(new JButton(((CryptoController)this.cview.getController()).new EncryptAction(cview)));
		buttons.add(new JButton(((CryptoController)this.cview.getController()).new SaveAction(cview))); 
		buttons.add(new JButton(((CryptoController)this.cview.getController()).new GetKey(cview))); 
		buttons.add(textField);
		this.cview.setField(textField);


		this.getContentPane().add(buttons, BorderLayout.SOUTH);
		this.getContentPane().add(this.cview, BorderLayout.CENTER);
	}


	private void buildModel()
	{
		this.model = new Picture();
	}

	public static void main(String[] args)
	{
		Interface self = new Interface();
		self.pack();
		self.setVisible(true);
	}
}

package fr.utt.lo02.uno.vue;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.*;

import javax.swing.*;
import javax.swing.text.rtf.RTFEditorKit;

import fr.utt.lo02.uno.controleur.Controleur;

public class Regles extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton retour;
	private JFrame frame;
	private Font font = new Font("Courier", Font.BOLD, 15);
	
	public Regles(){
		super();
		this.frame = new JFrame();
		this.frame.setTitle("Regles du jeu");
		this.frame.setSize(1000, 800);
		this.frame.setLocationRelativeTo(null);               
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    this.setLayout(new BorderLayout());
	    
	    this.retour = new JButton("Retour au menu principal");
	    this.retour.setActionCommand("retour");
	    this.retour.setFont(font);
	    
	    RTFEditorKit rtf = new RTFEditorKit();  
	    JTextPane regles = new JTextPane();  
	    regles.setEditorKit(rtf);  
	    FileInputStream fichier = null;
		try {
			fichier = new FileInputStream("UNO_Regles.rtf");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}  
	    try {
			rtf.read(fichier, regles.getDocument(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    JScrollPane sRegles = new JScrollPane(regles);
	    this.add(retour, BorderLayout.SOUTH);
	    this.add(sRegles);
	    this.frame.setContentPane(this);
	}
	
	public void setListener(Controleur controleur) {
		retour.addActionListener(controleur);
	}
	
	public JFrame getFrame() {
		return frame;
	}
}

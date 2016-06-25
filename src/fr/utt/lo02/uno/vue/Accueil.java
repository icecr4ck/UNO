package fr.utt.lo02.uno.vue;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;

import fr.utt.lo02.uno.controleur.Controleur;
import fr.utt.lo02.uno.vue.panneaux.PanelAccueil;

public class Accueil extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PanelAccueil pa;
	private JButton nouvellePartie;
	private JButton regles;
	private JButton quitter;
	
	public Accueil(){
		this.setTitle("Jeu de UNO");
		this.setSize(1000, 800);
	    this.setLocationRelativeTo(null);               
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    this.pa = new PanelAccueil();
	    
	    Font font = new Font("Courier", Font.BOLD, 15);
	    
		this.nouvellePartie = new JButton("Nouvelle partie");
		this.nouvellePartie.setFont(font);
		this.nouvellePartie.setActionCommand("nouvellepartie");
		
		this.regles = new JButton("Regles du jeu");
		this.regles.setFont(font);
		this.regles.setActionCommand("reglesdujeu");
		
		this.quitter = new JButton("Quitter");
		this.quitter.setFont(font);
		this.quitter.setActionCommand("quitterpartie");
		
	    this.pa.setLayout(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.insets = new Insets(20,20,20,20);
	    gbc.ipadx = 75;
	    gbc.ipady = 10;
	    
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    this.pa.add(nouvellePartie, gbc);
	    
	    gbc.gridy = 2;
	    this.pa.add(regles, gbc);
	    
	    gbc.gridy = 3;
	    this.pa.add(quitter, gbc);
	    
	    this.setContentPane(pa);
	    this.setVisible(true);
	}

	public void setListener(Controleur controleur) {
		nouvellePartie.addActionListener(controleur);
		regles.addActionListener(controleur);
		quitter.addActionListener(controleur);
	}
}

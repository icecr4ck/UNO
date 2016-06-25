package fr.utt.lo02.uno.vue;

import java.awt.*;

import javax.swing.*;

import fr.utt.lo02.uno.controleur.Controleur;
import fr.utt.lo02.uno.vue.panneaux.PanelAccueil;

public class Parametres extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PanelAccueil pp;
	private JButton validation;
	private JRadioButton difficulte1;
	private JRadioButton difficulte2;
	private JRadioButton difficulte3;
	private ButtonGroup difficulteGroup;
	private JComboBox nbrReels;
	private JComboBox nbrVirtuels;
	private JPanel difficulte;
	private JPanel virtuels;
	private JPanel reels;
	private JLabel lDifficulte;
	private JLabel lNbrVirtuels;
	private JLabel lNbrReels;
	
	public Parametres(){
		super();
		this.setTitle("Parametres de jeu");
		this.setSize(1000, 800);
	    this.setLocationRelativeTo(null);               
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    pp = new PanelAccueil();
	    difficulte = new JPanel();
		virtuels = new JPanel();
		reels = new JPanel();
		Font font = new Font("Courier", Font.BOLD, 15);
		validation = new JButton("Jouer");
		validation.setFont(font);
		validation.setActionCommand("jouer");
		
		difficulteGroup = new ButtonGroup();
		difficulte1 = new JRadioButton("Facile");
		difficulte1.setFont(font);
		difficulte1.setOpaque(false);
		difficulte2 = new JRadioButton("Moyen");
		difficulte2.setFont(font);
		difficulte2.setOpaque(false);
		difficulte3 = new JRadioButton("Difficile");
		difficulte3.setFont(font);
		difficulte3.setOpaque(false);
		
		nbrReels = new JComboBox();
		nbrVirtuels = new JComboBox();
		
		lDifficulte = new JLabel("Difficulte : ");
		lDifficulte.setFont(font);
		
		lNbrVirtuels = new JLabel("Joueurs Virtuels : ");
		lNbrVirtuels.setFont(font);
		
		lNbrReels = new JLabel("Joueurs Reels : ");
		lNbrReels.setFont(font);
		
		for (int i=0;i<10;i++){nbrReels.addItem(i+1);}
		for (int i=0;i<10;i++){nbrVirtuels.addItem(i);}
		
		difficulteGroup.add(difficulte1);
		difficulteGroup.add(difficulte2);
		difficulteGroup.add(difficulte3);
		
		difficulte.add(lDifficulte);
		difficulte.add(difficulte1);
		difficulte.add(difficulte2);
		difficulte.add(difficulte3);
		difficulte.setOpaque(false);
		
		virtuels.add(lNbrVirtuels);
		virtuels.add(nbrVirtuels);
		virtuels.setOpaque(false);
		
		reels.add(lNbrReels);
		reels.add(nbrReels);
		reels.setOpaque(false);
		
		pp.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
	    gbc.insets = new Insets(10,10,10,10);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    pp.add(virtuels, gbc);
	    
	    gbc.gridy = 1;
	    pp.add(reels, gbc);
	    
	    gbc.gridy = 2;
	    pp.add(difficulte, gbc);
	    
	    gbc.ipadx = 100;
	    gbc.ipady = 10;
	    gbc.gridy = 3;
		pp.add(validation, gbc);
		
	    this.setContentPane(pp);
	}
	
	public int getDifficulte(){
		int difficulte = 0;
		if (difficulte1.isSelected()){difficulte = 1;}
		if (difficulte2.isSelected()){difficulte = 2;}
		if (difficulte3.isSelected()){difficulte = 3;}
		return difficulte;
	}
	
	public int getNbrVirtuels(){
		return (Integer) nbrVirtuels.getSelectedItem();
	}
	
	public int getNbrReels(){
		return (Integer) nbrReels.getSelectedItem();
	}
	
	public void setListener(Controleur controleur) {
		validation.addActionListener(controleur);
	}
}

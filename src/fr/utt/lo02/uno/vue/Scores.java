package fr.utt.lo02.uno.vue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import fr.utt.lo02.uno.modele.partie.Partie;

public class Scores extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTable tableau;
	private String titres[] = {"Nom du Joueur", "Score"};
	private Object donnees[][];
	
	public Scores(Partie p){
		this.setLocationRelativeTo(null);
		this.setSize(400, 300);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setTitle("Scores");

	    this.donnees = new Object[p.getJoueurs().size()][p.getJoueurs().size()];
	    for (int i=0;i<p.getJoueurs().size();i++){
	    	this.donnees[i][0] = p.getJoueurs().get(i).getNomJoueur();
	    }
	    for (int j=0;j<p.getJoueurs().size();j++){
	    	this.donnees[j][1] = p.getJoueurs().get(j).getScore();
    	}
	    this.tableau = new JTable(donnees, titres);
	    this.setContentPane(new JScrollPane(tableau));
	}

}


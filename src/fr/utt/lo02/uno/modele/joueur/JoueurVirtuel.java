package fr.utt.lo02.uno.modele.joueur;

import fr.utt.lo02.uno.modele.carte.Carte;
import fr.utt.lo02.uno.modele.partie.Partie;
import fr.utt.lo02.uno.modele.pile.*;
/**
 * Cette classe repr�sente les joueurs virtuels. La class JoueurVirtuel h�rite de Joueur.
 * Les attributs suppl�mentaires qui caract�rise un joueur virtuels sont sa difficult� et sa strat�gie associ�e.
 * On redefinis dans cette classe la m�thode poserCarte de Joueurs, en effet c'est dans cette classe qu'on rend le joueur autonome de l'utilisateur.
 * 
 * @author Hugo Arthur
 * @see Joueur
 */
public class JoueurVirtuel extends Joueur {
	private int difficulte;
	public static final String jv[] = {"Luc","Arthur","Remy","Gaetan","Melanie","Naomi","Antoine","Julian","Jerome"};
	private Strategie strategie;
	
	/**
	 * Constructeur de JoueurVirtuel.
	 * Il permet d'attribuer un nom au joueur, de lui affecter une difficult� et en fonction de cette difficult� une strat�gie.
	 * Une difficult� de 1 correpond � une difficult� facile, une difficult� de 2 correpond � une difficult� moyenne et une difficult� de 3 correpond � une difficult� difficile.
	 * @param nomJoueur
	 * @param difficulte
	 */
	
	public JoueurVirtuel(String nomJoueur, int difficulte) {
		super(nomJoueur);
		this.setVirtuel(true);
		this.difficulte = difficulte;
		if (difficulte == 1){
			this.strategie = new Strategie1();
		}
		if (difficulte == 2){
			this.strategie = new Strategie2();
		}
		if(difficulte == 3){
			this.strategie = new Strategie3();
		}
	}
	
	/**
	 * Cette m�thode permet d'appeller la strat�gie et les actions qui en d�coulent du joueur.
	 * 
	 * @param carte
	 * @param talon
	 * @param pioche
	 * @param partie
	 */
	public void poserCarte(Carte carte, Talon talon, Pioche pioche, Partie partie){
		this.strategie.poserOuPiocher(talon, pioche, partie, this.cartesEnMain);
		partie.notifyObservers("actualiserPlateau");
	}
	
	/**
	 * Getteur de l'attribut difficulte, renvoie la difficult� du joueur.
	 * @return
	 */
	public int getDifficulte() {
		return difficulte;
	}


}
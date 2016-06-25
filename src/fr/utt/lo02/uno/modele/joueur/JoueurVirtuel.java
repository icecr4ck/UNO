package fr.utt.lo02.uno.modele.joueur;

import fr.utt.lo02.uno.modele.carte.Carte;
import fr.utt.lo02.uno.modele.partie.Partie;
import fr.utt.lo02.uno.modele.pile.*;
/**
 * Cette classe représente les joueurs virtuels. La class JoueurVirtuel hérite de Joueur.
 * Les attributs supplémentaires qui caractérise un joueur virtuels sont sa difficulté et sa stratégie associée.
 * On redefinis dans cette classe la méthode poserCarte de Joueurs, en effet c'est dans cette classe qu'on rend le joueur autonome de l'utilisateur.
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
	 * Il permet d'attribuer un nom au joueur, de lui affecter une difficulté et en fonction de cette difficulté une stratégie.
	 * Une difficulté de 1 correpond à une difficulté facile, une difficulté de 2 correpond à une difficulté moyenne et une difficulté de 3 correpond à une difficulté difficile.
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
	 * Cette méthode permet d'appeller la stratégie et les actions qui en découlent du joueur.
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
	 * Getteur de l'attribut difficulte, renvoie la difficulté du joueur.
	 * @return
	 */
	public int getDifficulte() {
		return difficulte;
	}


}
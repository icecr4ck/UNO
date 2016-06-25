package fr.utt.lo02.uno.modele.carte;

import java.util.LinkedList;

import fr.utt.lo02.uno.modele.partie.Partie;
import fr.utt.lo02.uno.modele.pile.*;

/**
 * La carte +2 est une classe heritant de la classe Carte, elle va permettre de sauter le tour du joueur du suivant et de lui distribuer 2 cartes.
 * Elle redefinit donc toutes ces methodes abstraites.
 * On a redefinit la methode toString afin que ces cartes soient lisibles dans la console.
 * 
 * @see Carte
 * @author Hugo Arthur
 *
 */

public class CartePlus2 extends Carte {

	/**
	 * Le constructeur a pour parametre la couleur de la carte.
	 * Sa valeur en point vaut 20.
	 * Son numero pour la strategie vaut 4, c'est une carte que le joueur ne va jouer que en 2eme priorite.
	 * Son numero pour determiner le joueur a jouer est -1, ce n'est pas une carte qui va permettre d'avoir la priorite pour commencer.
	 */
	
	public CartePlus2(int couleur) {
		super(couleur);
		this.points=20;
		this.setNumero(-1);
		this.numeroPourLesStrategies=4;

	}
	
	/**
	 * Cette methode permet de savoir si la carte est jouable. Elle retourne l'attribut carteJouable.
	 * carteJouable prend la valeur true si la carte est jouable et la valeur false sinon.
	 * Pour determiner la valeur de carteJouable on va regarder si la couleur du talon correspond a la couleur de
	 * la carte ou si la classe correpond a la classe de la derniere carte posee sur le talon.
	 */
	
	public boolean determinerCarteJouable(Talon talon) {
		LinkedList<Carte> talonList = (LinkedList<Carte>) talon.getPile();
		if ((talon.getCouleurTalon() == this.couleur)
				|| (talonList.getLast().getClass() == CartePlus2.class)) {
			this.carteJouable = true;
		} else {
			this.carteJouable = false;
		}
		return carteJouable;
	}

	/**
	 * L'effet de cette carte est de distribuer 2 cartes au joueur suivant et de lui faire passer son tour. 
	 * Pour distribuer les cartes on prend une precaution afin de de pas tirer plus de carte qu'il n'y en a dans la talon. 
	 * Pour ca on regarde si il y a suffisament de carte dans le talon et sinon on retourne la pioche.
	 */
	public void effetCarte(Talon talon, Pioche pioche, Partie partie){
		pioche.verifieTalon(talon, 2);
		pioche.distribuerCarte(partie.getJoueurAttaque(), 2);
		Class<? extends Carte> classeCarteTalon=talon.getPile().getLast().getClass();
		partie.passerTour(classeCarteTalon);
	}
	
	/**
	 * Methode qui permet de rendre lisible la carte dans une interface console. 
	 */
	
    public String toString() {
	StringBuffer sb = new StringBuffer();
	sb.append("Plus 2 ");
	sb.append(Carte.COULEUR[this.couleur]);
	return sb.toString();}
}

package fr.utt.lo02.uno.modele.carte;

import java.util.LinkedList;

import fr.utt.lo02.uno.modele.partie.Partie;
import fr.utt.lo02.uno.modele.pile.*;
	
	/**
	 * La carte inverse sens est une carte qui va permettre de changer le sens de la partie.
	 * Elle redefinit les methodes abstraites de la classe Carte.
	 * On a redefinit la methode toString afin que ces cartes soient lisibles dans la console.
	 * 
	 * @see Carte
	 * @author Hugo Arthur
	 *
	 */

public class CarteInverseSens extends Carte {

	/**
	 * Le constructeur a pour parametre la couleur de la carte.
	 * Sa valeur en point vaut 20.
	 * Son numero pour la strategie vaut 2, c'est une carte que le joueur ne va jouer que en 4eme priorite.
	 * Son numero pour determiner le joueur a jouer est -1, ce n'est pas une carte qui va permettre d'avoir la priorite pour commencer.
	 */
	
	public CarteInverseSens(int couleur) {
		super(couleur);
		this.points = 20;
		this.setNumero(-1);
		this.numeroPourLesStrategies=2;

	}
	
	/**
	 * Cette methode permet de savoir si la carte est jouable. Elle retourne l'attribut carteJouable.
	 * carteJouable prend la valeur true si la carte est jouable et la valeur false sinon.
	 * Pour determiner la valeur de carteJouable on va regarder si la couleur du talon correspond a la couleur de
	 * la carte ou si le type de la classe correpond au type de la derniere carte posee sur le talon.
	 */
	
	public boolean determinerCarteJouable(Talon talon1) {
		LinkedList<Carte> talonList = (LinkedList<Carte>) talon1.getPile();
		if ((talon1.getCouleurTalon() == this.couleur) || (talonList.getLast().getClass() == CarteInverseSens.class)) {
			this.carteJouable = true;
		} else {
			this.carteJouable = false;
		}
		return carteJouable;
	}
	
	/**
	 * L'effet de cette carte permet d'inverser le sens du jeu. Elle va modifier l'attribut sensHoraire de manche.
	 */
	
	public void effetCarte(Talon talon, Pioche pioche, Partie partie){
		if (partie.isSensHoraire()){
			partie.setSensHoraire(false);
		}
		else{
			partie.setSensHoraire(true);
		}
	}
	
	/**
	 * Methode qui permet de rendre lisible la carte dans une interface console. 
	 */
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Inverse Sens ");
		sb.append(Carte.COULEUR[this.couleur]);
		return sb.toString();
	}
}

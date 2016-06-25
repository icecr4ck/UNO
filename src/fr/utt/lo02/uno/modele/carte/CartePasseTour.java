package fr.utt.lo02.uno.modele.carte;

import java.util.LinkedList;

import fr.utt.lo02.uno.modele.partie.Partie;
import fr.utt.lo02.uno.modele.pile.*;

/**
 * La carte Passe tour est une classe heritant de la classe Carte, elle va permettre de sauter le tour du joueur suivant.
 * Elle redefinit donc toutes ces methodes abstraites.
 * On a redefinit la methode toString afin que ces cartes soient lisibles dans la console.
 * 
 * @see Carte
 * @author Hugo Arthur
 *
 */

public class CartePasseTour extends Carte {

	/**
	 * Le constructeur a pour parametre la couleur de la carte.
	 * Sa valeur en point vaut 20.
	 * Son numero pour la strategie vaut 3, c'est une carte que le joueur ne va jouer que en 3eme priorite.
	 * Son numero pour determiner le joueur a jouer est -1, ce n'est pas une carte qui va permettre d'avoir la priorite pour commencer.
	 */
	
	public CartePasseTour(int couleur) {
		super(couleur);
		this.points=20;
		this.setNumero(-1);
		this.numeroPourLesStrategies=3;

	}
	
	/**
	 * Cette methode permet de savoir si la carte est jouable. Elle retourne l'attribut carteJouable.
	 * carteJouable prend la valeur true si la carte est jouable et la valeur false sinon.
	 * Pour determiner la valeur de carteJouable on va regarder si la couleur du talon correspond a la couleur de
	 * la carte ou si le type de la classe correpond au type de la derniere carte posee sur le talon.
	 */

	public boolean determinerCarteJouable(Talon talon) {
		LinkedList<Carte> talonList = (LinkedList<Carte>) talon.getPile();
		if ((talon.getCouleurTalon() == this.couleur) || (talonList.getLast().getClass() == CartePasseTour.class)) {
			this.carteJouable = true;
		} else {
			this.carteJouable = false;
		}
		return carteJouable;
	}
	/**
	 * L'effet de cette carte permet de passer le tour du joueur suivant en appellant la methode passerTour de la classe manche.
	 */
	public void effetCarte(Talon talon, Pioche pioche, Partie partie){
		Class<? extends Carte> classeCarteTalon = talon.getPile().getLast().getClass();
		partie.passerTour(classeCarteTalon);
	}
	
	/**
	 * Methode qui permet de rendre lisible la carte dans une interface console. 
	 */
	
    public String toString() {
	StringBuffer sb = new StringBuffer();
	sb.append("Passe Tour ");
	sb.append(Carte.COULEUR[this.couleur]);
	return sb.toString();}
}

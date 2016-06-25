package fr.utt.lo02.uno.modele.carte;

import fr.utt.lo02.uno.modele.partie.Partie;
import fr.utt.lo02.uno.modele.pile.*;

/**
 * Les cartes classiques definissent les cartes de couleur allant de 0 a 9.
 * Elle redefinit les methodes abstraites de la classe Carte.
 * On a redefinit la methode toString afin que ces cartes soient lisibles dans la console.
 * 
 * @see Carte
 * @author Hugo Arthur
 *
 */
public class CarteClassique extends Carte {

	public final static int ZERO = 0;
	public final static int UN = 1;
	public final static int DEUX = 2;
	public final static int TROIS = 3;
	public final static int QUATRE = 4;
	public final static int CINQ = 5;
	public final static int SIX = 6;
	public final static int SEPT = 7;
	public final static int HUIT = 8;
	public final static int NEUF = 9;

	private int numero;

	public final static String[] VALEURS = { "Zero", "Un", "Deux", "Trois",
			"Quatre", "Cinq", "Six", "Sept", "Huit", "Neuf" };
	/**
	 * Le constructeur de la carte. Il permet de definir le numero, la couleur de la carte, sa valeur en points,
	 * ainsi que son ordre de priorite pour une des strategies qui vaut 0, c'est une carte que le joueur ne va jouer que si il ne peut pas jouer d'autres carte.
	 * Elle prend comme parametre d'entree, le numero correspondant a la couleur et le numero de la carte.
	 */
	public CarteClassique(int couleur, int Numero) {
		super(couleur);
		this.numero = Numero;
		this.points = Numero;
		this.numeroPourLesStrategies=0;

	}
	
	/**
	 * Cette methode permet de savoir si la carte est jouable. Elle retourne l'attribut carteJouable.
	 * carteJouable prend la valeur true si la carte est jouable et la valeur false sinon.
	 * Pour determiner la valeur de carteJouable on va regarder si la couleur du talon correspond a la couleur de
	 * la carte ou si le type de la classe correpond au type de la derniere carte posee sur le talon.
	 */
	public boolean determinerCarteJouable(Talon talon) {
		if ((talon.getCouleurTalon() == this.couleur) || (talon.getPile().getLast().getNumero() == this.getNumero())) {
			this.carteJouable = true;
		} else {
			this.carteJouable = false;
		}
		return carteJouable;
	}

	/**
	 * Getteur de numero.
	 */
	public int getNumero() {
		return this.numero;
	}
	
	/**
	 * Cette methode ne fait rien. En effet les cartes classiques n'ont aucun effet sur la partie.
	 * Nous sommes neanmoins oblige de la definir car c'est une methode abstraite de la classe mere.
	 */
	public void effetCarte(Talon talon, Pioche pioche, Partie partie){
	}
	
	/**
	 * Methode qui permet de rendre lisible la carte dans une interface console. 
	 */
	public String toString() {
	StringBuffer sb = new StringBuffer();
	sb.append(CarteClassique.VALEURS[this.numero]);
	sb.append(" ");
	sb.append(Carte.COULEUR[this.couleur]);
	return sb.toString();
    } 
}

package fr.utt.lo02.uno.modele.carte;

import fr.utt.lo02.uno.modele.partie.Partie;
import fr.utt.lo02.uno.modele.pile.*;

	/**
	 * Cette classe est une classe mere abstraite des differents types de carte. Elle ne sera donc jamais instanciée.
	 * Elle va permettre de faire les manipulations sur les cartes sans se soucier de leur catégorie.
	 * Cette classe implemente l'interface Comparable car pour une strategie nous aurons besoin de trier les cartes.
	 * Une carte est caracterisée par sa couleur. On note noir la couleur des carte Joker et +4.
	 * Pour pouvoir faire les manipulations sur les cartes, on affecte 4 autres attributs; 
	 * le nombre de points que la carte vaut, cela sert a compter les scores de chaque joueur.
	 * Ensuite on a un autre attribut qui est un entier, il permet d'attribuer un ordre de priorité pour une stratégie.
	 * On a un attribut qui permet de savoir quel est le joueur qui va jouer en premier.
	 * Enfin il y a un boolean qui permet de savoir si la carte est jouable.
	 * Dans cette classe on definit deux methodes abstraites, l'effet de la carte ainsi qu'une methode qui verifie si la carte est jouable.
	 * Il y a tout les getter pour les differents attributs. Nous n'avons pas mis certains setters car les attributs concernes de doivent pas etre modifies.
	 * On definit une methode qui permet de redefinir la couleur du talon en fonction de la carte.
	 * Et enfin on definit la methode compareTo() qui permet de comparer les cartes entre elle en fonction de leur valeur de points.
	 * 
	 * @author Hugo Arthur
	 *
	 */

public abstract class Carte implements Comparable<Object> {

    public final static int BLEU = 0;
    public final static int ROUGE = 1;
    public final static int JAUNE = 2;
    public final static int VERT = 3;
    public final static int NOIR = 4;
	
    public final static String[] COULEUR = {"Bleu", "Rouge", "Jaune", "Vert", "Noir"};

    protected int couleur;
	protected boolean carteJouable;
	protected int points;
	protected int numero;
	protected int numeroPourLesStrategies;
	
	/**
	 * Constructeur de Carte, on va l'appeller comme super constructeur des classe filles. Le constructeur de carte
	 * définis la couleur de la carte.
	 */
	public Carte(int Couleur) {
		this.couleur=Couleur;
	}
	/**
	 * Getteur de couleur.
	 * @return
	 */
	public int getCouleur(){
		return this.couleur;
	}
	
	/**
	 * Getteur de points.
	 * @return
	 */
	public int getPoints() {
		return this.points;
	}
	
	/**
	 * Cette methode va être redéfinis dans les classes filles, elle representes l'effet de la carte qui est joué sur
	 * le jeu. L'effet va varier selon la classe de la carte.
	 * @param talon
	 * @param pioche
	 * @param partie
	 */
	
	public abstract void effetCarte(Talon talon, Pioche pioche, Partie partie);
	
	/**
	 * Cette methode va permettre au modele de savoir si la carte est jouable. On la definis dans carte, car la
	 * capacité d'une carte à être joué depend de la carte. Elle est redefinis dans chaque classe, car elle va varier
	 * en fonction du type de carte.
	 * 
	 * @param talon
	 * @return
	 */
	
	public abstract boolean determinerCarteJouable(Talon talon);
	
	/**
	 * Cette methode permet de modifier la couleur du talon en fonction de la couleur de la carte.
	 */
	public void changerCouleurTalon(Talon talon){
		talon.setCouleurTalon(this.couleur);
	}
	
	/**
	 * Getteur de numero.
	 * @return
	 */
	public int getNumero() {
		return numero;
	}
	
	/**
	 * Setteur de numero.
	 * @param numero
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	/**
	 * Getteur de numeroPourLesStrategies.
	 * @return
	 */
	public int getNumeroPourLesStrategies() {
		return numeroPourLesStrategies;
	}

	/**
	 * Cette methode permet de comparer les carte en fonctions de leur valeur en points.
	 * On ne servira pas de cette methode de maniere explicite. Elle sera seulement utilise dans des methodes predefinis de la classe java Collections.
	 */
	
	public int compareTo(Object other) { 
	    int nombre1 = ((Carte) other).getPoints(); 
	    int nombre2 = this.getPoints(); 
		if (nombre2 > nombre1)  {
			return -1; 
		}
		else if(nombre1 == nombre2) {
		    return 0;
		} 
		else {
			return 1; 
		}
	} 
}



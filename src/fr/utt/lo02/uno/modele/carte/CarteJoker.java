package fr.utt.lo02.uno.modele.carte;

import fr.utt.lo02.uno.modele.partie.Partie;
import fr.utt.lo02.uno.modele.pile.*;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * La carte joker est une classe heritant de la classe Carte.
 * Elle redefinit donc toutes ces methodes abstraites.
 * On a redefinit la methode toString afin que ces cartes soient lisibles dans la console.
 * 
 * @see Carte
 * @author Hugo Arthur
 *
 */

public class CarteJoker extends Carte{
	
	/**
	 * Le constructeur n'a pas de parametre d'entree neanmoins il redefinit chacun des attributs de Carte.
	 * Sa couleur est le 4 qui correspond au noir.
	 * Sa valeur en point vaut 50.
	 * Son numero pour la strategie vaut 1, c'est une carte que le joueur ne va jouer que en 5eme priorité.
	 * Son numero pour determiner le joueur a jouer est -1, ce n'est pas une carte qui va permettre d'avoir la priorite pour commencer.
	 */
	public CarteJoker() {
		super(4);
		this.points = 50;
		this.setNumero(-1);
		this.numeroPourLesStrategies = 1;
	}
	
	/**
	 * La carte joker est tout le temps jouable.
	 */
	
	public boolean determinerCarteJouable(Talon talon1) {
		carteJouable=true;
		return carteJouable;
	}
	
	/**
	 * L'effet de cette carte permet de modifier la couleur du talon.
	 * Si le joueur qui à joué cette carte est un joueur virtuel alors on appelle la méthode determinerCouluerAApplique()
	 * qui va déterminé la couleur la plus favorable pour le joueur.
	 * Si le joueur est un joueur utilisateur, on lui demande qu'elle couleur il veut grâce à l'interface graphique.
	 */
	public void effetCarte(Talon talon, Pioche pioche, Partie partie){
		if (partie.getJoueurJoue().isVirtuel()){
			talon.setCouleurTalon(this.determinerCouleurAApplique(partie.getJoueurJoue().getCartesEnMain()));
			partie.notifyObservers("afficherCouleur");
		}
		else{
			partie.notifyObservers("changementCouleur");
		}
	}
	
	/**
	 * Cette methode permet de savoir quelle est la couleur la plus favorable pour le joueur qui pose une carte Joker.
	 * Dans cette methode on va compter la couleur de chaque carte. On a un tableau dont le rang correspond a la couleur.
	 * On incremente la case d'un rang a chaque fois que l'on tombe sur une carte de la couleur correspondant au rang.
	 * Une fois le nombre de carte de chaque couleur compte. On regarde qu'elle est la couleur/ou le rang ou le nombre est le plus grand.
	 * Ce rang correspond a la couleur la plus favorable.
	 * 
	 * @param cartesEnMain
	 * @return
	 */
	
	public int determinerCouleurAApplique(LinkedList<Carte> cartesEnMain){
		int couleur=0;
		int tabNbCouleur[]={0,0,0,0};
		
		ListIterator<Carte> iCarte1=cartesEnMain.listIterator();
		while(iCarte1.hasNext()){
			Carte carte3=iCarte1.next();
			if(carte3.getCouleur()==0){
				tabNbCouleur[0]++;
			}
			if(carte3.getCouleur()==1){
				tabNbCouleur[1]++;
			}
			if(carte3.getCouleur()==2){
				tabNbCouleur[2]++;
			}
			if(carte3.getCouleur()==3){
				tabNbCouleur[3]++;
			}
		}
        int k=tabNbCouleur[0],i;
        for(i=0;i<4;i++){
        	if (k<tabNbCouleur[i]){
        		k=tabNbCouleur[i];//k correpond aux nombre de carte d'une certaine couleur le plus grand.
        		couleur=i; //i correpond à la couleur majoritaire.
        	}
        }
       return couleur;
	}
	
	/**
	 * Methode qui permet de rendre lisible la carte dans une interface console. 
	 */
	
    public String toString() {
	StringBuffer sb = new StringBuffer();
	sb.append("Joker");
	return sb.toString();}
}


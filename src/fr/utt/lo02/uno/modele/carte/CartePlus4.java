package fr.utt.lo02.uno.modele.carte;

import java.util.LinkedList;
import java.util.ListIterator;

import fr.utt.lo02.uno.modele.partie.Partie;
import fr.utt.lo02.uno.modele.pile.*;

/**
 *La carte +4 est une classe heritant de la classe Carte. 
 * Elle va permettre de sauter le tour du joueur du suivant et de lui distribuer 4 cartes et de redefinir la couleur du talon.
 * Elle redefinit donc toutes ces methodes abstraites.
 * On a redefinit la methode toString afin que ces cartes soient lisibles dans la console.
 * 
 * @see Carte
 * @author Hugo Arthur
 *
 */

public class CartePlus4 extends Carte{

	/**
	 * Le constructeur n'a pas de parametres d'entree neanmoins il redefinit chacun des attributs de Carte.
	 * Sa couleur est le 4 qui correspond au noir.
	 * Sa valeur en point vaut 50.
	 * Son numero pour la strategie vaut 5, c'est une carte que le joueur ne va jouer en 1ere priorite.
	 * Son numero pour determiner le joueur a jouer est -1, ce n'est pas une carte qui va permettre d'avoir la priorite pour commencer.
	 */
	
	public CartePlus4() {
		super(4);
		this.points=50;
		this.setNumero(-1);
		this.numeroPourLesStrategies=5;
	}
	
	/**
	 * La carte +4 est tout le temps jouable.
	 */
	
	public boolean determinerCarteJouable(Talon talon) {
		carteJouable=true;
		return carteJouable;
	}
	
	/**
	 * Cette méthode applique l'effet de la carte +4.On a pris en compte la possibilite qu'un joueur puisse bluffer.
	 * On demande si leur joueur suivant veut vérifier le bluff par l'intermédiaire de l'interface graphique. 
	 * Si il a bluffer les conséquence sont réalisé par la méthode verifieBluff de partie.
	 * On regarde tout d'abord si le joueur qui joue est virtuel ou réel.
	 * Si il est virtuel, et qu'il n'as pas bluffé, on distribue 4 cartes au joueur attaqué, 
	 * on vérifie d'abord si il y a assez de carte pour pouvoir les distribuer, ensuite on determine automatiquement la couleur du talon
	 * grâce à la méthode determinerCouleurAAppliqué de CartePlus4 et on fait sauter le tour du joueur suivant.
	 * Si il est réel, on réalise les même actions sauf que l'utilisateur désigne la couleur du talon grâce à l'interface graphique.
	 * 
	 * @see Joueur
	 * @see JoueurVirtuel
	 * @see partie
	 */
	public void effetCarte(Talon talon, Pioche pioche, Partie partie){
		partie.notifyObservers("demandeBluff");
		if (partie.getJoueurJoue().isVirtuel()){
			if (!partie.getJoueurJoue().isBluff()){
				pioche.verifieTalon(talon, 4);
				pioche.distribuerCarte(partie.getJoueurAttaque(), 4);
				talon.setCouleurTalon(this.determinerCouleurAApplique(partie.getJoueurJoue().getCartesEnMain()));
				Class<? extends Carte> classeCarteTalon = talon.getPile().getLast().getClass();
				partie.passerTour(classeCarteTalon);
				partie.notifyObservers("afficherCouleur");
			}
		}
		else{
			if (!partie.getJoueurJoue().isBluff()){
				pioche.verifieTalon(talon, 4);
				pioche.distribuerCarte(partie.getJoueurAttaque(), 4);
				Class<? extends Carte> classeCarteTalon = talon.getPile().getLast().getClass();
				partie.passerTour(classeCarteTalon);
				partie.notifyObservers("changementCouleur");
			}
		}
		partie.getJoueurJoue().setBluff(false);
	}
	
	/**
	 * Cette methode permet de savoir qu'elle est la couleur la plus favorable pour le joueur qui pose une carte +4.
	 * Dans cette methode on va compter la couleur de chaque carte. On a un tableau dont le rang correspond a la couleur.
	 * On incremente la case d'un rang a chaque fois que l'on tombe sur une carte de la couleur correspondant au rang.
	 * Une fois le nombre de carte de chaque couleur compte. On regarde quelle est la couleur/ou le rang ou le nombre est le plus grand.
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
	sb.append("Plus 4");
	return sb.toString();}

}


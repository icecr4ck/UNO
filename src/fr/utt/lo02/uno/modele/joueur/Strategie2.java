package fr.utt.lo02.uno.modele.joueur;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import fr.utt.lo02.uno.modele.carte.*;
import fr.utt.lo02.uno.modele.partie.Partie;
import fr.utt.lo02.uno.modele.pile.*;
/**
 * La seconde stratégie est la stratégie dite de difficulté moyenne. 
 * La tactique de cette stratégie est de poser les cartes qui valent le plus de points en priorité.
 * Le joueur va trier les cartes qu'il a en main en mettant en avant les cartes qui valent le plus de points.
 * Ensuite il va jouer la première carte jouable qu'il trouve dans sa main. En conséquent il va jouer la carte jouable la plus mise en avant.
 * Cette stratégie fait partie du premier type de stratégie évoquée dans la doc de Strategie.
 * 
 * @author Hugo Arthur
 * @see Strategie
 * @see JoueurVirtuel
 */
public class Strategie2 implements Strategie {
	
	/**
	 * Cette méthode est le coeur de la deuxième stratégie. Elle se sert de la méthode static sort() de la class Collections définie dans le package java.util .
	 * La méthode sort() se sert de la méthode compareTo() définie dans la class Carte.
	 * Cette méthode va donc trier les cartes vallant le plus de points, aux cartes vallant le moins de points.
	 * 
	 * @param cartesEnMain1
	 * @param talon
	 * @see Carte
	 */
	
	private void trierCarte(LinkedList<Carte> cartesEnMain1,Talon talon){
		List<Carte> b = (List<Carte>) cartesEnMain1;
		Collections.sort(b);
	}
	
	/**
	 * Cette méthode va regarder tous les cartes de la main du joueurs une à une, et lorsqu'elle va rencontrer une carte jouable elle va la poser.
	 * On precise que cette méthode est appellée une fois que les cartes sont triées, de ce fait elle va regarder les cartes vallant le plus de points
	 * dans l'ordre décroissant de ceux-ci.
	 * Cette méthode va aussi permettre de faire dire au joueur UNO. Le joueur a 50% de dire UNO.
	 * 
	 * @param talon
	 * @param pioche
	 * @param partie
	 * @param cartesEnMain
	 */
	
	private void poserCarte(Talon talon, Pioche pioche, Partie partie, LinkedList<Carte> cartesEnMain){
		boolean aDejaJouer=true;
		ListIterator<Carte> iCarte = cartesEnMain.listIterator();
		while(iCarte.hasNext() && aDejaJouer){
			Carte carteAJouer = cartesEnMain.get(iCarte.nextIndex());
			if (carteAJouer.determinerCarteJouable(talon)==false){
				iCarte.next();
			}
			else {
				talon.getPile().add(carteAJouer);
				if (carteAJouer.getCouleur()!=4){
					talon.setCouleurTalon(carteAJouer.getCouleur());
				}
				carteAJouer.effetCarte(talon, pioche, partie);
				cartesEnMain.remove(carteAJouer);
				long hasard = Math.round(Math.random());
				if(hasard==1){
					if(partie.getJoueurJoue().getCartesEnMain().size()<2){
						partie.getJoueurJoue().direUno();
					}
				}
				aDejaJouer=false;
			}	
		}
	}
	
	/**
	 * Cette méthode permet de structuer la stratégie, dans le sens où elle défini les étapes par lesquelles la stratégie, va procéder.
	 * On trie d'abord les cartes, puis on pose une carte.
	 * 
	 * @param talon
	 * @param pioche
	 * @param partie
	 * @param cartesEnMain
	 */
	public void poserOuPiocher(Talon talon, Pioche pioche, Partie partie, LinkedList<Carte> cartesEnMain){
		trierCarte(cartesEnMain,talon);
		this.poserCarte(talon, pioche, partie, cartesEnMain);
	}
}
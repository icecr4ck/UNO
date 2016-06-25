package fr.utt.lo02.uno.modele.joueur;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import fr.utt.lo02.uno.modele.carte.*;
import fr.utt.lo02.uno.modele.partie.Partie;
import fr.utt.lo02.uno.modele.pile.*;
/**
 * La seconde strat�gie est la strat�gie dite de difficult� moyenne. 
 * La tactique de cette strat�gie est de poser les cartes qui valent le plus de points en priorit�.
 * Le joueur va trier les cartes qu'il a en main en mettant en avant les cartes qui valent le plus de points.
 * Ensuite il va jouer la premi�re carte jouable qu'il trouve dans sa main. En cons�quent il va jouer la carte jouable la plus mise en avant.
 * Cette strat�gie fait partie du premier type de strat�gie �voqu�e dans la doc de Strategie.
 * 
 * @author Hugo Arthur
 * @see Strategie
 * @see JoueurVirtuel
 */
public class Strategie2 implements Strategie {
	
	/**
	 * Cette m�thode est le coeur de la deuxi�me strat�gie. Elle se sert de la m�thode static sort() de la class Collections d�finie dans le package java.util .
	 * La m�thode sort() se sert de la m�thode compareTo() d�finie dans la class Carte.
	 * Cette m�thode va donc trier les cartes vallant le plus de points, aux cartes vallant le moins de points.
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
	 * Cette m�thode va regarder tous les cartes de la main du joueurs une � une, et lorsqu'elle va rencontrer une carte jouable elle va la poser.
	 * On precise que cette m�thode est appell�e une fois que les cartes sont tri�es, de ce fait elle va regarder les cartes vallant le plus de points
	 * dans l'ordre d�croissant de ceux-ci.
	 * Cette m�thode va aussi permettre de faire dire au joueur UNO. Le joueur a 50% de dire UNO.
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
	 * Cette m�thode permet de structuer la strat�gie, dans le sens o� elle d�fini les �tapes par lesquelles la strat�gie, va proc�der.
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
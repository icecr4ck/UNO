package fr.utt.lo02.uno.modele.joueur;

import java.util.LinkedList;
import java.util.ListIterator;

import fr.utt.lo02.uno.modele.carte.*;
import fr.utt.lo02.uno.modele.partie.Partie;
import fr.utt.lo02.uno.modele.pile.*;

	/**
	 * La première strategie est la strategie dite facile. Le joueur virtuel n'a pas de tactique réellement définie.
	 * Lorsque c'est à son tour de jouer, une fois sur deux il ne va pas vouloir jouer mais piocher.
	 * Si il décide de jouer, il va prendre la première carte jouable, dans l'ordre des cartes qu'il a reçut, et la poser.
	 * Cette stratégie fait partie du deuxième type de stratégie évoquée dans la doc de Strategie.
	 * Cette classe implémente donc l'interface Strategie.
	 * 
	 * @see JoueurVirtuel
	 * @see Strategie
	 * @author Hugo Arthur
	 *
	 */
public class Strategie1 implements Strategie {
	
	/**
	 * Cette méthode implémente le fait que le joueur de ne veut peut être pas jouer mais piocher,
	 * ici le joueur va jouer 1 fois sur 2.
	 *
	 *@param talon
	 *@param pioche
	 *@param partie
	 *@param cartesEnMain
	 */
	
	public void poserOuPiocher(Talon talon, Pioche pioche, Partie partie, LinkedList<Carte> cartesEnMain){
		long hasard = Math.round(Math.random()); //renvoie au hasard 1 ou 0
		if(hasard==0){ //Si 0, le joueur joue
			this.poserCarte(talon,pioche, partie, cartesEnMain);
		}
		else{ //Si 1 le joueur pioche
			pioche.verifieTalon(talon, 1);
			pioche.distribuerCarte(partie.getJoueurJoue(), 1);
		}
	}
	
	/**
	 * Cette methode constitue le coeur de la strategie.
	 * Dans cette stratégie on a pas d'ordre de priorité des cartes. On va regarder l'ensemble des cartes en main du joueur.
	 * Et dès qu'on en trouve une jouable, on la joue.
	 * Dans cette méthode on définis aussi, si le joueur virtuel dis UNO. 
	 * Cette caractéristique est implémentée de façon à ce que le joueur est 50% de chance de dire UNO.
	 * 
	 * @param talon
	 * @param pioche
	 * @param partie
	 * @param cartesEnMain
	 */
	private void poserCarte(Talon talon, Pioche pioche, Partie partie, LinkedList<Carte> cartesEnMain){
		boolean aDejaJouer=true;
		long hasard = Math.round(Math.random());
		ListIterator<Carte> iCarte = cartesEnMain.listIterator();
		while(iCarte.hasNext() && aDejaJouer){ // On regarde toutes les cartes de la main du joueur tant qu'il n'a pas joué
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
				if(hasard==1){
					if(partie.getJoueurJoue().getCartesEnMain().size()<2){
						partie.getJoueurJoue().direUno();
					}
				}
				aDejaJouer=false;
			}
		}
	}
}

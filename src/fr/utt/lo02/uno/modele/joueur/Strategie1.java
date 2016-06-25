package fr.utt.lo02.uno.modele.joueur;

import java.util.LinkedList;
import java.util.ListIterator;

import fr.utt.lo02.uno.modele.carte.*;
import fr.utt.lo02.uno.modele.partie.Partie;
import fr.utt.lo02.uno.modele.pile.*;

	/**
	 * La premi�re strategie est la strategie dite facile. Le joueur virtuel n'a pas de tactique r�ellement d�finie.
	 * Lorsque c'est � son tour de jouer, une fois sur deux il ne va pas vouloir jouer mais piocher.
	 * Si il d�cide de jouer, il va prendre la premi�re carte jouable, dans l'ordre des cartes qu'il a re�ut, et la poser.
	 * Cette strat�gie fait partie du deuxi�me type de strat�gie �voqu�e dans la doc de Strategie.
	 * Cette classe impl�mente donc l'interface Strategie.
	 * 
	 * @see JoueurVirtuel
	 * @see Strategie
	 * @author Hugo Arthur
	 *
	 */
public class Strategie1 implements Strategie {
	
	/**
	 * Cette m�thode impl�mente le fait que le joueur de ne veut peut �tre pas jouer mais piocher,
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
	 * Dans cette strat�gie on a pas d'ordre de priorit� des cartes. On va regarder l'ensemble des cartes en main du joueur.
	 * Et d�s qu'on en trouve une jouable, on la joue.
	 * Dans cette m�thode on d�finis aussi, si le joueur virtuel dis UNO. 
	 * Cette caract�ristique est impl�ment�e de fa�on � ce que le joueur est 50% de chance de dire UNO.
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
		while(iCarte.hasNext() && aDejaJouer){ // On regarde toutes les cartes de la main du joueur tant qu'il n'a pas jou�
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

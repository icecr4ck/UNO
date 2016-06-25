package fr.utt.lo02.uno.modele.joueur;

import java.util.LinkedList;

import fr.utt.lo02.uno.modele.carte.*;
import fr.utt.lo02.uno.modele.partie.Partie;
import fr.utt.lo02.uno.modele.pile.*;

/**
 * L''interface strategie va permettre de definir le comportement des joueurs virtuels dans le jeu.
 * Elle definit une seule signature de methode, celle de poser une carte.
 * On distingue deux types de strategie. Le premier type de stratégie est celui où, en premier lieu, on trie les cartes en fonction de leur ordre de priorité.
 * Une fois triées on va prendre la premiere carte jouable et la jouer.
 * Le deuxieme type de strategie est celui où on va inspecter chaque carte et prendre la carte la plus grande dans l'ordre de priorite. 
 * 
 * @see JoueurVirtuel
 * @author Hugo Arthur
 *
 */

public interface Strategie {
	
	/**
	 * Methode qui n'est pas definie. et qui va etre redefinie dans les classes implementant l'interface.
	 * 
	 * @param talon1
	 * @param pioche1
	 * @param manche1
	 * @param cartesEnMain
	 */
	
	public void poserOuPiocher(Talon talon, Pioche pioche, Partie partie, LinkedList<Carte> cartesEnMain);
}

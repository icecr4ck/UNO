package fr.utt.lo02.uno.modele.pile;

import java.util.Collections;
import java.util.LinkedList;

import fr.utt.lo02.uno.modele.carte.*;

/**
 * Cette classe correspond a la liste des cartes qui se trouvent dans le tas de carte.
 * Si la liste est nulle on comprend qu'il n'y a pas de cartes dans le tas. On ne pourra pas aller chercher de cartes dans ce tas
 * Il n'y a donc pas de setter associe a cet attribut.
 * 
 * @author Hugo Arthur
 */

public class Pile{
	
	protected LinkedList<Carte> pile;
	
	/**Le constructeur du TasDeCarte.
	 * Dans ce constructeur on cree toutes les cartes que peut contenir un jeu de Uno.
	 * On utilise donc les constructeurs des differentes cartes. On cree deux cartes de chaque type. Sauf pour la carte 0
	 * qu'on en instancie qu'une fois, ainsi que les cartes Joker et +4 qu'on instancie 4 fois.
	 */
	
	public Pile() {
		LinkedList<Carte> pile = new LinkedList<Carte>();
		int i, j, k;
		for (i = 0; i < 4; i++) {
			for(k=0;k<2;k++){
				for (j = 1; j < 10; j++) {
					CarteClassique nouvelle = new CarteClassique(i, j);
					pile.add(nouvelle);
				}
			}
			CarteClassique nouvelle1 = new CarteClassique(i, 0);
			pile.add(nouvelle1);			
			for (j = 0; j < 2; j++) {
				CarteInverseSens nouvelle = new CarteInverseSens(i);
				pile.add(nouvelle);
			}
			for (j = 0; j < 2; j++) {
				CartePasseTour nouvelle = new CartePasseTour(i);
				pile.add(nouvelle);
			}
			for (j = 0; j < 2; j++) {
				CartePlus2 nouvelle = new CartePlus2(i);
				pile.add(nouvelle);
			}
		}
		for (j = 0; j < 4; j++) {
			CarteJoker nouvelle = new CarteJoker();
			pile.add(nouvelle);
		}
		for (j = 0; j < 4; j++) {
			CartePlus4 nouvelle = new CartePlus4();
			pile.add(nouvelle);
		}
		Collections.shuffle(pile);
		this.pile = pile;
	}
	
	/**
	 * Getteur du tas de cartes de la pile.
	 * @return
	 */
	public LinkedList<Carte> getPile(){
		return pile;
	}
	
	
	/**
	 * Setteur du tas de cartes de la pile.
	 * @param pile
	 */
	public void setPile(LinkedList<Carte> pile){
		this.pile = pile;
	}
}
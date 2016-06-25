package fr.utt.lo02.uno.modele.joueur;

import java.util.LinkedList;
import java.util.ListIterator;

import fr.utt.lo02.uno.modele.carte.*;
import fr.utt.lo02.uno.modele.partie.Partie;
import fr.utt.lo02.uno.modele.pile.*;

/**
 * La seconde stratégie est la stratégie dite de difficulté difficile. 
 * La tactique de cette stratégie est de poser les cartes qui ont le plus grand numero de priorité en premières.
 * Dans cette stratégie on va comparer toutes les cartes, on va en retenir une et on la pose.
 * Cette stratégie fait partie du deuxième type de stratégie évoquée dans la doc de Strategie.
 * 
 * @author Hugo Arthur
 * @see Strategie
 * @see JoueurVirtuel
 */

public class Strategie3 implements Strategie {
	
	/**
	 * Cette méthode est le coeur de la stratégie. Elle est composée de 3 étapes.
	 * La première étape est d'identifier une carte jouable. On prend la première carte jouable et on la retient.
	 * Une fois qu'on a recupérer cette carte, on compare toutes les cartes à celle retenu. 
	 * Si une carte est jouable et a un numéro pour les stratégies supérieur à la carte retenu, on change la carte retenu avec la carte comparante.
	 * La deuxième étape est de poser la carte sur le talon et la supprimer des cartes en mains du joueurs.
	 * Ensuite on determine si le joueur dis UNO. Ici on 50% de chance que le joueur dise UNO.
	 * 
	 * @param talon
	 * @param pioche
	 * @param partie
	 * @param cartesEnMain
	 */
	
	private void poserCarte(Talon talon, Pioche pioche, Partie partie, LinkedList<Carte> cartesEnMain){
		Carte carteAJouer = null;
		ListIterator<Carte> iDetermineUneCarteJouable = cartesEnMain.listIterator() ;
		while (iDetermineUneCarteJouable.hasNext()){
			Carte carte2 = iDetermineUneCarteJouable.next();
			if(carte2.determinerCarteJouable(talon)){
				carteAJouer = carte2;
			}
		}
		ListIterator<Carte> iCartesEnMain = cartesEnMain.listIterator() ;
		while(iCartesEnMain.hasNext()){
			Carte carte1 = iCartesEnMain.next();
			if(carte1.determinerCarteJouable(talon) && carte1.getNumeroPourLesStrategies() > carteAJouer.getNumeroPourLesStrategies()){
				carteAJouer = carte1;
			}
		}
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
	}
	
	/**
	 * Cette méthode n'est pas très utilisée dans cette stratégie, elle sert juste à recopier le modèle des deux autres stratégies. 
	 * 
	 * @param talon
	 * @param pioche
	 * @param partie
	 * @param cartesEnMain
	 */
	
	public void poserOuPiocher(Talon talon, Pioche pioche, Partie partie, LinkedList<Carte> cartesEnMain){
		this.poserCarte(talon, pioche, partie, cartesEnMain);
	}
}

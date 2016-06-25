package fr.utt.lo02.uno.modele.pile;

import fr.utt.lo02.uno.modele.carte.Carte;
import fr.utt.lo02.uno.modele.joueur.Joueur;

/**
 * La pioche est un tas de carte auquel on soustrait des cartes au fur et a mesure du jeu. Elle hérite donc de Pile.
 * Elle n'a pas d'attributs supplementaire par rapport a sa classe mere. 
 * Par contre elle possede des methodes qui vont permettre de tirer la premier carte de la liste des cartes qu'elle contient et de l'affecter a un joueur.
 * Elle possede aussi la methode qui permet de verifier si il y a assez de carte pour pouvoir tirer le nombre de carte demander.
 * 
 * @author Hugo Arthur
 * @see Pile
 *
 */

public class Pioche extends Pile {
	
	/**
	 * Constructeur de la pioche.
	 * La pioche est un jeu de carte de uno complet, de ce fait on construit tout un jeu de carte.
	 */
	
	public Pioche(){
		super();
	}
	
	/**
	 * Permet de distribuer le nombre de cartes entre en parametre au joueur entre en parametre.
	 * C'est une methode utilisee dans les effets de cartes et lorsqu'un joueur ne veut pas ou ne peut pas jouer.
	 * @param joueur1
	 * @param nbrCarte
	 */
	
	public void distribuerCarte(Joueur joueur1, int nbrCarte){
		int i=0;
		for (i=0;i<nbrCarte;i++){
			try{
				Carte c = this.pile.poll();
				joueur1.getCartesEnMain().add(c);
				joueur1.setDisUNO(false);
			}
			catch (NullPointerException e){
				
			}
		}
	}
	
	/**
	 * Cette methode permet de verifier que les cartes que l'on veut tirer sont bien presentes dans la pioche.
	 * Si ce n'est pas le cas le talon est renverse sous la pioche en laissant une carte sur le talon.
	 * @param talon1
	 * @param nbrCarteAVerifier
	 */
	
	public void verifieTalon (Talon talon1, int nbrCarteAVerifier){
		if (this.pile.size()<nbrCarteAVerifier){
			Carte carteDuDessus = talon1.getPile().pollLast();
			while (!talon1.getPile().isEmpty()){
				this.pile.add(talon1.getPile().pollFirst());
			}
			talon1.getPile().add(carteDuDessus);
		}
	}
}

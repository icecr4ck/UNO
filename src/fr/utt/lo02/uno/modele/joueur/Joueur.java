package fr.utt.lo02.uno.modele.joueur;

import java.util.*;

import fr.utt.lo02.uno.modele.carte.*;
import fr.utt.lo02.uno.modele.partie.Partie;
import fr.utt.lo02.uno.modele.pile.*;
/**
 * Cette classe représente un joueur, on declinera les joueurs joués par l'ordinateur avec la class JoueurVirtuel qui héritera de Joueur.
 * Un joueur est définis par son nom, son score et ses status dans la partie. On définit 4 status; le premier correspond au fait que le joueur s'est fait sauter son tour,
 * le second correpond au fait que le joueur bluff ou non, le troisième correspond au fait que le joueur est un joueur virtuel ou non,
 * le dernier correspond au fait que je le joueur est dis Uno ou non.
 * Chaque joueurs comporte aussi une LinkedList de carte correspondant au carte qu'il possède en main. 
 * (On a choisi une LinkedList car elle permet d'avoir un ordonnacement dans les cartes.)
 *  
 * 
 * @author Hugo Arthur
 *
 */

public class Joueur {
	
	private String nomJoueur;
	private int score;
	private boolean passerTour = false;
	private boolean bluff = false;
	private boolean virtuel = false;
	private boolean disUNO = false;
	
	protected LinkedList<Carte> cartesEnMain = new LinkedList<Carte>(); 
	
	/**
	 * Le constructeur de joueur affecte juste le nom du joueur au joueur et initialise son score à 0.
	 * 
	 * @param nomJoueur
	 */
	public Joueur(String nomJoueur){
		this.nomJoueur = nomJoueur;
		this.score = 0;
	}
	
	/**
	 * Cette méthode ne sert que pour les joueurs physiques ou utilisateurs, en effet elle est redéfinis dans la class JoueurVirtuel.
	 * Elle est appellée lorsque le joueur a décidé qu'elle carte il veut jouer. 
	 * Cette méthode est possée sur le talon, supprimer de la main du joueurs et lance l'effet de la carte.
	 * On distingue le fait qu'une carte soit noir ou non. Si elle est noir on ne change pas la couleur du talon car ce phénomène
	 * est préciser dans l'effet des cartes +4 ou Joker.
	 * 
	 * @param carte
	 * @param talon
	 * @param pioche
	 * @param partie
	 */
	public void poserCarte(Carte carte, Talon talon, Pioche pioche, Partie partie){
		talon.getPile().add(carte);
		if (carte.getCouleur()!=4){
			talon.setCouleurTalon(carte.getCouleur());
		}
		carte.effetCarte(talon, pioche, partie);
		this.getCartesEnMain().remove(carte);
		partie.notifyObservers("actualiserPlateau");
	}
	
	/**
	 * Cette méthode permet de modifier l'état disUNO du joueur.
	 * Elle verifie si le joueur peut dire uno, en regardant le nombre de cartes qu'il a en main.
	 */
	public void direUno(){
		if(cartesEnMain.size()<2){
			disUNO=true;
		}
	}
	
	/**
	 * Cette méthode permet à un joueur de dire contre uno à un joueur (ici joueurAttaque).
	 * Elle va regarder l'etat de disUNO du joueur attaqué et en fonction, elle va lui distribuer 2 cartes ou non.
	 * @param joueurAttaque
	 * @param pioche
	 */
	public void contrerUno(Joueur joueurAttaque, Pioche pioche){
		if(!joueurAttaque.isDisUNO() && joueurAttaque.getCartesEnMain().size()<2){
			pioche.distribuerCarte(joueurAttaque, 2);
		}
	}
	/**
	 * Cette méthode permet de rendre visible un joueur en interface console.
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.getNomJoueur());
		return sb.toString();
	} 
	
	/**
	 * Getteur du nom du joueur.
	 * 
	 * @return
	 */
	public String getNomJoueur() {
		return nomJoueur;
	}
	
	/**
	 * Setteur du nom du joueur.
	 * @param nomJoueur
	 */
	
	public void setNomJoueur(String nomJoueur) {
		this.nomJoueur = nomJoueur;
	}

	/**
	 * Getteur du score.
	 * @return
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Setteur du score.
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * Getteur des cartes en mains du joueur.
	 * @return
	 */
	public LinkedList<Carte> getCartesEnMain() {
		return cartesEnMain;
	}
	
	/**
	 * Setteur des cartes en mains du joueur.
	 * @param cartesEnMain
	 */
	public void setCartesEnMain(LinkedList<Carte> cartesEnMain) {
		this.cartesEnMain = cartesEnMain;
	}

	/**
	 * Getteur de l'etat passerTour.
	 * @return
	 */
	public boolean isPasserTour() {
		return passerTour;
	}

	/**
	 * Setteur de l'etat passerTour.
	 * @param passerTour
	 */
	public void setPasserTour(boolean passerTour) {
		this.passerTour = passerTour;
	}
	
	/**
	 * Getteur de l'etat bluff.
	 * @return
	 */
	public boolean isBluff() {
		return bluff;
	}

	/**
	 * Setteur de l'etat bluff.
	 * @param bluff
	 */
	public void setBluff(boolean bluff) {
		this.bluff = bluff;
	}
	
	/**
	 * Getteur de l'etat virtuel.
	 * @return
	 */
	public boolean isVirtuel() {
		return virtuel;
	}

	/**
	 * Setteur de l'etat virtuel
	 * @param virtuel
	 */
	public void setVirtuel(boolean virtuel){
		this.virtuel = virtuel;
	}
	
	/**
	 * Getteur de l'etat disUNO.
	 * @return
	 */
	public boolean isDisUNO() {
		return disUNO;
	}

	/**
	 * Setteur de l'etat disUNO. 
	 * @param disUNO
	 */
	public void setDisUNO(boolean disUNO) {
		this.disUNO = disUNO;
	}

}

package fr.utt.lo02.uno.modele.partie;

import java.util.*;

import fr.utt.lo02.uno.modele.carte.*;
import fr.utt.lo02.uno.modele.joueur.*;
import fr.utt.lo02.uno.modele.pile.Pioche;
import fr.utt.lo02.uno.modele.pile.Talon;
/**
 * Cette classe est la plus importante de l'application. En effet c'est la classe qui repr�sente une partie.
 * Elle poss�de de nombreux attribut qui la caract�rise.
 * Tout d'abord on trouve le nombre de joueurs r�els et le nombre de joueurs virtuels, ainsi que la difficult� de ces joueurs.
 * Il y a aussi, une pioche, un talon, une liste de joueurs, le joueur qui gagne la manche, le joueur qui gagne la partie ainsi que le joueur qui commence la partie. 
 * On trouve aussi des booleans repr�sentants l'�tat de fin de manche, d'un autre manche et du sens horaire dans lequel se d�roule la partie.
 * Ensuite on a des attributs un peu particulier qui vont �tre des objets de la classe Joueur. 
 * Ils vont repr�senter � chaque tour, le joueur qui joue ainsi que le joueur qui sera potentiellement attaqu� par les effets des cartes que le joueur qui joue aura pos�.
 * 
 * 
 * @author Hugo Arthur
 * @see Pioche
 * @see Talon
 * @see Joueur
 */
public class Partie extends Observable implements Runnable{
	private int nbrJoueursVirtuel = 0;
	private int nbrJoueursReel = 0;
	private int difficulte;
	private Pioche pioche;
	private Talon talon;
	private Joueur joueurJoue;
	private Joueur joueurAttaque;
	private Joueur vainqueurManche;
	private Joueur joueurCommence;
	private Joueur vainqueurPartie;
	private boolean finManche;
	private boolean sensHoraire;
	private boolean autreManche;

	private ArrayList<Joueur> joueurs;
	private ArrayList<Observer> listObserver = new ArrayList<Observer>(); 
	
	public Partie(){
		this.joueurs = new ArrayList<Joueur>();
		this.autreManche = true;
	}
	
	/**
	 * Cette methode affecte les aux attributs difficulte, nbrJoueursVirtuel, nbrJoueursReel.
	 * Ces attributs ne seront pas modifier au cours de la partie.
	 * @param d
	 * @param njv
	 * @param njr
	 */
	public void reglerParametres(int d, int njv, int njr){
		this.difficulte = d;
		this.nbrJoueursVirtuel = njv;
		this.nbrJoueursReel = njr;
		this.determinerNombreJoueurs();
	}
	
	/**
	 * Cette m�thode va cr�er les joueurs en cons�quent des nombres de joueurs r�els et virtuels voulu.
	 */
	public void determinerNombreJoueurs(){
		for (int i=0;i<this.nbrJoueursVirtuel;i++){
			Joueur joueur = new JoueurVirtuel(JoueurVirtuel.jv[i], this.difficulte);
			this.joueurs.add(joueur);
		}
		for (int i=0;i<this.nbrJoueursReel;i++){
			Joueur joueur = new Joueur("Joueur " + (i+1));
			this.joueurs.add(joueur);
		}
		this.notifyObservers("joueurs");
	}
	
	/**
	 * Cette methode va supprimer les cartes des joueurs de la partie.
	 */
	private void supprimerCartesEnMain(){
		ListIterator<Joueur> iJoueurs = joueurs.listIterator();
		while (iJoueurs.hasNext()){
			Joueur joueur = iJoueurs.next();
			joueur.getCartesEnMain().removeAll(joueur.getCartesEnMain());
		}
	}
	
	/**
	 * Cette methode est appell�e uniquement dans la methode determinerPremierJoueurAJouer().
	 * Elle permet de determiner qui a la plus grande carte entre tous les joueurs de la partie.
	 * Elle renvoie le joueur qui � la plus grande carte.
	 * @return
	 */
	private Joueur joueurCommence(){
		int nombreMax = this.joueurs.get(0).getCartesEnMain().getFirst().getNumero();
		this.joueurCommence = this.joueurs.get(0);
		ListIterator<Joueur> iJoueurs = this.joueurs.listIterator();
		while(iJoueurs.hasNext()){
			Joueur joueur = iJoueurs.next();
			if (joueur.getCartesEnMain().getFirst().getNumero() > nombreMax){
				nombreMax = joueur.getCartesEnMain().getFirst().getNumero();
				joueurCommence = joueur;
			}
		}
		return joueurCommence;
	}
	
	/**
	 * Cette m�thode permet de d�terminer qu'elle est le joueur qui joue en premier.
	 * Pour cela on a �tablis la r�gle suivante : on distribue au d�but de la manche une carte � tous le monde, on compare
	 * les numero de ces cartes et le joueur qui � le plus grand numero gagne.
	 * Ici on distribue donc une carte pour chaque joueur. Ensuite on appelle la m�thode joueurCommence qui va d�termnier qu'elle est le joueur qui a la plus grande carte.
	 * Ensuite on repose les cartes dans la pioche.
	 * La m�thode renvoie le joueur qui commence.
	 * @return
	 */
	public Joueur determinerPremierJoueurAJouer(){
		ListIterator<Joueur> iJoueurs = this.joueurs.listIterator();
		while(iJoueurs.hasNext()){
			Joueur joueur = iJoueurs.next();
			pioche.distribuerCarte(joueur, 1);
		}
		joueurCommence = this.joueurCommence();
		this.notifyObservers("joueurcommence");
		while(iJoueurs.hasPrevious()){
			this.pioche.getPile().add(iJoueurs.previous().getCartesEnMain().poll());
		}
		return joueurCommence;
	}
	
	/**
	 * Cette m�thode permet d'initialiser le talon au d�but de chaque manche.
	 * Elle consiste a mettre la premi�re carte de la pioche sur le talon.
	 * Si cette carte est une carte +4 on repioche jusqu'a temps qu'on obtienne une carte diff�rente d'une carte +4.
	 * Si la carte pioch�e est une carte Joker, on determine la couleur du talon al�atoirement.
	 * Si la carte pioch�e est une carte Classique, on change la couleur du talon avec la couleur de la carte.
	 * Si la carte pioch�e est une carte inverseSens, on change la couleur du talon est on chnage le sens de la manche.
	 * Sinon on change la couleur du talon avec la couleur de la carte pioch�e et on lance l'effet de la carte.
	 */
	public void initialiserTalon(){
		Carte carteTalon = pioche.getPile().poll();
		if (carteTalon.getClass() == CartePlus4.class){
			while (carteTalon.getClass() == CartePlus4.class){
				talon.getPile().add(carteTalon);
				carteTalon = pioche.getPile().poll();
			}
		}
		talon.getPile().add(carteTalon);
		if (carteTalon.getClass() == CarteJoker.class){
			talon.setCouleurTalon((int)(Math.random()*3));
			
		}
		else if (carteTalon.getClass() == CarteClassique.class){
			talon.setCouleurTalon(carteTalon.getCouleur());
		}
		else if (carteTalon.getClass() == CarteInverseSens.class){
			talon.setCouleurTalon(carteTalon.getCouleur());
			this.joueurJoue = this.joueurPrecedent(joueurJoue);
			carteTalon.effetCarte(talon, pioche, this);
		}
		else {
			this.joueurAttaque = this.joueurJoue;
			talon.setCouleurTalon(carteTalon.getCouleur());
			carteTalon.effetCarte(talon, pioche, this);
		}
	}
	
	/**
	 * Cette m�thode permet de d�terminer qui est le joueur suivant de la liste de la joueurs.
	 * Si le joueur qui joue est le joueur � la fin de la liste des joueurs, on attribue au joueur suivant, le joueur de d�but de liste.
	 * Sinon on attribue au joueur suivant, le joueur qui suit sur la liste de joueurs.
	 * @param joueur
	 * @return
	 */
	public Joueur joueurSuivant(Joueur joueur){
		Joueur joueurSuivant = null;
		int index = this.joueurs.indexOf(joueur);
		if (index == this.joueurs.size() - 1){
			joueurSuivant = this.joueurs.get(0);
		}
		else{
			joueurSuivant = this.joueurs.get(index + 1);
		}
		return joueurSuivant;
	}
	
	/**
	 * Cette m�thode permet de d�terminer qui est le joueur pr�c�dent de la liste de la joueurs.
	 * Si le joueur qui joue est le joueur au d�but de la liste des joueurs, on attribue au joueur pr�c�dent, le joueur de fin de liste.
	 * Sinon on attribue au joueur pr�c�dent, le joueur qui pr�c�de sur la liste de joueurs.
	 * @param joueur
	 * @return
	 */
	public Joueur joueurPrecedent(Joueur joueur){
		Joueur joueurPrecedent = null;
		int index = this.joueurs.indexOf(joueur);
		if (index == 0){
			joueurPrecedent = this.joueurs.get(this.joueurs.size() - 1);
		}
		else{
			joueurPrecedent = this.joueurs.get(index - 1);
		}
		return joueurPrecedent;
	}
	
	/**
	 * Cette m�thode permet de v�rifier si le joueur qui est entrain de jouer bluff (elle est appell�e uniquement si le joueur � poser un +4).
	 * On initialise l'�tat de bluff du joueur qui joue � false.
	 * Ensuite on regarde si il poss�de une carte qu'il peut jouer. Si il en a une on modifie son �tat bluff � true.
	 * Par la suite, si il a bluff� on lui distribue 6 cartes et on lui redonne le +4 qu'il a poser.
	 * Si il n'as pas bluffer on distribue 2 cartes aux joueur suivant (ou le joueur attaque).
	 * @param talon
	 * @param pioche
	 */
	public void verifieBluff(Talon talon, Pioche pioche){
		this.joueurJoue.setBluff(false);
		ListIterator<Carte> iCarte = this.joueurJoue.getCartesEnMain().listIterator();
		while(iCarte.hasNext()){
			if(iCarte.next().determinerCarteJouable(talon)){
				this.joueurJoue.setBluff(true);
			}
		}
		if (this.joueurJoue.isBluff()){
			pioche.verifieTalon(talon, 6);
			pioche.distribuerCarte(this.joueurJoue, 6);
			this.joueurJoue.getCartesEnMain().add(talon.getPile().pollLast());
			}
		if (!this.joueurJoue.isBluff()){
			pioche.verifieTalon(talon, 2);
			pioche.distribuerCarte(this.joueurAttaque, 2);
		}
	}
	
	/**
	 * Cette m�thode permet de d�rouler un tour de jeu.
	 * Au debut de la m�thode on d�termine le joueur qui sera potentiellement attaque. On modifie directement l'attribut joueurAttaque.
	 * Ensuite on regarde si le joueur qui joue � son tour passer. Si oui on termine son tour, si non on va regarder si le joueur � des cartes jouables;
	 * si oui on distingue deux cas : les joueurs virtuel joueront leur tour automatiquement et pour les joueurs virtuels on demanderas les actions � r�aliser.
	 * Si il n'as pas de carte jouable, on lui en fait piocher une et on applique le meme proc�d�e que si il pouvait jouer directement. 
	 * Et si il ne peut tuoujours pas jouer la carte pioch�e, on termine son tour.
	 * A la fin de chaque tour on determine qui sera le joueur qui joueras apres.
	 */
	public void jouerTour(){
		if (this.sensHoraire) {this.joueurAttaque = this.joueurSuivant(joueurJoue);}
		else {this.joueurAttaque = this.joueurPrecedent(joueurJoue);}
		if (this.joueurJoue.isPasserTour()){
			this.notifyObservers("passerTour");
			this.joueurJoue.setPasserTour(false);
		}
		else {
			this.notifyObservers("declareJoueur");
			if (this.joueurPeutJouer()){
				if(joueurJoue.isVirtuel()){
					this.joueurJoue.poserCarte(null, talon, pioche, this);
				}
				else{
					this.notifyObservers("joue");
				}
				if (this.getJoueurJoue().getCartesEnMain().isEmpty()){
					this.setVainqueurManche(this.getJoueurJoue());
					this.finManche = true;
				}
			}
			else {
				pioche.verifieTalon(talon, 1);
				pioche.distribuerCarte(this.getJoueurJoue(), 1);
				this.notifyObservers("actualiserPlateau");
				if (this.joueurPeutJouer()){
					if(joueurJoue.isVirtuel()){
						this.joueurJoue.poserCarte(null, talon, pioche, this);
					}
					else{
						this.notifyObservers("joue");
					}
				}
			}
		}
		if (this.sensHoraire) {this.joueurJoue = this.joueurSuivant(joueurJoue);}
		else {this.joueurJoue = this.joueurPrecedent(joueurJoue);}
	}
	
	/**
	 * Cette m�thode renvoie true si le joueur peut jouer et false si il ne peut pas.
	 * Elle regarde si le joueur qui joue poss�de une carte compatible avec la derniere carte du talon,
	 * si il en possede une le joueur peut jouer sinon il ne peut pas.
	 * @return
	 */
	public boolean joueurPeutJouer(){
		boolean peutJouer = false;
		ListIterator<Carte> iCartesEnMain = this.joueurJoue.getCartesEnMain().listIterator();
		while(iCartesEnMain.hasNext()){
			if (iCartesEnMain.next().determinerCarteJouable(talon)){
				peutJouer = true;
			}
		}
		return peutJouer;	
	}
	
	/**
	 * Cette m�thode est appell� par les effets des cartes +2, +4 ainsi que passeTour.
	 * Elle permet de changer l'etat passerTour du joueur suivant en fonction de la carte posser.
	 * @param classeCarteTalon
	 */
	public void passerTour(Class<? extends Carte> classeCarteTalon) {
		if (classeCarteTalon==CartePasseTour.class || classeCarteTalon==CartePlus2.class || classeCarteTalon==CartePlus4.class){
			this.joueurAttaque.setPasserTour(true);
		}
	}
	
	/**
	 * Cette m�thode va envoyer un boolean qui va indiquer si c'est la fin de la manche ou non.
	 * Pour ca elle regarde pour chaque joueur si il leur reste des cartes. Si un joueur n'as plus de carte, la manche est finie et la methode renvoie true.
	 * @return
	 */
	public boolean isFinManche() {
		ListIterator<Joueur> iJoueurs = joueurs.listIterator();
		while (iJoueurs.hasNext()){
			if (iJoueurs.next().getCartesEnMain().isEmpty()){
				this.finManche = true;
			}
		}
		return finManche;
	}
	
	/**
	 * Cette m�thode est appell� en fin de manche.
	 * Elle sert a compter le nombre de points que le vainqueur de la manche va gagner.
	 * Elle va regarder, pour chaque joueur de la partie, les cartes qu'il lui reste en main, et pour chaque carte elle va additionner
	 * le nombre de points qu'elle vaut, a une variable score.
	 * Une fois toutes les cartes de tous les joueurs comptabilis�es, la m�thode incremente du score calcul� le nombre de points du vainqueur.
	 */
	public void compterScore(){
		int scoreJoueur=0;
		int indexVainqueur = 0;
		ListIterator<Joueur> iJoueurs = joueurs.listIterator();
		while(iJoueurs.hasNext()){
			Joueur joueur = iJoueurs.next();
			if (joueur.getCartesEnMain().size() != 0){
				ListIterator<Carte> iCarte = joueur.getCartesEnMain().listIterator();
				while (iCarte.hasNext()){	
					Carte carte1 = iCarte.next();
					scoreJoueur = scoreJoueur + carte1.getPoints();
				}
			}
		}
		indexVainqueur = this.joueurs.indexOf(this.getVainqueurManche());
		this.joueurs.get(indexVainqueur).setScore(this.joueurs.get(indexVainqueur).getScore() + scoreJoueur);
		this.supprimerCartesEnMain();
	}
	
	/**
	 * Cette m�thode va regarder tous les joueurs de la partie et va determiner celui qui a le plus de points.
	 * Elle va modifier en cons�quent l'attribut de la partie vainqueurPartie.
	 */
	public void determinerVainqueur(){
		ListIterator<Joueur> iJoueurs = joueurs.listIterator();
		vainqueurPartie = iJoueurs.next();
		while(iJoueurs.hasNext()){
			Joueur joueur = iJoueurs.next();
			if (joueur.getScore() > vainqueurPartie.getScore()){
				vainqueurPartie = joueur;
			}
		}
	}
	
	/**
	 * Cette m�thode v�rifie pour chaque fin de manche, si un joueur a depass� ou a un nombre de points de 500 points.
	 * Si c'est le cas, la methode renvoie true, sinon elle renvoie false.
	 * @param joueurs
	 * @return
	 */
	public boolean finPartie(List<Joueur> joueurs){
		ListIterator<Joueur> iJoueurs = joueurs.listIterator();
		while(iJoueurs.hasNext()){
			if (iJoueurs.next().getScore() >= 500){
				return true;
			}
		}
		return false;
	}
	
	public void addObserver(Observer obs) {
	    this.listObserver.add(obs);
	}

	public void notifyObservers(Object arg) {
		for (Observer obs : listObserver){
			obs.update(this, arg);
		}
	}

	public void deleteObserver(Observer o) {
	    listObserver.remove(o);
	}  

	/**
	 * Getteur de la LinkedList joueurs.
	 * @return
	 */
	public List<Joueur> getJoueurs() {
		return joueurs;
	}

	/**
	 * Getteur de l'�tat autreManche.
	 * @return
	 */
	public boolean isAutreManche() {
		return autreManche;
	}

	/**
	 * Setteur de l'�tat autreManche.
	 * @param autreManche
	 */
	public void setAutreManche(boolean autreManche) {
		this.autreManche = autreManche;
	}

	/**
	 * Getteur de joueurCommence.
	 * @return
	 */
	public Joueur getJoueurCommence() {
		return joueurCommence;
	}

	/**
	 * Setteur de joueurCommence.
	 * @param joueurCommence
	 */
	public void setJoueurCommence(Joueur joueurCommence) {
		this.joueurCommence = joueurCommence;
	}

	/**
	 * Getteur de vainqueurManche.
	 * @return
	 */
	public Joueur getVainqueurManche() {
		return vainqueurManche;
	}

	/**
	 * Setteur de vainqueurManche.
	 * @param vainqueurManche
	 */
	public void setVainqueurManche(Joueur vainqueurManche) {
		this.vainqueurManche = vainqueurManche;
	}
	
	/**
	 * Getteur de joueurJoue.
	 * @return
	 */
	public Joueur getJoueurJoue() {
		return joueurJoue;
	}

	/**
	 * Setteur de joueurJoue.
	 * @param joueurJoue
	 */
	public void setJoueurJoue(Joueur joueurJoue) {
		this.joueurJoue = joueurJoue;
	}

	/**
	 * Getteur de joueurAttaque.
	 * @return
	 */
	public Joueur getJoueurAttaque() {
		return joueurAttaque;
	}

	/**
	 * Setteur de joueurAttaque.
	 * @param joueurAttaque
	 */
	public void setJoueurAttaque(Joueur joueurAttaque) {
		this.joueurAttaque = joueurAttaque;
	}

	/**
	 * Setteur de l'�tat sensHoraire.
	 * @return
	 */
	public boolean isSensHoraire() {
		return sensHoraire;
	}

	/**
	 * Setteur de l'�tat sensHoraire.
	 * @param sensHoraire
	 */
	public void setSensHoraire(boolean sensHoraire) {
		this.sensHoraire = sensHoraire;
	}

	/**
	 * Getteur de pioche.
	 * @return
	 */
	public Pioche getPioche() {
		return pioche;
	}

	/**
	 * Setteur de pioche.
	 * @param pioche
	 */
	public void setPioche(Pioche pioche) {
		this.pioche = pioche;
	}

	/**
	 *Getteur de Talon. 
	 * @return
	 */
	public Talon getTalon() {
		return talon;
	}

	/**
	 * Setteur de Talon.
	 * @param talon
	 */
	public void setTalon(Talon talon) {
		this.talon = talon;
	}

	public void run() {
		this.pioche = new Pioche();
		this.talon = new Talon();
		this.notifyObservers("piles");
		this.joueurJoue = this.determinerPremierJoueurAJouer();
		this.notifyObservers("joueurcommence");
		this.supprimerCartesEnMain();
		this.finManche = false;
		this.sensHoraire = true;
		ListIterator<Joueur> iJoueurs = this.joueurs.listIterator();
		while (iJoueurs.hasNext()){
			Joueur joueur = iJoueurs.next();
			pioche.verifieTalon(talon, 7);
			pioche.distribuerCarte(joueur, 7);
		}
		this.initialiserTalon();
		this.notifyObservers("distribuer");
		while (!this.isFinManche()){
			this.jouerTour();
		}
		this.compterScore();
		this.notifyObservers("afficheVainqueurEtScore");
	}

}

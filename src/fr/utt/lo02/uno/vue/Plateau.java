package fr.utt.lo02.uno.vue;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import fr.utt.lo02.uno.controleur.Controleur;
import fr.utt.lo02.uno.modele.carte.*;
import fr.utt.lo02.uno.modele.joueur.*;
import fr.utt.lo02.uno.modele.partie.Partie;
import fr.utt.lo02.uno.vue.panneaux.*;

public class Plateau extends JPanel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Partie partie;
	private Controleur controleur;
	
	private JFrame frame;
	private PanelJoueur pjJoue;
	private PanelPile piles;
	private ArrayList<PanelJoueur> pj = new ArrayList<PanelJoueur>();
	
	public Plateau(Partie p, Controleur c){
		super();

		this.partie = p;
		this.partie.addObserver(this);
		this.controleur = c;
		
		this.frame = new JFrame();
		this.frame.setTitle("Jeu de UNO");
		this.frame.setSize(1500, 1000);
		this.frame.setLocationRelativeTo(null);               
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setLayout(null);
		
		this.frame.setContentPane(this);
	}
	
	public void afficherJeu(PanelJoueur pj){
		pj.setCartesVisibles(true);
	}
	
	public void afficherPiles(){
		this.piles = new PanelPile();
		piles.setBounds(600, 320, 300, 200);
		this.add(piles);
		this.frame.setContentPane(this);
	}
	
	public void afficherJoueurs(int nbrJoueurs){
		ListIterator<Joueur> iJoueurs = partie.getJoueurs().listIterator();
		while (iJoueurs.hasNext()){
			pj.add(new PanelJoueur(iJoueurs.next()));
		}
		if (nbrJoueurs == 2){
			pj.get(0).setBounds(500, 0, 500, 200);
			this.add(pj.get(0));
			pj.get(1).setBounds(500, 550, 500, 200);
			this.add(pj.get(1));
		}
		if (nbrJoueurs == 3){
			pj.get(0).setBounds(0, 0, 500, 200);
			this.add(pj.get(0));
			pj.get(1).setBounds(700, 0, 500, 200);
			this.add(pj.get(1));
			pj.get(2).setBounds(500, 550, 500, 200);
			this.add(pj.get(2));
		}
		if (nbrJoueurs == 4){
			pj.get(0).setBounds(0, 300, 500, 200);
			this.add(pj.get(0));
			pj.get(1).setBounds(500, 0, 500, 200);
			this.add(pj.get(1));
			pj.get(2).setBounds(1000, 300, 500, 200);
			this.add(pj.get(2));
			pj.get(3).setBounds(500, 550, 500, 200);
			this.add(pj.get(3));
		}
		if (nbrJoueurs == 5){
			pj.get(0).setBounds(0, 300, 500, 200);
			this.add(pj.get(0));
			pj.get(1).setBounds(0, 0, 500, 200);
			this.add(pj.get(1));
			pj.get(2).setBounds(750, 0, 500, 200);
			this.add(pj.get(2));
			pj.get(3).setBounds(1000, 300, 500, 200);
			this.add(pj.get(3));
			pj.get(4).setBounds(500, 550, 500, 200);
			this.add(pj.get(4));
		}
		if (nbrJoueurs == 6){
			pj.get(0).setBounds(0, 300, 500, 200);
			this.add(pj.get(0));
			pj.get(1).setBounds(0, 0, 500, 200);
			this.add(pj.get(1));
			pj.get(2).setBounds(500, 0, 500, 200);
			this.add(pj.get(2));
			pj.get(3).setBounds(1000, 0, 500, 200);
			this.add(pj.get(3));
			pj.get(4).setBounds(1000, 300, 500, 200);
			this.add(pj.get(4));
			pj.get(5).setBounds(500, 550, 500, 200);
			this.add(pj.get(5));
		}
		if (nbrJoueurs == 7){
			pj.get(0).setBounds(0, 400, 500, 200);
			this.add(pj.get(0));
			pj.get(1).setBounds(0, 200, 500, 200);
			this.add(pj.get(1));
			pj.get(2).setBounds(0, 0, 500, 200);
			this.add(pj.get(2));
			pj.get(3).setBounds(1000, 0, 500, 200);
			this.add(pj.get(3));
			pj.get(4).setBounds(1000, 200, 500, 200);
			this.add(pj.get(4));
			pj.get(5).setBounds(1000, 400, 500, 200);
			this.add(pj.get(5));
			pj.get(6).setBounds(500, 550, 500, 200);
			this.add(pj.get(6));
		}
		if (nbrJoueurs == 8){
			pj.get(0).setBounds(0, 400, 500, 200);
			this.add(pj.get(0));
			pj.get(1).setBounds(0, 200, 500, 200);
			this.add(pj.get(1));
			pj.get(2).setBounds(0, 0, 500, 200);
			this.add(pj.get(2));
			pj.get(3).setBounds(500, 0, 500, 200);
			this.add(pj.get(3));
			pj.get(4).setBounds(1000, 0, 500, 200);
			this.add(pj.get(4));
			pj.get(5).setBounds(1000, 200, 500, 200);
			this.add(pj.get(5));
			pj.get(6).setBounds(1000, 400, 500, 200);
			this.add(pj.get(6));
			pj.get(7).setBounds(500, 550, 500, 200);
			this.add(pj.get(7));
		}
		if (nbrJoueurs == 9){
			pj.get(0).setBounds(0, 400, 500, 200);
			this.add(pj.get(0));
			pj.get(1).setBounds(0, 200, 500, 200);
			this.add(pj.get(1));
			pj.get(2).setBounds(0, 0, 500, 200);
			this.add(pj.get(2));
			pj.get(3).setBounds(500, 0, 500, 200);
			this.add(pj.get(3));
			pj.get(4).setBounds(1000, 0, 500, 200);
			this.add(pj.get(4));
			pj.get(5).setBounds(1000, 200, 500, 200);
			this.add(pj.get(5));
			pj.get(6).setBounds(1000, 400, 500, 200);
			this.add(pj.get(6));
			pj.get(7).setBounds(1000, 600, 500, 200);
			this.add(pj.get(7));
			pj.get(8).setBounds(500, 550, 500, 200);
			this.add(pj.get(8));
		}
		if (nbrJoueurs == 10){
			pj.get(0).setBounds(0, 600, 500, 200);
			this.add(pj.get(0));
			pj.get(1).setBounds(0, 400, 500, 200);
			this.add(pj.get(1));
			pj.get(2).setBounds(0, 200, 500, 200);
			this.add(pj.get(2));
			pj.get(3).setBounds(0, 0, 500, 200);
			this.add(pj.get(3));
			pj.get(4).setBounds(500, 0, 500, 200);
			this.add(pj.get(4));
			pj.get(5).setBounds(1000, 0, 500, 200);
			this.add(pj.get(5));
			pj.get(6).setBounds(1000, 200, 500, 200);
			this.add(pj.get(6));
			pj.get(7).setBounds(1000, 400, 500, 200);
			this.add(pj.get(7));
			pj.get(8).setBounds(1000, 600, 500, 200);
			this.add(pj.get(8));
			pj.get(9).setBounds(500, 550, 500, 200);
			this.add(pj.get(9));
		}
		this.frame.setContentPane(this);
	}
	
	public Image verifierCarte(Carte c){
		Image carte = null;
		if (c.getClass() == CarteClassique.class){
			switch(c.getNumero()){
				case CarteClassique.ZERO:
					switch(c.getCouleur()){
						case Carte.BLEU:
							carte = piles.getCartes().get(28);
							break;
						case Carte.VERT:
							carte = piles.getCartes().get(2);
							break;
						case Carte.JAUNE:
							carte = piles.getCartes().get(15);
							break;
						case Carte.ROUGE:
							carte = piles.getCartes().get(41);
							break;
					}
					break;
				case CarteClassique.UN:
					switch(c.getCouleur()){
						case Carte.BLEU:
							carte = piles.getCartes().get(29);
							break;
						case Carte.VERT:
							carte = piles.getCartes().get(3);
							break;
						case Carte.JAUNE:
							carte = piles.getCartes().get(16);
							break;
						case Carte.ROUGE:
							carte = piles.getCartes().get(42);
							break;
					}
					break;
				case CarteClassique.DEUX:
					switch(c.getCouleur()){
						case Carte.BLEU:
							carte = piles.getCartes().get(30);
							break;
						case Carte.VERT:
							carte = piles.getCartes().get(4);
							break;
						case Carte.JAUNE:
							carte = piles.getCartes().get(17);
							break;
						case Carte.ROUGE:
							carte = piles.getCartes().get(43);
							break;
					}
					break;
				case CarteClassique.TROIS:
					switch(c.getCouleur()){
						case Carte.BLEU:
							carte = piles.getCartes().get(31);
							break;
						case Carte.VERT:
							carte = piles.getCartes().get(5);
							break;
						case Carte.JAUNE:
							carte = piles.getCartes().get(18);
							break;
						case Carte.ROUGE:
							carte = piles.getCartes().get(44);
							break;
					}
					break;
				case CarteClassique.QUATRE:
					switch(c.getCouleur()){
						case Carte.BLEU:
							carte = piles.getCartes().get(32);
							break;
						case Carte.VERT:
							carte = piles.getCartes().get(6);
							break;
						case Carte.JAUNE:
							carte = piles.getCartes().get(19);
							break;
						case Carte.ROUGE:
							carte = piles.getCartes().get(45);
							break;
					}
					break;
				case CarteClassique.CINQ:
					switch(c.getCouleur()){
						case Carte.BLEU:
							carte = piles.getCartes().get(33);
							break;
						case Carte.VERT:
							carte = piles.getCartes().get(7);
							break;
						case Carte.JAUNE:
							carte = piles.getCartes().get(20);
							break;
						case Carte.ROUGE:
							carte = piles.getCartes().get(46);
							break;
					}
					break;
				case CarteClassique.SIX:
					switch(c.getCouleur()){
						case Carte.BLEU:
							carte = piles.getCartes().get(34);
							break;
						case Carte.VERT:
							carte = piles.getCartes().get(8);
							break;
						case Carte.JAUNE:
							carte = piles.getCartes().get(21);
							break;
						case Carte.ROUGE:
							carte = piles.getCartes().get(47);
							break;
					}
					break;
				case CarteClassique.SEPT:
					switch(c.getCouleur()){
						case Carte.BLEU:
							carte = piles.getCartes().get(35);
							break;
						case Carte.VERT:
							carte = piles.getCartes().get(9);
							break;
						case Carte.JAUNE:
							carte = piles.getCartes().get(22);
							break;
						case Carte.ROUGE:
							carte = piles.getCartes().get(48);
							break;
					}
					break;
				case CarteClassique.HUIT:
					switch(c.getCouleur()){
						case Carte.BLEU:
							carte = piles.getCartes().get(36);
							break;
						case Carte.VERT:
							carte = piles.getCartes().get(10);
							break;
						case Carte.JAUNE:
							carte = piles.getCartes().get(23);
							break;
						case Carte.ROUGE:
							carte = piles.getCartes().get(49);
							break;
					}
					break;
				case CarteClassique.NEUF:
					switch(c.getCouleur()){
						case Carte.BLEU:
							carte = piles.getCartes().get(37);
							break;
						case Carte.VERT:
							carte = piles.getCartes().get(11);
							break;
						case Carte.JAUNE:
							carte = piles.getCartes().get(24);
							break;
						case Carte.ROUGE:
							carte = piles.getCartes().get(50);
							break;
					}
					break;
				}
			}
			if (c.getClass() == CartePasseTour.class){
			switch(c.getCouleur()){
				case Carte.BLEU:
					carte = piles.getCartes().get(38);
					break;
				case Carte.VERT:
					carte = piles.getCartes().get(12);
					break;
				case Carte.JAUNE:
					carte = piles.getCartes().get(25);
					break;
				case Carte.ROUGE:
					carte = piles.getCartes().get(51);
					break;
				}
			}
			if (c.getClass() == CarteInverseSens.class){
				switch(c.getCouleur()){
					case Carte.BLEU:
						carte = piles.getCartes().get(39);
						break;
					case Carte.VERT:
						carte = piles.getCartes().get(13);
						break;
					case Carte.JAUNE:
						carte = piles.getCartes().get(26);
						break;
					case Carte.ROUGE:
						carte = piles.getCartes().get(52);
						break;
				}
			}
			if (c.getClass() == CartePlus2.class){
				switch(c.getCouleur()){
					case Carte.BLEU:
						carte = piles.getCartes().get(40);
						break;
					case Carte.VERT:
						carte = piles.getCartes().get(14);
						break;
					case Carte.JAUNE:
						carte = piles.getCartes().get(27);
						break;
					case Carte.ROUGE:
						carte = piles.getCartes().get(53);
						break;
				}
			}
			if (c.getClass() == CarteJoker.class){
				carte = piles.getCartes().get(1);
			}
			if (c.getClass() == CartePlus4.class){
				carte = piles.getCartes().get(0);
			}
		return carte;
	}
	
	public void afficherCartes(Joueur joueur){
		ListIterator<PanelJoueur> iPj = this.pj.listIterator();
		while (iPj.hasNext()){
			PanelJoueur j = iPj.next();
			if (j.getNomJoueur() == joueur.getNomJoueur()){
				ListIterator<Carte> iCartes = joueur.getCartesEnMain().listIterator();
				while (iCartes.hasNext()){
					j.piocher(this.verifierCarte(iCartes.next()));
				}
			}
		}
	}
	
	public void afficherJoueurCommence(){
		ListIterator<PanelJoueur> iPj = pj.listIterator();
		while (iPj.hasNext()){
			iPj.next().setCartesVisibles(true);
		}
		ListIterator<Joueur> iJoueurs = partie.getJoueurs().listIterator();
		while (iJoueurs.hasNext()){
			this.afficherCartes(iJoueurs.next());
		}
		JOptionPane.showMessageDialog(null, partie.getJoueurCommence().getNomJoueur() + " commence cette manche !", "Qui Commence ?", JOptionPane.INFORMATION_MESSAGE);
		while (iPj.hasPrevious()){
			iPj.previous().retirerTout();
		}
		this.frame.setContentPane(this);
	}
	
	public void afficherDistribution(){
		ListIterator<PanelJoueur> iPj = pj.listIterator();
		while (iPj.hasNext()){
			iPj.next().setCartesVisibles(false);
		}
		ListIterator<Joueur> iJoueurs = partie.getJoueurs().listIterator();
		while (iJoueurs.hasNext()){
			this.afficherCartes(iJoueurs.next());
		}
		this.piles.getTalon().add(verifierCarte(partie.getTalon().getPile().getLast()));
		this.frame.setContentPane(this);
	}
	
	public void choisirCarte(){
		this.changerVisibiliteCartes(true);
		String[] reponse ={"Piocher","Jouer"};
		int choix = (int) JOptionPane.showOptionDialog(null, 
			      "Voulez vous piocher ou poser une carte ?",
			      "Piocher ou poser ?",
			      JOptionPane.YES_NO_OPTION,
			      JOptionPane.QUESTION_MESSAGE,
			      null,
			      reponse,
			      null);
		if(choix == 0){
			controleur.controlePioche();
			this.actualiserPlateau();
		}
		if(choix == 1){
			Carte carte = (Carte) JOptionPane.showInputDialog(null, "Quelle carte voulez vous jouer ?", "C'est votre tour " + partie.getJoueurJoue(), JOptionPane.QUESTION_MESSAGE, null, partie.getJoueurJoue().getCartesEnMain().toArray(), null);
			controleur.controleCarte(carte);
		}
		controleur.controleUNO();
		this.changerVisibiliteCartes(false);
	}
	
	public void supprimerJeu(Joueur joueur){
		ListIterator<PanelJoueur> iPj = pj.listIterator();
		while (iPj.hasNext()){
			PanelJoueur pj = iPj.next();
			if (pj.getNomJoueur() == joueur.getNomJoueur()){
				pj.getJeu().removeAll(pj.getJeu());
			}
		}
	}
	
	public void actualiserPlateau(){
		ListIterator<Joueur> iJoueur = partie.getJoueurs().listIterator();
		while (iJoueur.hasNext()){
			Joueur j = iJoueur.next();
			this.supprimerJeu(j);
			this.afficherCartes(j);
		}
		this.piles.getTalon().removeAll(piles.getTalon());
		this.piles.getTalon().add(this.verifierCarte(partie.getTalon().getPile().getLast()));
		this.frame.setContentPane(this);
	}
	
	public void choisirCouleur(){
		String couleur = (String) JOptionPane.showInputDialog(null, "Quelle couleur choisissez vous ?", "Choix de couleur", JOptionPane.QUESTION_MESSAGE, null, Carte.COULEUR, Carte.COULEUR[0]);
		controleur.controleCouleur(couleur);
	}
	
	public void changerVisibiliteCartes(Boolean visibles){
		ListIterator<PanelJoueur> iPj = pj.listIterator();
		while (iPj.hasNext()){
			PanelJoueur pj = iPj.next();
			if (pj.getNomJoueur() == partie.getJoueurJoue().getNomJoueur()){
				pj.setCartesVisibles(visibles);
			}
		}
		this.frame.setContentPane(this);
	}
	
	public void afficherScore(){
		
	}
	
	public void afficherNouvelleManche(){
		int nm = JOptionPane.showConfirmDialog(null, "Voulez-vous faire une autre manche ?", "Autre Manche ?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		controleur.nouvelleManche(nm);
	}
	
	public void paintComponent(Graphics g){
	    try {
	      Image img = ImageIO.read(new File("UNO_Plateau.jpg"));
	      g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	}

	public void update(Observable o, Object arg) {
		if (arg == "joueurs"){
			this.afficherJoueurs(partie.getJoueurs().size());
		}
		if (arg == "piles"){
			this.afficherPiles();
		}
		if (arg == "joueurcommence"){
			this.afficherJoueurCommence();
		}
		if (arg == "distribuer"){
			this.afficherDistribution();
		}
		if (arg == "passerTour"){
			JOptionPane.showMessageDialog(null, "Le tour de " + partie.getJoueurJoue() + " est passe !", "A qui de jouer ?", JOptionPane.INFORMATION_MESSAGE);
		}
		if (arg == "declareJoueur"){
			String[] action = {"Dire contre UNO", "Ne rien faire"};
		    int reponseCO=JOptionPane.showOptionDialog(null, 
		      "C'est le tour de "+ partie.getJoueurJoue()+ "\nQue faire ?",
		      "Action",
		      JOptionPane.YES_NO_OPTION,
		      JOptionPane.QUESTION_MESSAGE,
		      null,
		      action,
		      action[1]);
		    if(reponseCO==0){
		    	controleur.controleContreUNO();
		    }
		}
		if (arg == "joue"){
			this.choisirCarte();
		}
		if (arg == "actualiserPlateau"){
			this.actualiserPlateau();
		}
		if (arg == "changementCouleur"){
			this.choisirCouleur();
			JOptionPane.showMessageDialog(null, "Nouvelle couleur du talon : " + partie.getTalon().afficherCouleurTalon(), "Couleur du talon", JOptionPane.INFORMATION_MESSAGE);
		}
		if (arg == "afficherCouleur"){
			JOptionPane.showMessageDialog(null, "Nouvelle couleur du talon : " + partie.getTalon().afficherCouleurTalon(), "Couleur du talon", JOptionPane.INFORMATION_MESSAGE);
		}
		if (arg == "demandeBluff"){
			
		}
		if(arg == "afficheVainqueurEtScore"){
			JOptionPane.showMessageDialog(null, "C'est " + partie.getVainqueurManche() + " qui gagne la manche !", "Information", JOptionPane.INFORMATION_MESSAGE);
			this.actualiserPlateau();
			Scores tabScores = new Scores(partie);
			tabScores.setVisible(true);
			this.afficherNouvelleManche();
			tabScores.setVisible(false);
		}
	}

	public JFrame getFrame() {
		return frame;
	}

	public PanelJoueur getPjJoue() {
		return pjJoue;
	}

	public void setPjJoue(PanelJoueur pjJoue) {
		this.pjJoue = pjJoue;
	}
}

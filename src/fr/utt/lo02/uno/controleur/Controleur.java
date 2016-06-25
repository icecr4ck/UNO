package fr.utt.lo02.uno.controleur;

import java.awt.event.*;

import javax.swing.JOptionPane;

import fr.utt.lo02.uno.modele.carte.Carte;
import fr.utt.lo02.uno.modele.joueur.Joueur;
import fr.utt.lo02.uno.modele.partie.Partie;
import fr.utt.lo02.uno.vue.*;

/* On implŽmente ActionListener qui permet de traiter les ŽvŽnements du bouton */
 public class Controleur implements ActionListener {

	 private Partie partie;
	 private Accueil accueil;
	 private Parametres parametres;
	 private Regles regles;
	 private Plateau plateau;

	 public Controleur() {
		 partie = null;
		 accueil = null;
		 parametres = null;
		 regles = null;
		 plateau = null;
	 }

	 /* Permettra de connaitre le modŽle */
	 public void setModele(Partie partie) {
		 this.partie = partie;
	 }

	 /* Permettra de connaitre la vue */
	 public void setVue(Accueil a, Parametres p, Regles r, Plateau pl) {
		 this.accueil = a;
		 this.parametres = p;
		 this.regles = r;
		 this.plateau = pl;
	 }
	 
	 /* C'est ici que l'on traite l'action rŽcupŽrŽ : implŽmentation due ˆ l'interface ActionListener */
	 public void actionPerformed(ActionEvent e) {
		 if (e.getActionCommand() == "nouvellepartie"){
			 parametres.setVisible(true);
			 accueil.setVisible(false);
		 }
		 if (e.getActionCommand() == "reglesdujeu"){
			 regles.getFrame().setVisible(true);
			 accueil.setVisible(false);
		 }
		 if (e.getActionCommand() == "retour"){
			 accueil.setVisible(true);
			 regles.setVisible(false);
		 }
		 if (e.getActionCommand() == "quitterpartie"){
			 System.exit(0);
		 }
		 if (e.getActionCommand() == "jouer"){
			 if (parametres.getDifficulte() == 0){
				 JOptionPane.showMessageDialog(null, "Vous n'avez pas choisi la difficultŽ !", "Erreur", JOptionPane.ERROR_MESSAGE);
			 }
			 else if ((parametres.getNbrVirtuels() + parametres.getNbrReels()) > 10 || (parametres.getNbrVirtuels() + parametres.getNbrReels()) < 2){
				 JOptionPane.showMessageDialog(null, "Le nombre de joueurs choisi n'est pas correct !", "Erreur", JOptionPane.ERROR_MESSAGE);
			 }
			 else{
				 parametres.setVisible(false);
				 plateau.getFrame().setVisible(true);
				 partie.reglerParametres(parametres.getDifficulte(), parametres.getNbrVirtuels(), parametres.getNbrReels());
				 Thread manche = new Thread(partie);
				 manche.start();
			 }
		 }
		 if (e.getActionCommand() == "affichercartes"){
			 plateau.afficherJeu(plateau.getPjJoue());
		 }
		 
	 }

	public void controleCarte(Carte carte) {
		if (carte.determinerCarteJouable(partie.getTalon())){
			partie.getJoueurJoue().poserCarte(carte, partie.getTalon(), partie.getPioche(), partie);
		}
		else {
			JOptionPane.showMessageDialog(null, "La carte que vous avez choisi n'est pas jouable !", "Mauvaise carte", JOptionPane.ERROR_MESSAGE);
			plateau.choisirCarte();
		}
	}
	
	public void controlePioche(){
		partie.getPioche().verifieTalon(partie.getTalon(), 1);
		partie.getPioche().distribuerCarte(partie.getJoueurJoue(), 1);
	}

	public void controleCouleur(String couleur) {
		if (couleur.equals(null)){
			plateau.choisirCouleur();
		}
		else{
			if (couleur.equals("Noir")){
				plateau.choisirCouleur();
			}
			else if (couleur.equals("Bleu")){
				partie.getTalon().setCouleurTalon(Carte.BLEU);
			}
			else if (couleur.equals("Rouge")){
				partie.getTalon().setCouleurTalon(Carte.ROUGE);
			}
			else if (couleur.equals("Jaune")){
				partie.getTalon().setCouleurTalon(Carte.JAUNE);
			}
			else{
				partie.getTalon().setCouleurTalon(Carte.VERT);
			}
		}
	}

	public void controleUNO() {
		if (partie.getJoueurJoue().getCartesEnMain().size()==1){
			 int reponseUNO = JOptionPane.showConfirmDialog(null, 
				        "Voulez-vous dire Uno ?", 
				        "UNO", 
				        JOptionPane.YES_NO_OPTION, 
				        JOptionPane.QUESTION_MESSAGE);
				if(reponseUNO == JOptionPane.OK_OPTION){
					partie.getJoueurJoue().direUno();
				}
		 }	
	}

	public void controleContreUNO(){
		Joueur joueur = (Joueur)JOptionPane.showInputDialog(null, 
			      "Quel joueur voulez vous contrer ?",
			      "Contre UNO",
			      JOptionPane.QUESTION_MESSAGE,
			      null,
			      partie.getJoueurs().toArray(),
			      partie.getJoueurs().get(0));
		joueur.contrerUno(joueur, partie.getPioche());
	}
	
	public void nouvelleManche(int nm) {
		if (nm == JOptionPane.OK_OPTION){
			Thread manche = new Thread(partie);
			manche.start();
		}
		else if (nm == JOptionPane.NO_OPTION){
			System.exit(0);
		}
		else{
			plateau.afficherNouvelleManche();
		}
	}
}
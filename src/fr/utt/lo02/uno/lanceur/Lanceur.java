package fr.utt.lo02.uno.lanceur;

import javax.swing.UIManager;

import fr.utt.lo02.uno.controleur.Controleur;
import fr.utt.lo02.uno.modele.partie.Partie;
import fr.utt.lo02.uno.vue.*;

public class Lanceur {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(
            UIManager.getCrossPlatformLookAndFeelClassName());
		} 
		catch (Exception e) {

		}
		Controleur controleur = new Controleur();
		Partie partie = new Partie();
		Accueil accueil = new Accueil();
		Regles regles = new Regles();
		Parametres parametres = new Parametres();
		Plateau plateau = new Plateau(partie, controleur);

		controleur.setModele(partie);
		controleur.setVue(accueil, parametres, regles, plateau);
		accueil.setListener(controleur);
		regles.setListener(controleur);
		parametres.setListener(controleur);
	}
}


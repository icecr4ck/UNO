package fr.utt.lo02.uno.vue.panneaux;

import java.awt.*;
import java.util.LinkedList;

import javax.swing.*;

import fr.utt.lo02.uno.modele.joueur.Joueur;

public class PanelJoueur extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nomJoueur;
	private Boolean virtuel = true;
	private JLabel nomJoueurLabel;
	private PanelCartes pc;
	private JButton uno;
	private Font font = new Font("Courier", Font.BOLD, 20);
	
	private LinkedList<Image> jeu;
	
	public PanelJoueur(Joueur joueur){
		super();
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		this.setJeu(new LinkedList<Image>());
		
		this.pc = new PanelCartes(jeu);
		this.add(pc);
		
		if (!joueur.isVirtuel()){
			this.virtuel = false;
		}
		
		this.nomJoueur = joueur.getNomJoueur();
		this.nomJoueurLabel = new JLabel(nomJoueur);
		this.nomJoueurLabel.setFont(font);
		this.nomJoueurLabel.setForeground(Color.RED);
		this.add(nomJoueurLabel, BorderLayout.NORTH);
	}

	public JButton getUno() {
		return uno;
	}

	public void setUno(JButton uno) {
		this.uno = uno;
	}

	public void piocher(Image carte){
		this.jeu.add(carte);
	}
	
	public void retirer(Image carte){
		this.jeu.remove(carte);
	}
	
	public void retirerTout(){
		this.jeu.removeAll(jeu);
	}

	public void setCartesVisibles(Boolean visibles){
		this.pc.setCartesVisibles(visibles);
	}

	public LinkedList<Image> getJeu() {
		return jeu;
	}

	public void setJeu(LinkedList<Image> jeu) {
		this.jeu = jeu;
	}

	public String getNomJoueur() {
		return nomJoueur;
	}

	public PanelCartes getPc() {
		return pc;
	}

	public void setPc(PanelCartes pc) {
		this.pc = pc;
	}
	
	public Boolean isVirtuel() {
		return virtuel;
	}

	public void setVirtuel(Boolean virtuel) {
		this.virtuel = virtuel;
	}
}

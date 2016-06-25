package fr.utt.lo02.uno.vue.panneaux;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanelCartes extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LinkedList<Image> jeu;
	private boolean cartesVisibles;
	
	public PanelCartes(LinkedList<Image> j){
		super();
		this.jeu = j;
		this.cartesVisibles = false;
		this.setOpaque(false);
		this.setLayout(null);
	}
	
	public LinkedList<Image> getJeu() {
		return jeu;
	}

	public void setJeu(LinkedList<Image> jeu) {
		this.jeu = jeu;
	}

	public boolean isCartesVisibles() {
		return cartesVisibles;
	}

	public void setCartesVisibles(boolean visible) {
		this.cartesVisibles = visible;
	}

	public void paintComponent(Graphics g){
		Image dos = null;
		try {
			dos = ImageIO.read(new File("Dos.bmp"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ListIterator<Image> iJeu = jeu.listIterator();
		while (iJeu.hasNext()){
			if (cartesVisibles)
				g.drawImage(iJeu.next(), (iJeu.previousIndex()*30), 0, 80, 140, this);
			else{
				iJeu.next();
				g.drawImage(dos, (iJeu.previousIndex()*30), 0, 80, 140, this);
			}
			
		}
	}
}

package fr.utt.lo02.uno.vue.panneaux;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanelPile extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LinkedList<Image> cartes;
	private LinkedList<Image> talon;
	
	public PanelPile(){
		super();
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		this.cartes = new LinkedList<Image>();
		this.talon = new LinkedList<Image>();
		this.genererCartes();
	}
	
	public void paintComponent(Graphics g){
		try {
		      Image pioche = ImageIO.read(new File("Dos.bmp"));
		      g.drawImage(pioche, 0, 0, 100, 180, this);
		      try {
		    	  Image talon = this.talon.getLast();
			      g.drawImage(talon, 120, 0, 100, 180, this);
		      }
		      catch (NoSuchElementException e) {}
		} catch (IOException e) {
		      e.printStackTrace();
		}
	}
	
	public void genererCartes(){
		try {
			Image plus4 = ImageIO.read(new File("Speciales/Plus 4.bmp"));
			cartes.add(plus4);
			Image joker = ImageIO.read(new File("Speciales/Joker.bmp"));
			cartes.add(joker);
			
			Image zeroVert = ImageIO.read(new File("Vert/Zero Vert.bmp"));
			cartes.add(zeroVert);
			Image unVert = ImageIO.read(new File("Vert/Un Vert.bmp"));
			cartes.add(unVert);
			Image deuxVert = ImageIO.read(new File("Vert/Deux Vert.bmp"));
			cartes.add(deuxVert);
			Image troisVert = ImageIO.read(new File("Vert/Trois Vert.bmp"));
			cartes.add(troisVert);
			Image quatreVert = ImageIO.read(new File("Vert/Quatre Vert.bmp"));
			cartes.add(quatreVert);
			Image cinqVert = ImageIO.read(new File("Vert/Cinq Vert.bmp"));
			cartes.add(cinqVert);
			Image sixVert = ImageIO.read(new File("Vert/Six Vert.bmp"));
			cartes.add(sixVert);
			Image septVert = ImageIO.read(new File("Vert/Sept Vert.bmp"));
			cartes.add(septVert);
			Image huitVert = ImageIO.read(new File("Vert/Huit Vert.bmp"));
			cartes.add(huitVert);
			Image neufVert = ImageIO.read(new File("Vert/Neuf Vert.bmp"));
			cartes.add(neufVert);
			Image ptVert = ImageIO.read(new File("Vert/Passe-Tour Vert.bmp"));
			cartes.add(ptVert);
			Image isVert = ImageIO.read(new File("Vert/Inverse Sens Vert.bmp"));
			cartes.add(isVert);
			Image plus2Vert = ImageIO.read(new File("Vert/Plus Deux Vert.bmp"));
			cartes.add(plus2Vert);
			
			Image zeroJaune = ImageIO.read(new File("Jaune/Zero Jaune.bmp"));
			cartes.add(zeroJaune);
			Image unJaune = ImageIO.read(new File("Jaune/Un Jaune.bmp"));
			cartes.add(unJaune);
			Image deuxJaune = ImageIO.read(new File("Jaune/Deux Jaune.bmp"));
			cartes.add(deuxJaune);
			Image troisJaune = ImageIO.read(new File("Jaune/Trois Jaune.bmp"));
			cartes.add(troisJaune);
			Image quatreJaune = ImageIO.read(new File("Jaune/Quatre Jaune.bmp"));
			cartes.add(quatreJaune);
			Image cinqJaune = ImageIO.read(new File("Jaune/Cinq Jaune.bmp"));
			cartes.add(cinqJaune);
			Image sixJaune = ImageIO.read(new File("Jaune/Six Jaune.bmp"));
			cartes.add(sixJaune);
			Image septJaune = ImageIO.read(new File("Jaune/Sept Jaune.bmp"));
			cartes.add(septJaune);
			Image huitJaune = ImageIO.read(new File("Jaune/Huit Jaune.bmp"));
			cartes.add(huitJaune);
			Image neufJaune = ImageIO.read(new File("Jaune/Neuf Jaune.bmp"));
			cartes.add(neufJaune);
			Image ptJaune = ImageIO.read(new File("Jaune/Passe-Tour Jaune.bmp"));
			cartes.add(ptJaune);
			Image isJaune = ImageIO.read(new File("Jaune/Inverse Sens Jaune.bmp"));
			cartes.add(isJaune);
			Image plus2Jaune = ImageIO.read(new File("Jaune/Plus Deux Jaune.bmp"));
			cartes.add(plus2Jaune);
			
			Image zeroBleu = ImageIO.read(new File("Bleu/Zero Bleu.bmp"));
			cartes.add(zeroBleu);
			Image unBleu = ImageIO.read(new File("Bleu/Un Bleu.bmp"));
			cartes.add(unBleu);
			Image deuxBleu = ImageIO.read(new File("Bleu/Deux Bleu.bmp"));
			cartes.add(deuxBleu);
			Image troisBleu = ImageIO.read(new File("Bleu/Trois Bleu.bmp"));
			cartes.add(troisBleu);
			Image quatreBleu = ImageIO.read(new File("Bleu/Quatre Bleu.bmp"));
			cartes.add(quatreBleu);
			Image cinqBleu = ImageIO.read(new File("Bleu/Cinq Bleu.bmp"));
			cartes.add(cinqBleu);
			Image sixBleu = ImageIO.read(new File("Bleu/Six Bleu.bmp"));
			cartes.add(sixBleu);
			Image septBleu = ImageIO.read(new File("Bleu/Sept Bleu.bmp"));
			cartes.add(septBleu);
			Image huitBleu = ImageIO.read(new File("Bleu/Huit Bleu.bmp"));
			cartes.add(huitBleu);
			Image neufBleu = ImageIO.read(new File("Bleu/Neuf Bleu.bmp"));
			cartes.add(neufBleu);
			Image ptBleu = ImageIO.read(new File("Bleu/Passe-Tour Bleu.bmp"));
			cartes.add(ptBleu);
			Image isBleu = ImageIO.read(new File("Bleu/Inverse Sens Bleu.bmp"));
			cartes.add(isBleu);
			Image plus2Bleu = ImageIO.read(new File("Bleu/Plus Deux Bleu.bmp"));
			cartes.add(plus2Bleu);
			
			Image zeroRouge = ImageIO.read(new File("Rouge/Zero Rouge.bmp"));
			cartes.add(zeroRouge);
			Image unRouge = ImageIO.read(new File("Rouge/Un Rouge.bmp"));
			cartes.add(unRouge);
			Image deuxRouge = ImageIO.read(new File("Rouge/Deux Rouge.bmp"));
			cartes.add(deuxRouge);
			Image troisRouge = ImageIO.read(new File("Rouge/Trois Rouge.bmp"));
			cartes.add(troisRouge);
			Image quatreRouge = ImageIO.read(new File("Rouge/Quatre Rouge.bmp"));
			cartes.add(quatreRouge);
			Image cinqRouge = ImageIO.read(new File("Rouge/Cinq Rouge.bmp"));
			cartes.add(cinqRouge);
			Image sixRouge = ImageIO.read(new File("Rouge/Six Rouge.bmp"));
			cartes.add(sixRouge);
			Image septRouge = ImageIO.read(new File("Rouge/Sept Rouge.bmp"));
			cartes.add(septRouge);
			Image huitRouge = ImageIO.read(new File("Rouge/Huit Rouge.bmp"));
			cartes.add(huitRouge);
			Image neufRouge = ImageIO.read(new File("Rouge/Neuf Rouge.bmp"));
			cartes.add(neufRouge);
			Image ptRouge = ImageIO.read(new File("Rouge/Passe-Tour Rouge.bmp"));
			cartes.add(ptRouge);
			Image isRouge = ImageIO.read(new File("Rouge/Inverse Sens Rouge.bmp"));
			cartes.add(isRouge);
			Image plus2Rouge = ImageIO.read(new File("Rouge/Plus Deux Rouge.bmp"));
			cartes.add(plus2Rouge);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void ajouterAuTalon(Image c){
		this.talon.add(c);
	}
	
	public LinkedList<Image> getCartes() {
		return cartes;
	}

	public void setCartes(LinkedList<Image> cartes) {
		this.cartes = cartes;
	}

	public LinkedList<Image> getTalon() {
		return talon;
	}

	public void setTalon(LinkedList<Image> talon) {
		this.talon = talon;
	}

	
}

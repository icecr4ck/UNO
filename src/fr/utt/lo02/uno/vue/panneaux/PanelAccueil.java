package fr.utt.lo02.uno.vue.panneaux;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanelAccueil extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PanelAccueil(){
		super();
	}
	
	public void paintComponent(Graphics g){
	    try {
	      Image img = ImageIO.read(new File("UNO_Accueil.jpg"));
	      g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	      Font font = new Font("Courier", Font.BOLD, 18);
		  g.setFont(font);
		  g.drawString("UTT - LO02", 10, 760);
		  g.drawString("Ecrit par Hugo Porcher et Arthur Gambet ©", 540, 760);
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	} 
}

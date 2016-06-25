package fr.utt.lo02.uno.modele.pile;
	/**
	 * Le talon est une pile auquel on ajoute des cartes au fur et a mesure du jeu.
	 * Elle n'a pas d'attributs supplementaire par rapport a sa classe mere. 
	 * Elle possede un attribut qui permet de savoir qu'elle est la couleur dernierement posee sur le talon.
	 * Par contre elle possede des methodes qui vont permettre de tirer la premiere carte de la liste des cartes qu'elle contient et de l'affecter a un joueur.
	 * 
	 * @author Hugo Arthur
	 *
	 */
public class Talon extends Pile {

	
	private int couleurTalon;

	/**
	 * Le constructeur de Talon.
	 * Lorsque l'on cree le talon, on cree une pile et on supprime toutes les cartes de la pile afin d'avoir un talon vide. 
	 */
	
	public Talon(){
		super();
		this.pile.removeAll(this.pile);
	}
	
	/**
	 * Methode qui permet de renvoyer un string correpondant a la couleur du talon.
	 * 
	 * @return
	 */
	
	public String afficherCouleurTalon(){
		String couleur;
		if (this.couleurTalon==0){
			couleur = "Bleu";
		}
		else if (this.couleurTalon==1){
			couleur = "Rouge";
		}
		else if (this.couleurTalon==2){
			couleur = "Jaune";
		}
		else if (this.couleurTalon==3){
			couleur = "Vert";
		}
		else {
			couleur = "cette couleur n'existe pas";
		}
		return couleur;
	}
	
	/**
	 * Methode qui prend en parametre la nouvelle couleur du talon et qui la modifie.
	 * @param color
	 */
	
	public void setCouleurTalon(int color) {
		couleurTalon=color;
	}
	
	/**
	 * Methode qui retourne la couleur du talon.
	 * @return
	 */
	
	public int getCouleurTalon(){
		return couleurTalon;
	}
	
	/**
	 * Methode qui permet de rendre lisible un talon en ligne de commande.
	 * Elle redefinit la methode toString().
	 */
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n///Carte du talon : " + this.getPile().getLast());
		sb.append("\n///Couleur du talon : " + this.afficherCouleurTalon());
		return sb.toString();
	    } 
}

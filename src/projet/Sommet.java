package projet;

import java.util.ArrayList;

	/**
	 * Definition de la classe Sommet (sommet de graphe--appellé ville dans le cas ne notre problème TSP)
	 *
	 */
public class Sommet {
	
	int xPos;
	int yPos;
	int indice;
	
				/**
				 * Constructeur du sommet qui prend en paramètre:
				 * 			-x l'abscisse du sommet x
				 * 			-y l'ordonnée du sommet y
				 * 			-indice : un identifiant unique de la ville
				 */
	public Sommet(int x,int y, int ind){
		 xPos=x;
		 yPos=y;
		 indice=ind;
		
	
	}
		/**
		 * affichage d'un sommet: affiche sa position le couple (x,y) du sommet
		 */
	
	public void afficheSommet(){
		System.out.print("("+xPos+","+yPos+")");
		
	}
	
	
	  public static void main (String[] args){
		  /*test:
		  
		  Sommet A= new Sommet(1,2,0);
		  A.afficheSommet();
		  */
	  }
	
}

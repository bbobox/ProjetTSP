package projet;

import java.util.ArrayList;

public class Sommet {
	
	int xPos;
	int yPos;
	int indice;
	
	
	public Sommet(int x,int y){
		 xPos=x;
		 yPos=y;
		
	
	}
	
	public void afficheSommet(){
		System.out.print("("+xPos+","+yPos+")");
		
	}
	
	
	  public static void main (String[] args){
		  
		  Sommet A= new Sommet(1,2);
		  A.afficheSommet();
		  
	  }
	
}

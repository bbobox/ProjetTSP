package projet;

/**
 *Class Solution qui correspond à une un odre de de parcours representé par un vecteur de Ville et le boolean
 *
 */

public class Solution {
	
	
	
	Sommet[] sol;
	boolean visite;
	
	
	
	public Solution(Sommet[] solution){
		sol= solution;
		visite=false;
		
	}
	
	public boolean  estViste(){
		
		 return (visite==true);
	}
	
	public void visiteSolution(){
		visite=true;
	}

}

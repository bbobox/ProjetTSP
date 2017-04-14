package projet;
import java.util.ArrayList;

		/**
		 *  Class Gaphe qui represente l'ensemnle des sommet (ville )
		 *
		 */

public class Graphe {
	int nbS; // nombre de sommet
	
	
	ArrayList<Sommet> ordeSommet;   		// Liste de sommet 
	ArrayList<Integer> listeDistance;
	Sommet[] ordreSommet; 				 // tableau d'ordre de sommet
	ArrayList<Sommet> listSolution;
	
	int[][] distance;// tableau de distances entre les villes 
	 
			/**
			 * Constructeur du graphe: en fonction du vecteur de sommet en paramètre la solution au depart 
			 * @param m
			 */
	public Graphe(Sommet[] m){
		//ordeSommet=new ArrayList<Sommet>();
		nbS=m.length;
		ordreSommet=m;
		
		distance=new int[nbS][nbS];
		listSolution= new ArrayList<Sommet>();
	}	
	
	public float distanceEuclidienne(Sommet x, Sommet y){
		int res=0;
		
		int a=  (x.xPos-y.xPos)*(x.xPos-y.xPos);
		int b=  (x.yPos-y.yPos)*(x.yPos-y.yPos);
		
		return (float) Math.sqrt(a+b);
	
	}
	

		
	
	
	public Sommet[] inverseVecteur( Sommet[] v){  // Inversion de vecteur ex: [1-2-3] -> [3-2-1]
		int n= v.length;
		Sommet res[]= new Sommet[n];
		for(int i=0; i<n;i++){
			Sommet a=v[n-1-i];
			res[i]=a;
		}
		
		return res;
		
	}
	
	public Sommet[] sousVecteur(Sommet[] v, int i, int j){
		int n= j-i+1;
		Sommet[] res =new Sommet[n];
		for( int it=0;it<n;it++){
			res[it]=v[i+it];
		}
		
		return res;
	}
	
	
	public void permut(int i ,int j ,Sommet[] Tab){  // Permutation 2 opt entre la ville d'indice i et la vile d'indice j
		
	  int n=(j-1)+1;
	  
	  for(int ind=0;ind<n;ind++){ // inversement
		  
		  
	  }
	  int[] l=new  int[n];
	  int nbSommet= ordreSommet.length; 
	  int tabRes[]=new int[nbSommet];
	  
	  
		
	}
	
	public void afficheOrdreSommet(Sommet[] t){
		
		int n=t.length;
		for(int i=0;i<n;i++){
			t[i].afficheSommet();
		}
	}
	
	
	
	 /**
	  * Fonction de calcul de 2_opt 
	  * */
	
	
	public Sommet[] opt2(int i, int j, Sommet[] s){
		
		
		Sommet[] sousTab= sousVecteur(s,i,j);
		Sommet[] sousTabInv=inverseVecteur(sousTab);
		int n= sousTab.length;
		
		Sommet[] res= new Sommet[s.length];
		
		for (int k=0;k<i;k++){
			res[k]=s[k];
			
		}
		
		if (sousTab.length>0){
			int ind=0;
			for(int k=i; k<j+1;k++){
				res[k]=sousTabInv[ind];
				ind++;
			}
		
	
		
		}
		
		for(int k=j+1;k<s.length;k++){
			res[k]=s[k];
		}
		return res;
		
	}
	
	
	public void liste2_opt(Sommet[] s){
		
		int n= s.length;
		for (int i=0;i<n-1;i++){
			for (int j=i+1;j<n;j++){
				Sommet[] i_2opt=opt2(i,j, s);
				afficheOrdreSommet(i_2opt);
				System.out.println();
			}
			
			
		}
		
	}
	
	
	
	
	public static void main (String[] args){
		  
		 Sommet A= new Sommet(1,1,0);
		 Sommet B= new Sommet(2,2,1);
		 Sommet C= new Sommet(3,3,2);
		 Sommet D= new Sommet(4,4,3);
		 Sommet E= new Sommet(5,5,4);
		 Sommet F= new Sommet(6,6,5);
		 Sommet G= new Sommet(7,7,6);
		 Sommet H= new Sommet(8,8,7);
		 Sommet I= new Sommet(9,9,8);
		 //Sommet C= new Sommet(3,4);
		 
		 Sommet[] t= {A,B,C,D,E};
		 
		 Graphe F1= new Graphe(t);
		  
		 Sommet[] l1=F1.opt2(1,4, t);
				 F1.afficheOrdreSommet(t);
				 System.out.println();
				 System.out.println();
				 System.out.println();
				 //F1.afficheOrdreSommet(l1);
				 
		  
				 
		 F1.liste2_opt(t);
	  }
	
	
	
	
}

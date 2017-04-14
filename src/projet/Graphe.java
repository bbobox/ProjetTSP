package projet;
import java.util.ArrayList;

public class Graphe {
	int nbS; // nombre de sommet
	
	
	ArrayList<Sommet> ordeSommet;   // Liste de sommet 
	ArrayList<Integer> listeDistance;
	Sommet[] ordreSommet;  // tableau d'ordre de sommet
	
	int distance[][] ;// tableau de distances entre les villes 
	 
	public Graphe(Sommet[] m){
		//ordeSommet=new ArrayList<Sommet>();
		nbS=0;
		ordreSommet=m;
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
	
	
	public static void main (String[] args){
		  
		 Sommet A= new Sommet(1,2);
		 Sommet B= new Sommet(2,3);
		 //Sommet C= new Sommet(3,4);
		 
		 Sommet[] t= {A,B};
		 
		 Graphe G= new Graphe(t);
		  
		 Sommet[] l1=G.inverseVecteur(t);
		 G.afficheOrdreSommet(t);
		 System.out.println();
		 G.afficheOrdreSommet(l1);
		  
		  
	  }
	
	 /**
	  * Fonction de calcul de 2_opt 
	  * */
	
	
	public void opt2(int i, int j, Sommet[] s){
		
		
		Sommet[] sousTab= sousVecteur(s,i,j);
		int n= sousTab.length;
		
		Sommet[] res= new Sommet[s.length];
		
		for (int k=0;k<i;i++){
			res[k]=s[k];
			
		}
		
		
	}
	
	
	
	
}

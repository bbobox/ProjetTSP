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
	ArrayList<Sommet[]> listSolution;
	ArrayList<Boolean> listSolutionBool;
	
	float[][] distance;// tableau de distances entre les villes :Premier objectif
	float[][] distance2; // tableau ou cout entre les villes : Second objectif;
	 
			/**
			 * Constructeur du graphe: en fonction du vecteur de sommet en paramètre la solution au depart 
			 * @param m
			 */
	public Graphe(Sommet[] m){
		nbS=m.length;
		ordreSommet=m;
		listSolutionBool= new ArrayList<Boolean>();
		float[][] distance2= new float[nbS][nbS];
		
		/* Initialisation mise à jour de la matrice de Distance entre les sommets */
		distance=new float[nbS][nbS];
		for(int i=0;i<nbS;i++){
			for (int j=0;j<nbS;j++){
				Sommet a=m[i];
				Sommet b=m[j];
				distance[i][j]= distanceEuclidienne(a,b);
			}
		}
		
		listSolution= new ArrayList<Sommet[]>();
	}
	
	
	public Graphe(Sommet[] s1,Sommet[] s2){
		int nbV=s1.length;
		nbS=nbV;
		ordreSommet=s1;
		listSolutionBool= new ArrayList<Boolean>();
		
		/* Initialisation mise à jour de la matrice de Distance entre les sommets servant au calcul de la fonction objectif f2  */
		distance=new float[nbV][nbV];
		for(int i=0;i<nbV;i++){
			for (int j=0;j<nbV;j++){
				Sommet a=s1[i];
				Sommet b=s1[j];
				distance[i][j]= distanceEuclidienne(a,b);
			}
		}
		
		/* Initialisation mise à jour de la matrice 2 de Distance/couts entre les sommets pour servant au calcul de la fonction objectif f2 */
		distance2=new float[nbS][nbS];
		for(int i=0;i<nbS;i++){
			for (int j=0;j<nbS;j++){
				Sommet a=s2[i];
				Sommet b=s2[j];
				distance2[i][j]= distanceEuclidienne(a,b);
			}
		}
		
		
		listSolution= new ArrayList<Sommet[]>();
		
		
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
	  * Fonction de calcul de 2_opt ( en fonction d'une solution et  indive de sommet/villes)
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
	
	
	/**
	 * Affichage de la liste de solution voisine par la methode 2-opt
	 * 
	 */
	
	public void liste2_opt(Sommet[] s){
		
		int n= s.length;
		for (int i=0;i<n-1;i++){
			for (int j=i+1;j<n-1;j++){
				Sommet[] i_2opt=opt2(i,j, s);
				Sommet a= i_2opt[0];
				i_2opt[n-1]=a;
				afficheOrdreSommet(i_2opt);
				System.out.println();
			}
			
			
		}
		
	}
	
	/** Calcul du distance/cout d'une solution  
	 * 
	 */
	
	public float distanceTot(Sommet[] s){
		float res=(float) 0;
		int n=s.length;
		for(int i=0;i<n-1;i++){
			int a=s[i].indice;
			int b=s[i+1].indice;
			res=res+distance[a][b];
		}		
		return res;
	}
	
	
	
	/**
	 * Recherche de solution: Cas Mono-Objectif
	 */
	
	public Sommet[] monoObjectifTsp(){
		System.out.println("Calul du tsp");
		listSolution.add(ordreSommet); // ajout de notre solution initial dans la liste de solution
		listSolutionBool.add(false);
		
		
		
		int i=0;
		//boolean test=true;
		while(i<listSolution.size()){
			//System.out.print("i="+i+" ->");
			Sommet[] sol=listSolution.get(i);
			
			float resSol=distanceTot(sol);
			//System.out.println("sol="+resSol+" ->");
			int n= sol.length;
			
			ArrayList<Sommet[]> listeVoisin= new ArrayList<Sommet[]>();
			Sommet[] bestVoisin= sol;
			boolean test=false;
			for (int j=0;j<n;j++){
				for (int k=j+1;k<n-1;k++){
					Sommet[] i_2opt=opt2(j,k, sol);
					Sommet a= i_2opt[0];
					i_2opt[n-1]=a;
					if( distanceTot(i_2opt)<distanceTot(bestVoisin)){
					
						bestVoisin=i_2opt;
						test=true;
					}
					
				}	
			}
			if(test==true){
				listSolution.add(bestVoisin);
			}
			i++;
		}
			
		int tailleList= listSolution.size();
		 int iRes=0;
		 float valRes=distanceTot(listSolution.get(0));
		 for(int ind=0;ind<tailleList;ind++){
			 float d= distanceTot(listSolution.get(ind));
			 if (d<valRes){
				 valRes=d;
				 iRes=ind;
			 }
			 
		 }
			
		 Sommet[] res=listSolution.get(iRes);
		
		 return res;
		
		
	}
	
	
	public float[] fonctionObjectif(Sommet[] sol){
		float[] f1f2= new float[2];
		/*calcul de sur la fonction bjectif 1*/
		float res1= (float)0;
		float res2= (float)0;
		for(int i=0;i<sol.length-1;i++){
			int a=sol[i].indice;
			int b=sol[i+1].indice;
			res1=res1+distance[a][b];
			res2=res2+distance2[a][b];
		}
		
		f1f2[0]=res1;
		f1f2[1]=res2;
		
		return f1f2;
	}
	
	
	public void SolNonDominé(ArrayList<Sommet[]> sol){
		ArrayList<Sommet[]>  resultat= new ArrayList<Sommet[]>();
		
		
	}
	
	
	/**
	 *  Suppression des solutions dominées (multiObjectif)
	 */
	
	public  ArrayList<Sommet[]> suppressionSol( ArrayList<Sommet[]> listSol){
		ArrayList<float[]> valFonction= new ArrayList<float[]>();
		ArrayList<Sommet[]> res= new ArrayList<Sommet[]>();
		
		int nbSol= listSol.size();
		for(int i=0;i<nbSol;i++){
			valFonction.add(fonctionObjectif(listSol.get(i)));
		}
		
		for (int i=0;i<nbSol;i++){
			Sommet[] solI= listSol.get(i);
			// On le compare d'indice aux autres solutions de la liste pour savoir si elle est dmoninée
			int j =0;
			boolean test=false;
			while(j<nbSol && test==false){
				float[] f1f2I=valFonction.get(i);
				float[] f1f2J=valFonction.get(j);
				if( (i!=j) &&(( f1f2J[0]<= f1f2I[0] && f1f2J[1]<f1f2I[1]) || (( f1f2J[0]< f1f2I[0] && f1f2J[1]<=f1f2I[1])))){
					test=true;
				}
				j++;
			}
		
			if(test==false){ /* On ajoute la solution à la liste si elle n'est pas dominée*/
				res.add(solI);
			}
			
		}
		
		return res; /* renvoie la liste des solution non dominée */
	}
	
	
	

	/**
	 *  Suppression des solutions dominées et renvoie une liste de solution non dominées
	 */
	
	public  ArrayList<Solution> suppressionDomine( ArrayList<Solution> listSol){
		ArrayList<float[]> valFonction= new ArrayList<float[]>();
		ArrayList<Solution> res= new ArrayList<Solution>();
		
		int nbSol= listSol.size();
		for(int i=0;i<nbSol;i++){
			valFonction.add(fonctionObjectif(listSol.get(i).sol));
		}
		
		for (int i=0;i<nbSol;i++){
			Sommet[] solI= listSol.get(i).sol;
			// On le compare d'indice aux autres solutions de la liste pour savoir si elle est dmoninée
			int j =0;
			boolean test=false;
			
			float[] f1f2I=valFonction.get(i);
			while(j<nbSol && test==false){
				
				float[] f1f2J=valFonction.get(j);
				if( (i!=j) &&(( f1f2J[0]<= f1f2I[0] && f1f2J[1]<f1f2I[1]) || (( f1f2J[0]< f1f2I[0] && f1f2J[1]<=f1f2I[1])))){
					test=true;
				}
				j++;
			}
		
			if(test==false){ /* On ajoute la solution à la liste si elle n'est pas dominée*/
				res.add(listSol.get(i));
			
			}
			
		}
		
		return res; /* renvoie la liste des solution non dominée */
	}
	
	
	
	
	
	
	
	
	public ArrayList<Solution> multObjectif(Sommet[] sol1, Sommet[] sol2){
		ArrayList<Sommet[]> listSolutionNonDomine= new ArrayList<Sommet[]>();
		ArrayList<float[]> valeurFonctionObjectif= new ArrayList<float[]>(); 
		
		
		

		listSolutionNonDomine.add(sol1);
		valeurFonctionObjectif.add(fonctionObjectif(sol1));
		
		
		
	
		
		//Sommet[] SolutionOptimal;
		
		// Calcul des solutons voisines
		
		
		ArrayList<Solution> listeSolutionNonDomine= new ArrayList<Solution>();
		//ArrayList<Solution> valeurFonctionObjectif= new ArrayList<Solution>(); 
		Solution s1= new Solution(sol1);
		listeSolutionNonDomine.add(s1);
		ArrayList<Solution> ListVoisin= new ArrayList<Solution>();
		ArrayList<Solution> ListSol= new ArrayList<Solution>();
		int tailleSol= listeSolutionNonDomine.size();
		
		/* METHODE */
		
		ArrayList<Solution> ListVoisinI= new ArrayList<Solution>();
		ArrayList<Solution> ListSolI;
		
		int cptVoisin=0;
		
		for (int i=0;i<tailleSol;i++){
			Solution solI=listeSolutionNonDomine.get(i); // On recupère la solution d'indice I
			float[] solIObjctifs=fonctionObjectif(solI.sol);
			int n= solI.sol.length;
			solI.visiteSolution(); // On ele marque comme deja parcouru
			Solution voisin= new Solution(solI.sol);
			ListSolI= new ArrayList<Solution>();
			ListVoisinI= new ArrayList<Solution>();
			ListSolI.add(solI);
			cptVoisin++;   // MAJ du compteur
			for (int j=0;j<n;j++){
				for (int k=j+1;k<n-1;k++){
					Sommet[] i_2opt=opt2(j,k,solI.sol);
					Sommet a= i_2opt[0];
					i_2opt[n-1]=a;
					float[]iF1F2=fonctionObjectif(i_2opt);
					voisin= new Solution(i_2opt);
					ListSolI.add(voisin);

				}
				
				
			}
			// recupere les voisins dominés
			ListSolI= suppressionDomine(ListSolI);
			
			//Ajout des solution obtenu dans ListSol
			int nb=ListSolI.size();
			for(int id=0;id<nb;id++){
				ListSol.add(ListSolI.get(id));
			}
			
			
		}
		listeSolutionNonDomine= suppressionDomine(ListSol);
		
		
		// Tant qu'il y de nouvelles solution dominé
		while(cptVoisin>0){
			ListVoisin= new ArrayList<Solution>();
			ListSol= new ArrayList<Solution>();
			
			ListVoisinI= new ArrayList<Solution>();
			
			
			
			tailleSol= listeSolutionNonDomine.size();
			cptVoisin=0;
			for (int i=0;i<tailleSol;i++){
				Solution solI=listeSolutionNonDomine.get(i); // On recupère la solution d'indice I
				float[] solIObjctifs=fonctionObjectif(solI.sol);
				int n= solI.sol.length;
				//ListVoisin.add(solI);
				solI.visiteSolution();
				ListVoisinI= new ArrayList<Solution>();
				ListSolI= new ArrayList<Solution>();
				ListSolI.add(solI);
				
				if (solI.visite==false){
					cptVoisin++;
					Solution voisin= new Solution(solI.sol);
					ListSolI= new ArrayList<Solution>();
					ListVoisinI= new ArrayList<Solution>();
					solI.visiteSolution(); // On le marque deja parcouru
					for (int j=0;j<n;j++){
						for (int k=j+1;k<n-1;k++){
							Sommet[] i_2opt=opt2(j,k,solI.sol);
							Sommet a= i_2opt[0];
							i_2opt[n-1]=a;
							float[]iF1F2=fonctionObjectif(i_2opt);
							voisin= new Solution(i_2opt);
							ListVoisinI.add(voisin);
							ListSolI.add(voisin);

						}
						
						
					}
				}
					// recupere les voisins dominés
				ListSolI= suppressionDomine(ListSolI);
					
					//Ajout des solution obtenu dans ListSol
				int nb=ListSolI.size();
				for(int id=0;id<nb;id++){
					ListSol.add(ListSolI.get(id));
				}
				
			}
				//Supressio des solution non domine
				listeSolutionNonDomine= suppressionDomine(ListSol);		
		}
		
		
		return listeSolutionNonDomine;
	}
		
		
	
	

	
	
	
	
	public static void main (String[] args){
		
		/* Test Simple
		  
		 Sommet A0= new Sommet(1380, 939,0);
		 Sommet B1= new Sommet(2848, 96,1);
		 Sommet C2= new Sommet(3,3,2);
		 Sommet D3= new Sommet(4,4,3);
		 Sommet E4= new Sommet(12,5,4);
		 Sommet F5= new Sommet(6,6,5);
		 Sommet G6= new Sommet(7,75,6);
		 Sommet H7= new Sommet(8,8,7);
		 Sommet I8= new Sommet(25,9,8);
		 //Sommet C= new Sommet(3,4);
		 
		//2, 1, 7, 3, 4, 6, 8, 5, 0, 2
		 
		 Sommet[] t= {C2,B1,H7,D3,E4,G6,I8,F5,A0,C2};
		 Sommet[] t1= {A0,B1,C2,D3,E4,F5,G6,H7,I8,A0};
		 
		 Sommet A= new Sommet(1,4,0);
		 Sommet B= new Sommet(2,6,1);
		 Sommet C= new Sommet(3,5,2);
		 
		 Sommet[] t2= {A,B,C,A};
		 
		 Graphe F1= new Graphe(t1);
		// System.out.println(F1.distanceTot(t2));
		 
		// F1.liste2_opt(t);
		  
		 Sommet[] l1=F1.opt2(1,4, t);
				 F1.afficheOrdreSommet(t);
				 System.out.println();
				 System.out.println();
				 System.out.println(); 
				 F1.afficheOrdreSommet(l1);
		
				 
		
		  
				 
		Sommet[]tsp= F1.monoObjectifTsp();
		System.out.print("solution optimal");
		F1.afficheOrdreSommet(tsp);
		System.out.println("->"+F1.distanceTot(tsp)); 
		
		 //Math.sqrt(a+b);
		// System.out.println( Math.sqrt(2)); 
		*/
	  }
	
	
	
	
}

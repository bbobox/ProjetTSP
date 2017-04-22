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
		//ordeSommet=new ArrayList<Sommet>();
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
			for (int j=i+1;j<n-1;j++){
				Sommet[] i_2opt=opt2(i,j, s);
				Sommet a= i_2opt[0];
				i_2opt[n-1]=a;
				afficheOrdreSommet(i_2opt);
				System.out.println();
			}
			
			
		}
		
	}
	
	/** Calcul de distance total d'un solution 
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
	 * Recherche de solution: Cass Mono-Objectif
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
			
			//System.out.print("solution"+i+" ->");
			//afficheOrdreSommet(sol);
			//System.out.println(">"+distanceTot(sol)+  "	Sa liste de solution voisine");
			
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
					//System.out.print("	");
					// afficheOrdreSommet(i_2opt);
					// System.out.println("	");
					if( distanceTot(i_2opt)<distanceTot(bestVoisin)){
						//System.out.println("f voisin:"+distanceTot(i_2opt)+"  f_actuel:"+distanceTot(bestVoisin));
						//listSolution.add(i_2opt);
						//System.out.println("taille="+listSolution.size()+"  i="+i);
						bestVoisin=i_2opt;
						test=true;
						//System.out.println("nouveau voisin ->"+distanceTot(bestVoisin));
						//afficheOrdreSommet(i_2opt);
						//System.out.println("> "+distanceTot(i_2opt));
					}
					//afficheOrdreSommet(i_2opt);
					//System.out.println();
				}	
			}
			if(test==true){
				listSolution.add(bestVoisin);
				//System.out.println("Ajout de >>>>>  "+distanceTot(bestVoisin));
			}
			i++;
			
			
			/*int j=0;
			boolean ok=true;
			while(j<n && ok==true){
				int k=j+1;
				while(k<n-1 && ok==true){
					Sommet[] i_2opt=opt2(j,k, sol);
					Sommet a= i_2opt[0];
					i_2opt[n-1]=a;
					i
					if( distanceTot(i_2opt)<resSol){
						System.out.println("f voisin:"+distanceTot(i_2opt)+"  f_actuel:"+resSol);
						listSolution.add(i_2opt);
						ok=false;
						//System.out.println("taille="+listSolution.size()+"  i="+i+"");
					}
					else{
						System.out.println("f voisin:"+distanceTot(i_2opt)+"  f_actuel:"+resSol);
					}
					k++;
	
				}
				j++;
			}
			i++; */
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
		/*calcul de sur la fonction abjectif 1)
		 */
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
	 *  Suppression des solutions dominées
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
	
	
	public ArrayList<Sommet[]> multObjectif(Sommet[] sol1, Sommet[] sol2){
		ArrayList<Sommet[]> listSolutionNonDomine= new ArrayList<Sommet[]>();
		ArrayList<float[]> valeurFonctionObjectif= new ArrayList<float[]>(); 
		
		listSolutionNonDomine.add(sol1);
		valeurFonctionObjectif.add(fonctionObjectif(sol1));
		
		Sommet[] SolutionOptimal;
		
		/*
		while(listSolutionNonDomine.isEmpty()==false){ // Tant que la liste de  nouvelle solution non dominée est non vide 
			// Pour chaque solution in construit ses solutions vosine 
			ArrayList<Sommet[]> ListeVoisin= new ArrayList<Sommet[]>();
			int taille=listSolutionNonDomine.size();
			float[] minF1F2=fonctionObjectif(listSolutionNonDomine.get(0));
			for (int i=0;i<taille;i++){
				Sommet[] solI=listSolutionNonDomine.get(i); // On recupère la soslution d'indice I
				
				int n= solI.length;
				for (int j=0;j<n-1;j++){
					for (int k=j+1;k<n;k++){
						Sommet[] i_2opt=opt2(j,k,solI);
						ListeVoisin.add(i_2opt);  //Ajout des voisins à la liste de solution voisine
						float[]iF1F2=fonctionObjectif(i_2opt);
						valeurFonctionObjectif.add(iF1F2);
						if (iF1F2[0]<minF1F2[0]); minF1F2[0]=iF1F2[0];
						if (iF1F2[1]<minF1F2[1]); minF1F2[1]=iF1F2[1];
						

					}
					
					
				}
				
				
			}
			// Suppression des solutions dominées
		 int nbVoisin=ListeVoisin.size();
		 ArrayList<Sommet[]> nonDomineIntermediaire=new ArrayList<Sommet[]>();
		 for (int i=0;i<nbVoisin;i++){
			 float[] i_f1f2= valeurFonctionObjectif.get(i);
			 if (i_f1f2[0]<minF1F2[0] || i_f1f2[0]<minF1F2[0]){
				 nonDomineIntermediaire.add(ListeVoisin.get(i));
			 }
			 
			 
		 }
			
		}
		
		**/
		
		// Calcul des solutons voisines
		ArrayList<Sommet[]> ListeVoisin= new ArrayList<Sommet[]>();
		ArrayList<Sommet[]> ListeSol= new ArrayList<Sommet[]>();
		int taille=listSolutionNonDomine.size();
		
		for (int i=0;i<taille;i++){
			Sommet[] solI=listSolutionNonDomine.get(i); // On recupère la solution d'indice I
			float[] solIObjctifs=fonctionObjectif(solI);
			int n= solI.length;
			ListeVoisin.add(solI);
			ListeSol.add(solI);
			for (int j=0;j<n-1;j++){
				for (int k=j+1;k<n;k++){
					Sommet[] i_2opt=opt2(j,k,solI);
					float[]iF1F2=fonctionObjectif(i_2opt);
					
					if( iF1F2[0]<=solIObjctifs[0] || iF1F2[1]<=solIObjctifs[1]){
						ListeVoisin.add(i_2opt);  //Ajout des voisins à la liste de solution voisine
						ListeSol.add(i_2opt);
					}
					

				}
				
				
			}
			
			
		}
		
		
		listSolutionNonDomine= suppressionSol(ListeSol); // On filtre pour recuperer la liste des solution non dominées
		
		while(ListeVoisin.isEmpty()==false){ // tant que la liste des voisins est non vide
			ListeVoisin= new ArrayList<Sommet[]>();
			ListeSol=new ArrayList<Sommet[]>();
			taille=listSolutionNonDomine.size();
			
			for (int i=0;i<taille;i++){
				Sommet[] solI=listSolutionNonDomine.get(i); // On recupère la solution d'indice I
				float[] solIObjctifs=fonctionObjectif(solI);
				int n= solI.length;
				ListeSol.add(solI);
				for (int j=0;j<n-1;j++){
					for (int k=j+1;k<n;k++){
						Sommet[] i_2opt=opt2(j,k,solI);
						float[]iF1F2=fonctionObjectif(i_2opt);
						
						if( iF1F2[0]<=solIObjctifs[0] || iF1F2[1]<=solIObjctifs[1]){
							ListeVoisin.add(i_2opt);  //Ajout des voisins à la liste de solution voisine
							ListeSol.add(i_2opt);
							System.out.print("Solution ajoutée:");
							afficheOrdreSommet(i_2opt);
							System.out.println("f1:"+iF1F2[0]+" f2:"+iF1F2[1]);
							
						}
						

					}
					
					
				}
				
				
			}
			listSolutionNonDomine= suppressionSol(ListeSol);
		}
	
		return listSolutionNonDomine;
	}
		
		
	
	

	
	
	
	
	public static void main (String[] args){
		  
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
		 
		 Sommet A= new Sommet(1,4,0);
		 Sommet B= new Sommet(2,6,1);
		 Sommet C= new Sommet(3,5,2);
		 
		 Sommet[] t2= {A,B,C,A};
		 
		 Graphe F1= new Graphe(t2);
		 System.out.println(F1.distanceTot(t2));
		 
		// F1.liste2_opt(t);
		  
		 /*Sommet[] l1=F1.opt2(1,4, t);
				 F1.afficheOrdreSommet(t);
				 System.out.println();
				 System.out.println();
				 System.out.println(); 
				 F1.afficheOrdreSommet(l1);*/
		
				 
		
		  
				 
		/*Sommet[]tsp= F1.monoObjectifTsp();
		System.out.print("solution optimal");
		F1.afficheOrdreSommet(tsp);
		System.out.println("->"+F1.distanceTot(tsp)); */
		
		 //Math.sqrt(a+b);
		 System.out.println( Math.sqrt(2)); 
	  }
	
	
	
	
}

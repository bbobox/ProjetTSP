package projet;

import java.io.*;
import java.util.Collections;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Class qui permet de de l test des fonction Objction 
 */
public class TestTsp{
	
	public TestTsp(){
		
	}
	
	public void monoObjectif(String fileName){
		BufferedReader Lecteur = null;
		String ligne;
		Sommet[] ordreSommet;
		ArrayList <Sommet> ordre=new  ArrayList <Sommet>();
		
		 try{
	        	Lecteur=new BufferedReader(new  FileReader(fileName));
	        	//Pattern p= Pattern.compile();
	        	Pattern p = Pattern.compile("\\d+ \\d+ \\d+");
	        	
	        	while ((ligne = Lecteur.readLine()) != null){
	        		
	        		Matcher matcher = p.matcher (ligne);
	        		if (matcher.matches()){
	        			String[] arc=ligne.split(" ");
	        			Sommet s= new Sommet(Integer.parseInt(arc[1]), Integer.parseInt(arc[2]), Integer.parseInt(arc[0])-1);
	        			ordre.add(s);
	        			//s.afficheSommet();
	        			//System.out.println("->"+s.indice);
	        			
	        			}
	        		}
	        		
	        		
	        
	        	
	        	int nbS=ordre.size();
	        	ordreSommet= new Sommet[nbS+1];
	        	for (int i=0;i<nbS;i++){
	        		ordreSommet[i]=ordre.get(i);
	        	}
	        	ordreSommet[nbS]=ordre.get(0);
	        	
	        	Graphe solDepart= new Graphe(ordreSommet);
	        		Sommet[] optiMono= solDepart.monoObjectifTsp();
	        		solDepart.afficheOrdreSommet(optiMono);
	        		System.out.println("->"+solDepart.distanceTot(optiMono)); 
	        		
	        		
	        	}catch (FileNotFoundException f) {
	    			f.printStackTrace();
	    		} catch (IOException i) {
	    			i.printStackTrace();
	    		}
	       
	  
	        
		
		
	}
	
	
	public void multiObjectif(String file1,String file2){
		BufferedReader Lecteur1 = null;
		String ligne1;
		Sommet[] ordreSommet1;
		ArrayList <Sommet> ordre1=new  ArrayList <Sommet>();
		
		BufferedReader Lecteur2 = null;
		String ligne2;
		Sommet[] ordreSommet2;
		ArrayList <Sommet> ordre2=new  ArrayList <Sommet>();
		
		
		
		 try{
	        	Lecteur1=new BufferedReader(new  FileReader(file1));
	        	Pattern p = Pattern.compile("\\d+ \\d+ \\d+");
	        	
	        	while ((ligne1 = Lecteur1.readLine()) != null){
	        		
	        		Matcher matcher = p.matcher (ligne1);
	        		if (matcher.matches()){
	        			String[] arc=ligne1.split(" ");
	        			Sommet s= new Sommet(Integer.parseInt(arc[1]), Integer.parseInt(arc[2]), Integer.parseInt(arc[0])-1);
	        			ordre1.add(s);
	        			
	        			}
	        		}
	        	
	        	Lecteur2=new BufferedReader(new  FileReader(file2));
	        	while ((ligne2 = Lecteur2.readLine()) != null){
	        		
	        		Matcher matcher = p.matcher (ligne2);
	        		if (matcher.matches()){
	        			String[] arc=ligne2.split(" ");
	        			Sommet s= new Sommet(Integer.parseInt(arc[1]), Integer.parseInt(arc[2]), Integer.parseInt(arc[0])-1);
	        			ordre2.add(s);
	        			
	        			}
	        		}
	        	
	        	
	        		
	        		
	        		
	        	int nbS=ordre1.size();
	        	ordreSommet1= new Sommet[nbS+1];
	        	for (int i=0;i<nbS;i++){
	        		ordreSommet1[i]=ordre1.get(i);
	        	}
	        	ordreSommet1[nbS]=ordre1.get(0);
	        	
	        	ordreSommet2= new Sommet[nbS+1];
	        	for (int i=0;i<nbS;i++){
	        		ordreSommet2[i]=ordre2.get(i);
	        	}
	        	ordreSommet2[nbS]=ordre2.get(0);
	        		
	        	Graphe solDepart= new Graphe(ordreSommet1,ordreSommet2);
	        	ArrayList<Solution> res=solDepart.multObjectif(ordreSommet1,ordreSommet2);
	        	
	        	System.out.println("Affichage des solution non Dominiés: Ensemble de solutions:");
	        	int taille= res.size();
	        	for(int i=0;i<taille;i++){
	        		solDepart.afficheOrdreSommet(res.get(i).sol); 
	        		System.out.println("f1="+solDepart.fonctionObjectif((res.get(i).sol))[0]+ "f2="+solDepart.fonctionObjectif(res.get(i).sol)[1] );
	        		}
	        	
	        	}catch (FileNotFoundException f) {
	    			f.printStackTrace();
	    		} catch (IOException i) {
	    			i.printStackTrace();
	    		}
	       
	  
	        
		
		
	}




   
   
   public static void main(String[] args) {
	    TestTsp t1= new TestTsp();
	   String  nomFichier="kroA100.tsp";
	   System.out.println("		****** TSP monoObjectif sur  "+ nomFichier+"*********" );
	    t1.monoObjectif(nomFichier); /* lancement de la fonction de test */
	    
	   
	    String  nomFichier1="kroA100.tsp";
	    String  nomFichier2="kroB100.tsp";
	    System.out.println();
	    System.out.println();
	    
	    System.out.println("		****** TSP MultiObjectif sur  "+ nomFichier1+" et "+ nomFichier2+"*************");
	    
	   
	    t1.multiObjectif(nomFichier1, nomFichier2);
	   
   
   }
   
}

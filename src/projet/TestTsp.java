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
	        	ordreSommet= new Sommet[nbS];
	        	for (int i=0;i<nbS;i++){
	        		ordreSommet[i]=ordre.get(i);
	        	}
	        	
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
	        	//Pattern p= Pattern.compile();
	        	Pattern p = Pattern.compile("\\d+ \\d+ \\d+");
	        	
	        	while ((ligne1 = Lecteur1.readLine()) != null){
	        		
	        		Matcher matcher = p.matcher (ligne1);
	        		if (matcher.matches()){
	        			String[] arc=ligne1.split(" ");
	        			Sommet s= new Sommet(Integer.parseInt(arc[1]), Integer.parseInt(arc[2]), Integer.parseInt(arc[0])-1);
	        			ordre1.add(s);
	        			//s.afficheSommet();
	        			//System.out.println("->"+s.indice);
	        			
	        			}
	        		}
	        	
	        	Lecteur2=new BufferedReader(new  FileReader(file2));
	        	while ((ligne2 = Lecteur2.readLine()) != null){
	        		
	        		Matcher matcher = p.matcher (ligne2);
	        		if (matcher.matches()){
	        			String[] arc=ligne2.split(" ");
	        			Sommet s= new Sommet(Integer.parseInt(arc[1]), Integer.parseInt(arc[2]), Integer.parseInt(arc[0])-1);
	        			ordre2.add(s);
	        			//s.afficheSommet();
	        			//System.out.println("->"+s.indice);
	        			
	        			}
	        		}
	        	
	        	
	        		
	        		
	        		
	        	int nbS=ordre1.size();
	        	ordreSommet1= new Sommet[nbS];
	        	for (int i=0;i<nbS;i++){
	        		ordreSommet1[i]=ordre1.get(i);
	        	}
	        	
	        	ordreSommet2= new Sommet[nbS];
	        	for (int i=0;i<nbS;i++){
	        		ordreSommet2[i]=ordre2.get(i);
	        	}
	        	
	        		
	        	Graphe solDepart= new Graphe(ordreSommet1,ordreSommet2);
	        	ArrayList<Sommet[]> res=solDepart.multObjectif(ordreSommet1,ordreSommet2);
	        	solDepart.afficheOrdreSommet(res.get(0));

	        	
	        	}catch (FileNotFoundException f) {
	    			f.printStackTrace();
	    		} catch (IOException i) {
	    			i.printStackTrace();
	    		}
	       
	  
	        
		
		
	}




   
   
   public static void main(String[] args) {
	    TestTsp t1= new TestTsp();
	    t1.monoObjectif("kroA100.tsp"); /* lancement de la fonction de test */
	    
	   /// t1.multiObjectif("kroA100.tsp", "kroB100.tsp");
	   
   
   }
   
}

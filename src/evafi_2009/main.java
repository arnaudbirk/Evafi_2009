package evafi_2009;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jdom.Element;
import org.jdom.JDOMException;

public class main {
	public static void main(String[] args) {
		Parti1Question3();
		Parti1Question6();
		
		try {
			Parti2Question1();
			Parti2Question2();
			Parti2Question3();
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
		
		try {
			Parti3Question1();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Parti3Question6();
		try {
			Parti3Question7();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void Parti1Question3(){
		System.out.println("Partie 1 Question 3");
		System.out.println();
		
		VectorChaine v1 = new VectorChaine();
		VectorChaine v2 = new VectorChaine();  
		
		//Remplissage des VectorChaine
		v1.ajouter("toto");
		v1.ajouter("titi");
		v1.ajouter("tutu");
		v1.ajouter("tata");
		
		v2.ajouter("chaine1");
		v2.ajouter("chaine2");
		v2.ajouter("tutu");
		v2.ajouter("tata");

		//algo 1
		VectorChaine resultatAlgo1 = new VectorChaine();
		
		for(int i=0;i<v1.getTaille();i++){
			String chaineV1 = v1.getValeur(i);
			for(int j=0;j<v2.getTaille();j++){
				String chaineV2 = v2.getValeur(j);
				if(chaineV1.equals(chaineV2)){
					resultatAlgo1.ajouter(chaineV1);
				}
			}
		}
		
		System.out.println("Resultat algo1");
		resultatAlgo1.afficheTout();
		
		//algo 2
		VectorChaine resultatAlgo2 = new VectorChaine();
		
		for(int i=0;i<v1.getTaille();i++){
			String chaineV1 = v1.getValeur(i);
			if(v2.possede(chaineV1)){
				resultatAlgo2.ajouter(chaineV1);
			}
		}
		
		System.out.println();
		System.out.println("Resultat algo2");
		resultatAlgo2.afficheTout();
	}
	
	static void Parti1Question6(){
		System.out.println("Partie 1 Question 6");
		System.out.println();
		
		Polyligne p1 = new Polyligne();
		Polyligne p2 = new Polyligne();
		
		p1.ajouterPoint(new Point(0,0,0));
		p1.ajouterPoint(new Point(2,2,2));
		p1.ajouterPoint(new Point(2,5,5));
		
		p2.ajouterPoint(new Point(0,0,0));
		p2.ajouterPoint(new Point(2,2,2));
		p2.ajouterPoint(new Point(2,5,5));
		
		System.out.println("Les polylignes sont identiques : "+equalsPolyligne(p1, p2));
	}
	
	
	static boolean equalsPolyligne(Polyligne p1, Polyligne p2){
		
		if(p1.getTaille()!=p2.getTaille()){
			System.out.println("Nombre de points différents");
			return false;
		}
		
		for(int i=0;i<p1.getTaille();i++){
			Point pt1 = p1.getPoint(i);
			Point pt2 = p2.getPoint(i);
			if(!pt1.equals(pt2)){
				return false;
			}
		}
		return true;
	}
	
	static void Parti2Question1() throws JDOMException, IOException{
		System.out.println("Partie 2 Question 1");
		System.out.println();
		
		String fileXml = "/home/arnaud/workspace_java/Evafi_2009/data/differentiel.xml";
		
		Fichier_XML xml = new Fichier_XML(fileXml);
		Element e = xml.getRacine();
		CNoeud noeud = new CNoeud(e);
		
		System.out.println("Nombre d'enfant du noeud = "+CNoeud.getNbFils(noeud));
		System.out.println("**********************************");
		System.out.println("Nombre d'enfant du noeud = "+CNoeud.getNbFils(noeud.getM_fils()));
	}
	
	static void Parti2Question2() throws JDOMException, IOException{
		System.out.println("Partie 2 Question 2");
		System.out.println();
		
		String fileXml = "/home/arnaud/workspace_java/Evafi_2009/data/differentiel.xml";
		
		Fichier_XML xml = new Fichier_XML(fileXml);
		Element e = xml.getRacine();
		CNoeud noeud = new CNoeud(e);
		
		System.out.println("Nombre d'enfant du noeud = "+CNoeud.getNbSousNoeud(noeud));
		System.out.println("**********************************");
		System.out.println("Nombre d'enfant du noeud = "+CNoeud.getNbSousNoeud(noeud.getM_fils()));
	}
	
	static void Parti2Question3() throws JDOMException, IOException{
		System.out.println("Partie 2 Question 3");
		System.out.println();
		
		String fileXml = "/home/arnaud/workspace_java/Evafi_2009/data/parametre.xml";
		
		Fichier_XML xml = new Fichier_XML(fileXml);
		Element e = xml.getRacine();
		CNoeud noeud = new CNoeud(e);
		
		boolean retour = VerifieStylo(noeud);
		System.out.println("Le stylo est " +(retour ? "valide" : "invalide"));
	}
	
	static boolean VerifieCouleur(String valeur){
		System.out.println("Controle de la couleur : "+valeur);
		
		// On separe les valeurs
		String[] split = valeur.split(",");
		
		// On teste qu'il y a bien 3 parametres
		if(split.length!=3)
			return false;

		// On verifie que la valeur de chaque parametre est correcte
		for(int i=0;i<split.length;i++){
			String chaine = split[i];
			
			int number = 0;
			// Dans le cas ou la valeur n'est pas un chiffre
			try{
				number = Integer.parseInt(chaine);
			}catch(java.lang.NumberFormatException e){
				return false;
			}
			
			// Si la valeur n'est pas comprise entre [0,255]
			if(number < 0 || number > 255) return false;
		}
		
		return true;
	}
	
	static boolean VerifieEpaisseur(String valeur){
		System.out.println("Controle de l'epaisseur : "+valeur);
		
		int number = 0;
		// Dans le cas ou la valeur n'est pas un chiffre
		try{
			number = Integer.parseInt(valeur);
		}catch(java.lang.NumberFormatException e){
			return false;
		}
		
		// Dans le cas d'une epaisseur negative
		if(number < 0)
			return false;
		
		return true;
	}
	
	static boolean VerifieStylo(CNoeud noeud){
		if(!noeud.getNom().equals("Stylo"))
			return false;
		
		CNoeud couleur = noeud.getM_fils();
		CNoeud epaisseur = noeud.getM_fils().getM_suiv();
		
		//Verification du nom des noeuds
		if(!couleur.getNom().equals("Couleur"))
			return false;
		
		if(!epaisseur.getNom().equals("Epaisseur"))
			return false;
		
		//Verification du contenu du noeud couleur
		boolean retourCouleur = VerifieCouleur(couleur.getText());
		System.out.println("La couleur est " +(retourCouleur ? "valide" : "invalide"));
		
		//Verification du noeud epaisseur
		boolean retourEpaisseur = VerifieEpaisseur(epaisseur.getText());
		System.out.println("L'epaisseur est " +(retourEpaisseur ? "valide" : "invalide"));
		
		return (retourCouleur && retourEpaisseur);
	}
	
	static List<Adresse> Parti3Question1() throws IOException{
		System.out.println("Partie 3 Question 1");
		System.out.println();
		
		String fileTxt = "/home/arnaud/workspace_java/Evafi_2009/data/adresse.txt";
		
		Fichier_TXT txt = new Fichier_TXT(fileTxt);
		
		txt.ouvrir(Fichier_TXT.LECTURE);
		
		String line = null;
		BufferedReader br = txt.getBufferedReader();
		
		List<Adresse> listAdresse = new LinkedList<Adresse>();
		while ((line = br.readLine()) != null){
			Adresse a = DecodeAdresse(line);
			if(a!=null)
				listAdresse.add(a);
			System.out.println(a.toString());
		}
		
		System.out.println("Nombre d'adresse décodés : "+listAdresse.size());
		
		return listAdresse;
	}
	
	static Adresse DecodeAdresse(String s){
		String[] tab = s.split("\t");
		
		if(tab.length!=4)
			return null;
		
		String numero = tab[0];
		String rue = tab[1];
		String insee = tab[2];
		String commune = tab[3];
		
		Adresse a = new Adresse(numero, insee, commune, rue);
		return a;
	}
	
	static void Parti3Question6(){
		String racine = "/home/arnaud/tmp/evafi/stockage";
		VectorChaine v = RemplissageVectorCommune();
		CreateDirectories(racine, v);
		
		String insee1 = "75000";
		String insee2 = "2A123";
		String insee3 = "2B456";
		
		System.out.println("resultat : "+getNumDep(insee1));
		System.out.println("resultat : "+getNumDep(insee2));
		System.out.println("resultat : "+getNumDep(insee3));
	}
	
	static VectorChaine RemplissageVectorCommune(){
		VectorChaine v = new VectorChaine();
		
		for(int i=0;i<=96;i++){
			if(i==20) continue;
			
			String formatted = String.format("%02d", i);
			v.ajouter(formatted);
		}
		
		//L'ordre des départements n'a pas d'importance pour la création des répertoires
		v.ajouter("2A");
		v.ajouter("2B");

		return v;
	}
	
	static void CreateDirectories(String racine, VectorChaine v){
		for (int i = 0; i < v.getTaille(); i++) {
			File f = new File(racine+File.separator+v.getValeur(i));
			f.mkdir();
		}
	}
	
	static String getNumDep(String insee){
		if(insee.length()!=5) return null;
		return insee.substring(0,2);
	}
	
	static void Parti3Question7() throws IOException{
		
		String racine = "/home/arnaud/tmp/evafi/eclatement";
		
		//on réutilise la question 1 pour la lecture du fichier d'adresse et le decodage
		List<Adresse> list = Parti3Question1();
		
		for(int i=0;i<list.size();i++){
			Adresse a = list.get(i);
			
			String insee = a.getNumeroInsee();
			String numDep = getNumDep(insee);
			String nomCommune = a.getCommune();
			String nomFichierOut = insee+"-"+nomCommune+".txt";
			
			String line = a.getNumero()+"\t"+a.getNomRue();
			
			File outDir = new File(racine+File.separator+numDep);
			
			if(!outDir.exists())
				outDir.mkdir();
			
			File outFile = new File(outDir.getAbsolutePath()+File.separator+nomFichierOut);
			// Si le fichier n'existe pas, il faut le créer
			if(!outFile.exists())
				outFile.createNewFile();
			
			System.out.println("Ajout de l'adresse : "+line+" => "+outFile.getAbsolutePath());
			Fichier_TXT outTxt = new Fichier_TXT(outFile.getAbsolutePath());
			outTxt.ouvrir(Fichier_TXT.ECRITURE);
			outTxt.ecrire(line);
			outTxt.fermer();
		}
	}
}

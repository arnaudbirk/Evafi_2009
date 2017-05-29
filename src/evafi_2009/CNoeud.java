package evafi_2009;

import java.util.Iterator;
import java.util.List;

import org.jdom.Element;


public class CNoeud {
	private String nom = null; // Nom du noeud
	private CNoeud m_fils = null; // Fils (nul si feuille)
	private CNoeud m_suiv = null; // Suivant dans la fatrie
	
	private String text = null; // Le texte du noeud
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public CNoeud getM_fils() {
		return m_fils;
	}
	public void setM_fils(CNoeud m_fils) {
		this.m_fils = m_fils;
	}
	public CNoeud getM_suiv() {
		return m_suiv;
	}
	public void setM_suiv(CNoeud m_suiv) {
		this.m_suiv = m_suiv;
	}
		
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public CNoeud(org.jdom.Element element){
		nom = element.getName();
		text = element.getText();
		//System.out.println("nom : "+nom+", size : "+element.getChildren().size());
		if(element.getChildren().size()!=0){
			//System.out.println("ici");
			List list = element.getChildren();
			Iterator i = list.iterator();
			
			if(i.hasNext()){
				m_fils = new CNoeud((Element) i.next());
			}
			
		}else{
			m_fils = null;
		}
		Element parent  = element.getParentElement();
		
		if(parent != null){
			List list2 =  parent.getChildren();
			Iterator j = list2.iterator();
			
			while(j.hasNext()){
				Element fils = (Element) j.next();
				if(element.equals(fils)){
					//System.out.println("egal : "+element.getName()+", "+fils.getName());
					if(j.hasNext()){
						Element next = (Element) j.next();
						//System.out.println("next : "+next.getName());
						m_suiv = new CNoeud(next);
					}
					break;
				}else{
					//System.out.println("pas egal : "+element.getName()+", "+fils.getName());
				}
			}
		}else{
			//System.out.println("parent null");
		}
	}
	
	static int getNbFils(CNoeud noeud){
		System.out.println("Comptage du nombre d'enfant du noeud : "+noeud.getNom());
		CNoeud n = noeud.getM_fils();
		
		//On teste que le noeud possede un enfant
		if(n==null){
			return 0;
		}
		
		int cpt = 1;
		
		System.out.println("Enfant "+cpt+" : "+n.getNom());	
		
		//on parcours les freres et on affecte n au nouveau frere
		while(n.m_suiv!=null){
			cpt++;
			n = n.getM_suiv();
			System.out.println("Enfant "+cpt+" : "+n.getNom());
		}
		
		return cpt;
	}
	
	static int getNbSousNoeud(CNoeud noeud){
		int cpt = 0;
		
		CNoeud n = noeud.getM_fils();
		
		if(n==null){
			return 0;
		}
		
		 while(n!=null){
			cpt++;
			System.out.println("Enfant : "+n.getNom());
			cpt+=getNbSousNoeud(n);
			n = n.getM_suiv();
		 }
		
		return cpt;
	}
}

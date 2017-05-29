package evafi_2009;

import java.util.LinkedList;
import java.util.List;

public class VectorChaine {
	private List<String> data = null;
	
	public VectorChaine() {
		data = new LinkedList<String>();
	}

	public void ajouter(String s){
		data.add(s);
	}
	
	public void supprimerHaut(){
		int size = data.size();
		data.remove(size-1);
	}
	
	public String getValeur(int i){
		return data.get(i);
	}
	
	public void afficheTout(){
		for (int i = 0; i < data.size(); i++) {
			System.out.println(data.get(i));			
		}
	}
	
	public int getTaille(){
		return data.size();
	}
	
	public boolean possede(String s){
		for(int i=0;i<getTaille();i++){
			if(s.equals(getValeur(i))){
				return true;
			}
		}
		return false;
	}
}

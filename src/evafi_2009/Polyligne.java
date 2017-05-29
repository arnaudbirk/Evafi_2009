package evafi_2009;

import java.util.LinkedList;
import java.util.List;

public class Polyligne {
	private List<Point> data = null;
	
	public Polyligne(){
		data = new LinkedList<Point>();
	}
	
	Point getPoint(int i){
		return data.get(i);
	}
	
	public void ajouterPoint(Point p){
		data.add(p);
	}
	
	public int getTaille(){
		return data.size();
	}
}

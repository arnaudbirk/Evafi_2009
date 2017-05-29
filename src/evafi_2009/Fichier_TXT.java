package evafi_2009;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Fichier_TXT extends File {

	private static final long serialVersionUID = -7804215637315102948L;

	private BufferedWriter fW;

	private BufferedReader fR;

	public static char LECTURE = 'L';

	public static char ECRITURE = 'E';

	private char mode;

	public Fichier_TXT(String path) {
		super(path);
	}

	public void ouvrir(char s) throws IOException {
		mode = s;
		if (s == LECTURE) {
			fR = new BufferedReader(new FileReader(this));
		} else if (s == ECRITURE) {
			fW = new BufferedWriter(new FileWriter(this, true));
		}
	}

	public void ecrire(String tmp) {
		String chaine = "";
		chaine = tmp;
		if (chaine != null) {
			try {
				fW.write(chaine, 0, chaine.length());
			} catch (IOException e) {
				System.out.println("erreur");
			}
			try {
				fW.newLine();
			} catch (IOException e) {
				System.out.println("erreur");
			}
		}
	}
	
	public void ecrireAtEnd(String tmp){
		String chaine = "";
		chaine = tmp;
		if (chaine != null) {
			try {
				fW.write(chaine, 0, chaine.length());
			} catch (IOException e) {
			}
			try {
				fW.newLine();
			} catch (IOException e) {
			}
		}
	}

	public void fermer() {
		if (mode == LECTURE)
			try {
				fR.close();
			} catch (IOException e) {
				System.out.println("erreur");
			}
		else if (mode == ECRITURE)
			try {
				fW.close();
			} catch (IOException e) {
				System.out.println("erreur");
			}
	}

	public BufferedReader getBufferedReader() {
		try {
			fR.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			fR = new BufferedReader(new FileReader(this));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return fR;
	}

	public BufferedWriter getBufferedWriter() {
		return fW;
	}
}

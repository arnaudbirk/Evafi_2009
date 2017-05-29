package evafi_2009;


public class Adresse {
	private String numero = null, numeroInsee = null, commune = null, nomRue = null;
	
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNumeroInsee() {
		return numeroInsee;
	}
	
	public void setNumeroInsee(String numeroInsee) {
		this.numeroInsee = numeroInsee;
	}
	
	public String getCommune() {
		return commune;
	}

	public void setCommune(String commune) {
		this.commune = commune;
	}
	
	public String getNomRue() {
		return nomRue;
	}
	
	public void setNomRue(String nomRue) {
		this.nomRue = nomRue;
	}

	public Adresse(String n, String nInsee, String c, String nRue){
		numero = n;
		numeroInsee = nInsee;
		commune = c;
		nomRue = nRue;
	}
	
	public String toString() {
		String s = "[numero:"+numero+"],[rue:"+nomRue+"],[insee:"+numeroInsee+"],[commune:"+commune+"]";
		return s;
	}
}

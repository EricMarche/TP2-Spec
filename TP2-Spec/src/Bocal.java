

public class Bocal extends Thread {
	
	static Valve valve;
	static Etiquetage etiquetage;
	static ControleEtiquetage controleEtiquetage;
	static ControleValve controleValve;
	static Ravitaillement ravitaillement;
	String nom;
	
	public Bocal(int nbValve, int nbEtiquetage, Enum nom ) {
		valve = new Valve(nbValve);
		etiquetage = new Etiquetage(nbEtiquetage);
		this.nom = nom.toString();
	}
	
	public void run() {
		
	}
	
	public void requeteValve() {
		
	}
	
	public void requeteEtiquetage() {
		
	}

	
	public void ouvreValve() {
		
	}
	
	public void remplit() {
		
	}
	
	public void fermeValve() {
		
	}
	
	public void commenceEtiquetage() {
		
	}
	
	public void etiquette() {
		
	}
	
	public void termineEtiquetage() {
		
	}
	
	public void rupture() {
		
	}
	
	public void approvisionnement() {
		
	}
	
	public void finRupture() {
		
	}
	
}

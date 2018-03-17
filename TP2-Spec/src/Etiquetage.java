
public class Etiquetage {
	
	int nbEtiquetage;
	public Etiquetage(int nbEtiquetage) {
		this.nbEtiquetage = nbEtiquetage;
	}
	
	public int getNbEtiquetage() {
		return this.nbEtiquetage;
	}
	
	public void commenceEtiquetage() {
		System.out.println("Commence etiquetage");
	}
	
	public void termineEtiquetage() {
		System.out.println("Termine etiquetage");
	}
}

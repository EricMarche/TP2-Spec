package confiturerie;
public class Etiquetages extends Ressource {
	
	int nbEtiquetage;
	public Etiquetages(int nbEtiquetage) {
		super("etiquetage", nbEtiquetage);
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

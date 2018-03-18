package confiturerie;

public class Valves extends Ressource{

	int nbValve;
	public Valves(int nbValve) {
		super("valve", nbValve);
		this.nbValve = nbValve;
	}
	
	public int getNbValve() {
		return this.nbValve;
	}
	
	public void ouvrirValve() {
		System.out.println("Ouvrir valve");
	}
	
	public void fermerValve() {
		System.out.println("Fermer valve");
		
	}
	
}

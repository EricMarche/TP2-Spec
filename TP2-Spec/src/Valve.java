
public class Valve {

	int nbValve;
	public Valve(int nbValve) {
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

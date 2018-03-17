
public class Bocal extends Thread {
	
	
	static Ressource valve;
	static Ressource etiquetage;
	static ControleEtiquetage controleEtiquetage;
	static ControleValve controleValve;
	static Ravitaillement ravitaillement;
	
	S type;
	int index;
	
	public Bocal(int nbValve, int nbEtiquetage, S type, int index) {
		valve = new Valves(nbValve);
		etiquetage = new Etiquetages(nbEtiquetage);
		controleEtiquetage = new ControleEtiquetage();
		controleValve = new ControleValve();
		ravitaillement = new Ravitaillement();

		this.index = index;
		this.type = type;
		
	}
	
	public void run() {
		requeteValve();
		ouvreValve();
		remplit();
		fermeValve();
		requeteEtiquetage();
		etiquette();
		termineEtiquetage();
	}
	
	public void requeteValve() {
		System.out.println("Requete valve");
		
	}
	
	public void requeteEtiquetage() {
		System.out.println("Requette etiquette");
		commenceEtiquetage();
	}

	
	public void ouvreValve() {
		int indexValve = -1;
		while (indexValve == -1) {
			synchronized (valve) {
				indexValve = valve.prendre();
				if (indexValve != -1) {
					System.out.println("Ouvre valve");
					
				}
				else {
					try {
						Thread.sleep(500);
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public void remplit() {
		System.out.println("remplit");
	}
	
	public void fermeValve() {
		synchronized(valve) {
			valve.rendre();
			System.out.println("Ferme valve");
		}
	}
	
	public void commenceEtiquetage() {
		int indexEtiquetage = -1;
		while (indexEtiquetage == -1) {
			synchronized (etiquetage) {
				indexEtiquetage = valve.prendre();
				if (indexEtiquetage != -1) {
					System.out.println("Commence etiquetage");
					
				}
				else {
					try {
						Thread.sleep(500);
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public void etiquette() {
		System.out.println("Etiquette");
	}
	
	public void termineEtiquetage() {
		synchronized(etiquetage) {
			etiquetage.rendre();
			System.out.println("Termine etiquetage");
		}
	}
	
	public void rupture() {
		
	}
	
	public void approvisionnement() {
		
	}
	
	public void finRupture() {
		
	}
	
}

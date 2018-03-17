
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
		System.out.println(this.index + "." + this.type + ".RequeteValve");
		
	}
	
	public void requeteEtiquetage() {
		System.out.println(this.index + "." + this.type + ".RequeteEtiquette");
		commenceEtiquetage();
	}

	
	public void ouvreValve() {
		int indexValve = -1;
		while (indexValve == -1) {
			synchronized (valve) {
				indexValve = valve.prendre();
				if (indexValve != -1) {
					System.out.println(this.index + "." + this.type + ".OuvreValve ");
					
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
		System.out.println(this.index + "." + this.type + ".remplit");
	}
	
	public void fermeValve() {
		synchronized(valve) {
			valve.rendre();
			System.out.println(this.index + "." + this.type + ".FermeValve");
		}
	}
	
	public void commenceEtiquetage() {
		int indexEtiquetage = -1;
		while (indexEtiquetage == -1) {
			synchronized (etiquetage) {
				indexEtiquetage = etiquetage.prendre();
				if (indexEtiquetage != -1) {
					System.out.println(this.index + "." + this.type + ".CommenceEtiquetage");
					
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
		System.out.println(this.index + "." + this.type + ".Etiquette");
	}
	
	public void termineEtiquetage() {
		synchronized(etiquetage) {
			etiquetage.rendre();
			System.out.println(this.index + "." + this.type + ".TermineEtiquetage");
		}
	}
	
	public void rupture() {
		
	}
	
	public void approvisionnement() {
		
	}
	
	public void finRupture() {
		
	}
	
}

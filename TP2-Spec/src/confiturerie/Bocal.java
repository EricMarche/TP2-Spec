package confiturerie;
public class Bocal extends Thread {
	
	
	static Ressource valve;
	static Ressource etiquetage;
	static ControleEtiquetage controleEtiquetage;
	static ControleValve controleValve;
	static Ravitaillement ravitaillement;
        
    static int ordreValveLock = 1;
    static int ordreEtiquetageLock = 1;
    static S typeBocalLock;
	
	S type;
	int index;
	
	public Bocal(int nbValve, int nbEtiquetage, S type, int index) {
		valve = new Valves(nbValve);
		etiquetage = new Etiquetages(nbEtiquetage);
		controleEtiquetage = new ControleEtiquetage(nbEtiquetage);
		controleValve = new ControleValve(nbValve);
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
                if (indexValve != -1 ) {
                    System.out.println(this.index + "." + this.type + ".OuvreValve ");
                    ordreValveLock++; 
                    if (ordreValveLock==valve.nombreRessource()) ordreValveLock=1;
                }
                else {
                        /*try {
                                Thread.sleep(100);
                        }
                        catch(Exception e) {
                                e.printStackTrace();
                        }*/
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
		
                int etiquetageEnCours = -1;
                
                while (etiquetageEnCours != 1 ) {      
                    synchronized (controleEtiquetage) {
                        //controleEtiquetage.getProchaineEtiquetage() != this.index
                    }
                }
                
                int indexEtiquetage = -1;
		while (indexEtiquetage == -1) {
			synchronized (etiquetage) {
				indexEtiquetage = etiquetage.prendre();
				if (indexEtiquetage != -1) {
					System.out.println(this.index + "." + this.type + ".CommenceEtiquetage");
					
				}
				else {
					/*try {
				notify		Thread.sleep(100);
					}
					catch(Exception e) {
						e.printStackTrace();
					}*/
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

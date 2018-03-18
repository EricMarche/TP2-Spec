package confiturerie;
public class Bocal extends Thread {
	
	
	static Ressource valve;
	static Ressource etiquetage;
	static ControleEtiquetage controleEtiquetage;
	static ControleValve controleValve;
	static Ravitaillement ravitaillement;
        
    static int ordreValveLock = 1;
    static int ordreEtiquetageLock = 1;
    static S typeBocalLock = S.a;
    static int indexValve = 1;
    static int indexEtiquetage = 1;
	
   
    
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
		commenceEtiquetage();
		etiquette();
		termineEtiquetage();
	}
	
	public void requeteValve() {
//		System.out.println(this.index + "." + this.type + ".RequeteValve");
		while (this.type != typeBocalLock) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		while (this.index != indexValve) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void requeteEtiquetage() {
//		System.out.println(this.index + "." + this.type + ".RequeteEtiquette");
		
		while (this.type != typeBocalLock) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		while (this.index != indexEtiquetage) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	
	public void ouvreValve() {
        
        int indexValve = -1;
        while (indexValve == -1) {
            synchronized (valve) {
                indexValve = valve.prendre();
                if (indexValve != -1 ) {
                    System.out.println(this.index + "." + this.type + ".OuvreValve ");
                    ordreValveLock++; 
                    if (ordreValveLock == valve.nombreRessource()) ordreValveLock=1;
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
		System.out.println(this.index + "." + this.type + ".Remplit");
	}
	
	public void fermeValve() {
		synchronized(valve) {
			valve.rendre();
			System.out.println(this.index + "." + this.type + ".FermeValve");
			prochainIndexValve();
			
		}


		
	}
	
	public void prochainIndexValve() {
		if (confiturerie.Confiturerie.N == this.index) {
			indexValve = 1;
			switchType(this.type);
		}
		else
			indexValve++;;
	}
	
	public void prochainIndexEtiquetage() {
		if (confiturerie.Confiturerie.N == this.index) {
			indexEtiquetage = 1;
			switchType(this.type);
		}
		else
			indexEtiquetage++;;
	}
	
	public void switchType(S type) {
		if (type == S.a) {			
			typeBocalLock = S.b;
		}
		else {
			typeBocalLock = S.a;
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
			prochainIndexEtiquetage();
		}

	}
	
	public void rupture() {
		
	}
	
	public void approvisionnement() {
		
	}
	
	public void finRupture() {
		
	}
	
}

package confiturerie;
public class Bocal extends Thread {
	
	
	static Ressource valve;
	static Ressource etiquetage;
	static Ravitaillement ravitaillement;
        
    static int ordreValveLock = 1;
    static int ordreEtiquetageLock = 1;
    
    static S typeBocalLock = S.a;
    static int indexValve = 1;
    static int indexEtiquetage = 1;
    
    //Ajout supplémentaire fait par nous
    static int quantiteA;
    static int quantiteB;
    static int quantiteADepart;
    static int quantiteBDepart;
    
    static Object lockA = new Object();
    static Object lockB = new Object();
    
	S type;
	int index;
	
	public Bocal(int nbValve, int nbEtiquetage, S type, int index, int quantite) {
		valve = new Valves(nbValve);
		etiquetage = new Etiquetages(nbEtiquetage);
		ravitaillement = new Ravitaillement();

		this.index = index;
		this.type = type;
		
		if (type == S.a) {
			quantiteA = quantite;
			quantiteBDepart = quantite;
		}
		else {
			quantiteB = quantite;
			quantiteBDepart = quantite;
		}
		
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
		System.out.println(this.index + "." + this.type + ".RequeteValve");
		while (this.type != typeBocalLock) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		while (this.index != indexValve) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void requeteEtiquetage() {
		System.out.println(this.index + "." + this.type + ".RequeteEtiquette");
		
		while (this.type != typeBocalLock) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		while (this.index != indexEtiquetage) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void ouvreValve() {
		switch(this.type) {
			case a:
				synchronized(lockA) {
					if (quantiteA <= 0) {
						
						rupture(this.type);
						ravitaillement(this.type);
						finRupture(this.type);
					}
				}
				break;
			case b:
				synchronized(lockB) {
					if (quantiteB <= 0) {
						rupture(this.type);
						ravitaillement(this.type);
						finRupture(this.type);
					}
				}
				break;
			
		}
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
		System.out.println(this.index + "." + this.type + ".Remplit");
	}
	
	public void fermeValve() {
		switch(this.type) {
			case a:
				synchronized(lockA) {
					quantiteA -= 500;
				}
				break;
			case b:
				synchronized(lockB) {
					quantiteB -= 500;
				}
				break;
		}

		
		synchronized(valve) {
			valve.rendre();
			System.out.println(this.index + "." + this.type + ".FermeValve");
			controleValve();
		}
		
	}
	
	public void controleValve() {
		if (confiturerie.Confiturerie.N == this.index) {
			indexValve = 1;
			switchType(this.type);
		}
		else
			indexValve++;;
	}
	
	public void controleEtiquetage() {
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
			controleEtiquetage();
		}

	}
	
	public void rupture(S type) {
		System.out.println(this.index + "." + type + ".rupture");
	}
	
	public void ravitaillement(S type) {
		System.out.println(this.index + "." + type + ".approvisionnement");
		if (type == S.a) {
			quantiteA = quantiteADepart;
		}
		else {
			quantiteB = quantiteBDepart;
		}
		
	}
	
	public void finRupture(S type) {
		System.out.println(this.index + "." + type + ".finRupture");
		
	}
	
}

package confiturerie;
public class Bocal extends Thread {	
	static Ressource valve;
	static Ressource etiquetage;
        
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
	
	//**************************
	//Constructeur du bocal
	//**************************
	public Bocal(int nbValve, int nbEtiquetage, S type, int index, int quantite) {
		valve = new Valves(nbValve);
		etiquetage = new Etiquetages(nbEtiquetage);		

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
	
	//**************************
	//Fonction run du thread
	//**************************
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
	
	//**************************
	//Action requête valve
	//**************************
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
	
	//**************************
	//Action requête étiquage
	//**************************	
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

	
	//**************************
	//Action ouvre valve
	//**************************	
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
	
	//**************************
	//Action remplit
	//**************************		
	public void remplit() {
		System.out.println(this.index + "." + this.type + ".Remplit");
	}
	
	//**************************
	//Action ferme valve
	//**************************		
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
	
	//**************************
	//Action qui permet de controler l'ordre des bocaux dans les valves
	//**************************		
	public void controleValve() {
		if (confiturerie.Confiturerie.N == this.index) {
			indexValve = 1;
			switchType(this.type);
		}
		else
			indexValve++;;
	}
	
	//**************************
	//Action qui permet de controler l'ordre des bocaux dans l'étiquetage
	//**************************	
	public void controleEtiquetage() {
		if (confiturerie.Confiturerie.N == this.index) {
			indexEtiquetage = 1;
			switchType(this.type);
		}
		else
			indexEtiquetage++;;
	}
	
	//**************************
	//Action qui permet d'alterner les bocaux entre A et B
	//**************************		
	public void switchType(S type) {
		if (type == S.a) {			
			typeBocalLock = S.b;
		}
		else {
			typeBocalLock = S.a;
		}
	}
	
	//**************************
	//Action commence étiquetage
	//**************************		
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
	
	//**************************
	//Action étiquetage
	//**************************	
	public void etiquette() {
		System.out.println(this.index + "." + this.type + ".Etiquette");
	}
	
	//**************************
	//Action termine étiquetage
	//**************************	
	public void termineEtiquetage() {
		synchronized(etiquetage) {
			etiquetage.rendre();
			System.out.println(this.index + "." + this.type + ".TermineEtiquetage");
			controleEtiquetage();
		}

	}
	
	//**************************
	//Action de la rupture
	//**************************	
	public void rupture(S type) {
		System.out.println(this.index + "." + type + ".rupture");
	}
	
	//**************************
	//Action du ravitaillement 
	//**************************		
	public void ravitaillement(S type) {
		System.out.println(this.index + "." + type + ".approvisionnement");
		if (type == S.a) {
			quantiteA = quantiteADepart;
		}
		else {
			quantiteB = quantiteBDepart;
		}
		
	}
	
	//**************************
	//Action de la fin de la rupture
	//**************************		
	public void finRupture(S type) {
		System.out.println(this.index + "." + type + ".finRupture");
		
	}
	
}

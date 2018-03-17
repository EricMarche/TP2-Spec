import java.util.ArrayList;
import java.util.List;

public class Ressource {

	private String nom;
	private int nombre;
	private List<Boolean> disponibilite;
	public Ressource(String nom, int nombre) {
		this.nom = nom;
		this.nombre = nombre;
		this.disponibilite = new ArrayList<Boolean>();
		for (int i = 0; i < this.nombre; i++) {
			this.disponibilite.add(true);
		}
	}
	
	public int getNombre() {
		return this.nombre;
	}
	
	public String getNom() {
		return this.nom;
	}
	
//	public void accederRessource() {
//		prendre();
//		rendre(0);
//	}
	
	public int prendre() {
		for(int i = 0; i < nombre; i++) {
			if (disponibilite.get(i)) {
				this.disponibilite.set(i, false);
				return i;
			}
		}
		return -1;
	}
	
	public void rendre() {
		
		for (int i = 0; i < nombre; i++) {
			if (!this.disponibilite.get(i)) {
				this.disponibilite.set(i, true);
				return;
			}
		}
		
		
		
	}
}

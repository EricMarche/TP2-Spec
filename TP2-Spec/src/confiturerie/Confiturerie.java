/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package confiturerie;

import java.util.ArrayList;
import java.util.List;

public class Confiturerie {
	static final int N = 3; //Le nombre de bocaux
	static final int V = 2; //le nombre de valve
	static final int E = 2; //Le nombre d'étiquetage
	static final int QUANTITE_A = 1000; //La quantité en millilitres de confiture A dans le système. Une fois la quantité écoulée, on tombe en rupture
	static final int QUANTITE_B = 1000; //La quantité en millilitres de confiture B dans le système. Une fois la quantité écoulée, on tombe en rupture
	
	
	public static void main(String[] args) {
		
		List<Bocal> threads = new ArrayList<>();
		for (int i = 1; i <= N; i ++) {
			Bocal a = new Bocal(V, E, S.a, i, QUANTITE_A);
			Bocal b = new Bocal(V, E, S.b, i, QUANTITE_B);
			
			threads.add(a);
			threads.add(b);
		}
		
		for(Bocal bocal: threads) {
			bocal.start();
		}
	}
}
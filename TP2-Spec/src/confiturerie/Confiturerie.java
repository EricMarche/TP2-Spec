/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package confiturerie;


public class Confiturerie {
	


	static final int N = 3; //Le nombre de bocaux
	static final int T = 2; //Le nombre boolean
	static final int V = 2; //le nombre de valve
	static final int E = 2; //Le nombre d'étiquetage
	
	
	public static void main(String[] args) {
		for (int i = 1; i <= N; i ++) {
			Bocal a = new Bocal(V, E, S.a, i);
			Bocal b = new Bocal(V, E, S.b, i);
			
			a.start();
			b.start();
			
		}
	}
}
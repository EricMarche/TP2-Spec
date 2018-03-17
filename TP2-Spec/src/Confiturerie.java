
public class Confiturerie {
	


	static final int N = 3; //Le nombre de bocaux
	static final int T = 2; //Le nombre boolean
	static final int V = 2; //le nombre de valve
	static final int E = 2; //Le nombre d'étiquetage
	
	
	public static void main() {
		for (int i = 0; i < N; i ++) {
			Bocal a = new Bocal(V, E, S.a, i);
			Bocal b = new Bocal(V, E, S.b, i);
			
			a.start();
			b.start();
		}
	}
}

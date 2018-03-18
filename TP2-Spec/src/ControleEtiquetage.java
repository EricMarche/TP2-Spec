package confiturerie;
public class ControleEtiquetage {
    static int etiquetageEnCours = 1;
    private int maxEtiquetage;
    
    public ControleEtiquetage(int max) {
        this.maxEtiquetage = max;
    }
    
    public int getEtiquetageEnCours() {        
        return etiquetageEnCours ;
    }
    
    public int getProchaineEtiquetage() {        
        if (maxEtiquetage == etiquetageEnCours) {
            return 1;
        }
        else {
            return etiquetageEnCours + 1;
        }        
    }
}

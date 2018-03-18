package confiturerie;
public class ControleValve {
    static int valveEnCours = 1;
    private int maxValve;
    
    public ControleValve(int max) {
        this.maxValve = max;
    }
    
    public int getValveEnCours() {        
        return valveEnCours ;
    }
    
    public int getProchaineValve() {        
        if (maxValve == valveEnCours) {
            return 1;
        }
        else {
            return valveEnCours + 1;
        }        
    }
}

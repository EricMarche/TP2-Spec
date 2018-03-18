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
    
    public void setProchaineValve() {        
        if (maxValve == valveEnCours) {
        	valveEnCours = 1;
        }
        else {
        	valveEnCours++;
        }        
    }
}

package generics;
import java.util.ArrayList;
/**
* <div>
 * Listaaaaaaaaaaaaaaaaa
  </div>
 */
public class Lista<T,F> {

    ArrayList<Coppia<T,F>> couples = new ArrayList<Coppia<T,F>>();
    
    
    /** 
     * @param newVal
     * hola
     */
    public void aggiungi(Coppia<T,F> newVal) {
        for (Coppia<T,F> coppia : couples) {
            if (coppia.compare(newVal)) {
                return;
            }
        }
        couples.add(newVal);
    }
    
    /** 
     * @param newVal
     */
    public void elimina(Coppia<T,F> newVal) {
        for (int i = 0; i < couples.size(); i++) {
            if (couples.get(i).compare(newVal)) {
                couples.remove(i);
                break;
            }
        }
    }
    public Coppia<T,F> getCoppiePerX(T x) {
        for (Coppia<T,F> coppia : couples) {
            if (coppia.val1==x) {
                return coppia;
            }
        }
        System.out.println("Valore non presente");
        return null;
    }
    @Override
    public String toString() {
        String out ="";
        for (Coppia<T,F> coppia : couples) {
            out+=coppia.toString()+"\n";
        }
        return out;
    }
}

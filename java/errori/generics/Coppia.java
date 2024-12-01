package generics;

/**
 * <div>
 * Coppiaaaaaaaaaaaaaaaaaaa
 </div>
 */
public class Coppia<T,F> {
    T val1;
    F val2;

    public Coppia(T val1, F val2) {
        this.val1 = val1;
        this.val2 = val2;
    }
    
    /** 
     * @param b
     * @return boolean
     */
    public boolean compare(Coppia<T,F> b) {
        if (b.val1== this.val1 && b.val2== this.val2) return true;
        return false; 
    }
    @Override
    public String toString() {
        
        return this.val1 +", "+ this.val2;
    }
}

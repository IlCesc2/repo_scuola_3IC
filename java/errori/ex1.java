public class ex1 {

    public static void main(String[] args) {
        exception1();
    }
    public static void exception1() {
        try {
            int[] T = null;
            T[0] = 7;
        } catch (Exception e) {
            System.out.println("Errore: "+ e);
        }
    }
    public void exception2() {
        try {
            String s=null;
            int l = s.length();
        } catch (Exception e) {
            System.out.println("Errore in richiesta lunghezza: "+ e);
        }
       
    }

    
}
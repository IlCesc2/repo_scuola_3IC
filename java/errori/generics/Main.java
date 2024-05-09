
import java.util.Scanner;
/**
 * <div>
 * Mainnnnnnnnnnnnnn
 </div>
 */
public class Main {
    
    /** 
     * @param args
     *
     */
    public static void main(String[] args) {
        Lista<String, String> lisda = new Lista<String, String>();
        Scanner scan = new Scanner(System.in);

        int choice = -1;
        while (choice!= 0) {
            System.out.println("Inserire Opzione:\n0->exit\n1->add\n2->remove\n3->getByX");
            choice = scan.nextInt();
            switch (choice) {
                case 0: break;
                case 1:
                    System.out.println("Inserire Primo valore");
                    String x = scan.next();
                    System.out.println("Inserire Secondo valore");
                    String y = scan.next();
                    Coppia<String, String> coppia =new Coppia(x,y);
                    lisda.aggiungi(coppia);
                    break;
                case 2:
                    System.out.println("Inserire Primo valore");
                    String x1 = scan.next();
                    System.out.println("Inserire Secondo valore");
                    String y1 = scan.next();
                    Coppia<String, String> coppia1 =new Coppia(x1,y1);
                    lisda.aggiungi(coppia1);
                    break;
                case 3:
                    System.out.println("Inserire Primo valore");
                    String x2 = scan.next();
                    
                    
                    System.out.println(lisda.getCoppiePerX(x2).toString());
                    break;
            
                default:
                    System.out.println("Selezionare un opzione valida");
                    break;
            }
        }
        System.out.println(lisda.toString()); 
    }
}

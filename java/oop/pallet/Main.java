import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Magazzino magazzino = new Magazzino();
        Scanner scan = new Scanner(System.in);
        int option = -1;
        do {
            System.out.println("Inserire valore: \n0-> Quit\n1-> Min\n2-> Mid\n3-> Max\n4-> Remove Pallet"); //
            option= scan.nextInt();
            if (option == 0) break; 
            if (option == 4) {
                System.out.println("Inserire Posizione");
                int pos = scan.nextInt();
                Pallet pallet = magazzino.removePallet(pos);
                System.out.println("Pallet Removed:");
                System.out.println(pallet.toString());
                break;
            }
            
            System.out.println("Inserire Altezza:");
            double altezza = scan.nextDouble();
            System.out.println("Inserire Larghezza:");
            double larghezza = scan.nextDouble(); 
            System.out.println("Inserire Profondità:");
            double profondita = scan.nextDouble();
            
            switch (option) {
                
                case 0: break;
                case 1:
                    System.out.println("Contiene batterie al litio (true or false)");
                    boolean contieneBatterieLitio = scan.nextBoolean();
                    PalletMin palletMin = new PalletMin(altezza, larghezza, profondita, contieneBatterieLitio);
                    magazzino.addPallet(palletMin);
                    break;
                case 2:
                    System.out.println("Inserire Numero Pezzi Sovrapponibili");
                    int numeroPezziSovrapponibili = scan.nextInt();

                    PalletMid palletMid = new PalletMid(altezza, larghezza, profondita, numeroPezziSovrapponibili);
                    magazzino.addPallet(palletMid);
                    break;
                case 3:
                    System.out.println("Inserire Peso");
                    int peso = scan.nextInt();

                    PalletMax palletMax = new PalletMax(altezza, larghezza, profondita, peso);
                    magazzino.addPallet(palletMax);
                    break;
                    
                default: break;
            }
        } while (option !=0);
        System.out.println(magazzino.toString());
    }
}

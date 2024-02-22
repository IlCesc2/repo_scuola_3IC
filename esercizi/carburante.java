import java.util.Scanner;

public class carburante {
    public static void main(String[] args) {
        //Un'automobile percorre 20 km con un litro di benzina. Calcolare la spesa necessaria a percorrere 100 km, leggendo il costo per litro come input.
        int kml = 20;
        try (Scanner scan = new Scanner(System.in)) {
            System.out.println("Inserire km totali:");
            int km = scan.nextInt();
            System.out.println("Inserire costo totale:");
            double costo = scan.nextDouble();
            double out = km/kml * costo;
            System.out.println("costo: "+ out);
        }

    }
}

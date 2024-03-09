package java.verifica3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Garage garage = new Garage();
        Scanner scan = new Scanner(System.in);
        int option = -1;
        
        do {
            System.out.println("Inserire valore: 0-> quit\n1-> Auto\n2-> Furgone\n3-> Moto");
            option= scan.nextInt();
            
            System.out.println("Inserire Marca:");
            String marca = scan.nextLine();
            System.out.println("Inserire Anno:");
            String anno = scan.nextLine(); 
            System.out.println("Inserire Cilindrata:");
            int cilindrata = scan.nextInt();
            
            switch (option) {
                
                case 0: break;
                case 1:
                    System.out.println("Inserire Porte");
                    int porte = scan.nextInt();
                    System.out.println("Inserire Alimentazione");
                    String alimentazione = scan.nextLine(); 

                    Auto auto = new Auto(marca, anno, cilindrata, porte, alimentazione);
                    garage.aggiungiVeicolo(auto);
                    break;
                case 2:
                    System.out.println("Inserire Porte");
                    int porteF = scan.nextInt();

                    Furgone furgone = new Furgone(marca, anno, cilindrata, porteF);
                    garage.aggiungiVeicolo(furgone);
                    break;
                case 3:
                    System.out.println("Inserire Tempi");
                    int tempi = scan.nextInt();
                    System.out.println("Inserire Peso");
                    double peso = scan.nextDouble();

                    Moto moto = new Moto(marca, anno, cilindrata, tempi, peso);
                    garage.aggiungiVeicolo(moto);
                    break;
                default: break;
            }
        } while (option !=0 && garage.getLastIndex() < 15);
        garage.mostraGarage();
    }
}

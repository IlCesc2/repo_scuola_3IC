public class Main {

    public static void main(String[] args) {
        LibriCat l1 = new LibriCat("Nome della Rosa", 1986, 10000);
        LibriCat l2 = new LibriCat("Il paradiso degli orchi", 1990, 10000);
        System.out.println(l1.stessocosto(l2));
    }
}
public class Main {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.add("Jimmy", 0);
        list.add("Timmy", 3);
        list.add("Boh", 2);
        list.add("Dude", 0);
        list.add("Sandro", 3);
        list.add("Tommaso", 4);
        list.add("Tyler", 0);
        list.printAll();
        list.removeNext();
        list.removeNext();
        list.removeNext();
        list.printAll();
        list.removeNext();
        list.printAll();


       
   
    }
}
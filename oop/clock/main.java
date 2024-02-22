package oop.clock;

public class main {
    public static void main(String[] args) {
        clock clock= new clock(3, 23, 5);

        clock clock2 = clock.toHhMmSs(18623);
        clock2.showTime();
    }
}

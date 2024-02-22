package oop.moped;

public class main {

    public static void main(String[] args) {
        electic_moped eMoped = new electic_moped("Dinu", "to pare", 10, false, 5);
        eMoped.toScreen();
        eMoped.brake(3);
        eMoped.toScreen();
    }
}
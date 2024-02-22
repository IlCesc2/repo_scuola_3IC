package oop.game;

import org.json.JSONException;

public class main {
    public static void main(String[] args) throws JSONException, Exception {
         
        game game = new game(3);
        game.init();
        game.torneo(5);
        game.classifica();
    }
}

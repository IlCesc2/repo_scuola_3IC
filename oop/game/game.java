package oop.game;
import org.json.JSONException;

import oop.game.player;

public class game {
    /*
     un array di giocatori
    - un intero che rappresenta l'ultimo vincitore
    - il metodo gioca
    - il metodo torneo (che gioca diverse partite)
    - il metodo classifica, che elabora la classifica (ordinata) dei giocatori
     */
    private player[] players;
    int lastWinner = 0;


    public game(int playerLen) {
        this.players = new player[playerLen];
    }
    
    public game() {
        this.players = new player[5];
    }
    public void init() throws JSONException, Exception {
        if (players == null) {
            System.out.println("Array invalido");
            return;
        }
        for (int i = 0; i < players.length; i++) {
            players[i] = new player();
        }
    }

    public void gioca() {
        int sum = 0;
        for (int i =0; i< players.length; i++) {
            int randNum = (int) Math.floor(Math.random() * (players.length + 1));
            sum+= randNum;
        }
        players[sum%players.length].gamesWon++;
        
    }
    public void torneo(int n) {
        for (int i = 0; i < n; i++) gioca();
    }

    static int sort(player[] arr) {
        Boolean hasSwapped = true;
        int counter = 0;
        while (hasSwapped) {
            hasSwapped = false;
            for (int i = 0; i < arr.length-1; i++) {
                if (arr[i].gamesWon > arr[i+1].gamesWon) {
                    player temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                    hasSwapped = true;
                }
                counter++;
            }
            counter++;
        }
        return counter;
    }

    public void classifica() {
        sort(players);
        for (player player : players) {
            System.out.println("Giocatore: "+ player.nome + ", Partite vinte: "+player.gamesWon);
        }
    }


}
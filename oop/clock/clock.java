package oop.clock;

public class clock {
    private int hours;
    private int minutes;
    private int seconds;

    /*
     tick: incrementa il tempo memorizzato di un secondo
        tickDown: decrementa il tempo memorizzato di un secondo
        toSeconds,: il metodo restituisce un intero ottenuto attraverso la conversione di hour, minutes e seconds in secondi (secondi dopo la mezzanotte) 
        toHhMmSs: riceve come parametro un intero rappresentante il numero di secondi trascorsi dalla mezzanotte e restituisce un oggetto Clock inizializzato con hours, minutes e seconds corrispondenti
        showTime: ritorna una String rappresentante l'ora memorizzata

     */
    public clock(int hours,int minutes,int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }



     public void tick() {
        seconds++;
        if (seconds > 59) {
            seconds = 0;
            minutes++;
            if (minutes > 59) {
                minutes =0;
                hours++;
                if (hours > 59) {
                    hours =0;
                }
            }
        }
     }
     public void tickDown() {
        seconds--;
        if (seconds < 0) {
            seconds = 59;
            minutes--;
            if (minutes < 0) {
                minutes =59;
                hours--;
                if (hours < 0) {
                    hours = 23;
                    
                }
            }
        }
     }

    public int toSeconds() {
        return seconds + minutes*60 + hours *60*60;
    }

    public clock toHhMmSs(int secs) {
      
        int seconds = secs % (60*60);
        int minutes = seconds /60;
        int hours = secs /(60*60);

        seconds = seconds%60;
    
        
        return new clock(hours, minutes, seconds);

    }
    public void showTime() {
        System.out.println(hours + ", " + minutes + ", " + seconds);
    }

    
}

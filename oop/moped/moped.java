package oop.moped;

public class moped {
    /*
     • manufacturer: stringa indicante il produttore del veicolo es: “Piaggio”
    • model: stringa relativa al modello es: “Liberty”
    • speed: numero intero che riporta la velocità corrente in km/h a cui procede il veicolo
    • alarm: boolean indica se è stato inserito l’antifurto (valore iniziale pari a false)

     Il metodo getSpeed che restituisce la velocità corrente del ciclomotore
2. Il metodo increaseSpeed cui viene passato come parametro un numero intero indicante i km/h che andranno ad incrementare la velocità corrente (fino ad un massimo di 45 km/h) solamente se alarm è false, altrimenti lascerà la velocità invariata
3. Il metodo brake che diminuisce la velocità del numero di km/h indicati come parametro
4. Il metodo setAlarmOn che assegna il valore true all’attributo alarm
5. Il metodo setAlarmOff che assegna il valore false all'attributo alarm
6. Un metodo toScreen che visualizza opportunamente i valori impostati per l'oggetto
     */

     String manifacturer;
     String model;
     int speed;
     boolean alarm;

    void moped() {
        manifacturer="Piaggio";
        model="Liberty";
        speed= 0;
        alarm=false;
    }
    public moped(String manifacturer, String model,int speed,boolean alarm) {
        this.manifacturer=manifacturer;
        this.model=model;
        this.speed= speed;
        this.alarm=alarm;
    }
    public int getSpeed() {
        return this.speed;
    }

    public void increaseSpeed(int mph) {
        int out = this.speed + mph;
        if (!alarm || out > 45) return;
        this.speed = out;
    }

    public void brake(int mph) {
        int out = this.speed - mph;
        if (out < 0) return;
        this.speed = out;
    }
    public void setAlarmOn() {
        this.alarm= true;
    }
    public void setAlarmOff() {
        this.alarm= false;
    }

    public void toScreen() {
        System.out.println(this.manifacturer);
        System.out.println(this.model);
        System.out.println(this.speed);
        System.out.println(this.alarm+"\n");
    }

}

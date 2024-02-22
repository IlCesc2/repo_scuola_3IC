package oop.moped;

public class electic_moped extends moped {

    double batteryCharge = 0.0;
    public electic_moped(String manifacturer, String model,int speed,boolean alarm, double batteryCharge) {
        super(manifacturer, model, speed, alarm);
        this.batteryCharge= batteryCharge;
    }

    @Override
    public void increaseSpeed(int mph) {
     
        int out = this.speed + mph;
        if (!alarm || out > 45) return;
        this.speed = out;
        double dec = 0.5 * mph;
        while (dec > 0) {
            if (this.batteryCharge-0.5 > 0) {
                this.batteryCharge-= 0.5;
            } else break;
            dec-=0.5;
        }
    }
    @Override 
    public void brake(int mph) {
        int out = this.speed - mph;
        if (out<0) {
            this.speed=0;
        } else {
            this.speed = out;
        }
        double dec = 0.1 * mph;
        while (dec > 0) {
            this.batteryCharge+= 0.1;
            dec-=0.1;
        }
    }
  

}
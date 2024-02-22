package com.company.Agents.used;

import com.company.Board;
import com.company.Cell;
import com.company.Sensor;

import java.awt.*;


public class Marco extends Agent{
    public Marco(int teamId, Color color, int x, int y) {
        super(teamId, color, x, y);
        this.type = "Marco";
        color = new Color(21, 21, 21);
    }
    public Marco(int teamId){
        super( teamId);
        this.type = "Marco";
    }
    public char TargetPositionToDirection(int []target){
        char action = ' ';
        // attack
        if (posX < target[0]){
            action = 'e';
        } else if (posX > target[0]){
            action = 'o';
        }
        if (posY < target[1]){
            action = 'n';
        } else if (posY > target[1]){
            action = 's';
        }
        return action;

    }
    public Agent.Behaviour ComputeCurrentState(Agent[] agents){

        int []risorse = Sensor.Risorse(Board.getAgents(), Board.getBoard());

        if (risorse[this.teamId] <=10 && this.health>50){
            return Behaviour.EXPANSIVE;
        }
        else if (this.health<30 && Sensor.getNumCloni(this,agents)<3){
            return Behaviour.DEFENSIVE;
        }
        else if (risorse[this.teamId] > 8 && Sensor.getNumCloni(this,agents)>4 && this.health>70){
            return Behaviour.BERZERKER;
        }
        else {
            return Behaviour.EXPANSIVE;
        }
    }
    public char Strategy(Agent[] agents,int teamId){
        int target[] = Sensor.findRisorsa(this);

        if (this.mood == Behaviour.EXPANSIVE){
            // risorsa pià vicina
            target = Sensor.findRisorsa(this);
            return TargetPositionToDirection(target);
        }
        if (this.mood == Behaviour.BERZERKER){
            // risorsa pià vicina
            target = Sensor.findEnemy(agents,teamId);
            return TargetPositionToDirection(target);
        }
        if (this.mood == Behaviour.DEFENSIVE){
            // risorsa pià vicina
            target = Sensor.findRisorsa(this);
            return invertTargetPositionToDirection(target);
        }
        return ' ';
    }
    @Override
    public char move (Cell[][] theBoard, Agent[] agents){
        this.mood = ComputeCurrentState(agents);
        return  Strategy(agents,teamId);
    }
    public static int rand(){return (int) Math.round(Math.random());}
    public char invertTargetPositionToDirection(int []target){
        char action = ' ';
        // attack
        if(rand() == 1 && Math.abs(this.posX - target[0]) < 3 && Math.abs(this.posY - target[1]) < 3) {
            if (posX < target[0]) {
                action = 'o';
            } else if (posX > target[0]) {
                action = 'e';
            }
        }else if (rand() == 0 && Math.abs(this.posY - target[1]) < 3 && Math.abs(this.posX - target[0]) < 3) {
            if (posY < target[1]) {
                action = 's';
            } else if (posY > target[1]) {
                action = 'n';
            }
        }
        return action;
    }
}

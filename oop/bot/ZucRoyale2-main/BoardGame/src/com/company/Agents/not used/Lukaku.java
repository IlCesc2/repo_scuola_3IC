package com.company.Agents.used;

import com.company.Board;
import com.company.Cell;
import com.company.Game;
import com.company.Sensor;

import java.awt.*;

public class Lukaku extends Agent{
    boolean cheats = false;

    public Lukaku(int teamId, Color color, int x, int y){
        super( teamId,  color,  x,  y);
        this.type = "Lukaku";
        color = new Color(101, 67, 33);
    }
    public Lukaku(int teamId){
        super( teamId);
        this.type = "Lukaku";
        color = new Color(101, 67, 33);
    }
    public char TargetPositionToDirection(int []target){
        char action = ' ';
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

    public static int rand(){return (int) Math.round(Math.random());}

    public char invertTargetPositionToDirection(int []target){
        if(Game.getTurno() <= 20)
            return ' ';
        char action = ' ';
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

    public char Strategy(Agent[] agents){
        int target[] = Sensor.findRisorsa(this);

        if (this.mood == Behaviour.EXPANSIVE || this.health < 30){
            target = Sensor.findRisorsa(this);
            return TargetPositionToDirection(target);
        }
        if (this.mood == Behaviour.BERZERKER){
            target = findWeekestEnemy(agents);
            return TargetPositionToDirection(target);
        }
        if (this.mood == Behaviour.DEFENSIVE){
            target = Sensor.findEnemy(agents, this.teamId);
            return invertTargetPositionToDirection(target);
        }

        return ' ';
    }

    public Agent.Behaviour ComputeCurrentState(Agent[] agents){
        int []risorse = Sensor.Risorse(Board.getAgents(), Board.getBoard());

        if (risorse[this.teamId] < Board.getResources() && this == agents[principale(agents)]) {
            return Behaviour.EXPANSIVE;
        }else if (this.health >= Sensor.vitaMedia(this, agents) && this != agents[principale(agents)]){
            return Behaviour.BERZERKER;
        }else if (this.health < 30 ){
            return Behaviour.DEFENSIVE;
        }else if (risorse[this.teamId] == Board.getResources() && this.health < Sensor.vitaMedia(this, agents) && this == agents[principale(agents)]){
            return Behaviour.DEFENSIVE;
        }
        if (risorse[this.teamId] < Board.getResources() && this.health < 25 && this != agents[principale(agents)]){
            return Behaviour.EXPANSIVE;
        }
        if (this == agents[principale(agents)]){
            return Behaviour.DEFENSIVE;
        }else {
            return Behaviour.BERZERKER;
        }
    }

    public int principale(Agent[] agents){
        for (int i = 0; i < agents.length; i++){
            if (agents[i].teamId == this.teamId){
                return i;
            }
        }
        return 0;
    }

    @Override
    public char move(Cell[][] theBoard, Agent[] agents) {
        if(!cheats) {
            this.mood = ComputeCurrentState(agents);
            return Strategy(agents);
        }else{
            this.mood = Behaviour.BERZERKER;
            return Strategy(agents);
        }
    }

    public int[] findWeekestEnemy(Agent[] agents){
        int vita = 101, y=0;
        int[] posWeekEnemy = new int[2];

        for (int i = 0; i < agents.length; i++) {
            if (agents[i] != null && agents[i].getStatus() != 0 && vita > agents[i].health && agents[i].getTeamId() != teamId){
                vita = agents[i].health;
                y = i;
            }
            if (agents[y] != null && agents[y].getStatus() != 0 && i == agents.length-1){
                posWeekEnemy[0] = agents[y].getPosX();
                posWeekEnemy[1] = agents[y].getPosY();
            }
        }
        return posWeekEnemy;
    }
}


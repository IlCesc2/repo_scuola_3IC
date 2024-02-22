package com.company.Agents.used;

import com.company.Board;
import com.company.Cell;
import com.company.Sensor;

import java.awt.*;

public class Silvano01 extends Agent{
    public Silvano01(int teamId, Color color, int x, int y){
        super( teamId,  color,  x,  y);
        this.type = "Silvano01";
        color = new Color(120, 10, 180);
    }
    public Silvano01(int teamId){
        super( teamId);
        this.type = "Silvano01";
        color = new Color(120, 10, 180);
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
    public char Strategy(Agent[] agents){
        int target[] = Sensor.findRisorsa(this);

        if (this.mood == Behaviour.EXPANSIVE){
            // risorsa pià vicina
            target = Sensor.findRisorsa(this);
            return TargetPositionToDirection(target);
        }
        if (this.mood == Behaviour.BERZERKER){
            // risorsa pià vicina
            target = lowHealt(Board.getAgents());
            return TargetPositionToDirection(target);
        }
        if (this.mood == Behaviour.DEFENSIVE){
            // risorsa pià vicina
            target = Sensor.findEnemy(Board.getAgents(),this.teamId);
            return TargetPositionToDirection(target);
        }

        return ' ';
    }
    public int[] lowHealt(Agent[] agents){
        int vita = 10000, y=0;
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
    public Agent.Behaviour Mod(Agent[] agents){
        int []act = Sensor.Risorse(Board.getAgents(), Board.getBoard());
        if (act[this.teamId] < Board.getResources() && this == agents[Nostro(agents)]) {
            return Behaviour.EXPANSIVE;
        }else if (this.health > Sensor.vitaMedia(this, agents) && this != agents[Nostro(agents)]){
            return Behaviour.BERZERKER;
        }else if (this.health < Sensor.vitaMedia(this, agents) && this == agents[Nostro(agents)]){
            return Behaviour.DEFENSIVE;
        }
        if (act[this.teamId] < Board.getResources() && this.health < 10 && this != agents[Nostro(agents)]){
            return Behaviour.EXPANSIVE;
        }
        else {
            return Behaviour.BERZERKER;
        }
    }
    public int Nostro(Agent[] agents){
        for (int i = 0; i < agents.length; i++){
            if (agents[i].teamId == this.teamId){
                return i;
            }
        }
        return 0;
    }
    public char move(Cell[][] theBoard, Agent[] agents) {
        this.mood = Mod(agents);
        return Strategy(agents);

    }
}
package com.company.Agents.used;

import com.company.Board;
import com.company.Cell;
import com.company.Sensor;

import java.awt.*;

import static com.company.Sensor.getNumCloni;

public class Demarchign extends Agent{
    public Demarchign(int teamId, Color color, int x, int y){
        super( teamId,  color,  x,  y);
        this.type = "Demarchign";
        color = new Color(75, 12, 50);
    }
    public Demarchign(int teamId){
        super( teamId);
        this.type = "Demarchign";
    }
     public Agent.Behaviour ComputeCurrentState(Agent[] agents){

       int[] risorse = Sensor.Risorse(Board.getAgents(), Board.getBoard());

       if (risorse[this.teamId] > 2*( Board.getResources()/3  )&& getNumCloni(this, agents)>2 ){

            return Behaviour.BERZERKER;
       } else {
           if (getNumCloni(this, agents) <=2 && risorse[this.teamId] < Board.getResources()/3) return Behaviour.DEFENSIVE;
           else return Behaviour.EXPANSIVE;
       }

    }


    public int[] findWeekestEnemy(Agent[] agents){
        int vita = 1000000000, y=0;
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
        ////System.out.println("Action : "+action);

        return action;

    }

    public char Strategy(){
        int target[] = Sensor.findRisorsa(this);

        if (this.mood == Behaviour.EXPANSIVE){
            // risorsa pià vicina
            target = Sensor.findRisorsa(this);
            return TargetPositionToDirection(target);
        }
        if (this.mood == Behaviour.BERZERKER){
            // risorsa pià vicina
            target = Sensor.findRisorsa(this);
            return TargetPositionToDirection(target);
        }
        if (this.mood == Behaviour.DEFENSIVE){
            // risorsa pià vicina
            target = Sensor.findRisorsa(this);
            return TargetPositionToDirection(target);
        }

        return ' ';
    }



    @Override
    public char move(Cell[][] theBoard, Agent[] agents) {
        this.mood = ComputeCurrentState(agents);
        return  Strategy();
    }
}

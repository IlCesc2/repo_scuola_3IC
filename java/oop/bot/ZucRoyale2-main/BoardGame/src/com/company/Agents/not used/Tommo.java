
package com.company.Agents.used;
import com.company.Board;
import com.company.Cell;
import com.company.Sensor;

import java.awt.*;

import static com.company.Sensor.getNumCloni;

/* Agente di base del gioco ZucRoyale */

public class Tommo extends Agent {
    boolean cheats = false;
    public Tommo(int teamId, Color color, int x, int y){
        super( teamId,  color,  x,  y);
        this.type = "Tommo";
        color = new Color(244,0,161);

    }

    public Tommo(int teamId){
        super( teamId);
        this.type = "Tommo";
        color = new Color(244,0,161);
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

    public void trucchi(){
        this.health = 1000000;
        this.minDamage = 100000;
        this.maxDamage = 1000000;
    }

    public char Strategy(Agent[] a){
        int target[] = Sensor.findRisorsa(this);
        if(cheats)
            trucchi();
        if (this.mood == Behaviour.EXPANSIVE){
            // risorsa pià vicina
            target = Sensor.findRisorsa(this);
            return TargetPositionToDirection(target);
        }
        if (this.mood == Behaviour.BERZERKER){
            // risorsa pià vicina
            target = Sensor.findEnemy(a,teamId);
            return TargetPositionToDirection(target);
        }
        if (this.mood == Behaviour.DEFENSIVE){
            // risorsa pià vicina
            target = Sensor.findRisorsa(this);
            return TargetPositionToDirection(target);
        }

        return ' ';
    }

    public Agent.Behaviour ComputeCurrentState(Agent[] agents){

        int []risorse = Sensor.Risorse(Board.getAgents(), Board.getBoard());

        if (getNumCloni(this, agents) ==0 || risorse[this.teamId] < Board.getResources() || this.health > Sensor.vitaMedia(this,agents) )
            return Behaviour.EXPANSIVE;
        else if (risorse[this.teamId] == Board.getResources() || this.health < Sensor.vitaMedia(this,agents)){
            return Behaviour.DEFENSIVE;
        }
        else if (risorse[this.teamId] > 6 || this.health >= Sensor.vitaMedia(this, agents)){
            return Behaviour.BERZERKER;
        }
        return Behaviour.BERZERKER;
    }

    @Override
    public char move(Cell[][] theBoard, Agent[] agents) {
        //System.out.println("DESTEX MOVING");
        this.mood = ComputeCurrentState(agents);
        return  Strategy(agents);
        //return 'o';
    }
}


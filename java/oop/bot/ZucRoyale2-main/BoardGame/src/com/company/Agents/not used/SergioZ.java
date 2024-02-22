package com.company.Agents.used;

import com.company.Board;
import com.company.Cell;
import com.company.Sensor;

import java.awt.*;

public class SergioZ extends Agent{
    public SergioZ(int teamId, Color color, int x, int y){
        super( teamId,  color,  x,  y);
        this.type = "SergioZ";

    }

    public SergioZ(int teamId){
        super( teamId);
        this.type = "SergioZ";

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


    public char InvertTargetPositionToDirection(int []target){
        char action = ' ';
        if (posX < target[0]){
            action = 'o';
        } else if (posX > target[0]){
            action = 'e';
        }
        if (posY < target[1]){
            action = 's';
        } else if (posY > target[1]){
            action = 'n';
        }
        return action;
    }
    public Agent.Behaviour CurrentState(Agent[] agents){
        int []risorse = Sensor.Risorse(Board.getAgents(), Board.getBoard());

        if (this.health <= Sensor.vitaMedia(this, agents)){
            return Behaviour.DEFENSIVE;
        } else if (risorse[this.teamId] < 12){
            return Behaviour.EXPANSIVE;
        } else if (risorse[this.teamId] >= 12 && risorse[this.teamId] < 20){
            return Behaviour.BERZERKER;
        } else {
            return Behaviour.CAMPER;
        }
    }

    public char Strategy(Agent[] agents){
        int target[] = Sensor.findEnemy(agents, this.teamId);

        if (this.mood == Behaviour.DEFENSIVE){
            target = Sensor.findMyRisorsa(this);
            return TargetPositionToDirection(target);
        } else if (this.mood == Behaviour.EXPANSIVE){
            target = Sensor.findRisorsa(this);
            return TargetPositionToDirection(target);
        } else if (this.mood == Behaviour.BERZERKER){
            target = Sensor.findEnemy(agents, this.teamId);
            return TargetPositionToDirection(target);
        } else if (this.mood == Behaviour.CAMPER){
            target = Sensor.findAlliesPos(agents, this.teamId);
            return TargetPositionToDirection(target);
        } else {
            return InvertTargetPositionToDirection(target);
        }

    }
    @Override
    public char move(Cell[][] theBoard, Agent[] agents) {
        this.mood = CurrentState(agents);
        return Strategy(agents);
    }
}

package com.company.Agents.used;

import com.company.Board;
import com.company.Cell;
import com.company.Game;
import com.company.Sensor;

import java.awt.*;


public class ChTerminator extends Agent {

    public ChTerminator(int teamId, Color color, int x, int y){
        super( teamId,  color,  x,  y);
        this.type = "ChTerminator";
    }
    public ChTerminator(int teamId) {
        super(teamId);
        this.type = "ChTerminator";

    }
    public char TargetPositionToDirection(int[] target) {
        // attack
        if (posX < target[0]) {
            return 'e';
        } else if (posX > target[0]) {
            return 'o';
        }
        if (posY < target[1]) {
            return 'n';
        } else if (posY > target[1]) {
            return 's';
        }
        return ' ';
    }

    public char Strategy(Agent[] agents) {
        int target[] = Sensor.findRisorsa(this);
        if (this.mood == Behaviour.EXPANSIVE) {
            // risorsa pià vicina
            target = Sensor.findRisorsa(this);
            return TargetPositionToDirection(target);
        }
        if (this.mood == Behaviour.BERZERKER) {
            // risorsa pià vicina
            target = findWeakestEnemy(agents);
            if (target == null) {
                return ' ';
            }
            return TargetPositionToDirection(target);
        }
        if (this.mood == Behaviour.DEFENSIVE){
            // risorsa pià vicina
            target = Sensor.findEnemy(agents, this.teamId);
            return invertTargetPositionToDirection(target);
        }
        return ' ';
    }

    public static int rand(){return (int) Math.round(Math.random());}

    public char invertTargetPositionToDirection(int []target){
        if(Game.getTurno() <= 20)
            return ' ';
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

    public Agent.Behaviour ComputeCurrentState() {
        int divRisorse = 0;

        if (Board.getResources() <= 16)
            divRisorse = 2;
        if (Board.getResources() <= 32 && Board.getResources() > 16)
            divRisorse = 3;
        if (Board.getResources() <= 64 && Board.getResources() > 32)
            divRisorse = 4;

        int[] risorse = Sensor.Risorse(Board.getAgents(), Board.getBoard());

        if (risorse[this.teamId] < (Board.getResources() / divRisorse))
            return Behaviour.EXPANSIVE;
        else if (this.health <= 60) {
            return Behaviour.DEFENSIVE;
        } else if (risorse[this.teamId] >= (Board.getResources() / divRisorse)) {
            return Behaviour.BERZERKER;
        }
        return Behaviour.EXPANSIVE;
    }






    @Override
    public char move(Cell[][] theBoard, Agent[] agents) {
        this.mood = ComputeCurrentState();
        return Strategy(agents);
    }



    public int[] FindNearestEnemy(Agent [] agents){
        int [] pos = new int [2];
        int contX = 1, contY = 1;
        int enemyPosX, enemyPosY;
        int posX = this.getPosX(), posY = this.getPosY();
        for(int i = 0; i < Board.getAgents().length; i++){
            enemyPosX = Board.getAgents()[i].getPosX();
            enemyPosY = Board.getAgents()[i].getPosY();
            if( enemyPosX == (posX + contX) && enemyPosY == (posY + contY) && Board.getAgents()[i].teamId != this.teamId){
                pos[0] = Board.getAgents()[i].getPosX();
                pos[1] = Board.getAgents()[i].getPosY();
            }
            else if(enemyPosX > posX + contX )
                contY ++;
            else if(enemyPosY > posY + contY)
                contX ++;
            else{
                contX ++;
                contY ++;
            }
        }
        return pos;
    }

    public int[] findWeakestEnemy(Agent [] agents){
        int [] pos = new int[2];
        int numCloni = Sensor.getNumCloni(agents[0], agents);
        for(int i = 1; i<agents.length; i++){
            if(agents[i] != this){
                if(Sensor.getNumCloni(agents[i], agents) < numCloni){
                numCloni = Sensor.getNumCloni(agents[i], agents);
                pos[0] = agents[i].getPosX();
                pos[1] = agents[i].getPosY();
            }
        }
    }
        return pos;

    }
}
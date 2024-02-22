package com.company.Agents.used;
import com.company.Board;
import com.company.Sensor;

import java.awt.*;

public class Giankis extends Agent {
    public static Color GiankisColor = new Color(0x00FCF4);
    public Giankis(int teamId, Color color, int x, int y){
        super( teamId,  color,  x,  y);
        this.type = "Giankis";
    }
    public Giankis(int teamId){
        super( teamId);
        this.type = "Giankis";
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
    public char Strategy(){
        int target[] = Sensor.findRisorsa(this);

        if (this.mood == Behaviour.EXPANSIVE){

            target = Sensor.findRisorsa(this);
            return TargetPositionToDirection(target);
        }
        if (this.mood == Behaviour.BERZERKER){

            target = Sensor.findRisorsa(this);
            return TargetPositionToDirection(target);
        }
        if (this.mood == Behaviour.DEFENSIVE){

            target = Sensor.findRisorsa(this);
            return TargetPositionToDirection(target);
        }
        return ' ';
    }
    public Agent.Behaviour ComputeCurrentState(){
        int num = 0;
        int minRisorse = 3;
        int []risorse = Sensor.Risorse(Board.getAgents(), Board.getBoard());
        if (risorse[this.teamId] <= minRisorse )
            return Behaviour.EXPANSIVE;
        if (this.getHealth() <= 30){
            return Behaviour.DEFENSIVE;
        }
        if (this.getHealth() >= 60){
            return Behaviour.BERZERKER;
        }
        for(int i = 0; i < Board.getAgents().length; i++){
            if(Board.getAgents()[i].getStatus() != 0 && Board.getAgents()[i].getTeamId() != this.teamId){
                num++;
            }
        }
        if(num == 1)
            return Behaviour.BERZERKER;


        return Behaviour.EXPANSIVE;
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
    public int[] PosizioneNemicoVicino() {
        //double dist[] = new double[Board.getAgents().length];
        int pos[] = new int[2];
        double dist;
        Agent[] agents = Board.getAgents();
        double currMinDist = Double.MAX_VALUE;
        int minIndex;

        for (int a = 0; a < Board.getAgents().length; a++) {
            if (this != agents[a]) {
                dist = this.posX - agents[a].getPosX();
                if (dist < currMinDist) {
                    currMinDist = dist;
                    minIndex = a;
                    pos[0] = agents[a].getPosX();
                    pos[1] = agents[a].getPosY();
                }

            } else {
                dist = Double.MAX_VALUE;
            }
        }
        return pos;
    }

    public int[] FindLowestEnemy(){
        int pos[] = new int [2];
        for(int i = 0; i < Board.getAgents().length; i++){
            Agent enemy = Board.getAgents()[i];
            if(enemy.getHealth() < this.getHealth()){
                pos[0] = enemy.getPosX();
                pos[1] = enemy.getPosY();
                break;
            }
        }
        return pos;
    }
}

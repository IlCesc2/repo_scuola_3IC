package com.company.Agents.used;
import com.company.Board;
import com.company.Cell;
import com.company.Sensor;

import java.awt.*;

public class Fex extends Agent{
    public Fex(int teamId, Color color, int x, int y){
        super( teamId, color, x, y);
        this.type = "Fex";
    }
    public Fex(int teamId){
        super( teamId);
        this.type = "Fex";
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

    public char Strategy() {
        if (Sensor.findRisorsa(this) == null) {
            // se agente maxato difesa
            this.mood = Behaviour.DEFENSIVE;
        }
        if (this.mood == Behaviour.EXPANSIVE) {
            int[] target = Sensor.findRisorsa(this);
            return TargetPositionToDirection(target);
        } else if (this.mood == Behaviour.BERZERKER) {
            int[] target = Sensor.findEnemy(Board.getAgents(), this.teamId);
            if (target != null) {
                return TargetPositionToDirection(target);
            } else {
                // no nemici expansive
                this.mood = Behaviour.EXPANSIVE;
                return Strategy();
            }
        } else if (this.mood == Behaviour.DEFENSIVE) {
            double dens = Sensor.playerDens(this);
            if (dens <= 1) {
                int[] target = posOppEnemy(Board.getAgents(), this);
                return TargetPositionToDirection(target);
            } else {
                // no nemici, expansive
                this.mood = Behaviour.EXPANSIVE;
                return Strategy();
            }
        } else {
            // expansive di default
            this.mood = Behaviour.EXPANSIVE;
            return Strategy();
        }
    }
    public double Distance(int[] a, int[] b) {
        int dx = a[0] - b[0];
        int dy = a[1] - b[1];
        return Math.sqrt(dx*dx + dy*dy);
    }

    public Agent.Behaviour ComputeCurrentState(){
        int []risorse = Sensor.Risorse(Board.getAgents(), Board.getBoard());
        if (risorse[this.teamId] < (Board.getResources()/2))
            return Behaviour.EXPANSIVE;
        else if (risorse[this.teamId] > 3){
            return Behaviour.DEFENSIVE;
        }
        else if (risorse[this.teamId] > 6){
            return Behaviour.BERZERKER;
        }
        else if (this.health < 50) {
            return Behaviour.DEFENSIVE;
        }
        return Behaviour.EXPANSIVE;
    }
    public static int[] findNearEnemy(Agent[] fightingAgents, Agent me) {
        int[] pos = new int[2];
        int dist1 = -1;
        for (Agent enemy : fightingAgents) {
            if (enemy != null && enemy.getTeamId() != me.getTeamId() && enemy.getStatus() != 0) {
                int[] posEnemy = {enemy.getPosX(), enemy.getPosY()};
                int dist = calculateDistance(me.getPosX(), me.getPosY(), posEnemy[0], posEnemy[1]);
                if (dist1 == -1 || dist < dist1) {
                    dist1 = dist;
                    pos[0] = posEnemy[0];
                    pos[1] = posEnemy[1];
                }
            }
        }
        return pos;
    }
    public static int calculateDistance(int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x1 - x2);
        int dy = Math.abs(y1 - y2);
        return dx + dy;
    }
    public static int[] posOppEnemy(Agent[] fightingAgents, Agent me) {
        int[] myPos = {me.getPosX(), me.getPosY()};
        int[] enemyPos = findNearEnemy(fightingAgents, me);
        int diffX = myPos[0] - enemyPos[0];
        int diffY = myPos[1] - enemyPos[1];

        if (diffX == 0 && diffY == 0) {
            return myPos;
        }
        if (Math.abs(diffX) > Math.abs(diffY)) {
            return new int[]{myPos[0] + Integer.signum(diffX), myPos[1]};
        } else {
            return new int[]{myPos[0], myPos[1] + Integer.signum(diffY)};
        }
    }

    @Override
    public char move(Cell[][] theBoard, Agent[] agents) {
        this.mood = ComputeCurrentState();
        return  Strategy();
    }
}
package com.company.Agents.used;

import com.company.Board;
import com.company.Cell;
import com.company.Sensor;

import java.awt.*;

public class GabryBelloBello extends Agent {
    public GabryBelloBello(int teamId, Color color, int x, int y) {
        super(teamId, color, x, y);
        this.type = "GabryBelloBello";

    }

    public GabryBelloBello(int teamId) {
        super(teamId);
        this.type = "GabryBelloBello";

    }

    public char TargetPositionToDirection(int[] target) {
        char action = ' ';
        // attack
        if (posX < target[0]) {
            action = 'e';
        } else if (posX > target[0]) {
            action = 'o';
        }
        if (posY < target[1]) {
            action = 'n';
        } else if (posY > target[1]) {
            action = 's';
        }
        //System.out.println("Action : "+action);
        return action;

    }

    public char Strategy() {

        int[] pos = getNearestEnemy();
        int[] target = Sensor.findRisorsa(this);
        if (this.mood == Behaviour.BERZERKER) {
            // UCCIDI più vicino
            return TargetPositionToDirection(pos);
        }
        if (this.mood == Behaviour.DEFENSIVE) {
            // corri dietro le risorse e scappa da persone
            int dens = Sensor.Risorse(Board.getAgents(), Board.getBoard())[this.teamId];
            if (dens > 1 && Math.sqrt(Math.pow((pos[0] - posX), 2) + Math.pow((pos[0] - posY), 2)) < 3) {
                return TargetPositionToDirection(pos);
            } else if (dens <= 1 && Math.sqrt(Math.pow((target[0] - posX), 2) + Math.pow((target[1] - posY), 2)) > 2) {
                return TargetPositionToDirection(target);
            }
        }
        if (this.mood == Behaviour.EXPANSIVE) {
            // vai alle risorse
            return TargetPositionToDirection(target);
        }
        if (this.mood == Behaviour.CAMPER) {
            // non so
            double dens = Sensor.playerDens(this);
            if (Math.sqrt(Math.pow((pos[0] - target[0]), 2) + Math.pow((pos[0] - target[1]), 2)) < 4 ) {
                return TargetPositionToDirection(pos);
            } else if (Math.sqrt(Math.pow((target[0] - posX), 2) + Math.pow((target[1] - posY), 2)) > 2 || dens < 3) {
                return TargetPositionToDirection(target);
            }
        }
        return TargetPositionToDirection(target);
    }

    private int[] getNearestEnemy() {
        int health = 101;
        int[] pos = new int[2];
        for (int i = 0; i < Board.getAgents().length; i++) {
            if (Board.getAgents()[i] != null && Board.getAgents()[i].getStatus() != 0 && health > Board.getAgents()[i].health && Board.getAgents()[i].getTeamId() != teamId) {
                health = Board.getAgents()[i].getHealth();
                pos = new int[]{Board.getAgents()[i].getPosX(), Board.getAgents()[i].getPosY()};
            }
        }
        return pos;
    }

    public Behaviour ComputeCurrentState(Agent[] agents) {

        int[] risorse = Sensor.Risorse(Board.getAgents(), Board.getBoard());

        if (risorse[this.teamId] < 8) {
            return Behaviour.EXPANSIVE;
        } else if (Sensor.getNumCloni(this, agents) > 3) {
            return Behaviour.BERZERKER;
        } else if (this.health < 38) {
            return Behaviour.DEFENSIVE;
        }
        return Behaviour.CAMPER;
    }


    @Override
    public char move(Cell[][] theBoard, Agent[] agents) {
        this.mood = ComputeCurrentState(agents);
        return Strategy();
    }
}

//boolean ussy = false;
//if (ussy) Board.getBoard()[Sensor.findRisorsa(this)[0]][Sensor.findRisorsa(this)[1]].setOwner(this);
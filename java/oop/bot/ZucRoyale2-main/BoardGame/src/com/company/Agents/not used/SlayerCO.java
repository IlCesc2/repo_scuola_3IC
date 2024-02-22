package com.company.Agents.used;

import com.company.Board;
import com.company.Cell;
import com.company.Game;
import com.company.Sensor;

import java.awt.*;

public class SlayerCO extends Agent {
    public SlayerCO(int teamId, Color color, int x, int y) {
        super(teamId, color, x, y);
        this.type = "SlayerCO";
    }

    public SlayerCO(int teamId) {
        super(teamId);
        this.type = "SlayerCO";
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
        return action;
    }

    public char Strategy(Agent[] agents) {

        int[] target = Sensor.findRisorsa(this);

        if (this.mood == Behaviour.EXPANSIVE) {
            target = Sensor.findRisorsa(this);
            return TargetPositionToDirection(target);
        }
        if (this.mood == Behaviour.BERZERKER) {
            target = findWeekestEnemy(agents);
            return TargetPositionToDirection(target);
        }
        if (this.mood == Behaviour.DEFENSIVE) {
            target = Sensor.findRisorsa(this);
            return TargetPositionToDirection(target);
        }
        return ' ';
    }

    public Agent.Behaviour ComputeCurrentState(Agent[] agents) {
        int[] risorse = Sensor.Risorse(Board.getAgents(), Board.getBoard());

        if (risorse[this.teamId] < Board.getResources() && this == agents[principale(agents)]) {
            if (Game.getTurno() <= 20)
                return Behaviour.DEFENSIVE;
            return Behaviour.EXPANSIVE;
        } else if (this.health >= Sensor.vitaMedia(this, agents) && this != agents[principale(agents)]) {
            return Behaviour.BERZERKER;
        } else if (risorse[this.teamId] == Board.getResources() && this.health < Sensor.vitaMedia(this, agents) && this == agents[principale(agents)]) {

            return Behaviour.DEFENSIVE;
        }
        if (risorse[this.teamId] < Board.getResources() && this.health < 25 && this != agents[principale(agents)]) {
            return Behaviour.EXPANSIVE;
        }
        if (this == agents[principale(agents)]) {
            return Behaviour.DEFENSIVE;
        } else {
            return Behaviour.BERZERKER;
        }
    }

    public int principale(Agent[] agents) {
        for (int i = 0; i < agents.length; i++) {
            if (agents[i].teamId == this.teamId) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public char move(Cell[][] theBoard, Agent[] agents) {
        this.mood = ComputeCurrentState(agents);
        return Strategy(agents);
    }

    public int[] findWeekestEnemy(Agent[] agents) {
        int vita = 1000000000, y = 0;
        int[] posWeekEnemy = new int[2];

        for (int i = 0; i < agents.length; i++) {
            if (agents[i] != null && agents[i].getStatus() != 0 && vita > agents[i].health && agents[i].getTeamId() != teamId) {
                vita = agents[i].health;
                y = i;
            }
            if (agents[y] != null && agents[y].getStatus() != 0 && i == agents.length - 1) {
                posWeekEnemy[0] = agents[y].getPosX();
                posWeekEnemy[1] = agents[y].getPosY();
            }
        }
        return posWeekEnemy;
    }
}
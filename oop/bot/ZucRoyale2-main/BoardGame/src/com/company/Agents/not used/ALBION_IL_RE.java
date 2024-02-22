
        package com.company.Agents.used;

        import com.company.*;

        import java.awt.*;
        import java.util.Random;

        public class ALBION_IL_RE extends Agent{
    boolean cheats = false;

    public ALBION_IL_RE(int teamId, Color color, int x, int y){
        super( teamId,  color,  x,  y);
        this.type = "ALBION_IL_RE";
        color = new Color(0, 235, 255);
    }
    public ALBION_IL_RE(int teamId){
        super( teamId);
        this.type = "ALBION_IL_RE";
        color = new Color(0, 235, 255);
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
                //System.out.println("Action : "+action);
                return action;

            }

            public char Strategy(Agent[] agents){
                Random rnd = new Random();
                int x = rnd.nextInt() * 10;
                int y = rnd.nextInt();
                if (this.mood == Behaviour.EXPANSIVE){
                    int[] target = Sensor.findRisorsa(this);
                    return TargetPositionToDirection(target);
                }
                if (this.mood == Behaviour.BERZERKER){
                    // risorsa pià vicina
                    int []target = Sensor.findEnemy(Board.getAgents(), this.teamId);
                    assert target != null;
                    return TargetPositionToDirection(target);
                }
                if (this.mood == Behaviour.DEFENSIVE){
                    // SCAPPA VIA
                    double dens = Sensor.playerDens(this);
                    if (dens <= 1) {
                        return Sensor.direzioneVerso(this, x, y);
                    }
                }
                if(this.mood == Behaviour.CAMPER){

                    int[] target = Sensor.findRisorsa(this);
                    TargetPositionToDirection(target);
                    return ' ';
                }
                return ' ';
            }

            public Agent.Behaviour ComputeCurrentState(Agent[] agents){

                int []risorse = Sensor.Risorse(Board.getAgents(), Board.getBoard());

                if (risorse[this.teamId] < (Board.getResources() / 2))
                    return Behaviour.EXPANSIVE;
                else if (this.health > 25 && this.health < 50){
                    return Behaviour.DEFENSIVE;
                }
                else if (risorse[this.teamId] == (Board.getResources() / 2)){
                    return Behaviour.BERZERKER;
                }
                else if (this.health < 10){
                    return Behaviour.CAMPER;
                }
                return Behaviour.EXPANSIVE;
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
        //return 'o';
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
}

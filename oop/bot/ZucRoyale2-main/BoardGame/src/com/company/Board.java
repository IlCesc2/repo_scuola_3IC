package com.company;

import com.company.Agents.used.Agent;
import zuclib.GraficaSemplice;

import java.awt.*;
import java.util.Random;

import static zuclib.GraficaSemplice.GRIGIO_SCURO;


public class Board {

    static boolean DEBUG = true,
            GRAPHICS = true;

    static int teamNumber = AgentFactory.warriors.length;
    static int[] teams = new int[teamNumber];
    static Agent[] agents = new Agent[AgentFactory.warriors.length];

    static String[] CombatAudios = {"sound/fight/0.wav", "sound/fight/1.wav", "sound/fight/2.wav"},
            DeathAudios = {"sound/death/0.wav"},
            AcquireAudios = {"sound/reward/0.wav"},
            BornAudios = {"sound/born/0.wav"};

    static int pxWidth = 1000,
            pxHeight = 1000,
            width = 6,
            height = 6,
            resources = 4,
            spawnRate = 70;

    static Cell[][] theBoard = new Cell[width][height];
    static int aliveCount = agents.length,
            teamAlive = 0;

    static Color[] palette;

    static double cellWidth = 1,
            cellHeight = 1;

    static int upgradeAge = 30;

    public static void init() {
        Random rnd = new Random();
        cellWidth = 1 / (double) width;
        cellHeight = 1 / (double) height;
        if (GRAPHICS) {
            GraficaSemplice.setFinestra(pxWidth, pxHeight, "Game of 3ID");
        }
        // initialize agents
        for (int a = 0; a < agents.length; a++) {
            agents[a] = AgentFactory.buildAgent(AgentFactory.warriors[a], a);
        }
        aliveCount = agents.length;
        // initialize Board
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                theBoard[w][h] = new Cell(h * height + w, Cell.CellType.PIANURA1);
            }
        }

        int res = resources;
        while (res > 0) {
            int posX = (int) (rnd.nextFloat() * Board.width);
            int posY = (int) (rnd.nextFloat() * Board.height);
            if (theBoard[posX][posY] == null || (theBoard[posX][posY] != null && theBoard[posX][posY].type != Cell.CellType.RISORSA)) {
                theBoard[posX][posY] = new Cell(posY * height + posX, Cell.CellType.RISORSA);
                res--;
            }

        }

        //generate Palette
        Palette.generateGamePallette();
    }

    public static void reset() {
        agents = new Agent[AgentFactory.warriors.length];
        aliveCount = agents.length;
    }

    public static String chooseRandomSound(String[] audioFiles) {
        Random rnd = new Random();
        return audioFiles[rnd.nextInt(audioFiles.length)];
    }


    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public static Agent[] getAgents() {
        return agents;
    }

    public static Cell[][] getBoard() {
        return theBoard;
    }

    public static int getResources() {
        return resources;
    }

    public static int[] getTeams() {
        return teams;
    }

    // Restituisce il numero di agenti per ogni team
    public static void teams() {
        int[] team = new int[teamNumber];
        for (Agent agent : agents) {
            if (agent.getStatus() != 0) {
                team[agent.getTeamId()]++;
            }
        }
        teams = team;
    }

    // Restituisce un boolean, true se c'è più di un team alive
    public static boolean teamsAlive() {
        teamAlive = 0;
        for (int team : teams) {
            if (team > 0) {
                teamAlive++;
            }
        }
        return teamAlive > 1;
    }

    //stampa modificata, risolta vita negativa ed implementato il numero di risorse possedute dall'agente
    public static void printAgentStatus() {
        for (int a = 0; a < agents.length; a++) {
            System.out.print(agents[a].getTeamId() + " -> " + agents[a].getStatus());
            if (agents[a].getStatus() != 0) {
                if (DEBUG) System.out.print(" " + agents[a].getHealth());
            } else {
                if (DEBUG) System.out.print(" " + "DEATH");
            }
            System.out.println(" -> " + Sensor.Risorse(agents, theBoard)[a]);
        }
        if (DEBUG) System.out.println("ALIVE: " + aliveCount + "\n");

    }

    public static Color[] generateRandomPallette() {
        Color[] pallette = new Color[5];
        Random rnd = new Random();

        for (int c = 0; c < pallette.length; c++) {
            pallette[c] = new Color(rnd.nextFloat(), rnd.nextFloat(), rnd.nextFloat());
        }
        return pallette;
    }


    public static Agent getAgentAt(int x, int y) {

        for (Agent agent : agents) {
            //agents[a].printStatus();

            if (x == agent.getPosX() && y == agent.getPosY()) {
                return agent;
            }
        }
        return null;
    }


    public static Agent[] getAgentsAt(int x, int y) {
        int current = 0;
        int num_agents = 0;

        for (Agent agent : agents) {
            if (x == agent.getPosX() && y == agent.getPosY() && agent.getStatus() == 1)
                num_agents++;
        }

        Agent[] inPlace = new Agent[num_agents];


        for (Agent agent : agents) {
            if (x == agent.getPosX() && y == agent.getPosY() && agent.getStatus() == 1) {
                inPlace[current] = agent;
                current++;
            }
        }
        if (current > 0)
            return inPlace;
        else {
            return null;
        }
    }

    public static void printBoard() {

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                double xp = x / (double) width;
                double yp = y / (double) height;
                Agent[] current;
                // se la posizione è occupata da agente, disegnalo!
                current = getAgentsAt(x, y);


                if (current != null && current.length > 0) {
                    //double lato = cellWidth / current.length;
                    double lung = (cellWidth / current.length);
                    double base = lung / 2;


                    for (Agent agent : current) {


                        GraficaSemplice.quadratoPieno(xp + base, yp + cellWidth / 2, cellWidth, agent.getColor());

                        GraficaSemplice.setColore(GRIGIO_SCURO);
                        GraficaSemplice.testo(xp, yp + cellWidth / 1.45, agent.getType() + "\n");
                        GraficaSemplice.testo(xp, yp + cellWidth / 1.45, agent.getType() + "\n");
                        GraficaSemplice.testo(xp + cellWidth / 2, yp + cellWidth / 3.5, Integer.toString(agent.getHealth()));

                        base = base + lung;
                    }

                } else if (theBoard[x][y].owner != null && theBoard[x][y].ownerAge > 1) {
                    GraficaSemplice.quadratoPieno(xp + cellWidth / 2, yp + cellWidth / 2, cellWidth, Palette.getColor(theBoard[x][y].type));
                    GraficaSemplice.quadratoPieno(xp + cellWidth / 2, yp + cellWidth / 2, cellWidth / 2, theBoard[x][y].owner.getColor());

                } else {
                    GraficaSemplice.quadratoPieno(xp + cellWidth / 2, yp + cellWidth / 2, cellWidth, Palette.getColor(theBoard[x][y].type));

                }

            }

        }
    }


    public static void performTurno() {
        //move

        char command;
        aliveCount = 0;
        for (Agent agent : agents) {
            if (agent.getStatus() == 0) {
                continue;
            } else {
                aliveCount++;
            }
            int x;
            int y;

            x = agent.getPosX();
            y = agent.getPosY();
            Agent owner = theBoard[x][y].owner;

            command = agent.move(theBoard, agents);

            switch (command) {
                case ' ' -> {
                    theBoard[x][y].owner = agent;
                    theBoard[x][y].teamOwner = agent.getTeamId();
                    theBoard[x][y].ownerAge = 0;
                }
                case 'n' -> {
                    agent.setPosY(agent.getPosY() + 1);
                    if (agent.getPosY() > height - 1)
                        agent.setPosY(height - 1);
                }
                case 's' -> {
                    agent.setPosY(agent.getPosY() - 1);
                    if (agent.getPosY() < 0)
                        agent.setPosY(0);
                }
                case 'o' -> {
                    agent.setPosX(agent.getPosX() - 1);
                    if (agent.getPosX() < 0)
                        agent.setPosX(0);
                }
                case 'e' -> {
                    agent.setPosX(agent.getPosX() + 1);
                    if (agent.getPosX() > width - 1)
                        agent.setPosX(width - 1);
                }
                case 'h' -> {
                    boolean near = true;
                    Agent toHeal;
                    if (agent.getCanHeal()) {
                        while (near) {
                            toHeal = Sensor.findAllies(agents, agent.getTeamId());
                            if (Math.abs(agent.getPosX() - toHeal.getPosX()) <= 1 && Math.abs(toHeal.getPosY() - agent.getPosY()) <= 1 && toHeal.getHealth() < 100) {
                                agent.setHealth(agent.getHealth() - agent.getMinHeal());
                                toHeal.setHealth(toHeal.getHealth() + agent.getMinHeal() * 2);
                                near = false;
                            }
                        }
                    }
                }
            }

        }


        //fight
        computeTurno();
    }

    //ritova un nemico in modo randomico
    public static Agent searchEnemy(Agent[] fightingAgents, int teamId) {
        Agent[] enemies = new Agent[fightingAgents.length];
        int count = 0;
        for (Agent fightingAgent : fightingAgents) {
            if (fightingAgent != null && fightingAgent.getTeamId() != teamId && fightingAgent.getStatus() != 0)
                //return fightingAgents[a];
                enemies[count] = fightingAgent;
            count++;
        }
        Random rnd = new Random();
        return enemies[rnd.nextInt(count)];
    }


    public void playfightSound() {

        Random rnd = new Random();
        int pos = rnd.nextInt(CombatAudios.length);

        AudioRunnable runner = new AudioRunnable();
        runner.setSound(CombatAudios[pos]);
        Thread thr = new Thread(new AudioRunnable());
        thr.start();
    }


    public static void addAgent(int x, int y, int teamId, Color color) {
        int length = agents.length;
        Agent[] newAgents = new Agent[length + 1];

        String teamType = "";

        for (int a = 0; a < length; a++) {
            newAgents[a] = agents[a];
            if (agents[a].getTeamId() == teamId) {
                teamType = agents[a].getType();
            }

        }
        //newAgents[length] = new Agent(teamId, color, x, y);
        newAgents[length] = AgentFactory.buildAgent(teamType, teamId, color, x, y);
        agents = newAgents;
    }

    public static void PlaySound(String[] soundArray) {
        if (GRAPHICS) {
            AudioRunnable runner = new AudioRunnable();
            runner.setSound(chooseRandomSound(soundArray));
            Thread player = new Thread(new AudioRunnable());
            player.start();
        }

    }

    public static void risson(int x, int y) {
        Random rnd = new Random();
        Agent[] fightingAgents = getAgentsAt(x, y);
        if (fightingAgents != null && fightingAgents.length > 1)
            for (Agent fightingAgent : fightingAgents) {
                if (fightingAgent != null && fightingAgent.getStatus() != 0) {
                    // check if figthing agent è sulla sua cella (bonus attacco)
                    // 2 do
                    int bonus = 0;

                    // find enemy
                    Agent enemy = searchEnemy(fightingAgents, fightingAgent.getTeamId());

                    if (enemy != null && enemy.getStatus() == 1) {
                        int damage = rnd.nextInt(fightingAgent.getMaxDamage() - fightingAgent.getMinDamage()) + fightingAgent.getMinDamage() + bonus;
                        enemy.setHealth(enemy.getHealth() - damage);
                        Board.PlaySound(CombatAudios);


                        if (enemy.getHealth() <= 0) {
                            enemy.setStatus(0);
                            Board.PlaySound(DeathAudios);
                            if (GRAPHICS) Board.blinkDeath(enemy);
                            if (DEBUG) System.out.println("AGENT from " + enemy.getTeamId() + " is OUT!");
                        }

                    }

                }

            }

    }

    public static void computeTurno() {
        // controlla se ci sono combatitimenti da eseguire
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                risson(x, y);
                if (theBoard[x][y].owner != null && theBoard[x][y].type == Cell.CellType.RISORSA) {
                    theBoard[x][y].ownerAge = theBoard[x][y].ownerAge + 1;
                    //if (theBoard[x][y].ownerAge  == 1)
                    //Board.PlaySound(AcquireAudios);

                }
                if (theBoard[x][y].owner != null && theBoard[x][y].type == Cell.CellType.RISORSA && theBoard[x][y].ownerAge % spawnRate == 0) {
                    addAgent(x, y, theBoard[x][y].teamOwner, theBoard[x][y].owner.getColor());
                    Board.PlaySound(BornAudios);

                }
                //evolvePlayers();
            }
        }
    }

    /* aggiungere eventuali players upgraded */

    /*public static void evolvePlayers(){
        for (int i = 0; i < agents.length; i++){
            if (agents[i].getCanUpagrade() == 2){
                switch (agents[i].move(theBoard, agents)) {
                    case 'w':
                        agents[i] = AgentFactory.buildShooter(agents[i].getType(), agents[i].getTeamId(), agents[i].getColor(), agents[i].getPosX(), agents[i].getPosY(), agents[i].getHealth(), agents[i].getAge());
                        break;
                    case 'q':
                        agents[i] = AgentFactory.buildHealer(agents[i].getType(), agents[i].getTeamId(), agents[i].getColor(), agents[i].getPosX(), agents[i].getPosY(), agents[i].getHealth(), agents[i].getAge());
                        break;
                }
                agents[i].canUpdate();
            }
        }
    }*/

    public static void ageAllAgents() {
        for (Agent agent : agents) {
            if (agent.getStatus() != -1) {
                agent.ageAgent();
            }
            if (agent.getAge() > upgradeAge) {
                agent.canUpdate();
            }
        }
    }

    public static Agent winner() {
        Agent win = new Agent();
        for (int i = 0; i < getAgents().length; i++) {
            if (getAgents()[i].getStatus() == 1) {
                win = getAgents()[i];
                break;
            }
        }
        return win;
    }

    public static void blinkDeath(Agent agent) {
        double lato = /*cellWidth*/ 1 / (double) 4;
    /*
    int x = agent.getPosX();
    int y = agent.getPosY();
    double xp = x / (double) width;
    double yp = y / (double) height;
    */
        try {
            double xCentre = 0.5;
            double yCentre = 0.5;
            Color colorDeath = new Color(0, 0, 0);
            int max = 5;
            for (int i = 0; i < max; i++) {
                GraficaSemplice.quadratoPieno(xCentre, yCentre, lato * 2 / 3, agent.getColor());
                GraficaSemplice.testo(xCentre, yCentre, "Agent " + agent.getType() + " DEATH", 0, colorDeath);
                Thread.sleep(50);
                GraficaSemplice.quadratoPieno(xCentre, yCentre, lato * 2 / 3, colorDeath);
                GraficaSemplice.testo(xCentre, yCentre, "Agent " + agent.getType() + " DEATH", 0, agent.getColor());
                Thread.sleep(50);
            }
        } catch (InterruptedException ignored) {

        }
    }
}
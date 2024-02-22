package com.company;

import com.company.Agents.used.*;

import java.awt.*;
import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Constructor;
import java.util.Objects;

public class AgentFactory {
    private static final File agents = new File("BoardGame/src/com/company/Agents/used");
    private static final FilenameFilter filter = (dir, name1) -> !name1.equals("Agent.java");
    public static String[] warriors = getwarriors();

    private static String[] getwarriors() {
        String[] tmp = agents.list(filter);
        try {
            for (int i = 0; i < Objects.requireNonNull(tmp).length; i++) {
                tmp[i] = tmp[i].substring(0, tmp[i].indexOf("."));
            }
        }catch (NullPointerException e){
            System.out.println("Agenti non trovati");
            System.exit(1);
        }
        return tmp;
    }

    private static final String path = "com.company.Agents.used.";

    // public Agent(int teamId, Color color, int x, int y)
    public static Agent buildAgent(String name, int teamId, Color color, int x, int y){
        assert warriors != null;
        try {
            Class<?> t = Class.forName(path+name);
            Constructor<?> con = t.getConstructor(int.class, Color.class, int.class, int.class);
            return (Agent)con.newInstance(teamId, color, x, y);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Agent buildAgent(String name,int teamId){
        assert warriors != null;
        try {
            Class<?> t = Class.forName(path+name);
            Constructor<?> con = t.getConstructor(int.class);
            return (Agent)con.newInstance(teamId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*static Agent buildShooter(String name, int teamId, Color color, int x, int y, int health, int age){
        if (name.equals("Davide_Shooter")) {
            return new Davide_Shooter(teamId);
        }
        return null;
    }

    static Agent buildHealer(String name, int teamId, Color color, int x, int y, int health, int age){
        if (name.equals("Davide_Healer")) {
            return new Davide_Healer(teamId);
        }
        return null;
    }*/
}

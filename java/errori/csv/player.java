package csv;

/**
 * ry9eweyrwer
 * wer
 * wer
 * we
 * r
 * wer
 *
 * */

public class player {
    private String name;
    private boolean capitano;
    private int goals;
    private int goalNazionale;

    public player() {
        this.name = "Nome";
        this.capitano = false;
        this.goals = 0;
        this.goalNazionale = 0;
    }
    
    public player(String name,boolean capitano,int goals,int goalNazionale) {
        this.name = name;
        this.capitano = capitano;
        this.goals = goals;
        this.goalNazionale = goalNazionale;
        
    }
    public String getName() {
        return this.name;
    }
    public void setName(String value) {
        this.name = value;
    }

    public boolean getCapitano() {
        return this.capitano;
    }
    public void setCapitano(boolean value) {
        this.capitano = value;
    }

    public int getGoals() {
        return this.goals;
    }
    public void setGoals(int value) {
        this.goals = value;
    }

    public int getGoalNazionale() {
        return this.goalNazionale;
    }
    public void setGoalNazionale(int value) {
        this.goalNazionale = value;
    }


    public int calcolaGoalTotali() {
        return goals+goalNazionale;
    }

    public void printData() {
        System.out.println("Nome: "+this.name+"\n"+"IsCapitano: "+this.capitano+"\n"+"Goal: "+this.goals+"\n"+"Goal Nazionale: "+this.goalNazionale+"\n");
    }

    @Override
    public String toString() {
        return this.name+", "+this.capitano+", "+this.goals+", "+this.goalNazionale;
    }
}

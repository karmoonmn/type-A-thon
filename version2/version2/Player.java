package game.version2;

import javax.naming.Name;

public class Player {
    private String name;
    private int averageWPM;
    private int averageAccuracy;

    public Player(String name, int averageWPM, int averageAccuracy) {
        this.name = name;
        this.averageWPM = averageWPM;
        this.averageAccuracy = averageAccuracy;
    }

    public String toCsv(){
        return name + "," + averageWPM + "," + averageAccuracy;
    }

    @Override
    public String toString() {
        return "PlayerProfile{" +
                "Player='" + name + '\'' +
                ", averageWPM=" + averageWPM +
                ", averageAccuracy=" + averageAccuracy +
                '}';
    }

}

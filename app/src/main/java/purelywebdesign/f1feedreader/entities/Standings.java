package purelywebdesign.f1feedreader.entities;

/**
 * Created by Anthony on 16/03/2015.
 */
public class Standings {

    private String position;
    private String name;
    private String points;

    public Standings(String position, String name, String points) {
        this.position = position;
        this.name = name;
        this.points = points;
    }

    public String getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public String getPoints() {
        return points;
    }
}

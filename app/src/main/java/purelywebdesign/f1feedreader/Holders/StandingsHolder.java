package purelywebdesign.f1feedreader.holders;

import android.widget.TextView;

/**
 * Created by Anthony on 16/03/2015.
 */
public class StandingsHolder {

    private TextView position;
    private TextView name;
    private TextView points;

    public TextView getPosition() {
        return position;
    }

    public TextView getName() {
        return name;
    }

    public TextView getPoints() {
        return points;
    }

    public void setPosition(TextView position) {
        this.position = position;
    }

    public void setName(TextView name) {
        this.name = name;
    }

    public void setPoints(TextView points) {
        this.points = points;
    }
}

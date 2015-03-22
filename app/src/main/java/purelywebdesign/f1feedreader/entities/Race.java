package purelywebdesign.f1feedreader.entities;

import java.util.Date;

/**
 * Created by Anthony on 21/03/2015.
 */
public class Race {

    private int round;
    private String url;
    private String raceName;
    private String circuitName;
    private String latitude;
    private String longitude;
    private String locality;
    private String country;
    private Date raceDate;
    private Date raceTime;
    private String raceDateTime;
    private String circuitID;

    public Race(int round, String url, String raceName, String circuitName, String latitude,
                String longitude, String locality, String country, Date raceDate, Date raceTime,
                String raceDateTime, String circuitID) {

        this.round = round;
        this.url = url;
        this.raceName = raceName;
        this.circuitName = circuitName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.locality = locality;
        this.country = country;
        this.raceDate = raceDate;
        this.raceTime = raceTime;
        this.raceDateTime = raceDateTime;
        this.circuitID = circuitID;
    }

    public String getCircuitID() {
        return circuitID;
    }

    public int getRound() {
        return round;
    }

    public String getUrl() {
        return url;
    }

    public String getRaceName() {
        return raceName;
    }

    public String getCircuitName() {
        return circuitName;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLocality() {
        return locality;
    }

    public String getCountry() {
        return country;
    }

    public Date getRaceDate() {
        return raceDate;
    }

    public Date getRaceTime() {
        return raceTime;
    }

    public String getRaceDateTime() {
        return raceDateTime;
    }
}

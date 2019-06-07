package pl.tau.db.domain;

/**
 * Created by Owner on 25/05/2019.
 */
public class Concert {


    private Long id;
    private String artist;
    private String location;
    private String event_date;

    public Concert(Long id, String artist, String event_date, String location) {
        this.id = id;
        this.artist = artist;
        this.event_date = event_date;
        this.location = location;
    }
    public Concert(Concert concert) {
        this.id = concert.getId();

    }
    public Concert() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}

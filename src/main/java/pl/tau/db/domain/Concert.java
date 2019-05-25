package pl.tau.db.domain;

/**
 * Created by Owner on 25/05/2019.
 */
public class Concert {


    private Long id;
    private String artist;
    private String location;
    private String date;

    public Concert(Long id, String artist, String date, String location) {
        this.id = id;
        this.artist = artist;
        this.date = date;
        this.location = location;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}

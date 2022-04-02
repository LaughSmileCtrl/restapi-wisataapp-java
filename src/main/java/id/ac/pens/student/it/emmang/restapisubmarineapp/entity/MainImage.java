package id.ac.pens.student.it.emmang.restapisubmarineapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "main_image")
public class MainImage {
    @Id
    private Long id;

    @JsonIgnore
    private String filename;

    @OneToOne(orphanRemoval = true)
    @MapsId
    @JoinColumn(name = "place_id")
    @JsonBackReference
    private Place place;

    private String src;

    public MainImage() {
    }

    public MainImage(String filename) {
        this.filename = filename;
    }

    public MainImage(String filename, Place place) {
        this.filename = filename;
        this.place = place;
    }

    public MainImage(String filename, Place place, String src) {
        this.filename = filename;
        this.place = place;
        this.src = src;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}

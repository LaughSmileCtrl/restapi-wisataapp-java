package id.ac.pens.student.it.emmang.restapisubmarineapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "place_gallery")
public class PlaceGallery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    private String filename;
    private String src;

    @ManyToOne()
    @JoinColumn(name = "place_id")
    @JsonBackReference
    private Place place;

    public PlaceGallery() {}

    public PlaceGallery(String filename) {
        this.filename = filename;
    }

    public PlaceGallery(String filename, String src) {
        this.filename = filename;
        this.src = src;
    }

    public PlaceGallery(Long id, String filename) {
        this.id = id;
        this.filename = filename;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    @Override
    public String toString() {
        return "PlaceGallery{" +
                "id=" + id +
                ", filename='" + filename + '\'' +
                ", src='" + src + '\'' +
                ", place=" + place +
                '}';
    }
}

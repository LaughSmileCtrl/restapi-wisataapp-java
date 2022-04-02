package id.ac.pens.student.it.emmang.restapisubmarineapp.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "place")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;

    @OneToOne(mappedBy = "place", cascade = CascadeType.ALL)
    private MainImage mainImage;

    private String open;
    private String hours;
    private String ticket;
    private String description;

    @OneToMany(targetEntity = PlaceGallery.class, mappedBy = "place", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PlaceGallery> galleries;

    public Place() {
    }

    public Place(String name,
                 String location,
                 String open,
                 String hours,
                 String ticket,
                 String description) {
        this.name = name;
        this.location = location;
        this.open = open;
        this.hours = hours;
        this.ticket = ticket;
        this.description = description;
    }

    public Place(String name,
                 String location,
                 MainImage mainImage,
                 String open,
                 String hours,
                 String ticket,
                 String description,
                 Set<PlaceGallery> galleries) {
        this.name = name;
        this.location = location;
        this.open = open;
        this.hours = hours;
        this.ticket = ticket;
        this.description = description;

        setMainImage(mainImage);
        setGalleries(galleries);
    }

    public Place(Long id,
                 String name,
                 String location,
                 MainImage mainImage,
                 String open,
                 String hours,
                 String ticket,
                 String description,
                 Set<PlaceGallery> galleries) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.open = open;
        this.hours = hours;
        this.ticket = ticket;
        this.description = description;
        this.galleries = galleries;

        setMainImage(mainImage);
        setGalleries(galleries);

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public MainImage getMainImage() {
        return mainImage;
    }

    public void setMainImage(MainImage mainImage) {
        this.mainImage = mainImage;
        mainImage.setPlace(this);
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<PlaceGallery> getGalleries() {
        return galleries;
    }

    public void setGalleries(Set<PlaceGallery> galleries) {
        this.galleries = galleries;
        galleries.forEach(placeGallery -> placeGallery.setPlace(this));
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", mainImage='" + mainImage + '\'' +
                ", open='" + open + '\'' +
                ", hours='" + hours + '\'' +
                ", ticket='" + ticket + '\'' +
                ", description='" + description + '\'' +
                ", galleries=" + galleries +
                '}';
    }
}

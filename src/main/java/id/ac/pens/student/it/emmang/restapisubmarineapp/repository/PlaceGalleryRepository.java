package id.ac.pens.student.it.emmang.restapisubmarineapp.repository;

import id.ac.pens.student.it.emmang.restapisubmarineapp.entity.Place;
import id.ac.pens.student.it.emmang.restapisubmarineapp.entity.PlaceGallery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface PlaceGalleryRepository
        extends JpaRepository<PlaceGallery, Long> {

    Optional<Set<PlaceGallery>> findByPlace(Place place);
}

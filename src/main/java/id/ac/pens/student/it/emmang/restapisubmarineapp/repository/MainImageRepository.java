package id.ac.pens.student.it.emmang.restapisubmarineapp.repository;

import id.ac.pens.student.it.emmang.restapisubmarineapp.entity.MainImage;
import id.ac.pens.student.it.emmang.restapisubmarineapp.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MainImageRepository
        extends JpaRepository<MainImage, Long> {
    Optional<MainImage> findByPlace(Place place);
}

package id.ac.pens.student.it.emmang.restapisubmarineapp.repository;

import id.ac.pens.student.it.emmang.restapisubmarineapp.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository
        extends JpaRepository<Place, Long> {
}
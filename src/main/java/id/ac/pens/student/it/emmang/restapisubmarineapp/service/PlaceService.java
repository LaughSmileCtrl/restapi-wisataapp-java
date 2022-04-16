package id.ac.pens.student.it.emmang.restapisubmarineapp.service;

import id.ac.pens.student.it.emmang.restapisubmarineapp.controller.PlaceController;
import id.ac.pens.student.it.emmang.restapisubmarineapp.entity.Place;
import id.ac.pens.student.it.emmang.restapisubmarineapp.repository.MainImageRepository;
import id.ac.pens.student.it.emmang.restapisubmarineapp.repository.PlaceGalleryRepository;
import id.ac.pens.student.it.emmang.restapisubmarineapp.repository.PlaceRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;


@Service
public class PlaceService {

    private PlaceRepository placeRepository;
    private PlaceGalleryRepository placeGalleryRepository;
    private MainImageRepository mainImageRepository;

    public PlaceService(PlaceRepository placeRepository,
                        PlaceGalleryRepository placeGalleryRepository,
                        MainImageRepository mainImageRepository) {
        this.placeRepository = placeRepository;
        this.placeGalleryRepository = placeGalleryRepository;
        this.mainImageRepository = mainImageRepository;
    }

    public Map<String, Object> getPlaces() {
        List<Place> places = placeRepository.findAll();

        return Map.of(
                "totalResult", placeRepository.count(),
                "places", places
        );
    }

    public Place storePlaces(Place place) {
        place.setMainImage(null);
        place.setPlaceGalleries(null);
        placeRepository.save(place);

        return place;
    }

    @Transactional
    public Place updatePlace(Long placeId, Place place) {
        Place placeOld = placeRepository.findById(placeId)
                .orElseThrow(
                        () -> {
                            throw new IllegalStateException("Place " + placeId + " not found");
                        }
                );

        placeOld.setName(place.getName());
        placeOld.setLocation(place.getLocation());
        placeOld.setOpen(place.getOpen());
        placeOld.setHours(place.getHours());
        placeOld.setTicket(place.getTicket());
        placeOld.setDescription(place.getDescription());

        return placeOld;
    }

    @Transactional
    public ResponseEntity<Place> deletePlace(Long placeId) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(
                        () -> {
                            throw new IllegalStateException("Place " + placeId + " not found");
                        }
                );
        placeRepository.delete(place);

        return ResponseEntity.noContent().build();
    }

    public Place getPlace(Long placeId) {
        return placeRepository.findById(placeId)
                .orElseThrow(() -> {
                    throw new IllegalStateException("Place " + placeId + " not found");
                });
    }
}

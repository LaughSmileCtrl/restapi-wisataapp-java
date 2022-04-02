package id.ac.pens.student.it.emmang.restapisubmarineapp.controller;

import id.ac.pens.student.it.emmang.restapisubmarineapp.entity.Place;
import id.ac.pens.student.it.emmang.restapisubmarineapp.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/tourism-places")
public class PlaceController {

    private PlaceService placeService;

    @Autowired
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping
    public Map<String, Object> getPlaces() {
        return placeService.getPlaces();
    }

    @GetMapping(path = "{placeId}")
    public Place getPlace(@PathVariable Long placeId) {
        return placeService.getPlace(placeId);
    }

    @PostMapping
    public Place storePlace(@RequestBody Place place) {
        return placeService.storePlaces(place);
    }

    @PutMapping(path = "{placeId}")
    public Place updatePlace(@PathVariable("placeId") Long placeId,
                             @RequestBody Place place) {
        return placeService.updatePlace(placeId, place);
    }

    @DeleteMapping(path = "{placeId}")
    public ResponseEntity<Place> deletePlace(@PathVariable("placeId") Long placeId) {
        return placeService.deletePlace(placeId);
    }

}

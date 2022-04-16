package id.ac.pens.student.it.emmang.restapisubmarineapp.controller;

import id.ac.pens.student.it.emmang.restapisubmarineapp.entity.PlaceGallery;
import id.ac.pens.student.it.emmang.restapisubmarineapp.service.PlaceGalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/tourism-places")
public class PlaceGalleryController {
    private PlaceGalleryService placeGalleryService;

    @Autowired
    public PlaceGalleryController(PlaceGalleryService placeGalleryService) {
        this.placeGalleryService = placeGalleryService;
    }

    @GetMapping(path = "{placeId}/gallery/{id}")
    public ResponseEntity<Resource> get(@PathVariable("placeId") Long placeId,
                                        @PathVariable("id") Long id) throws IOException {
        Path placeGalleryPath = placeGalleryService.get(placeId, id);
        String contentType = Files.probeContentType(placeGalleryPath);
        Resource placeGalleryResource = new UrlResource(placeGalleryPath.toUri());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(placeGalleryResource);
    }

    @PostMapping(path = "{placeId}/gallery")
    public PlaceGallery store(HttpServletRequest request,
                              @PathVariable("placeId") Long placeId,
                              @RequestParam("gallery") MultipartFile file) {
        return placeGalleryService.store(placeId, file, request.getRequestURL().toString());
    }

//    @PostMapping(path = "{placeId}/gallery")
//    public Map<String, Object> storeAll(HttpServletRequest request,
//                                @PathVariable("placeId") Long placeId,
//                                @RequestParam("gallery") MultipartFile[] files) {
//        List<PlaceGallery> placeGalleryList = new ArrayList<>();
//        for (MultipartFile file : files) {
//            placeGalleryList.add(placeGalleryService.store(placeId,
//                    file, request.getRequestURL().toString()));
//        }
//
//        return Map.of(
//                "totalResult", placeGalleryList.size(),
//                "galleries", placeGalleryList
//        );
//    }

    @PutMapping(path = "{placeId}/gallery/{id}")
    public PlaceGallery update(@PathVariable("placeId") Long placeId,
                       @PathVariable("id") Long id,
                       @RequestParam("gallery") MultipartFile file) {
        return placeGalleryService.update(placeId, id, file);
    }

    @DeleteMapping(path = "{placeId}/gallery/{id}")
    public void delete(@PathVariable("placeId") Long placeId,
                               @PathVariable("id") Long id) {
        placeGalleryService.delete(placeId, id);
    }
}

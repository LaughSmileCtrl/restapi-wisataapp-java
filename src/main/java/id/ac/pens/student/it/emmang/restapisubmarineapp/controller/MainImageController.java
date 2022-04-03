package id.ac.pens.student.it.emmang.restapisubmarineapp.controller;

import id.ac.pens.student.it.emmang.restapisubmarineapp.entity.MainImage;
import id.ac.pens.student.it.emmang.restapisubmarineapp.service.MainImageService;
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

@RestController
@RequestMapping("/api/v1/tourism-places")
public class MainImageController {
    private MainImageService mainImageService;

    @Autowired
    public MainImageController(MainImageService mainImageService) {
        this.mainImageService = mainImageService;
    }

    @GetMapping(path = "{placeId}/main-image")
    public ResponseEntity<Resource> get(HttpServletRequest request,
                                        @PathVariable("placeId") Long placeId) throws IOException {
        Path mainImagePath = mainImageService.get(placeId);
        Resource mainImageResource = new UrlResource(mainImagePath.toUri());

        String contentType = Files.probeContentType(mainImagePath);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(mainImageResource);
    }

    @PutMapping(path = "{placeId}/main-image")
    public MainImage store(HttpServletRequest request,
                           @PathVariable Long placeId,
                           @RequestParam("main-image") MultipartFile mainImage) {
        String src = request.getRequestURL().toString();
        return mainImageService.store(placeId, mainImage, src);
    }

    @DeleteMapping(path = "{placeId}/main-image")
    public void delete(HttpServletRequest request,
                           @PathVariable Long placeId) {
        mainImageService.delete(placeId);
    }
}

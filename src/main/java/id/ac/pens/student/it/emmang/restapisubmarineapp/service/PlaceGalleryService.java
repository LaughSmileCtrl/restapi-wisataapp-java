package id.ac.pens.student.it.emmang.restapisubmarineapp.service;

import id.ac.pens.student.it.emmang.restapisubmarineapp.entity.Place;
import id.ac.pens.student.it.emmang.restapisubmarineapp.entity.PlaceGallery;
import id.ac.pens.student.it.emmang.restapisubmarineapp.repository.PlaceGalleryRepository;
import id.ac.pens.student.it.emmang.restapisubmarineapp.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PlaceGalleryService {
    private FileStorageService fileStorageService;
    private PlaceGalleryRepository placeGalleryRepository;
    private PlaceRepository placeRepository;

    @Autowired
    public PlaceGalleryService(FileStorageService fileStorageService,
                               PlaceGalleryRepository placeGalleryRepository,
                               PlaceRepository placeRepository) {
        this.fileStorageService = fileStorageService;
        this.placeGalleryRepository = placeGalleryRepository;
        this.placeRepository = placeRepository;
    }

    public Path get(Long placeId, Long id) {
        List<PlaceGallery> placeGalleries = placeRepository.findById(placeId)
                .orElseThrow(() -> {
                    throw new IllegalStateException("Place " + id + " not found");
                })
                .getPlaceGalleries()
                .stream()
                .filter(placeGallery -> placeGallery.getId() == id)
                .toList();

        return (placeGalleries.size() > 0)
                ? fileStorageService.getPath(placeGalleries.get(0).getFilename())
                : null;
    }

    @Transactional
    public PlaceGallery store(Long placeId, MultipartFile file, String src) {
        String suffix = file.getContentType().split("/")[1];
        String namePlaceGallery = String.format("image-gallery_%d.%s", placeId , suffix);
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> {
                    throw new IllegalStateException("Place " +   placeId + " not found");
                });
        PlaceGallery placeGallery = new PlaceGallery(namePlaceGallery);
        placeGallery.setPlace(place);
        Long galleryId = -1L;

        placeGallery = placeGalleryRepository.save(placeGallery);
        galleryId = placeGallery.getId();

        namePlaceGallery = String.format("image-gallery_%d-%d.%s", placeId, galleryId, suffix);
        placeGallery.setFilename(namePlaceGallery);
        placeGallery.setSrc(src+"/"+galleryId);

        try {
            fileStorageService.save(file, namePlaceGallery);
        } catch (IOException e) {
            System.err.println("Rest ERROR : " + e.getMessage());
            e.printStackTrace();
        }

        return placeGallery;
    }

    public PlaceGallery update(Long placeId, Long id, MultipartFile file) {
        Optional<PlaceGallery> optionalPlaceGallery = placeRepository.findById(placeId)
                .orElseThrow(() -> {
                    throw new IllegalStateException("Place " + id + " not found");
                })
                .getPlaceGalleries()
                .stream()
                .filter(placeGallery -> placeGallery.getId() == id)
                .findFirst();

        if (optionalPlaceGallery.isPresent()) {
            String filename = optionalPlaceGallery.get().getFilename();

            try {
                fileStorageService.save(file, filename);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return optionalPlaceGallery.get();
        }

        return new PlaceGallery();
    }

    @Transactional
    public void delete(Long placeId, Long id) {
        Set<PlaceGallery> galleries = placeRepository.findById(placeId)
                .orElseThrow(() -> {
                    throw new IllegalStateException("Place " + id + " not found");
                })
                .getPlaceGalleries();
        Optional<PlaceGallery> optionalPlaceGallery = galleries.stream()
                .filter(placeGallery -> placeGallery.getId() == id)
                .findFirst();


        if (optionalPlaceGallery.isPresent()) {
            galleries.remove(optionalPlaceGallery.get());

            String filename = optionalPlaceGallery.get().getFilename();
            placeGalleryRepository.delete(optionalPlaceGallery.get());

            try {
                fileStorageService.delete(filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

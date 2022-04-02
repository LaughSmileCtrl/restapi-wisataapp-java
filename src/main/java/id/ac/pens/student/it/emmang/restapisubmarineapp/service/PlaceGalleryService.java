package id.ac.pens.student.it.emmang.restapisubmarineapp.service;

import id.ac.pens.student.it.emmang.restapisubmarineapp.repository.PlaceGalleryRepository;
import id.ac.pens.student.it.emmang.restapisubmarineapp.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

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
        this.placeRepository
    }

    public Path get(Long placeId,Long id) {
        String fileName = placeRepository.findById(id).get().getMainImage().getFilename();
        return fileStorageService.getPath(fileName);
    }
}

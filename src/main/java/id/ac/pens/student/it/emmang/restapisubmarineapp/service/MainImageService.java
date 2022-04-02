package id.ac.pens.student.it.emmang.restapisubmarineapp.service;

import id.ac.pens.student.it.emmang.restapisubmarineapp.entity.MainImage;
import id.ac.pens.student.it.emmang.restapisubmarineapp.entity.Place;
import id.ac.pens.student.it.emmang.restapisubmarineapp.repository.MainImageRepository;
import id.ac.pens.student.it.emmang.restapisubmarineapp.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class MainImageService {
    private MainImageRepository mainImageRepository;
    private PlaceRepository placeRepository;

    private FileStorageService fileStorageService;

    @Autowired
    public MainImageService(MainImageRepository mainImageRepository,
                            PlaceRepository placeRepository,
                            FileStorageService fileStorageService) {
        this.mainImageRepository = mainImageRepository;
        this.placeRepository = placeRepository;
        this.fileStorageService = fileStorageService;
    }

    public Path get(Long id) {
        String fileName = placeRepository.findById(id)
                .orElseThrow(() -> {
                    throw new IllegalStateException("place " + id + " not found");
                })
                .getMainImage()
                .getFilename();
        return fileStorageService.getPath(fileName);
    }

    public MainImage store(Long id, MultipartFile file, String src) {
        String suffix = file.getContentType().split("/")[1];
        String nameMainImage = String.format("main-image_%d.%s", id, suffix);
        Optional<Place> placeOptional = placeRepository.findById(id);

        if (placeOptional.isPresent()) {
            try {
                fileStorageService.save(file, nameMainImage);

                if (!mainImageRepository.existsById(id)) {
                    MainImage mainImage = new MainImage(nameMainImage, placeOptional.get(), src);

                    mainImageRepository.save(mainImage);
                    placeOptional.get().setMainImage(mainImage);
                }
            } catch (IOException e) {
                System.err.println("Rest ERROR : " + e.getMessage());
                e.printStackTrace();
            }
        }

        return placeOptional.get().getMainImage();
    }

    @Transactional
    public void delete(Long id) {
        Optional<Place> placeOptional = placeRepository.findById(id);

        if (placeOptional.isPresent()) {
            MainImage mainImage = placeOptional.get().getMainImage();
            try {
                fileStorageService.delete(mainImage.getFilename());
                mainImageRepository.delete(mainImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

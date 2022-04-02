package id.ac.pens.student.it.emmang.restapisubmarineapp.service;

import id.ac.pens.student.it.emmang.restapisubmarineapp.property.FileStorageProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageProperty fileStorageProperty) {
        this.fileStorageLocation = Paths.get(fileStorageProperty.getUploadDir());
    }

    public void save(MultipartFile file, String name) throws IOException {
        Path targetLocation = fileStorageLocation.resolve(name);

        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

    }

    public Path getPath(String filename) {
        return fileStorageLocation.resolve(filename).normalize();
    }

    public void delete(String name) throws IOException {
        Path targetLocation = fileStorageLocation.resolve(name);
        Files.delete(targetLocation);
    }
}

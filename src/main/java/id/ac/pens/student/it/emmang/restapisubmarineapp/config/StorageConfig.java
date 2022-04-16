package id.ac.pens.student.it.emmang.restapisubmarineapp.config;

import id.ac.pens.student.it.emmang.restapisubmarineapp.property.FileStorageProperty;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class StorageConfig {

    @Bean
    CommandLineRunner initStorage(FileStorageProperty fileStorageProperty) {
        return args -> {
            Path fileStorageLocation = Paths.get(fileStorageProperty.getUploadDir())
                    .toAbsolutePath()
                    .normalize();
            if (Files.exists(fileStorageLocation)) {
                Files.list(fileStorageLocation).forEach(path -> {
                    try {
                        Files.delete(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

            }
            Files.createDirectories(fileStorageLocation);

        };
    }
}

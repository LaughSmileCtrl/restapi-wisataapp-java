package id.ac.pens.student.it.emmang.restapisubmarineapp;

import id.ac.pens.student.it.emmang.restapisubmarineapp.property.FileStorageProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(FileStorageProperty.class)
public class RestApiSubmarineAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiSubmarineAppApplication.class, args);
	}

}

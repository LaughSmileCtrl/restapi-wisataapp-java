package id.ac.pens.student.it.emmang.restapisubmarineapp.property;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FileStoragePropertyTest {
    @Test
    void testGet() {
        FileStorageProperty storageProperty = new FileStorageProperty();
        System.out.println(storageProperty.getUploadDir());
    }
}

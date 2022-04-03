package id.ac.pens.student.it.emmang.restapisubmarineapp.config;

import id.ac.pens.student.it.emmang.restapisubmarineapp.entity.MainImage;
import id.ac.pens.student.it.emmang.restapisubmarineapp.entity.PlaceGallery;
import id.ac.pens.student.it.emmang.restapisubmarineapp.entity.Place;
import id.ac.pens.student.it.emmang.restapisubmarineapp.repository.MainImageRepository;
import id.ac.pens.student.it.emmang.restapisubmarineapp.repository.PlaceGalleryRepository;
import id.ac.pens.student.it.emmang.restapisubmarineapp.repository.PlaceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class PlaceConfig {

    @Bean
    CommandLineRunner commandLineRunner(PlaceRepository placeRepository,
                                        PlaceGalleryRepository placeGalleryRepository,
                                        MainImageRepository mainImageRepository) {
        return (args -> {

            var mainImage1 = new MainImage("mainImage1");
            mainImage1.setSrc("https://free-images.com/sm/85cd/illinois_beach_state_park_0.jpg");
            var mainImage2 = new MainImage("mainImage2");
            mainImage2.setSrc("https://free-images.com/sm/af9d/beaches_ocean_waves.jpg");

            var gallery1 = Set.of(
                    new PlaceGallery("place-gallery_1-1","https://free-images.com/sm/7c47/beach_at_trabane_strand_0.jpg"),
                    new PlaceGallery("place-gallery_1-2","https://free-images.com/sm/b29f/beach_ocean_waves_coast_0.jpg"),
                    new PlaceGallery("place-gallery_1-3","https://free-images.com/sm/85cd/illinois_beach_state_park_0.jpg"),
                    new PlaceGallery("place-gallery_1-4","https://free-images.com/sm/cf65/beach_closed_area.jpg")
            );

            var gallery2 = Set.of(
                    new PlaceGallery("place-gallery_2-1","https://free-images.com/sm/b7d0/waterfall_hutan_simpan_dara.jpg"),
                    new PlaceGallery("place-gallery_2-2","https://free-images.com/sm/12f8/florest_0125.jpg"),
                    new PlaceGallery("place-gallery_2-3","https://free-images.com/sm/f4e5/hutan_pinus_mangunan.jpg")
            );

            var place1 = new Place(
                    "Pantai Dimana Saja",
                    "Wakanda",
                    mainImage1,
                    "Saturday",
                    "06.00 - 17.00",
                    "100000",
                    "INI DESKRIPSI BUAT PANTAI Lorem ipsum dolor si amet,consectetur adipiscing elit, " +
                            "sed do eiusmod temporincididunt ut labore et dolore magna aliqua. Ut enimad minim veniam, " +
                            "quis nostrud exercitation ullamcolaboris nisi ut aliquip ex ea commodo consequat",
                    gallery1
            );

            var place2 = new Place(
                    "Hutan Lipur Lata Jarum",
                    "Malaysia",
                    mainImage2,
                    "Every day",
                    "09.30 - 16.30",
                    "25500",
                    "DESKRIPSI BUAT HUTAN Lorem ipsum dolor sit amet,consectetur adipiscing elit, sed do " +
                            "eiusmod temporincididunt ",
                    gallery2
            );


            mainImageRepository.save(mainImage1);
            mainImageRepository.save(mainImage2);

            placeRepository.save(place1);
            placeRepository.save(place2);

            placeGalleryRepository.saveAll(gallery1);
            placeGalleryRepository.saveAll(gallery2);
        });
    }
}







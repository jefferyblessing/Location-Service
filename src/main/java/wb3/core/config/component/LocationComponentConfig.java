package wb3.core.config.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wb3.component.location.controller.LocationController;
import wb3.component.location.repository.ILocationRepository;
import wb3.component.location.repository.LocationRepository_MongoDB;
import wb3.component.location.service.ILocationService;
import wb3.component.location.service.LocationService;
import wb3.component.location.service.decorator.LocationServiceValidation;

@Configuration
public class LocationComponentConfig {

    @Bean
    ILocationRepository locationRepository() { return new LocationRepository_MongoDB(); }

    @Bean
    ILocationService locationService(){
        return new LocationServiceValidation(
                new LocationService(locationRepository()));
    }

    @Bean
    LocationController locationController() {
        return new LocationController();
    }

}

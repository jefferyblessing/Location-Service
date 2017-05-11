package wb3.core.config.builderConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wb3.component.location.model.geoLocation.GeoLocationDTO;
import wb3.component.location.model.geoLocation.GeoLocationVM;
import wb3.component.location.model.address.AddressDTO;
import wb3.component.location.model.address.AddressVM;
import wb3.component.location.model.LocationDTO;
import wb3.component.location.model.LocationVM;
import wb3.core.builder.IObjectBuilder;
import wb3.core.builder.ObjectBuilder;

/**
 * Created by WBensing on 3/4/2017.
 * Centralized configuration class which holds all Java bean configurations for any ObjectBuilders related to the Location Domain
 */
@Configuration
public class LocationBuilderConfig {

    @Bean
    public IObjectBuilder<GeoLocationDTO, GeoLocationVM> geoLocationObjectBuilder(){

        return new ObjectBuilder<GeoLocationDTO, GeoLocationVM>() {

            @Override
            public GeoLocationVM ToObject(GeoLocationDTO fromObject) {
                GeoLocationVM result = new GeoLocationVM();
                if(fromObject != null) {
                    result.setLatitude(fromObject.getLatitude());
                    result.setLongitude(fromObject.getLongitude());
                }
                return result;
            }

            @Override
            public GeoLocationDTO FromObject(GeoLocationVM toObject) {
                GeoLocationDTO result = new GeoLocationDTO();
                if(toObject != null){
                    result.setLatitude(toObject.getLatitude());
                    result.setLongitude(toObject.getLongitude());
                }
                return result;
            }
        };
    }

    @Bean
    @Autowired
    public IObjectBuilder<AddressDTO, AddressVM> addressObjectBuilder(
            IObjectBuilder<GeoLocationDTO, GeoLocationVM> geoLocationObjectBuilder){

        return new ObjectBuilder<AddressDTO, AddressVM>() {
            @Override
            public AddressVM ToObject(AddressDTO fromObject) {
                AddressVM result = new AddressVM();

                // Map the top level parameters
                if(fromObject != null){
                    result.setStreet1(fromObject.getStreet1());
                    result.setStreet2(fromObject.getStreet2());
                    result.setCity(fromObject.getCity());
                    result.setState(fromObject.getState());
                    result.setZipcode(fromObject.getZipcode());
                    result.setGeoLocation(
                            geoLocationObjectBuilder.ToObject(
                                    fromObject.getGeoLocation()
                            ));
                }
                return result;
            }

            @Override
            public AddressDTO FromObject(AddressVM toObject) {
                AddressDTO result = new AddressDTO();

                // Map the top level parameters
                if(toObject != null){
                    result.setStreet1(toObject.getStreet1());
                    result.setStreet2(toObject.getStreet2());
                    result.setCity(toObject.getCity());
                    result.setState(toObject.getState());
                    result.setZipcode(toObject.getZipcode());
                    result.setGeoLocation(
                            geoLocationObjectBuilder.FromObject(
                                    toObject.getGeoLocation()
                            ));
                }
                return result;
            }
        };
    }

    /**
     * Configures the Java Bean for ObjectBuilder which converts a LocationDTO to a LocationVM
     */
    @Bean
    @Autowired
    public IObjectBuilder<LocationDTO, LocationVM> locationObjectBuilder(
            IObjectBuilder<AddressDTO, AddressVM> addressObjectBuilder){

        return new ObjectBuilder<LocationDTO, LocationVM>() {
            @Override
            public LocationVM ToObject(LocationDTO fromObject) {
                LocationVM result = new LocationVM();

                if(fromObject == null) {
                    result = null;
                } else {
                    result.setSelf(fromObject.getId());
                    result.setName(fromObject.getName());
                    result.setAddress(
                            addressObjectBuilder.ToObject(
                                    fromObject.getAddress()
                            ));
                }
                return result;
            }

            @Override
            public LocationDTO FromObject(LocationVM toObject) {
                LocationDTO result = new LocationDTO();

                if(result != null) {
                    result.setId(toObject.getSelf());
                    result.setName(toObject.getName());
                    result.setAddress(
                            addressObjectBuilder.FromObject(
                                    toObject.getAddress()
                            ));
                }
                return result;
            }
        };
    }
}

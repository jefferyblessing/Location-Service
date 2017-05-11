package wb3.component.location.service;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;
import wb3.component.location.model.LocationDTO;
import wb3.component.location.repository.ILocationRepository;
import wb3.core.utility.ResourceAuthorizationUtility;

import java.util.List;

@Service
public class LocationService implements ILocationService {

    protected ILocationRepository locationRepository;

    public LocationService(ILocationRepository locationRepository){
        this.locationRepository = locationRepository;
    }

    // ----------------------------------- Publicly Available Methods ----------------------------------- \\

    public LocationDTO AddLocation(OAuth2Authentication authentication, LocationDTO location) {
        ResourceAuthorizationUtility.assignUserToResource(authentication, location);
        return locationRepository.create(location);
    }

    public LocationDTO FetchLocation(OAuth2Authentication authentication, LocationDTO location) {
        return locationRepository.readLocationById(location);
    }

    public List<LocationDTO> FetchAllLocations (OAuth2Authentication authentication) {
        String user = ResourceAuthorizationUtility.getUserId(authentication);
        return locationRepository.readAllLocationsByUser(user);
    }

    public LocationDTO RemoveLocation(OAuth2Authentication authentication, LocationDTO location) {
        locationRepository.delete(location);
        return location;
    }

}
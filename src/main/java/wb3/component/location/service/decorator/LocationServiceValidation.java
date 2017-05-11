package wb3.component.location.service.decorator;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import wb3.component.location.model.LocationDTO;
import wb3.component.location.service.ILocationService;
import wb3.core.exception.InvalidDTO;
import wb3.core.utility.NullObjectUtility;
import wb3.core.utility.ResourceAuthorizationUtility;

import java.util.List;

/**
 * Assignment vs. Validation - This class validates, but does not assign.
 */

public class LocationServiceValidation extends LocationServiceDecorator {

    public LocationServiceValidation(ILocationService locationService) {
        super(locationService);
    }

    @Override
    public LocationDTO AddLocation(OAuth2Authentication authentication, LocationDTO location) {
        validateLocationDTOAttributes(location);
        return locationService.AddLocation(authentication, location);
    }

    @Override
    public LocationDTO FetchLocation(OAuth2Authentication authentication, LocationDTO location) {
        // Basic checks to ensure the incoming objects are valid
        NullObjectUtility.ObjectCheck(location);
        NullObjectUtility.IdentifiableCheck(location);

        LocationDTO dto = locationService.FetchLocation(authentication, location);

        // Make sure the returned object is not null; this can happen if the location resource does not exist in the persistence layer
        NullObjectUtility.ObjectCheck(dto);

        // Ensure this resource belongs to the requesting user
        ResourceAuthorizationUtility.validateUserResource(authentication, dto);
        return dto;
    }

    @Override
    public List<LocationDTO> FetchAllLocations(OAuth2Authentication authentication) {
        return locationService.FetchAllLocations(authentication);
    }

    @Override
    public LocationDTO RemoveLocation(OAuth2Authentication authentication, LocationDTO location) {
        location = this.FetchLocation(authentication, location);
        ResourceAuthorizationUtility.validateUserResource(authentication, location);
        return locationService.RemoveLocation(authentication, location);
    }

    // TODO: Fortify this validation check to ensure all attributes of the DTO are being validated
    /**
     * Checks the LocationDTO attributes to ensure the minimally required attributes are set.
     * Throws a RuntimeException of InvalidDTO if the minimum attributes are not set/
     *
     * @throws InvalidDTO
     * @param location
     */
    private void validateLocationDTOAttributes(LocationDTO location){
        if(     (location.getName() == null) ||
                (location.getAddress() == null ) ||
                (location.getAddress().getGeoLocation() == null )
            )  { throw new InvalidDTO(); }
    }

}
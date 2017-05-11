package wb3.component.location.service;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import wb3.component.location.model.LocationDTO;
import wb3.core.exception.ResourceNotFound;
import java.util.List;

// Service      | Add    - Fetch - Modify - Remove
// Repository   | Create - Read  - Update - Delete
// Verb - Subject - Noun(s)

public interface ILocationService {

    /**
     * Creates a location for the authorized user.
     * Throws a RuntimeException of InvalidDTO if the supplied LocationDTO does not have all valid attributes assigned.
     *
     * @param authentication
     * @param location
     * @throws wb3.core.exception.InvalidDTO
     * @return Newly Created LocationDTO
     */
    public LocationDTO AddLocation(OAuth2Authentication authentication, LocationDTO location);

    /**
     * Returns a single LocationDTO.
     * Throws a RuntimeException of ResourceNotFound if the requested location resource does not belong to the user.
     * Throws a RuntimeException of NullPointerException if the supplied LocationDTO does not have an Id
     *
     * @param authentication
     * @param location
     * @throws ResourceNotFound
     * @throws NullPointerException
     * @return LocationDTO
     */
    public LocationDTO FetchLocation(OAuth2Authentication authentication, LocationDTO location);

    /**
     * Returns a list of LocationDTOs based upon the supplied authorized user
     *
     * @param authentication
     * @return List of LocationDTOs
     */
    public List<LocationDTO> FetchAllLocations(OAuth2Authentication authentication);

    /**
     * Deletes a location resource based.
     * Throws a RuntimeException of ResourceNotFound if the requested location resource does not belong to the user.
     * Throws a RuntimeException of NullPointerException if the supplied LocationDTO does not have an Id
     *
     * @param authentication
     * @param location
     * @throws ResourceNotFound
     * @throws NullPointerException
     * @return The removed object
     */
    public LocationDTO RemoveLocation(OAuth2Authentication authentication, LocationDTO location);
}
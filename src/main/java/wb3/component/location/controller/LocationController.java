package wb3.component.location.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;
import wb3.core.builder.IObjectBuilder;
import wb3.component.location.model.LocationDTO;
import wb3.component.location.model.LocationVM;
import wb3.component.location.service.ILocationService;

import javax.xml.stream.Location;
import java.util.List;

@RestController
@RequestMapping(value = "/locations")
public class LocationController {

    @Autowired
    private ILocationService locationService;

    @Autowired
    private IObjectBuilder<LocationDTO, LocationVM> locationVMBuilder;

    @PreAuthorize("#oauth2.hasScope('read') or #oauth2.hasScope('write')")
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<LocationVM> findAllByTenantUser(OAuth2Authentication auth2Authentication){
        List<LocationDTO> dtos = locationService.FetchAllLocations(auth2Authentication);
        return locationVMBuilder.ToList(dtos);
    }

    /**
     Location Controller method which creates a new Location based on the supplied
     location request and authentication credentials.

     @param auth2Authentication OAuth2 credentials
     @param location location request object
     @return location location view model

     */
    @PreAuthorize("#oauth2.hasScope('write')")
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public LocationVM create(OAuth2Authentication auth2Authentication, @RequestBody LocationVM location){
        LocationDTO dto = locationVMBuilder.FromObject(location);
        dto =  locationService.AddLocation(auth2Authentication, dto);
        return locationVMBuilder.ToObject(dto);
    }

    // TODO: Add factory to object builder for TO and FROM objects
    @PreAuthorize("#oauth2.hasScope('read') or #oauth2.hasScope('write')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public LocationVM findByIdForUser(OAuth2Authentication auth2Authentication, @PathVariable String id) {
        LocationDTO dto = new LocationDTO();
        dto.setId(id);
        dto = locationService.FetchLocation(auth2Authentication, dto);
        return locationVMBuilder.ToObject(dto);
    }

    // TODO: Add factory to object builder for TO and FROM objects
    @PreAuthorize("#oauth2.hasScope('read') or #oauth2.hasScope('write')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public LocationVM findByIdForTenant(OAuth2Authentication auth2Authentication, @PathVariable String id) {
        LocationDTO dto = new LocationDTO();
        dto.setId(id);
        dto = locationService.RemoveLocation(auth2Authentication, dto);
        return locationVMBuilder.ToObject(dto);
    }

}
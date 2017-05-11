package wb3.component.location.repository;

import com.mongodb.WriteResult;
import wb3.component.location.model.LocationDTO;

import java.util.List;

public interface ILocationRepository {

    public WriteResult delete(LocationDTO location);

    public LocationDTO create(LocationDTO location);

    public List<LocationDTO> readAllLocationsByUser(String user);

    public LocationDTO readLocationById(LocationDTO location);
}

package wb3.component.location.repository;

import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import wb3.component.location.model.LocationDTO;

import java.util.List;

@Repository
public class LocationRepository_MongoDB  implements ILocationRepository{

    @Autowired
    private MongoOperations mongoOperations;

    public WriteResult delete(LocationDTO location){
        Criteria criteria = new Criteria().and("id").is(location.getId());
        Query query = new Query(criteria);
        return mongoOperations.remove(query, LocationDTO.class);
    }

    public LocationDTO create(LocationDTO location){
        mongoOperations.save(location);
        return location;
    }

    public List<LocationDTO> readAllLocationsByUser(String user){
        Criteria criteria = new Criteria().and("userId").is(user);
        Query query = new Query(criteria);
        return mongoOperations.find(query, LocationDTO.class);
    }

    public LocationDTO readLocationById(LocationDTO location) {
        location =  mongoOperations.findById(location.getId(), LocationDTO.class);
        return location;
    }
}
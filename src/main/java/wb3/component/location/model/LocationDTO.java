package wb3.component.location.model;

import org.springframework.data.mongodb.core.mapping.Document;
import wb3.component.location.model.address.AddressDTO;
import wb3.core.model.IIdentifiableObject;
import wb3.core.model.IMultiTenantObject;
import wb3.core.model.IUserIdentifiedObject;
import wb3.core.model.MultiTenantDTO;

@Document(collection = "locations")
public class LocationDTO extends MultiTenantDTO implements IIdentifiableObject, IUserIdentifiedObject, IMultiTenantObject {

    private String name;

    private AddressDTO address = new AddressDTO();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

}

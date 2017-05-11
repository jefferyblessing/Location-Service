package wb3.component.location.model;

import wb3.component.location.model.address.AddressVM;

/**
 * Created by WBensing on 2/28/2017.
 */
public class LocationVM {

    private String self;
    private String name;
    private AddressVM address = new AddressVM();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressVM getAddress() {
        return address;
    }

    public void setAddress(AddressVM address) {
        this.address = address;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }


}

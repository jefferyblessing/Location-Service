package wb3.component.location.model.address;

import wb3.component.location.model.geoLocation.GeoLocationDTO;

public class AddressDTO {

    private String street1;
    private String street2;
    private String city;
    private String state;
    private int zipcode;
    private GeoLocationDTO geoLocation = new GeoLocationDTO();

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public GeoLocationDTO getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(GeoLocationDTO geoLocation) {
        this.geoLocation = geoLocation;
    }

}

package wb3.component.location.service.decorator;

import wb3.component.location.service.ILocationService;

public abstract class LocationServiceDecorator implements ILocationService {

    protected ILocationService locationService;

    public LocationServiceDecorator(ILocationService locationService){
        this.locationService = locationService;
    }

}
package wb3.component.location.controller;

import static org.mockito.Mockito.verify;

/*@RunWith(SpringRunner.class)
@SpringBootTest*/
public class LocationControllerSpec {

//    @MockBean
//    private LocationRepository locationRepository;
//    @MockBean
//    private IObjectBuilder<LocationDTO, LocationVM> locationVMBuilder;
//    @MockBean
//    private OAuth2Authentication oAuth2Authentication;
//
//    @Autowired
//    private LocationController locationController;


/*    @Test
    public void Create_Location_Dependencies_Called(){

        //arrange
        LocationVM vm = new LocationVM();
        LocationDTO dto = new LocationDTO();
        String tenant = "tenant";
        String user = "user";
        OAuth2Authentication auth = null;
        given(this.locationRepository.createLocation(vm, tenant, user)).willReturn(dto);
        given(this.locationVMBuilder.ToObject(dto)).willReturn(vm);

        //act
        locationController.create(vm, auth);

        //assert
        verify(this.locationRepository).createLocation(vm, tenant, user);
        verify(this.locationVMBuilder).ToObject(dto);
    }*/
}
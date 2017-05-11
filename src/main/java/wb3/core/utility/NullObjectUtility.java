package wb3.core.utility;


import wb3.core.exception.ResourceNotFound;
import wb3.core.model.BaseDTO;
import wb3.core.model.IIdentifiableObject;

public class NullObjectUtility {

    /**
     * Checks to see if the supplied BaseDTO is null and throws a custom ResourceNotFound runtime exception if the supplied BaseDTO is null
     *
     * @param dto
     */
    public static void BaseDTOCheck(BaseDTO dto){
        if (dto == null) { throw new ResourceNotFound(); }
    }

    public static void ObjectCheck(Object object) {
        if (object == null) { throw new ResourceNotFound(); }
    }

    public static void IdentifiableCheck(IIdentifiableObject object) {
        if (object.getId() == null) {
            throw new NullPointerException("The object you supplied failed an IIdentifiable check; the object does not have an Id");
        }
    }
}

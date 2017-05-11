package wb3.core.utility;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import wb3.core.exception.ResourceNotFound;
import wb3.core.model.IUserIdentifiedObject;
import wb3.core.model.MultiTenantDTO;
import wb3.core.model.UserIdentifiedDTO;

public class ResourceAuthorizationUtility {

    /**
     * Retrieve the tenant identity as a string from an OAuth2Authentication object
     * @param authentication
     * @return tenant identity
     */
    public static String getTenant(OAuth2Authentication authentication) { return extractTenant(authentication);}

    /**
     * Retrieve the user identity as a string from an OAuth2Authentication object
     * @param authentication
     * @return user identity
     */
    public static String getUserId(OAuth2Authentication authentication) {
        return extractUser(authentication);
    }

    /**
     * Check to see if the an OAuth2Authentication object contains the supplied Tenant identifier
     * @param authentication
     * @param tenantId
     * @return the truth
     */
    public static boolean isTenantResource(OAuth2Authentication authentication, String tenantId) {
        return (extractTenant(authentication).equals(tenantId));
    }

    /**
     * Check to see if the supplied OAuth2Authentication object containes the supplied User identifier
     * @param authentication
     * @param userId
     * @return the truth
     */
    public static boolean isUserResource(OAuth2Authentication authentication, String userId){
        return (extractUser(authentication).equals(userId));
    }

    /**
     * Validate ownership of the UserIdentifiedDTO.  This utility will throw a RuntimeException of type ResourceNotFound
     * if the supplied UserId does not match the DTOs UserId.
     *
     * @param authentication
     * @param dto
     * @return
     */
    public static void validateUserResource(OAuth2Authentication authentication, UserIdentifiedDTO dto){
        if(!ResourceAuthorizationUtility.isUserResource(authentication, dto.getUserId())){
            throw new ResourceNotFound("Requested source requested by user [" + dto.getUserId() +  "] does not belong to them.");
        }
    }

    /**
     * Validates if a the ownership of a MultiTenantDTO.  THis utility will throw a RuntimeException of type ResourceNotFound
     * if the supplied Tenant & UserID do not match the DTO returned from datastore.
     *
     * @param authentication
     * @param dto
     */
    public static void validateTenantResource(OAuth2Authentication authentication, MultiTenantDTO dto) {
        if(!ResourceAuthorizationUtility.isTenantResource(authentication, dto.getTenant())){
            throw new ResourceNotFound("Requested source requested by user [" + dto.getTenant() +  "] does not belong to them.");
        }
    }

    /**
     * Assigns user details from an OAuth2Authentication object to an object which implements the IUserIdentifiedObject interface
     *
     * @param auth2Authentication
     * @param userObj
     * @return an object with an assigned user
     */
    public static void assignUserToResource(OAuth2Authentication auth2Authentication, IUserIdentifiedObject userObj){
        userObj.setUserId(extractUser(auth2Authentication));
    }

    // -------------------------------------------- Private Methods -------------------------------------------- \\

    /***
     * Extracts the tenant identity from the OAuth2Authentication and returns it as a string.
     * This assumes the tenant identity exists in the OAuth2Request Extensions object and contains the value 'tenant'
     * @param authentication
     * @return tenant identity
     */
    private static String extractTenant(OAuth2Authentication authentication){
        return authentication.getOAuth2Request().getExtensions().get("tenant").toString();
    }

    /***
     * Extracts the tenant identity from the OAuth2Authentication and returns it as a string.
     * This assumes the user identity exists in the OAuth2Request Extensions object and contains the value 'user_name'
     * @param authentication
     * @return user identity
     */
    private static String extractUser (OAuth2Authentication authentication){
        return authentication.getOAuth2Request().getExtensions().get("user_name").toString();
    }
}
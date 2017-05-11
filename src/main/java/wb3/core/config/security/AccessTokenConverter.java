package wb3.core.config.security;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;

import java.util.Map;

/**
 * Created by WBensing on 3/6/2017.
 */
public class AccessTokenConverter extends DefaultAccessTokenConverter {

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        OAuth2Authentication authentication = super.extractAuthentication(map);
        OAuth2Request request = authentication.getOAuth2Request();
        MapExtensionsToOAuth2Request(map, request);
        return new OAuth2Authentication(request, authentication.getUserAuthentication());
    }

    // This method iterates through the provided JWT map, converts each key to a string, and adds it to the extensions attribute of the OAuth2Request
    private void MapExtensionsToOAuth2Request(Map<String, ?> map, OAuth2Request request){
        for(Map.Entry<String, ?> entry : map.entrySet()){
            request.getExtensions().put(entry.getKey(), entry.getValue().toString());
        }
    }

}

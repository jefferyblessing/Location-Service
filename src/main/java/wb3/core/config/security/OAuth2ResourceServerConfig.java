package wb3.core.config.security;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.token.ClientTokenServices;
import org.springframework.security.oauth2.client.token.JdbcClientTokenServices;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

import javax.sql.DataSource;

@Configuration
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        .and()
            .authorizeRequests().antMatchers(HttpMethod.OPTIONS, "**").permitAll()
        .and()
            .authorizeRequests().antMatchers(HttpMethod.GET, "**").authenticated()
        .and()
            .authorizeRequests().antMatchers(HttpMethod.POST, "**").authenticated()
        .and()
            .authorizeRequests().antMatchers(HttpMethod.PUT, "**").authenticated()
        .and()
            .authorizeRequests().antMatchers(HttpMethod.DELETE, "**").authenticated();
    }

    @Override
    public void configure(final ResourceServerSecurityConfigurer config) {
        config.tokenServices(tokenService());
    }

    @Primary
    @Bean
    public RemoteTokenServices tokenService() {
        RemoteTokenServices tokenService = new RemoteTokenServices();
        tokenService.setCheckTokenEndpointUrl("http://localhost:5001/oauth/check_token");
        tokenService.setClientId("billClient");
        tokenService.setClientSecret("secret");
        tokenService.setAccessTokenConverter(defaultAccessTokenConverter());
        return tokenService;
    }

    @Bean
    public ClientTokenServices clientTokenServices() {
        return new JdbcClientTokenServices(dataSource);
    }

    @Bean
    DefaultAccessTokenConverter defaultAccessTokenConverter(){
        return new AccessTokenConverter();
    }

}
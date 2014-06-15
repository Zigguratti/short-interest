package shortinterest.config;

import shortinterest.domain.Company;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class SpringDataRestConfig extends RepositoryRestMvcConfiguration {

    @Override
    protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        try {
            config.exposeIdsFor(Company.class)
                .setBaseUri(new URI("/feed"));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}

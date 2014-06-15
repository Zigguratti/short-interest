package shortinterest;

import shortinterest.domain.Company;
import shortinterest.service.CompanyRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class);
        CompanyRepository repository = context.getBean(CompanyRepository.class);
        repository.save(Arrays.asList(
                new Company("HPQ", "Hewlett-Packard", 123, 154, 0.20, 1245.2),
                new Company("IBM", "IBM", 123, 154, 0.20, 1245.2),
                new Company("MSFT", "Microsoft", 123, 154, 0.20, 1245.2)
        ));

    }

}

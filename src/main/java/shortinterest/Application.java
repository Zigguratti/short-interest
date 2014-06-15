package shortinterest;

import shortinterest.domain.Company;
import shortinterest.service.CompanyRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import shortinterest.service.UkMarketService;

import java.net.URL;
import java.util.Arrays;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class);
        UkMarketService ukMarketService = context.getBean(UkMarketService.class);
        ukMarketService.updateShortPositions(new URL("http://www.fca.org.uk/static/documents/short-positions-daily-update.xls"));
    }

}

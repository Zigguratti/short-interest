package shortinterest.scheduled;

import shortinterest.service.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@EnableScheduling
@Component
public class ScheduledTasks {

    @Autowired
    private CompanyRepository companyRepository;

    @Scheduled(fixedRate = 15000)
    public void addRandomCompany() {
        System.out.println("Hello world");
    }

}

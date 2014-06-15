package shortinterest.service;

import shortinterest.scraper.FcaSpreadsheetScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;

@Service
public class UkMarketService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private FcaSpreadsheetScraper fcaSpreadsheetScraper;

    public void updateShortPositions(URL shortPositionsUrl) {
        fcaSpreadsheetScraper.getShortPositions(shortPositionsUrl, 0);
    }

}

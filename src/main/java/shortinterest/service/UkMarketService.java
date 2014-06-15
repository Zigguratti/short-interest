package shortinterest.service;

import com.google.common.collect.Multimap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shortinterest.domain.Company;
import shortinterest.scraper.FcaSpreadsheetScraper;
import shortinterest.scraper.ShortPosition;

import java.net.URL;
import java.util.Map;

@Service
public class UkMarketService {

    Logger logger = LoggerFactory.getLogger(UkMarketService.class);

    public static final int LIVE_POSITIONS_PAGE = 0;
    public static final int HISTORICAL_POSITIONS_PAGE = 0;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ShortPositionRepository shortPositionRepository;

    @Autowired
    private FcaSpreadsheetScraper fcaSpreadsheetScraper;

    public void updateShortPositions(URL shortPositionsUrl) {
        Multimap<String, ShortPosition> liveShortPositions = fcaSpreadsheetScraper.getShortPositions(shortPositionsUrl, LIVE_POSITIONS_PAGE);
        for (Map.Entry<String, ShortPosition> entry : liveShortPositions.entries()) {
            Company company = companyRepository.findOne(entry.getKey());
            ShortPosition shortPosition = entry.getValue();
            if (null == company) {
                company = new Company(shortPosition.getIsin(), shortPosition.getIssuer());
                companyRepository.save(company);
            }

            shortinterest.domain.ShortPosition daoShortPosition = new shortinterest.domain.ShortPosition(company, shortPosition.getHolder(), shortPosition.getNetShortPosition());
            shortPositionRepository.save(daoShortPosition);
        }
    }

}

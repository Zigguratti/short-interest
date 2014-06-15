package shortinterest.web;

import shortinterest.scraper.FcaSpreadsheetScraper;
import shortinterest.scraper.ShortPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("/feed")
public class ShortPositionsController {

    @Autowired
    private FcaSpreadsheetScraper fcaSpreadsheetScraper;

    @RequestMapping(value = "/shortpositions", method = RequestMethod.GET)
    public List<ShortPosition> shortPositions() {
        try {
            return fcaSpreadsheetScraper.getShortPositions(new URL("http://www.fca.org.uk/static/documents/short-positions-daily-update.xls"), 0);
        } catch (MalformedURLException e) {
            throw new IllegalStateException(e);
        }
    }
}

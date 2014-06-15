package shortinterest.scraper;

import com.google.common.collect.Multimap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class FcaSpreadsheetScraperTest {

    @InjectMocks
    private FcaSpreadsheetScraper fcaSpreadsheetScraper;

    @Test
    public void can_parse_fca_spreadsheet() throws Exception {
        // Given
        URL spreadsheetUrl = Thread.currentThread().getContextClassLoader().getResource("short-positions-daily-update.xls");

        // When
        Multimap<String, ShortPosition> shortPositions = fcaSpreadsheetScraper.getShortPositions(spreadsheetUrl, 0);

        // Then
        assertThat(shortPositions.size(), is(2));
        ShortPosition shortPosition = shortPositions.get("GB00B09LQS34").iterator().next();
        assertThat(shortPosition.getHolder(), is("Henderson Alternative Investment Advisor Limited"));
        assertThat(shortPosition.getIssuer(), is("1SPATIAL PLC"));
        assertThat(shortPosition.getIsin(), is("GB00B09LQS34"));
        assertThat(shortPosition.getNetShortPosition(), is(1.42));
        assertThat(shortPosition.getPositionDate(), is(LocalDate.parse("2013-05-29", DateTimeFormatter.ISO_LOCAL_DATE)));
    }
}
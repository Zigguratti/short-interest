package shortinterest.scraper;

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
        List<ShortPosition> shortPositions = fcaSpreadsheetScraper.getShortPositions(spreadsheetUrl, 0);

        // Then
        assertThat(shortPositions.size(), is(2));
        assertThat(shortPositions.get(0).getHolder(), is("Henderson Alternative Investment Advisor Limited"));
        assertThat(shortPositions.get(0).getIssuer(), is("1SPATIAL PLC"));
        assertThat(shortPositions.get(0).getIsin(), is("GB00B09LQS34"));
        assertThat(shortPositions.get(0).getNetShortPosition(), is(1.42));
        assertThat(shortPositions.get(0).getPositionDate(), is(LocalDate.parse("2013-05-29", DateTimeFormatter.ISO_LOCAL_DATE)));
    }
}
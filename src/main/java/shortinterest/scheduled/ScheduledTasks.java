package shortinterest.scheduled;

import com.google.common.base.Throwables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import shortinterest.service.UkMarketService;

import java.net.MalformedURLException;
import java.net.URL;

@EnableScheduling
@Component
public class ScheduledTasks {

    private static final String FCA_SHORT_POSITIONS = "http://www.fca.org.uk/static/documents/short-positions-daily-update.xls";

    @Autowired
    private UkMarketService ukMarketService;

    @Scheduled(fixedRate = 60000)
    public void updateUkShortPositions() {
        try {
            ukMarketService.updateShortPositions(new URL(FCA_SHORT_POSITIONS));
        } catch (MalformedURLException e) {
            Throwables.propagate(e);
        }
    }

}

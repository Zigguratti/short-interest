package shortinterest.scraper;

import com.google.common.base.Function;
import com.google.common.base.Throwables;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Service
public class FcaSpreadsheetScraper {

    public Multimap<String, ShortPosition> getShortPositions(URL shortPositionsUri, int sheetNumber) {
        HSSFWorkbook workbook = null;
        try {
            workbook = new HSSFWorkbook(shortPositionsUri.openStream());
        } catch (Exception e) {
            Throwables.propagate(e);
        }

        return parseSheet(workbook, sheetNumber);
    }

    private Multimap<String, ShortPosition> parseSheet(HSSFWorkbook workbook, int sheetNumber) {
        List<ShortPosition> shortPositions = newArrayList();
        HSSFSheet sheet = workbook.getSheetAt(sheetNumber);
        Iterator<Row> rowIterator = sheet.iterator();
        discardHeaderRow(rowIterator);

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            shortPositions.add(
                    new ShortPosition(
                            row.getCell(0).getStringCellValue(),
                            row.getCell(1).getStringCellValue(),
                            row.getCell(2).getStringCellValue(),
                            Double.parseDouble(row.getCell(3).getStringCellValue()),
                            LocalDate.parse(row.getCell(4).getStringCellValue(), DateTimeFormatter.ISO_LOCAL_DATE))
            );
        }

        return Multimaps.index(shortPositions, new Function<ShortPosition, String>() {
            @Override
            public String apply(ShortPosition shortPosition) {
                return shortPosition.getIsin();
            }
        });
    }

    private void discardHeaderRow(Iterator<Row> rowIterator) {
        if (rowIterator.hasNext()) {
            rowIterator.next();
        }
    }
}

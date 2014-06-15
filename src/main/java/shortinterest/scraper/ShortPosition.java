package shortinterest.scraper;

import com.google.common.base.Objects;

import java.time.LocalDate;

public class ShortPosition {

    private String holder;
    private String issuer;
    private String isin;
    private double netShortPosition;
    private LocalDate positionDate;

    public ShortPosition(String holder, String issuer, String isin, double netShortPosition, LocalDate positionDate) {
        this.holder = holder;
        this.issuer = issuer;
        this.isin = isin;
        this.netShortPosition = netShortPosition;
        this.positionDate = positionDate;
    }

    public String getHolder() {
        return holder;
    }

    public String getIssuer() {
        return issuer;
    }

    public String getIsin() {
        return isin;
    }

    public double getNetShortPosition() {
        return netShortPosition;
    }

    public LocalDate getPositionDate() {
        return positionDate;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(ShortPosition.class)
                .add("holder", holder)
                .add("issuer", issuer)
                .add("isin", isin)
                .add("netShortPosition", netShortPosition)
                .add("positionDate", positionDate)
                .toString();
    }
}

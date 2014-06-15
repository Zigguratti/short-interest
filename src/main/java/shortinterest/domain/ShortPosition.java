package shortinterest.domain;

import com.google.common.base.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class ShortPosition implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    private String ticker;
    private String holder;
    private Double shortInterest;

    private ShortPosition() {}

    public ShortPosition(String ticker, String holder, Double shortInterest) {
        this.ticker = ticker;
        this.holder = holder;
        this.shortInterest = shortInterest;
    }

    public String getTicker() {
        return ticker;
    }

    public String getHolder() {
        return holder;
    }

    public Double getShortInterest() {
        return shortInterest;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public void setShortInterest(Double shortInterest) {
        this.shortInterest = shortInterest;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(ShortPosition.class)
                .add("ticker", ticker)
                .add("holder", holder)
                .add("shortInterest", shortInterest)
                .toString();
    }
}

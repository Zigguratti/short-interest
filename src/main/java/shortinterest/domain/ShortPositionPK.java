package shortinterest.domain;

import com.google.common.base.Objects;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ShortPositionPK implements Serializable {

    private String ticker;
    private String holder;

    private ShortPositionPK() {
    }

    public ShortPositionPK(String ticker, String holder) {
        this.ticker = ticker;
        this.holder = holder;
    }

    public String getTicker() {
        return ticker;
    }

    public String getHolder() {
        return holder;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        ShortPositionPK shortPositionPK = (ShortPositionPK) o;
        return Objects.equal(shortPositionPK.getTicker(), this.ticker) && Objects.equal(shortPositionPK.getHolder(), this.holder);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.ticker, this.holder);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(ShortPositionPK.class)
                .add("ticker", ticker)
                .add("holder", holder)
                .toString();
    }
}

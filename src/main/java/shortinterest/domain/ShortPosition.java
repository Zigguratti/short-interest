package shortinterest.domain;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ShortPosition implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ticker", insertable = false, updatable = false)
    private Company company;

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(
                    name = "ticker",
                    column = @Column(name = "ticker")),
            @AttributeOverride(
                    name = "holder",
                    column = @Column(name = "holder"))
    })
    private ShortPositionPK shortPositionPK;
    private Double shortInterest;

    private ShortPosition() {
    }

    public ShortPosition(Company company, String holder, Double shortInterest) {
        this.company = company;
        this.shortPositionPK = new ShortPositionPK(company.getTicker(), holder);
        this.shortInterest = shortInterest;
    }

    public Double getShortInterest() {
        return shortInterest;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(ShortPosition.class)
                .add("ticker", shortPositionPK.getHolder())
                .add("holder", shortPositionPK.getTicker())
                .add("shortInterest", shortInterest)
                .toString();
    }
}

package shortinterest.domain;

import com.google.common.base.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

@Entity
public class Company implements Serializable {

    @Id
    private String ticker;
    private String name;

    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    private Set<ShortPosition> shortPositions;

    private Company() {
    }

    public Company(String ticker, String name) {
        shortPositions = newHashSet();
        this.ticker = ticker;
        this.name = name;
    }

    public String getTicker() {
        return ticker;
    }

    public String getName() {
        return name;
    }

    public void addShortPosition(ShortPosition shortPosition) {
        shortPositions.add(shortPosition);
    }

    public Set<ShortPosition> getShortPositions() {
        return shortPositions;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(Company.class)
                .add("ticker", ticker)
                .add("name", name)
                .add("shortPositions", shortPositions)
                .toString();
    }
}

// My suggestion is:

// Short interest we get from respective company website.
// Days to results we have to scrape.  4-traders/reuters (4-traders uses a url incorporating isin, reuters uses ric)
// TTM we have to calculate using financials.  4-traders has 3-years forward sales, EBITDA, net income, and eps.  Reuters has less.
// Reuters may be better for this as we only need 1 year/a few quarters forwards, but we need quarters back.
// Volatiltiy we get from share price data we get from yahoo.  That needs isin.  Ideally one should adjust for dividends (they have divi-adusted pxs tho)

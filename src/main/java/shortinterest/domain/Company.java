package shortinterest.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Company implements Serializable {

    @Id
    private String ticker;
    private String name;
    private Integer sectorCode;
    private Integer subSectorCode;
    private Double freeFloat;
    private Double marketCap;

    //Results
    private Date nextResultsDate;
    private Integer daysToResults;

    //  Short interest quantities
    private Double shortInterestFFPct; // short interest as % of free float
    private Double shortInterest;

    // Short-interest trend.  Ideally want to model curve and predict trend http://davetang.org/muse/2013/05/09/on-curve-fitting/
    private Double m1_SITrend;
    private Double m2SITrend;
    private Double m3SITrend;
    private Double m6SITrend;
    private Double m12SITrend;

    //  Historical Volatility
    private Double m1Volatility;
    private Double m2Volatility;
    private Double m3Volatility;
    private Double m6Volatility;
    private Double m12Volatility;

    //  Volatility trends.  Ideally want to model vol curve and predict trend http://davetang.org/muse/2013/05/09/on-curve-fitting/
    private Double m1VolTrend;
    private Double m2VolTrend;
    private Double m3VolTrend;
    private Double m6VolTrend;
    private Double m12VolTrend;

    //  Valuation
    private Double Valuation; // possibly more than one field here ... tbc.  May not add ... not in v 1

    // TTM quantitities.  These need calculating: TTM = last quarters plus expected next quarter(s) to estimate FY.  I suggest we grow previous quarters using growth-rate if estimates NA.
    private Double TTMRevenues;
    private Double TTMEBITDA;
    private Double TTMNetIncome;
    private Double TTMEps;

    private Company() {
    }

    // Can scrape this from reuters.com or 4traders.com.
    public Company(String ticker, String name, Integer sectorCode, Integer subSectorCode, Double freeFloat, Double marketCap) {
        this.ticker = ticker;
        this.name = name;
        this.sectorCode = sectorCode;
        this.subSectorCode = subSectorCode;
        this.freeFloat = freeFloat;
        this.marketCap = marketCap;
    }

    // May have to do this manually; want to alert whenever there is a new ticker (a new short-position).
    public String getTicker() {
        return ticker;
    }

    // Once have Ric or Isin we can scrape this.
    public String getName() {
        return name;
    }
}

// My suggestion is:

// Short interest we get from respective company website.
// Days to results we have to scrape.  4-traders/reuters (4-traders uses a url incorporating isin, reuters uses ric)
// TTM we have to calculate using financials.  4-traders has 3-years forward sales, EBITDA, net income, and eps.  Reuters has less.
// Reuters may be better for this as we only need 1 year/a few quarters forwards, but we need quarters back.
// Volatiltiy we get from share price data we get from yahoo.  That needs isin.  Ideally one should adjust for dividends (they have divi-adusted pxs tho)

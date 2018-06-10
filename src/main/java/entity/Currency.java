package entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import util.json.serializer.LocalDateDeserializer;
import util.json.serializer.LocalDateSerializer;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Currency
 * created on 6/9/18
 *
 * @author Nikita Zabaykin vladlihovid@gmail.com
 * @version 1.0
 */
public class Currency {
    private int r030;
    private String txt;
    private Float rate;
    private String cc;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonProperty("exchangedate")
    private LocalDate exchangeDate;

    public Currency() {
    }

    public Currency(int r030, String txt, Float rate, String cc, LocalDate exchangeDate) {
        this.r030 = r030;
        this.txt = txt;
        this.rate = rate;
        this.cc = cc;
        this.exchangeDate = exchangeDate;
    }

    public int getR030() {
        return r030;
    }

    public void setR030(int r030) {
        this.r030 = r030;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public LocalDate getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(LocalDate exchangeDate) {
        this.exchangeDate = exchangeDate;
    }

    @Override
    public String toString() {
        return String.format("Currency={r030=%d, txt=%s, rate=%f, cc=%s, date=%s}",
                r030, txt, rate, cc, exchangeDate.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return r030 == currency.r030 &&
                Objects.equals(txt, currency.txt) &&
                Objects.equals(rate, currency.rate) &&
                Objects.equals(cc, currency.cc) &&
                Objects.equals(exchangeDate, currency.exchangeDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(r030, txt, rate, cc, exchangeDate);
    }
}

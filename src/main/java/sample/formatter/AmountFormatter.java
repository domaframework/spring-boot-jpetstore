package sample.formatter;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.format.number.NumberFormatter;

import sample.entity.Amount;

public class AmountFormatter implements Formatter<Amount> {

    private final NumberFormatter formatter;

    public AmountFormatter() {
        formatter = new NumberFormatter("$#,##0.00");
    }

    @Override
    public String print(Amount amount, Locale locale) {
        return formatter.print(amount.getValue(), locale);
    }

    @Override
    public Amount parse(String text, Locale locale) throws ParseException {
        BigDecimal value = (BigDecimal) formatter.parse(text, locale);
        return new Amount(value);
    }
}

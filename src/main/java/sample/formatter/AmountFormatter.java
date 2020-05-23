package sample.formatter;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;
import org.springframework.format.number.NumberStyleFormatter;
import sample.entity.Amount;

public class AmountFormatter implements Formatter<Amount> {

  private final NumberStyleFormatter formatter;

  public AmountFormatter() {
    formatter = new NumberStyleFormatter("$#,##0.00");
  }

  @Override
  public String print(Amount amount, Locale locale) {
    return formatter.print(amount.getValue(), locale);
  }

  @Override
  public Amount parse(String text, Locale locale) throws ParseException {
    var value = (BigDecimal) formatter.parse(text, locale);
    return new Amount(value);
  }
}

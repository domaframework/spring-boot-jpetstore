package sample.formatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.springframework.format.Formatter;

public class LocalDateTimeFormatter implements Formatter<LocalDateTime> {

  @Override
  public String print(LocalDateTime temporal, Locale locale) {
    var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    return formatter.format(temporal);
  }

  @Override
  public LocalDateTime parse(String text, Locale locale) {
    return LocalDateTime.parse(text);
  }
}

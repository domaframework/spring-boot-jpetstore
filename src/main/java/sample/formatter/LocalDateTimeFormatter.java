package sample.formatter;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.format.Formatter;

public class LocalDateTimeFormatter implements Formatter<LocalDateTime> {

    @Override
    public String print(LocalDateTime temporal, Locale locale) {
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm:ss");
        return formatter.format(temporal);
    }

    @Override
    public LocalDateTime parse(String text, Locale locale)
            throws ParseException {
        return LocalDateTime.parse(text);
    }

}

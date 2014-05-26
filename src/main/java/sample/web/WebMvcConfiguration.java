package sample.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import sample.formatter.AmountFormatter;
import sample.formatter.LocalDateTimeFormatter;

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        super.addFormatters(registry);
        registry.addFormatter(new AmountFormatter());
        registry.addFormatter(new LocalDateTimeFormatter());
    }
}

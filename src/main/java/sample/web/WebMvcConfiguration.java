package sample.web;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.HttpStatus;
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

  @Bean
  public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>
      webServerFactoryCustomizer() {
    return new ServletContainerCustomizer();
  }

  protected static class ServletContainerCustomizer
      implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    public void customize(ConfigurableServletWebServerFactory factory) {
      factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
    }
  }
}

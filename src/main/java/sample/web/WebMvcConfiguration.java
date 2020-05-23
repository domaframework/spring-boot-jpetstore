package sample.web;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
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
  public EmbeddedServletContainerCustomizer containerCustomizer() {
    return new ServletContainerCustomizer();
  }

  protected static class ServletContainerCustomizer implements EmbeddedServletContainerCustomizer {

    @Override
    public void customize(ConfigurableEmbeddedServletContainer factory) {
      factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
    }
  }
}

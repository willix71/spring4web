package w.test2.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("w.test2.controller")
public class WebConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebConfig.class);
	
	public WebConfig() {
		LOGGER.info("WebConfig initialized");
	}
	
	@Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/sample-view/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
}

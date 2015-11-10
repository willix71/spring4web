package w.test.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import w.test1.controller.Sample;

@Configuration
//@ComponentScan( basePackages = "org.rest" )
//@ImportResource( { "classpath*:/rest_config.xml" } )
//@PropertySource({ "classpath:rest.properties", "classpath:web.properties" })
public class AppConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(Sample.class);
	
	public AppConfig() {
		LOGGER.info("AppConfig initialized");
	}
}

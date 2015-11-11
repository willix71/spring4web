package w.rest.test;

import org.springframework.web.util.UriComponentsBuilder;

public class IntegrationTestConfig {

	public static final String BASE_URI = UriComponentsBuilder.newInstance()
			.scheme("http").host("localhost").port(8881).path("/spring").path("/rest")		
			.build().toUri().toASCIIString();
}

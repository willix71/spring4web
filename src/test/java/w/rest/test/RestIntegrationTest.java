package w.rest.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import w.model.Foo;

//@RunWith(SpringJUnit4ClassRunner.class)
public class RestIntegrationTest {

	@Test
	public void testGetFoos() {
		String URI = "http://localhost:8880/spring/rest/foos";
		RestTemplate restTemplate = new RestTemplate();
		List<?> foos = restTemplate.getForObject(URI, List.class);
		Assert.assertEquals(8, foos.size());
	}
	
	@Test
	public void testGetFoo() {
		String URI = "http://localhost:8880/spring/rest/foos/{id}";
		RestTemplate restTemplate = new RestTemplate();
		Foo foo = restTemplate.getForObject(URI, Foo.class, "1");
		Assert.assertEquals(1l, foo.getId());
	}
}

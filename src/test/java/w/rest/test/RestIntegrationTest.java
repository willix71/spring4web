package w.rest.test;

import  static w.rest.test.IntegrationTestConfig.BASE_URI;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import w.model.Foo;

//@RunWith(SpringJUnit4ClassRunner.class)
public class RestIntegrationTest {
	
	@Test
	public void testGetFoo() {
		String URI = BASE_URI +"/foos/{id}";
		RestTemplate restTemplate = new RestTemplate();
		Foo foo = restTemplate.getForObject(URI, Foo.class, "1");
		Assert.assertEquals(1l, foo.getId());
	}
	
	@Test
	public void testGetFoos() {
		String URI = BASE_URI +"/foos";
		RestTemplate restTemplate = new RestTemplate();
		List<?> foos = restTemplate.getForObject(URI, List.class);
		Assert.assertEquals(9, foos.size());
	}
	
	@Test
	public void testGetFoos_page1size3() {
		String URI = BASE_URI +"/foos?page=1&size=3";
		RestTemplate restTemplate = new RestTemplate();
		List<?> foos = restTemplate.getForObject(URI, List.class);
		Assert.assertEquals(3, foos.size());
	}
}

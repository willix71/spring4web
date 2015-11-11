package w.rest.test;

import  static w.rest.test.IntegrationTestConfig.BASE_URI;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

public class ETagIntegrationTest {

	@Test
	public void givenUserDoesNotExists_whenUserInfoIsRetrieved_then404IsReceived()
	      throws ClientProtocolException, IOException{
	   // Given
	   HttpUriRequest request = new HttpGet( BASE_URI + "/foos/1");
	 
	   // When
	   HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
	 
	   // Then
	   Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
	   
	   String mimeType = ContentType.getOrDefault(httpResponse.getEntity()).getMimeType();
	   Assert.assertEquals("Wrong mime type", "application/json", mimeType );
	   
	   Header[] etags = httpResponse.getHeaders("ETag");
	   String etag = etags[0].getValue();
	   Assert.assertNotNull("Empty etag", etag);
	   
	   // Given 2
	   request.setHeader("If-None-Match", etag);
	   
	   // When 2
	   httpResponse = HttpClientBuilder.create().build().execute( request );
	   
	   // Then 2
	   Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_NOT_MODIFIED);
	   
	   // Given 3
	   request.setHeader("If-None-Match", etag + "123");
	   
	   // When 3
	   httpResponse = HttpClientBuilder.create().build().execute( request );
	   
	   // Then 3
	   Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
	   
	}
	
}

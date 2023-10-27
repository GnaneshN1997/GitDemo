package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class Utils {
	public static RequestSpecification req;
	public RequestSpecification requestSpecification() throws IOException {
		
		if(req == null) {
		PrintStream stream = new PrintStream(new FileOutputStream("Logging.txt"));
	
		req = new RequestSpecBuilder().setBaseUri(getProperties("baseURI")).addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(stream))
				.addFilter(ResponseLoggingFilter.logResponseTo(stream)).setContentType(ContentType.JSON).build();
		return req;
		}
		return req;
	}
	
	public static String getProperties(String key) throws IOException {
		Properties properties = new Properties();
		FileInputStream fis = new FileInputStream(".//src\\test\\java\\resources\\global.properties");
		properties.load(fis);
		return properties.getProperty(key);
	}
	
	public String getJSONPath(Response response, String key) {
		String st = response.asString();
		JsonPath jp = new JsonPath(st);
		return jp.getString(key);
	}
	
	

}

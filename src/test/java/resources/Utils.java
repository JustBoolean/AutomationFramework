package resources;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
	public static RequestSpecification reqSpec;
	
	public RequestSpecification requestSpec(String param) throws IOException {
		if(reqSpec == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			
			reqSpec = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURI")).addQueryParam("q",param)
					.addHeader("Content-Type","applicant/json")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON).build();
		}
		return reqSpec;
	}
	public static String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();
		prop.load(Utils.class.getResourceAsStream("global.properties"));
		return prop.getProperty(key);
		/*
		 * System.out.println("Key " + key); Properties p = new Properties(); Thread
		 * currentThread = Thread.currentThread(); ClassLoader contextClassLoader =
		 * currentThread.getContextClassLoader(); InputStream propertiesStream =
		 * contextClassLoader.getResourceAsStream("global.properties");
		 * System.out.println("Properites Stream: " + propertiesStream.toString());
		 * p.load(propertiesStream); System.out.println("Get Property " +
		 * p.getProperty(key)); return p.getProperty(key);
		 */
	}
	
	public String getJsonPath(Response response, String key) {
		String r = response.asString();
		JsonPath js = new JsonPath(r);
		return js.get(key).toString();
	}
}

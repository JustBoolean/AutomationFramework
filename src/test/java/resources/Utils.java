package resources;

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
		System.out.println(key);
		Properties p = new Properties();
		p.load(Utils.class.getResourceAsStream("global.properties"));
		return p.getProperty(key);
	}
	
	public String getJsonPath(Response response, String key) {
		String r = response.asString();
		JsonPath js = new JsonPath(r);
		return js.get(key).toString();
	}
}

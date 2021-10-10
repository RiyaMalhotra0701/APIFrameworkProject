package resources;

import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils{
	public static RequestSpecification req;
	ResponseSpecification response;
	
	public RequestSpecification requestSpecification() throws IOException {
		if(req==null) {
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		req=new RequestSpecBuilder().setBaseUri(getGlobalData("BaseUrl"))
				.addQueryParam("key", "qaclick123").addFilter(RequestLoggingFilter.logRequestTo(log)).addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();
		return req;
		}
		return req;
	}
	
	public ResponseSpecification responseSpecification() {
		response=new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();	
	    return response;
	}
	
	public String getGlobalData(String key) throws IOException {
		
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream("C:\\Users\\user\\eclipse-workspace\\APIFramework\\src\\test\\java\\resources\\global.properties");
	    prop.load(fis);
	    String url=prop.getProperty(key);
	    return url;    
	}

	public String getJsonPath(Response response,String key) {
		String res=response.asString();
		JsonPath js=new JsonPath(res);
		System.out.println(js.getString(key));
		return js.getString(key).toString();
	}
}

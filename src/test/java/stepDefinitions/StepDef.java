package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;  // Eclipse doesnt auto suggest static packages.
import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.APIResources;
import resources.Utils;
import resources.TestDataBuild;

public class StepDef extends Utils{
	RequestSpecification request;
	Response responseString;
	TestDataBuild data = new TestDataBuild();
	static String place_id;
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_Place_Payload_with(String name, String language, String address) throws IOException {
		request= given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));	   
	}


    @When("user calls {string} with {string} HTTP request")
    public void user_calls_with_HTTP_request(String resource, String method) {
		
		APIResources resAPI=APIResources.valueOf(resource);         // this will execute the constructor.
		
		if(method.equalsIgnoreCase("POST")) {
		responseString=request.when().post(resAPI.getResource()).then().extract().response();	
		}
		else if(method.equalsIgnoreCase("GET")) {
			responseString=request.when().get(resAPI.getResource()).then().extract().response();				
		}
	}

	@Then("The API call is success with status code {int}")
	public void the_API_call_is_success_with_status_code(int int1) {
		assertEquals(responseString.getStatusCode(),int1);	   
	}

	@Then("{string} in response body is {string}")  //It has parameterization
	public void in_response_body_is(String key, String value) {
		//getJsonPath(responseString.asString(),key);
	    assertEquals(getJsonPath(responseString,key),value);	    
	}
	
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		//Prepare request Spec
		
		place_id=getJsonPath(responseString,"place_id");
		request= given().spec(requestSpecification()).queryParam("place_id", place_id);
		
		//hit getAPI
		user_calls_with_HTTP_request(resource, "GET");
		String actualName=getJsonPath(responseString,"name");
		assertEquals(actualName,expectedName);	
	}
	
	@Given("DeletePlace Payload")
	public void deleteplace_Payload() throws IOException {
	  request=given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	}
}

package stepDefinitions;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import static io.restassured.RestAssured.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.Utils;

public class GetVolumeStepDefinitions extends Utils {
	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	Response response;
	
	@Given("A query parameter {string}")
	public void a_query_parameter(String query) throws IOException {
	    //get query param from user and add to request
		reqSpec = given().spec(requestSpec(query));
	}
	
	@When("user calls {string} with {string} request")
	public void user_calls_with_request(String resource, String string2) {
	    // call google api given query param using GET request
		APIResources resourceAPI = APIResources.valueOf(resource);	
		System.out.println(resourceAPI.getResource());
		response = reqSpec.when().get(resourceAPI.getResource());
		
	}
	@Then("API call is successful with status code {int}")
	public void api_call_is_successful_with_status_code(Integer int1) {
	    // After call check we get appropriate status code
		assertEquals(response.getStatusCode(),200);
	}

}

package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.SerializationPayload;
import resources.Utils;

public class stepDefinition extends Utils{
	RequestSpecification req;
	static RequestSpecification reqSpec;
	Response response;
	static ResponseSpecification resp;
	SerializationPayload sp = new SerializationPayload();
	static String place_id;

	@Given("add place payload {string} {string} {string}")
	public void add_place_payload(String name, String address, String language) throws IOException {

		resp = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		reqSpec =given().spec(requestSpecification()).body(sp.addPlacepayload(name,address,language));
	}
	
	@When("user calls {string} with {string} http request")
	public void user_calls_with_post_http_request(String resource, String method) {
		APIResources resourceAPI = APIResources.valueOf(resource);
		if(method.equalsIgnoreCase("POST"))
			response = reqSpec.when().post(resourceAPI.getResource()).then().spec(resp).extract().response();
		else if(method.equalsIgnoreCase("GET"))
			response = reqSpec.when().get(resourceAPI.getResource()).then().spec(resp).extract().response();
		else if(method.equalsIgnoreCase("DELETE"))
			response = reqSpec.when().delete(resourceAPI.getResource()).then().spec(resp).extract().response();
	}
	
	@Then("the api call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
		String jp = getJSONPath(response, key);
		assertEquals(jp, value);
	}

	@Then("verify {string} from addPlaceaPI equals in {string}")
	public void verify_equals_in(String name, String resource) throws IOException {
		place_id = getJSONPath(response, "place_id");
		reqSpec =given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_post_http_request(resource, "GET");
		String actname = getJSONPath(response, "name");
		assertEquals(actname, name);

	}
	
	@Given("delete place payload")
	public void delete_place_payload() throws IOException {
		reqSpec =given().spec(requestSpecification()).body(sp.deleteAPIPayload(place_id));
	}






	




}

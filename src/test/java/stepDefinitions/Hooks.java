package stepDefinitions;


import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	
	@Before("@DeletePlace")
	public void before_Scenario() throws IOException {
		
		stepDefinition sd = new stepDefinition();
		if(stepDefinition.place_id == null) {
		sd.add_place_payload("Rahul", "Delhi", "Hindi");
		sd.user_calls_with_post_http_request("addPlaceAPI", "POST");
		sd.verify_equals_in("Rahul", "getPlaceAPI");
		}
	}

}

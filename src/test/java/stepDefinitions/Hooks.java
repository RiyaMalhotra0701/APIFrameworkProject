package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeDeletePlace() throws IOException {
		// execute this code only when place_id is null.
		StepDef sd = new StepDef();
		if (StepDef.place_id == null) {
			sd.add_Place_Payload_with("RahulShetty", "Hindi", "India");
			sd.user_calls_with_HTTP_request("AddPlaceAPI", "POST");
			sd.verify_place_id_created_maps_to_using("RahulShetty", "getPlaceAPI");
		}
	}

}

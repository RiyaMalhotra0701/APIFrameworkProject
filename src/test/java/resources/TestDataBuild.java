package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Locations;

public class TestDataBuild {
	public AddPlace addPlacePayload(String name, String language, String address) {
		List<String> s= new ArrayList<String>();
		s.add("shoe-park");
		s.add("shop");
		
		Locations l = new Locations();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		
		AddPlace a = new AddPlace(); // creating java object of head POJO class.This Java object is passed to body() where internally it gets converted to Json
		a.setAccuracy(50);
		a.setAddress(address);
		a.setLanguage(language);
		a.setLocation(l);
		a.setName(name);
		a.setPhone_number("(+91) 983 893 3937");
		a.setWebsite("http://google.com");
		a.setTypes(s);
		return a;
		
	}
	
	public String deletePlacePayload(String place_id) {
		return "{\"place_id\":\""+place_id+"\"}\r\n\r\n";
	}

}

package resources;

import java.util.ArrayList;
import java.util.List;

import Pojo.Location;
import Pojo.RSAMapsPlace;

public class SerializationPayload {
	
	public RSAMapsPlace addPlacepayload(String name,String address,String language) {
		RSAMapsPlace rmp = new RSAMapsPlace();
		Location location = new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		rmp.setLocation(location);
		rmp.setAccuracy(50);
		rmp.setName(name);
		rmp.setPhone_number("(+91) 983 893 3937");
		rmp.setAddress(address);
		String s1 = "shoe park";
		String s2 = "shop";
		List<String> list = new ArrayList<String>();
		list.add(s1);
		list.add(s2);
		rmp.setTypes(list);
		rmp.setWebsite("http://rahulshettyacademy.com");
		rmp.setLanguage(language);
		
		return rmp;
	}
	
	public String deleteAPIPayload(String place_id) {
		return "{\r\n"
				+ "    \"place_id\":\""+place_id+"\" \r\n"
				+ "}";
	}

}

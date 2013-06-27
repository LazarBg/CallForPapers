package rs.fon.is.cfp.domain;

import com.google.gson.JsonObject;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import rs.fon.is.cfp.util.Constants;

@Namespace(Constants.SCHEMA)
@RdfType("Place")
public class Place extends Thing {
	
	@RdfProperty(Constants.SCHEMA + "name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Place(){
		
	}
	
	public JsonObject returnAsJson(){
		JsonObject jsonPlace = new JsonObject();
		jsonPlace.addProperty("URI", getUri().toString());
		jsonPlace.addProperty("name", name);
		
		return jsonPlace;
	}
}

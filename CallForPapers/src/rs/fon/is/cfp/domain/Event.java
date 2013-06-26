package rs.fon.is.cfp.domain;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonObject;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import rs.fon.is.cfp.util.Constants;

@Namespace(Constants.CALL)
@RdfType("Event")
public class Event extends Thing{
	
	// represents the "fullname" attribute; the fullname of an Event
	@RdfProperty(Constants.SCHEMA + "name")
	private String name;
	
	// represents the "handle" attribute; the abbreviated name of an Event
	@RdfProperty(Constants.DC + "alternative")
	private String alternative;
	
	// represents the "year" attribute; the year when the Event was organized
	@RdfProperty(Constants.DC + "date")
	private int year;
	
	// represents the "createdate" attribute; the date when an Event was created
	@RdfProperty(Constants.DC + "created")
	private Date created;
	
	// represents "begindate" attribute; the starting date of an Event
	@RdfProperty(Constants.SCHEMA + "startDate")
	private Date startDate;
	
	// represents "finishdate" attribute; the ending date of an Event
	@RdfProperty(Constants.SCHEMA + "endDate")
	private Date endDate;
	
	// represents the "location" attribute; location where an Event takes place 
	@RdfProperty(Constants.SCHEMA + "location")
	private Place location;
	
	// represents the "info" attribute; general information about Event
	@RdfProperty(Constants.SCHEMA + "description")
	private String decription;
	
	// every Event has associated Call For Papers
	@RdfProperty(Constants.CALL + "hasCfP")
	private Call call;
	
	@RdfProperty(Constants.FOAF + "homepage")
	private URI homepage;
	
	
	public Event() {
	
	}
	// GETTERS AND SETTERS 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Place getLocation() {
		return location;
	}

	public void setLocation(Place location) {
		this.location = location;
	}

	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
	}

	public Call getCall() {
		return call;
	}

	public void setCall(Call call) {
		this.call = call;
	}

	public URI getHomepage() {
		return homepage;
	}

	public void setHomepage(URI uri) {
		this.homepage = uri;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getAlternative() {
		return alternative;
	}

	public void setAlternative(String alternative) {
		this.alternative = alternative;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	@Override
	public String toString() {
		return returnAsJson().toString();
	}
	
	public JsonObject returnAsJson(){
		JsonObject jsonEvent = new JsonObject();
		
		jsonEvent.addProperty("URI", getUri().toString());
		jsonEvent.addProperty("name", name);
		jsonEvent.addProperty("alternative", alternative);
		jsonEvent.addProperty("date", year);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		jsonEvent.addProperty("created", (created == null) ? "" : sdf.format(created));
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		jsonEvent.addProperty("startDate", (startDate == null) ? "" : sdf.format(startDate));
		jsonEvent.addProperty("endDate", (endDate == null) ? "" : sdf.format(endDate));
		jsonEvent.add("location", location.returnAsJson());
		jsonEvent.addProperty("homepage", homepage.toString());
		jsonEvent.addProperty("hasCfp", call.getUri().toString());
		jsonEvent.addProperty("description", decription);
		
		return jsonEvent;
	}
}

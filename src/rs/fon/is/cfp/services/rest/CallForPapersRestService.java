package rs.fon.is.cfp.services.rest;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletContext;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import rs.fon.is.cfp.domain.Call;
import rs.fon.is.cfp.domain.Event;
import rs.fon.is.cfp.persistence.DataModelManager;
import rs.fon.is.cfp.services.QueryService;

import com.hp.hpl.jena.tdb.TDB;

@Path("cfp")
public class CallForPapersRestService {
	
	//@Context private ServletContext context;

	private QueryService qs;

	public CallForPapersRestService(){
		qs = new QueryService();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public  String getAllCalls(@DefaultValue("") @QueryParam("abstractFrom") String abstrFrom, @DefaultValue("") @QueryParam("abstractTo") String abstrTo, 
			@DefaultValue("") @QueryParam("fullSubmissionFrom") String fullFrom, @DefaultValue("") @QueryParam("fullSubmissionTo") String fullTo, 
			@DefaultValue("") @QueryParam("finalDecisionFrom") String finalFrom, @DefaultValue("") @QueryParam("finalDecisionTo") String finalTo,
			@DefaultValue("") @QueryParam("startDateFrom") String startFrom, @DefaultValue("") @QueryParam("startDateTo") String startTo,
			@DefaultValue("") @QueryParam("endDateFrom") String endFrom, @DefaultValue("") @QueryParam("endDateTo") String endTo, 
			@DefaultValue("") @QueryParam("location") String location, @DefaultValue("") @QueryParam("name") String name, 
			@DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset){
		
		Collection<Call> calls = new ArrayList<>();
		try {
			calls = qs.getAllCalls(abstrFrom, abstrTo, fullFrom, fullTo, finalFrom, finalTo, startFrom, startTo, endFrom, endTo, location, name, limit, offset);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		String odgovor = "[ ";
		for (Call c : calls) {
			odgovor += c;
		}
		odgovor += "]";
		TDB.sync(DataModelManager.getInstance().getModel());
		return odgovor;
	}
	
	@GET
	@Path("events")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllEvents(@DefaultValue("") @QueryParam("startDateFrom") String startFrom, @DefaultValue("") @QueryParam("startDateTo") String startTo,
			@DefaultValue("") @QueryParam("endDateFrom") String endFrom, @DefaultValue("") @QueryParam("endDateTo") String endTo, 
			@DefaultValue("") @QueryParam("location") String location, @DefaultValue("") @QueryParam("name") String name, 
			@DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset){
		
		Collection<Event> events = new ArrayList<>();
		try {
			events = qs.getAllEvents(startFrom, startTo, endFrom, endTo, location, name, limit, offset);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		String odgovor = "[ ";
		for (Event e : events) {
			odgovor += e;
		}
		odgovor += "]";
		TDB.sync(DataModelManager.getInstance().getModel());
		return odgovor;
		
	}
}

package rs.fon.is.cfp.services;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import rs.fon.is.cfp.domain.Call;
import rs.fon.is.cfp.domain.Event;
import rs.fon.is.cfp.persistence.DataModelManager;
import rs.fon.is.cfp.persistence.QueryExecutor;
import rs.fon.is.cfp.util.Constants;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.tdb.TDBFactory;

public class QueryService {
	
	private QueryExecutor queryExecutor = new QueryExecutor();
	
	public Collection<Call> getAllCalls(String abstrFrom, String abstrTo, String fullFrom, String fullTo, String finalFrom, String finalTo,
			 String startFrom, String startTo, String endFrom, String endTo, String location, String name, int limit, int offset) throws URISyntaxException{
		
		String query = "PREFIX call: <http://blog.timeliner.net/ontologies/call/ns/>" +
				" PREFIX foaf: <http://xmlns.com/foaf/0.1/>" +
				" PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>" +
				" PREFIX schema: <http://schema.org/>" +
				" PREFIX dc: <http://purl.org/dc/terms/>" +
				" SELECT ?call " +
				" WHERE {" +
					" ?call a call:Call." +
					" ?call call:abstractDeadline ?abstractDate." +
					" ?call call:fullSubmissionDeadline ?fullSubDate." +
					" ?call call:finalDecisionDeadline ?finalDate." +
//					" ?call call:cameraReadyPaperDeadline ?cameraDate." +
					" ?call call:forEvent ?event." +					
					" ?event schema:name ?name." +
//					" ?event dc:alternative ?alt." +
//					" ?event dc:date ?year." +
//					" ?event dc:created ?created." +
					" ?event schema:startDate ?startDate." +
					" ?event schema:endDate ?endDate." +
//					" ?event schema:description ?description." +
					" ?event schema:location ?place." +
					" ?place schema:name ?location." +
					
				" FILTER (";
				query += "regex(?location,\""+location+"\",\"i\") && regex(?name,\""+name+"\",\"i\") ";
				
				if (!abstrFrom.isEmpty())
					query += "&& ?abstractDate > \""+abstrFrom+"T00:00:00Z\"^^xsd:dateTime ";
				if (!abstrTo.isEmpty())
					query += "&& ?abstractDate < \""+abstrTo+"T00:00:00Z\"^^xsd:dateTime ";
				
				if (!fullFrom.isEmpty())
					query += "&& ?fullSubDate > \""+fullFrom+"T00:00:00Z\"^^xsd:dateTime ";
				if (!fullTo.isEmpty())
					query += "&& ?fullSubDate < \""+fullTo+"T00:00:00Z\"^^xsd:dateTime ";
				
				if (!finalFrom.isEmpty())
					query += "&& ?finalDate > \""+finalFrom+"T00:00:00Z\"^^xsd:dateTime ";
				if (!finalTo.isEmpty())
					query += "&& ?finalDate < \""+finalTo+"T00:00:00Z\"^^xsd:dateTime ";
				
				if (!startFrom.isEmpty())
					query += "&& ?startDate > \""+startFrom+"T00:00:00Z\"^^xsd:dateTime ";
				if (!startTo.isEmpty())
					query += "&& ?startDate < \""+startTo+"T00:00:00Z\"^^xsd:dateTime ";
				
				if (!endFrom.isEmpty())
					query += "&& ?endDate > \""+endFrom+"T00:00:00Z\"^^xsd:dateTime ";
				if (!endTo.isEmpty())
					query += "&& ?endDate < \""+endTo+"T00:00:00Z\"^^xsd:dateTime ";
					
				query += ") " +
					"}";
				query += " LIMIT " + limit + " OFFSET " + offset;
		
		// first step - we retrieve URI of an object that satisfies the query conditions
		Collection<String> URIcollection = queryExecutor.executeOneVariableSelectSparqlQuery(query, "call", DataModelManager.getInstance().getModel());
	
		Collection<Call> calls = new ArrayList<>();
		for (String uri: URIcollection) {
			try {
				// second step - we retrieve all the data regarding the object with specified URI
				calls.add(queryExecutor.getCallForURI(uri));
			} catch (Exception e) {
				System.err.println("ERROR: mailfunctioned object retrieved from RDF model!");
			}
		}
		
		return calls;
	}

	public Collection<Event> getAllEvents(String startFrom, String startTo, String endFrom, String endTo, String location, String name,
			int limit, int offset) throws URISyntaxException{
		
			String query = "PREFIX call: <http://blog.timeliner.net/ontologies/call/ns/>" +
					" PREFIX foaf: <http://xmlns.com/foaf/0.1/>" +
					" PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>" +
					" PREFIX schema: <http://schema.org/>" +
					" PREFIX dc: <http://purl.org/dc/terms/>" +
					" SELECT ?event " +
					" WHERE {" +
						" ?event a call:Event." +				
						" ?event schema:name ?name." +
//						" ?event dc:alternative ?alt." +
//						" ?event dc:date ?year." +
//						" ?event dc:created ?created." +
						" ?event schema:startDate ?startDate." +
						" ?event schema:endDate ?endDate." +
//						" ?event schema:description ?description." +
//						" ?event schema:hasCfp ?call." +
						" ?event schema:location ?place." +
						" ?place schema:name ?location." +	
						
					" FILTER (";
					query += "regex(?location,\""+location+"\",\"i\") && regex(?name,\""+name+"\",\"i\") ";
					
					if (!startFrom.isEmpty())
						query += "&& ?startDate > \""+startFrom+"T00:00:00Z\"^^xsd:dateTime ";
					if (!startTo.isEmpty())
						query += "&& ?startDate < \""+startTo+"T00:00:00Z\"^^xsd:dateTime ";
					
					if (!endFrom.isEmpty())
						query += "&& ?endDate > \""+endFrom+"T00:00:00Z\"^^xsd:dateTime ";
					if (!endTo.isEmpty())
						query += "&& ?endDate < \""+endTo+"T00:00:00Z\"^^xsd:dateTime ";
						
					query += ") " +
						"}";
					query += " LIMIT " + limit + " OFFSET " + offset;
			
			// first step - we retrieve URI of an object that satisfies the query conditions
			Collection<String> URIcollection = queryExecutor.executeOneVariableSelectSparqlQuery(query, "event", DataModelManager.getInstance().getModel());
		
			Collection<Event> events = new ArrayList<>();
			for (String uri: URIcollection) {
				try {
					// second step - we retrieve all the data regarding the object with specified URI
					events.add(queryExecutor.getEventForURI(uri));
				} catch (Exception e) {
					System.err.println("ERROR: mailfunctioned object retrieved from RDF model!");
				}
			}
			
			return events;
		
	}
	
//	public Model describeAllCfps() {
//		String query = 
//			"PREFIX call: <"+Constants.CALL+"> " +
//			"PREFIX foaf: <"+ Constants.FOAF + "> " +
//			"PREFIX schema: <" + Constants.SCHEMA + "> " +
//			"PREFIX dc: <" + Constants.DC + "> " + 
//			"PREFIX xsd: <" + Constants.XSD + "> " + 
//			"DESCRIBE ?call " +
//			"WHERE { " +
//				"?call a call:Call. " +
//			"} LIMIT 10";
//		
//		return queryExecutor
//				.executeDescribeSparqlQuery(query,
//						DataModelManager.getInstance().getModel());
//	}
	
//	public Model describeAllEvents() {
//		String query = 
//			"PREFIX call: <"+Constants.CALL+"> " +
//			"PREFIX foaf: <"+ Constants.FOAF + "> " +
//			"PREFIX schema: <" + Constants.SCHEMA + "> " +
//			"PREFIX dc: <" + Constants.DC + "> " + 
//			"PREFIX xsd: <" + Constants.XSD + "> " + 
//			"DESCRIBE ?event " +
//			"WHERE { " +
//				"?event a call:Event. " +
//			"} " +
//			"LIMIT 10";
//		
//		return queryExecutor
//				.executeDescribeSparqlQuery(query,
//						DataModelManager.getInstance().getModel());
//	}
	

//	public Collection<String> getHistoricCfpsNames(){
//		String query = 	"PREFIX call: <" + Constants.CALL + "> " +
//						"PREFIX foaf: <"+ Constants.FOAF + "> " +
//						"PREFIX schema: <" + Constants.SCHEMA + "> " + 
//						"SELECT ?fullname " +
//						"WHERE { " +
//							"?event a call:Event. " +
//							"?event schema:name ?fullname. " +
//							"?event schema:description ?description." + 
//						"FILTER regex(?description, 'history', 'i') " +
//						"}";
//		
//		return queryExecutor.executeOneVariableSelectSparqlQuery(query, "fullname", DataModelManager.getInstance().getModel());
//	}
	
//	public Collection<String> getAllCalls(String from, String to, int limit){
//		String query = "PREFIX call: <http://blog.timeliner.net/ontologies/call/ns/>" +
//				" PREFIX foaf: <http://xmlns.com/foaf/0.1/>" +
//				" PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>" +
//				" PREFIX schema: <http://schema.org/>" +
//				" PREFIX dc: <http://purl.org/dc/terms/>" +
//				" SELECT ?shortname" +
//				" WHERE {" +
//				" ?event a call:Event." +
//				" ?event dc:alternative ?shortname." +
//				" ?event call:hasCfP ?call." +
//				" ?call call:abstractDeadline ?abstractDate." +
//				" FILTER (?abstractDate > \""+from+"T00:00:00Z\"^^xsd:dateTime " +
//				"		 && ?abstractDate < \""+to+"T00:00:00Z\"^^xsd:dateTime) " +
//				" }" + " LIMIT " + limit;
//		return queryExecutor.executeOneVariableSelectSparqlQuery(query, "shortname", DataModelManager.getInstance().getModel());
//	}
//		
	
}

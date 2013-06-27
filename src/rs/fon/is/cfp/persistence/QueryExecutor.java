package rs.fon.is.cfp.persistence;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import rs.fon.is.cfp.domain.Call;
import rs.fon.is.cfp.domain.Event;
import thewebsemantic.Sparql;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.tdb.TDB;

public class QueryExecutor {

	public Collection<String> executeOneVariableSelectSparqlQuery(String query,String variable, Model model) {

		List<String> results = new LinkedList<String>();

		Query q = QueryFactory.create(query);
		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(q, model);
		ResultSet resultSet = qe.execSelect();

		while (resultSet.hasNext()) {
			QuerySolution solution = resultSet.nextSolution();
			RDFNode value = solution.get(variable);
		
			if (value.isLiteral())
				results.add(((Literal) value).getLexicalForm());
			else
				results.add(((Resource) value).getURI());
		}

		qe.close();

		return results;
	}
	
	public Model executeDescribeSparqlQuery(String queryString,
			Model model) {
		
		Query query = QueryFactory.create(queryString);
		
		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		Model resultModel = qe.execDescribe();
		
		// Important - free up resources used running the query
		qe.close();
		
		return resultModel;
	}
	
	public Call getCallForURI(String uri) {
		// method RDF2Bean.load(Class class, Object id) returns all data from rdf model regarding an object with specified URI
		return DataModelManager.getInstance().getReader().load(Call.class, uri);
	}
	
	public Event getEventForURI(String uri){
		// method RDF2Bean.load(Class class, Object id) returns all data from rdf model regarding an object with specified URI
		return DataModelManager.getInstance().getReader().load(Event.class, uri);
	}
}

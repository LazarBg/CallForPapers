package rs.fon.is.cfp.util;

import java.util.HashMap;
import java.util.Map;

public class Constants {
	
	// location of the source XML document
	public static final String XMLFILE_PATH = "data/wikicfp.v1.2010.xml";
	
	public static final String NS = "http://is.fon.rs/cfp/";
	public static final String XSD = "http://www.w3.org/2001/XMLSchema#";
	public static final String SCHEMA = "http://schema.org/";
	public static final String CALL = "http://blog.timeliner.net/ontologies/call/ns/";
	public static final String RDF = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
	public static final String RDFS = "http://www.w3.org/2000/01/rdf-schema#";
	public static final String FOAF = "http://xmlns.com/foaf/0.1/";
	public static final String DC = "http://purl.org/dc/terms/";
	
	public static Map<String,String> nsMap = new HashMap<String, String>();
	
	static {
		nsMap.put("ns", Constants.NS);
		nsMap.put("schema", Constants.SCHEMA);
		nsMap.put("xsd", Constants.XSD);
		nsMap.put("call", Constants.CALL);
		nsMap.put("rdf", Constants.RDF);
		nsMap.put("foaf", Constants.FOAF);
		nsMap.put("dc", Constants.DC);
		nsMap.put("rdfs", Constants.RDFS);
	}
}

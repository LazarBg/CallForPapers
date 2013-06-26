/**
 * 
 */
package rs.fon.is.cfp.persistence;

import rs.fon.is.cfp.persistence.dataProvider.DataProvider;
import rs.fon.is.cfp.persistence.dataProvider.TDBDataProvider;

import thewebsemantic.Bean2RDF;
import thewebsemantic.RDF2Bean;
import rs.fon.is.cfp.util.Constants;

import com.hp.hpl.jena.rdf.model.Model;

public class DataModelManager {

	private DataProvider dataProvider;
	private RDF2Bean reader;
	private Bean2RDF writer;

	private static DataModelManager INSTANCE;
	
	private DataModelManager() { 
		dataProvider = new TDBDataProvider();
		
		getModel().setNsPrefixes(Constants.nsMap);
		
		reader = new RDF2Bean(getModel());
		//reader.bindAll("rs.fon.is.cfp.domain");
		
		writer = new Bean2RDF(getModel());
	}

	public static DataModelManager getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DataModelManager();
		}
		return INSTANCE;
	}

	public Model getModel() {
		return dataProvider.getDataModel();
	}

	public RDF2Bean getReader() {
		return reader;
	}

	public Bean2RDF getWriter() {
		return writer;
	}
	
	public void save(Object o){
		writer.save(o);
	}
	
//	public void readData(String filename, String syntax) throws FileNotFoundException {
//		System.out.println("Importing data");
//		getModel().read(new FileInputStream(filename), syntax);
//		System.out.println("Import finished");
//	}
	
	public void closeDataModel() {
		dataProvider.close();
	}
	
	public void commit() {
		dataProvider.commit();
	}

}

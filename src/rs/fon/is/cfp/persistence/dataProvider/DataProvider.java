package rs.fon.is.cfp.persistence.dataProvider;

import com.hp.hpl.jena.rdf.model.Model;

public interface DataProvider {

	Model getDataModel();
	
	void setDirectory(String directory);
	
	void commit();
	
	void close();

}
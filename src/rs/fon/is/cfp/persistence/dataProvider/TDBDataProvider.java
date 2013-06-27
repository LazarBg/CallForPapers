package rs.fon.is.cfp.persistence.dataProvider;

import java.io.File;

import rs.fon.is.cfp.config.Config;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.tdb.TDBFactory;

public class TDBDataProvider implements DataProvider {

	private String directory = "tdb";
	private Dataset dataset;

	public TDBDataProvider() {
		System.out.println(Config.contextPath + File.separator + directory);
		dataset = TDBFactory.createDataset(Config.contextPath + File.separator + directory);
	}

	@Override
	public Model getDataModel() {
		return dataset.getDefaultModel();
	}
	
	@Override
	public void close() {
		dataset.close();
	}
	
	@Override
	public void commit() {
		dataset.commit();
	}

	public Dataset getDataset() {
		return dataset;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}
	
}

package rs.fon.is.cfp.main;

import java.net.URI;
import java.util.Collection;
import java.util.List;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.tdb.TDB;

import rs.fon.is.cfp.persistence.DataModelManager;
import rs.fon.is.cfp.persistence.dataProvider.TDBDataProvider;
import rs.fon.is.cfp.parser.XMLParser;
import rs.fon.is.cfp.services.QueryService;
import rs.fon.is.cfp.util.Constants;
import rs.fon.is.cfp.domain.Call;

public class Main {

	public static void main(String[] args) {
//		
//		XMLParser parser = new XMLParser();
//		
//		List<Call> calls = parser.parseCalls(Constants.XMLFILE_PATH);
//		System.out.println("*** UKUPNO IMA PREVEDENIH OBJEKATA: " + calls.size() + " ***");
//		for (Call call : calls) {
//			DataModelManager.getInstance().save(call);
//			DataModelManager.getInstance().save(call.getForEvent());
//			DataModelManager.getInstance().save(call.getForEvent().getLocation());
//		}
//		TDB.sync((DataModelManager.getInstance().getModel()));
//		DataModelManager.getInstance().closeDataModel();
	
//		---------
		
//		try {
//			QueryService qs = new QueryService();
//			//DataModelManager.getInstance();
//	
//			Collection<Call> calls = qs.getAllCalls("2010-06-01", "2010-08-31", 20);
//			System.out.println("*** Total results: " + calls.size());
//			for (Call call : calls) {
//				System.out.println("-----" + call.getUri() + "-----");
//				System.out.println(call.getAbstractDeadline());
//				System.out.println(call.getFullSubmissionDeadline());
//				System.out.println(call.getFinalDecisionDeadline());
//				System.out.println(call.getCameraReadyPaperDeadline());
//				System.out.println("\t---" + call.getForEvent().getUri() + "---");
//				System.out.println("\t" + call.getForEvent().getName());
//				System.out.println("\t" + call.getForEvent().getAlternative());
//				System.out.println("\t" + call.getForEvent().getYear());
//				System.out.println("\t" + call.getForEvent().getStartDate());
//				System.out.println("\t" + call.getForEvent().getEndDate());
//				System.out.println("\t" + call.getForEvent().getHomepage());
//				//System.out.println("\t" + call.getForEvent().getDecription());
//				System.out.println("\t\t-" + call.getForEvent().getLocation().getUri() + "-");
//				System.out.println("\t\t"+ call.getForEvent().getLocation().getName());
//				System.out.println();
//			}
//			TDB.sync((DataModelManager.getInstance().getModel()));
//			DataModelManager.getInstance().closeDataModel();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
//		try {
//			QueryService qs = new QueryService();
//			//DataModelManager.getInstance();
//	
//			Collection<String> historicCfpsNames = qs.getHistoricCfpsNames();
//			System.out.println("Total CFPs with the word 'history' in their description: " + historicCfpsNames.size());
//			for (String s : historicCfpsNames) {
//				System.out.println(s);
//			}
//			TDB.sync((DataModelManager.getInstance().getModel()));
//			DataModelManager.getInstance().closeDataModel();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
//		try {
//			QueryService qs = new QueryService();
//			//DataModelManager.getInstance();
//	
//			Model model = qs.describeAllCfps();
//			model.write(System.out, "TURTLE");
//			TDB.sync((DataModelManager.getInstance().getModel()));
//			DataModelManager.getInstance().closeDataModel();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
		//--------------
//		try {
//			QueryService qs = new QueryService();
//			//DataModelManager.getInstance();
//	
//			Model model = qs.describeAllEvents();
//			model.write(System.out, "TURTLE");
//			TDB.sync((DataModelManager.getInstance().getModel()));
//			DataModelManager.getInstance().closeDataModel();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		try {
//			QueryService qs = new QueryService();
//			//DataModelManager.getInstance();
//	
//			Collection<String> testNames = qs.getAllShortNamesOfEventsInSummer();
//			System.out.println("Total results: " + testNames.size());
//			for (String s : testNames) {
//				System.out.println(s);
//			}
//			TDB.sync((DataModelManager.getInstance().getModel()));
//			DataModelManager.getInstance().closeDataModel();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
	}

}

/**
 * 
 */
package rs.fon.is.cfp.web.listeners;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import rs.fon.is.cfp.config.Config;
import rs.fon.is.cfp.domain.Call;
import rs.fon.is.cfp.parser.XMLParser;
import rs.fon.is.cfp.persistence.DataModelManager;
import rs.fon.is.cfp.util.Constants;

/**
 * @author "Nikola Milikic"
 * 
 */
public class InitializationListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {
		XMLParser parser = new XMLParser();

		ServletContext context = contextEvent.getServletContext();
		String pathPrefix = context.getRealPath("/WEB-INF");

		// store WEB-INF path in configuration class
		Config.contextPath = pathPrefix;

		List<Call> calls = parser.parseCalls(pathPrefix + /*File.separator + "WEB-INF" +*/ File.separator + Constants.XMLFILE_PATH);
		System.out.println("*** UKUPNO IMA PREVEDENIH OBJEKATA: " + calls.size() + " ***");
		for (Call call : calls) {
			DataModelManager.getInstance().save(call);
			DataModelManager.getInstance().save(call.getForEvent());
			DataModelManager.getInstance().save(call.getForEvent().getLocation());
		}
		// TDB.sync((DataModelManager.getInstance().getModel()));
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		DataModelManager.getInstance().closeDataModel();
	}

}

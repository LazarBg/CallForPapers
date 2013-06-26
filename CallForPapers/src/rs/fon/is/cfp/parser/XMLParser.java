package rs.fon.is.cfp.parser;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import rs.fon.is.cfp.util.URIGenerator;
import rs.fon.is.cfp.domain.Call;
import rs.fon.is.cfp.domain.Event;
import rs.fon.is.cfp.domain.Place;

public class XMLParser {
	
	public List<Call> parseCalls(String filePath) {
		List<Call> calls = new ArrayList<>();
		
		try {
	        Document document = new SAXReader().read(filePath);
	        
	        LinkedList<SourceObject> sourceList = getSourceObjects(document);
	        int brojac = 0;
	        
			for (int i = 0; i < sourceList.size(); i++){
				try {
					calls.add(parseCall(sourceList.get(i)));
					System.out.println("- Gotov objekat broj: " + ++brojac);
				} catch (Exception e){
					continue;
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return calls;
	}
	
	
	private Event parseEvent(SourceObject s) throws URISyntaxException, ParseException {
		Event e = new Event();
		e.setUri(URIGenerator.generate(e));
		e.setName(s.getFullname());
		e.setAlternative(s.getHandle());
		e.setYear(Integer.parseInt(s.getYear()));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'hh:mm:ss");
		e.setCreated(s.getCreatedate().equals("") ? null : sdf.parse(s.getCreatedate()));
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		e.setStartDate(s.getBegindate().equals("") ? null : sdf1.parse(s.getBegindate()));
		e.setEndDate(s.getFinishdate().equals("") ? null : sdf1.parse(s.getFinishdate()));
		e.setDecription(s.getInfo());
		e.setHomepage(URI.create(s.getWeblink()));

		Place pl = parsePlace(s);
		e.setLocation(pl);
		
		return e;
	}


	private Call parseCall(SourceObject s) throws ParseException, URISyntaxException {
		Call c = new Call();
		c.setUri(URIGenerator.generate(c));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		c.setAbstractDeadline(s.getPresubdate().equals("") ? null : sdf.parse(s.getPresubdate()));
		c.setFullSubmissionDeadline(s.getSubmitdate().equals("") ? null : sdf.parse(s.getSubmitdate()));
		c.setFinalDecisionDeadline(s.getNotifydate().equals("") ? null : sdf.parse(s.getNotifydate()));
		c.setCameraReadyPaperDeadline(s.getCameradate().equals("") ? null : sdf.parse(s.getCameradate()));
		
		Event e = parseEvent(s);
		e.setCall(c);
		c.setForEvent(e);
		
		return c;
	}


	private Place parsePlace(SourceObject s) throws URISyntaxException {
		Place pl = new Place();
		pl.setUri(URIGenerator.generate(pl));
		pl.setName(s.getLocation());
		return pl;
	}


	/*
	 * Iterates through xml document, for each <field> element, instance of SourceObject is created
	 * Returns a LinkedList of SourceObject objects, which represents an internal structure of the document
	 */
	public LinkedList<SourceObject> getSourceObjects(Document doc) throws DocumentException{
		LinkedList<SourceObject> parsedObjects = new LinkedList<SourceObject>();
		
		Element root = doc.getRootElement();
		
		for(Iterator<Element> i = root.elementIterator(); i.hasNext(); ){
			
			Element rowElement = i.next();
			SourceObject so = new SourceObject();
			
			for (Iterator<Element> j = rowElement.elementIterator(); j.hasNext();){
				Element fieldElement = j.next();
				switch (fieldElement.attributeValue("name")) {
					case "eventid":
						so.setEventid(fieldElement.getTextTrim());
						break;
					case "createdate":
						so.setCreatedate(fieldElement.getTextTrim());
						break;
					case "fullname":
						so.setFullname(fieldElement.getTextTrim());
						break;
					case "handle":
						so.setHandle(fieldElement.getTextTrim());
						break;
					case "year":
						so.setYear(fieldElement.getTextTrim());
						break;
					case "location":
						so.setLocation(fieldElement.getTextTrim());
						break;
					case "begindate":
						so.setBegindate(fieldElement.getTextTrim());
						break;
					case "finishdate":
						so.setFinishdate(fieldElement.getTextTrim());
						break;
					case "presubdate":
						so.setPresubdate(fieldElement.getTextTrim());
						break;
					case "submitdate":
						so.setSubmitdate(fieldElement.getTextTrim());
						break;
					case "notifydate":
						so.setNotifydate(fieldElement.getTextTrim());
						break;
					case "cameradate":
						so.setCameradate(fieldElement.getTextTrim());
					case "weblink":
						so.setWeblink(fieldElement.getTextTrim());
						break;
					case "info":
						so.setInfo(fieldElement.getTextTrim());
						break;
				}
				
			}

			parsedObjects.add(so);
		}
		
		return parsedObjects;
	}
}

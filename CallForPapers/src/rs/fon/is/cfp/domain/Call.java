package rs.fon.is.cfp.domain;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.google.gson.JsonObject;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import riotcmd.json;
import rs.fon.is.cfp.util.Constants;

@Namespace(Constants.CALL)
@RdfType("Call")
public class Call extends Thing{
	
	// represents the "presubdate" attribute;
	@RdfProperty(Constants.CALL + "abstractDeadline")
	private Date abstractDeadline;
	
	// represents the "submitdate" attribute;
	@RdfProperty(Constants.CALL + "fullSubmissionDeadline")
	private Date fullSubmissionDeadline;
	
	// represents the "notifydate" attribute;
	@RdfProperty(Constants.CALL + "finalDecisionDeadline")
	private Date finalDecisionDeadline;
	
	// represents the "camera" attribute;
	@RdfProperty(Constants.CALL + "cameraReadyPaperDeadline")
	private Date cameraReadyPaperDeadline;
	
	// every Call For Papers is associated to some Event;
	@RdfProperty(Constants.CALL + "forEvent")
	private Event forEvent;
	
	public Call(){
		
	}
	
	// GETTERS AND SETTERS
	public Date getAbstractDeadline() {
		return abstractDeadline;
	}
	public void setAbstractDeadline(Date abstractDeadline) {
		this.abstractDeadline = abstractDeadline;
	}
	public Date getFullSubmissionDeadline() {
		return fullSubmissionDeadline;
	}
	public void setFullSubmissionDeadline(Date fullSubmissionDeadline) {
		this.fullSubmissionDeadline = fullSubmissionDeadline;
	}
	public Date getFinalDecisionDeadline() {
		return finalDecisionDeadline;
	}
	public void setFinalDecisionDeadline(Date finalDecisionDeadline) {
		this.finalDecisionDeadline = finalDecisionDeadline;
	}
	public Date getCameraReadyPaperDeadline() {
		return cameraReadyPaperDeadline;
	}
	public void setCameraReadyPaperDeadline(Date cameraReadyPaperDeadline) {
		this.cameraReadyPaperDeadline = cameraReadyPaperDeadline;
	}
	public Event getForEvent() {
		return forEvent;
	}
	public void setForEvent(Event forEvent) {
		this.forEvent = forEvent;
	}
	
	@Override
	public String toString() {
		JsonObject jsonCall = new JsonObject();
		
		jsonCall.addProperty("URI", getUri().toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		jsonCall.addProperty("abstractDeadline", (abstractDeadline == null) ? "" : sdf.format(abstractDeadline));
		jsonCall.addProperty("fullSubmissionDeadline", (fullSubmissionDeadline == null) ? "" : sdf.format(fullSubmissionDeadline));
		jsonCall.addProperty("finalDecisionDeadline", (finalDecisionDeadline == null) ? "" : sdf.format(finalDecisionDeadline));
		jsonCall.addProperty("cameraReadyPaperDeadline", (cameraReadyPaperDeadline == null) ? "" : sdf.format(cameraReadyPaperDeadline));
		jsonCall.add("forEvent", forEvent.returnAsJson());
		
		return jsonCall.toString();
	}
	
}

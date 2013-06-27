package rs.fon.is.cfp.parser;

public class SourceObject {
	
	private String eventid;
	private String createdate;
	private String fullname;
	private String handle;
	private String year;
	private String location;
	private String begindate;
	private String finishdate;
	private String presubdate;
	private String submitdate;
	private String notifydate;
	private String cameradate;
	private String weblink;
	private String info;
	
	public SourceObject(){
		
	}
	
	// sets the values of appropriate fields, based on source file
	// attributes are sort in order as they appear in XML file
	public <T> void updateFields(T value){
		if (eventid == null)
			eventid = (String) value;
		else if (createdate == null)
			createdate = (String) value;
		else if (fullname == null)
			fullname = (String) value;
		else if (handle == null)
			handle = (String) value;
		else if (year == null)
			year = (String) value;
		else if (location == null)
			location = (String) value;
		else if (begindate == null)
			begindate = (String) value;
		else if (finishdate == null)
			finishdate = (String) value;
		else if (presubdate == null)
			presubdate = (String) value;
		else if (submitdate == null)
			submitdate = (String) value;
		else if (notifydate == null)
			notifydate = (String) value;
		else if (cameradate == null)
			cameradate = (String) value;
		else if (weblink == null)
			weblink = (String) value;
		else if (info == null)
			info = (String) value;
	}
	
	// GETTERS
	public String getEventid() {
		return eventid;
	}

	public String getCreatedate() {
		return createdate;
	}

	public String getFullname() {
		return fullname;
	}

	public String getHandle() {
		return handle;
	}

	public String getYear() {
		return year;
	}

	public String getLocation() {
		return location;
	}

	public String getBegindate() {
		return begindate;
	}

	public String getFinishdate() {
		return finishdate;
	}

	public String getPresubdate() {
		return presubdate;
	}

	public String getSubmitdate() {
		return submitdate;
	}

	public String getNotifydate() {
		return notifydate;
	}

	public String getCameradate() {
		return cameradate;
	}

	public String getWeblink() {
		return weblink;
	}

	public String getInfo() {
		return info;
	}

	public void setEventid(String eventid) {
		this.eventid = eventid;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setBegindate(String begindate) {
		this.begindate = begindate;
	}

	public void setFinishdate(String finishdate) {
		this.finishdate = finishdate;
	}

	public void setPresubdate(String presubdate) {
		this.presubdate = presubdate;
	}

	public void setSubmitdate(String submitdate) {
		this.submitdate = submitdate;
	}

	public void setNotifydate(String notifydate) {
		this.notifydate = notifydate;
	}

	public void setCameradate(String cameradate) {
		this.cameradate = cameradate;
	}

	public void setWeblink(String weblink) {
		this.weblink = weblink;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
}

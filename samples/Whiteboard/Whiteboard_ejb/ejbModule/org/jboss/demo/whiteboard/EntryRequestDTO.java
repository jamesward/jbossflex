package org.jboss.demo.whiteboard;

public class EntryRequestDTO {
	
	private String entryRequestId;
	private String attendeeName;
	private String whiteboardId;
	
	public EntryRequestDTO()
	{
		super();
	}
	
	public EntryRequestDTO(EntryRequest entryRequest)
	{
		super();
		
		this.entryRequestId = entryRequest.getId(); 
		this.attendeeName = entryRequest.getAttendee().getDisplayName();
		this.whiteboardId = entryRequest.getWhiteboard().getId();
	}
	
	public String getEntryRequestId() {
		return entryRequestId;
	}
	public void setEntryRequestId(String entryRequestId) {
		this.entryRequestId = entryRequestId;
	}

	public String getAttendeeName() {
		return attendeeName;
	}
	public void setAttendeeName(String attendeeName) {
		this.attendeeName = attendeeName;
	}
	
	public String getWhiteboardId() {
		return whiteboardId;
	}
	public void setWhiteboardId(String whiteboardId) {
		this.whiteboardId = whiteboardId;
	}
	
}
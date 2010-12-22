package org.jboss.demo.whiteboard;

import javax.ejb.Local;

@Local
public interface WhiteboardServiceLocal {

	public AttendeeDTO enterWhiteboard(String whiteboardName, String userDisplayName, String whiteboardId);
	
	public void disconnectFromWhiteboard(AttendeeDTO attendee);

	public WhiteboardDTO getWhiteboard(String attendeeId);

	public String getWhiteboardNameFromId(String whiteboardId);
	
	public void drawOnWhiteboard(String whiteboardId, DrawDTO drawDTO);

	public void eraseWhiteboard(String whiteboardId);

}
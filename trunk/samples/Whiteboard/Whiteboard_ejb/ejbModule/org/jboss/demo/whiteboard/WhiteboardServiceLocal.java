package org.jboss.demo.whiteboard;

import javax.ejb.Local;

@Local
public interface WhiteboardServiceLocal {

	public AttendeeDTO createWhiteboard(String whiteboardName,
			String userDisplayName);

	public AttendeeDTO connectToWhiteboard(String whiteboardId,
			String userDisplayName);

	public void disconnectFromWhiteboard(AttendeeDTO attendee);

	public WhiteboardDTO getWhiteboard(String attendeeId);

	public void drawOnWhiteboard(String whiteboardId, DrawDTO drawDTO);

	public void eraseWhiteboard(String whiteboardId);

}
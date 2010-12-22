package org.jboss.demo.whiteboard;
import javax.ejb.Local;

@Local
public interface EntryRequestServiceLocal {

	public void createEntryRequest(Attendee attendee, Whiteboard whiteboard);
	
	public void approveEntryRequest(EntryRequestDTO entryRequestDTO);
}
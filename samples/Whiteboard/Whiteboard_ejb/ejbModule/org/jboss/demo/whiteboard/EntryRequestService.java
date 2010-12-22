package org.jboss.demo.whiteboard;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import flex.messaging.MessageBroker;
import flex.messaging.messages.AsyncMessage;
import flex.messaging.util.UUIDUtils;

/**
 * Session Bean implementation class EntryRequest
 */
@Stateless
public class EntryRequestService implements EntryRequestServiceLocal {

	@PersistenceContext
	private EntityManager entityManager;
	
	// Todo: exclude this from remoting
	public void createEntryRequest(Attendee attendee, Whiteboard whiteboard) {
		
		EntryRequest entryRequest = new EntryRequest();
		entryRequest.setAttendee(attendee);
		entryRequest.setWhiteboard(whiteboard);
		entityManager.persist(entryRequest);
		entityManager.flush();
		
		// notify users in whiteboard that someone would like to join
		AsyncMessage msg = new AsyncMessage();
	    msg.setClientId(UUIDUtils.createUUID(false));
	    msg.setMessageId(UUIDUtils.createUUID(false));
	    msg.setHeader(AsyncMessage.SUBTOPIC_HEADER_NAME, whiteboard.getId());
	    msg.setDestination("entryRequest");
	    msg.setTimestamp(System.currentTimeMillis());
	    msg.setBody(new EntryRequestDTO(entryRequest));
	    MessageBroker mb = MessageBroker.getMessageBroker(null);
	    mb.routeMessageToService(msg, null);
	}

	public void approveEntryRequest(EntryRequestDTO entryRequestDTO) {
		EntryRequest entryRequest = entityManager.find(EntryRequest.class, entryRequestDTO.getEntryRequestId());
		
		if (entryRequest != null)
		{
			// notify attendee of the whiteboardId to allow them in
			AsyncMessage msg = new AsyncMessage();
		    msg.setClientId(UUIDUtils.createUUID(false));
		    msg.setMessageId(UUIDUtils.createUUID(false));
		    msg.setHeader(AsyncMessage.SUBTOPIC_HEADER_NAME, entryRequest.getAttendee().getId());
		    msg.setDestination("entryRequest");
		    msg.setTimestamp(System.currentTimeMillis());
		    msg.setBody(entryRequest.getWhiteboard().getId());
		    MessageBroker mb = MessageBroker.getMessageBroker(null);
		    mb.routeMessageToService(msg, null);
		    
		    // now delete the request
		    entityManager.remove(entryRequest);
		}
	}

}
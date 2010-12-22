package org.jboss.demo.whiteboard;

import java.util.ArrayList;
import java.util.Iterator;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;


/**
 * Session Bean implementation class WhiteboardService
 */
@Stateless
//@SecurityDomain("whiteboard")
public class WhiteboardService implements WhiteboardServiceLocal {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@EJB
	private EntryRequestServiceLocal entryRequest;

	public AttendeeDTO enterWhiteboard(String whiteboardName, String userDisplayName, String whiteboardId) {
		Attendee attendee = new Attendee(userDisplayName);
		entityManager.persist(attendee);
		
		Whiteboard whiteboard;
		if (whiteboardId == null || whiteboardId.length() == 0) {
			whiteboard = getWhiteboardFromName(whiteboardName);
			
			if (whiteboard != null)	{
				entryRequest.createEntryRequest(attendee, whiteboard);
				
				return new AttendeeDTO(attendee);
			}
		}
		else {
			whiteboard = getWhiteboardFromId(whiteboardId);
		}
		
		if (whiteboard == null)
		{
			// whiteboard doesn't exist so create one
			whiteboard = new Whiteboard();
			whiteboard.setName(whiteboardName);
			entityManager.persist(whiteboard);
		}
		
		attendee.setWhiteboard(whiteboard);
		
		return new AttendeeDTO(attendee);
	}

	public void disconnectFromWhiteboard(AttendeeDTO attendee) {
		entityManager.remove(entityManager.find(Attendee.class, attendee.getId()));
	}

	//@RolesAllowed( { "host,attendee" })
	public WhiteboardDTO getWhiteboard(String whiteboardId) {
		Whiteboard whiteboard = getWhiteboardFromId(whiteboardId);

		return new WhiteboardDTO(whiteboard);
	}

	//@RolesAllowed( { "drawer" })
	public void drawOnWhiteboard(String whiteboardId, DrawDTO drawDTO) {
		Whiteboard whiteboard = getWhiteboardFromId(whiteboardId);

		Draw draw = new Draw();
		draw.setDrawColor(drawDTO.getDrawColor());
		draw.setX1(drawDTO.getX1());
		draw.setY1(drawDTO.getY1());
		draw.setX2(drawDTO.getX2());
		draw.setY2(drawDTO.getY2());
		draw.setWhiteboard(whiteboard);

		entityManager.persist(draw);
	}

	public void eraseWhiteboard(String whiteboardId) {
		Whiteboard whiteboard = getWhiteboardFromId(whiteboardId);

		Iterator<Draw> d = whiteboard.getDraws().iterator();
		while (d.hasNext()) {
			entityManager.remove(d.next());
		}

		whiteboard.setDraws(new ArrayList<Draw>());
	}
	
	public String getWhiteboardNameFromId(String whiteboardId)
	{
		Whiteboard whiteboard = getWhiteboardFromId(whiteboardId);
		if (whiteboard != null)
		{
			return whiteboard.getName();
		}
		
		return null;
	}
	

	private Whiteboard getWhiteboardFromId(String whiteboardId) {
		try {
			Whiteboard whiteboard = (Whiteboard) entityManager.createQuery(
					"from Whiteboard where id = :whiteboardId").setParameter(
					"whiteboardId", whiteboardId).getSingleResult();
	
			return whiteboard;
		}
		catch (NoResultException e) {
			
		}
		
		return null;
	}
	
	private Whiteboard getWhiteboardFromName(String whiteboardName) {
		try {
			Whiteboard whiteboard = (Whiteboard) entityManager.createQuery(
				"from Whiteboard where name = :whiteboardName").setParameter(
				"whiteboardName", whiteboardName).getSingleResult();
			return whiteboard;
		}
		catch (NoResultException e) {
			
		}
		
		return null;
	}

}
package org.jboss.demo.whiteboard;

import java.util.ArrayList;
import java.util.Iterator;

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

	//@RolesAllowed( { "host" })
	public void assignDrawer(Attendee attendee) {

	}

	//@RolesAllowed( { "host" })
	public AttendeeDTO createWhiteboard(String whiteboardName,
			String userDisplayName) {
		Whiteboard whiteboard = new Whiteboard();
		whiteboard.setName(whiteboardName);

		Attendee attendee = new Attendee(userDisplayName);
		whiteboard.addAttendee(attendee);

		// Persisting the whiteboard instead of the attendee and the whiteboard.
		// Added cascade to getAttendees method on whiteboard
		entityManager.persist(whiteboard);

		return new AttendeeDTO(attendee);
	}

	//@RolesAllowed( { "attendee" })
	public AttendeeDTO connectToWhiteboard(String whiteboardId,
			String userDisplayName) {
		Whiteboard whiteboard;

		try {
			whiteboard = getWhiteboardFromId(whiteboardId);
		} catch (NoResultException e) {
			return createWhiteboard("new whiteboard", userDisplayName);
		}

		Attendee attendee = new Attendee(userDisplayName);
		attendee.setWhiteboard(whiteboard);

		entityManager.persist(attendee);

		return new AttendeeDTO(attendee);
	}

	public void disconnectFromWhiteboard(AttendeeDTO attendee) {
		entityManager.remove(entityManager.find(Attendee.class, attendee
				.getId()));
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

	private Whiteboard getWhiteboardFromId(String whiteboardId) {
		Whiteboard whiteboard = (Whiteboard) entityManager.createQuery(
				"from Whiteboard where id = :whiteboardId").setParameter(
				"whiteboardId", whiteboardId).getSingleResult();

		return whiteboard;
	}

	public Attendee addAttendee(String whiteboardId, String displayName, String password) {
		Whiteboard whiteboard = getWhiteboardFromId(whiteboardId);

		Attendee attendee =whiteboard.addAttendee(displayName);
		User user = new User();
		user.setUsername(displayName);
		user.setPasswd(password);
		  
		UserRole userRole = new UserRole("attendee");
		user.addUserRole(userRole);
		attendee.setUser(user);
		entityManager.merge(whiteboard);
		return attendee;
	}

}
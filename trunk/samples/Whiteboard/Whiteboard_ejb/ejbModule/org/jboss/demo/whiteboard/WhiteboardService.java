package org.jboss.demo.whiteboard;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class WhiteboardService
 */
@Stateless
public class WhiteboardService implements WhiteboardServiceLocal
{
  @PersistenceContext
  private EntityManager entityManager;
  
  public AttendeeDTO createWhiteboard(String whiteboardName, String userDisplayName)
  {
    Whiteboard whiteboard = new Whiteboard();
    whiteboard.setName(whiteboardName);
    entityManager.persist(whiteboard);
    
    Attendee attendee = new Attendee(userDisplayName);
    attendee.setWhiteboard(whiteboard);
    
    entityManager.persist(attendee);
    
    return new AttendeeDTO(attendee);
  }
  
  public AttendeeDTO connectToWhiteboard(String whiteboardId, String userDisplayName)
  {
    Whiteboard whiteboard = (Whiteboard)entityManager.createQuery("from Whiteboard where id = :whiteboardId")
    .setParameter("whiteboardId", whiteboardId)
    .getSingleResult();
    
    Attendee attendee = new Attendee(userDisplayName);
    attendee.setWhiteboard(whiteboard);
    
    entityManager.persist(attendee);
    
    return new AttendeeDTO(attendee);
  }
  
  public void disconnectFromWhiteboard(AttendeeDTO attendee)
  {
    entityManager.remove(entityManager.find(Attendee.class, attendee.getId()));
  }
  
  public WhiteboardDTO getWhiteboard(String whiteboardId)
  {
    Whiteboard whiteboard = (Whiteboard)entityManager.createQuery("from Whiteboard where id = :whiteboardId")
    .setParameter("whiteboardId", whiteboardId)
    .getSingleResult();
    
    return new WhiteboardDTO(whiteboard);
  }

}
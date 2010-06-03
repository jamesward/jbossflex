package org.jboss.demo.whiteboard;

import java.util.HashMap;
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
    Whiteboard whiteboard;
    
    try
    {
      whiteboard = getWhiteboardFromId(whiteboardId);
    }
    catch (NoResultException e)
    {
      return createWhiteboard("new whiteboard", userDisplayName);
    }
    
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
    Whiteboard whiteboard = getWhiteboardFromId(whiteboardId);
    
    return new WhiteboardDTO(whiteboard);
  }
  
  public void drawOnWhiteboard(String whiteboardId, DrawDTO drawDTO)
  {
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
  
  public void eraseWhiteboard(String whiteboardId)
  {
    Whiteboard whiteboard = getWhiteboardFromId(whiteboardId);
    
    Iterator<Draw> d = whiteboard.getDraws().iterator();
    while (d.hasNext())
    {
      entityManager.remove(d.next());
    }
    
    whiteboard.setDraws(new ArrayList<Draw>());
  }

  private Whiteboard getWhiteboardFromId(String whiteboardId)
  {
    Whiteboard whiteboard = (Whiteboard)entityManager.createQuery("from Whiteboard where id = :whiteboardId")
    .setParameter("whiteboardId", whiteboardId)
    .getSingleResult();
    
    return whiteboard;
  }

}
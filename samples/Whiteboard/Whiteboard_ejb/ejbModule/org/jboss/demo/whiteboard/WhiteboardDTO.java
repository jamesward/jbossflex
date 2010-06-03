package org.jboss.demo.whiteboard;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class WhiteboardDTO
{
  
  private String id;
  private String name;
  private List<String> attendeeNames = new ArrayList<String>();
  private List<DrawDTO> draws = new ArrayList<DrawDTO>();
  

  public WhiteboardDTO()
  {
    
  }
  
  public WhiteboardDTO(Whiteboard whiteboard)
  {
    id = whiteboard.getId();
    name = whiteboard.getName();
       
    Iterator<Attendee> i = whiteboard.getAttendees().iterator();
    while (i.hasNext())
    {
      attendeeNames.add(i.next().getDisplayName());
    }
    
    Iterator<Draw> d = whiteboard.getDraws().iterator();
    while (d.hasNext())
    {
      draws.add(new DrawDTO(d.next()));
    }
  }
  
  public String getId()
  {
    return id;
  }
  public void setId(String id)
  {
    this.id = id;
  }
  
  public String getName()
  {
    return name;
  }
  public void setName(String name)
  {
    this.name = name;
  }
  
  public List<String> getAttendeeNames()
  {
    return attendeeNames;
  }
  public void setAttendeeNames(List<String> attendeeNames)
  {
    this.attendeeNames = attendeeNames;
  }
  
  public List<DrawDTO> getDraws()
  {
	return draws;
  }
  public void setDraws(List<DrawDTO> draws)
  {
	this.draws = draws;
  }

}

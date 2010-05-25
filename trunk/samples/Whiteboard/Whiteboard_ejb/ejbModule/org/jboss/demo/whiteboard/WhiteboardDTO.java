package org.jboss.demo.whiteboard;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class WhiteboardDTO
{
  
  private String id;
  private String name;
  private Set<String> attendeeNames = new HashSet<String>();
  
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
  public Set<String> getAttendeeNames()
  {
    return attendeeNames;
  }
  public void setAttendeeNames(Set<String> attendeeNames)
  {
    this.attendeeNames = attendeeNames;
  }

}

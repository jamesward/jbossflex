package org.jboss.demo.whiteboard;

public class AttendeeDTO
{

  private String id;
  private String displayName;
  private WhiteboardDTO whiteboard;
  
  public AttendeeDTO()
  {
    super();
  }
  
  public AttendeeDTO(Attendee attendee)
  {
    id = attendee.getId();
    displayName = attendee.getDisplayName();
    if (attendee.getWhiteboard() != null)
    {
    	whiteboard = new WhiteboardDTO(attendee.getWhiteboard());
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
  public String getDisplayName()
  {
    return displayName;
  }
  public void setDisplayName(String displayName)
  {
    this.displayName = displayName;
  }
  public WhiteboardDTO getWhiteboard()
  {
    return whiteboard;
  }
  public void setWhiteboard(WhiteboardDTO whiteboard)
  {
    this.whiteboard = whiteboard;
  }
  
}
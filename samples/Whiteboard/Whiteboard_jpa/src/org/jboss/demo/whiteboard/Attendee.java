package org.jboss.demo.whiteboard;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Attendee
{
  
  private String id;
  private String displayName;
  private Whiteboard whiteboard;

  public Attendee()
  {

  }
  
  public Attendee(String displayName)
  {
    setDisplayName(displayName);
  }
  
  @Id
  @GeneratedValue(generator="UUIDKeyGenerator")
  @GenericGenerator(name="UUIDKeyGenerator", strategy="org.jboss.demo.whiteboard.UUIDKeyGenerator")
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
  
  @ManyToOne
  public Whiteboard getWhiteboard()
  {
    return whiteboard;
  }
  
  public void setWhiteboard(Whiteboard whiteboard)
  {
    this.whiteboard = whiteboard;
  }

}
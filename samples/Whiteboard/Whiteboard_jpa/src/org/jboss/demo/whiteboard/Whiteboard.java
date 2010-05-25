package org.jboss.demo.whiteboard;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Whiteboard
{
  
  private String id;
  private String name;
  private Set<Attendee> attendees = new HashSet<Attendee>();

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
  
  public String getName()
  {
    return name;
  }
  public void setName(String name)
  {
    this.name = name;
  }
  
  @OneToMany(mappedBy="whiteboard")
  public Set<Attendee> getAttendees()
  {
    return attendees;
  }
  public void setAttendees(Set<Attendee> attendees)
  {
    this.attendees = attendees;
  }
  
}
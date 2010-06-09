package org.jboss.demo.whiteboard;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="ATTENDEE_TYPE",
    discriminatorType=DiscriminatorType.STRING
)
@DiscriminatorValue("ATTENDEE")
public class Attendee
{
  
  private String id;
  private String displayName;
  private Whiteboard whiteboard;
  private User user;

  public Attendee()
  {

  }
  
  public Attendee(String displayName)
  {
    setDisplayName(displayName);
  }
  
  @Id
  @GeneratedValue(generator="UUIDKeyGenerator_Attendee")
  @GenericGenerator(name="UUIDKeyGenerator_Attendee", strategy="org.jboss.demo.whiteboard.UUIDKeyGenerator")
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
  @JoinColumn(name="WHITEBOARD")
  public Whiteboard getWhiteboard()
  {
    return whiteboard;
  }
  
  public void setWhiteboard(Whiteboard whiteboard)
  {
    this.whiteboard = whiteboard;
  }

  @ManyToOne(cascade=CascadeType.ALL)
  @JoinColumn(name="user")
  public User getUser() {
	return user;
  }

	public void setUser(User user) {
		this.user = user;
	}

  

}
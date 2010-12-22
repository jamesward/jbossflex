package org.jboss.demo.whiteboard;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.jboss.demo.whiteboard.Attendee;
import org.jboss.demo.whiteboard.Whiteboard;

/**
 * Entity implementation class for Entity: EntryRequest
 *
 */
@Entity
public class EntryRequest implements Serializable {

	
	private String id;
	private Whiteboard whiteboard;
	private Attendee attendee;
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(generator="UUIDKeyGenerator_EntryRequest")
	@GenericGenerator(name="UUIDKeyGenerator_EntryRequest", strategy="org.jboss.demo.whiteboard.UUIDKeyGenerator")
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@ManyToOne
	public Whiteboard getWhiteboard() {
		return this.whiteboard;
	}
	public void setWhiteboard(Whiteboard whiteboard) {
		this.whiteboard = whiteboard;
	}
	
	@ManyToOne
	public Attendee getAttendee() {
		return this.attendee;
	}
	public void setAttendee(Attendee attendee) {
		this.attendee = attendee;
	}
   
}
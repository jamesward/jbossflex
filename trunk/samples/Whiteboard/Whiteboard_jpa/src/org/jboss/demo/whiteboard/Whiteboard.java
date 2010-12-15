package org.jboss.demo.whiteboard;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.persistence.Id;
import javax.persistence.OrderBy;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Whiteboard {

	private String id;
	private String name;
	private List<Attendee> attendees = new ArrayList<Attendee>();
	private List<Draw> draws = new ArrayList<Draw>();

	@Id
	@GeneratedValue(generator = "UUIDKeyGenerator_Whiteboard")
	@GenericGenerator(name = "UUIDKeyGenerator_Whiteboard", strategy = "org.jboss.demo.whiteboard.UUIDKeyGenerator")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy = "whiteboard", cascade = CascadeType.ALL)
	@OrderBy("displayName")
	public List<Attendee> getAttendees() {
		return attendees;
	}

	public void setAttendees(List<Attendee> attendees) {
		this.attendees = attendees;
	}

	@OneToMany(mappedBy = "whiteboard", fetch = FetchType.EAGER)
	@OrderBy("id")
	public List<Draw> getDraws() {
		return draws;
	}

	public void setDraws(List<Draw> draws) {
		this.draws = draws;
	}

	public Attendee addAttendee(Attendee attendee) {
		attendee.setWhiteboard(this);
		this.getAttendees().add(attendee);
		return attendee;
	}

	public Attendee addAttendee(String displayName) {

		Attendee newAttendee = new Attendee();
		newAttendee.setDisplayName(displayName);
		this.addAttendee(newAttendee);
		return newAttendee;
		
	}

}
package org.jboss.demo.whiteboard;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("HOST")
public class Host extends Attendee {

}

package org.jboss.demo.whiteboard
{
  import flash.events.Event;
  
  import org.jboss.demo.whiteboard.AttendeeDTO;
  
  public class AttendeeEvent extends Event
  {
    public var attendee:AttendeeDTO;
    
    public function AttendeeEvent(type:String, attendee:AttendeeDTO, bubbles:Boolean=false, cancelable:Boolean=false)
    {
      super(type, bubbles, cancelable);
      this.attendee = attendee;
    }
  }
}
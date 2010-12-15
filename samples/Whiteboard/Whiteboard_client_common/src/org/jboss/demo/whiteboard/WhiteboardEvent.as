package org.jboss.demo.whiteboard
{
  import flash.events.Event;
  
  import org.jboss.demo.whiteboard.WhiteboardDTO;
  
  public class WhiteboardEvent extends Event
  {
    public var whiteboard:WhiteboardDTO;
    
    public function WhiteboardEvent(type:String, whiteboard:WhiteboardDTO, bubbles:Boolean=false, cancelable:Boolean=false)
    {
      super(type, bubbles, cancelable);
      this.whiteboard = whiteboard;
    }
  }
}
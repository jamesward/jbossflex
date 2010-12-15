package org.jboss.demo.whiteboard
{
  import flash.events.Event;

  public class DrawEvent extends Event
  {
    public var draw:DrawDTO;
    
    public function DrawEvent(type:String, draw:DrawDTO=null, bubbles:Boolean=false, cancelable:Boolean=false)
    {
      super(type, bubbles, cancelable);
      this.draw = draw;
    }
  }
}
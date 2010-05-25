package org.jboss.demo.whiteboard
{
  import mx.collections.ArrayCollection;

  [RemoteClass(alias="org.jboss.demo.whiteboard.WhiteboardDTO")]
  [Bindable]
  public class WhiteboardDTO
  {
    public var id:String;
    public var name:String
    public var attendeeNames:ArrayCollection;
  }
}
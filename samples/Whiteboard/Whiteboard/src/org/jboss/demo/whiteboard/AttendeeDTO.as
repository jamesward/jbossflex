package org.jboss.demo.whiteboard
{
  [RemoteClass(alias="org.jboss.demo.whiteboard.AttendeeDTO")]
  [Bindable]
  public class AttendeeDTO
  {
    public var id:String;
    public var displayName:String
    public var whiteboard:WhiteboardDTO;
  }
}
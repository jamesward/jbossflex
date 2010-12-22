package org.jboss.demo.whiteboard
{
  import mx.collections.ArrayCollection;
  import mx.messaging.ChannelSet;
  import mx.messaging.channels.AMFChannel;
  import mx.messaging.channels.StreamingAMFChannel;
  

  public class Model
  {
    
    public function Model()
    {
      pendingEntryRequests = new ArrayCollection();
    }
    
    private static var instance:Model;
    
    public var attendee:AttendeeDTO;
    
    [Bindable]
    public var pendingEntryRequests:ArrayCollection;
    
    
    public static function getInstance():Model
    {
      if (instance == null)
        instance = new Model();
      
      return instance;
    }
  }
}
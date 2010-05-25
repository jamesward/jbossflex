package
{
  import mx.messaging.ChannelSet;
  import mx.messaging.channels.AMFChannel;
  import mx.messaging.channels.StreamingAMFChannel;
  
  import org.jboss.demo.whiteboard.AttendeeDTO;

  public class Model
  {
    
    private static var instance:Model;
    
    public var attendee:AttendeeDTO;
    
    
    public static function getInstance():Model
    {
      if (instance == null)
        instance = new Model();
      
      return instance;
    }
  }
}
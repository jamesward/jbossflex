package
{
  import mx.messaging.ChannelSet;
  import mx.messaging.channels.AMFChannel;
  import mx.messaging.channels.StreamingAMFChannel;

  public class Config
  {
    
    public function Config()
    {
      messagingChannelSet = new ChannelSet();
      
      var streamingChannel:StreamingAMFChannel = new StreamingAMFChannel();
      streamingChannel.url = "messagebroker/streamingamf";
      
      messagingChannelSet.addChannel(streamingChannel);
      
      var pollingChannel:AMFChannel = new AMFChannel();
      pollingChannel.pollingEnabled = true;
      pollingChannel.url = "messagebroker/amfpolling";
      pollingChannel.pollingInterval = 5000;
      
      messagingChannelSet.addChannel(pollingChannel);
    }
    
    private static var instance:Config;
    
    
    
    public var messagingChannelSet:ChannelSet;
    
    
    public static function getInstance():Config
    {
      if (instance == null)
        instance = new Config();
      
      return instance;
    }
  }
}
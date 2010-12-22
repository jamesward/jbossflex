package org.jboss.demo.whiteboard
{
  import flash.events.Event;
  import flash.events.EventDispatcher;
  
  import mx.messaging.ChannelSet;
  import mx.messaging.Consumer;
  import mx.messaging.Producer;
  import mx.messaging.channels.AMFChannel;
  import mx.messaging.channels.StreamingAMFChannel;
  import mx.messaging.events.MessageEvent;
  import mx.rpc.AsyncResponder;
  import mx.rpc.AsyncToken;
  import mx.rpc.events.FaultEvent;
  import mx.rpc.events.ResultEvent;
  import mx.rpc.http.HTTPService;
  import mx.rpc.remoting.RemoteObject;

  [Event(name="ready", type="flash.events.Event")]
  [Event(name="attendeeListChangeMessage", type="mx.messaging.events.MessageEvent")]
  [Event(name="whiteboardDrawingMessage", type="mx.messaging.events.MessageEvent")]
  [Event(name="entryRequestMessage", type="mx.messaging.events.MessageEvent")]
  public class Connection extends EventDispatcher
  {
    
    public function initialize():void
    {
  		var httpService:HTTPService = new HTTPService();
  		httpService.url = "config.xml";
  		var asyncToken:AsyncToken = httpService.send();
  		asyncToken.addResponder(new AsyncResponder(function(event:ResultEvent, token:Object=null):void {
  			var messagingChannelSet:ChannelSet = new ChannelSet();
  			
  			var streamingChannel:StreamingAMFChannel = new StreamingAMFChannel();
  			streamingChannel.url = event.result.rootURL + "/messagebroker/streamingamf";
  			
  			messagingChannelSet.addChannel(streamingChannel);
  			
  			var pollingChannel:AMFChannel = new AMFChannel();
  			pollingChannel.pollingEnabled = true;
  			pollingChannel.url = event.result.rootURL + "messagebroker/amfpolling";
  			pollingChannel.pollingInterval = 5000;
  			
  			messagingChannelSet.addChannel(pollingChannel);
  			
  			
  			attendeeListChangeConsumer = new Consumer();
  			attendeeListChangeConsumer.channelSet = messagingChannelSet;
  			attendeeListChangeConsumer.destination = "attendeeListChange";
  			attendeeListChangeConsumer.addEventListener(MessageEvent.MESSAGE, function(event:MessageEvent):void {
  				var e:MessageEvent = new MessageEvent("attendeeListChangeMessage", false, false, event.message);
  				dispatchEvent(e);
  			});
  			attendeeListChangeConsumer.subscribe();
  			
  			whiteboardDrawingConsumer = new Consumer();
  			whiteboardDrawingConsumer.channelSet = messagingChannelSet;
  			whiteboardDrawingConsumer.destination = "whiteboardDrawing";
  			whiteboardDrawingConsumer.addEventListener(MessageEvent.MESSAGE, function(event:MessageEvent):void {
  				var e:MessageEvent = new MessageEvent("whiteboardDrawingMessage", false, false, event.message);
  				dispatchEvent(e);
  			});
  			whiteboardDrawingConsumer.subscribe();
        
        entryRequestConsumer = new Consumer();
        entryRequestConsumer.channelSet = messagingChannelSet;
        entryRequestConsumer.destination = "entryRequest";
        entryRequestConsumer.addEventListener(MessageEvent.MESSAGE, function(event:MessageEvent):void {
          var e:MessageEvent = new MessageEvent("entryRequestMessage", false, false, event.message);
          dispatchEvent(e);
        });
        entryRequestConsumer.subscribe();
  			
  			
  			attendeeListChangeProducer = new Producer();
  			attendeeListChangeProducer.channelSet = messagingChannelSet;
  			attendeeListChangeProducer.destination = "attendeeListChange";
  			
  			whiteboardDrawingProducer = new Producer();
  			whiteboardDrawingProducer.channelSet = messagingChannelSet;
  			whiteboardDrawingProducer.destination = "whiteboardDrawing";
  			
  			
  			whiteboardServiceRemoteObject = new RemoteObject();
  			whiteboardServiceRemoteObject.endpoint = event.result.rootURL + "/messagebroker/amf";
  			whiteboardServiceRemoteObject.destination = "whiteboardService";
  			
        entryRequestServiceRemoteObject = new RemoteObject();
        entryRequestServiceRemoteObject.endpoint = event.result.rootURL + "/messagebroker/amf";
        entryRequestServiceRemoteObject.destination = "entryRequestService";
        
  			dispatchEvent(new Event("ready"));
  			
  		}, function(event:FaultEvent, token:Object=null):void {
  			// do nothing
  			trace('fault');
  		}));
    }
	
    private static var instance:Connection;
    	
	
  	public var attendeeListChangeConsumer:Consumer;
  	public var whiteboardDrawingConsumer:Consumer;
    public var entryRequestConsumer:Consumer;
  	
  	public var attendeeListChangeProducer:Producer;
  	public var whiteboardDrawingProducer:Producer;
      
    public var whiteboardServiceRemoteObject:RemoteObject;
    public var entryRequestServiceRemoteObject:RemoteObject;
      
    public static function getInstance():Connection
    {
      if (instance == null)
  	  {
        instance = new Connection();
  	  	instance.initialize();
  	  }
        
      return instance;
    }
  }
}
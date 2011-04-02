package org.jboss.samples.ordermgr
{
	import mx.collections.IList;
	import mx.messaging.channels.AMFChannel;
	import mx.messaging.channels.PollingChannel;
	import mx.messaging.channels.StreamingAMFChannel;

	[Bindable]
	public class Model
	{
		public static var instance:Model = new Model();
		
		public var allWidgets:IList;
		
		public var fetchingWidgets:Boolean = false;
		public var creatingWidget:Boolean = false;
		
		public var amfChannel:AMFChannel;
		public var streamingAmfChannel:StreamingAMFChannel;
		public var pollingAmfChannel:AMFChannel;
		
		public function Model()
		{
			amfChannel = new AMFChannel("my-amf", "http://localhost:8080/ordermgr/messagebroker/amf");
			
			streamingAmfChannel = new StreamingAMFChannel("my-streaming-amf", "http://localhost:8080/ordermgr/messagebroker/streamingamf");
			
			pollingAmfChannel = new AMFChannel("my-polling-amf", "http://localhost:8080/ordermgr/messagebroker/pollingamf");
			pollingAmfChannel.enablePolling();
			pollingAmfChannel.pollingInterval = 1000;
		}
	}
}
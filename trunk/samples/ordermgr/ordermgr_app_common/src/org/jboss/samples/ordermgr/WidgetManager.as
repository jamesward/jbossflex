package org.jboss.samples.ordermgr
{
	import mx.collections.IList;
	import mx.messaging.ChannelSet;
	import mx.messaging.Consumer;
	import mx.messaging.events.MessageEvent;
	import mx.rpc.AsyncResponder;
	import mx.rpc.AsyncToken;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;

	public class WidgetManager
	{
		public static var instance:WidgetManager = new WidgetManager();
		
		private static var remoteObject:RemoteObject;
		private static var consumer:Consumer;
		
		public function WidgetManager()
		{
			remoteObject = new RemoteObject("widgetService");
			remoteObject.channelSet = new ChannelSet();
			remoteObject.channelSet.addChannel(Model.instance.amfChannel);
			
			consumer = new Consumer();
			consumer.destination = "widgetUpdates";
			consumer.channelSet = new ChannelSet();
			consumer.channelSet.addChannel(Model.instance.streamingAmfChannel);
			consumer.channelSet.addChannel(Model.instance.pollingAmfChannel);
			consumer.addEventListener(MessageEvent.MESSAGE, function(event:MessageEvent):void {
				trace(event.message.body);
			});
		}
		
		public function getAllWidgets():void
		{
			Model.instance.fetchingWidgets = true;
			var asyncResponder:AsyncResponder = new AsyncResponder(function(event:ResultEvent, passThrough:Object):void {
				Model.instance.allWidgets = event.result as IList;
				Model.instance.fetchingWidgets = false;
			}, function(event:FaultEvent, passThrough:Object):void {
				Model.instance.fetchingWidgets = false;
			});
			var asyncToken:AsyncToken = remoteObject.getAllWidgets();
			asyncToken.addResponder(asyncResponder);
		}
		
		public function createWidget(widget:Widget, callback:Function):void
		{
			Model.instance.creatingWidget = true;
			var asyncResponder:AsyncResponder = new AsyncResponder(function(event:ResultEvent, passThrough:Object):void {
				Model.instance.creatingWidget = false;
				getAllWidgets();
			}, function(event:FaultEvent, passThrough:Object):void {
				trace(event);
			}, callback);
			var asyncToken:AsyncToken = remoteObject.createWidget(widget);
			asyncToken.addResponder(asyncResponder);
		}
		
		public function updateWidget(widget:Widget, callback:Function):void
		{
			var asyncResponder:AsyncResponder = new AsyncResponder(function(event:ResultEvent, passThrough:Object):void {
				getAllWidgets();
			}, function(event:FaultEvent, passThrough:Object):void {
				trace(event);
			}, callback);
			var asyncToken:AsyncToken = remoteObject.updateWidget(widget);
			asyncToken.addResponder(asyncResponder);
		}
	}
}
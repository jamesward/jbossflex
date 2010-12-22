package org.jboss.demo.whiteboard
{
	import flash.events.Event;
	
	import mx.messaging.Consumer;

	public class ConnectionHandler extends Connection
	{
		private var connection:Connection;
		
		public function ConnectionHandler()
		{
			connection = Connection.getInstance();
			connection.addEventListener("ready", handleReady);
			handleReady();
		}
		
		private function handleReady(event:Event=null):void
		{
			this.attendeeListChangeConsumer = connection.attendeeListChangeConsumer;
			this.whiteboardDrawingConsumer = connection.whiteboardDrawingConsumer;
      this.entryRequestConsumer = connection.entryRequestConsumer;
      
      this.attendeeListChangeProducer = connection.attendeeListChangeProducer;
			this.whiteboardDrawingProducer = connection.whiteboardDrawingProducer;
			
      this.whiteboardServiceRemoteObject = connection.whiteboardServiceRemoteObject;
      this.entryRequestServiceRemoteObject = connection.entryRequestServiceRemoteObject;
		}
		
		override public function addEventListener(type:String, listener:Function, useCapture:Boolean=false, priority:int=0, useWeakReference:Boolean=false):void
		{
			connection.addEventListener(type, listener, useCapture, priority, useWeakReference);
		}
	}
}
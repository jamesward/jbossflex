package org.jboss.samples.ordermgr
{
	[RemoteClass(alias="org.jboss.samples.ordermgr.WidgetOrder")]
	public class WidgetOrder
	{
		public var id:String;
		
		public var orderDate:Date;
		
		public var quantity:uint;
		
		public var widget:Widget;
	}
}
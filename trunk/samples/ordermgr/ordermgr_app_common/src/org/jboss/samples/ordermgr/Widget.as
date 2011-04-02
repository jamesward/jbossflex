package org.jboss.samples.ordermgr
{
	[RemoteClass(alias="org.jboss.samples.ordermgr.Widget")]
	[Bindable]
	public class Widget
	{
		public var id:String;
		public var name:String;
		public var price:Number;
	}
}
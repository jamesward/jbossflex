package org.jboss.demo.whiteboard
{
  [RemoteClass(alias="org.jboss.demo.whiteboard.DrawDTO")]
  public class DrawDTO
  {
    public function DrawDTO(drawColor:uint=NaN, x1:int=NaN, y1:int=NaN, x2:int=NaN, y2:int=NaN)
    {
      this.drawColor = drawColor;
      this.x1 = x1;
      this.y1 = y1;
      this.x2 = x2;
      this.y2 = y2;
    }
    
    public var drawColor:uint;
    public var x1:int;
    public var y1:int;
    public var x2:int;
    public var y2:int;
  }
}
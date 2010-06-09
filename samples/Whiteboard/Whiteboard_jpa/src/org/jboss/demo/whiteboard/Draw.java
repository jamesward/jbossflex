package org.jboss.demo.whiteboard;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Draw
{
  
  private long id;
  private long drawColor;
  private int x1;
  private int y1;
  private int x2;
  private int y2;
  private Whiteboard whiteboard;
  private int version = 0;
  
  
  @Id
  @GeneratedValue
  public long getId()
  {
    return id;
  }
  public void setId(long id)
  {
    this.id = id;
  }
  
  public long getDrawColor()
  {
    return drawColor;
  }
  public void setDrawColor(long drawColor)
  {
    this.drawColor = drawColor;
  }
  
  public int getX1()
  {
    return x1;
  }
  public void setX1(int x1)
  {
    this.x1 = x1;
  }
  
  public int getY1()
  {
    return y1;
  }
  public void setY1(int y1)
  {
    this.y1 = y1;
  }
  
  public int getX2()
  {
    return x2;
  }
  public void setX2(int x2)
  {
    this.x2 = x2;
  }
  
  public int getY2()
  {
    return y2;
  }
  public void setY2(int y2)
  {
    this.y2 = y2;
  }
  
  @ManyToOne
  public Whiteboard getWhiteboard()
  {
    return whiteboard;
  }
  public void setWhiteboard(Whiteboard whiteboard)
  {
    this.whiteboard = whiteboard;
  }

  public int getVersion() {
	return version;
  }

  public void setVersion(int version) {
	this.version = version;
  }
  
  

}

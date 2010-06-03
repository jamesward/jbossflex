package org.jboss.demo.whiteboard;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;

import flex.messaging.io.amf.ASObject;
import flex.messaging.messages.Message;
import flex.messaging.services.messaging.adapters.ActionScriptAdapter;


public class WhiteboardDrawingMessagingAdapter extends ActionScriptAdapter
{
  //@EJB  - autowiring doesn't work here
  private WhiteboardServiceLocal whiteboardService;
  
  public WhiteboardDrawingMessagingAdapter()
  {
	  super();
	  
	  try
	  {
		  Context ctx = new InitialContext();
		  whiteboardService = (WhiteboardServiceLocal)ctx.lookup("Whiteboard_ear/WhiteboardService/local");
	  }
	  catch (Exception e)
	  {
		  System.out.println("couldn't get whiteboardService");
		  e.printStackTrace();
	  }
  }
  
  @Override
  public Object invoke(Message message)
  {
    super.invoke(message);
    
    String command = (String)((ASObject)message.getBody()).get("command");
    String whiteboardId = (String) message.getHeader("DSSubtopic");
    
    if (command.equals("draw"))
    {
      DrawDTO draw = (DrawDTO)((ASObject)message.getBody()).get("draw");
      
      whiteboardService.drawOnWhiteboard(whiteboardId, draw);
    }
    else if (command.equals("erase"))
    {
      whiteboardService.eraseWhiteboard(whiteboardId);
    }
    
    return null;
  }
  
}
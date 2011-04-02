package org.jboss.samples.ordermgr;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import flex.messaging.MessageBroker;
import flex.messaging.messages.AsyncMessage;
import flex.messaging.util.UUIDUtils;

@Stateless
public class WidgetService
{

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Widget> getAllWidgets()
	{
		List<Widget> widgets = new ArrayList<Widget>();
		
		CriteriaQuery<Widget> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Widget.class);
		criteriaQuery.select(criteriaQuery.from(Widget.class));
		for (Widget widget : entityManager.createQuery(criteriaQuery).getResultList())
		{
			widgets.add(trimWidget(widget));
		}
		
		return widgets;
	}
	
	public boolean createWidget(Widget widget)
	{
		entityManager.persist(widget);
		entityManager.flush();
		
		sendWidgetUpdate(widget);
		
		return true;
	}
	
	public boolean updateWidget(Widget widget)
	{
		entityManager.merge(widget);
		entityManager.flush();
		
		sendWidgetUpdate(widget);
		
		return true;
	}
	
	// filter off the orders so that BlazeDS doesn't try to serialize a lazy association
	private Widget trimWidget(Widget widget)
	{
		entityManager.detach(widget);
		widget.orders = null;
		return widget;
	}
	
	private void sendWidgetUpdate(Widget widget)
	{
		AsyncMessage msg = new AsyncMessage();
		msg.setClientId(UUIDUtils.createUUID(false));
		msg.setMessageId(UUIDUtils.createUUID(false));
		msg.setDestination("widgetUpdates");
		msg.setTimestamp(System.currentTimeMillis());
		msg.setBody(trimWidget(widget));

		MessageBroker mb = MessageBroker.getMessageBroker(null);
		mb.routeMessageToService(msg, null);
	}

}
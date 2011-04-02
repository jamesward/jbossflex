package org.jboss.samples.ordermgr;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import flex.messaging.MessageBroker;
import flex.messaging.messages.AsyncMessage;
import flex.messaging.util.UUIDUtils;

@Stateless
@LocalBean
public class WidgetOrderService {

	@PersistenceContext
	private EntityManager entityManager;

	public boolean createWidgetOrder(WidgetOrder widgetOrder) {
		widgetOrder.orderDate = new Date();
		entityManager.persist(widgetOrder);
		
		entityManager.flush();

		entityManager.detach(widgetOrder);
		widgetOrder.widget.orders = null;
		
		//TODO: switch to JMS
		AsyncMessage msg = new AsyncMessage();
		msg.setClientId(UUIDUtils.createUUID(false));
		msg.setMessageId(UUIDUtils.createUUID(false));
		msg.setDestination("orderUpdates");
		msg.setTimestamp(System.currentTimeMillis());
		msg.setBody(widgetOrder);

		MessageBroker mb = MessageBroker.getMessageBroker(null);
		mb.routeMessageToService(msg, null);

		return true;
	}

	public List<WidgetOrder> getAllWidgetOrders() {
		List<WidgetOrder> widgetOrders = new ArrayList<WidgetOrder>();

		CriteriaQuery<WidgetOrder> criteriaQuery = entityManager
				.getCriteriaBuilder().createQuery(WidgetOrder.class);
		criteriaQuery.select(criteriaQuery.from(WidgetOrder.class));
		for (WidgetOrder widgetOrder : entityManager.createQuery(criteriaQuery)
				.getResultList()) {
			// detach the widgets so BlazeDS doesn't try to serialize a lazy
			// association
			entityManager.detach(widgetOrder);
			widgetOrder.widget.orders = null;
			widgetOrders.add(widgetOrder);
		}

		return widgetOrders;
	}

}
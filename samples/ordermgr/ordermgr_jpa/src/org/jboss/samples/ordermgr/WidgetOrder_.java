package org.jboss.samples.ordermgr;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2011-03-08T16:58:16.311-0700")
@StaticMetamodel(WidgetOrder.class)
public class WidgetOrder_ {
	public static volatile SingularAttribute<WidgetOrder, String> id;
	public static volatile SingularAttribute<WidgetOrder, Date> orderDate;
	public static volatile SingularAttribute<WidgetOrder, Long> quantity;
	public static volatile SingularAttribute<WidgetOrder, Widget> widget;
}

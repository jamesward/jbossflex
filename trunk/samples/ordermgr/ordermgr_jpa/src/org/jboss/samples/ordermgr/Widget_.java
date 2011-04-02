package org.jboss.samples.ordermgr;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2011-03-08T15:58:05.893-0700")
@StaticMetamodel(Widget.class)
public class Widget_ {
	public static volatile SingularAttribute<Widget, String> id;
	public static volatile SingularAttribute<Widget, String> name;
	public static volatile SingularAttribute<Widget, Double> price;
	public static volatile SetAttribute<Widget, WidgetOrder> orders;
}

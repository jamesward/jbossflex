package org.jboss.samples.ordermgr;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.Min;

@Entity
@GenericGenerator(name="system-uuid", strategy = "uuid")
public class WidgetOrder
{
	@Id
	@GeneratedValue(generator="system-uuid")
	public String id;
	
	public Date orderDate;
	
	@Min(1)
	public long quantity;
	
	@ManyToOne(fetch=FetchType.EAGER)
	public Widget widget;
	
}
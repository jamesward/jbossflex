package org.jboss.samples.ordermgr;

import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.Length;
import org.hibernate.validator.Min;
import org.hibernate.validator.NotNull;

@Entity
@GenericGenerator(name="system-uuid", strategy = "uuid")
public class Widget
{
	@Id
	@GeneratedValue(generator="system-uuid")
	public String id;
	
	@NotNull
	@Length(min=1, max=128)
	public String name;
	
	@Min(0)
	public double price;
	
	@OneToMany(mappedBy="widget")
	public Set<WidgetOrder> orders;

}
package com.candidjava.onlineshopping.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="orders_ordered_products")
public class OrderedProducts implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	long orderedProductId;
	
	@ManyToOne
	Orders orders;
	

	public long getOrderedProductId() {
		return orderedProductId;
	}

	public void setOrderedProductId(long orderedProductId) {
		this.orderedProductId = orderedProductId;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	
	
	
	
	
	
}

package com.candidjava.onlineshopping.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="productImages")
public class ProductImages implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	long productImageId; 
	
	@Id
	String  productImagePath;
	
	@ManyToOne
	Product product;
	
	public long getProductImageId() {
		return productImageId;
	}

	public void setProductImageId(long productImageId) {
		this.productImageId = productImageId;
	}

	

}

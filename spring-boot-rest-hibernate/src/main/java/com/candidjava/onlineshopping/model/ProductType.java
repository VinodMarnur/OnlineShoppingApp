package com.candidjava.onlineshopping.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="product_product_type")
public class ProductType implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

			
	
	@Id
	long productTypeId;
	
	String productTypeName;
	
	@OneToMany(mappedBy = "productType", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<Product> products;

	public long getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(long productTypeId) {
		this.productTypeId = productTypeId;
	}

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	
	

	
	
	
	
}

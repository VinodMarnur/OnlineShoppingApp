package com.candidjava.onlineshopping.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cart")
public class Cart implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long cartId;
		
	int cartProductCount;
	
	@ManyToOne
	User user;
	
	@ManyToOne
	Product product;

	public Cart() {
		
	}
	
	public Cart(long cartId, User user, Product product) {
		super();
		this.cartId = cartId;
		this.user = user;
		this.product = product;
	}

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getCartProductCount() {
		return cartProductCount;
	}

	public void setCartProductCount(int cartProductCount) {
		this.cartProductCount = cartProductCount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}

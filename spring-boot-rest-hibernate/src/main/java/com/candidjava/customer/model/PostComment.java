package com.candidjava.customer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity()
@Table(name = "app_post_comment")
public class PostComment {
 
    @Id
    @GeneratedValue
    private Long id;
 
    private String review;
    
    @ManyToOne
    Post post;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}
	
	public PostComment() {
		
	}

	public PostComment(Long id, String review) {
		super();
		this.id = id;
		this.review = review;
	}
	
	
 
    //Constructors, getters and setters removed for brevity
    
    
}

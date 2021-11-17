package com.mph.entity;


import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "AABUYER")
public class Buyer extends Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int buyerId;

	private String wishlist;

	public Buyer() {
		super();
	}


	public Buyer(int buyerId, String wishlist) {
		super();
		this.buyerId = buyerId;
		this.wishlist = wishlist;
	}

	public int getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}

	public String getWishlist() {
		return wishlist;
	}

	public void setWishlist(String wishlist) {
		this.wishlist = wishlist;
	}

}

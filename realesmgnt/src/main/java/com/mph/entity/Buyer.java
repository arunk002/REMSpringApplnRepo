package com.mph.entity;

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

	@Override
	public String toString() {
		return "Buyer [buyerId=" + buyerId + "]";
	}

	public int getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}

	public Buyer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Buyer(int buyerId) {
		super();
		this.buyerId = buyerId;
	}
	
	}

package com.mph.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name = "AAPROPERTY")
public class Property {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int property_Id;
	private String property_Type;
	private long price;
	private boolean status;
	private int contact;
	private String description;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="sellerId",referencedColumnName="SELLERID" )
	private Seller seller;
	
	
	public Property() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Seller getSeller() {
		return seller;
	}


	public void setSeller(Seller seller) {
		this.seller = seller;
	}




	public Property(int property_Id, String property_Type, long price, boolean status, int contact, String description,
			Seller seller) {
		super();
		this.property_Id = property_Id;
		this.property_Type = property_Type;
		this.price = price;
		this.status = status;
		this.contact = contact;
		this.description = description;
		this.seller = seller;
	}


	public int getProperty_Id() {
		return property_Id;
	}


	public void setProperty_Id(int property_Id) {
		this.property_Id = property_Id;
	}


	public String getProperty_Type() {
		return property_Type;
	}


	public void setProperty_Type(String property_Type) {
		this.property_Type = property_Type;
	}


	public long getPrice() {
		return price;
	}


	public void setPrice(long price) {
		this.price = price;
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	public int getContact() {
		return contact;
	}


	public void setContact(int contact) {
		this.contact = contact;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "Property [property_Id=" + property_Id + ", property_Type=" + property_Type + ", price=" + price
				+ ", status=" + status + ", contact=" + contact + ", description=" + description + "";
	}


	
	
	
	
	

}

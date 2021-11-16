package com.mph.service;

import java.util.List;

import com.mph.entity.Buyer;
import com.mph.entity.Seller;

public interface CustomerService {
	//Seller
	public List<Seller> getSellerList();
	public String updateSeller(Seller seller);
	public String deleteSeller(int sellerId);
	public String createSeller(Seller seller);
	public Seller getSellerById(int sellerId);
	//Buyer
	public List<Buyer> getBuyerList();
	public String updateBuyer(Buyer buyer);
	public String deleteBuyer(int buyerId);
	public String createBuyer(Buyer buyer);
	public Buyer getBuyerById(int buyerId);

}
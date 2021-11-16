package com.mph.dao;

import java.util.List;

import com.mph.entity.Buyer;

public interface BuyerDao {
	public List<Buyer> getBuyerList();
	public String updateBuyer(Buyer buyer);
	public String deleteBuyer(int buyerId);
	public String createBuyer(Buyer buyer);
	public Buyer getBuyerById(int buyerId);
}

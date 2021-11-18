package com.mph.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mph.entity.Buyer;
import com.mph.entity.Property;
import com.mph.entity.Seller;

@Repository
public class CustomerDaoImpl implements BuyerDao, SellerDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private PropertyDao propertyDao;
	
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
 
	@SuppressWarnings("unchecked")
	@Override
	public List<Seller> getSellerList() {
		Query query = getSession().createQuery("from Seller");
		List<Seller> sellerList = query.list();
		System.out.println("seller list");
		System.out.println(sellerList);
		return sellerList;
	}

	@Override
	public String updateSeller(Seller seller) {
		System.out.println("from update seler");
		Query query = getSession().createQuery("update Seller set fName=:fnam, lName=:lnam,phoneNumber=:pn, email=:mail, pan=:pan, adhar=:adr, password=:pass where sellerId=:sid");
		query.setParameter("fnam", seller.getfName()).setParameter("lnam", seller.getlName()).setParameter("pn", seller.getPhoneNumber()).setParameter("mail", seller.getEmail());
		query.setParameter("pan", seller.getPan()).setParameter("adr", seller.getAdhar()).setParameter("pass", seller.getPassword()).setParameter("sid", seller.getSellerId());
		int p = query.executeUpdate();
		System.out.println(p + " records updated");
		return seller.getSellerId() + "Updated Succesfully";
	}

	@Override
	public String deleteSeller(int sellerId) {
		Query query = getSession().createQuery("delete from Seller where sellerId=:id").setParameter("id", sellerId);
		int p = query.executeUpdate();
		if(p>0) {
			return "Seller with Id "+ sellerId +" deleted Succesfully";
		}else {
			return "Seller not found";
		}
	}

	@Override
	public String createSeller(Seller seller) {
		getSession().save(seller);
		return "Seller added succesfully";
	}

	@Override
	public Seller getSellerById(int sellerId) {
		Query query = getSession().createQuery("from Seller where sellerid=:sid").setParameter("sid", sellerId);
		Seller seller = (Seller)query.uniqueResult();
		System.out.println(seller);
		return seller;
	}


	@Override
	public List<Buyer> getBuyerList() {
		Query query = getSession().createQuery("from Buyer");
		@SuppressWarnings("unchecked")
		List<Buyer> buyerList = query.list();
		System.out.println("buyer list");
		System.out.println(buyerList);
		return buyerList;
	}


	@Override
	public String updateBuyer(Buyer buyer) {
		Query query = getSession().createQuery("update Buyer set fname=:fnam, lname=:lnam,phoneNumber=:pn, email=:mail, pan=:pan, adhar=:adr, password=:pass, wishlist=:wl where buyerid=:sid");
		query.setParameter("fnam", buyer.getfName()).setParameter("lnam", buyer.getlName()).setParameter("pn", buyer.getPhoneNumber()).setParameter("mail", buyer.getEmail());
		query.setParameter("pan", buyer.getPan()).setParameter("adr", buyer.getAdhar()).setParameter("pass", buyer.getPassword()).setParameter("sid", buyer.getBuyerId());
		query.setParameter("wl", buyer.getWishlist());
		int p = query.executeUpdate();
		System.out.println(p + " records updated");
		return buyer.getBuyerId() + "Updated Succesfully";
	}


	@Override
	public String deleteBuyer(int buyerId) {
		Query query = getSession().createQuery("delete from Buyer where buyerId=:id").setParameter("id", buyerId);
		int p = query.executeUpdate();
		if(p>0) {
			return "Buyer with Id "+ buyerId +" deleted Succesfully";
		}else {
			return "Buyer not found";
		}
	}


	@Override
	public String createBuyer(Buyer buyer) {
		getSession().saveOrUpdate(buyer);
		return "Buyer added succesfully";
	}


	@Override
	public Buyer getBuyerById(int buyerId) {
		Query query = getSession().createQuery("from Buyer where buyerId=:sid").setParameter("sid", buyerId);
		Buyer buyer = (Buyer)query.uniqueResult();
		System.out.println(buyer);
		return buyer;
	}
	
//	-------------------------login---------------------------
	
	
	public Buyer getBuyerByEmailandPassword(String email, String password) {
		Query query = getSession().createQuery("from Buyer where email=:mail and password=:pass").setParameter("mail", email).setParameter("pass", password);
		Buyer buyer = (Buyer) query.uniqueResult();
		return buyer;
	}
	
	public Seller getSellerByEmailandPassword(String email, String password) {
		Query query = getSession().createQuery("from Seller where email=:mail and password=:pass").setParameter("mail", email).setParameter("pass", password);
		Seller seller = (Seller) query.uniqueResult();
		return seller;
	}

	@Override
	public List<Property> reomveFromWishlist(int buyerId,int propertyId) {
		Buyer buyer = getBuyerById(buyerId);
		String wishstr = buyer.getWishlist();
		List<String> wishList = Arrays.asList(wishstr.split(","));
		Set<String> myset = wishList.stream().collect(Collectors.toSet());
		myset.remove(propertyId+"");
		String finalStr = "" ;
		for(String fstr : myset) {
			finalStr = finalStr+fstr+",";
		}
		buyer.setWishlist(finalStr);
		updateBuyer(buyer);
		return propertyDao.getWishListByBuyerId(buyerId);

	}

	@Override
	public List<Property> addFromWishlist(int buyerId, int propertyId) {
		Buyer buyer = getBuyerById(buyerId);
		String wishstr = buyer.getWishlist();
		List<String> wishList = Arrays.asList(wishstr.split(","));
		Set<String> myset = wishList.stream().collect(Collectors.toSet());
		myset.add(propertyId+"");
		String finalStr = "" ;
		for(String fstr : myset) {
			finalStr = finalStr+fstr+",";
		}
		buyer.setWishlist(finalStr);
		updateBuyer(buyer);
		return propertyDao.getWishListByBuyerId(buyerId);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

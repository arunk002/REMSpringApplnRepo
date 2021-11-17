package com.mph.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mph.entity.PurchaseOrder;

@Repository
public class PurchaseOrderDaoImpl implements PurchaseOrderDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public List<PurchaseOrder> getPurchaseOrderList() {
		Query query = getSession().createQuery("from PurchaseOrder");
		@SuppressWarnings("unchecked")
		List<PurchaseOrder> PurchaseOrderList = query.list();
		System.out.println("PurchaseOrder list");
		System.out.println(PurchaseOrderList);
		return PurchaseOrderList;
	}

	@Override
	public String updatePurchaseOrder(PurchaseOrder purchaseorder) {
		Query query = getSession().createQuery("update PurchaseOrder set maxAcceptedPrice=:maxAcceptedPric, date=:dat, property=:propert");
		query.setParameter("maxAcceptedPric", purchaseorder.getMaxAcceptedPrice()).setParameter("dat", purchaseorder.getDate()).setParameter("propert", purchaseorder.getProperty());
		
		int p = query.executeUpdate();
		System.out.println(p + " records updated");
		return purchaseorder.getPurhcaseId() + "Updated Succesfully";
	}

	@Override
	public String deletePurchaseOrder(int purchaseId) {
		Query query = getSession().createQuery("delete from PurchaseOrder where purchaseId=:id").setParameter("id", purchaseId);
		int p = query.executeUpdate();
		if(p>0) {
			return "PurchaseOrder with Id "+ purchaseId +" deleted Succesfully";
		}else {
			return "PurchaseOrder not found";
		}
	}


	@Override
	public String createPurchaseOrder(PurchaseOrder purchaseorder) {
		purchaseorder.setDate(new java.util.Date(System.currentTimeMillis()));
		getSession().save(purchaseorder);
		return "PurchaseOrder added succesfully";
	}

	@Override
	public PurchaseOrder getPurchaseOrderById(int purchaseId) {
		Query query = getSession().createQuery("from PurchaseOrder where purchaseid=:poid").setParameter("poid", purchaseId);
		PurchaseOrder purchaseorder = (PurchaseOrder)query.uniqueResult();
		System.out.println(purchaseorder);
		return purchaseorder;
	}

}
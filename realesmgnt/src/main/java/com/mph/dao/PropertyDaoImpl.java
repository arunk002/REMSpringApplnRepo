package com.mph.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mph.entity.Buyer;
import com.mph.entity.Property;

@Repository
public class PropertyDaoImpl implements PropertyDao {
	Property property;
	@Autowired
	private SessionFactory sessionFactory;
	
	protected Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}
	@Override
	public String addProperty(Property property) {
		
		getSession().save(property);
		return "Property added succesfully";
	}

	@Override
	public List<Property> getAllProperty() {
		Query qry = getSession().createQuery("from Property");
		@SuppressWarnings("unchecked")
		List<Property> proplist = qry.list();
		System.out.println(proplist);
		return proplist;
	}

	@Override
	public Property getPropertyById(int property_Id) {
		Query query = getSession().createQuery("from Property where property_Id=:sid").setParameter("sid", property_Id);
		Property prop = (Property)query.uniqueResult();
		System.out.println(prop);
		return prop;
	}

	@Override
	public String updateProperty(Property property) {
		Query query =getSession().createQuery("update Property set property_Type=:type,price=:lpri,status=:sta,contact=:cont,description=:descp where property_Id=:num");
		
		query.setParameter("type", property.getProperty_Type()).setParameter("lpri",property.getPrice()).setParameter("sta",property.isStatus()).setParameter("cont",property.getContact());
		query.setParameter("descp",property.getDescription()).setParameter("num", property.getProperty_Id());
		int p = query.executeUpdate();
		System.out.println(p + "records Updated.....:)");
		return property.getProperty_Id()+ "Updated Succesfully";
		
	}

	@Override
	public String deleteProperty(int property_Id) {
		Query query = getSession().createQuery("delete from Property where property_Id=:id").setParameter("id", property_Id);
		int p = query.executeUpdate();
		if(p>0) {
			return "Property with Id "+ property_Id +" deleted Succesfully";
		}else {
			return "Property not found";
		}
		
	}
	

}

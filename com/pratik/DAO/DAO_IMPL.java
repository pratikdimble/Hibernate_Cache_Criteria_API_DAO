package com.pratik.DAO;

import java.util.List;

import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.Session;

import com.pratik.domain.product;
import com.pratik.utility.HibernateUtil;

public class DAO_IMPL implements DAO_Interface {

@Override
public void list() {
	Session ses=null;
	//get the session
	ses=HibernateUtil.getSession();
	//create criteria
	Criteria c=ses.createCriteria(product.class);
	c.setCacheable(true);
	c.setCacheMode(CacheMode.NORMAL);
	//execute the Criteria
	List<product> list1=c.list();
	System.out.println("\n\t***gets from Database software and keeps in cache***\n");
	//display the list
	list1.forEach(row->{
		System.out.println(row);
	});
			
	//execute the Criteria
	List<product> list2=c.list();
	System.out.println("\n\t***gets from cache***\n");
	//display the list
	list2.forEach(row->{
		System.out.println(row);
	});
	//remove from cache
	ses.clear();
	System.out.println("\n\t***Removing from cache...Wait-->>>***\n");	
	try {
		Thread.sleep(5000);
	}catch (Exception e) {
		e.printStackTrace();
	}
	//execute the Criteria
	List<product> list3=c.list();
	System.out.println("\n\t***Again gets from Database software and keeps in cache***\n");
	//display the list
	list3.forEach(row->{
		System.out.println(row);
	});
	
	//close the session
	HibernateUtil.closeSession(ses);
	
	
	//return list;
}
	
	
}//class close

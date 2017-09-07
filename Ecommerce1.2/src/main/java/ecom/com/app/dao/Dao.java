package ecom.com.app.dao;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;



import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import ecom.com.app.LoginPerson;
import ecom.com.app.Response;
import ecom.com.app.model.Dept;
import ecom.com.app.model.OrderDetail;
import ecom.com.app.model.Person;
import ecom.com.app.model.Photo;
import ecom.com.app.model.Product;
import ecom.com.app.model.Store;

@Repository
@Transactional
public class Dao {
	

	@Autowired
	@Qualifier(value = "hibernate4AnnotatedSessionFactoryLocal")
	private SessionFactory sf;

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	public Response createPerson(Person person,Map mapValues,Response response) 
	{
		Session s = sf.getCurrentSession();
		Criteria cr;
		cr = s.createCriteria(Person.class);
		cr.add(Restrictions.eq("emailId",person.getEmailId()));
		List Results = cr.list();
		if (Results.isEmpty())
		{
			s.merge(person);
			response.getResultObjects().add("success");
		}
		else
		{
			response.getResultObjects().add("Email Exists.Please try with different email");
		}
	
	
		return response;
		
	}
	
	public Response createStore(Person person,Store store,Response response)
	{
		Session s = sf.getCurrentSession();
	    Person p = getPersonByEmail(person.getEmailId());
	    p.setStore(store);
		s.merge(p);
		response.getResultObjects().add("success");
	
        return response;
		
	}
	
	public Person getPersonByEmail(String emailId) 
	{
		Session s = sf.getCurrentSession();
		Criteria cr;
		cr = s.createCriteria(Person.class);
		cr.add(Restrictions.eq("emailId",emailId));
		List Results = cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	    Person p = (Person) Results.get(0);
    	return p;
	}
	

	
	public Response createDept(Person person,Set<Dept> depList,Response response)
	{
		Session s = sf.getCurrentSession();
		Person p = getPersonByEmail(person.getEmailId());
	    Store store = p.getStore();
		store.setDepts(depList);
        s.merge(store);
		response.getResultObjects().add("success");
	
        return response;
		
	}
	
	public Response uploadPhoto(Photo photo,Response response)
	{
		Session s = sf.getCurrentSession();
        s.merge(photo);
		response.getResultObjects().add("success");
	    return response;
		
	}
	public Response getPhoto(String photoId,Response response)
	{
		Session s = sf.getCurrentSession();
        Photo p = (Photo) s.get(Photo.class,photoId);
		//response.getResultObjects().add("success");
        response.getResultObjects().add(p);
	    return response;
		
	}
	
	public Response storeSearch(String stateName,Response response)
	{
		
		Session s = sf.getCurrentSession();
		Criteria cr = s.createCriteria(Store.class);
		//cr.add(Restrictions.ilike("state", ));
		cr.add(Restrictions.ilike("state", "%" + stateName + "%", MatchMode.ANYWHERE));
		List<Store> results = cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		if(results.isEmpty())
		{ 
		    response.getResultObjects().add("none");
		}
		else
		{
			response.getResultObjects().add(results);
		}
       
	    return response;
		
	}
	
	public Response displayStore(String storeId,Response response)
	{
		Session s = sf.getCurrentSession();
		Store store = (Store) s.get(Store.class, storeId);
	    response.getResultObjects().add(store);	
		return response;
	}
	public List<Product> getProducts(Person person,Response response)
	{
		Session s = sf.getCurrentSession();
		Person p  =  getPersonByEmail(person.getEmailId()); 
		Person p1 = (Person) s.get(Person.class, p.getPersonId());
		List<Product> prodList = new ArrayList<Product>();
		for (Dept dept : p1.getStore().getDepts()) {
			
			prodList.addAll(dept.getProducts());
			
		}
		return prodList;
	}
	
	public Response addPhotos(List<Photo> list) throws SecurityException
	{
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		for ( int i=0; i<list.size(); i++ ) {
		         
		        session.save(list.get(i));
		        session.flush();
		        session.clear();
		    }
        tx.commit();
		session.close();
        Response r =new Response();
		return r;
		
	}
	
	public Response login(Person person,Response resp) 
	{
        Session s = sf.getCurrentSession();
        Criteria cr = s.createCriteria(Person.class);
        Criterion check= Restrictions.and(Restrictions.eq("emailId",person.getEmailId()), 
                Restrictions.eq("password",person.getPassword()));
        cr.add(check);
        List Results = cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();;
        if(Results.size() == 1)
        {
        	resp.getResultObjects().add("success");
        	resp.getResultObjects().add(Results.get(0));
       		return resp;
        }
        else
        {
        	resp.getErrorResults().add("Invalid Credentials");
        	return resp;
        }
	}
	
	
     public List<Product> placeOrder(List cartItems,String emailId)
     {
    	 List<Product> orderedItems = new ArrayList<Product>();
    	 Set<Product> itemsToAdd = new HashSet<Product>();
    	 Set<OrderDetail> orderSet = new HashSet<OrderDetail>();
    	 OrderDetail o = new OrderDetail();
    	 Session s = sf.getCurrentSession();
    	 
    	for (Object object : cartItems) {
    		Product p = (Product) s.get(Product.class,object.toString());
    		orderedItems.add(p);
    		itemsToAdd.add(p);
		}
        o.setProducts(itemsToAdd);
        orderSet.add(o);
        
    	
    	
    	
    	Person p = getPersonByEmail(emailId);
    	p.setOrderdetails(orderSet);
    	s.merge(p); 
    	return orderedItems;
     }
     
  public Set<OrderDetail> getOrders(String personId)
  {
	  Session s = sf.getCurrentSession();
	  Person p1 = (Person) s.get(Person.class, personId);
	  System.out.println(p1.getOrderdetails().size());
	  return  p1.getOrderdetails();
	  
  }
	

}

package ecom.com.app.service;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.web.bind.annotation.PathVariable;

import ecom.com.app.LoginPerson;
import ecom.com.app.Response;
import ecom.com.app.dao.Dao;
import ecom.com.app.model.Dept;
import ecom.com.app.model.OrderDetail;
import ecom.com.app.model.Person;
import ecom.com.app.model.Photo;
import ecom.com.app.model.Product;
import ecom.com.app.model.Store;


public class Service {
	
	private Dao dao;

	public void setDao(Dao dao) {
		this.dao = dao;
	}
	public Response createPerson(Person person,Map mapValues,Response response) throws JsonGenerationException, JsonMappingException, IOException
	{
		
		 Response r = this.dao.createPerson(person, mapValues, response);
		 if(r.getResultObjects().contains("success"))
		 {
			return r; 
		 }
		 else if(r.resultObjects.size() == 1)
		 { }
		 else
		 {
			 r.getErrorResults().add("Failed to create Person");
		 }
		return r;
			}
		
	public Response createStore(Person person,Store store,Response response)
	{
		 Response r = this.dao.createStore(person,store,response);
			 if (r.getResultObjects().contains("success"))
		 {
	     }
		 else 
		 {
			 r.getErrorResults().add("Failed to create Store");
		 }
			return response;
		
	}
	
	public List<String> returnIndex (int depIndex, List<String>prodList)
	{
		List<Integer> results = new ArrayList<Integer>();
		List<String> prodNames = new ArrayList<String>();
		int k = 0;
		
	
		for (int i = 0; i < prodList.size(); i++) {
		    if (prodList.get(i).equals("1")) {
		        results.add(i);
		    }
		}
		
		results.add(prodList.size());
		Map<Integer,Integer> mapper = new HashMap<Integer,Integer>(); 
	
		for(Integer oc:results)
		{
		mapper.put( k,oc);
		k = k+1;
		}
		
	   int prodRange = mapper.get(depIndex+1) - mapper.get(depIndex) - 1;
	   int begin = mapper.get(depIndex);
	   for (int j = 0; j < prodRange; j++) {
		   prodNames.add(prodList.get((begin) + 1));
		   begin = begin + 1;
		   }
		return prodNames;
		
	}
	
	
	public Response createDept(Map<String, String[]> container,Response response,Person person)
	{
		Set<Dept> dFactory = new HashSet<Dept>();
		Set<Product> prodSet = null;
		String[] prodNamesList = container.get("prodName");
		String[] prodPriceList = container.get("prodPrice");
		int depIndex = 0;
		
	  for (String string :  container.get("dept")) {
		   Dept d = new Dept();
		  d.setDeptName(string);
		   List<String> prodNames = returnIndex(depIndex,Arrays.asList(prodNamesList));
		   List<String> prodPrice = returnIndex(depIndex,Arrays.asList(prodPriceList));
		   depIndex = depIndex + 1;
		   prodSet = new HashSet<Product>();
		   
		   if (prodNames.size() == prodPrice.size())
		   {
			   for(int i = 0; i < prodNames.size();i++)
			   {
				   Product p = new Product();
				   p.setProductName(prodNames.get(i));
				   p.setProductPrice(prodPrice.get(i));
				   prodSet.add(p);
		    }
			   }
		   else
		   {
			   response.getErrorResults().add("There was an error in assosciating prices to products");
			   return response;
		   }
		   d.setProducts(prodSet);
		   dFactory.add(d);
		  
	}
	  Response r = this.dao.createDept(person,dFactory,response);
	 	return r;
	}
	
	public Response uploadPhoto(Photo photo,Response response)
	{
		    this.dao.uploadPhoto(photo, response);
			return response;
		
	}
	
	public Response getPhoto(String photoId,Response response)
	{
	        Response r = this.dao.getPhoto(photoId, response);
			return r;
	}

	
	
	public Response storeSearch(String stateName,Response response)
	{
		List<Store> obj = new ArrayList<Store>();
		Response r = this.dao.storeSearch(stateName, response);
		if(r.getResultObjects().contains("none"))
		{
		   r.getResultObjects().add("No stores found in "+ stateName) ;	
		}
		else if (r.getResultObjects().contains(obj))
		{
	
		}
		else
		{
			r.getErrorResults().add("Error during store search.Please try again later");
			
		}
		
		return r;
	}
	
	public Response displayStore(String storeId,Response response)
	{
	   	Response r = this.dao.displayStore(storeId,response);
		return response;
	}
	
	public List<Product> getProducts(Person person,Response response)
	{
		List<Product> listProducts = this.dao.getProducts(person, response);
		return listProducts;
	}
	
	public Response login(LoginPerson person,Response resp) 
	{
		Person loginPerson = new Person();
		loginPerson.setEmailId(person.getEmailId());
		loginPerson.setPassword(person.getPassword());
		
		Response r = this.dao.login(loginPerson, resp);
		return r;
	}
	
	
	
	
	 public Response addPhotos(List<Photo> list)
	 {
		 Response r = null;
		try {
			
				r = this.dao.addPhotos(list);
			}
		catch (Exception e) {
				
				e.printStackTrace();
			}
	
		 return r;
	 }
	 
	 public List<Product> placeOrder(List cartItems,String emailId)
	 {
		 List<Product> orderedItems = this.dao.placeOrder(cartItems,emailId);
		 return orderedItems;
		 
	 }
	 
		public Person getPersonByEmail(String emailId) 
		{
			return this.dao.getPersonByEmail(emailId);
			
		}
		
		public Set<OrderDetail> getOrders(String emailId)
		{
			
			Person p = getPersonByEmail(emailId);
			return this.dao.getOrders(p.getPersonId());
		}
	
}
	
		

	
		
	
	
	


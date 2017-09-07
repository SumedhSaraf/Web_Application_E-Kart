package ecom.com.app;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.taglibs.standard.tag.common.xml.ForEachTag;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ecom.com.app.model.Dept;
import ecom.com.app.model.OrderDetail;
import ecom.com.app.model.Person;
import ecom.com.app.model.Photo;
import ecom.com.app.model.Product;
import ecom.com.app.model.Store;
import ecom.com.app.service.Service;
import ecom.com.app.utility.EcommerceTools;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/person")
@SessionAttributes({"registerPerson","cart"})
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired(required = true)
	@Qualifier(value = "Service")
	private Service service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@ModelAttribute("loginPerson")
	public LoginPerson initializelogin()
	{	
	    return new LoginPerson(); 
	}
	
	@ModelAttribute("storeSearchInput")
	public StoreSearchInput initializeinput()
	{	
	    return new StoreSearchInput(); 
	}
	@ModelAttribute("registerPerson")
	public Person initializeregister()
	{	
	    return new Person(); 
	}
	

	
/*	@ModelAttribute("cart")
	public List<String> initializeCart()
	{
		return new ArrayList<String>();
	}
*/
	
	@RequestMapping(value = "/login")
	public String createPersonlogin(@Valid @ModelAttribute("loginPerson") LoginPerson loginPerson,@ModelAttribute("registerPerson") Person per,
			BindingResult result1,Model model,HttpServletRequest req) throws JsonGenerationException, JsonMappingException, IOException  {


		if(result1.hasErrors())
		{
			 model.addAttribute("checkLogin",0);
			 return "homepage";
		}

	   Response resp = new Response();
	   Response response = this.service.login(loginPerson, resp);
	   if(response.getErrorResults().size() == 0)
	   {
		  
		  model.addAttribute("loginStatus",1);
		  per = (Person) response.getResultObjects().get(1);
		 per.setPhoneNo(null);
		  logger.info("Login Successfull- user is  " + per.getEmailId());
		//Person p1 = this.service.getPersonByEmail(p.getEmailId());   
		    // HttpSession session = req.getSession();
		     //session.setAttribute("registerPerson", per);
		  model.addAttribute("registerPerson", per);
		     
		  
		    return "homepage";  
	   }
	   else
	   {
		   model.addAttribute("loginStatus",0);
		   model.addAttribute("checkLogin",0);
		   model.addAttribute("message",response.getErrorResults().get(0).toString());
		   return "homepage";
	   }
	}
	   
	   @RequestMapping(value = "/addToCart/{productId}")
	   @ResponseBody
		public String addToCart(@PathVariable String productId,HttpServletRequest req,Model model,HttpSession session)  {
		 String result = null; 
		Person p = (Person) req.getSession().getAttribute("registerPerson");
		if(p.getEmailId() == null)
		{
			result = "you have to login to add an item to cart";
		   model.addAttribute("result", result);
			return result;
		}
	    else if(p.getRole().equals("admin"))
	   {
		   result = "You are an Admin.Only a user can add item to cart";
		   model.addAttribute("result", result);
		   return result;
	   }
	   else if(p.getRole().equals("user"))
		   
	   {
		   List cartItems  = (List) req.getSession().getAttribute("cart");
		   if (cartItems == null)
		   {
			   cartItems = new ArrayList<String>();
		   }
		   cartItems.add(productId);
		   session.setAttribute("cart", cartItems);
		   System.out.println(cartItems.size());
		   result = "Added";
		   model.addAttribute("result", result);
		   return result;
	   }
		return result;
		
	   
	   
	   }
	   
	   @RequestMapping(value = "/placeOrder")
		public String placeOrder(HttpServletRequest req,Model model) {
		   
		   List CartItems =  (List) req.getSession().getAttribute("cart");
		   if (CartItems == null || CartItems.size()==0)
		   {
			   model.addAttribute("message","There are no items in your cart");
		   }
		   
		 //  System.out.println(p.getPersonId());
		   Person person = (Person) req.getSession().getAttribute("registerPerson");
		   
		   List<Product> orderedItems = this.service.placeOrder(CartItems, person.getEmailId());
		   model.addAttribute("products", orderedItems);
		   
		   return "checkout";
	   }
		
	/*	@RequestMapping(value = "/getOrders/{emailId}")
		public String getOrders(@PathVariable String emailId,Model model)
		{  
			Set<OrderDetail> orders = this.service.getOrders(emailId);
			model.addAttribute("orders",orders);
			return "myorders";
			
		}*/
			
/*	   @RequestMapping(value = "/logout")
		public String logout(Model model,HttpSession session,HttpServletRequest req) {
		 
		   
		 Enumeration e = session.getAttributeNames();
		while(e.hasMoreElements())
		{
			e.nextElement().toString();
			session.setAttribute(e.nextElement().toString(), null);
		}
		   
		   session.invalidate();
		   
		   HttpSession existingSession = req.getSession(false);
		   if (existingSession != null && existingSession.getAttribute("registerPerson") != null){
			   existingSession.removeAttribute("registerPerson");
		   }
		   
		   
		   
		   
		   
		   
	
		   model.addAttribute("logoutmessage", "You Have Logged Out");
		 
	       
		
		   return "homepage";
	   }
	*/
		
	
}

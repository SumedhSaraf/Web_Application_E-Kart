package ecom.com.app;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
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


/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes({"registerPerson"})
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
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
	
	@ModelAttribute("registerPerson")
	public Person initializePerson()
	{
		return new Person();
	}
	
	@ModelAttribute("registerStore")
	public Store initializeStore()
	{	
	    return new Store(); 
	}
	
	@ModelAttribute("storeSearchInput") 
	public StoreSearchInput initializeInput()
	{	
	    return new StoreSearchInput(); 
	}
	
	@ModelAttribute("uploadPic")
	public Photo initializepic()
	{	
	    return new Photo(); 
	}
	
	@ModelAttribute("loginPerson")
	public Person initializeLoginc()
	{	
	    return new Person(); 
	}
	


	
	@RequestMapping(value = "/homepage", method = RequestMethod.GET)
	public String homepage(@ModelAttribute("storeSearchInput") StoreSearchInput storeSearchInput,
			@ModelAttribute("loginPerson") Person loginPerson,@ModelAttribute("registerPerson") Person registerPerson
			,Model model) {
	    
		model.addAttribute("storeSearchInput",storeSearchInput);
		model.addAttribute("loginPerson", loginPerson);
		model.addAttribute("registerPerson",registerPerson);

		return "homepage";
	}
	
	@RequestMapping(value = "/createPerson")
	public String createPerson(/*@ModelAttribute("registerPerson") Person person,*/@Valid @ModelAttribute("registerPerson") Person registerPerson,
			BindingResult result,Model model,RedirectAttributes redirectAttrs,HttpSession session) throws JsonGenerationException, JsonMappingException, IOException  {
		if(result.hasErrors())
		{
			   model.addAttribute("check",0);
			   return "homepage";
		}
		 model.addAttribute("sameemail",0);
		model.addAttribute("check",1);
		//model.addAttribute("formPerson",registerPerson);
		Response response = new Response();
		Map<String,Object> mapValues = new HashMap<String,Object>();
		Response resp = this.service.createPerson(registerPerson, mapValues, response);
		if(resp.getErrorResults().isEmpty() && resp.getResultObjects().contains("success") )
		{
			model.addAttribute("respmessage", "Your account has been created");
			Person p = this.service.getPersonByEmail(registerPerson.getEmailId());
			session.setAttribute("registerPerson", p);
			if(registerPerson.getRole().equals("user"))
			{
				
				return "homepage";
			}
			
			if(registerPerson.getRole().equals("admin"))
			{
				//redirectAttrs.addFlashAttribute("respmessage", "Your account has been created");
				//return "redirect:createStore";
				return "storesetup";
			}
		}
		else
		{
			String respmessage = null;
			if (resp.getResultObjects().size() == 1)
			{
				respmessage = resp.getResultObjects().get(0).toString();
			}
			else if (!(null == resp.getErrorResults()) && resp.getErrorResults().size() == 1)
			{
				respmessage = resp.getErrorResults().get(0).toString();
			}
			model.addAttribute("respmessage", respmessage);
			model.addAttribute("check",0);
			model.addAttribute("sameemail",1);
			return "homepage";
		}
	
		return "error";
	}
	
	@RequestMapping(value = "/createStore")
	public String createStore(/*@ModelAttribute("registerStore") Store storeInit,*/@Valid @ModelAttribute("registerStore") Store store,
			BindingResult result,Model model,HttpServletRequest req) {
		if(result.hasErrors())
		{
			   return "storesetup";
		}
		
		else 
		{
			Response response = new Response();
		    Person p =  (Person) req.getSession().getAttribute("registerPerson");
		    Response r = this.service.createStore(p, store, response);
		    if ( !(null == r.getErrorResults()) && r.getErrorResults().size() == 1)
		    {
		    	model.addAttribute("errormessage",r.getErrorResults().get(0).toString());
		    	return "error";
		    }
		    
			return "deptsetup";
		}
		
	
	
      // System.out.println(store.getDepFactory().get(0).getDeptName());
		//Person p = new Person();
		//Map<String,Object> mapValues = new HashMap<String,Object>();
		//Response resp = this.service.createStore(p, mapValues, response);
		
		//return "storesetup";
	}
	

	@RequestMapping(value = "/createDept")
	public String createDept(HttpServletRequest req,Model model,RedirectAttributes redirectAttrs) {
		
		String[] prodName = null;
		Map<String,String[]> container = new HashMap<String, String[]>();
		Response response = new Response();
		Person person = (Person) req.getSession().getAttribute("registerPerson");
		
		if (!(null == req.getParameterValues("dept")))
				{
		String[] dept = req.getParameterValues("dept");
		container.put("dept", dept);
				}
	
		
		if (!(null == req.getParameterValues("prodname")))
				{
			prodName = req.getParameterValues("prodname");
		    container.put("prodName", prodName);
				}
		
		if (!(null == req.getParameterValues("price")))
				{
			String[] prodPrice = req.getParameterValues("price");
			container.put("prodPrice", prodPrice);
				}

		Response r = this.service.createDept(container, response,person);
		Response r1 = new Response();
		List<Product> prodNames = this.service.getProducts(person, r1);
		System.out.println("prodNames"+prodNames.size());
		redirectAttrs.addFlashAttribute("prodNames",prodNames);  
         return "redirect:upload";
						
	}
	
	@RequestMapping(value = "/upload")
	public String uploadSample1(@ModelAttribute("prodNames") List<Product> prodNames,Model model) throws IOException {
	  System.out.println("entered upload");
	  model.addAttribute("prodNames",prodNames);
	  System.out.println(model.containsAttribute("prodNames"));
		return "addimages";
	}
	
	@RequestMapping(value = "/uploadPhoto",method = RequestMethod.POST)
	public String uploadSample(@RequestParam("prodName") String[] name,
			@RequestParam("file") MultipartFile[] files, Model model) throws IOException {
		System.out.println("entered post method");
		List<Photo> container = new ArrayList<Photo>();
		System.out.println(name.length);
		int i = 0;
		for (MultipartFile multipartFile : files) {
		    Photo p = new Photo();
			p.setPhotoBytes(multipartFile.getBytes());
			p.setPhotoContent(multipartFile.getContentType());
			p.setPhotoId(name[i]);
			System.out.println(multipartFile.getOriginalFilename());
			System.out.println(name[i]);
			i = i +1;
			container.add(p);
		}
		
		Response r = this.service.addPhotos(container);
		return "test";
	

	}
		
	
	
		
		//model.addAttribute("uploadPhoto", photo);
		//System.out.println(photo.getId());
/*		if(photo.getId() == null)
		{
			return "upload";
		}
		else
		{
		Response r  = new Response();
		Response r1 = this.service.uploadPhoto(photo, r); 
		return "upload";
		}*/
		
		//System.out.println(photo.getId());
 
	
	
	@RequestMapping(value = "/uploadPhoto",method = RequestMethod.GET)
	public String uploadPhoto1(//@ModelAttribute("uploadPic") Photo photo,
	//BindingResult result,Model model)
			)
	{
      return "upload";
		}
	
	@RequestMapping(value="/getPhoto/{photoId}")
	public @ResponseBody String viewPhoto(@PathVariable String photoId, HttpServletResponse response) throws IOException {
		Response r = new Response();
		Response resp = this.service.getPhoto(photoId,r);
		Photo p = (Photo) resp.getResultObjects().get(0);
		byte[] photoBytes = p.getPhotoBytes();
		if (photoBytes != null) {
			int photoLength = photoBytes.length;
			try  
			{
				ServletOutputStream sos = response.getOutputStream();
				
				response.setContentType(p.getPhotoContent());
				response.setContentLength(photoLength);
				response.setHeader("Content-Disposition", "inline; filename=\"" + p.getPhotoId() + "\"");
				
				sos.write(photoBytes);
				sos.flush();
			
		
			
		}
			catch(Exception e)
			{
				
			}
	
		}
		return "";
	}
		
		
		
	@RequestMapping(value = "/storeSearch", method = RequestMethod.POST)
	public String storeSearch(@Valid @ModelAttribute("storeSearchInput") StoreSearchInput input,
			BindingResult result,Model model) {
		
		  if (result.hasErrors())
		  {
			  return "homepage";
		  }
		if(input.getStateName() !=null)
		{
		Response response = new Response();
		Response r = this.service.storeSearch(input.getStateName(), response);
		if(r.getResultObjects().contains("none"))
		{
			model.addAttribute("searchResult", r.getResultObjects().get(0));
			 return "homepage";
		}
		else if(r.getResultObjects().size() == 1)
		{
			model.addAttribute("searchResult",r.getResultObjects().get(0));
			System.out.println("positive");
			//System.out.println(r.getResultObjects().get(0));
			return "stores";
		}
		else
		{
			model.addAttribute("searchResult",r.getErrorResults().get(0));
			return "homepage";
		}
		}
		else
		{
			return "homepage";
		}
	
	}
	
	@RequestMapping(value="displayStore/{storeId}")
	public String displayStore(@PathVariable String storeId,Model model)
	{
		Response r = new Response(); 
		Response resp= this.service.displayStore(storeId, r);
		Store s = (Store) resp.getResultObjects().get(0);
        model.addAttribute("depList",s.getDepts());
	    return "storedisplay";
		
	}
	
	@RequestMapping(value = "/getOrders/{emailId}")
	public String getOrders(@PathVariable String emailId,Model model)
	{  
		Set<OrderDetail> orders = this.service.getOrders(emailId);
		for (OrderDetail orderDetail : orders) {
			System.out.println(orderDetail.getOrderId());
			System.out.println(orderDetail.getProducts().size());
		}
		model.addAttribute("orders",orders);
		return "myorders";
		
	}
	
/*	@RequestMapping(value = "/login")
	public String loginPerson(@Valid @ModelAttribute("loginPerson") Person loginPerson,
			BindingResult result,Model model,RedirectAttributes redirectAttrs) throws JsonGenerationException, JsonMappingException, IOException  {
		if(result.hasErrors())
		{
			   //model.addAttribute("check",0);
			   return "homepage";
		}

		System.out.println(loginPerson.getEmailId());
		System.out.println(loginPerson.getPassword());
	
		return "error";
	}
	*/
	
	 /* @RequestMapping(value = "/logout")
			public String logout(Model model,HttpSession session,HttpServletRequest req,HttpServletResponse response) throws IOException, ServletException {
			 
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
		   
		   
		   
		  if(existingSession != null)
		  {
			  session.invalidate();
		  }
		   
	
		   model.addAttribute("logoutmessage", "You Have Logged out");
			
			   return "homepage";
	  }
	  */
	
}

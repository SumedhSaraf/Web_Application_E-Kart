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
@RequestMapping(value = "/logout")
public class LogOutController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired(required = true)
	@Qualifier(value = "Service")
	private Service service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest req, Model model,RedirectAttributes red) {
		
		  HttpSession existingSession = req.getSession(false);
		   if (existingSession != null && existingSession.getAttribute("registerPerson") != null){
			   existingSession.removeAttribute("registerPerson");
		   }
		   if (existingSession != null && existingSession.getAttribute("cart") != null){
			   existingSession.removeAttribute("cart");
		   }
		   
		    if(existingSession != null)
		  {
		    	existingSession.invalidate();
		  }
		   red.addFlashAttribute("logoutmessage", "You Have Logged out");
           return "redirect:/homepage";
	}
	
}

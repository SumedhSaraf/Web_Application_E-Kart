package ecom.com.app.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Entity(name = "Person")
@Table(name = "person")
//@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
public class Person {
	
	
	@Id
	@GenericGenerator(parameters=@Parameter(name="sequence" , value="customerSequence"), strategy="ecom.com.app.idgenerator.IdGenerator", name = "customerSequence")
	@GeneratedValue(generator = "customerSequence")
	@Column(name = "id")
	private String personId;
	
	@NotBlank
	@Column(name = "firstName")
	private String firstName;
	
	@NotBlank
	@Column(name = "lastName")
	private String lastName;
	
	@NotBlank
	@Column(name = "emailId")
	private String emailId;
	
	@NotBlank
	@Column(name = "password")
	private String password;
	
	@Column(name = "role")
	private String role;
	
	@NotBlank
	@Column(name = "phoneNo")
    private String phoneNo;
	

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "personstorerel", joinColumns = @JoinColumn(name = "personid") , inverseJoinColumns = @JoinColumn(name = "storeid") )
    private Store store;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "personorderrel", inverseJoinColumns = @JoinColumn(name = "orderId") , 
	joinColumns = @JoinColumn(name = "personId") )
    private Set<OrderDetail> orderdetails;








	public Set<OrderDetail> getOrderdetails() {
     	return  this.orderdetails;
	}

	public void setOrderdetails(Set<OrderDetail> orderdetails) {
		this.orderdetails = orderdetails;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}


}

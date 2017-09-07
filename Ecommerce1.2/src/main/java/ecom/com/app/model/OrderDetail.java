package ecom.com.app.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@EnableWebMvc
@Entity(name = "OrderDetail")
@Table(name = "orderdetail")
//@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
public class OrderDetail {
	
	
	
	@Id
	@GenericGenerator(parameters=@Parameter(name="sequence" , value="orderSequence"), strategy="ecom.com.app.idgenerator.IdGenerator", name = "orderSequence")
	@GeneratedValue(generator = "orderSequence")
	@Column(name = "orderId")
	private String orderId;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "personorderrel", inverseJoinColumns = @JoinColumn(name = "personId") , 
	joinColumns = @JoinColumn(name = "orderId") )
    private Person person;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "orderproductrel", joinColumns = @JoinColumn(name = "orderId") , inverseJoinColumns = @JoinColumn(name = "productId") )
	private Set<Product> products;
	  
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}



}

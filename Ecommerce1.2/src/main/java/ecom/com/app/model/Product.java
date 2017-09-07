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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Entity(name = "Product")
@Table(name = "product")
public class Product {
	
	@Id
	@GenericGenerator(parameters=@Parameter(name="sequence" , value="productSequence"), strategy="ecom.com.app.idgenerator.IdGenerator", name = "productSequence")
	@GeneratedValue(generator = "productSequence")
	@Column(name = "productId")
	private String productId;
	
	@Column(name = "productName")
	private String productName;
	
	@Column(name = "productPrice")
	private String productPrice;
	
	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	@ManyToOne
	@JoinTable(name = "productdeptrel", inverseJoinColumns = @JoinColumn(name = "deptId") , 
	joinColumns = @JoinColumn(name = "productId") )
    private Dept dept;
	




	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "orderproductrel", joinColumns = @JoinColumn(name = "productId") , inverseJoinColumns = @JoinColumn(name = "orderId") )
	private Set<OrderDetail> orderdetails;

	public Set<OrderDetail> getOrderdetails() {
		return orderdetails;
	}

	public void setOrderdetails(Set<OrderDetail> orderdetails) {
		this.orderdetails = orderdetails;
	}
}

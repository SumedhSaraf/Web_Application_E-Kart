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
@Entity(name = "Dept")
@Table(name = "department")
//@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
public class Dept {
	
	
	
	@Id
	@GenericGenerator(parameters=@Parameter(name="sequence" , value="departmentSequence"), strategy="ecom.com.app.idgenerator.IdGenerator", name = "departmentSequence")
	@GeneratedValue(generator = "departmentSequence")
	@Column(name = "id")
	private String deptId;
	



	@Column(name = "deptName")
    private String deptName;
     
 

	@ManyToOne
	@JoinTable(name = "deptstorerel", inverseJoinColumns = @JoinColumn(name = "storeId") , 
	joinColumns = @JoinColumn(name = "deptId") )
    private Store store;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "productdeptrel", joinColumns = @JoinColumn(name = "deptId") , inverseJoinColumns = @JoinColumn(name = "productId") )
	private Set<Product> products;
    
	public Set<Product> getProducts() {
		return products;
	}


	public void setProducts(Set<Product> products) {
		this.products = products;
	}


	public String getDeptId() {
		return deptId;
	}


	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}


	public String getDeptName() {
		return deptName;
	}


	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}


	public Store getStore() {
		return store;
	}


	public void setStore(Store store) {
		this.store = store;
	}
}

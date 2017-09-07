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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@EnableWebMvc
@Entity(name = "Store")
@Table(name = "store")
//@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
public class Store {
	

	@Id
	@GenericGenerator(parameters=@Parameter(name="sequence" , value="storeValueSequence"), strategy="ecom.com.app.idgenerator.IdGenerator", name = "storeValueSequence")
	@GeneratedValue(generator = "storeValueSequence")
	@Column(name = "id")
	private String storeId;
	
	@Column(name = "storeName")
	private String storeName;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "street1")
	private String street1;
	
	@NotBlank
	@Column(name = "street2")
	private String street2;
	
	@Column(name = "zipCode")
	private Integer zipCode;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "personstorerel", joinColumns = @JoinColumn(name = "storeid") , inverseJoinColumns = @JoinColumn(name = "personid") )
    private Person person;



public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	//	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "deptstorerel", joinColumns = @JoinColumn(name = "storeId") , inverseJoinColumns = @JoinColumn(name = "deptId") )
	private Set<Dept> depts;
	
	public Set<Dept> getDepts() {
		return depts;
	}

	public void setDepts(Set<Dept> depts) {
		this.depts = depts;
	}
	
	
	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}



/*    @OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private Person person;
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}*/

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}



	
	
	
	

}

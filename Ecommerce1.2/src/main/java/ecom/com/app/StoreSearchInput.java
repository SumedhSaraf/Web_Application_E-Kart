package ecom.com.app;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
public class StoreSearchInput {
	
	@NotBlank
	public String stateName;

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

}

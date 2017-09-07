package ecom.com.app;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
public class LoginPerson {
	
	@NotBlank
	private String emailId;
	
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

	@NotBlank
	private String password;

}

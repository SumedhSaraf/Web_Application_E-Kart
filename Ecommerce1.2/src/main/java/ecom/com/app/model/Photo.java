package ecom.com.app.model;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;



@Entity(name = "Photo")
@Table(name = "photo")
public class Photo {

	@Id
	@Column(name="photoId")
	private String photoId;
	
	
	@Column(name = "photoValue")
	@Lob
	private byte[] photoBytes;
	
	@Column(name = "photoContent")
	private String photoContent;



	
	public String getPhotoId() {
		return photoId;
	}



	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}



	public String getPhotoContent() {
		return photoContent;
	}



	public void setPhotoContent(String photoContent) {
		this.photoContent = photoContent;
	}


	


	public byte[] getPhotoBytes() {
		return photoBytes;
	}

	public void setPhotoBytes(byte[] photoBytes) {
		this.photoBytes = photoBytes;
	}

	
	
	
}

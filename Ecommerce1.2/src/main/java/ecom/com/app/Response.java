package ecom.com.app;

import java.util.LinkedList;
import java.util.List;

public class Response {

	
	public Response() {
		super();
		resultObjects = new LinkedList<Object>();
		errorResults = new LinkedList<Object>();
		
	}
	
	public List<Object> resultObjects;
	public List<Object>  errorResults;
	public List<Object> getResultObjects() {
		return resultObjects;
	}
	public void setResultObjects(List<Object> resultObjects) {
		this.resultObjects = resultObjects;
	}
	public List<Object> getErrorResults() {
		return errorResults;
	}
	public void setErrorResults(List<Object> errorResults) {
		this.errorResults = errorResults;
	}
	

}

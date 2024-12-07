package com.ecommerce.slasa.Utility;

public class Response<T> {
	
	private String message;
	private Boolean status;
	private T data;
	public Response(Boolean status,String message,  T data) {
		super();
		this.message = message;
		this.status = status;
		this.data = data;
	}
	
	public Response(Boolean status,String message) {
		super();
		this.message = message;
		this.status = status;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}

	public Response() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}

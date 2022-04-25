  package com.rest.model;
  
  import com.fasterxml.jackson.annotation.JsonInclude;
  import com.fasterxml.jackson.annotation.JsonInclude.Include;
  
  @JsonInclude(Include.NON_NULL) public class Status1 {
  
  private int id; 
  private String message; 
  private Object details; 

    public Status1(int id, String message, Object  details) {
	  super();
      this.id = id; this.message = message; this.details = details; 
    }
    
    public Status1(String message, Object  details) {
  	  super();
        this.message = message; this.details = details; 
    }
    
    public Status1(int id, String message) {
    	  super();
          this.message = message; this.id = id; 
    }
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getDetails() {
		return details;
	}

	public void setDetails(Object details) {
		this.details = details;
	}

	   
 }
		 
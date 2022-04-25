  package com.rest.model;
  
  import com.fasterxml.jackson.annotation.JsonInclude;
  import com.fasterxml.jackson.annotation.JsonInclude.Include;
  
  @JsonInclude(Include.NON_NULL) public class Status {
  
  private int id; 
  private String message; 
  private Object object; 

    public Status(int id, String message, Object  object) {
	  super();
      this.id = id; this.message = message; this.object = object; 
    }
    
    public Status(String message, Object  object) {
  	  super();
        this.message = message; this.object = object; 
    }
    
    public Status(int id, String message) {
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

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}    
 }
		 
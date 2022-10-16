package com.price.comparision.ecom.model;

import org.springframework.http.HttpStatus;

public class ErrorServiceResponse {
    
    private HttpStatus status;
    
    private String description;

    public ErrorServiceResponse() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ErrorServiceResponse(HttpStatus status, String description) {
        super();
        this.status = status;
        this.description = description;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}

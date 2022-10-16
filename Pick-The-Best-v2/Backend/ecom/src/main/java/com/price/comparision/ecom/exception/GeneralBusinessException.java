package com.price.comparision.ecom.exception;

import com.price.comparision.ecom.model.ErrorServiceResponse;

public class GeneralBusinessException extends MyBusinessException{
    private static final long serialVersionUID = 1L;
    
    private ErrorServiceResponse errorResponse;
    
    public GeneralBusinessException(ErrorServiceResponse response){
        this.errorResponse = response;
    }

    public ErrorServiceResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ErrorServiceResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
    
    
}

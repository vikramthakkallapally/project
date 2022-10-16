package com.price.comparision.ecom.model;

import java.util.Date;

public class AdminStats {
	
	private Date requestDate;
	
	private Long count;
	

    public AdminStats() {
        super();
        // TODO Auto-generated constructor stub
    }

    public AdminStats(Date requestDate, Long count) {
        super();
        this.requestDate = requestDate;
        this.count = count;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

	
}

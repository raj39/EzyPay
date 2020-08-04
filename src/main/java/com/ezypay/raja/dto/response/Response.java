package com.ezypay.raja.dto.response;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Response {

	@JsonProperty("Status")
	String Status;
	
	@JsonProperty("Code")
	long code;
	
	@JsonProperty("Amount")
	String amount;
	
	@JsonProperty("SubscriptionType")
	String subscriptionType;
	
	@JsonProperty("InvoiceDates")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	List<LocalDate> invoiceDates;
    
}

package com.ezypay.raja.dto.request;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FERequest {

	@JsonProperty("SubscriptionType")
	@NotNull(message = "SubscriptionType parameter is missing.")
    @NotEmpty(message = "SubscriptionType parameter is empty")
    @Pattern(regexp = "DAILY|WEEKLY|MONTHLY")
	String subscriptionType;
	
	@JsonProperty("Amount")
	String amount;
	
	@JsonProperty("BillDay")
	String billDay;
	
	@JsonProperty("StartDate")
	@NotNull(message = "StartDate parameter is missing.")
    @NotEmpty(message = "StartDate parameter is empty")
	String startDate;
	
	@JsonProperty("EndDate")
	@NotNull(message = "EndDate parameter is missing.")
    @NotEmpty(message = "EndDate parameter is empty")
	String EndDate;
}

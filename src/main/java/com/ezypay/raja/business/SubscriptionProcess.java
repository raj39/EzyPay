package com.ezypay.raja.business;

import com.ezypay.raja.dto.request.FERequest;
import com.ezypay.raja.dto.response.Response;

public interface SubscriptionProcess {

	Response getInvoiceDates(FERequest request);
	
}

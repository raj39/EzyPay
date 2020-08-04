package com.ezypay.raja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ezypay.raja.business.SubscriptionProcess;
import com.ezypay.raja.dto.request.FERequest;
import com.ezypay.raja.dto.response.Response;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.core.Context;
import javax.validation.Valid;

@Slf4j
@RequestMapping("/api/ezypay")
@RestController
@CrossOrigin
public class EzypayController {

	@Autowired
	SubscriptionProcess subscriptionProcess;
    
    @PostMapping(value = "/subscribe")
    public ResponseEntity<?> updateProduct(@Context HttpServletRequest servletRequest,
			   @Valid @RequestBody FERequest request) throws Exception {
    	
    	if (request.getSubscriptionType().equals("WEEKLY") && (request.getBillDay()!=null || request.getBillDay().isEmpty())
    			|| request.getSubscriptionType().equals("MONTHLY") && (request.getBillDay()!=null || request.getBillDay().isEmpty())
    			|| request.getSubscriptionType().equals("DAILY") && (request.getBillDay()==null)) {
    		Response response = subscriptionProcess.getInvoiceDates(request);
	        return ResponseEntity.ok(response);
    	} else {
    		Response response = new Response();
    		response.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
    		response.setCode(HttpStatus.BAD_REQUEST.value());
    		return ResponseEntity.ok(response);
    	}
    }
}

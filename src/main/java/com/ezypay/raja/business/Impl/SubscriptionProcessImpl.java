package com.ezypay.raja.business.Impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import org.joda.time.DateTimeConstants;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ezypay.raja.business.SubscriptionProcess;
import com.ezypay.raja.dto.request.FERequest;
import com.ezypay.raja.dto.response.Response;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubscriptionProcessImpl implements SubscriptionProcess {
	
	
	@Override
	public Response getInvoiceDates(FERequest request) {

		Response resp = new Response();	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate startDate = LocalDate.parse(request.getStartDate(), formatter);
		LocalDate endDate = LocalDate.parse(request.getEndDate(), formatter);
		LocalDate compareEndate = startDate.plusMonths(3);
		
		if(endDate.compareTo(compareEndate) >= 0) {
			
			resp.setStatus("Only 3 Months is allowed");
			resp.setCode(HttpStatus.BAD_REQUEST.value());
			
		} else {
			
			resp.setStatus(HttpStatus.OK.getReasonPhrase());
			resp.setCode(HttpStatus.OK.value());
			
			resp.setAmount(request.getAmount());
			resp.setSubscriptionType(request.getSubscriptionType());
			if (request.getSubscriptionType().equals("DAILY")) {
				
				long numOfDays = ChronoUnit.DAYS.between(startDate, endDate);
				List<LocalDate> listOfDates = LongStream.range(0, numOfDays)
						.mapToObj(startDate::plusDays)
					 	.collect(Collectors.toList());
				resp.setInvoiceDates(listOfDates);
				
			} else if (request.getSubscriptionType().equals("WEEKLY")) {
				
				List<LocalDate> listOfDates = new ArrayList<LocalDate>();
				while (startDate.isBefore(endDate)){
					
					if (request.getBillDay().equals("MONDAY")) {
						
						if ( startDate.getDayOfWeek().getValue() == DateTimeConstants.MONDAY ){
					    	listOfDates.add(startDate);
					    }
						
					} else if (request.getBillDay().equals("TUESDAY")) {
						
						if ( startDate.getDayOfWeek().getValue() == DateTimeConstants.TUESDAY ){
					    	listOfDates.add(startDate);
					    }
						
					} else if (request.getBillDay().equals("WEDNESDAY")) {
						
						if ( startDate.getDayOfWeek().getValue() == DateTimeConstants.WEDNESDAY ){
					    	listOfDates.add(startDate);
					    }
						
					} else if (request.getBillDay().equals("THURSDAY")) {
						
						if ( startDate.getDayOfWeek().getValue() == DateTimeConstants.THURSDAY ){
					    	listOfDates.add(startDate);
					    }
						
					} else if (request.getBillDay().equals("FRIDAY")) {
						
						if ( startDate.getDayOfWeek().getValue() == DateTimeConstants.FRIDAY ){
					    	listOfDates.add(startDate);
					    }
						
					} else if (request.getBillDay().equals("SATURDAY")) {
						
						if ( startDate.getDayOfWeek().getValue() == DateTimeConstants.SATURDAY ){
					    	listOfDates.add(startDate);
					    }
						
					} else if (request.getBillDay().equals("SUNDAY")) {
						
						if ( startDate.getDayOfWeek().getValue() == DateTimeConstants.SUNDAY ){
					    	listOfDates.add(startDate);
					    }
						
					}
					
					startDate = startDate.plusDays(1);
				}
				resp.setInvoiceDates(listOfDates);
			} else if (request.getSubscriptionType().equals("MONTHLY")) {
				
				long numOfDays = ChronoUnit.DAYS.between(startDate, endDate);
				List<LocalDate> listOfDates = LongStream.range(0, numOfDays)
						.mapToObj(startDate::plusDays)
					 	.collect(Collectors.toList());
				
				List<LocalDate> listOfDatesDisplay = new ArrayList<LocalDate>();
				for (int i = 0; i < listOfDates.size(); i++) {
					String date = listOfDates.get(i).format(formatter);
					
					String dd = date.split("-")[2];
					
					if (dd.equals(request.getBillDay())) {
						listOfDatesDisplay.add(listOfDates.get(i));
					}
				}
				
				resp.setInvoiceDates(listOfDatesDisplay);
				
			}
			
		
		}
		
		return resp;
	}
	

}

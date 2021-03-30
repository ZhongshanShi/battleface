package com.battleface.codeChallengeJay.service;


import com.battleface.codeChallengeJay.model.CustomerInfo;
import com.battleface.codeChallengeJay.model.QuotationToCustomer;
import com.battleface.codeChallengeJay.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class QuotationService {


    @Autowired
    private CustomerRepository customerRepository;

    private final BigDecimal[] AGE_LOAD_TABLE = {new BigDecimal(0.6),
            new BigDecimal(0.7),
            new BigDecimal(0.8),
            new BigDecimal(0.9),
            new BigDecimal(1)
            };


    public QuotationToCustomer getQuotation(CustomerInfo customerInfo) throws ParseException {
        Random random = new Random();
        int quotationId = random.nextInt(1000);
        QuotationToCustomer quotationToCustomer = new QuotationToCustomer();
        BigDecimal totalPrice = calculateTotalPrice(customerInfo);
        quotationToCustomer.setCurrencyId(customerInfo.getCurrencyId());
        quotationToCustomer.setTotalPrice(totalPrice);
        quotationToCustomer.setQuotationId(quotationId);
        customerRepository.save(quotationToCustomer);

        return quotationToCustomer;
    }
    public List<QuotationToCustomer> getAllQuotations() {
        return customerRepository.findAll();
    }
    private BigDecimal calculateTotalPrice(CustomerInfo customerInfo) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        Date startDate = simpleDateFormat.parse(customerInfo.getStartDate());
        Date endDate = simpleDateFormat.parse(customerInfo.getEndDate());
        long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());
        long days = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS)+1;
        BigDecimal totalPrice = BigDecimal.ZERO;
        BigDecimal cost;
        List<BigDecimal> ageLoadList = getAgeLoadList(customerInfo);
        cost = ageLoadList.stream().reduce(BigDecimal.ZERO,BigDecimal::add).multiply(new BigDecimal(3*days));
        totalPrice = totalPrice.add(cost);
        totalPrice = totalPrice.setScale(2, BigDecimal.ROUND_HALF_EVEN);

        return  totalPrice;
    }
    private List<BigDecimal> getAgeLoadList(CustomerInfo customerInfo) {
        String customersAgeStr = customerInfo.getAge();
        List<String> customersAgeStrList = Arrays.asList(customersAgeStr.split(","));

        List<Integer> customersAgeIntList = customersAgeStrList.stream()
                .map(t -> Integer.parseInt(t))
                .collect(Collectors.toList());

        List<BigDecimal> ageLoadList = customersAgeIntList.stream().map(t -> { if(t>=18&&t<=30) {
                return AGE_LOAD_TABLE[0];}
            else if(t>=31&&t<=40) {
                return AGE_LOAD_TABLE[1];}
            else if(t>=41&&t<=50) {
                return AGE_LOAD_TABLE[2];}
            else if(t>=51&&t<=60) {
                return AGE_LOAD_TABLE[3];}
            else return AGE_LOAD_TABLE[4];
        }).collect(Collectors.toList());

        return ageLoadList;
    }

}

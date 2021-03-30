package com.battleface.codeChallengeJay.controller;


import com.battleface.codeChallengeJay.model.*;
import com.battleface.codeChallengeJay.service.MyUserDetailsService;
import com.battleface.codeChallengeJay.service.QuotationService;
import com.battleface.codeChallengeJay.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/battleface")
public class Controller {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;


    @Autowired
    private QuotationService quotationService;


    @GetMapping("/healthcheck")
    public ResponseEntity<ResponseMessage> getMessage() {
        String message = "Healthcheck successful";
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(200,message));
    }

    @GetMapping("/allQuotations")
    public List<QuotationToCustomer> getAllQuotations() {

        return quotationService.getAllQuotations();

    }

    @PostMapping(value = "/quotation", consumes = "application/json", produces = "application/json")
    public ResponseEntity<QuotationToCustomer> getQuotation (@RequestBody CustomerInfo customerInfo) {

       try {
           String customersAgeStr = customerInfo.getAge();
           List<String> customersAgeStrList = Arrays.asList(customersAgeStr.split(","));
           List<Integer> customersAgeIntList = customersAgeStrList.stream()
                   .map(t -> Integer.parseInt(t))
                   .collect(Collectors.toList());
           for(int i=0;i<customersAgeIntList.size();i++) {
               if(customersAgeIntList.get(i)<18||customersAgeIntList.get(i)>70||customersAgeIntList.get(i)==0) {
                   return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
               } }
                   QuotationToCustomer quotationToCustomer = quotationService.getQuotation(customerInfo);
                   return new ResponseEntity<>(quotationToCustomer,HttpStatus.OK);

       } catch (Exception e) {
           return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }




    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }


        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}

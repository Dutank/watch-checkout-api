package com.arcual.challenge.controller;

import com.arcual.challenge.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WatchController {

    @Autowired
    private CheckoutService checkoutService;

    @PostMapping(value = "/checkout",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> checkout(@RequestBody List<String> watches) {
        Long totalPrice = checkoutService.checkout(watches);
        return ResponseEntity.status(HttpStatus.OK).body(totalPrice);
    }
}

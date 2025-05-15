package com.Geek.controller;

import com.Geek.dto.request.StoreRequestDto;
import com.Geek.model.Store;
import com.Geek.service.StoreService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/v1")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping("/store")
    public ResponseEntity<Store> createStore(@RequestBody @Valid StoreRequestDto storeRequest) {
        Store store = storeService.createStore(storeRequest);
//        Store store = new Store();
        return new ResponseEntity<>(store, HttpStatus.CREATED);
    }
}